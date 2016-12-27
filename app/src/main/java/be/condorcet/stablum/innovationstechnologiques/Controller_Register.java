package be.condorcet.stablum.innovationstechnologiques;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.condorcet.stablum.innovationstechnologiques.Beans.User;

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
        User user;
        switch (view.getId()) {
            case R.id.btnRegister:
                user = new User();
                user.setLogin(((EditText)findViewById(R.id.editPseudo)).getText().toString().trim());
                user.setPassword(((EditText)findViewById(R.id.editPassword)).getText().toString().trim());
                finish();
                break;
        }
    }
}