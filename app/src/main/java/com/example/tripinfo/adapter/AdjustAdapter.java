package com.example.tripinfo.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripinfo.R;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.etc.DownloadBC;

import java.util.List;

public class AdjustAdapter extends RecyclerView.Adapter<AdjustAdapter.MyViewHolder> {
    List<WarningInfo.Warning> warningList;
    Context context;
    DownloadBC downloadBC;

    public AdjustAdapter(List<WarningInfo.Warning> warningList, DownloadBC downloadBC) {
        this.warningList = warningList;
        this.downloadBC = downloadBC;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv, contextTv, dayTv;
        AppCompatButton downloadBtn;
        View viewItem;

        public MyViewHolder(@NonNull View view) {
            super(view);
            titleTv = view.findViewById(R.id.listviewAd_titleTv);
            contextTv = view.findViewById(R.id.listviewAd_contextTv);
            dayTv = view.findViewById(R.id.listviewAd_dayTv);
            downloadBtn = view.findViewById(R.id.listviewAd_downloadBtn);
            viewItem = view.findViewById(R.id.listviewAd_view);
        }
    }

    @NonNull
    @Override
    public AdjustAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_adjust, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdjustAdapter.MyViewHolder holder, int position) {
        WarningInfo.Warning data = warningList.get(position);

        holder.titleTv.setText(data.getTitle());
        holder.dayTv.setText(data.getWrt_dt());
        if(data.getTxt_origin_cn() != null) {
            holder.contextTv.setText(data.getTxt_origin_cn());
        } else {
            holder.contextTv.setText(data.getNews());
        }

        if(warningList.size() == 1) {
            holder.viewItem.setVisibility(View.GONE);
        }

        if(downloadBC != null) {
            String url = data.getFile_download_url();
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/TripInfo") + "/" + data.getCountry_nm() + "_지도.jpg";

            holder.downloadBtn.setVisibility(View.VISIBLE);
            holder.downloadBtn.setOnClickListener(v -> downloadBC.URLDownloading(url, path));
        } else {
            holder.downloadBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return warningList.size();
    }
}
