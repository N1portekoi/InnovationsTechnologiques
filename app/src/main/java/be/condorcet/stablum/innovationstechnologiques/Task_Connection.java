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
    public Context context;

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
            // URL
            URL url = new URL("http://192.168.1.4/php/se_connecter.php");
            HttpURLConnection UrlConn = (HttpURLConnection) url.openConnection();
            UrlConn.setRequestMethod("POST");
            UrlConn.setAllowUserInteraction(false);

            // POST Parameters
            OutputStream os = UrlConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write("pseudo="+params[0] + "&mdp="+params[1]);
            writer.flush();
            writer.close();
            os.close();

            // Reading
            InputStream Response = UrlConn.getInputStream();
            BufferedReader BufReader = new BufferedReader(new InputStreamReader(Response, "UTF-8"));
            while ((line = BufReader.readLine()) != null)
            {
                Sb.append(line);
            }
            UrlConn.disconnect();
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
                Toast.makeText(context, R.string.C1000, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, R.string.C2000, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}