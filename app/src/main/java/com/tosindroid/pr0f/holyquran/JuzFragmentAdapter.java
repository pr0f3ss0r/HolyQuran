package com.tosindroid.pr0f.holyquran;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JuzFragmentAdapter extends RecyclerView.Adapter<JuzFragmentAdapter.ViewHolder> {
    JuzFragment ctx;
    List<String> juz, juzNumber, juzPageNumber, juzSurah;
    int pageToGo;
    private static final String TAG = "checking";

    public JuzFragmentAdapter(JuzFragment context, List<String> juz, List<String> juzPageNumber, List<String> juzNumber, List<String> juzSurahName){
        this.ctx = context;
        this.juz = juz;
        this.juzNumber = juzNumber;
        this.juzPageNumber = juzPageNumber;
        this.juzSurah = juzSurahName;
    };

    @NonNull
    @Override
    public JuzFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juz_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JuzFragmentAdapter.ViewHolder holder, int position) {
        holder.juz.setText(juz.get(position));
        holder.juzNumber.setText(juzNumber.get(position));
        holder.juzPageNumber.setText(juzPageNumber.get(position));
        holder.juzSurah.setText(juzSurah.get(position));
    }



    @Override
    public int getItemCount() {
        return juz.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView juz;
        TextView juzNumber;
        TextView juzPageNumber;
        TextView juzSurah;
        LinearLayout juzLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            juz = itemView.findViewById(R.id.juzNameTextView);
            juzNumber = itemView.findViewById(R.id.juzNumberTextView);
            juzPageNumber = itemView.findViewById(R.id.pageNumberTextView);
            juzSurah = itemView.findViewById(R.id.surahNameTextView);
            juzLinearLayout = itemView.findViewById(R.id.juzLinearLayout);

            juzLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), QuranViewActivity.class);
                    //get the page number from the textview
                    String pageValue = juzNumber.getText().toString();
                    Log.d(TAG, pageValue);
                    pageValue = pageValue.substring(5);

                    pageToGo  = Integer.parseInt(pageValue);
                    myIntent.putExtra("fatir", pageToGo);
                    v.getContext().startActivity(myIntent);


                }
            });
        }
    }
}
