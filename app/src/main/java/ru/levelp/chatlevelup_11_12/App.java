package ru.levelp.chatlevelup_11_12;


import android.app.Application;

import io.realm.Realm;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
