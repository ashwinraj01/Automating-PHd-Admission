package com.winash.uniapp.ui.FAQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winash.uniapp.R;
import com.winash.uniapp.VersionsAdapter;
import com.winash.uniapp.version;

import java.util.List;

public class FAQfragementadapter extends RecyclerView.Adapter<FAQfragementadapter.VersionVH> {
    List<FAQ_question> versionsList;
    Context context;

    public FAQfragementadapter(List<FAQ_question> versionsList,Context context) {
        this.versionsList = versionsList;
        this.context=context;
    }

    @NonNull
    @Override
    public FAQfragementadapter.VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_faq_card,parent, false);
        return new FAQfragementadapter.VersionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQfragementadapter.VersionVH holder, int position) {

        FAQ_question faq = versionsList.get(position);
        holder.queTxt.setText(faq.question);
        holder.answer.setText(faq.getAnswer());
        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder {

        TextView queTxt;
        EditText answer;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public VersionVH(@NonNull View itemView) {
            super(itemView);

            queTxt = itemView.findViewById(R.id.que_admin);
            answer = itemView.findViewById(R.id.ans_admin);

            linearLayout = itemView.findViewById(R.id.linear_layout_admin_faq_view);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_user_faq_admin);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FAQ_question faq = versionsList.get(getAdapterPosition());
                    faq.setExpandable(!faq.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
