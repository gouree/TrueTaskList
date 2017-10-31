package com.example.gouree.truetasklist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

//mainactivity class
public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    //inflate a options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
       return super.onCreateOptionsMenu(menu);
    }




    //menus for add task, display task and completed task list
   @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId())
        {
            //adding a new task
            case R.id.action_addtask:

                Intent intent = new Intent(this,TaskDetail.class);
                intent.putExtra("task_Id",0);
                startActivity(intent);
                break;

            case R.id.action_displaytask:
                // displaying the task list

                Intent i = new Intent(this,HomeScreen.class);
                i.putExtra("task_Id",0);
                startActivity(i);
                break;

          case R.id.action_taskstat:
              //displaying the completed task list
                Intent in = new Intent(this,TasksCompleted.class);
                Bundle bundle = new Bundle();
                String name = " ";
                bundle.putString("namekey",name);
                Toast.makeText(this, "value -->"+ name, Toast.LENGTH_SHORT).show();
                in.putExtras(bundle);
                startActivity(in);


        }


        return super.onOptionsItemSelected(item);
    }




}