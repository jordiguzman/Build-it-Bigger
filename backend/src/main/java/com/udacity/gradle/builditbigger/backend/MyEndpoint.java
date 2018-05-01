package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import appkite.jordiguzman.com.libjokes.Jokes;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a joke and show */
    @ApiMethod(name = "tellJokes")
    public MyBean sayJoke() {
        MyBean response = new MyBean();
        response.setData(new Jokes().getRandomJoke());

        return response;
    }

}
