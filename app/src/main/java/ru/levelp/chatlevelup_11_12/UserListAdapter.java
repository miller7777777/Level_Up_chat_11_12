package ru.levelp.chatlevelup_11_12;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> users;
    private OnListItemClickListener clickListener;

    public UserListAdapter(List<User> users, OnListItemClickListener clickListener) {
        this.users = users;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_example, parent, false);
        Log.d("UserListAdapter", "Отработал ViewHolder onCreateViewHolder");
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView avatar;
        TextView name;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = (TextView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        public void bind(User user){
//            avatar.setImageResource(R.mipmap.ic_launcher);
            avatar.setText(user.getName().substring(0, 1));
            name.setText(user.getName());
            description.setText(user.getDescription());
        }

        @Override
        public void onClick(View view) {

            clickListener.OnClick(view, getAdapterPosition());
        }
    }
}
