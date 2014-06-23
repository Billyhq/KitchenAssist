package com.tianyu.kitchenassist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ItemsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private KitchenSQLiteHelper dbHelper;
    private String[] allColumns = { KitchenSQLiteHelper.ITEM_ID,
            KitchenSQLiteHelper.COLUMN_ITEM_NAME, KitchenSQLiteHelper.COLUMN_ITEM_NUM,
            KitchenSQLiteHelper.COLUMN_ITEM_REMAIN };

    public ItemsDataSource(Context context) {
        dbHelper = new KitchenSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createItem(KitchenItem item) {
        ContentValues values = new ContentValues();
        values.put(KitchenSQLiteHelper.COLUMN_ITEM_NAME, item.item_name);
        values.put(KitchenSQLiteHelper.COLUMN_ITEM_NUM, item.item_num);
        values.put(KitchenSQLiteHelper.COLUMN_ITEM_REMAIN, item.item_remain);
        database.insert(KitchenSQLiteHelper.TABLE_ITEMS, null, values);
        //long insertId = database.insert(KitchenSQLiteHelper.TABLE_ITEMS, null,
        //        values);
        //Cursor cursor = database.query(KitchenSQLiteHelper.TABLE_ITEMS,
        //        allColumns, KitchenSQLiteHelper.ITEM_ID + " = " + insertId, null,
        //        null, null, null);
        //cursor.moveToFirst();
        //KitchenItem newItem = cursorToItem(cursor);
        //cursor.close();
        //return newItem;
    }

    public void deleteItem(KitchenItem item) {
        long id = item.getId();
        System.out.println("Item deleted with id: " + id);
        database.delete(KitchenSQLiteHelper.TABLE_ITEMS, KitchenSQLiteHelper.ITEM_ID
                + " = " + id, null);
    }

    //only update the remain field
    public void updateItem(KitchenItem item) {
        ContentValues values = new ContentValues();
        long id = item.getId();
        String[] whereArgs = new String[] {String.valueOf(id)};

        Log.w("KA", "id is " + id + "new remain is " + item.item_remain);
        values.put(KitchenSQLiteHelper.COLUMN_ITEM_REMAIN, item.item_remain);
        //database.update(KitchenSQLiteHelper.TABLE_ITEMS, values, "_id=?", whereArgs);
        database.update(KitchenSQLiteHelper.TABLE_ITEMS, values, KitchenSQLiteHelper.ITEM_ID
                + " = " + id, null);
    }

    public List<KitchenItem> getAllItems() {
        List<KitchenItem> items = new ArrayList<KitchenItem>();

        Cursor cursor = database.query(KitchenSQLiteHelper.TABLE_ITEMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            KitchenItem item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return items;
    }

    public Cursor getCursor() {
        Cursor cursor = database.query(KitchenSQLiteHelper.TABLE_ITEMS,
                allColumns, null, null, null, null, "item_remain");  //ordered by item_remain

        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getSpecificCursor( int id ) {
        Cursor cursor = database.query(KitchenSQLiteHelper.TABLE_ITEMS,
                        allColumns, KitchenSQLiteHelper.ITEM_ID + " = " + id, null,
                        null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    private KitchenItem cursorToItem(Cursor cursor) {
        KitchenItem item = new KitchenItem();
        item.setId(cursor.getLong(0));
        item.setName(cursor.getString(1));
        item.setNum(cursor.getInt(2));
        item.setReamin(cursor.getInt(3));
        return item;
    }
}