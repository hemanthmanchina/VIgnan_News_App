package com.example.vignannewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DataClass> dataList;
    private Context context;


    public MyAdapter(Context context, ArrayList<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //DataClass data = dataList.get(position);
        Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.recyclerImage);
        holder.recyclerCaption.setText(dataList.get(position).getCaption());
        /*holder.deleteButton.setOnClickListener(v -> {
            DataClass deletedData = dataList.remove(position);
            notifyItemRemoved(position);
            mDatabase = FirebaseDatabase.getInstance().getReference("Images");

            // Get a reference to the Firebase Storage
            mStorage = FirebaseStorage.getInstance().getReference("gs://vignan-news-app.appspot.com");

            // Delete the image from Firebase Storage
            mStorage.child(deletedData.getImageURL()).delete();

            // Delete the data from Firebase Database
            mDatabase.child(deletedData.getKey()).removeValue();
        });*/
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImage;
        TextView recyclerCaption;
        //Button deleteButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);
            recyclerCaption = itemView.findViewById(R.id.recyclerCaption);
            /*recyclerImage = itemView.findViewById(R.id.image_view);
            recyclerCaption = itemView.findViewById(R.id.text_view);
            deleteButton = itemView.findViewById(R.id.delete_button);*/
        }
    }
}