package be.condorcet.stablum.innovationstechnologiques;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.JsonReader;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludovic on 27-12-16.
 */

public class Task_Users extends AsyncTask<String, Void, Integer> {
    private Context context;
    private EditText editText;
    private List<String> list;


    public Task_Users(Context context) {
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
            // URL
            URL url = new URL("http://192.168.1.4/php/lister_pseudos.php");
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
                // Affichage des utilisateurs
                LinearLayout linearLayout = (LinearLayout)((Activity)context).findViewById(R.id.llusers);
                for (int i = 0; i < list.size(); i++) {
                    TextView textView = new TextView(context);
                    textView.setText(list.get(i));
                    textView.setWidth(45);
                    textView.setHeight(75);
                    textView.setBackgroundResource(R.drawable.shape);
                    textView.setGravity(Gravity.CENTER);
                    RelativeLayout.LayoutParams llp = (new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    llp.setMargins(50, 50, 50, 0);
                    textView.setLayoutParams(llp);
                    linearLayout.addView(textView);
                }
                break;
            case 500:
                Toast.makeText(context, R.string.U500, Toast.LENGTH_SHORT).show();
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