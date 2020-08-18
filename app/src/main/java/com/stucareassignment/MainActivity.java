package com.stucareassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.stucareassignment.adapter.DogAdapter;
import com.stucareassignment.databinding.ActivityMainBinding;
import com.stucareassignment.viewmodel.DogViewModel;
import com.stucareassignment.model.Dog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private DogViewModel dogViewModel;
    DogAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new DogAdapter();
        recyclerView.setAdapter(adapter);

        dogViewModel = ViewModelProviders.of(this).get(DogViewModel.class);
        adapter.setOnItemClickListener(dog -> {
            Toast.makeText(getApplicationContext(),"Selected dog name is : "+dog.getName(), Toast.LENGTH_SHORT).show();
        });

        getDogData();

    }

    //getting dog data from API
    private void getDogData() {
        //observer will listen to live data
        dogViewModel.getDogData().observe(this, userList -> {
            adapter.setDogList((ArrayList<Dog>) userList);
        });
    }


}