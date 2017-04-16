package ru.levelp.chatlevelup_11_12;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ru.levelp.chatlevelup_11_12.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class NewUserAndChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_and_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Список пользователей и чатов");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ChatListFragment(), "Чаты");
        adapter.addFragment(new UserListFragment(), "Юзеры");

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }
}
