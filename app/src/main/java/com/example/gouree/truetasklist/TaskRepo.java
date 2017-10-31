package com.example.gouree.truetasklist;

/**
 * Created by omsai on 28/10/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

//taskrepo class handling all database functions of task list

public class TaskRepo {
    ImageView img;
    private DBHelper dbHelper;

    public TaskRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Task task,String date) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Task.KEY_name, task.name);
        values.put(Task.KEY_desc,task.desc);
        values.put(Task.KEY_status,task.status);
        values.put(Task.KEY_date,date);

        // Inserting Row
        long task_Id = db.insert(task.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) task_Id;
    }


    //deteting a row
    public void delete(int task_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Task.TABLE, Task.KEY_ID + "= ?", new String[]{String.valueOf(task_Id)});
        db.close(); // Closing database connection
    }


    //updating a row
    public void update(Task task) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.KEY_name,task.name);
        values.put(Task.KEY_desc,task.desc);
        values.put(Task.KEY_status,task.status);
        values.put(Task.KEY_date,task.date);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Task.TABLE, values, Task.KEY_ID + "= ?", new String[]{String.valueOf(task.task_ID)});
        db.close(); // Closing database connection
    }
    //get complete task list using hashmaps

    public ArrayList<HashMap<String, String>> getTaskList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Task.KEY_ID + "," +
                Task.KEY_name + "," +
                Task.KEY_desc + "," +
                Task.KEY_date + "," +
                Task.KEY_status +
                " FROM " + Task.TABLE + " ORDER BY " +
                  Task.KEY_date +" ASC " ;


        ArrayList<HashMap<String, String>> taskList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> task = new HashMap<String, String>();
                task.put("id", cursor.getString(cursor.getColumnIndex(Task.KEY_ID)));
                task.put("name", cursor.getString(cursor.getColumnIndex(Task.KEY_name)));
                task.put("desc", cursor.getString(cursor.getColumnIndex(Task.KEY_desc)));
                task.put("date", cursor.getString(cursor.getColumnIndex(Task.KEY_date)));
                taskList.add(task);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskList;

    }



    //get task corresponding to a particular id
    public Task getTaskById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Task.KEY_ID + "," +
                Task.KEY_name + "," +
                Task.KEY_desc + "," +
                Task.KEY_date + "," +
                Task.KEY_status +
                " FROM " + Task.TABLE
                + " WHERE " +
                Task.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Task task = new Task();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                task.task_ID = cursor.getInt(cursor.getColumnIndex(Task.KEY_ID));
                task.name = cursor.getString(cursor.getColumnIndex(Task.KEY_name));
                task.desc = cursor.getString(cursor.getColumnIndex(Task.KEY_desc));
                task.status = cursor.getInt(cursor.getColumnIndex(Task.KEY_status));
                task.date = cursor.getString(cursor.getColumnIndex(Task.KEY_date));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return task;
    }


    //get task name coresponding to a particular id

    public String getTaskName(int Id) {


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Task.KEY_ID + "," +
                Task.KEY_name + "," +
                Task.KEY_desc + "," +
                Task.KEY_date + "," +
                Task.KEY_status +
                " FROM " + Task.TABLE
                 + " WHERE " +
                Task.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string


        int iCount = 0;
        Task task = new Task();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                task.task_ID = cursor.getInt(cursor.getColumnIndex(Task.KEY_ID));
                task.name = cursor.getString(cursor.getColumnIndex(Task.KEY_name));
                task.desc = cursor.getString(cursor.getColumnIndex(Task.KEY_desc));
                task.status = cursor.getInt(cursor.getColumnIndex(Task.KEY_status));
                task.date = cursor.getString(cursor.getColumnIndex(Task.KEY_date));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();


        return task.name;
    }







}
