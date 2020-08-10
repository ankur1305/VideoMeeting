package com.example.videomeeting.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videomeeting.Listeners.UsersListener;
import com.example.videomeeting.Models.User;
import com.example.videomeeting.R;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> users;
    private UsersListener usersListener;
    private List<User> selectedUsers;

    public UsersAdapter(List<User> users, UsersListener usersListener) {

        this.users = users;
        this.usersListener = usersListener;
        selectedUsers = new ArrayList<>();
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_user,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView textFirstChar, textUsername, textEmail;
        ImageView imageAudioMeeting, imageVideoMeeting, imageSelected;
        ConstraintLayout userContainer;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textFirstChar = itemView.findViewById(R.id.textFirstChar);
            textUsername = itemView.findViewById(R.id.textUsername);
            textEmail = itemView.findViewById(R.id.textEmail);
            imageAudioMeeting = itemView.findViewById(R.id.imageAudioMeeting);
            imageVideoMeeting = itemView.findViewById(R.id.imageVideoMeeting);
            imageSelected = itemView.findViewById(R.id.imageSelected);
            userContainer = itemView.findViewById(R.id.userContainer);
        }

        void setUserData(User user) {
            textFirstChar.setText(user.firstName.substring(0, 1));
            textUsername.setText(String.format("%s %s", user.firstName, user.lastName));
            textEmail.setText(user.email);
            imageAudioMeeting.setOnClickListener(v -> usersListener.initiateAudioMeeting(user));
            imageVideoMeeting.setOnClickListener(v -> usersListener.initiateVideoMeeting(user));

            userContainer.setOnLongClickListener(v -> {
                if (imageSelected.getVisibility() != View.VISIBLE) {
                    selectedUsers.add(user);
                    imageSelected.setVisibility(View.VISIBLE);
                    imageAudioMeeting.setVisibility(View.GONE);
                    imageVideoMeeting.setVisibility(View.GONE);
                    usersListener.onMultipleUsersAction(true);
                }
                return true;
            });

            userContainer.setOnClickListener(v -> {
                if (imageSelected.getVisibility() == View.VISIBLE) {
                    selectedUsers.remove(user);
                    imageSelected.setVisibility(View.GONE);
                    imageAudioMeeting.setVisibility(View.VISIBLE);
                    imageVideoMeeting.setVisibility(View.VISIBLE);
                    if (selectedUsers.size() == 0) {
                        usersListener.onMultipleUsersAction(false);
                    }
                } else {
                    if (selectedUsers.size() > 0) {
                        selectedUsers.add(user);
                        imageSelected.setVisibility(View.VISIBLE);
                        imageVideoMeeting.setVisibility(View.GONE);
                        imageAudioMeeting.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
