package com.umutcanneset.parse;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //set log level
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
            .applicationId("GKGOguBdOrPpa2JLqpMdREMH52dglJMr15WcZhrm")
                .clientKey("lkRmdFuxOX9ZSycUN50LwLdErAeMd2SUBSNyl08K")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
