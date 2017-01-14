package be.condorcet.stablum.innovationstechnologiques;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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

public class Task_Games extends AsyncTask<Void, Void, Integer> {
    private Context context;
    private List<String> list;

    public Task_Games(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int code = -1;
        String s;
        try {
            // Liste jeux
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
                    s = json.nextString();
                    json.endObject();
                    // Top 1 associé au jeu
                    // URL2
                    URL url2 = new URL(DATABASE.getURL() + "afficher_top.php?jeu="+s);
                    HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                    connection2.setRequestMethod("GET");
                    connection2.setRequestProperty("Content-Type", "text/plain");
                    connection2.setRequestProperty("charset", "utf-8");
                    connection2.setAllowUserInteraction(false);
                    // Reading2
                    JsonReader json2 = new JsonReader(new InputStreamReader(connection2.getInputStream(), "UTF-8"));
                    json2.beginObject();
                    if (json2.hasNext()) {
                        json2.nextName();
                        code = json2.nextInt();
                        json2.nextName();
                        json2.beginArray();
                        if (json2.hasNext()) {
                            json2.beginObject();
                            json2.nextName();
                            s = context.getResources().getString(R.string.game) + " : " + s + " - TOP 1 : " + json2.nextString();
                            json2.nextName();
                            json2.nextString();
                            //s += json2.nextString();
                            list.add(s);
                            json2.endObject();
                        }
                    }
                    json2.close();
                    connection2.disconnect();
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
                // Affichage jeu + top 1 + boutton
                LinearLayout linearLayout = (LinearLayout)((Activity)context).findViewById(R.id.llGames);
                RelativeLayout.LayoutParams llp = (new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                llp.setMargins(50, 50, 50, 0);
                for (int i = 0; i < list.size(); i++) {
                    TextView textView = new TextView(context);
                    textView.setText(list.get(i));
                    textView.setWidth(45);
                    textView.setHeight(75);
                    textView.setBackgroundResource(R.drawable.shape);
                    textView.setGravity(Gravity.CENTER);
                    textView.setLayoutParams(llp);
                    linearLayout.addView(textView);
                }
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