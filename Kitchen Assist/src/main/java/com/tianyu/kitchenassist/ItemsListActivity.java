package com.tianyu.kitchenassist;

/**
 * Created by Administrator on 14-6-1.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ItemsListActivity extends Activity {
    private List<KitchenItem> values;
    private int index = 0;
    private ItemsDataSource datasource;
    private int position = 0;
    private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        KitchenItem item;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        datasource = new ItemsDataSource(this);
        datasource.open();

        values = datasource.getAllItems();
        cursor = datasource.getCursor();

        Bundle bundle = this.getIntent().getExtras();
        if( bundle == null )
            Log.w("KA", "no bundle");
        else
        {
            item = new KitchenItem();
            item.id = this.getIntent().getExtras().getInt("com.tianyu.com.EXTRA_ITEM_ID");
            item.item_name = this.getIntent().getExtras().getString("com.tianyu.com.EXTRA_ITEM_NAME");
            item.item_num = this.getIntent().getExtras().getInt("com.tianyu.com.EXTRA_ITEM_NUM");
            item.item_remain = this.getIntent().getExtras().getInt("com.tianyu.com.EXTRA_ITEM_REMAIN");
            //position = this.getIntent().getExtras().getInt("com.tianyu.com.EXTRA_POSITION");
            //Log.w( "KA", "get position is" + position );
            //Cursor cursor = datasource.getCursor();
            //if(cursor.moveToPosition(position))
            //{
                TextView item_name = (TextView) findViewById(R.id.field_show_item_name);
                TextView item_num = (TextView) findViewById(R.id.field_show_item_num);
                TextView item_remain = (TextView) findViewById(R.id.field_show_item_remain);
                item_name.setText(item.item_name);
                item_num.setText("" + item.item_num);
                item_remain.setText("" + item.item_remain);
            //}
            //cursor.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    public void onClick(View view) {
        KitchenItem item;
        TextView item_name = (TextView) findViewById(R.id.field_show_item_name);
        TextView item_num = (TextView) findViewById(R.id.field_show_item_num);
        TextView item_remain = (TextView) findViewById(R.id.field_show_item_remain);
        switch (view.getId()) {
            case R.id.button_next:
                /*if( index < values.size() - 1 )
                {
                    index++;
                    item = values.get(index);
                    item_name.setText(item.item_name);
                    item_num.setText("" + item.item_num);
                }*/
                if( position < cursor.getCount() - 1 )
                {
                    position++;
                    cursor.moveToPosition(position);
                    item_name.setText(cursor.getString(1));  //name
                    item_num.setText("" + cursor.getInt(2));      //number
                    item_remain.setText("" + cursor.getInt(3));      //remain
                }
                break;
            case R.id.button_previous:
                /*
                if( index > 0 )
                {
                    index--;
                    item = values.get(index);
                    item_name.setText(item.item_name);
                    item_num.setText("" + item.item_num);
                }*/
                if( position > 0 )
                {
                    position--;
                    cursor.moveToPosition(position);
                    item_name.setText(cursor.getString(1));  //name
                    item_num.setText("" + cursor.getInt(2));      //number
                    item_remain.setText("" + cursor.getInt(3));      //remain
                }
                break;
            case R.id.button_update_time:
                KitchenItem newItem = new KitchenItem();
                //item = values.get(index);

                cursor.moveToPosition(position);
                newItem.id = cursor.getInt(0);   //id
                newItem.item_remain = 100;
                datasource.updateItem(newItem);
                break;
        }
    }
}
