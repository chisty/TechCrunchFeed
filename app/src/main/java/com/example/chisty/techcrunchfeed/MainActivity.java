package com.example.chisty.techcrunchfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            taskFragment= new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction().add(taskFragment, "TaskFragment").commit();
        }else{
            taskFragment= (PlaceholderFragment)getSupportFragmentManager().findFragmentByTag("TaskFragment");
        }
        taskFragment.StartTask();
    }


    //region Property
    PlaceholderFragment taskFragment;
    //endregion

}
