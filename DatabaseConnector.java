package com.example.it_lab_local.petlabdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector {

    private static final String DATABASE_NAME = "myDogs";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    public void open() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            database.close();
        }
    }

    public void updateDog(long id, String breed, String name, int age) {
        ContentValues editContact = new ContentValues();
        editContact.put("breed", breed);
        editContact.put("name", name);
        editContact.put("age", age);

        open();
        database.update("dogs", editContact, "_id=" + id, null);
        close();
    }

    public Cursor getAllDogs()
    {
        return database.query("dogs", new String[] {"_id", "breed", "name", "age"},
                null, null, null, null, "breed");
    } // end method getAllContacts


    public Cursor getOneContact(long id) {
        return database.query("contacts", null, "_id=" + id, null, null , null , null);
    }

    public void deleteDog(long id) {
        open();
        database.delete("dogs", "_id=" + id, null);
    }

    public void insertDog(String breed, String name, int age) {
        try {
            ContentValues newDog = new ContentValues();
            newDog.put("breed", breed);
            newDog.put("name", name);
            newDog.put("age", age);

            open();
            database.insert("dogs", null, newDog);
            close();
        } catch (Exception e){}
    }


    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String  createQuery = "CREATE TABLE dogs" +
                    "(_id integer primary key autoincrement," +
                    "breed TEXT," +
                    "name TEXT," +
                    "age INTEGER);";

            sqLiteDatabase.execSQL(createQuery); // potential error, should use object reference "db", not sqLiteDatebase
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
