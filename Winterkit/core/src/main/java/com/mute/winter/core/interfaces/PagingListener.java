package com.mute.winter.core.interfaces;

/**
 * @author dkoller
 * @since 23.11.2016
 */

public interface PagingListener {
    void onLoading();
    void onRefresh();
    void onResult();
}
