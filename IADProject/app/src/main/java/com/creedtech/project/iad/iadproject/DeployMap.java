package com.creedtech.project.iad.iadproject;


import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.creedtech.project.iad.iadproject.R;

import java.util.Map;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DeployMap extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

        private GoogleMap mMap;

        private GoogleApiClient googleApiClient;

        private static final int LOCATION_REQUEST_CODE = 101;
        LatLng loc1,loc2;
        TextView diss;
        LinearLayout rt;
        TextView time;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_deploy_map);

                diss= (TextView)findViewById(R.id.mapdistance);
                rt = (LinearLayout)findViewById(R.id.traveldetails);
                rt.setVisibility(View.INVISIBLE);
                time= (TextView)findViewById(R.id.maptime);

                googleApiClient = new GoogleApiClient.Builder(this)
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .build();

        }

        public void onStart() {
                super.onStart();
                googleApiClient.connect();
        }

        public void onStop() {
                super.onStop();
                googleApiClient.disconnect();

        }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng KDU = new LatLng(6.840468, 79.967319);
                loc1=KDU;
                mMap.addMarker(new MarkerOptions().position(KDU).title("Drone HQ"));
                float zoomLevel = 20.0f;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KDU, zoomLevel));

                //LatLng loc = new google.maps.LatLng(marker.position.lat(), marker.position.lng());
                //bounds.extend(KDU);
                //mMap = googleMap;

                //checkLocationandAddToMap();
              /*  checkLocationandAddToMap();
                rt.setVisibility(View.VISIBLE);
                double d=distance(loc1.latitude,loc1.longitude,loc1.latitude,loc2.longitude);

                diss.setText(String.valueOf(d)+"KM");
                double ti= (d/40)*60;
                time.setText(ti+"Minutes");*/
        }

        @Override
        public void onConnected(Bundle bundle) {
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

               // if(mapFragment!=null)
                        mapFragment.getMapAsync(this);
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {

        }

        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                switch (requestCode) {
                        case LOCATION_REQUEST_CODE:
                                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
//Permission Granted
                                } else
                                        Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
                                break;

                }

        }

        private void checkLocationandAddToMap() {
//Checking if the user has granted the permission
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
//Requesting the Location permission
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
                        return;
                }

//Fetching the last known location using the Fus
                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

//MarkerOptions are used to create a new Marker.You can specify location, title etc with MarkerOptions
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("You are Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).flat(true );
                loc2=new LatLng(location.getLatitude(), location.getLongitude());
//Adding the created the marker on the map
                mMap.addMarker(markerOptions);
               // LatLngBounds xx=new LatLngBounds(loc1,loc2);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom((float) 11.6)
                        .bearing(0)
                        .tilt(45)
                        .build();
               // mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(xx,0));
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),4000,null);
                Log.d("myTag", String.valueOf(location.getLatitude())+" ][ "+String.valueOf(location.getLongitude()));

                //Draw Line Btw pointers
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(loc1, loc2)
                        .width(10)
                        .color(Color.RED));

              /*  LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(loc1);
                builder.include(loc1);
                LatLngBounds bounds = builder.build();
                int padding = 0; // offset from edges of the map in pixels
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.animateCamera(cu);*/

        }

        public void UpdateLocation(View view){
                checkLocationandAddToMap();
                rt.setVisibility(View.VISIBLE);
                double d=distance(loc1.latitude,loc1.longitude,loc1.latitude,loc2.longitude);

                diss.setText(String.valueOf(d)+"KM");
                double ti= (d/40)*60;
                time.setText(ti+"Minutes");


                //mMap.fitBounds(bounds);
                //mMap.panToBounds(bounds);
                /*LatLng KDU = new LatLng(6.817964, 79.890928);
                mMap.addMarker(new MarkerOptions().position(KDU).title("Marker in KDC"));
                float zoomLevel = 16.0f;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KDU, zoomLevel));*/
        }

        //Calculate distance between two points
        private double distance(double lat1, double lon1, double lat2, double lon2) {
                double theta = lon1 - lon2;
                double dist = Math.sin(deg2rad(lat1))
                        * Math.sin(deg2rad(lat2))
                        + Math.cos(deg2rad(lat1))
                        * Math.cos(deg2rad(lat2))
                        * Math.cos(deg2rad(theta));
                dist = Math.acos(dist);
                dist = rad2deg(dist);
                dist = dist * 60 * 1.1515;
                return (Math.round(dist));
        }

        private double deg2rad(double deg) {
                return (deg * Math.PI / 180.0);
        }

        private double rad2deg(double rad) {
                return (rad * 180.0 / Math.PI);
        }

        @Override
        public void onBackPressed() {
                Thread myThread = new Thread(){
                        @Override
                        public void run() {

                                Intent intent = new Intent(getApplicationContext(),Mainactivity.class);
                                startActivity(intent);
                                finish();
                        }
                };
                myThread.start();
        }
}