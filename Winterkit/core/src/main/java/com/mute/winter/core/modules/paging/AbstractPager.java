package com.mute.winter.core.modules.paging;


import android.support.v7.widget.LinearLayoutManager;

import com.mute.winter.core.interfaces.PagingListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @author dkoller
 * @since 13.10.2016
 */
public abstract class AbstractPager {

    protected int pageNumber;
    protected int firstPageNumber = 0;
    protected boolean isFirstPage = true;
    protected boolean isLoading = false;
    protected boolean isLastPage = false;
    protected int resultCount;

    private List<PagingListener> pagingListeners = new ArrayList<>();

    protected boolean checkConditions(){
        return true;
    }

    public void loadFirstPage(){
        if (checkConditions()){
            if (isFirstPage){
                if (!isLoading){
                    isLoading = true;
                    isFirstPage = true;
                    isLastPage = false;
                    pageNumber = getFirstPageNumber();

                    clearData();
                    onRefresh();

                    performCall(getPageSize(), pageNumber);
                }
            }else {
                loadNextPage();
            }
        }else {
            onResult();
        }
    }

    public void loadNextPage(){
        if (checkConditions()){
            if (triggerNextPage()) {
                isLoading = true;
                isFirstPage = false;
                pageNumber++;

                performCall(getPageSize(), pageNumber);
            }else {
                onResult();
            }
        }else {
            onResult();
        }
    }

    public void refresh(){
        isFirstPage = true;
        loadFirstPage();
    }

    private boolean triggerNextPage(){
        return !isLoading && !isLastPage;
    }

    public boolean triggerNextPage(LinearLayoutManager linearLayoutManager){
        return triggerNextPage(linearLayoutManager, getItemCount());
    }

    public boolean triggerNextPage(LinearLayoutManager linearLayoutManager, int variablePageSize){
        if (linearLayoutManager == null){
            return false;
        }

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        return triggerNextPage()
                && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= variablePageSize;
    }

    protected abstract int getPageSize();

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    abstract public void clearData();

    public boolean isEmpty(){
        return getItemCount() == 0;
    }

    abstract public int getItemCount();

    public int getResultCount() {
        return resultCount;
    }

    protected void performCall(int pageSize, int pageNumber){

    }

    protected void onResult(){
        for (int i = 0; i< pagingListeners.size(); i++){
            pagingListeners.get(i).onResult();
        }
    }

    public int getFirstPageNumber() {
        return firstPageNumber;
    }

    protected void onRefresh(){
        for (int i = 0; i< pagingListeners.size(); i++){
            pagingListeners.get(i).onRefresh();
        }
    }

    protected void onLoading(){
        for (int i = 0; i< pagingListeners.size(); i++){
            pagingListeners.get(i).onLoading();
        }
    }

    public void setPagingListener(PagingListener pagingListener) {
        pagingListeners.add(pagingListener);
    }

    public void removePagingListener(PagingListener pagingListener){
        pagingListeners.remove(pagingListener);
    }
}
