package com.mute.winter.location.modules.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.mute.winter.location.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * @author dkoller
 * @since 16.02.2017
 *
 * A GoogleMaps Fragment that contains the Map and Sliding-Panel at the bottom
 */

public abstract class PanelMapsFragment extends MapFragment implements SlidingUpPanelLayout.PanelSlideListener {

    private SlidingUpPanelLayout slidingUpPanelLayout;
    private LinearLayout slidingUpPanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_panelmaps, container, false);

        slidingUpPanelLayout = view.findViewById(R.id.slidingLayout);
        slidingUpPanel = view.findViewById(R.id.slidingUpPanel);

        slidingUpPanelLayout.addPanelSlideListener(this);
        slidingUpPanelLayout.setOverlayed(true);

        return view;
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

    /**
     * Panel ================================================================
     */

    protected void removePanelViews(){
        slidingUpPanel.removeAllViews();
    }

    protected void addViewToPanel(View view){
        slidingUpPanel.addView(view);
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    public void setPanelState(SlidingUpPanelLayout.PanelState state){
        slidingUpPanelLayout.setPanelState(state);
    }

    public SlidingUpPanelLayout.PanelState getPanelState(){
        return slidingUpPanelLayout.getPanelState();
    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
        //Logger.v("PanelState changed: %s --> %s", previousState.name(), newState.name());
    }
}
