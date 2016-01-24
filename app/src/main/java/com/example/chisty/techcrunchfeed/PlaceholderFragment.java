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
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback= (IResultCallback)context;
            if(task != null){
                task.OnAttach(callback);
            }
        }
        catch (Exception e){
            throw e;
        }
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
            task= new TechCrunchTask(callback);
            task.execute();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback= null;
        if(task != null){
            task.OnDetach();
        }
    }

    //region Property
    TechCrunchTask task;
    IResultCallback callback;
    //endregion
}
