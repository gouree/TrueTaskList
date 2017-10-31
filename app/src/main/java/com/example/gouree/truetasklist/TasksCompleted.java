package com.example.gouree.truetasklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//class handling completed tasks

public class TasksCompleted extends AppCompatActivity {
    DataBaseClass dBhelper;
    ListView lsttask;
    ArrayAdapter<String> madapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_completed);
        //object of dbhelper class
        dBhelper = new DataBaseClass(this);
        Bundle bundle = new Bundle();
        bundle= getIntent().getExtras();
        String name  = (bundle.getString("namekey"));

        Toast.makeText(this, "value in completed app  -->" + name, Toast.LENGTH_SHORT).show();
        lsttask =(ListView) findViewById(R.id.listview);
        Button btn = (Button)findViewById(R.id.buttonbk);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TasksCompleted.this, MainActivity.class);
                startActivity(i);
            }
        });


        //name is value recieved as a bundle from task detail class of the tasks marked as completed
        //insert this task in database and load list

        dBhelper.insertNewTask(name);
        loadTaskList();



    }


    //loadlist of completed tasks
    private void loadTaskList()
    {

        //set adapters and use Arraylist
        ArrayList<String> taskList = dBhelper.getTaskList();

        if (madapter == null)
        {
            madapter = new ArrayAdapter<String>(this, R.layout.row, R.id.tasktitle, taskList);
            lsttask.setAdapter(madapter);
        }
        else
        {
            madapter.clear();
            madapter.addAll(taskList);
            madapter.notifyDataSetChanged();
        }

    }

    //dete completed tasks
    public void deleteTask(View view)
    {

        View parent = (View) view.getParent();
        TextView taskText = (TextView) findViewById(R.id.tasktitle);
        String task = String.valueOf(taskText.getText());
        dBhelper.deleteTask(task);
        loadTaskList();
        Toast.makeText(this, "deleted task-->" +task, Toast.LENGTH_SHORT).show();
    }
}
