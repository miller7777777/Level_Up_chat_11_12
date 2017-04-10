package ru.levelp.chatlevelup_11_12.storage;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;
import io.realm.Sort;
import ru.levelp.chatlevelup_11_12.Chat;

public class ChatDatabase {

    private Realm realm;

    public ChatDatabase() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("chat_db")
                .build();

        realm = Realm.getInstance(configuration);
    }

    public void copyOrUpdate(Chat chat) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(chat);
        realm.commitTransaction();
    }

    public void copyOrUpdate(List<Chat> chats) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(chats);
        realm.commitTransaction();
    }

    public List<Chat> getAll(){
        return realm.copyFromRealm(realm.where(Chat.class).findAllSorted("participant", Sort.DESCENDING));
    }

    public void close(){
        if(!realm.isClosed()){
            realm.close();
        }
    }

    public void addChangeListener(RealmChangeListener<Realm> changeListener){
        realm.addChangeListener(changeListener);
    }

}
