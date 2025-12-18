package com.example.healthsheet;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthsheet.entities.User;

public class LoginScreen extends AppCompatActivity {
    private Button register;
    private Button login;
    private EditText email;
    private EditText password;

    UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        this.register=(Button) this.findViewById(R.id.RegisterL);
        this.email=(EditText) this.findViewById(R.id.emailInput);
        this.password=(EditText) this.findViewById(R.id.passwordInput);
        this.login=(Button) this.findViewById(R.id.login);
        userManager=new UserManager(this);
        try{
            //userManager.openToWrite(DataBaseHelper.DB_NAME)
            userManager.open();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userMail = email.getText().toString();
                    String userPassword = password.getText().toString();
                    Log.d("myTag",userMail);
                    User loginUser = new User(userMail, userPassword);
                    if (userManager.loginUser(loginUser) == false) {
                        Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "User logged in successfully!", Toast.LENGTH_LONG).show();
                        Intent loginNav=new Intent(getApplicationContext(),UserProfileScreen.class);
                        loginNav.putExtra("mail",userMail);
                        startActivity(loginNav);
                    }
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "something went wrong! \n" + e.getMessage(), Toast.LENGTH_LONG).show();

                }
                catch (Exception e1){
                    Toast.makeText(getApplicationContext(), "something went wrong! \n" + e1.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
        register.setOnClickListener(v -> {
            Intent register=new Intent(getApplicationContext(), RegisterScreen.class);
            Toast.makeText(getApplicationContext(), "navigating to register!", Toast.LENGTH_LONG).show();

            startActivity(register);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}