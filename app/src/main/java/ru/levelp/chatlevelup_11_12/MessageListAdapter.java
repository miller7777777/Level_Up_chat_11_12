package ru.levelp.chatlevelup_11_12;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder>{

    private List<Message> messages;
    private OnListItemClickListener clickListener;

//    private static final int TYPE_MY = 0;
//    private static final int TYPE_OTHER = 1;
//    private String selfId;

    public MessageListAdapter(List<Message> messages, OnListItemClickListener clickListener) {
        this.messages = messages;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == 0){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_messages_list, parent, false);
            return new ViewHolder(v);
        }else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participant_message_list, parent, false);
            return new ViewHolder(v);
        }


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(messages.get(position));

    }

    @Override
    public int getItemViewType(int position) {

        if(position % 2 == 0){
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView messageTv;

        public ViewHolder(View itemView) {
            super(itemView);
            messageTv = (TextView) itemView.findViewById(R.id.message);
            itemView.setOnClickListener(this);
        }

        public void bind(Message message) {


            long t = message.getCreated();
            Date date = new Date(t);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy - HH:mm");

//            String text = message.getBody();
            String text = message.getBody() + "\n\n" + dateFormat1.format(date);

            messageTv.setText(text);

        }

        @Override
        public void onClick(View view) {
            clickListener.OnClick(view, getAdapterPosition());

        }
    }

//    public int getItemViewType(int position) {
//        if (messages.get(position).getSender().equals(messages.get(position).getId())) {
//            return TYPE_MY;
//        }
//        return TYPE_OTHER;
//    }
}
