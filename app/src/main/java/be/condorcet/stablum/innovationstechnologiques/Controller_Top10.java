package be.condorcet.stablum.innovationstechnologiques;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Controller_Top10 extends AppCompatActivity {
    private EditText editGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_top10);
        // Recuperation
        editGame = (EditText)findViewById(R.id.editGame);
    }

    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGame:
                Task_GameList task_gameList = new Task_GameList(this);
                task_gameList.execute();
                break;
            case R.id.btnTop10Display:
                Task_Top10 task_top10 = new Task_Top10(this);
                task_top10.execute(editGame.getText().toString());
                break;
        }

    }
}