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
            case R.id.btnSignIn: // Sign In
                // User
                User user = new User();
                user.setLogin(((EditText)findViewById(R.id.editPseudo)).getText().toString().trim());
                user.setPassword(((EditText)findViewById(R.id.editPassword)).getText().toString().trim());
                // AsyncTask
                Task_Connection task_Connection = new Task_Connection(this);
                task_Connection.execute(user.getLogin(), user.getPassword());
                break;
            case R.id.btnSignUp: // Sign Up
                Intent intent = new Intent(Controller_Connection.this, Controller_Register.class);
                startActivity(intent);
                break;
        }
    }
}
