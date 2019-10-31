package com.example.artlet_v1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;


@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    private Context context;

    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        final ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.genre_name = cell.findViewById(R.id.genre_name);
            viewHolder.time = cell.findViewById(R.id.title_time_label);
            viewHolder.date = cell.findViewById(R.id.title_date_label);
            viewHolder.content_name = cell.findViewById(R.id.title_from_address);
            viewHolder.type = cell.findViewById(R.id.title_to_address);
            viewHolder.requestsCount = cell.findViewById(R.id.content_deadline_time);
            viewHolder.pledgePrice = cell.findViewById(R.id.title_requests_count);
            viewHolder.contentRequestBtn = cell.findViewById(R.id.content_request_btn);
            viewHolder.artistName = cell.findViewById(R.id.content_name_view);
            viewHolder.likeButton = cell.findViewById(R.id.content_like_button);
            viewHolder.content_id = cell.findViewById(R.id.top_content_id);
            viewHolder.authorName = cell.findViewById(R.id.authorName);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.genre_name.setText(item.getGenre_name());
        viewHolder.time.setText(item.getTime());
        viewHolder.date.setText(item.getDate());
        viewHolder.content_name.setText(item.getContent_name());
        viewHolder.type.setText(item.getType());
        viewHolder.requestsCount.setText(String.valueOf(item.getRequestsCount()));
        viewHolder.pledgePrice.setText(item.getNum_likes());
        viewHolder.artistName.setText(item.getUser_name());
        viewHolder.authorName.setText(item.getUser_name());
        viewHolder.content_id.setText(item.getContentId());

        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
            viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper dbHelper = new DatabaseHelper(context);
                    final SQLiteDatabase db = dbHelper.getReadableDatabase();
                    String content_id = viewHolder.content_id.getText().toString();
                    int user_id = 1;
                    if(checkIfAlreadyLiked(db, content_id)) {
//                        v.setBackgroundResource(R.drawable.round);
                        v.setBackgroundResource(R.color.highlight_blue);
                        decrementLikesCount(db,content_id);
                        removeLike(db, content_id);
                    }
                    else {
                        insertIntoLikeTable(db, user_id, content_id);
                        incrementLikesCount(db, content_id);
                        v.setBackgroundResource(R.color.highlight_yellow);

                    }
                }
            });


        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
          /*  viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("inside like", "onClick: liked");
                }
            });*/

        }

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView genre_name;
        TextView contentRequestBtn;
        TextView artistName;
        TextView pledgePrice;
        TextView content_name;
        TextView type;
        TextView requestsCount;
        TextView date;
        TextView time;
        TextView content_id;
        TextView likeButton;
        TextView authorName;
    }

    public void insertIntoLikeTable(SQLiteDatabase db, int user_id, String content_id) {
        db.execSQL("INSERT INTO 'likes' ('user_id', 'content_id' ) VALUES (?, ?)", new String[]{String.valueOf(user_id), String.valueOf(content_id)});
//        Cursor c = db.rawQuery("INSERT INTO like values(")
//        Log.d("aaa", "insertIntoLikeTable: insert like");
    }

    public boolean checkIfAlreadyLiked(SQLiteDatabase db, String content_id) {
        Cursor c  = db.rawQuery("SELECT * FROM likes where content_id = ? and user_id= ?", new String[]{content_id, "1"});
        if(c.getCount() > 0) {
            return true;
        }
        return false;
    }

    public void removeLike(SQLiteDatabase db, String content_id) {
//        Cursor c  = db.rawQuery("DELETE FROM likes where content_id = ? and user_id= ?", new String[]{content_id, "1"});
        db.execSQL("delete from likes where content_id="+content_id+ " and user_id=1");

    }

    public void decrementLikesCount(SQLiteDatabase db, String content_id) {
        db.execSQL("UPDATE content \n" +
                "SET likes = likes - 1\n" +
                "WHERE id = $content_id\n" +
                "and id > 0");
    Log.d("aaa", "decrement likes");
    }


    public void incrementLikesCount(SQLiteDatabase db, String content_id) {
        db.execSQL("UPDATE content \n" +
                "SET likes = likes + 1\n" +
                "WHERE id = $content_id");
        Log.d("aaa", "increment likes");
    }
}

