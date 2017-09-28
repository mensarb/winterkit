package com.mute.winter.location.modules.map;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.mute.winter.location.models.LocationMarkerItem;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * @author dkoller
 * @since 16.02.2017
 *
 * GoogleMaps Fragment that contains the whole maps-functionality
 */
public abstract class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowCloseListener, GoogleMap.OnMapClickListener,
        ClusterManager.OnClusterItemInfoWindowClickListener<LocationMarkerItem>, ClusterManager.OnClusterItemClickListener<LocationMarkerItem>,
        ClusterManager.OnClusterClickListener<LocationMarkerItem>, ClusterManager.OnClusterInfoWindowClickListener<LocationMarkerItem>, GoogleMap.OnCameraMoveListener {

    @Nullable private GoogleMap googleMap;
    @Nullable private ClusterManager<LocationMarkerItem> clusterManager;
    @Nullable private LocationMarkerRenderer clusterRenderer;

    @Nullable protected LocationMarkerItem currentMarker;
    @Nullable protected Marker selectedMarker;
    @Nullable protected Marker tempMarker;

    /**
     * Init Map
     */

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (checkStartConditions()){
            init();
        }
    }

    @Override
    public void onDestroyView() {
        if (clusterManager != null) {
            clusterManager.clearItems();
        }

        if (googleMap != null) {
            googleMap.clear();
        }

        super.onDestroyView();
    }

    protected boolean checkStartConditions(){
        return true;
    }

    protected void init(){
        if(getMapFragment() != null && getLocationManager() != null) {
            getMapFragment().getMapAsync(this);
        }
    }

    protected SupportMapFragment getMapFragment(){
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        try {
            setMapSettings();
            initClusterManager();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initClusterManager(){
        clusterManager = new ClusterManager<>(getActivity(), getGoogleMap());
        clusterRenderer = new LocationMarkerRenderer(getActivity(), getGoogleMap(), clusterManager);
        clusterManager.setRenderer(clusterRenderer);

        clusterManager.setOnClusterItemClickListener(this);
        clusterManager.setOnClusterItemInfoWindowClickListener(this);
        clusterManager.setOnClusterClickListener(this);
        clusterManager.setOnClusterInfoWindowClickListener(this);

        getGoogleMap().setOnCameraIdleListener(clusterManager);
        getGoogleMap().setOnMarkerClickListener(clusterManager);
        getGoogleMap().setOnInfoWindowClickListener(clusterManager);
        getGoogleMap().setOnInfoWindowCloseListener(this);
        getGoogleMap().setOnMapClickListener(this);
        getGoogleMap().setOnCameraMoveListener(this);
    }

    @Nullable
    protected GoogleMap getGoogleMap() {
        if (googleMap == null){
            Logger.e("Map is not ready");
        }
        return googleMap;
    }

    @Nullable
    protected ClusterManager<LocationMarkerItem> getClusterManager() {
        if (clusterManager == null){
            Logger.e("ClusterManager is null");
        }
        return clusterManager;
    }

    @Nullable
    protected LocationMarkerRenderer getClusterRenderer() {
        if (clusterRenderer == null){
            Logger.e("ClusterRenderer is null");
        }
        return clusterRenderer;
    }

    protected void clearClusterItems(){
        if (getClusterManager() != null){
            getClusterManager().clearItems();
        }
    }

    protected void clusterItems(){
        if (getClusterManager() != null){
            getClusterManager().cluster();
        }
    }

    public LocationManager getLocationManager() {
        return (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    public boolean isMapReady(){
        return googleMap != null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    /**
     * Marker
     */
    abstract protected void addMarkersToMap(boolean focus);
    abstract protected void addMarkersToMap(boolean focus, boolean animated);

    @Nullable
    protected LocationMarkerItem getLocationMarker(int position){
        ClusterManager<LocationMarkerItem> clusterManager = getClusterManager();
        if (clusterManager != null){
            List<LocationMarkerItem> items = (List<LocationMarkerItem>) clusterManager.getAlgorithm().getItems();
            if (position >= 0 && position < items.size()) {
                return items.get(position);
            }
        }
        return null;
    }

    protected void focusMarker(LocationMarkerItem marker,  boolean animated, float zoomLevel){
        if (marker != null){
            LatLng position = marker.getPosition();
            createTemporaryMarker(position, marker.getTitle());
            moveCameraTo(position, animated, zoomLevel);
        }
    }

    protected void createTemporaryMarker(LatLng position, String title){
        removeTemporaryMarker();

        MarkerOptions markerOptions = new MarkerOptions()
                .position(position);
        tempMarker = getGoogleMap().addMarker(markerOptions);

        if (title != null) {
            tempMarker.setTitle(title);
            tempMarker.showInfoWindow();
        }
    }

    private void removeTemporaryMarker(){
        if (tempMarker != null) {
            tempMarker.remove();
        }
    }

    @Override
    public void onInfoWindowClose(Marker marker) {

    }

    protected Marker getMarker(LocationMarkerItem locationMarkerItem){
        return getClusterRenderer().getMarker(locationMarkerItem);
    }

    /**
     * Zoom
     */
    protected static float ZOOM_WORLD = 1;
    protected static float ZOOM_CONTINENT = 5;
    protected static float ZOOM_CITY = 10;
    protected static float ZOOM_STREETS = 15;
    protected static float ZOOM_BUILDINGS = 20;

    protected void moveCameraTo(LatLng markerPosition, boolean animated, float zoomLevel){
        if (markerPosition != null && isMapReady()) {
            CameraPosition position = new CameraPosition.Builder()
                    .target(markerPosition)
                    .zoom(zoomLevel)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(position);

            if (animated) {
                getGoogleMap().animateCamera(cameraUpdate);
            } else {
                getGoogleMap().moveCamera(cameraUpdate);
            }
        }
    }

    public void moveCameraToMyPosition(boolean animated){
        moveCameraToMyPosition(animated, ZOOM_STREETS);
    }

    public void moveCameraToMyPosition(boolean animated, float zoomLevel){
        Location currentLocation = getMyCurrentLocation();
        if (currentLocation != null) {
            moveCameraTo(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), animated, zoomLevel);
        }
    }

    @Nullable
    protected Location getMyCurrentLocation() {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                return getMyCurrentLocationAndroid5();
            } else {
                return getMyCurrentLocationAndroid4();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    @TargetApi(23)
    private Location getMyCurrentLocationAndroid5() throws SecurityException{
        Location bestLocation = null;
        List<String> providers = getLocationManager().getProviders(true);
        for (String provider : providers) {
            Location location = getLocationManager().getLastKnownLocation(provider);
            if (location == null) {
                continue;
            }
            if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = location;
            }
        }
        return bestLocation;
    }

    @Nullable
    private Location getMyCurrentLocationAndroid4(){
        return getGoogleMap().getMyLocation();
    }

    /**
     * Map Settings
     */
    protected boolean isMyLocationButtonEnabled = false;
    protected boolean isMapToolbarEnabled = false;
    protected boolean isZoomControlsEnabled = false;
    protected boolean isScrollingGesturesEnabled = true;
    protected boolean isZoomGesturesEnabled = true;

    protected void setMapSettings(){
        UiSettings settings = getGoogleMap().getUiSettings();

        settings.setMyLocationButtonEnabled(isMyLocationButtonEnabled());
        settings.setMapToolbarEnabled(isMapToolbarEnabled());
        settings.setZoomControlsEnabled(isZoomControlsEnabled());
        settings.setScrollGesturesEnabled(isScrollingGesturesEnabled());
        settings.setZoomGesturesEnabled(isZoomGesturesEnabled());

        try{
            getGoogleMap().setMyLocationEnabled(true);
        }catch (SecurityException s){
            s.printStackTrace();
        }
    }

    public boolean isMyLocationButtonEnabled() {
        return isMyLocationButtonEnabled;
    }

    public void setMyLocationButtonEnabled(boolean myLocationButtonEnabled) {
        isMyLocationButtonEnabled = myLocationButtonEnabled;
    }

    public boolean isMapToolbarEnabled() {
        return isMapToolbarEnabled;
    }

    public void setMapToolbarEnabled(boolean mapToolbarEnabled) {
        isMapToolbarEnabled = mapToolbarEnabled;
    }

    public boolean isZoomControlsEnabled() {
        return isZoomControlsEnabled;
    }

    public void setZoomControlsEnabled(boolean zoomControlsEnabled) {
        isZoomControlsEnabled = zoomControlsEnabled;
    }

    public boolean isScrollingGesturesEnabled() {
        return isScrollingGesturesEnabled;
    }

    public void setScrollingGesturesEnabled(boolean scrollingGesturesEnabled) {
        isScrollingGesturesEnabled = scrollingGesturesEnabled;
    }

    public boolean isZoomGesturesEnabled() {
        return isZoomGesturesEnabled;
    }

    public void setZoomGesturesEnabled(boolean zoomGesturesEnabled) {
        isZoomGesturesEnabled = zoomGesturesEnabled;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onClusterItemInfoWindowClick(LocationMarkerItem locationMarkerItem) {

    }

    @Override
    public boolean onClusterItemClick(LocationMarkerItem locationMarkerItem) {
        return false;
    }

    @Override
    public boolean onClusterClick(Cluster<LocationMarkerItem> cluster) {

        return false;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<LocationMarkerItem> cluster) {

    }

    @Override
    public void onCameraMove() {
        CameraPosition cameraPosition = getMapCameraPosition();
        boolean shouldRenderAsCluster = cameraPosition.zoom < ZOOM_STREETS;
        getClusterRenderer().setShouldRenderAsCluster(shouldRenderAsCluster);
    }

    protected CameraPosition getMapCameraPosition(){
        return getGoogleMap().getCameraPosition();
    }

    protected Projection getProjection(){
        return getGoogleMap().getProjection();
    }

    /**
     * @return the visible Region in [m]
     */
    protected float getVisibleRegionDistance(){
        VisibleRegion visibleRegion = getProjection().getVisibleRegion();

        LatLng farRight = visibleRegion.farRight;
        LatLng farLeft = visibleRegion.farLeft;
        LatLng nearRight = visibleRegion.nearRight;
        LatLng nearLeft = visibleRegion.nearLeft;

        float[] distanceWidth = new float[2];
        Location.distanceBetween(
                (farRight.latitude + nearRight.latitude)/2,
                (farRight.longitude + nearRight.longitude)/2,
                (farLeft.latitude + nearLeft.latitude)/2,
                (farLeft.longitude + nearLeft.longitude)/2,
                distanceWidth
        );

        float[] distanceHeight = new float[2];
        Location.distanceBetween(
                (farRight.latitude + nearRight.latitude)/2,
                (farRight.longitude + nearRight.longitude)/2,
                (farLeft.latitude + nearLeft.latitude)/2,
                (farLeft.longitude + nearLeft.longitude)/2,
                distanceHeight
        );

        float distance;

        if (distanceWidth[0]>distanceHeight[0]){
            distance = distanceWidth[0];
        } else {
            distance = distanceHeight[0];
        }

        return distance;
    }
}
