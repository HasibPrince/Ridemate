package com.audacity.ridemate.ConfirmationPage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.audacity.ridemate.NavigationBarAdapter;
import com.audacity.ridemate.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback,MapFragmentContract.View {

    private MapView mMapView;
    private GoogleMap googleMap;
    private ImageView gpsView;
    private com.audacity.ridemate.LocationManager locationManager;
    private TextView address;
    private RecyclerView recyclerView;

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
        locationManager = new com.audacity.ridemate.LocationManager(getContext());
        initUI(savedInstanceState, view);
        return view;
    }

    private void initUI(Bundle savedInstanceState, View view) {
        initMap(savedInstanceState, view);

        initGPSButton(view);

        address = (TextView) view.findViewById(R.id.address);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        NavigationBarAdapter adapter = new NavigationBarAdapter(getActivity());
        //adapter.setOnNavigationItemSelectedListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        }
        mMapView.getMapAsync(this);
    }

    private void showCurrentLocationInMap() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            askForPermission();
        }

        Location location = this.locationManager.findCurrentLocation();
        //Log.d(getTag(), "Location: " + location.getProvider() + "==>>lat: " + location.getLatitude() + " lon: " + location.getLongitude());
        if (location != null) {
            showALocationInMap(location.getLatitude(), location.getLongitude());
            address.setText(this.locationManager.getAddress(location));

        }
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions( getActivity(), new String[] {  Manifest.permission.ACCESS_COARSE_LOCATION  },
                101 );
        showCurrentLocationInMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showALocationInMap(double lat, double lon) {

        LatLng sydney = new LatLng(lat, lon);
        MarkerOptions marker = new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.car));
        googleMap.addMarker(marker);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           askForPermission();
        }

        googleMap.setMyLocationEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        showALocationInMap(-34, 151);
    }

    @Override
    public void setPresenter(MapFragmentContract.Presenter presenter) {

    }

    @Override
    public void showALocationInMap(double lat, double lon, String address) {

    }
}
