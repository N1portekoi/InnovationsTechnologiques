package be.condorcet.stablum.innovationstechnologiques;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ludovic on 26-12-16.
 */

public class Controller_Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_register);
    }

    protected void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnRegister:
                String login = ((EditText)findViewById(R.id.editPseudo)).getText().toString();
                String password = ((EditText)findViewById(R.id.editPassword)).getText().toString();
                String confirmPassword = ((EditText)findViewById(R.id.editConfirmPassword)).getText().toString();
                if (password.equals(confirmPassword)) {
                    // Task
                    Task_Register task_register = new Task_Register(this);
                    task_register.execute(login.trim(), password.trim());
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.R120, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}