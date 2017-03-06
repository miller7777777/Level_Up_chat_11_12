package ru.levelp.chatlevelup_11_12;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder>{

    private ArrayList<Chat> chats;
    private OnListItemClickListener clickListener;


    public ChatListAdapter(ArrayList<Chat> chats, OnListItemClickListener clickListener) {
        this.chats = chats;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(chats.get(position));

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView chatAvatar;
        TextView participant;
        TextView lastMessage;
        TextView updated;



        public ViewHolder(View itemView) {
            super(itemView);
            chatAvatar = (TextView) itemView.findViewById(R.id.chat_avatar);
            participant = (TextView) itemView.findViewById(R.id.participant);
            lastMessage = (TextView) itemView.findViewById(R.id.last_message);
            updated = (TextView) itemView.findViewById(R.id.chat_updated);
            itemView.setOnClickListener(this);
        }

        public void bind(Chat chat) {

            chatAvatar.setText(chat.getParticipants()[1].substring(0, 1));
            participant.setText(chat.getParticipants()[1]);
            lastMessage.setText(chat.getLastMessage());
            long timeChatUpdated = chat.getUpdated();
            Date date = new Date(timeChatUpdated);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
            String time = dateFormat.format(date);
            String time1 = dateFormat1.format(date);
            updated.setText(time1);
//            lastMessage.setText(time1);
        }

        @Override
        public void onClick(View view) {

            clickListener.OnClick(view, getAdapterPosition());

        }
    }
}
