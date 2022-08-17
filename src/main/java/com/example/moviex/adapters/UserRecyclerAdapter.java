package com.example.moviex.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviex.R;
import com.example.moviex.adapters.interfaces.IUserAdapterCommunicator;
import com.example.moviex.retrofit.model.SearchItem;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {
    private final List<SearchItem> searchItemsList;
    IUserAdapterCommunicator iUserAdapterCommunicator;

    public UserRecyclerAdapter(List<SearchItem> searchItems, IUserAdapterCommunicator iUserAdapterCommunicator) {
        this.searchItemsList = searchItems;
        this.iUserAdapterCommunicator = iUserAdapterCommunicator;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SUBSTITLE", "came here");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_default_view, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SearchItem movieFields = searchItemsList.get(position);
        holder.title.setText(movieFields.getTitle());
        holder.date.setText(movieFields.getYear());
        holder.type.setText(movieFields.getType());
        holder.imDb.setText(movieFields.getImdbID());
        Glide.with(holder.logo.getContext()).load(movieFields.getPoster()).placeholder(R.drawable.image).into(holder.logo);

        holder.root.setOnClickListener(view -> {
            iUserAdapterCommunicator.OnUserClick(movieFields, position);
        });

    }

    @Override
    public int getItemCount() {
        return searchItemsList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView type;
        private TextView date;
        private TextView imDb;
        private ImageView logo;
        private View root;

        public UserViewHolder(View view) {
            super(view);
            root = view;
            logo = view.findViewById(R.id.naruto_poster);
            title = view.findViewById(R.id.naruto_title);
            type = view.findViewById(R.id.naruto_type);
            date = view.findViewById((R.id.naruto_year));
            imDb = view.findViewById(R.id.naruto_ImdB);

        }

    }

}
