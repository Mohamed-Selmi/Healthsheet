package com.example.healthsheet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthsheet.entities.User;

public class update_profile extends AppCompatActivity {
    private User currentUser;

    private UserManager userManager;

    private String name;

    private Button goback;

    private EditText email;
    private EditText username;

    private EditText password;

    private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_profile);
        update=(Button) this.findViewById(R.id.update);
        this.email=(EditText) this.findViewById(R.id.emailInput);
        this.username=(EditText) this.findViewById(R.id.usernameinput);
        this.password=(EditText) this.findViewById(R.id.passwordInput);
        goback=(Button) this.findViewById(R.id.goback);
        userManager=new UserManager(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}