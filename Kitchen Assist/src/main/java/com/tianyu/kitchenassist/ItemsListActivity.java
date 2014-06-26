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
    //private ItemsDataSource datasource;
    //private int position = 0;
    //private Cursor cursor;
    private KitchenItem item = new KitchenItem();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        //datasource = new ItemsDataSource(this);
        //datasource.open();

        //cursor = datasource.getCursor();

        Bundle bundle = this.getIntent().getExtras();
        if( bundle == null )
            Log.w("KA", "no bundle");
        else
        {
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
    protected void onStop() {
        //datasource.close();
        //cursor.close();
        super.onStop();
    }

    public void onClick(View view) {
        //TextView item_name = (TextView) findViewById(R.id.field_show_item_name);
        //TextView item_num = (TextView) findViewById(R.id.field_show_item_num);
        switch (view.getId()) {
            /*case R.id.button_next:
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
                if( position > 0 )
                {
                    position--;
                    cursor.moveToPosition(position);
                    item_name.setText(cursor.getString(1));  //name
                    item_num.setText("" + cursor.getInt(2));      //number
                    item_remain.setText("" + cursor.getInt(3));      //remain
                }
                break;*/
            case R.id.button_update_time:
                KitchenItem newItem = new KitchenItem();
                ItemsDataSource datasource = new ItemsDataSource(this);
                datasource.open();

                newItem.id = item.id;   //id
                if( item.item_remain > 0 )
                {
                    newItem.item_remain = item.item_remain - 1;
                    item.item_remain--;
                }
                else
                    newItem.item_remain = 0;
                datasource.updateItem(newItem);

                //update remain time
                TextView item_remain = (TextView) findViewById(R.id.field_show_item_remain);
                item_remain.setText("" + newItem.item_remain);
                break;
        }
    }
}
