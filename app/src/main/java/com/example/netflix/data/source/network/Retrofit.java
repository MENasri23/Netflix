package com.example.netflix.data.source.network;

import com.example.netflix.data.model.User;

public class Retrofit {

    public User getUser() {
        return new User("userId", "email");
    }
}
