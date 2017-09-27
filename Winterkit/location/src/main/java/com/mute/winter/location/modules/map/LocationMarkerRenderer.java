package com.mute.winter.location.modules.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * @author dkoller
 * @since 19.12.2016
 */

public class LocationMarkerRenderer extends DefaultClusterRenderer<LocationMarkerItem> {

    private boolean shouldRenderAsCluster = true;

    public LocationMarkerRenderer(Context context, GoogleMap map, ClusterManager<LocationMarkerItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(LocationMarkerItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<LocationMarkerItem> cluster, MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<LocationMarkerItem> cluster) {
        return super.shouldRenderAsCluster(cluster) && shouldRenderAsCluster;
    }

    public void setShouldRenderAsCluster(boolean shouldRenderAsCluster) {
        this.shouldRenderAsCluster = shouldRenderAsCluster;
    }
}
