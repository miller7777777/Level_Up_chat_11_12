package ru.levelp.chatlevelup_11_12;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ru.levelp.chatlevelup_11_12.storage.MessageDatabase;

import static android.os.Build.VERSION_CODES.M;

public class MessagesListActivity extends AppCompatActivity {

    private RecyclerView messageListRecyclerView;
    private MessageListAdapter adapter;
    private List<Message> messages;

    private MessageDatabase messageDB;
    private Boolean visible = false;

    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(MessageListAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Log.d(MessageListAdapter.class.getSimpleName(), new Date(System.currentTimeMillis()).toString());
        Toast.makeText(MessagesListActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        messageDB = new MessageDatabase();
        Log.e(MessagesListActivity.class.getSimpleName(), "Draw screen");

        messageListRecyclerView = (RecyclerView) findViewById(R.id.message_list_recyclerview);
        Log.e(MessagesListActivity.class.getSimpleName(), "Define RecyclerView");

        messageListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.e(MessagesListActivity.class.getSimpleName(), "Set up RecyclerView LayoutManager");

        createFakeMessages();


        adapter = new MessageListAdapter(messages, clickListener);
        Log.e(MessagesListActivity.class.getSimpleName(), "Adapter created");

        messageListRecyclerView.setAdapter(adapter);

        Log.e(MessagesListActivity.class.getSimpleName(), "Adapter assigned to Activity");

    }

    @NonNull
    private void createFakeMessages() {
        List<Message> messages1 = new ArrayList<Message>();


        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "В фоне работает FoneService.java, который, в отдельном потоке, каждые 15 секунд делает запрос на сервер."));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "луашоа цлаощфыза выылфооа ылвщ ФВЩЗловщ"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "ЛЫРВФпр гфыиипыос вригва"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "уаорфыалфжьбмси офысо мслывв"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "лывомщ"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "всатфалофзб"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "алофуащздфуаб"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "валыоащфла"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "ваьофал шрашфиа шраш"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "вфыьатшфоа уарфшар уар"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "аофрашф ушрагф угаргфыра"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "афоарфш фашрфша фа фша"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "аофшщао фцавшфра ацшфа"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "вафыьроа фуашфра афа"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "фаво аафйуа  афа ф"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "аьыфлоа афушуооа па фпаф"));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "афыало афф афаш афф"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "афаол афа а фаф а"));
//        messages1.add(new Message("Miller777", "Victoria Tiffany", "афваш аф ашфышуа п аппауфаупупцкпиым "));
//        messages1.add(new Message("Victoria Tiffany", "Victoria Tiffany", "ал памышпг пып щыплолыз п"));

        Log.e(MessagesListActivity.class.getSimpleName(), "Collection full");
        Log.d(MessagesListActivity.class.getSimpleName(), "Collection full");


        Collections.sort(messages1, Message.compare);

        Log.e(MessagesListActivity.class.getSimpleName(), "Collection sorted");

        messageDB.copyOrUpdate(messages1);

        performsMessages();

    }

    private void performsMessages() {

        messages = messageDB.getAll();

        messageDB.addChangeListener(element -> {
            if(adapter != null){
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onListChanged(int position){
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        visible = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                MessageDatabase secondMessageDB = new MessageDatabase();
                while (visible){
                    secondMessageDB.copyOrUpdate(new Message(UUID.randomUUID().toString().substring(0, 10),
                            UUID.randomUUID().toString().substring(0, 10),
                            "Bla-bla-bla"));

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                secondMessageDB.close();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        messageDB.close();
        super.onDestroy();
    }
}
