package com.mute.winter.location.modules.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.mute.winter.location.R;


/**
 * @author dkoller
 * @since 16.02.2017
 *
 * A simple GoogleMaps Fragment that contains only the Map
 */

public abstract class SimpleMapsFragment extends MapFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_simplemaps, container, false);
    }

    @Override
    protected SupportMapFragment getMapFragment(){
        return (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        addMarkersToMap(true);
    }
}
