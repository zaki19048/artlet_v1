package com.example.artlet_v1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artlet_v1.R;

import java.util.ArrayList;

public class LeaderBoardFragmentt extends AppCompatActivity {

//    ListView listView;
//    ArrayList<String> list;
//    ArrayAdapter<String > adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_leaderboard);
//
//        //searchView = (SearchView) findViewById(R.id.searchView);
//        listView = (ListView) findViewById(R.id.list_leader);
//
//        list = new ArrayList<>();
//        list.add("C");
//        list.add("Python");
//        list.add("C#");
//        list.add("C++");
//        list.add("Go");
//        list.add("PHP");
//        list.add("HTML");
//        list.add("Alpha");
//        list.add("Java");
//        list.add("Kotlin");
//        list.add("Forton");
//        list.add("Pascal");
//        list.add("Julia");
//
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
//
//    }
//}



    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_leaderboard);
        DatabaseHelper dh = new DatabaseHelper(this.getApplicationContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT content.id as content_id , genre.name as genre_name, user.name as user_name, title, type, likes ,content.created_at as created_at,file FROM content INNER JOIN user ON content.author_id = user.id INNER JOIN genre ON content.genre_id = genre.id ORDER BY content.id DESC", null);
        final String[] title=new String[c.getCount()] ;
        lv = (ListView) findViewById(R.id.list_leader);
        if(c!=null && c.moveToFirst())
        {
            int i=0;
            do {
                String uname = c.getString(c.getColumnIndex("user_name"));
                title[i]=uname;
                i=i+1;
            }while(c.moveToNext());
        }


       // getAuthorname();
        CustomAdapter adapter = new CustomAdapter(this,title);
        lv.setAdapter(adapter);
    }

//    public void getAuthorname()
//    {
//
//
//
//    }



}

class CustomAdapter extends ArrayAdapter<String>
{
    Context context;
    String[] title;


    CustomAdapter(Context c, String[] title)
    {

        super(c, R.layout.item_list_leader,title);
        this.context = c;
        this.title=title;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = vi.inflate(R.layout.item_list_leader, parent, false);
        TextView titlee = (TextView) row.findViewById(R.id.textView1);
        int pos = position+1;
        titlee.setText(+pos + ".                 " + title[position]);
        pos++;
        return row;
    }

}

