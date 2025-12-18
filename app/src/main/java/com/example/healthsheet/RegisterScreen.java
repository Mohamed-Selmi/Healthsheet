package com.example.healthsheet;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
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

import java.sql.SQLDataException;

public class RegisterScreen extends AppCompatActivity {
    private Button register;
    private Button login;
    private EditText email;
    private EditText username;

    private EditText password;

    UserManager userManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);

        register=(Button) this.findViewById(R.id.register);
        this.email=(EditText) this.findViewById(R.id.emailInput);
        this.username=(EditText) this.findViewById(R.id.usernameinput);
        this.password=(EditText) this.findViewById(R.id.passwordInput);
        userManager=new UserManager(this);
        try{
            //userManager.openToWrite(DataBaseHelper.DB_NAME)
            userManager.open();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
        register.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {
                        try{
                          String userMail=email.getText().toString();
                          String userName=username.getText().toString();
                          String userPassword=password.getText().toString();
                          User new_user=new User(userMail,userName,userPassword);
                            if (userManager.insertUser(new_user)==-1){
                                Toast.makeText(getApplicationContext(),"User already exists!",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Account created successfully!",Toast.LENGTH_LONG).show();
                            }
                        }
                        catch(SQLiteConstraintException e){
                            Toast.makeText(getApplicationContext(),"Email already in use! \n"+e.getMessage(),Toast.LENGTH_LONG).show();

                        }

                    }
                }
        );
        //login.setOnClickListener();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}