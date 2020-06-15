package com.example.myapplication;

import com.example.myapplication.firebase.gpsSaver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static java.lang.StrictMath.abs;

public class start extends AppCompatActivity {
    private FirebaseDatabase mDatabase;

    String data_address;

    String[] permission_list = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    Double goal_latitude;
    Double goal_longitude;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String nowuser = mAuth.getCurrentUser().getUid();
    gpsSaver gps = new gpsSaver(nowuser);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.pocketpolice_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        data_address = getIntent().getStringExtra("input_address");

        Location input_loc=findGeoPoint(this,data_address);
        checkPermission();
        //Point pointFromGeoCoder = getPointFromGeoCoder(this, data_address);


        goal_latitude=input_loc.getLatitude();
        goal_longitude=input_loc.getLongitude();

        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = 0;
                double longitude = 0;

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                }
                Double currentLatitude = location.getLatitude();
                Double currentLongitude = location.getLongitude();

                String lati = String.valueOf(currentLatitude);
                String longi = String.valueOf(currentLongitude);
                gps.saveGPS(lati + "," + longi);
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

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locationListener);


        //도착 버튼 클릭시
        Button button1 = (Button) findViewById(R.id.btn_arriving);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
                Location nowlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                double now_longi=abs(nowlocation.getLongitude());
                double now_lati=nowlocation.getLatitude();

                Log.d("test nowloc lati", String.valueOf(now_lati));
                Log.d("test nowloc longi", String.valueOf(now_longi));

                Log.d("test goal_latitude", String.valueOf(goal_latitude));
                Log.d("test goal_longitude", String.valueOf(goal_longitude));

                if(now_lati>=goal_latitude-0.002&&now_lati<=goal_latitude+0.002&&now_longi>=goal_longitude-0.002&&now_longi<=goal_longitude+0.002){
                    Intent intent1 = new Intent(getApplicationContext(), arriving_complete.class);
                    startActivity(intent1);
                    locationManager.removeUpdates(locationListener);
//
                }
                else{
                    Toast.makeText(getApplicationContext(),"현 위치와 목적지로 설정한 위치가 다릅니다.",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

//    private Point getPointFromGeoCoder(Context context,String data_address) {
//        Point point = new Point();
//        point.addr = data_address;
//
//        Geocoder geocoder = new Geocoder(context);
//        List<Address> listAddress;
//        try {
//            listAddress = geocoder.getFromLocationName(data_address, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//            point.havePoint = false;
//            return point;
//        }
//
//        if (listAddress.isEmpty()) {
//            point.havePoint = false;
//            return point;
//        }
//
//        point.havePoint = true;
//        point.x = listAddress.get(0).getLongitude();
//        point.y = listAddress.get(0).getLatitude();
//        return point;
//    }


    private void checkPermission() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M)
            return;

        for(String permission: permission_list){
            int chk=checkCallingOrSelfPermission(permission);
            if(chk== PackageManager.PERMISSION_DENIED){
                requestPermissions(permission_list,0);
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0)
        {
            for(int i=0; i<grantResults.length; i++)
            {
                //허용됬다면
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                }
                else {
                    Toast.makeText(getApplicationContext(),"앱권한설정하세요",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext,Locale.KOREA);

        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(address, 5);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;
    }

    class Point {
        // 위도
        public double x;
        // 경도
        public double y;
        public String addr;
        // 포인트를 받았는지 여부
        public boolean havePoint;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("x : ");
            builder.append(x);
            builder.append(" y : ");
            builder.append(y);
            builder.append(" addr : ");
            builder.append(addr);

            return builder.toString();
        }
    }


}