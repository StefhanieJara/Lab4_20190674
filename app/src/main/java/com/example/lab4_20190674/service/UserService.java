package com.example.lab4_20190674.service;

import android.provider.ContactsContract;

import com.example.lab4_20190674.dto.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("/api")
    Call<Profile> getResults();
}
