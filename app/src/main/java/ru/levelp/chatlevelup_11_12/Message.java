package ru.levelp.chatlevelup_11_12;


import java.util.Comparator;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static android.os.Build.VERSION_CODES.M;

public class Message extends RealmObject{

    @PrimaryKey
    private String id;
    private String sender;
    private String chatId;
    private long created;
    private String body;

    public Message(String sender, String chatId, String body) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.chatId = chatId;
        this.created = System.currentTimeMillis() - (long) (Math.random() * (1000 * 60 * 60 *24));
        this.body = body;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getChatId() {
        return chatId;
    }

    public long getCreated() {
        return created;
    }

    public String getBody() {
        return body;
    }

    public static final Comparator<Message> compare = new Comparator<Message>() {
        @Override
        public int compare(Message message1, Message message2) {
            return Long.compare(message1.created, message2.created);
        }
    };
}
