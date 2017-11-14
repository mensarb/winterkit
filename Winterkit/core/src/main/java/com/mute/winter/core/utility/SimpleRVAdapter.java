package com.mute.winter.core.utility;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dkoller
 * @since 25.10.2017
 *
 * Simple String Adapter for Recyclerviews
 */

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.ViewHolder>{

    private List<String> content = new ArrayList<>();

    public SimpleRVAdapter(List<String> content) {
        this.content = content;
    }

    public SimpleRVAdapter() {

    }

    public void add(String string){
        content.add(string);
        notifyDataSetChanged();
    }

    public void addAll(List<String> strings){
        content.addAll(strings);
        notifyDataSetChanged();
    }

    public void clear(){
        content.clear();
        notifyDataSetChanged();
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new ViewHolder(new TextView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView;
        }
    }
}
