package com.example.artlet_v1;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MangaReader extends AppCompatActivity {

    private ViewPager viewpager;
    private FragmentCollectionAdapter adapter;

    //static int[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_reader);
        viewpager = findViewById(R.id.pager);
        createFolder("imageDir");

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getExternalFilesDir("imageDir/Blep");
        List<File> fileList = new ArrayList<>();
        for(int i=4;i<20;i++)
        {
            File file = new File(directory, "m" +i+ ".jpg");
            fileList.add(file);
        }
        ImageFragment.images = fileList;

        adapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setPageTransformer(false, new ZoomOutPageTransformer());
    }

    public void createFolder(String fname) {
        String myfolder = getApplicationContext().getExternalFilesDir("/") + "/" + fname;
        File f = new File(myfolder);
        if (!f.exists())
            if (!f.mkdir()) {
                Toast.makeText(this, myfolder + " can't be created.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, myfolder + " can be created.", Toast.LENGTH_LONG).show();
                f.mkdirs();
            }
        else
            Toast.makeText(this, myfolder + " already exits.", Toast.LENGTH_LONG).show();

        f = new File(getApplicationContext().getExternalFilesDir("/") + "/" + fname + "/" + "Blep");
        if (!f.exists())
        {
            Toast.makeText(this, "Unzipped", Toast.LENGTH_LONG).show();
            String zipfilename = "Blep";
            String zipFile = myfolder + "/" + zipfilename + ".zip";
            String unzipLocation = myfolder + "/" + zipfilename + "/";
            Log.d("Zip", zipFile);
            Log.d("Zip Loc ", myfolder);
            ZipDecompress df = new ZipDecompress(zipFile, unzipLocation);
            df.unzip();
        }
        else
        {
            Toast.makeText(this, "Already Unzipped", Toast.LENGTH_LONG).show();
        }
    }
}
