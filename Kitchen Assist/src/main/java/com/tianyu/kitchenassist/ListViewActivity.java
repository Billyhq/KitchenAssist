package com.tianyu.kitchenassist;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListViewActivity extends ListActivity {
    private ItemsDataSource datasource;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datasource = new ItemsDataSource(this);
        datasource.open();

        cursor = datasource.getCursor();
        String[] from = { "item_name" };
        int[] to = {R.id.itemNameEntryText};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_list_view, cursor, from, to);
        setListAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        datasource.close();
        cursor.close();
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        Log.w("KA", "ListViewActivity onListItemClick" + position + " " + id);
    }
}