package com.example.videomeeting.Utilities;

import java.util.HashMap;

public class Constants {

    public static final String KEY_COLLECTION_USERS = "Users";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_FCM_TOKEN = "fcm_token";

    public static final String KEY_PREFERENCE_NAME = "videoMeetingPreference";
    public static final String KEY_IS_SIGNED_IN = "IsSignedIn";

    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";

    public static final String REMOTE_MSG_TYPE = "type";
    public static final String REMOTE_MSG_INVITATION = "invitation";
    public static final String REMOTE_MSG_MEETING_TYPE = "meetingType";
    public static final String REMOTE_MSG_INVITER_TOKEN = "inviterToken";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS = "registration_ids";

    public static HashMap<String, String> getRemoteMessageHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put(
                Constants.REMOTE_MSG_AUTHORIZATION,
                "key=AAAAOH6FVco:APA91bFbA2DRTwCDmciPx_v9Dxf_5NjmGjP15YOwETcQNzRZ7_psaOI-EJmzwfP_X3gKMWFMaYUIx8s3kRvjziPRhOQp8KhqbBFD9LtGBZ7lglQWzNVNOfovvfPgLmtuKj45lmYVjvXH"
        );
        headers.put(
                Constants.REMOTE_MSG_CONTENT_TYPE,
                "application/json"
        );
        return headers;
    }
}
