package com.example.tripinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripinfo.R;
import com.example.tripinfo.activity.SelectActivity;
import com.example.tripinfo.dto.CountryInfo;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    List<CountryInfo> countryList;
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor sp_e;

    public MainAdapter(List<CountryInfo> countryList) {
        this.countryList = countryList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView countryTv, countryEnTv;
        LinearLayout layout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            countryTv = view.findViewById(R.id.listviewMain_countryTv);
            countryEnTv = view.findViewById(R.id.listviewMain_countryEnTv);
            layout = view.findViewById(R.id.listviewMain_layout);
        }
    }

    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_main, parent, false);

        sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp_e = sp.edit();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {
        CountryInfo countryInfo = countryList.get(position);
        holder.countryTv.setText(countryInfo.getCountryName());
        holder.countryEnTv.setText(countryInfo.getCountryEnName());

        holder.layout.setOnClickListener(v -> {
            sp_e.putString("id", countryInfo.getId());
            String countryName;
            if(countryInfo.getCountryName().equals("튀르키예")) {
                countryName = "튀르키예공화국";
            } else {
                countryName = countryInfo.getCountryName();
            }
            sp_e.putString("countryName", countryName);
            sp_e.putString("countryEnName", countryInfo.getCountryEnName());
            sp_e.putString("continent", countryInfo.getContinent());
            sp_e.putString("imgUrl", countryInfo.getImgUrl());
            sp_e.putString("basic", countryInfo.getBasic());
            sp_e.putString("id", countryInfo.getId());
            sp_e.commit();

            Intent intent = new Intent(context, SelectActivity.class);
            intent.putExtra("countryName", countryInfo.getCountryName());
            intent.putExtra("id", countryInfo.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}
