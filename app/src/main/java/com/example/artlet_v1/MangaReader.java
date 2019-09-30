package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class MangaReader extends AppCompatActivity {

    private ViewPager viewpager;
    private FragmentCollectionAdapter adapter;

    //static int[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_reader);
        viewpager = findViewById(R.id.pager);
        //loadDemoImages();
        adapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setPageTransformer(false, new ZoomOutPageTransformer());
    }

   /* void loadDemoImages()
    {
        drawables = new int[]{R.drawable.m4, R.drawable.m5, R.drawable.m6,R.drawable.m7,
                R.drawable.m8, R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12,
                R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16, R.drawable.m17,
                R.drawable.m18, R.drawable.m19, R.drawable.m20 };
        InputStream inputStream = null;
        try {

            AssetManager assetManager = getAssets();
            String[] images = assetManager.list("op");
            Log.d("nicki", ""+images.length);
            drawables = new Drawable[images.length];
            for (int i = 0; i < images.length; i++) {
                Log.d("nicki", "dogs/" + images[i]);
                Log.d("nicki", "i = " + i);

                //       inputStream = assetManager.open("dogs/" + images[i]);
                //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
         //       Drawable drawable = Drawable.createFromStream(inputStream, null);
         //       drawables[i] = drawable;
            }
        } catch (IOException e) {
            Log.d("nicki", "IMAGE READING FAILED");
        }
      }
        */

}
