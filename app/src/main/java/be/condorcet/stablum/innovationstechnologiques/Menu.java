package be.condorcet.stablum.innovationstechnologiques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ludovic on 27-12-16.
 */

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    protected void onClickScore(View view) {
        Intent intent = new Intent(Menu.this, Score.class);
        startActivity(intent);
    }

    protected void onClickTop10(View view) {
        Intent intent = new Intent(Menu.this, Top10.class);
        startActivity(intent);
    }

    protected void onClickGames (View view) {
        Intent intent = new Intent(Menu.this, Games.class);
        startActivity(intent);
    }

    protected void onClickUsers (View view) {
        Intent intent = new Intent(Menu.this, Users.class);
        startActivity(intent);
    }

    protected void onClickSignOut (View view) {

    }
}
