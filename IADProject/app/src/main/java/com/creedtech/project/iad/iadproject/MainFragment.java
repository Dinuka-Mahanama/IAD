package com.creedtech.project.iad.iadproject;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;

    public MainFragment() {
        // Required empty public constructor
    }

    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button claimhist = (Button) rootView.findViewById(R.id.claimhistbt);
        claimhist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickedClaim(v);
            }
        });

        Button vehicledet = (Button) rootView.findViewById(R.id.vehicledetbt);
        vehicledet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickedVehicle(v);
            }
        });

        Button policydet = (Button) rootView.findViewById(R.id.policydetbt);
        policydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickedPolicy(v);
            }
        });

        Button userdet = (Button) rootView.findViewById(R.id.userdetbt);
        userdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickedUser(v);
            }
        });

        return rootView;


    }

   /* public void onButtonClicked(View view) {


        //do your stuff here..
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.Fragment_container, new ClaimHistory(), "NewFragmentTag");
        ft.commit();

        ft.addToBackStack(null);
    }*/

    public void onButtonClickedClaim(View view) {
        //do your stuff here..

       /* final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.Fragment_container, new ClaimHistory(), "NewFragmentTag");
        ft.commit();

        ft.addToBackStack(null);*/
        Intent intent = new Intent(getActivity(), ClaimHistory.class);
        startActivity(intent);
    }

    public void onButtonClickedVehicle(View view) {
        //do your stuff here..
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.Fragment_container, new About(), "NewFragmentTag");
        ft.commit();

        ft.addToBackStack(null);
    }

    public void onButtonClickedPolicy(View view) {
      /*  //do your stuff here..
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.Fragment_container, new Payments(), "NewFragmentTag");
        ft.commit();

        ft.addToBackStack(null);*/
        Intent intent = new Intent(getActivity(), Payments.class);
        startActivity(intent);

    }

    public void onButtonClickedUser(View view) {
        //do your stuff here..
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.Fragment_container, new Profile(), "NewFragmentTag");
        ft.commit();

        ft.addToBackStack(null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        LatLng pp = new LatLng(6.821674, 79.891393);
        MarkerOptions option = new MarkerOptions();
        option.position(pp).title("Ratmalana");
        map.addMarker(option);
        float zoomLevel = 16.0f;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, zoomLevel));

    }

    public void claimfragclick(View view){
        Intent intent = new Intent(getActivity(), ClaimHistory.class);
        startActivity(intent);
    }

}

