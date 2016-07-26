package com.example.a.p01_xml_listview_1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    class MyDomParser extends AsyncTask<String,Void,Document>{

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            String str="";
            NodeList nodeList = document.getElementsByTagName("data");
            for(int i=0;i<nodeList.getLength();i++){
                Element data = (Element) nodeList.item(i);
                String strHour = getText(data,"hour");
                String strDay = getText(data,"day");
                String strTemp = getText(data,"temp");
                String strwdKor =getText(data,"wdKor");
                str += strHour+"::"+strDay+"::"+strTemp+"::"+strwdKor+"\n";
                textView.setText(str);
                // Log.d("::::",":::"+hourString);
            }
            textView.setText(str);
            //textView.setText(document.getTextContent());
        }

        public String getText(Element data,String tag){

            NodeList hourList = data.getElementsByTagName(tag);
            Element hour = (Element) hourList.item(0);
            NodeList textNode = hour.getChildNodes();
            String hourString = textNode.item(0).getNodeValue();
            return hourString;
        }
        @Override
        protected Document doInBackground(String... params) {
            Document doc = null;
            try {
                URL url = new URL(params[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = dbf.newDocumentBuilder();
                doc = builder.parse(url.openStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDomParser task = new MyDomParser();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");
    }
}
