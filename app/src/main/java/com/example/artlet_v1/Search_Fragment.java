package com.example.artlet_v1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


public class Search_Fragment extends Fragment {
    public SearchView search;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_search_fragment, container, false);
       search = (SearchView) view.findViewById(R.id.search);
       search.setQueryHint("SearchView Fragment");
      return view;
    }
}
