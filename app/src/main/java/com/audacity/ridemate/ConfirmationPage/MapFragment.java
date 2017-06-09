package com.audacity.ridemate.ConfirmationPage;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.audacity.ridemate.LocationChangeListener;
import com.audacity.ridemate.Model.LocalModel.Ride;
import com.audacity.ridemate.NavigationBarAdapter;
import com.audacity.ridemate.R;
import com.audacity.ridemate.Utils.Utils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crash.FirebaseCrash;


public class MapFragment extends Fragment implements OnMapReadyCallback,MapFragmentContract.View,NavigationBarAdapter.NavigationItemSelectedListener, LocationChangeListener {

    private MapView mMapView;
    private GoogleMap googleMap;
    private ImageView gpsView;
    private com.audacity.ridemate.LocationManager locationManager;
    private TextView address, timeTextView, multiplierTextView, capacityTextView;
    private RecyclerView recyclerView;

    private MapFragmentContract.Presenter presenter;
    private NavigationBarAdapter adapter;
    private MarkerOptions markerOptions;
    private Marker marker;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        locationManager = new com.audacity.ridemate.LocationManager(getActivity(), this);
        initUI(savedInstanceState, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
        adapter.setSelectedPosition(2);
    }

    private void initUI(Bundle savedInstanceState, View view) {
        initMap(savedInstanceState, view);

        initGPSButton(view);

        initTextViews(view);

        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new NavigationBarAdapter(getActivity());
        adapter.setOnNavigationItemSelectedListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
    }

    private void initTextViews(View view) {
        address = (TextView) view.findViewById(R.id.address);
        timeTextView = (TextView) view.findViewById(R.id.time);
        multiplierTextView = (TextView) view.findViewById(R.id.multiplier);
        capacityTextView = (TextView) view.findViewById(R.id.people);
    }


    private void initGPSButton(View view) {
        gpsView = (ImageView) view.findViewById(R.id.gps);
        gpsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrentLocationInMap();
            }
        });
    }

    private void initMap(Bundle savedInstanceState, View view) {
        mMapView = (MapView) view.findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(new Exception(e.getMessage()));

        }
        mMapView.getMapAsync(this);
    }

    private void showCurrentLocationInMap() {
        Location location = this.locationManager.findCurrentLocation();
        //Log.d(getTag(), "Location: " + location.getProvider() + "==>>lat: " + location.getLatitude() + " lon: " + location.getLongitude());
        if (location != null) {
            showALocationInMap(location.getLatitude(), location.getLongitude());
            address.setText(this.locationManager.getAddress(location));
        }

        Utils.logFirebaseAnalytics("100",this.locationManager.getAddress(location), "Location");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showALocationInMap(double lat, double lon) {

        LatLng location = new LatLng(lat, lon);
        initMarker(location);
        marker.setPosition(location);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void initMarker(LatLng location) {
        if(markerOptions == null || marker == null) {
            markerOptions = new MarkerOptions().position(location).title("Marker Title").snippet("Marker Description");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car));
            marker = googleMap.addMarker(markerOptions);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        Utils.checkLocationPermission(getActivity());

        googleMap.setMyLocationEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        showCurrentLocationInMap();
    }

    @Override
    public void setPresenter(MapFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showRideInfo(Ride ride) {
        timeTextView.setText(ride.getTime());
        multiplierTextView.setText(ride.getMultiplier());
        capacityTextView.setText(ride.getCapacity());
    }

    @Override
    public void onItemSelected(int id) {
        presenter.getRideDataById(id);
    }

    @Override
    public void onLocationChanged(Location location) {
        showALocationInMap(location.getLatitude(), location.getLongitude());
    }
}
