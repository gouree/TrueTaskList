package com.example.gouree.truetasklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

//class handling list view of task items
public class HomeScreen extends ListActivity {
    Button btnAdd,btnGetAll;
    TextView taskid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
            btnGetAll = (Button) findViewById(R.id.btnGetAll);
            btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


        //object of taskrepo class
        TaskRepo repo = new TaskRepo(this);

        //create a hashmap for list
        ArrayList<HashMap<String, String>> taskList =  repo.getTaskList();
        if(taskList.size()!=0) {
            final ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    taskid = (TextView) view.findViewById(R.id.taskid);
                    //fetch the unique ids of tasks
                    String taskId = taskid.getText().toString();
                    //send id as a bundle to taskdetail class
                    Intent objIndent = new Intent(getApplicationContext(),TaskDetail.class);
                    objIndent.putExtra("task_Id", Integer.parseInt( taskId));
                    startActivity(objIndent);

                }
            });

            //list adapter displaying name,description,id and date
            ListAdapter adapter = new SimpleAdapter( HomeScreen.this,taskList, R.layout.view_task_entry, new String[] {"name","desc","id","date"}, new int[] {R.id.Taskname, R.id.taskdes,R.id.taskid,R.id.taskdate});
            setListAdapter(adapter);

        }else{

            //if task list is empty show message
            Toast.makeText(this,"No task!",Toast.LENGTH_SHORT).show();
        }


    }


}

