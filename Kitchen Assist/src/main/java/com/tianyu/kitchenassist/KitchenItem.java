package com.tianyu.kitchenassist;

/**
 * Created by Administrator on 14-6-1.
 */
public class KitchenItem {
    long    id;
    String  item_name;
    int     item_num;
    int     item_remain;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return item_name;
    }

    public void setName(String item_name) {
        this.item_name = item_name;
    }

    public int getNum() {
        return item_num;
    }

    public void setNum(int item_num) {
        this.item_num = item_num;
    }

    public void setReamin(int item_remain) {
        this.item_remain = item_remain;
    }
    // Will be used by the ArrayAdapter in the ListView
    //@Override
    //public String toString() {
    //    return comment;
    //}
}
