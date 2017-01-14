package be.condorcet.stablum.innovationstechnologiques;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ludovic on 27-12-16.
 */

public class Task_Register extends AsyncTask<String, Void, Integer> {
    private Context context;

    public Task_Register(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Integer doInBackground(String... params) {
        int code = -1;
        try {
            // DATABASE
            URL url = new URL(DATABASE.getURL() + "creer_compte.php");
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
            JsonReader json = new JsonReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            json.beginObject();
            while (json.hasNext()) {
                switch (json.nextName()) {
                    case "code":
                        code = json.nextInt();
                        break;
                    //case "id": // Si besoin de l'id
                    //    id = json.nextInt();
                    //    break;
                }
            }
            json.endObject();
            json.close();
            connection.disconnect();
        }
        catch (Exception e) {}
        return code;
    }

    @Override
    protected void onProgressUpdate(Void... progress) {
    }

    @Override
    protected void onPostExecute(Integer result)  {
        switch (result) {
            case 0:
                Toast.makeText(context, R.string.R0, Toast.LENGTH_SHORT).show();
                ((Activity) context).finish();
                break;
            case 100:
                Toast.makeText(context, R.string.R100, Toast.LENGTH_SHORT).show();
                break;
            case 110:
                Toast.makeText(context, R.string.R110, Toast.LENGTH_SHORT).show();
                break;
            case 200:
                Toast.makeText(context, R.string.R200, Toast.LENGTH_SHORT).show();
                break;
            case 1000:
                Toast.makeText(context, R.string.DB1000, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, R.string.DB2000, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}