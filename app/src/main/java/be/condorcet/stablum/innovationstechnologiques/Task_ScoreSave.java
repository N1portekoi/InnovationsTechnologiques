package be.condorcet.stablum.innovationstechnologiques;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
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

public class Task_ScoreSave extends AsyncTask<String, Void, String> {
    private Context context;

    public Task_ScoreSave(Context context) {
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
            URL url = new URL("http://192.168.1.4/php/ajouter_score.php?"+
                    "jeu="+params[0]+
                    "&score="+params[1]+
                    "&pseudo="+params[2]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");
            connection.setAllowUserInteraction(false);

            // Reading
            InputStream Response = connection.getInputStream();
            BufferedReader BufReader = new BufferedReader(new InputStreamReader(Response, "UTF-8"));
            while ((line = BufReader.readLine()) != null)
            {
                Sb.append(line);
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
                Toast.makeText(context, R.string.SS0, Toast.LENGTH_SHORT).show();
                ((Activity) context).finish();
                break;
            case "100":
                Toast.makeText(context, R.string.SS100, Toast.LENGTH_SHORT).show();
                break;
            case "110":
                Toast.makeText(context, R.string.SS110, Toast.LENGTH_SHORT).show();
                break;
            case "1000":
                Toast.makeText(context, R.string.SS1000, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, R.string.SS2000, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}