package com.example.atry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Objects;

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
    public static final String ORDERS_STAFF_ID = "staff_id";              //FK on staff table

    String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS " + ORDERS_TABLE +
            " ("
            + ORDERS_ORDER_ID + " INTEGER PRIMARY KEY, "
            + ORDERS_CUSTOMER_ID + " INTEGER NOT NULL, "
            + ORDERS_ORDER_DATE + " STRING, "
            + ORDERS_STAFF_ID + " INTEGER, "
            + "FOREIGN KEY (" + ORDERS_CUSTOMER_ID + ") REFERENCES " + CUSTOMERS_TABLE + "(" + CUSTOMERS_COL1_ID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
            + "FOREIGN KEY (" + ORDERS_STAFF_ID + ") REFERENCES " + STAFF_TABLE + "(" + STAFF_COL1_STAFFID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ")";

    //staff table
    public static final String STAFF_TABLE = "staff";
    public static final String STAFF_COL1_STAFFID = "staff_id";
    public static final String STAFF_COL2_FNAME = "first_name";
    public static final String STAFF_COL3_LNAME = "last_name";
    public static final String STAFF_COL4_EMAIL = "email";
    public static final String STAFF_COL5_PHONE = "phone";

    String CREATE_TABLE_STAFF = "CREATE TABLE IF NOT EXISTS " + STAFF_TABLE +
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
            + ORDER_ITEMS_ORDER_ID + " INTEGER, "
            + ORDER_ITEMS_ITEM_ID + " INTEGER , "
            + ORDER_ITEMS_PRODUCT_ID + " INTEGER, "
            + ORDER_ITEMS_QUANTITY + " INTEGER, "
            + ORDER_ITEMS_PRICE + " DOUBLE,"
            + ORDER_ITEMS_DISCOUNT + " DOUBLE,"
            + "PRIMARY KEY (" + ORDER_ITEMS_ORDER_ID + "," + ORDER_ITEMS_ITEM_ID + "), "
            + "FOREIGN KEY (" + ORDER_ITEMS_ORDER_ID + ") REFERENCES " + ORDERS_TABLE + "(" + ORDERS_ORDER_ID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
            + "FOREIGN KEY (" + ORDER_ITEMS_PRODUCT_ID + ") REFERENCES " + PRODUCTS_TABLE + "(" + PRODUCTS_COL1_PRODID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ")";


    //categories table
    public static final String CATEGORIES_TABLE = "categories";
    public static final String CATEGORIES_COL1_CATEGORYID = "category_id";
    public static final String CATEGORIES_COL2_CATEGORYNAME = "category_name";
//
//    String CREATE_TABLE_CATEGORIES = "CREATE TABLE IF NOT EXISTS " + CATEGORIES_TABLE +
//                                    "("
//                                        + CATEGORIES_COL1_CATEGORYID + " INTEGER PRIMARY KEY, "
//                                        + CATEGORIES_COL2_CATEGORYNAME + " TEXT NOT NULL " +
//                                    ")";

    //brands table
    public static final String BRANDS_TABLE = "brands";
    public static final String BRANDS_COL1_BRANDID = "brand_id";
    public static final String BRANDS_COL2_BRANDNAME = "category_name";

