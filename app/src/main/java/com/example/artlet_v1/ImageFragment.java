package com.example.artlet_v1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

import java.io.File;
import java.util.List;

public class ImageFragment extends Fragment {

    private ImageView imageView;
    int[] myImageList = {R.drawable.m4, R.drawable.m5, R.drawable.m6,R.drawable.m7,
            R.drawable.m8, R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12,
            R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16, R.drawable.m17,
            R.drawable.m18, R.drawable.m19, R.drawable.m20 };;
    static List<File> images;

    public ImageFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = view.findViewById(R.id.image);
        int pos = getArguments().getInt("pos");
        //imageView.setImageResource(myImageList[pos%myImageList.length]);
        imageView.setImageDrawable(Drawable.createFromPath(images.get(pos%images.size()).toString()));
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(view.getContext()));
        return view;
    }
}