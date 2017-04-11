package ru.levelp.chatlevelup_11_12.storage;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.Sort;
import ru.levelp.chatlevelup_11_12.Chat;
import ru.levelp.chatlevelup_11_12.Message;

public class MessageDatabase {

    private Realm realm;

    public MessageDatabase() {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("message_db")
                .build();
        realm = Realm.getInstance(configuration);
    }

    public void copyOrUpdate(Message message) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(message);
        realm.commitTransaction();
    }

    public void copyOrUpdate(List<Message> messages) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(messages);
        realm.commitTransaction();
    }

    public List<Message> getAll(){
        return realm.copyFromRealm(realm.where(Message.class).findAllSorted("created", Sort.ASCENDING));
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
