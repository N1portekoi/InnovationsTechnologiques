package be.condorcet.stablum.innovationstechnologiques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Controller_Connection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
    }

    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn: // Verification Login/Password
                // EditTexts
                EditText editPseudo = (EditText)findViewById(R.id.editPseudo);
                EditText editPassword = (EditText)findViewById(R.id.editPassword);
                // Login/Password avec trim()
                String login = editPseudo.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                // Connexion a la DB
                Task_Connection task_Connection = new Task_Connection(this);
                task_Connection.execute(login, password);
                // Erase
                editPseudo.setText("");
                editPassword.setText("");
                break;
            case R.id.btnSignUp: // Appel l'écran d'enregistrement
                Intent intent = new Intent(Controller_Connection.this, Controller_Register.class);
                startActivity(intent);
                break;
        }
    }
}
