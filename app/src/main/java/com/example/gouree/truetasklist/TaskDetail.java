package com.example.gouree.truetasklist;

/**
 * Created by omsai on 28/10/2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//class handling events occuring on tasks --> insert,delete,completed ,close

public class TaskDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    //buttons to delete,close,save,completed
    Button btnSave , btnDelete;
    Button btnClose , btnCompleted;
    EditText editTextName;
    EditText editTextDesc;


    DatePicker datepicker;
    //EditText editTextAge;


    //set task id and status to 0
    private int _Task_Id=0;
    private int _Status=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        //set values
        btnSave = (Button) findViewById(R.id.Save);
        btnDelete = (Button) findViewById(R.id.Delete);
        btnClose = (Button) findViewById(R.id.Close);
        btnCompleted =(Button)findViewById(R.id.Completed);
        editTextName = (EditText) findViewById(R.id.tadd);
        editTextDesc = (EditText) findViewById(R.id.tdesc);
        datepicker =(DatePicker) findViewById(R.id.datePicker);


        //get day,month and year
        String day =  String.valueOf(datepicker.getDayOfMonth());
        String month = String.valueOf(datepicker.getMonth()+1);
        String year = String.valueOf(datepicker.getYear());

        String val1 = day.concat("/");
        String val2 = val1.concat(month);
        String val3 = val2.concat("/");
        String date = val3.concat(year);



        //set onclick listners for buttons
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnCompleted.setOnClickListener(this);


        _Task_Id =0;
        Intent intent = getIntent();
        _Task_Id =intent.getIntExtra("task_Id", 0);
        TaskRepo repo = new TaskRepo(this);
        Task task = new Task();
        task = repo.getTaskById(_Task_Id);

        editTextName.setText(task.name);
        editTextDesc.setText(task.desc);


       // editTextEmail.setText(student.email);
   }



    public void onClick(View view) {

        //save button
        if (view == findViewById(R.id.Save)){
            TaskRepo repo = new TaskRepo(this);
            Task task = new Task();
            task.name= editTextName.getText().toString();
            task.desc =editTextDesc.getText().toString();

            task.task_ID= _Task_Id;
            task.status = _Status;

            String day =  String.valueOf(datepicker.getDayOfMonth());
            String month = String.valueOf(datepicker.getMonth()+1);
            String year = String.valueOf(datepicker.getYear());

            String val1 = day.concat("/");
            String val2 = val1.concat(month);
            String val3 = val2.concat("/");
            String date = val3.concat(year);

            //send values to insert method in Taskrepo class
            if (_Task_Id==0){
                _Task_Id = repo.insert(task,date);

                Toast.makeText(this,"New Task Inserted",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(task);
                Toast.makeText(this,"Task Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.Delete)){
            //delete task handling
            TaskRepo repo = new TaskRepo(this);
            //call delete method in Taskrepo class
            repo.delete(_Task_Id);
            Toast.makeText(this, "Task Record Deleted", Toast.LENGTH_SHORT).show();

            finish();
          Intent i =  new Intent(getApplicationContext(),MainActivity.class);
          startActivity(i);
            Toast.makeText(this, "Task Record Deleted", Toast.LENGTH_SHORT).show();
        }else if (view== findViewById(R.id.Completed)) {
            //completed tasks handling
            TaskRepo repo = new TaskRepo(this);
             Intent intent = new Intent(TaskDetail.this,TasksCompleted.class);
            Bundle bundle = new Bundle();
            //send id to gettaskname method of Taskrepo class and get name
            String name = repo.getTaskName(_Task_Id);
            //send name to Taskscompleted class
            bundle.putString("namekey",name);
            Toast.makeText(this, "value -->"+ name, Toast.LENGTH_SHORT).show();
            intent.putExtras(bundle);
            startActivity(intent);
            //delete the completed tasks from task list
            repo.delete(_Task_Id);
            Toast.makeText(this, "Task Record Marked as completed", Toast.LENGTH_SHORT);
            finish();
        /*   Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);*/
        }else if (view== findViewById(R.id.Close)){
            // close button handling
            finish();

            Intent i =  new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }


    }

}
