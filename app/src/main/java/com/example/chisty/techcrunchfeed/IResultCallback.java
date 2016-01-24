package com.example.chisty.techcrunchfeed;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chisty on 1/24/2016.
 */
public interface IResultCallback {
    void OnPreExecute();
    void OnPostExecute(ArrayList<HashMap<String, String>> result);
}
