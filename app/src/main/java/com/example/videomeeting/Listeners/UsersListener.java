package com.example.videomeeting.Listeners;

import com.example.videomeeting.Models.User;

public interface UsersListener {

    void initiateVideoMeeting(User user);
    void initiateAudioMeeting(User user);
}
