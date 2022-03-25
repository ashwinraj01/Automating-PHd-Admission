package com.winash.uniapp.ui.FAQ;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winash.uniapp.AdminDashboard;
import com.winash.uniapp.R;
import com.winash.uniapp.VersionsAdapter;
import com.winash.uniapp.version;

import java.util.ArrayList;
import java.util.List;

public class FAQfragementadapter extends RecyclerView.Adapter<FAQfragementadapter.MyViewHolder> {
   public ArrayList<FAQ_question> versionsList;
    public Context context;

    public FAQfragementadapter(ArrayList<FAQ_question> versionsList,Context context) {
        this.versionsList = versionsList;
        this.context=context;
    }

    @NonNull
    @Override
    public FAQfragementadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_faq_card,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQfragementadapter.MyViewHolder holder, int position) {

        FAQ_question faq = versionsList.get(position);
        holder.queTxt.setText(faq.getQuestion());
        holder.answer.setText(faq.getAnswer());
        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView queTxt;
        EditText answer;
        Button reply;
        DatabaseReference ref;
        LinearLayout linearLayout;
        LinearLayout expandableLayout;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);

            queTxt = itemView.findViewById(R.id.que_admin);
            answer = itemView.findViewById(R.id.ans_admin);
            reply=itemView.findViewById(R.id.button_reply_admin);
            linearLayout = itemView.findViewById(R.id.linear_layout_admin_faq_view);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_user_faq_admin);
            ref= FirebaseDatabase.getInstance().getReference("Faqs");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FAQ_question faq = versionsList.get(getAdapterPosition());
                    faq.setExpandable(!faq.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FAQ_question neww=new FAQ_question(queTxt.getText().toString(),answer.getText().toString());
                    ArrayList<FAQ_question> temp= (ArrayList<FAQ_question>) versionsList.clone();
                    versionsList.clear();
                    ref.child(temp.get(getAdapterPosition()).getQuestion()).setValue(neww).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(view.getContext(), "Updated reply", Toast.LENGTH_SHORT).show();

                            }
                            else
                                Toast.makeText(view.getContext(), "Error occured try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }
}
