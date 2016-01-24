package com.example.chisty.techcrunchfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IResultCallback {

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

        articlesListView = (ListView) findViewById(R.id.articlesListView);
    }

    @Override
    public void OnPreExecute() {

    }

    @Override
    public void OnPostExecute(ArrayList<HashMap<String, String>> result) {
        articlesListView.setAdapter(new MyAdapter(this, result));
    }

    PlaceholderFragment taskFragment;
    ListView articlesListView;
}

