package com.example.healthsheet;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthsheet.entities.User;

public class UserProfileScreen extends AppCompatActivity {
    User currentUser;

    UserManager userManager;
    private Button logout;
    private TextView userNameDisplay;

    private TextView userEmailDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile_screen);
        userEmailDisplay=this.findViewById(R.id.emailDB);
        userNameDisplay=this.findViewById(R.id.usernameDB);
        logout=this.findViewById(R.id.logout);
        String mail=getIntent().getStringExtra("mail");
        Log.d("myTag","test in profile:"+mail);

        userManager=new UserManager(this);
        try{
            userManager.open();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
        try {
            currentUser = userManager.getUser(mail);
        }
        catch(Exception e){
            Log.d("myTag", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        if(currentUser!=null){
            Toast.makeText(getApplicationContext(), currentUser.getEmail(), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "This isn't working", Toast.LENGTH_LONG).show();

        }
        userEmailDisplay.setText(currentUser.getEmail());
        userNameDisplay.setText(currentUser.getUsername());
        logout.setOnClickListener(v -> {
            Intent logout=new Intent(getApplicationContext(), LoginScreen.class);
            Toast.makeText(getApplicationContext(), "navigating to login!", Toast.LENGTH_LONG).show();

            startActivity(logout);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}