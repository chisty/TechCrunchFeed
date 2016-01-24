package com.example.chisty.techcrunchfeed;

import android.content.Context;
import android.os.AsyncTask;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by chisty on 1/24/2016.
 */
public class TechCrunchTask extends AsyncTask<Void, Void, ArrayList<HashMap<String, String>>> {

    public TechCrunchTask(IResultCallback callback) {
        this.callback= callback;
    }

    public void OnAttach(IResultCallback callback){
        this.callback= callback;
    }

    public void OnDetach(){
        this.callback= null;
    }

    @Override
    protected void onPreExecute() {
        if(callback != null){
            callback.OnPreExecute();
        }

    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
        if(callback != null){
            callback.OnPostExecute(result);
        }
    }

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
        ArrayList<HashMap<String, String>> result= new ArrayList<>();
        try {
            URL url= new URL(downloadURL);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream= connection.getInputStream();
            result= ProcessXML(inputStream);
        } catch (Exception e) {
            Helper.Log(e + "");
        }
        return result;
    }

    public ArrayList<HashMap<String, String>> ProcessXML(InputStream inputStream) throws Exception{
        DocumentBuilder builder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document xmlDocument=  builder.parse(inputStream);
        Element rootElement= xmlDocument.getDocumentElement();
        Helper.Log("" + rootElement.getTagName());
        NodeList itemList= rootElement.getElementsByTagName("item");

        NodeList itemChildren;
        Node currentItem, currentChild;
        ArrayList<HashMap<String, String>> result= new ArrayList<>();
        HashMap<String, String>  map;
        int count=0;

        for (int i=0; i<itemList.getLength(); i++){
            map= new HashMap<>();
            currentItem= itemList.item(i);
            itemChildren= currentItem.getChildNodes();

            for(int j=0; j<itemChildren.getLength(); j++){
                currentChild= itemChildren.item(j);
                if(currentChild.getNodeName().equalsIgnoreCase("title")){
                    map.put("title", currentChild.getTextContent());
                }
                else if(currentChild.getNodeName().equalsIgnoreCase("pubDate")){
                    map.put("pubDate", currentChild.getTextContent());
                }
                else if(currentChild.getNodeName().equalsIgnoreCase("description")){
                    map.put("description", currentChild.getTextContent());
                }
                else if(currentChild.getNodeName().equalsIgnoreCase("media:thumbnail")){
                    count++;
                    if(count == 2) {
                        map.put("imageURL", currentChild.getAttributes().item(0).getTextContent());
                    }

                }
            }

            if(map.isEmpty() == false){
                result.add(map);
            }
            count= 0;
        }
        return result;
    }

    //region Property
    private String downloadURL= "http://feeds.feedburner.com/techcrunch/android?format=xml";
    private IResultCallback callback;
    //endregion
}
