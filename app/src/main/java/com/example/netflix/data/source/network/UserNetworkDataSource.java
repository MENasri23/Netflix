package com.example.netflix.data.source.network;

import com.example.netflix.data.model.User;

public class UserNetworkDataSource {
    private final Retrofit retrofit;

    public UserNetworkDataSource() {
        this.retrofit = new Retrofit();
    }

    public User getUserById(int id) {
        return retrofit.getUser();
    }
}
