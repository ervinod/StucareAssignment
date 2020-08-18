package com.stucareassignment.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.stucareassignment.api.ApiRequestData;
import com.stucareassignment.api.RetrofitClient;
import com.stucareassignment.model.Dog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {

    private ArrayList<Dog> dogArrayList = new ArrayList<>();
    private MutableLiveData<List<Dog>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public DogRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Dog>> getDogData() {
        ApiRequestData apiService = RetrofitClient.getRetrofitServer();
        Call<List<String>> call = apiService.getDogs();

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body() != null) {
                    Log.d("API RESPONSE",""+response.body());

                    List<String> dogStringList = (List<String>) response.body();
                    ArrayList<Dog> dogArrayList = new ArrayList<>();

                    if(dogStringList!=null && dogStringList.size()>0){

                        //iterating dog image list to create new liast of object type
                        for(int i=0;i<dogStringList.size();i++){
                            //adding image to dog
                            Dog dog = new Dog();
                            dog.setName("Dog "+i);
                            dog.setDescription("This is Dog "+i+" description");
                            dog.setImage(dogStringList.get(i));
                            dogArrayList.add(dog);
                        }
                        mutableLiveData.setValue(dogArrayList);
                    }


                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //handle api error here like dismissing progress bar
            }
        });
        return mutableLiveData;
    }

}
