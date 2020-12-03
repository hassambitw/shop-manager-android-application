package com.example.atry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MyShop4.db";

    //customers table
    public static final String CUSTOMERS_TABLE = "customers";
    public static final String CUSTOMERS_COL1_ID = "customer_id";
    public static final String CUSTOMERS_COL2_FNAME = "first_name";
    public static final String CUSTOMERS_COL3_LNAME = "last_name";
    public static final String CUSTOMERS_COL4_PHONE = "phone";
    public static final String CUSTOMERS_COL5_EMAIL = "email";

    String CREATE_TABLE_CUSTOMERS = "CREATE TABLE IF NOT EXISTS " + CUSTOMERS_TABLE +
                                    "("
                                            + CUSTOMERS_COL1_ID + " INTEGER PRIMARY KEY NOT NULL, "
                                            + CUSTOMERS_COL2_FNAME + " TEXT NOT NULL, "
                                            + CUSTOMERS_COL3_LNAME + " TEXT NOT NULL, "
                                            + CUSTOMERS_COL4_PHONE + " TEXT NOT NULL, "
                                            + CUSTOMERS_COL5_EMAIL + " TEXT NOT NULL " +
                                    ")";

    //orders table
    public static final String ORDERS_TABLE = "orders";
    public static final String ORDERS_ORDER_ID = "order_id";
    public static final String ORDERS_CUSTOMER_ID = "customer_id";      //FK on cust
    public static final String ORDERS_ORDER_DATE = "order_date";
    public static final String ORDERS_STAFF_ID="staff_id";              //FK on staff table

    String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS " + ORDERS_TABLE +
                                " ("
                                    + ORDERS_ORDER_ID + " INTEGER PRIMARY KEY, "
                                    + ORDERS_CUSTOMER_ID + " INTEGER NOT NULL, "
                                    + ORDERS_ORDER_DATE + " STRING, "
                                    + ORDERS_STAFF_ID + " INTEGER, "
                                    + "FOREIGN KEY (" + ORDERS_CUSTOMER_ID + ") REFERENCES " + CUSTOMERS_TABLE +  "(" + CUSTOMERS_COL1_ID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
                                    + "FOREIGN KEY (" + ORDERS_STAFF_ID + ") REFERENCES " + STAFF_TABLE +  "(" + STAFF_COL1_STAFFID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                                ")";

    //staff table
    public static final String STAFF_TABLE = "staff";
    public static final String STAFF_COL1_STAFFID = "staff_id";
    public static final String STAFF_COL2_FNAME = "first_name";
    public static final String STAFF_COL3_LNAME = "last_name";
    public static final String STAFF_COL4_EMAIL = "email";
    public static final String STAFF_COL5_PHONE = "phone";

        String CREATE_TABLE_STAFF = "CREATE TABLE IF NOT EXISTS " + CUSTOMERS_TABLE +
                                    "("
                                        + STAFF_COL1_STAFFID + " INTEGER PRIMARY KEY, "
                                        + STAFF_COL2_FNAME + " TEXT NOT NULL, "
                                        + STAFF_COL3_LNAME + " TEXT NOT NULL, "
                                        + STAFF_COL4_EMAIL + " TEXT NOT NULL, "
                                        + STAFF_COL5_PHONE + " TEXT NOT NULL " +
                                    ")";

    //order_items table
    public static final String ORDER_ITEMS_TABLE = "order_items";
    public static final String ORDER_ITEMS_ORDER_ID = "order_id";       //FK on orders table
    public static final String ORDER_ITEMS_ITEM_ID = "item_id";
    public static final String ORDER_ITEMS_PRODUCT_ID = "product_id";   //FK on products table
    public static final String ORDER_ITEMS_QUANTITY = "quantity";
    public static final String ORDER_ITEMS_PRICE = "price";
    public static final String ORDER_ITEMS_DISCOUNT = "discount";

    String CREATE_TABLE_ORDERITEMS = "CREATE TABLE IF NOT EXISTS " + ORDER_ITEMS_TABLE +
                                    " ("
                                        + ORDER_ITEMS_ORDER_ID +" INTEGER, "
                                        + ORDER_ITEMS_ITEM_ID + " INTEGER , "
                                        + ORDER_ITEMS_PRODUCT_ID +" INTEGER, "
                                        + ORDER_ITEMS_QUANTITY + " INTEGER, "
                                        + ORDER_ITEMS_PRICE + " DOUBLE,"
                                        + ORDER_ITEMS_DISCOUNT+" DOUBLE,"
                                        + "PRIMARY KEY (" + ORDER_ITEMS_ORDER_ID + "," + ORDER_ITEMS_ITEM_ID + "), "
                                        + "FOREIGN KEY (" + ORDER_ITEMS_ORDER_ID + ") REFERENCES " + ORDERS_TABLE +  "(" + ORDERS_ORDER_ID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
                                        + "FOREIGN KEY (" + ORDER_ITEMS_PRODUCT_ID + ") REFERENCES " + PRODUCTS_TABLE +  "(" + PRODUCTS_COL1_PRODID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                                    ")";




    //categories table
    public static final String CATEGORIES_TABLE = "categories";
    public static final String CATEGORIES_COL1_CATEGORYID = "category_id";
    public static final String CATEGORIES_COL2_CATEGORYNAME = "category_name";

    String CREATE_TABLE_CATEGORIES = "CREATE TABLE IF NOT EXISTS " + CATEGORIES_TABLE +
                                    "("
                                        + CATEGORIES_COL1_CATEGORYID + " INTEGER PRIMARY KEY, "
                                        + CATEGORIES_COL2_CATEGORYNAME + " TEXT NOT NULL " +
                                    ")";

    //brands table
    public static final String BRANDS_TABLE = "brands";
    public static final String BRANDS_COL1_BRANDID = "brand_id";
    public static final String BRANDS_COL2_BRANDNAME = "category_name";

    String CREATE_TABLE_BRANDS = "CREATE TABLE IF NOT EXISTS " + BRANDS_TABLE +
                                    "("
                                        + BRANDS_COL1_BRANDID + " INTEGER PRIMARY KEY, "
                                        + BRANDS_COL2_BRANDNAME + " TEXT NOT NULL " +
                                    ")";

    //suppliers table
    public static final String SUPPLIER_TABLE = "supplier";
    public static final String SUPPLIER_COL1_ID = "supplier_id";
    public static final String SUPPLIER_COL2_NAME = "supplier_name";
    public static final String SUPPLIER_COL3_NUM = "supplier_number";
    public static final String SUPPLIER_COL4_EMAIL = "supplier_email";

    String CREATE_TABLE_SUPPLIER = "CREATE TABLE IF NOT EXISTS " + SUPPLIER_TABLE +
                                    "("
                                        + SUPPLIER_COL1_ID + " INTEGER PRIMARY KEY, "
                                        + SUPPLIER_COL2_NAME + " TEXT NOT NULL, "
                                        + SUPPLIER_COL3_NUM + "TEXT NOT NULL, "
                                        + SUPPLIER_COL4_EMAIL + "TEXT NOT NULL " +
                                    ")";


    //products table
    public static final String PRODUCTS_TABLE = "products";
    public static final String PRODUCTS_COL1_PRODID = "product_id";
    public static final String PRODUCTS_COL2_BRANDID = "brand_id";          //FK on brands
    public static final String PRODUCTS_COL3_CATEGORYID = "category_id";    //FK on categories
    public static final String PRODUCTS_COL4_MODELYR = "model_year";
    public static final String PRODUCTS_COL5_LISTPRICE = "list_price";
    public static final String PRODUCTS_COL6_STOCK = "stock";
    //public static final String PRODUCTS_COL7_SUPPLIERID = "prodSupplier_id";

    String CREATE_TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + PRODUCTS_TABLE +
                                    "("
                                        + PRODUCTS_COL1_PRODID + " INTEGER PRIMARY KEY, "
                                        + PRODUCTS_COL2_BRANDID + " TEXT NOT NULL, "
                                        + PRODUCTS_COL3_CATEGORYID + " INTEGER NOT NULL, "
                                        + PRODUCTS_COL4_MODELYR + " INTEGER NOT NULL, "
                                        + PRODUCTS_COL5_LISTPRICE + " DOUBLE NOT NULL, "
                                        + PRODUCTS_COL6_STOCK + " INTEGER NOT NULL, "
                                        //+ PRODUCTS_COL7_SUPPLIERID + "INTEGER NOT NULL, "
                                        + "FOREIGN KEY (" + PRODUCTS_COL3_CATEGORYID + ") REFERENCES " + CATEGORIES_TABLE +  "(" + CATEGORIES_COL1_CATEGORYID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
                                        + "FOREIGN KEY (" + PRODUCTS_COL2_BRANDID + ") REFERENCES " + BRANDS_TABLE +  "(" + BRANDS_COL1_BRANDID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                                       // + "FOREIGN KEY (" + PRODUCTS_COL7_SUPPLIERID + ") REFERENCES " + SUPPLIER_TABLE +  "(" + SUPPLIER_COL1_SUPPLIERID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                                    ")";


    //shipment table
    public static final String SHIPMENT_TABLE = "shipment";
    public static final String SHIPMENT_COL1_SHIPMENTNUM = "shipment_num";
    public static final String SHIPMENT_COL2_PRODUCTID = "shipment_product_id";     //FK on prod table
    public static final String SHIPMENT_COL3_QUOTE = "shipment_quote";
    public static final String SUPPLIER_COL4_SUPPLIERID = "shipment_supplier_id";   //FK on supplier table
    public static final String SUPPLIER_COL5_SHIPMENTDATE = "shipment_date";

    String CREATE_TABLE_SHIPMENT = "CREATE TABLE IF NOT EXISTS " + SHIPMENT_TABLE +
                                    "("
                                        + SHIPMENT_COL1_SHIPMENTNUM + " INTEGER NOT NULL, "
                                        + SHIPMENT_COL2_PRODUCTID + " INTEGER NOT NULL, "
                                        + SHIPMENT_COL3_QUOTE + "DECIMAL NOT NULL, "
                                        + SUPPLIER_COL4_SUPPLIERID + "INTEGER NOT NULL, "
                                        + SUPPLIER_COL5_SHIPMENTDATE + "TEXT NOT NULL, "
                                        + "PRIMARY KEY " + "(" + SHIPMENT_COL1_SHIPMENTNUM + "," + SHIPMENT_COL2_PRODUCTID + "), "
                                        + "FOREIGN KEY (" + SHIPMENT_COL2_PRODUCTID + ") REFERENCES " + PRODUCTS_TABLE +  "(" + PRODUCTS_COL1_PRODID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
                                        + "FOREIGN KEY (" + SUPPLIER_COL4_SUPPLIERID + ") REFERENCES " + SUPPLIER_TABLE +  "(" + SUPPLIER_COL1_ID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                                    ")";


    SQLiteDatabase db;

    public DBHelper(Context context) {

        super(context, DB_NAME , null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS order_items");*/
        System.out.println("IN CREATE BOOM");

        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_ORDERS);
       db.execSQL(CREATE_TABLE_ORDERITEMS);
//        db.execSQL(CREATE_TABLE_STAFF);
//        db.execSQL(CREATE_TABLE_PRODUCTS);
//        db.execSQL(CREATE_TABLE_CATEGORIES);
//        db.execSQL(CREATE_TABLE_BRANDS);
//        db.execSQL(CREATE_TABLE_SUPPLIER);
//        db.execSQL(CREATE_TABLE_SHIPMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
       /* if (newVersion > oldVersion) {
            String add_orders = "ALTER TABLE " + ORDERS_TABLE_NAMEe + " ADD COLUMN staff_id INTEGER";
            db.execSQL(add_orders);
        }*/
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMERS_TABLE);
        onCreate(db); 
    }

    //insert methods
    public void insertOrder(int orderId, int customerId, String orderDate, int staff_id){
        boolean check;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("customer_id", customerId);
        contentValues.put("order_date", orderDate);
        db.insert("orders", "", contentValues);
        }


    //check for duplicates
    private boolean checkCustomerDuplicate (int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + CUSTOMERS_TABLE + " WHERE " + CUSTOMERS_COL1_ID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;       // no match
        }
        cursor.close();
        return true;           // match found
    }

    public boolean insertCustomer (int customerID, String customer_Fname, String customer_Lname, String customer_phone, String customer_email) {
        //db.execSQL("DROP TABLE " + CUSTOMERS_TABLE);
        boolean check;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_id", customerID);
        contentValues.put("first_name", customer_Fname);
        contentValues.put("last_name", customer_Lname);
        contentValues.put("phone", customer_phone);
        contentValues.put("email", customer_email);
        check = checkCustomerDuplicate(customerID);
        if (!check) {       // if there's no match, add
            long result = db.insert("customers", "", contentValues);
            return result != -1;
        } else {
            return false;
        }
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

    //getMethods
    public Cursor getCustomers () {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_customer = "SELECT * FROM " + CUSTOMERS_TABLE;
        return db.rawQuery(select_customer,null);
    }


    public Cursor getAllFrom_Orders() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query("orders",null,null,null,null,null,null);
    }
    public Cursor get_Order_Details(int order_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_orders = "SELECT * FROM " + ORDERS_TABLE +
                " WHERE "+ ORDERS_ORDER_ID +" = "+order_id;
        return db.rawQuery(select_orders,null);
    }
    public Cursor getAllFrom_Order_items(int order_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_order_items = "SELECT * FROM " + ORDER_ITEMS_TABLE +
                " WHERE "+ ORDERS_ORDER_ID +" = "+order_id;
        return db.rawQuery(select_order_items,null);
    }
    public void updateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String update_orders = "UPDATE " + ORDERS_TABLE + " SET staff_id=3 WHERE order_id=1";
        db.execSQL(update_orders);
        String update_orders2 = "UPDATE " + ORDERS_TABLE + " SET staff_id=9 WHERE order_id=2";
        db.execSQL(update_orders2);
        String update_orders3 = "UPDATE " + ORDERS_TABLE + " SET staff_id=83 WHERE order_id=69";
        db.execSQL(update_orders3);
    }

}
