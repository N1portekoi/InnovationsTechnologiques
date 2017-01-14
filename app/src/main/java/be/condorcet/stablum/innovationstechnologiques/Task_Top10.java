package be.condorcet.stablum.innovationstechnologiques;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.JsonReader;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludovic on 27-12-16.
 */

public class Task_Top10 extends AsyncTask<String, Void, Integer> {
    private Context context;
    private EditText editText;
    private List<String> list;


    public Task_Top10(Context context) {
        this.context = context;
        editText = ((EditText)((Activity)context).findViewById(R.id.editGame));
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Integer doInBackground(String... params) {
        int code = -1;
        String s;
        try {
            // DATABASE
            URL url = new URL(DATABASE.getURL() + "afficher_top.php?"+
                    "jeu="+params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");
            connection.setAllowUserInteraction(false);

            // Reading
            JsonReader json = new JsonReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            json.beginObject();
            list = new ArrayList<>();
            if (json.hasNext()) {
                json.nextName();
                code = json.nextInt();
                json.nextName();
                json.beginArray();

                while (json.hasNext()) {
                    json.beginObject();
                    json.nextName();
                    s = json.nextString();
                    json.nextName();
                    s += " (" + json.nextString() + ")";
                    list.add(s);
                    json.endObject();
                }
            }
            json.endObject();
            json.close();
            connection.disconnect();

        }
        catch (Exception e) {

        }
        return code;
    }

    @Override
    protected void onProgressUpdate(Void... progress) {
    }

    @Override
    protected void onPostExecute(Integer result)  {
        switch (result) {
            case 0:
                // Create sequence of items
                final CharSequence[] games = list.toArray(new String[list.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder.setTitle("Top 10 : " + editText.getText());
                dialogBuilder.setItems(games, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        editText.setText("");
                    }
                });
                AlertDialog alertDialogObject = dialogBuilder.create();
                alertDialogObject.show();
                break;
            case 100:
                Toast.makeText(context, R.string.T100, Toast.LENGTH_SHORT).show();
                break;
            case 500:
                Toast.makeText(context, R.string.T500, Toast.LENGTH_SHORT).show();
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