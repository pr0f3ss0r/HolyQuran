package com.tosindroid.pr0f.holyquran;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

import java.lang.reflect.Field;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


public class QuranViewPagerAdapter extends RecyclerView.Adapter<QuranViewPagerAdapter.ViewHolder> {

    Context mContext;
    private int[] images;


    public QuranViewPagerAdapter(Context context, ViewPager2 viewPager2) {
        this.mContext = context;
        buildImageArray();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.imageView.setImageResource(images[position]);

    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PhotoView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


    private void buildImageArray() {

        // this will give you **all** drawables, including those from e.g. the support libraries!
        Field[] drawables = R.drawable.class.getDeclaredFields();
        SparseIntArray temp = new SparseIntArray();
        int index = 0;
        for (Field f : drawables) {
            try {
                System.out.println("R.drawable." + f.getName());
                // check the drawable is "yours" by comparing the name to your name pattern
                // this is the point where some unwanted drawable may slip in,
                // so you should spend some effort on the naming/ matching of your pictures
                if (f.getName().startsWith("holy_quran_simple_arabic_")) {
                    //System.out.println("R.drawable." + f.getName() + "==========================================");
                    int id = mContext.getResources().getIdentifier(f.getName(), "drawable", mContext.getPackageName());
                    temp.append(index, id);
                    index++;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        images = new int[index];
        for (int i = 0; i < index; i++) {
            images[i] = temp.valueAt(i);
        }
    }
}

