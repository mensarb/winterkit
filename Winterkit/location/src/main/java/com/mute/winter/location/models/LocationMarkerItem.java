package com.mute.winter.location.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * @author dkoller
 * @since 02.11.2016
 *
 * LocationMarkerItem represents a marker on the map.
 */
public class LocationMarkerItem implements ClusterItem {

    private final LatLng position;
    private final String title;
    private final int id;

    public LocationMarkerItem(LatLng position, String title, int id) {
        this.position = position;
        this.title = title;
        this.id = id;
    }

    /**
     * The position of this marker. This must always return the same value.
     */
    @Override
    public LatLng getPosition() {
        return position;
    }

    /**
     * The title of this marker.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The description of this marker.
     */
    @Override
    public String getSnippet() {
        return null;
    }

    public int getId() {
        return id;
    }
}
