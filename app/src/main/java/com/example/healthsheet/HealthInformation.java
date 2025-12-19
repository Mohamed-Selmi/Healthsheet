package com.example.healthsheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthsheet.entities.User;

public class HealthInformation extends AppCompatActivity {
    private ImageButton diary;
    private ImageButton profile;
    private ImageButton health;
    private ImageButton history;
    private ImageButton bodyfat;
    User currentUser;

    UserManager userManager;

    CalorieLogManager calorieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_information);
        String mail=getIntent().getStringExtra("mail");
        userManager=new UserManager(this);
        calorieManager=new CalorieLogManager(this);
        try{
            userManager.open();
            calorieManager.open();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
        try{
            currentUser=userManager.getUser(mail);
        }
        catch(Exception e){
            Log.d("myTag", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        this.diary=this.findViewById(R.id.diary);
        this.profile=this.findViewById(R.id.profile);
        this.health=this.findViewById(R.id.healthinfo);
        this.history=this.findViewById(R.id.history);
        this.bodyfat=this.findViewById(R.id.bodyfat);







        diary.setOnClickListener(v -> {
            Intent diary=new Intent(getApplicationContext(), CaloriesLogScreen.class);
            Toast.makeText(getApplicationContext(), "navigating to login!", Toast.LENGTH_LONG).show();
            diary.putExtra("mail",currentUser.getEmail());
            startActivity(diary);
        });
        bodyfat.setOnClickListener(v -> {
            Intent bodyfat=new Intent(getApplicationContext(), BodyMeasurements.class);
            Toast.makeText(getApplicationContext(), "navigating to login!", Toast.LENGTH_LONG).show();
            bodyfat.putExtra("mail",currentUser.getEmail());
            startActivity(bodyfat);
        });
        health.setOnClickListener(v -> {
            Intent health=new Intent(getApplicationContext(), HealthInformation.class);
            Toast.makeText(getApplicationContext(), "navigating to login!", Toast.LENGTH_LONG).show();
            health.putExtra("mail",currentUser.getEmail());
            startActivity(health);
        });
        history.setOnClickListener(v -> {
            Intent history=new Intent(getApplicationContext(), LogHistory.class);
            Toast.makeText(getApplicationContext(), "navigating to login!", Toast.LENGTH_LONG).show();
            history.putExtra("mail",currentUser.getEmail());
            startActivity(history);
        });
        profile.setOnClickListener(v -> {
            Intent profile=new Intent(getApplicationContext(), UserProfileScreen.class);
            Toast.makeText(getApplicationContext(), "navigating to login!", Toast.LENGTH_LONG).show();
            profile.putExtra("mail",currentUser.getEmail());
            startActivity(profile);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}