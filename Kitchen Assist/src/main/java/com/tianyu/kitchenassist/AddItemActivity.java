package com.tianyu.kitchenassist;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

public class AddItemActivity extends Activity {
    private ItemsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        datasource = new ItemsDataSource(this);
        datasource.open();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }

    public void onClick(View view) {
        KitchenItem item = new KitchenItem();
        EditText item_name = (EditText) findViewById(R.id.item_name);
        EditText item_num = (EditText) findViewById(R.id.item_number);
        EditText item_remain = (EditText) findViewById(R.id.item_remain);
        switch (view.getId()) {
            case R.id.button_add:
                item.item_name = item_name.getText().toString();
                item.item_num = Integer.parseInt(item_num.getText().toString());
                item.item_remain = Integer.parseInt(item_remain.getText().toString());
                datasource.createItem(item);
                break;
        }
    }
}
