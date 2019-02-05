package com.example.trustbuss.trustbuss;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //setLogLevel
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("lRK88nB1NZWwjYyf8cJ8LoWEN3nNewVWx94ba3EF")
                .clientKey("kNEhmfETl4l2ncMNmNhXIKFlEFAsHb6X0nJddNwa")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
