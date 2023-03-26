package com.example.tripinfo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripinfo.R;
import com.example.tripinfo.activity.NoticeDataActivity;
import com.example.tripinfo.dto.WarningInfo;

import java.util.List;

public class SafetyNoticeAdapter extends RecyclerView.Adapter<SafetyNoticeAdapter.MyViewHolder> {
    List<WarningInfo.Warning> warningList;
    Context context;

    public SafetyNoticeAdapter(List<WarningInfo.Warning> warningList) {
        this.warningList = warningList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv, dayTv, levelTv;
        LinearLayout layout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            titleTv = view.findViewById(R.id.listviewNotice_title);
            dayTv = view.findViewById(R.id.listviewNotice_day);
            levelTv = view.findViewById(R.id.listviewNotice_level);
            layout = view.findViewById(R.id.listviewNotice_layout);
        }
    }

    @NonNull
    @Override
    public SafetyNoticeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_notice, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SafetyNoticeAdapter.MyViewHolder holder, int position) {
        WarningInfo.Warning data = warningList.get(position);
        holder.titleTv.setText(data.getTitle());
        holder.dayTv.setText(data.getWrt_dt());
        if(data.getCtgy_nm() != null) {
            holder.levelTv.setText(data.getCtgy_nm());
            holder.levelTv.setVisibility(View.VISIBLE);
            if(data.getCtgy_nm().equals("안내")) {
                holder.levelTv.setTextColor(ContextCompat.getColor(context, R.color.light_blue01));
            } else if(data.getCtgy_nm().equals("주의")) {
                holder.levelTv.setTextColor(ContextCompat.getColor(context, R.color.light_red01));
            }
        }
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoticeDataActivity.class);
            intent.putExtra("title", data.getTitle());
            intent.putExtra("day", data.getWrt_dt());
            intent.putExtra("context", data.getTxt_origin_cn());
            intent.putExtra("level", data.getCtgy_nm());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return warningList.size();
    }
}
