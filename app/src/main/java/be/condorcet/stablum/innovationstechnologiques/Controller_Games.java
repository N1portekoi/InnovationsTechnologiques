package be.condorcet.stablum.innovationstechnologiques;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Controller_Games extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_games);
        // Connexion à la DB
        Task_Games task_games = new Task_Games(this);
        task_games.execute();
    }
}
