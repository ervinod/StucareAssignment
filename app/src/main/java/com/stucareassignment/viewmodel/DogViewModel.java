package com.stucareassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.stucareassignment.model.Dog;
import com.stucareassignment.repository.DogRepository;

import java.util.List;

public class DogViewModel extends AndroidViewModel {
    DogRepository repository;

    public DogViewModel(@NonNull Application application) {
        super(application);
        repository = new DogRepository(application);
    }

    public LiveData<List<Dog>> getDogData() {
        //call api in repo which will return livedata as list
        return repository.getDogData();
    }

}
