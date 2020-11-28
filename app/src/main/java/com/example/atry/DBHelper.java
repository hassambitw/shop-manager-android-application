package com.example.atry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyShop4.db";

    public static final String ORDERS_TABLE_NAMEe = "orders";
    public static final String ORDERS_ORDER_ID = "order_id";
    public static final String ORDERS_CUSTOMER_ID = "customer_id";
    public static final String ORDERS_ORDER_DATE = "order_date";
    public static final String ORDERS_STAFF_ID="staff_id";


    public static final String ORDER_ITEMS_TABLE_NAME = "order_items";
    public static final String ORDER_ITEMS_ORDER_ID = "order_id";
    public static final String ORDER_ITEMS_ITEM_ID = "item_id";
    public static final String ORDER_ITEMS_PRODUCT_ID = "product_id";
    public static final String ORDER_ITEMS_QUANTITY = "quantity";
    public static final String ORDER_ITEMS_PRICE = "price";
    public static final String ORDER_ITEMS_DISCOUNT = "discount";
    SQLiteDatabase db;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME , null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS order_items");*/
        System.out.println("IN CREATE BOOM");
        String createTable_orders2 = "CREATE TABLE IF NOT EXISTS " + ORDERS_TABLE_NAMEe +
                " ("+ ORDERS_ORDER_ID +" INTEGER PRIMARY KEY, "+
                ORDERS_CUSTOMER_ID+ " INTEGER , " +
                ORDERS_ORDER_DATE +" STRING, " +
                ORDERS_STAFF_ID+" INTEGER"+
              ")";
        String createTable_order_items = "CREATE TABLE IF NOT EXISTS " + ORDER_ITEMS_TABLE_NAME +
                " ("+ ORDER_ITEMS_ORDER_ID +" INTEGER, "+
                ORDER_ITEMS_ITEM_ID + " INTEGER , " +
                ORDER_ITEMS_PRODUCT_ID +" INTEGER, " +
                ORDER_ITEMS_QUANTITY + " INTEGER, " +
                ORDER_ITEMS_PRICE + " DOUBLE," +
                ORDER_ITEMS_DISCOUNT+" DOUBLE,"+
                "PRIMARY KEY("+ORDER_ITEMS_ORDER_ID+","+ORDER_ITEMS_ITEM_ID+
                "))";
        db.execSQL(createTable_orders2);
        db.execSQL(createTable_order_items);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
       /* if (newVersion > oldVersion) {
            String add_orders = "ALTER TABLE " + ORDERS_TABLE_NAMEe + " ADD COLUMN staff_id INTEGER";
            db.execSQL(add_orders);
        }*/

    }
    public void insertOrder(int orderId, int customerId, String orderDate,int staff_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("customer_id", customerId);
        contentValues.put("order_date", orderDate);
        contentValues.put("staff_id",staff_id);
        db.insert("orders","",contentValues);

    }


    public void insert_order_item(int orderId, int itemId, int productId, int quantity, double price, double discount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("item_id", itemId);
        contentValues.put("product_id", productId);
        contentValues.put("quantity", quantity);
        contentValues.put("price", price);
        contentValues.put("discount",discount);
        db.insert("order_items","",contentValues);

    }
    public Cursor getAllFrom_Orders() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query("orders",null,null,null,null,null,null);
    }
    public Cursor getAllFrom_Order_items(int order_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_order_items = "SELECT * FROM " + ORDER_ITEMS_TABLE_NAME +
                " WHERE "+ ORDERS_ORDER_ID +" = "+order_id;
        return db.rawQuery(select_order_items,null);

    }
    public void updateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String update_orders = "UPDATE " + ORDERS_TABLE_NAMEe + " SET staff_id=3 WHERE order_id=1";
        db.execSQL(update_orders);
        String update_orders2 = "UPDATE " + ORDERS_TABLE_NAMEe + " SET staff_id=9 WHERE order_id=2";
        db.execSQL(update_orders2);
        String update_orders3 = "UPDATE " + ORDERS_TABLE_NAMEe + " SET staff_id=83 WHERE order_id=69";
        db.execSQL(update_orders3);
    }

}
