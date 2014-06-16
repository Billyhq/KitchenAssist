package com.tianyu.kitchenassist;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onClick(View view) {
    Intent intent;
    //@SuppressWarnings("unchecked")
    switch (view.getId()) {
        case R.id.add_item:
            intent = new Intent(this, AddItemActivity.class);
            startActivity(intent);
            break;
        case R.id.item_list:
            intent = new Intent(this, ItemsListActivity.class);
            startActivity(intent);
            break;
        case R.id.button_list_view:
            intent = new Intent(this, ListViewActivity.class);
            startActivity(intent);
            break;

    }
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }
}
