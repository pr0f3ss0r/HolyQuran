package com.tosindroid.pr0f.holyquran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SurahFragmentAdapter extends RecyclerView.Adapter<SurahFragmentAdapter.ViewHolder> implements Filterable {
    SurahFragment ctx;
    List<Chapters> chapters, chaptersCopy;
    int pageToGo;
    private static final String TAG = "checkers";




    public SurahFragmentAdapter(SurahFragment context, ArrayList<Chapters> chapters) {
        this.ctx = context;
        this.chapters = chapters;
        this.chaptersCopy = chapters;

        Log.d(TAG, "performFiltering 0.1: "+chapters);
        Log.d(TAG, "performFiltering 0.2: "+chaptersCopy);

}



    @NonNull
        @Override
        public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_grid_layout, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder (@NonNull ViewHolder holder,int position){
            holder.chapterName.setText(chaptersCopy.get(position).getSurahs());
            holder.surahNumber.setText(chaptersCopy.get(position).getChapterNumber());
            holder.pageNumber.setText(chaptersCopy.get(position).getPageNumber());
        }

        @Override
        public int getItemCount () {
            return chaptersCopy.size();
            }


    @Override
    public Filter getFilter() {
        return filters;
    }

    private Filter filters = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            String filterPattern = constraint.toString().toLowerCase().trim();
            Log.d(TAG, "performFiltering 2: "+chaptersCopy);
            List<Chapters> filteredList = new ArrayList<>();
            if(filterPattern == null || filterPattern.length() == 0){
                results.count = chapters.size();
                results.values = chapters;
                Log.d(TAG, "performFiltering 2.5: "+chapters);
                Log.d(TAG, "performFiltering 2.8: "+chaptersCopy);
                Log.d(TAG, "performFiltering 2.9: "+results.values);
            }else {
                List<Chapters> filteredChapters = new ArrayList<>();
                for (Chapters item: chapters){
                    Log.d(TAG, "performFiltering 3: " +filteredChapters);
                    if (item.getSurahs().toLowerCase().contains(filterPattern)){
                        filteredChapters.add(item);
                        Log.d(TAG, "performFiltering 3.2: " +filteredChapters);
                    }
                    results.count = filteredChapters.size();
                    results.values = filteredChapters;
                }
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            chaptersCopy = (ArrayList<Chapters>) results.values;
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView chapterName;
        TextView surahNumber;
        TextView pageNumber;
        CardView cardView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.chapterName = itemView.findViewById(R.id.surahNameTextView);
                this.surahNumber = itemView.findViewById(R.id.surahNumberTextView);
                this.pageNumber = itemView.findViewById(R.id.pageNumberTextView);
                this.cardView = itemView.findViewById(R.id.cardView);

                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //v.getContext().startActivity(new Intent(v.getContext(), QuranViewActivity.class));
                        Intent myIntent = new Intent(v.getContext(), QuranViewActivity.class);
                        String pageValue = pageNumber.getText().toString();
                        // 4 is start index to be removed and -1 is the last index
                        pageValue = pageValue.substring(5);
                        Log.d(TAG, pageValue);
                        pageToGo  = Integer.parseInt(pageValue);
                        myIntent.putExtra("fatir", pageToGo);
                        v.getContext().startActivity(myIntent);

                    }
                });

            }
        }


    }
