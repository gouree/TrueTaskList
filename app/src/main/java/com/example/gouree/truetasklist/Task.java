package com.example.gouree.truetasklist;

import java.util.Date;

/**
 * Created by omsai on 28/10/2017.
 */


//class holding the tables and column names of task list
public class Task
{
    public static final String TABLE = "Task";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "task_name";
    public static final String KEY_desc = "description";
    public static final String KEY_status = "status";
    public static final String KEY_date = "date";

    // property help us to keep data
    public int task_ID;
    public String name;
    public String desc;
    public int status;
    public String date;
}
