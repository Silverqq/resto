package com.example.resto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;

    // Таблица Пространство
    private static final String TABLE_SPACE = "Пространство";
    private static final String COL_SPACE_ID = "ID_Пространство";
    private static final String COL_SPACE_NAME = "Название";

    // Таблица Меню
    private static final String TABLE_MENU = "Меню";
    private static final String COL_MENU_ID = "ID_Menu";

    // Таблица Стол
    public static final String TABLE_TABLE = "Стол";
    public static final String COL_TABLE_ID = "ID_Стола";

    // Таблица Счет
    public static final String TABLE_BILL = "Счет";
    public static final String COL_BILL_ID = "ID_Счета";
    public static final String COL_BILL_AMOUNT = "Сумма";

    // Таблица Продукт
    public static final String TABLE_PRODUCT = "Продукт";
    public static final String COL_PRODUCT_ID = "ID_Product";
    public static final String COL_PRODUCT_PRICE = "Цена";
    public static final String COL_PRODUCT_NAME = "Название";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы Пространство
        String CREATE_SPACE_TABLE = "CREATE TABLE " + TABLE_SPACE + "("
                + COL_SPACE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SPACE_NAME + " VARCHAR(255) NOT NULL)";
        db.execSQL(CREATE_SPACE_TABLE);

        // Создание таблицы Меню с внешним ключом
        String CREATE_MENU_TABLE = "CREATE TABLE " + TABLE_MENU + "("
                + COL_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SPACE_ID + " INTEGER, "
                + "FOREIGN KEY(" + COL_SPACE_ID + ") REFERENCES "
                + TABLE_SPACE + "(" + COL_SPACE_ID + "))";
        db.execSQL(CREATE_MENU_TABLE);

        // Создание таблицы Стол
        String CREATE_TABLE_TABLE = "CREATE TABLE " + TABLE_TABLE + "("
                + COL_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SPACE_ID + " INTEGER, "
                + "FOREIGN KEY(" + COL_SPACE_ID + ") REFERENCES "
                + TABLE_SPACE + "(" + COL_SPACE_ID + "))";
        db.execSQL(CREATE_TABLE_TABLE);

        // Создание таблицы Счет
        String CREATE_BILL_TABLE = "CREATE TABLE " + TABLE_BILL + "("
                + COL_BILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TABLE_ID + " INTEGER, "
                + COL_BILL_AMOUNT + " INTEGER, "
                + "FOREIGN KEY(" + COL_TABLE_ID + ") REFERENCES "
                + TABLE_TABLE + "(" + COL_TABLE_ID + "))";
        db.execSQL(CREATE_BILL_TABLE);

        // Создание таблицы Продукт
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + COL_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_BILL_ID + " INTEGER, "
                + COL_MENU_ID + " INTEGER, "
                + COL_PRODUCT_PRICE + " INTEGER, "
                + COL_PRODUCT_NAME + " VARCHAR(255), "
                + "FOREIGN KEY(" + COL_BILL_ID + ") REFERENCES "
                + TABLE_BILL + "(" + COL_BILL_ID + "), "
                + "FOREIGN KEY(" + COL_MENU_ID + ") REFERENCES "
                + TABLE_MENU + "(" + COL_MENU_ID + "))";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPACE);
        onCreate(db);
    }
}