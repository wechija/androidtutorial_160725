package com.example.a.p01_xml_listview_1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<String>();
    public ArrayAdapter<String> adapter = null;
    class MyDomParser extends AsyncTask<String,Void,Document>{


        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            try{
                String str="";
                NodeList nodeList = document.getElementsByTagName("data");
                for(int i=0;i<nodeList.getLength();i++){
                    Element data = (Element) nodeList.item(i);
                    String strHour = getText(data,"hour");
                    String strDay = getText(data,"day");
                    String strTemp = getText(data,"temp");
                    String strwdKor =getText(data,"wdKor");
                    str = strHour+"::"+strDay+"::"+strTemp+"::"+strwdKor+"\n";
                    list.add(str);

                }
            }catch(Exception ex){
                ex.printStackTrace();
                list.add("test");
            }

            adapter.notifyDataSetChanged();


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

    class MyData{
        int imgId;
        String strTitle;

        public MyData(int imgId,String strTitle){
            this.imgId = imgId;
            this.strTitle = strTitle;
        }
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                LayoutInflater inf = getLayoutInflater();
                view = inf.inflate(R.layout.item_view,null);
            }
            String data = list.get(i);
            TextView itemTitle = (TextView)view.findViewById(R.id.weather);

            itemTitle.setText(data);
             return view;
        }
    }


    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                list
        );
        listView.setAdapter(adapter);
        MyDomParser task = new MyDomParser();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");

        ImageView iv = (ImageView) findViewById(R.id.testImgView);
        iv.setImageResource(R.drawable.rain);
    }
}
