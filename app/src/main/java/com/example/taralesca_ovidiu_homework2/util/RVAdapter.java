package com.example.taralesca_ovidiu_homework2.util;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taralesca_ovidiu_homework2.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TextViewHolder> {
    private String[] dataSet;

    public RVAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    }

    public void swapDataSet(String[] newData) {
        this.dataSet = newData;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        return new TextViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.textView.setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        TextViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
