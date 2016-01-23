package com.example.chisty.techcrunchfeed;

import android.os.AsyncTask;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by chisty on 1/24/2016.
 */
public class TechCrunchTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url= new URL(downloadURL);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream= connection.getInputStream();
            ProcessXML(inputStream);
        } catch (Exception e) {
            Helper.Log(e + "");
        }
        return null;
    }


    public void ProcessXML(InputStream inputStream) throws Exception{
        DocumentBuilder builder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document xmlDocument=  builder.parse(inputStream);
        Element rootElement= xmlDocument.getDocumentElement();
        Helper.Log("" + rootElement.getTagName());
        NodeList itemList= rootElement.getElementsByTagName("item");

        NodeList itemChildren= null;
        Node currentItem, currentChild;
        for (int i=0; i<itemList.getLength(); i++){
            currentItem= itemList.item(i);
            itemChildren= currentItem.getChildNodes();

            for(int j=0; j<itemChildren.getLength(); j++){
                currentChild= itemChildren.item(j);
                if(currentChild.getNodeName().equalsIgnoreCase("title")){
                    Helper.Log(currentChild.getTextContent());
                }
            }
        }
    }

    //region Property
    String downloadURL= "http://feeds.feedburner.com/techcrunch/android?format=xml";
    //endregion
}
