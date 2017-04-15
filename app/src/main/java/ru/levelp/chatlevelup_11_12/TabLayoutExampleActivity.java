package ru.levelp.chatlevelup_11_12;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import static ru.levelp.chatlevelup_11_12.R.id.pager;

public class TabLayoutExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_example);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ChatListFragment(), "Chats");
        adapter.addFragment(new UserListFragment(), "Users");

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }
}
