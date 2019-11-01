package com.example.artlet_v1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class DocUploader extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_file_uploader);

        db = new DatabaseHelper(this);
        browseFiles();
    }


    private void browseFiles() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult ( int req, int result, Intent data)
    {
//         TODO Auto-generated method stub
        super.onActivityResult(req, result, data);
        if (result == RESULT_OK) {
            Uri fileuri = data.getData();
            String   docFilePath = getFileNameByUri(this, fileuri);
            Log.d("FilePath", "onActivityResult: " + docFilePath);
            String revTitle = "", revType = "", title = "", type = "";
            for(int i = docFilePath.length() - 1; i >= 0; i--) {
                if(docFilePath.charAt(i) == '/') {
                    break;
                } else {
                    title += docFilePath.charAt(i);
                    if(docFilePath.charAt(i) == '.') {
                        revType = title;
                    }
                }
            }
            for(int i = revTitle.length() - 1; i >= 0; i--) {
                title += revTitle.charAt(i);
            }
            for(int i = revType.length() - 1; i >= 0; i--) {
                type += revType.charAt(i);
            }
            Intent intent = new Intent(this, DocActivity.class);
            intent.putExtra("docPath", docFilePath);
            startActivity(intent);
            db.InsertContentData(db, title, "someId", "someGenreId", type, docFilePath, new Date().toString());
            finish();
        }
    }

// get file path

    private String getFileNameByUri(Context context, Uri uri) {
        String filepath = "";//default fileName
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        } else if (uri.getScheme().compareTo("file") == 0) {
            try {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            filepath = uri.getPath();
        }
        return filepath;
    }
}
