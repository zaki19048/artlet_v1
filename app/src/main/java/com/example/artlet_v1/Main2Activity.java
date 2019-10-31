package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button b = (Button) findViewById(R.id.button10);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Log.d("DFgfd", "onClick: sadasd");
            }
        });
    }
}
