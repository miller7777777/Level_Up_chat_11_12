package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class LifeCycleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d(LifeCycleActivity.class.getSimpleName(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LifeCycleActivity.class.getSimpleName(), "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LifeCycleActivity.class.getSimpleName(), "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LifeCycleActivity.class.getSimpleName(), "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LifeCycleActivity.class.getSimpleName(), "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LifeCycleActivity.class.getSimpleName(), "onDestroy");

    }
}
