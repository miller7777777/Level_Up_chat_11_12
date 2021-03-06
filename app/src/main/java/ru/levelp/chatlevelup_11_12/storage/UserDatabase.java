package ru.levelp.chatlevelup_11_12.storage;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.Sort;
import ru.levelp.chatlevelup_11_12.User;

public class UserDatabase {

    private Realm realm;

    public UserDatabase(){

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("user_db")
                .build();

        realm = Realm.getInstance(configuration);

    }

    public void copyOrUpdate(User user){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    public List<User> getAll(){
        return realm.where(User.class).findAllSorted("name", Sort.DESCENDING);

    }

    public void close(){
        if(!realm.isClosed()){
            realm.close();
        }
    }

    public void copyOrUpdate(List<User> users) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(users);
        realm.commitTransaction();
    }

    public void addChangeListener(RealmChangeListener<Realm> changeListener){
        realm.addChangeListener(changeListener);
    }
}
