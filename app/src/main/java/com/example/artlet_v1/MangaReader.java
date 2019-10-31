package com.example.artlet_v1;

import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        for (int i = 4; i < 20; i++) {
            File file = new File(directory, "m" + i + ".jpg");
            fileList.add(file);
        }
        ImageFragment.images = fileList;

        adapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setPageTransformer(false, new ZoomOutPageTransformer());
    }

    public void createFolder(String fname) {

        //make directory
        String myfolder = getApplicationContext().getExternalFilesDir("/") + "/" + fname;
        File f = new File(myfolder);
        if (!f.exists())
            if (!f.mkdir()) {
                Toast.makeText(this, myfolder + " can't be created.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, myfolder + " can be created.", Toast.LENGTH_LONG).show();
                f.mkdirs();

                ///////////////////////////////////////////////////////////////////
                // Copy file from assets to folder
                AssetManager assetManager = getAssets();
                String[] files = null;
                try {
                    files = assetManager.list("");
                } catch (IOException e) {
                    Log.e("tag", "Failed to get asset file list.", e);
                }
                for (String filename : files) {
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = assetManager.open(filename);

                        File outFile = new File(myfolder, filename);

                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    } catch (IOException e) {
                        Log.e("tag", "Failed to copy asset file: " + filename, e);
                    }
                }
                ///////////////////////////////////////////////////////////////////////////
            }
        else
            Toast.makeText(this, myfolder + " already exits.", Toast.LENGTH_LONG).show();


        // unzip file
        f = new File(getApplicationContext().getExternalFilesDir("/") + "/" + fname + "/" + "Blep");
        if (!f.exists()) {
            Toast.makeText(this, "Unzipped", Toast.LENGTH_LONG).show();
            String zipfilename = "Blep";
            String zipFile = myfolder + "/" + zipfilename + ".zip";
            String unzipLocation = myfolder + "/" + zipfilename + "/";
            Log.d("Zip", zipFile);
            Log.d("Zip Loc ", myfolder);
            ZipDecompress df = new ZipDecompress(zipFile, unzipLocation);
            df.unzip();
        } else {
            Toast.makeText(this, "Already Unzipped", Toast.LENGTH_LONG).show();
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
