package com.example.gouree.truetasklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by omsai on 31/10/2017.
 */

//database class for  handling completed task lst

public class DataBaseClass extends SQLiteOpenHelper
{


    // database name ,version,column name for completed task list
    public static final String TASK_NAME ="task_name";
    public static final String DATABASE_NAME ="user_info";
    public static final String TABLE_NAME ="reg_info";
    public static final int database_version =1;


    public static final String DB_NAME ="task_details";
    public static final String TBL_NAME ="task_description";
    public static final int db_version =1;
    public static final String TASK_DESC ="task_desc";



    public DataBaseClass(Context context)
    {
        super(context, DATABASE_NAME,null,database_version);
        Log.d("Database  operations","Database created");
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
       //create a table
        String CREATE_QUERY ="CREATE TABLE "+ TABLE_NAME + "(" + TASK_NAME + " TEXT ); ";
        db.execSQL(CREATE_QUERY);
        Log.d("Database  operations","Table created");


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        String query = String.format("DELETE TABLE IF EXISTS %s",TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }


    //insert task name in completed list table
    public void insertNewTask(String task)
    {
        SQLiteDatabase SQ= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK_NAME, task);

        long k = SQ.insert(TABLE_NAME,null,cv);
        Log.d("Database  operations","One row inserted");
    }


    //delete completed task
    public void deleteTask(String task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, TASK_NAME + " = ?",new String[]{task});
        // db.delete(DB_TABLE, DB_COLUMN + " = ?", new String[]{task});
        db.close();
    }


    //array list of completed task
    public ArrayList<String> getTaskList()
    {
        ArrayList<String> data=new ArrayList<String>();
        SQLiteDatabase SQ = this.getReadableDatabase();
        String[] columns = {TASK_NAME };
        Cursor CR = SQ.query(TABLE_NAME,columns,null,null,null,null,null);
        String fieldToAdd=null;

        while(CR.moveToNext())
        {
            fieldToAdd = CR.getString(0);
            data.add(fieldToAdd);
        }
        CR.close();
        return data;


    }


    public Cursor getData (String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }
}
