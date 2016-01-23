package com.example.chisty.techcrunchfeed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    public void StartTask(){
        if(task != null){
            task.cancel(true);
        }else{
            task= new TechCrunchTask();
            task.execute();
        }
    }

    //region Property
    TechCrunchTask task;
    //endregion
}
