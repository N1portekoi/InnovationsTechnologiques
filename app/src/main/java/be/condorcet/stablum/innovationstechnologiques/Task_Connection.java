package be.condorcet.stablum.innovationstechnologiques;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ludovic on 27-12-16.
 */
public class Task_Connection extends AsyncTask<String, Void, String> {
    private Context context;
    private String login;

    public Task_Connection (Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder Sb = new StringBuilder();
        String line;
        try {
            // DATABASE
            URL url = new URL(DATABASE.getURL() + "se_connecter.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setAllowUserInteraction(false);

            // POST Parameters
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write("pseudo="+params[0] + "&mdp="+params[1]);
            writer.flush();
            writer.close();
            os.close();

            // Reading
            InputStream Response = connection.getInputStream();
            BufferedReader BufReader = new BufferedReader(new InputStreamReader(Response, "UTF-8"));
            while ((line = BufReader.readLine()) != null)
            {
                Sb.append(line);
                login = params[0];
            }
            connection.disconnect();
        }
        catch (Exception e) {

        }
        return Sb.toString().replace(String.valueOf((char)65279), ""); // Elimimnation d'un caractere unicode dans le cas de l'erreur 0
    }

    @Override
    protected void onProgressUpdate(Void... progress) {
    }

    @Override
    protected void onPostExecute(String result)  {
        switch (result) {
            case "0":
                Intent intent = new Intent(context, Controller_Menu.class);
                intent.putExtra("login", login);
                context.startActivity(intent);
                break;
            case "100":
                Toast.makeText(context, R.string.C100, Toast.LENGTH_SHORT).show();
                break;
            case "110":
                Toast.makeText(context, R.string.C110, Toast.LENGTH_SHORT).show();
                break;
            case "200":
                Toast.makeText(context, R.string.C200, Toast.LENGTH_SHORT).show();
                break;
            case "1000":
                Toast.makeText(context, R.string.DB1000, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, R.string.DB2000, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}