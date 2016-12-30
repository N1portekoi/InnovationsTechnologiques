package be.condorcet.stablum.innovationstechnologiques;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Controller_Score extends AppCompatActivity {
    private String login;
    private EditText editGame;
    private EditText editScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_score);
        // Recuperation
        login = getIntent().getStringExtra("login");
        editGame = (EditText)findViewById(R.id.editGame);
        editScore = (EditText)findViewById(R.id.editScore);
    }

    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGame:
                Task_GameList task_gameList = new Task_GameList(this);
                task_gameList.execute();
                break;
            case R.id.btnSaveScore:
                try {// Test si nombre valide
                    Integer.valueOf(editScore.getText().toString());
                    // Requete serveur
                    Task_ScoreSave task_scoreSave = new Task_ScoreSave(this);
                    task_scoreSave.execute(editGame.getText().toString(), editScore.getText().toString(), login);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), R.string.SS90, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}