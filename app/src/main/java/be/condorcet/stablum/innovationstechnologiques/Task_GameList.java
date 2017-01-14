package be.condorcet.stablum.innovationstechnologiques;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.JsonReader;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludovic on 27-12-16.
 */

public class Task_GameList extends AsyncTask<Void, Void, Integer> {
    private Context context;
    private EditText editText;
    private List<String> list;


    public Task_GameList(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int code = -1;
        try {
            // DATABASE
            URL url = new URL(DATABASE.getURL() + "lister_jeux.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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
                    list.add(json.nextString());
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
                dialogBuilder.setTitle(R.string.titleGames);
                dialogBuilder.setItems(games, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Affiche le choix de l'utilisateur dans editText
                        String selectedText = games[item].toString();
                        editText = ((EditText)((Activity)context).findViewById(R.id.editGame));
                        editText.setText(selectedText);
                    }
                });
                AlertDialog alertDialogObject = dialogBuilder.create();
                alertDialogObject.show();
                break;
            case 500:
                Toast.makeText(context, R.string.Gl500, Toast.LENGTH_SHORT).show();
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