package com.stucareassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.stucareassignment.R;
import com.stucareassignment.databinding.ItemDogBinding;
import com.stucareassignment.model.Dog;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.UserViewHolder> {
    private ArrayList<Dog> dogs;
    private OnItemClickListener listener;
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemDogBinding itemDogBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_dog, parent, false);
        return new UserViewHolder(itemDogBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Dog currentDog = dogs.get(position);
        holder.itemDogBinding.setDog(currentDog);
    }

    @Override
    public int getItemCount() {
        if (dogs != null) {
            return dogs.size();
        } else {
            return 0;
        }
    }

    public void setDogList(ArrayList<Dog> dogs) {
        this.dogs = dogs;
        notifyDataSetChanged();
    }
    public Dog getCurrentItemAt(int position) {
        return dogs.get(position);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ItemDogBinding itemDogBinding;

        public UserViewHolder(@NonNull ItemDogBinding itemDogBinding) {
            super(itemDogBinding.getRoot());
            this.itemDogBinding = itemDogBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getCurrentItemAt(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Dog dog);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
