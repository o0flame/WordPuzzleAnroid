package edu.gatech.seclass.sdpscramble.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.gatech.seclass.sdpscramble.model.InProgressScramble;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by panpan on 10/10/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PlayerManager.db";

    // player table name
    private static final String TABLE_PLAYER = "InProgressScramble";

    // User Table Columns names
    private static final String COLUMN_PLAYER_UID = "player_uniqueusername";
    private static final String COLUMN_SCRAMBLE_ID = "scramble_id";
    private static final String COLUMN_SCRAMBLE_INPUT = "scramble_inputWord";


    // create table sql query
    private String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYER + "("
            + COLUMN_PLAYER_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SCRAMBLE_ID+ " TEXT,"
            + COLUMN_SCRAMBLE_INPUT+ " TEXT" + ")";

    // drop table sql query
    private String DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLAYER;

    /**
     * Constructor
     *
     * @param context
     */


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_PLAYER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param scramble
     */
    public void addInProgress(InProgressScramble scramble) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_UID, scramble.getPlayerID());
        values.put(COLUMN_SCRAMBLE_ID, scramble.getUniqueIdentifier());
        values.put(COLUMN_SCRAMBLE_INPUT, scramble.getInputWord());



        // Inserting Row
        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all record and return the list of InProgressScramble records
     *
     * @return list
     */
    public List<InProgressScramble> getAllInProgressScramble() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PLAYER_UID,
                COLUMN_SCRAMBLE_ID,
                COLUMN_SCRAMBLE_INPUT,

        };
        // sorting orders
        String sortOrder =
                COLUMN_PLAYER_UID + " ASC";
        List<InProgressScramble> scrambleList = new ArrayList<InProgressScramble>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_PLAYER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InProgressScramble scramble= new InProgressScramble();
                scramble.setPlayerID(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYER_UID)));
                scramble.setUniqueIdentifier(cursor.getString(cursor.getColumnIndex(COLUMN_SCRAMBLE_ID)));
                scramble.setInputWord(cursor.getString(cursor.getColumnIndex(COLUMN_SCRAMBLE_INPUT)));

                // Adding user record to list
                scrambleList.add(scramble);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return scrambleList;
    }

    /**
     * This method to update user record
     *
     * @param scramble
     */
    public void updateUser(InProgressScramble scramble) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_UID, scramble.getPlayerID());
        values.put(COLUMN_SCRAMBLE_ID, scramble.getUniqueIdentifier());
        values.put(COLUMN_SCRAMBLE_INPUT, scramble.getInputWord());
        // updating row
        db.update(TABLE_PLAYER, values, COLUMN_PLAYER_UID + " = ?",
                new String[]{String.valueOf(scramble.getPlayerID())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param scramble
     */
    public void deleteUser(InProgressScramble scramble) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_PLAYER, COLUMN_PLAYER_UID+ " = ?",
                new String[]{String.valueOf(scramble.getPlayerID())});
        db.close();
    }




}
