package be.condorcet.stablum.innovationstechnologiques;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import be.condorcet.stablum.innovationstechnologiques.Beans.User;

public class Controller_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_menu);
    }

    protected void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnScore:
                intent = new Intent(Controller_Menu.this, Controller_Score.class);
                startActivity(intent);
                break;
            case R.id.btnTop10:
                intent = new Intent(Controller_Menu.this, Controller_Top10.class);
                startActivity(intent);
                break;
            case R.id.btnGames:
                intent = new Intent(Controller_Menu.this, Controller_Games.class);
                startActivity(intent);
                break;
            case R.id.btnUsers:
                intent = new Intent(Controller_Menu.this, Controller_Users.class);
                startActivity(intent);
                break;
            case R.id.btnSignOut:
                finish();
                break;
        }
    }
}