//    String CREATE_TABLE_BRANDS = "CREATE TABLE IF NOT EXISTS " + BRANDS_TABLE +
//                                    "("
//                                        + BRANDS_COL1_BRANDID + " INTEGER PRIMARY KEY, "
//                                        + BRANDS_COL2_BRANDNAME + " TEXT NOT NULL " +
//                                    ")";

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
            + SUPPLIER_COL3_NUM + " TEXT NOT NULL, "
            + SUPPLIER_COL4_EMAIL + " TEXT NOT NULL " +
            ")";


    //products table
    public static final String PRODUCTS_TABLE = "products";
    public static final String PRODUCTS_COL1_PRODID = "product_id";
    public static final String PRODUCTS_COL2_PRODNAME = "product_name";
    public static final String PRODUCTS_COL3_BRAND = "brand";          //FK on brands - not really
    public static final String PRODUCTS_COL4_CATEGORY = "category";    //FK on categories - not really
    public static final String PRODUCTS_COL5_MODELYR = "model_year";
    public static final String PRODUCTS_COL6_LISTPRICE = "list_price";
    public static final String PRODUCTS_COL7_STOCK = "stock";
    //public static final String PRODUCTS_COL7_SUPPLIERID = "prodSupplier_id";

    String CREATE_TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + PRODUCTS_TABLE +
            "("
            + PRODUCTS_COL1_PRODID + " INTEGER PRIMARY KEY, "
            + PRODUCTS_COL2_PRODNAME + " TEXT NOT NULL, "
            + PRODUCTS_COL3_BRAND + " TEXT NOT NULL, "
            + PRODUCTS_COL4_CATEGORY + " TEXT NOT NULL, "
            + PRODUCTS_COL5_MODELYR + " INTEGER NOT NULL, "
            + PRODUCTS_COL6_LISTPRICE + " DOUBLE NOT NULL, "
            + PRODUCTS_COL7_STOCK + " INTEGER NOT NULL " +
            //+ PRODUCTS_COL7_SUPPLIERID + "INTEGER NOT NULL, "
            // + "FOREIGN KEY (" + PRODUCTS_COL3_CATEGORYID + ") REFERENCES " + CATEGORIES_TABLE +  "(" + CATEGORIES_COL1_CATEGORYID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
            // + "FOREIGN KEY (" + PRODUCTS_COL2_BRANDID + ") REFERENCES " + BRANDS_TABLE +  "(" + BRANDS_COL1_BRANDID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            // + "FOREIGN KEY (" + PRODUCTS_COL7_SUPPLIERID + ") REFERENCES " + SUPPLIER_TABLE +  "(" + SUPPLIER_COL1_SUPPLIERID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ")";


    //shipment table
    public static final String SHIPMENT_TABLE = "shipment";
    public static final String SHIPMENT_COL1_SHIPMENTNUM = "shipment_num";
    public static final String SHIPMENT_COL3_QUOTE = "shipment_quote";
    public static final String SUPPLIER_COL4_SUPPLIERID = "shipment_supplier_id";   //FK on supplier table
    public static final String SUPPLIER_COL5_SHIPMENTDATE = "shipment_date";

    String CREATE_TABLE_SHIPMENT = "CREATE TABLE IF NOT EXISTS " + SHIPMENT_TABLE +
            "("
            + SHIPMENT_COL1_SHIPMENTNUM + " INTEGER NOT NULL, "
            + SHIPMENT_COL3_QUOTE + " INTEGER NOT NULL, "
            + SUPPLIER_COL4_SUPPLIERID + " INTEGER NOT NULL, "
            + SUPPLIER_COL5_SHIPMENTDATE + " TEXT NOT NULL, "
            + "PRIMARY KEY " + "(" + SHIPMENT_COL1_SHIPMENTNUM + "), "
            + "FOREIGN KEY (" + SUPPLIER_COL4_SUPPLIERID + ") REFERENCES " + SUPPLIER_TABLE + "(" + SUPPLIER_COL1_ID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ")";

    //shipment items table
    public static final String SHIPMENT_ITEMS_TABLE = "shipment_items";
    public static final String SHIPMENT_ITEMS_COL1_ITEM_ID = "item_id";
    public static final String SHIPMENT_ITEMS_COL2_PRODUCTID = "shipment_product_id";     //FK on prod table
    public static final String SHIPMENT_ITEMS_COL3_QUANTITY = "shipment_quantity";
    public static final String SHIPMENT_ITEMS_COL4_SHIPMENTNUM = "shipment_num";


    String CREATE_TABLE_SHIPMENT_ITEMS = "CREATE TABLE IF NOT EXISTS " + SHIPMENT_ITEMS_TABLE +
            "("
            + SHIPMENT_ITEMS_COL1_ITEM_ID + " INTEGER NOT NULL, "
            + SHIPMENT_ITEMS_COL2_PRODUCTID + " INTEGER NOT NULL, "
            + SHIPMENT_ITEMS_COL3_QUANTITY + " INTEGER NOT NULL, "
            + SHIPMENT_ITEMS_COL4_SHIPMENTNUM + " INTEGER NOT NULL, "
            + "PRIMARY KEY " + "(" + SHIPMENT_ITEMS_COL1_ITEM_ID + "," + SHIPMENT_ITEMS_COL4_SHIPMENTNUM + "), "
            + "FOREIGN KEY (" + SHIPMENT_ITEMS_COL2_PRODUCTID + ") REFERENCES " + PRODUCTS_TABLE + "(" + PRODUCTS_COL1_PRODID + ") ON DELETE CASCADE ON UPDATE CASCADE, "
            + "FOREIGN KEY (" + SHIPMENT_ITEMS_COL4_SHIPMENTNUM + ") REFERENCES " + SHIPMENT_TABLE + "(" + SHIPMENT_COL1_SHIPMENTNUM + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ")";


    SQLiteDatabase db;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS order_items");*/
        System.out.println("IN CREATE BOOM");

        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_ORDERITEMS);
        db.execSQL(CREATE_TABLE_STAFF);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_SHIPMENT);
        db.execSQL(CREATE_TABLE_SHIPMENT_ITEMS);
