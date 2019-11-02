package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TestActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_actvity);
        Intent intent = getIntent();
        int user_id = intent.getIntExtra("view_userid",1);
        String users= String.valueOf(user_id);
        Toast.makeText(this, users,Toast.LENGTH_LONG).show();

    }

    public void getOnBackPress() {
        this.startActivity(new Intent(TestActvity.this,DashboardActivity.class));
        finish();
        return;
    }


}
