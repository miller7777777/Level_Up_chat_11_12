package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class ExampleWithFragmentActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_with_fragment);

        ChatListFragment fragment = new ChatListFragment();
        Bundle args = new Bundle();
        args.putString("key", "value");
        fragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container, fragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
