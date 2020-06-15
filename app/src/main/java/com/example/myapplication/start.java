package com.example.myapplication;

import com.example.myapplication.firebase.gpsSaver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.security.cert.TrustAnchor;


public class start extends AppCompatActivity {
    String[] permission_list = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

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


        checkPermission();
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
                double goal_latitude=37.421998333333335;
                double goal_longitude=-122.08400000000002;

                checkPermission();
                Location nowlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                Log.d("nowlocation", String.valueOf(nowlocation.getLatitude()));
                Log.d("nowlocation", String.valueOf(nowlocation.getLongitude()));


                double now_longi=nowlocation.getLongitude();
                double now_lati=nowlocation.getLatitude();

                if(now_lati>=goal_latitude-0.001&&now_lati<=goal_latitude+0.001&&now_longi>=goal_longitude-0.001&&now_longi<=goal_longitude+0.001){
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


}

