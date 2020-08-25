package com.tosindroid.pr0f.holyquran;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JuzFragmentAdapter extends RecyclerView.Adapter<JuzFragmentAdapter.ViewHolder> implements Filterable {
    JuzFragment ctx;
    List<JuzPart> parts, partsCopy;
    int pageToGo;
    private static final String TAG = "checking";

    public JuzFragmentAdapter(JuzFragment context, List<JuzPart> parts){
        this.ctx = context;
        this.parts = parts;
        this.partsCopy = parts;
    };

    @NonNull
    @Override
    public JuzFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juz_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JuzFragmentAdapter.ViewHolder holder, int position) {
        holder.juz.setText(partsCopy.get(position).getJuz());
        holder.juzNumber.setText(partsCopy.get(position).getJuzNumber());
        holder.juzPageNumber.setText(partsCopy.get(position).getJuzPageNumber());
        holder.juzSurah.setText(partsCopy.get(position).getJuzSurahName());
    }



    @Override
    public int getItemCount() {
        return partsCopy.size();
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
            Log.d(TAG, "performFiltering 2: "+partsCopy);
            List<JuzPart> filteredList = new ArrayList<>();
            if(filterPattern == null || filterPattern.length() == 0){
                results.count = parts.size();
                results.values = parts;
                Log.d(TAG, "performFiltering 2.5: "+parts);
                Log.d(TAG, "performFiltering 2.8: "+partsCopy);
                Log.d(TAG, "performFiltering 2.9: "+results.values);
            }else {
                List<JuzPart> filteredChapters = new ArrayList<>();
                for (JuzPart item: parts){
                    Log.d(TAG, "performFiltering 3: " +filteredChapters);
                    if (item.getJuz().toLowerCase().contains(filterPattern)){
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
            partsCopy = (ArrayList<JuzPart>) results.values;
            notifyDataSetChanged();
        }
    };


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
                    String pageValue = juzPageNumber.getText().toString();
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
