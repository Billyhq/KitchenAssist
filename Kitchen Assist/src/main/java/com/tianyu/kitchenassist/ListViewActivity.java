package com.tianyu.kitchenassist;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListViewActivity extends ListActivity {
    private ItemsDataSource datasource;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new ModeCallback());

        datasource = new ItemsDataSource(this);
        datasource.open();

        cursor = datasource.getCursor();
        String[] from = { "item_name" };
        //int[] to = {R.id.itemNameEntryText};
        int[] to = {android.R.id.text1};
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_list_view, cursor, from, to);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  android.R.layout.simple_list_item_checked, cursor, from, to) {
            @Override
            public boolean hasStableIds () {
                return true;
            }
        };
        setListAdapter(adapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getActionBar().setSubtitle("长按删除");
    }

    private class ModeCallback implements ListView.MultiChoiceModeListener {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_view, menu);
            mode.setTitle("删除物品");
            setSubtitle(mode);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    Toast.makeText(ListViewActivity.this, "删除了" + getListView().getCheckedItemCount() +
                            "件物品", Toast.LENGTH_SHORT).show();
                    long []ids = getListView().getCheckedItemIds();
                    Log.w("KA", "chosen id size is " + ids.length);
                    for( int i = 0; i < ids.length; i++ )
                        Log.w("KA", "chosen id is " + ids[i]);
                    /*
                    SparseBooleanArray checkedItems = getListView().getCheckedItemPositions();
                    for( int i = 0; i < checkedItems.size(); i++ )
                    {
                        if (checkedItems.valueAt(i))
                        {
                            String chosen_item = getListView().getAdapter().getItem(checkedItems.keyAt(i)).toString();
                            getListView().getAdapter().getItem(checkedItems.keyAt(i));
                            getString
                            Log.w("KA", "chosen id is " + chosen_item);
                        }
                    }*/
                    mode.finish();
                    break;
                default:
                    Toast.makeText(ListViewActivity.this, "Clicked " + item.getTitle(),
                                   Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public void onItemCheckedStateChanged(ActionMode mode,
                                              int position, long id, boolean checked) {
            setSubtitle(mode);
        }

        private void setSubtitle(ActionMode mode) {
            final int checkedCount = getListView().getCheckedItemCount();
            switch (checkedCount) {
                case 0:
                    mode.setSubtitle(null);
                    break;
                //case 1:
                //    mode.setSubtitle("One item selected");
                //    break;
                default:
                    mode.setSubtitle("准备删除" + checkedCount + "件物品");
                    break;
            }
        }
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
        cursor.moveToPosition(position);

        //transfer item information directly
        Intent intent = new Intent(this, ItemsListActivity.class);

        intent.putExtra("com.tianyu.com.EXTRA_ITEM_ID", cursor.getInt(0));
        intent.putExtra("com.tianyu.com.EXTRA_ITEM_NAME", cursor.getString(1));
        intent.putExtra("com.tianyu.com.EXTRA_ITEM_NUM", cursor.getInt(2));
        intent.putExtra("com.tianyu.com.EXTRA_ITEM_REMAIN", cursor.getInt(3));
        startActivity(intent);
    }
}