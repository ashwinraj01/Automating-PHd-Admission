package com.winash.uniapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VersionsAdapter extends RecyclerView.Adapter<VersionsAdapter.VersionVH> {

    List<version> versionsList;

    public VersionsAdapter(List<version> versionsList) {
        this.versionsList = versionsList;
    }

    @NonNull
    @Override
    public VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_fragment,parent, false);
        return new VersionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionVH holder, int position) {

        version versions = versionsList.get(position);
        holder.queTxt.setText(versions.getQue());
        holder.conTxt.setText(versions.getCon());
        holder.userTxt.setText(versions.getUser());
        holder.ansTxt.setText(versions.getAns());

        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder {

        TextView queTxt, conTxt, ansTxt, userTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public VersionVH(@NonNull View itemView) {
            super(itemView);

            queTxt = itemView.findViewById(R.id.que);
            conTxt = itemView.findViewById(R.id.con);
            ansTxt = itemView.findViewById(R.id.ans);
            userTxt = itemView.findViewById(R.id.user);

            linearLayout = itemView.findViewById(R.id.linear_layout_user_faq_view);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_user_faq);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    version versions = versionsList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
