package ru.levelp.chatlevelup_11_12;


import android.util.Log;

import java.util.Comparator;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static android.R.id.primary;

public class Chat extends RealmObject{

    @PrimaryKey
    private String id;
    private String title;
    private String author;
    private String participant;
    private String lastMessage;
    private long created;
    private long updated;

    public Chat(String author, String participant) {
        this.id = UUID.randomUUID().toString();
        this.author = author;
        this.participant = participant;
        this.lastMessage = "Xxxxxx xxxxxxxxxxxxxxxxx xxxxxxxxxxxxxx xxxxxxxxxxxxx xxxxxxxxxxxxx";
//        this.created = System.currentTimeMillis() - (long) (Math.random() * (1000 * 60 * 60 *24));
        this.created = System.currentTimeMillis();
        this.updated = created;
        this.title = participant;
        Log.d("Chat", "Создан новый чат с " + participant);
    }

    public Chat() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getParticipant() {
        return participant;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }

    public static final Comparator<Chat> compare = new Comparator<Chat>() {
        @Override
        public int compare(Chat chat1, Chat chat2) {
            return Long.compare(chat2.updated, chat1.updated);
        }
    };
}
