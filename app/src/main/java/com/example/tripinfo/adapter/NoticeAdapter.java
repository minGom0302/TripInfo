package com.example.tripinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripinfo.R;
import com.example.tripinfo.activity.NoticeDataActivity;
import com.example.tripinfo.dto.NoticeData;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {
    List<NoticeData> noticeList;
    Context context;

    public NoticeAdapter(List<NoticeData> noticeList) {
        this.noticeList = noticeList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, day;
        LinearLayout layout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.listviewNotice_title);
            day = view.findViewById(R.id.listviewNotice_day);
            layout = view.findViewById(R.id.listviewNotice_layout);
        }
    }

    @NonNull
    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_notice, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.MyViewHolder holder, int position) {
        NoticeData notice = noticeList.get(position);
        if(notice != null) {
            holder.title.setText(notice.getTitle());
            holder.day.setText(notice.getWritten_dt());
            holder.layout.setOnClickListener(v -> {
                Intent intent = new Intent(context, NoticeDataActivity.class);
                intent.putExtra("title", notice.getTitle());
                intent.putExtra("context", notice.getTxt_origin_cn().replaceAll("&nbsp;", " ")
                        .replaceAll("&lt;", "\n<").replaceAll("&gt;", ">").replaceAll("□", "\n□")
                        .replaceAll("ㅇ", "\nㅇ").replaceAll("○", "\n\n○").replaceAll("※", "\n\n※"));
                intent.putExtra("day", notice.getWritten_dt());
                intent.putExtra("download", notice.getFile_download_url());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return noticeList.size();
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public void updateItem(List<NoticeData> noticeList) {
        this.noticeList = noticeList;
    }
}
