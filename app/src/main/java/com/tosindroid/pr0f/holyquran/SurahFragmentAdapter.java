package com.tosindroid.pr0f.holyquran;

import android.content.Context;
import android.content.Intent;
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
    List<String>  chapterName, chapterNumber, pageNumber;
    List<String>  chapterNameFiltered;
    int pageToGo;
    private static final String TAG = "checkers";




    public SurahFragmentAdapter(SurahFragment context, List<String> chapterName, List<String> chapterNumber, List<String> pageNumber) {
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.pageNumber = pageNumber;
        this.ctx = context;

        chapterNameFiltered = new ArrayList<>(chapterName);

        Log.d(TAG, chapterNameFiltered.toString());

}

    @NonNull
        @Override
        public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_grid_layout, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder (@NonNull ViewHolder holder,int position){
            holder.chapterName.setText(chapterName.get(position));
            holder.surahNumber.setText(chapterNumber.get(position));
            holder.pageNumber.setText(pageNumber.get(position));

        }

        @Override
        public int getItemCount () {
            return chapterName.size();
            }



    @Override
    public Filter getFilter() {
        return filters;
    }

    private Filter filters = new Filter() {
        @Override

        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(chapterNameFiltered);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(String item: chapterNameFiltered){
                    if(item.toLowerCase().contains(filterPattern)){
                        Log.d(TAG, "performFiltering: "+item);
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            chapterName.clear();
            Log.d(TAG, "cleared? "+chapterName + " "+ chapterNameFiltered);
            chapterName.addAll((List<String>) results.values);
            notifyDataSetChanged();
        }
    };




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView chapterName;
        TextView pageNumber;
        TextView surahNumber;
        CardView cardView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                chapterName = itemView.findViewById(R.id.surahNameTextView);
                surahNumber = itemView.findViewById(R.id.surahNumberTextView);
                pageNumber = itemView.findViewById(R.id.pageNumberTextView);
                cardView = itemView.findViewById(R.id.cardView);

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

                       /* for (int i = 0; i < cardView.getChildCount(); i++){
                            cardView.getChildAt(i);
                            ViewGroup viewGroup = (ViewGroup) cardView.getChildAt(0);
                            for(int j=0; j<viewGroup.getChildCount(); j++){
                                String getName = ((TextView) viewGroup.getChildAt(j)).getText().toString();
                                Log.d(TAG, getName);
                            }
                        }*/


                        //String a = ctx.squares[0];
                    }
                });

            }
        }

    }
