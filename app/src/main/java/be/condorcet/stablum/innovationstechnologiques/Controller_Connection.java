package be.condorcet.stablum.innovationstechnologiques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.condorcet.stablum.innovationstechnologiques.Beans.User;

public class Controller_Connection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
    }

    protected void onClick(View view) {
        Intent intent;
        User user;

        switch (view.getId()) {
            case R.id.btnSignIn:
                user = new User();
                user.setLogin(((EditText)findViewById(R.id.editPseudo)).getText().toString().trim());
                user.setPassword(((EditText)findViewById(R.id.editPassword)).getText().toString().trim());
                intent = new Intent(Controller_Connection.this, Controller_Menu.class);
                startActivity(intent);
                break;
            case R.id.btnSignUp:
                intent = new Intent(Controller_Connection.this, Controller_Register.class);
                startActivity(intent);
                break;
        }
    }
}
