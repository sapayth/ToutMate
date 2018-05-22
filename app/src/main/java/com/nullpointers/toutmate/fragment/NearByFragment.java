package com.nullpointers.toutmate.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nullpointers.toutmate.MainActivity;
import com.nullpointers.toutmate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private GoogleMapOptions options;
    private SupportMapFragment mapFragment;
    private Context context;

    private Spinner locationCategorySp;
    private Spinner locationDistanceSp;

    private String[] locationCategories = {
            "All",
            "Restaurant",
            "Bank",
            "ATM",
            "Hospital",
            "Shopping Mall",
            "Mosque",
            "Bus Station",
            "Police Station"
    };

    private String[] locationDistances = {
            "0.5km",
            "1km",
            "1.5km",
            "2km",
            "2.5km",
            "3km",
            "4km",
            "5km",
            "6km",
            "8km",
            "10km"
    };


    public NearByFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_near_by, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        options = new GoogleMapOptions();
        options.zoomControlsEnabled(true);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance(options);

        FragmentTransaction ft = getFragmentManager().beginTransaction()
                .replace(R.id.mapContainer, mapFragment);
        ft.commit();

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationCategorySp = view.findViewById(R.id.locationCategorySpinner);
        locationDistanceSp = view.findViewById(R.id.locationDistanceSpinner);

        ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item,
                        locationCategories);
        ArrayAdapter<String> distanceArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item,
                locationDistances);

        locationCategorySp.setAdapter(categoryArrayAdapter);
        locationDistanceSp.setAdapter(distanceArrayAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    501);
        } else {
            map.setMyLocationEnabled(true);
            LatLng latLng = new LatLng(MainActivity.latitude, MainActivity.longitude);
            map.addMarker(new MarkerOptions().position(latLng));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                MainActivity.locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 0, 0, MainActivity.locationListener);
            }
        }
    }
}
