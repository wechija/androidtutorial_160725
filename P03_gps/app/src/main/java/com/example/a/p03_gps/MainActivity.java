package com.example.a.p03_gps;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textCnt;
    TestDbHandler dbHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textCnt = (TextView)findViewById(R.id.textCnt);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.delete();
                textView.setText("");
                textCnt.setText("0");
            }
        });
        dbHandler = new TestDbHandler(this);

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final Geocoder geocoder = new Geocoder(this);

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String str = "lat : " + location.getLatitude() + "lon :" + location.getLongitude() + "\n";
                //textView.append(str);
                Date vDate = new Date();
                SimpleDateFormat pOutformatter =  new SimpleDateFormat ("yyyy-MM dd HH:mm:ss", java.util.Locale.KOREA);
                dbHandler.insert(location.getLatitude()+"", location.getLongitude()+"",  pOutformatter.format(vDate));


                List<Map<String,String>> result =  dbHandler.selectAll();
                textView.setText("");
                for(Map<String,String> tmpMap:result){
                    textView.append("lat:"+tmpMap.get("lat")+",lgt:"+tmpMap.get("lgt")+",시간:"+tmpMap.get("inserttime")+"\n");
                }
                textCnt.setText("총 건수 :"+result.size());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }
}
