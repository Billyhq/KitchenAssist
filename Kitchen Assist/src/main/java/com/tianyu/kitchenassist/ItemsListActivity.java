package com.tianyu.kitchenassist;

/**
 * Created by Administrator on 14-6-1.
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ItemsListActivity extends Activity {
    private List<KitchenItem> values;
    private int index = 0;
    private ItemsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        KitchenItem item;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        datasource = new ItemsDataSource(this);
        datasource.open();

        values = datasource.getAllItems();
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
        switch (view.getId()) {
            case R.id.button_next:
                if( index < values.size() - 1 )
                {
                    index++;
                    item = values.get(index);
                    item_name.setText(item.item_name);
                    item_num.setText("" + item.item_num);
                }
                break;
            case R.id.button_previous:
                if( index > 0 )
                {
                    index--;
                    item = values.get(index);
                    item_name.setText(item.item_name);
                    item_num.setText("" + item.item_num);
                }
                break;
        }
    }
}