//        db.execSQL(CREATE_TABLE_CATEGORIES);
//        db.execSQL(CREATE_TABLE_BRANDS);
        db.execSQL(CREATE_TABLE_SUPPLIER);
//        db.execSQL(CREATE_TABLE_SHIPMENT);

        // for orders, do products, staff and customers first
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
    public int insertOrder(int orderId, int customerId, String orderDate, int staff_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("customer_id", customerId);
        contentValues.put("order_date", orderDate);
        contentValues.put("staff_id", staff_id);
        if (checkOrdersDuplicate(orderId)) {
            //duplicate exists so error code 2
            return 2;
        } else if (!checkCustomerExists(customerId)) {
            //cust does not exist, error code 3
            return 3;
        } else if (!checkStaffExists(staff_id)) {
            //staff does not exist, error code 4
            return 4;
        } else {

            db.insert("orders", "", contentValues);
            return 1;

        }
    }

    public int insert_order_item(int orderId, int itemId, int productId, int quantity, double price, double discount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("item_id", itemId);
        contentValues.put("product_id", productId);
        contentValues.put("quantity", quantity);
        contentValues.put("price", price);
        contentValues.put("discount", discount);
        if (checkItemsDuplicate(itemId)) {
            //duplicate exists so error code 2
            return 2;
        }else if(!checkProductExists(productId)){
            return 3;
        }
        else if(!enoughQuantity( productId, quantity)){
            return 4;
        }
        else {
            db.insert("order_items", "", contentValues);
            return 1;
        }
    }


    //check if staff exists
    private boolean checkStaffExists(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + STAFF_TABLE + " WHERE " + STAFF_COL1_STAFFID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() >= 1) {
            cursor.close();
            return true;       // found match
        }
        cursor.close();
        return false;           // no match
    }




    //check if customer exists
    private boolean checkCustomerExists(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + CUSTOMERS_TABLE + " WHERE " + CUSTOMERS_COL1_ID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() >= 1) {
            cursor.close();
            return true;       // found match
        }
        cursor.close();
        return false;           // no match
    }
    //check if customer exists
    public boolean checkProductExists(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + PRODUCTS_TABLE + " WHERE " + PRODUCTS_COL1_PRODID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() >= 1) {
            cursor.close();
            return true;       // found match
        }
        cursor.close();
        return false;           // no match
    }

    //Check supplier exists
    private boolean checkSupplierExists(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + SUPPLIER_TABLE + " WHERE " + SUPPLIER_COL1_ID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() >= 1) {
            cursor.close();
            return true;       // found match
        }
        cursor.close();
        return false;           // no match
    }

    //check for duplicates in orders
    private boolean checkOrdersDuplicate(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + ORDERS_TABLE + " WHERE " + ORDERS_ORDER_ID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;       // no match
        }
        cursor.close();
        return true;           // match found
    }
    //check for duplicates in orders
    private boolean checkItemsDuplicate(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + ORDER_ITEMS_TABLE + " WHERE " + ORDER_ITEMS_ITEM_ID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;       // no match
        }
        cursor.close();
        return true;           // match found
    }
    //check for duplicates in orders
    private boolean checkShipmentItemsDuplicate(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + SHIPMENT_ITEMS_TABLE + " WHERE " + SHIPMENT_ITEMS_COL1_ITEM_ID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;       // no match
        }
        cursor.close();
        return true;           // match found
    }
    private boolean checkShipmentsDuplicate(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + SHIPMENT_TABLE + " WHERE " + SHIPMENT_COL1_SHIPMENTNUM + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;       // no match
        }
        cursor.close();
        return true;           // match found
    }

    //check for duplicates
    private boolean checkCustomerDuplicate(int ID) {
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

    //check for duplicates
    private boolean checkProductDuplicate(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + PRODUCTS_TABLE + " WHERE " + PRODUCTS_COL1_PRODID + "=" + ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;       // no match
        }
        cursor.close();
        return true;           // match found
    }

    public boolean insertCustomer(int customerID, String customer_Fname, String customer_Lname, String customer_phone, String customer_email) {
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

    public boolean insertStaff (int staffID, String staff_fname, String staff_lname, String staff_phone, String staff_email) {
        boolean check;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("staff_id", staffID);
        contentValues.put("first_name", staff_fname);
        contentValues.put("last_name", staff_lname);
        contentValues.put("phone", staff_phone);
        contentValues.put("email", staff_email);
        check = checkStaffExists(staffID);
        if (!check) {       // if there's no match, add
            long result = db.insert("staff", "", contentValues);
            return result != -1;
        } else {
            return false;
        }
    }

    public boolean insertSupplier (int supplierID, String supplier_name, String supplier_number, String supplier_email) {
        boolean check;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("supplier_id", supplierID);
        contentValues.put("supplier_name", supplier_name);
        contentValues.put("supplier_number", supplier_number);
        contentValues.put("supplier_email", supplier_email);
        check = checkSupplierExists(supplierID);
        if (!check) {       // if there's no match, add
            long result = db.insert("supplier", "", contentValues);
            return result != -1;
        } else {
            return false;
        }
    }

    public int  insertShipment(int shipmentID, int supplier_id, int shipment_quote, String shipment_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("shipment_num", shipmentID);
        contentValues.put("shipment_supplier_id", supplier_id);
        contentValues.put("shipment_quote", shipment_quote);
        contentValues.put("shipment_date", shipment_date);
        if (checkShipmentsDuplicate(shipmentID)) {
            //duplicate exists so error code 2
            return 2;
        }else if(!checkSupplierExists(supplier_id)){
            return 3;
        }else {
            db.insert("shipment", "", contentValues);
            return 1;
        }

    }

    public int insert_shipment_items(int shipmentID, int item_id, int product_id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("shipment_num", shipmentID);
        contentValues.put("item_id", item_id);
        contentValues.put("shipment_product_id", product_id);
        contentValues.put("shipment_quantity", quantity);
        if (checkShipmentItemsDuplicate(item_id)) {
            //duplicate exists so error code 2
            return 2;
        }else if(!checkProductExists(product_id)){
            return 3;
        }else {
            db.insert("shipment_items", "", contentValues);
            return 1;
        }

    }

    public boolean insertProduct(int prodID, String ProductName, String ProductBrand, String ProductCategory, int ProductYear, double ProductListPrice, int ProductQuantity) {
        boolean check;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id", prodID);
        contentValues.put("product_name", ProductName);
        contentValues.put("brand", ProductBrand);
        contentValues.put("category", ProductCategory);
        contentValues.put("model_year", ProductYear);
        contentValues.put("list_price", ProductListPrice);
        contentValues.put("stock", ProductQuantity);
        check = checkProductDuplicate(prodID);
        if (!check) {       // if there's no match, add
            long result = db.insert("products", "", contentValues);
            return result != -1;
        } else {
            return false;
        }
    }
    public void decreaseQuantity(int productID,int quantity){
        SQLiteDatabase db = this.getWritableDatabase();

        String update_query="UPDATE products SET stock = stock - "+quantity+" WHERE product_id = "+productID;
        db.execSQL(update_query);
    }
    public void increaseQuantity(int productID,int quantity){
        SQLiteDatabase db = this.getWritableDatabase();

        String update_query="UPDATE products SET stock = stock + "+quantity+" WHERE product_id = "+productID;
        db.execSQL(update_query);
    }
    public boolean enoughQuantity(int productID,int quantity){
        SQLiteDatabase db = this.getWritableDatabase();

        String select_query="SELECT stock FROM products WHERE product_id= "+productID;
        Cursor c=db.rawQuery(select_query,null);
        int stock=0;
        while(c.moveToNext()){
            stock=c.getInt(c.getColumnIndex("stock"));
        }
        if(stock-quantity<0){
            return false;
        }else{
            return true;
        }
    }


    public boolean updateProduct(int prodID, String ProductName, String ProductBrand, String ProductCategory, int ProductYear, double ProductListPrice, int ProductQuantity) {
        // boolean update;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("product_id", prodID);
        contentValues.put("product_name", ProductName);
        contentValues.put("brand", ProductBrand);
        contentValues.put("category", ProductCategory);
        contentValues.put("model_year", ProductYear);
        contentValues.put("list_price", ProductListPrice);
        contentValues.put("stock", ProductQuantity);
        long result = db.update("products", contentValues, "product_id = ?", new String[]{String.valueOf(prodID)});
        System.out.println("After input");
        return result != -1;
    }

    public boolean updateCustomer(int customerID, String customer_Fname, String customer_Lname, String customer_phone, String customer_email) {
        boolean update;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", customer_Fname);
        contentValues.put("last_name", customer_Lname);
        contentValues.put("phone", customer_phone);
        contentValues.put("email", customer_email);
//        String update_query = "UPDATE " + CUSTOMERS_TABLE + " SET " + CUSTOMERS_COL2_FNAME + "='" + customer_Fname + "', " + CUSTOMERS_COL3_LNAME + "='" + customer_Lname + "', "
//                            + CUSTOMERS_COL4_PHONE + "='" + customer_phone + "', " + CUSTOMERS_COL5_EMAIL + "='" + customer_email + "' WHERE " + CUSTOMERS_COL1_ID + "=" + customerID;
//       db.execSQL(update_query);
        long result = db.update("customers", contentValues, "customer_id = ?", new String[]{String.valueOf(customerID)});
        System.out.println("After update");
        return result != -1;
    }

    public boolean updateStaff(int staffID, String staff_fname, String staff_lname, String staff_phone, String staff_email) {
        boolean update;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", staff_fname);
        contentValues.put("last_name", staff_lname);
        contentValues.put("phone", staff_phone);
        contentValues.put("email", staff_email);
        long result = db.update("staff", contentValues, "staff_id = ?", new String[]{ String.valueOf(staffID) });
        System.out.println("After update");
        return result != -1;
    }

    public boolean updateSupplier(int supplierID, String supplier_name, String supplier_num, String supplier_email) {
        boolean update;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("supplier_name", supplier_name);
        contentValues.put("supplier_number", supplier_num);
        contentValues.put("supplier_email", supplier_email);
        long result = db.update("supplier", contentValues, "supplier_id = ?", new String[]{ String.valueOf(supplierID) });
        System.out.println("After update");
        return result != -1;
    }


    //getMethods
    public Cursor getCustomers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_customer = "SELECT * FROM " + CUSTOMERS_TABLE;
        return db.rawQuery(select_customer, null);
    }

    public Cursor getStaff() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_staff = "SELECT * FROM " + STAFF_TABLE;
        return db.rawQuery(select_staff, null);
    }

    //getMethods
    public Cursor getShipments() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_shipment = "SELECT * FROM " + SHIPMENT_TABLE;
        return db.rawQuery(select_shipment, null);
    }

    public Cursor getProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_products = "SELECT * FROM " + PRODUCTS_TABLE;
        return db.rawQuery(select_products, null);
    }

    public Cursor get_Shipment_items(int shipment_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_orders = "SELECT * FROM " + SHIPMENT_ITEMS_TABLE +
                " WHERE "+ SHIPMENT_ITEMS_COL4_SHIPMENTNUM +" = "+shipment_id;
        return db.rawQuery(select_orders,null);
    }

    public Cursor getSuppliers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_supplier = "SELECT * FROM " + SUPPLIER_TABLE;
        return db.rawQuery(select_supplier, null);
    }

    public Cursor getSupplierName(int supplier_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_supplier = "SELECT supplier_name FROM " + SUPPLIER_TABLE + " WHERE " + SUPPLIER_COL1_ID + " = " + supplier_id;
        return db.rawQuery(select_supplier,null);
    }

    public Cursor getPrice_Of_One_purchase(int itemID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT p.list_price*si.shipment_quantity AS totalPrice FROM" + " shipment_items si, products p WHERE si.item_id=? AND p.product_id=si.shipment_product_id";
        return db.rawQuery(select_query, new String[]{ String.valueOf(itemID)} );
    }
    public Cursor getPrice_Of_One_sale(int itemID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT o.price*((100-o.discount)/100)*o.quantity AS totalPrice FROM" + " order_items o WHERE o.item_id=?";
        return db.rawQuery(select_query, new String[]{ String.valueOf(itemID)} );
    }
    public Cursor getProductName(int productID){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT product_name FROM" + " products  WHERE product_id=?";
        return db.rawQuery(select_query, new String[]{ String.valueOf(productID)} );
    }
    public Cursor get_price_and_name(int itemID){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT p.product_name, p.list_price FROM" + " shipment_items si, products p WHERE si.item_id=? AND p.product_id=si.shipment_product_id";
        return db.rawQuery(select_query, new String[]{ String.valueOf(itemID)} );
    }
    public Cursor get_price_only(int productID){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT p.list_price FROM" + " products p WHERE p.product_id=?";
        return db.rawQuery(select_query, new String[]{ String.valueOf(productID)} );
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

    public Cursor get_Shipment_Details(int shipment_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_orders = "SELECT * FROM " + SHIPMENT_TABLE +
                " WHERE "+ SHIPMENT_COL1_SHIPMENTNUM +" = "+shipment_id;
        return db.rawQuery(select_orders,null);
    }


    public Cursor getAllFrom_Order_items(int order_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_order_items = "SELECT * FROM " + ORDER_ITEMS_TABLE +
                " WHERE "+ ORDERS_ORDER_ID +" = "+order_id;
        return db.rawQuery(select_order_items,null);
    }
    public void deleteOrders_and_related_items(int order_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_orders = "DELETE FROM " + ORDERS_TABLE +
                " WHERE "+ ORDERS_ORDER_ID +" = "+order_id;
        String delete_order_items = "DELETE FROM " + ORDER_ITEMS_TABLE +
                " WHERE "+ ORDERS_ORDER_ID +" = "+order_id;
        db.execSQL(delete_order_items);
        db.execSQL(delete_orders);

    }
    public void deleteShipment_and_related_items(int shipment_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_shipments = "DELETE FROM " + SHIPMENT_TABLE +
                " WHERE "+ SHIPMENT_COL1_SHIPMENTNUM +" = "+shipment_id;
        String delete_shipment_items = "DELETE FROM " + SHIPMENT_ITEMS_TABLE +
                " WHERE "+ SHIPMENT_ITEMS_COL4_SHIPMENTNUM +" = "+shipment_id;
        db.execSQL(delete_shipment_items);
        db.execSQL(delete_shipments);


    }

    public void deleteProduct(int prod_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_prod = "DELETE FROM " + PRODUCTS_TABLE +
                " WHERE "+ PRODUCTS_COL1_PRODID +" = "+prod_id;
        db.execSQL(delete_prod);
    }

    public Cursor getCustomerTransactions(int custID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT c.first_name, c.last_name, o.order_id, oi.price*oi.quantity*((100-oi.discount)/100) AS totalPrice FROM " + "customers c, orders o, order_items oi WHERE c.customer_id = o.customer_id AND o.order_id = oi.order_id AND c.customer_id = ?";
        return db.rawQuery(select_query, new String[]{ String.valueOf(custID)} );
    }

    public Cursor getShipmentDetails(int supplierID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "SELECT si.item_id, s.shipment_num, s.shipment_date, SUM((p.list_price*si.shipment_quantity)*1.05) AS totalPrice " +
                                "FROM " + "shipment s, shipment_items si, products p, supplier sp " +
                                "WHERE sp.supplier_id = s.shipment_supplier_id " +
                                "AND s.shipment_num = si.shipment_num " +
                                "AND si.shipment_product_id = p.product_id " +
                                "AND sp.supplier_id = ? " +
                                "GROUP BY s.shipment_num";
        return db.rawQuery(select_query, new String[]{ String.valueOf(supplierID)} );
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
