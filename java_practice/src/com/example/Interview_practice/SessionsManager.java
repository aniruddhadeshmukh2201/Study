package com.example.Interview_practice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


class Session {

}


public class SessionsManager {
    Map<String, Session> sessions = new ConcurrentHashMap<>();


    public void createSession(String userId, Session session) {
        sessions.put(userId, session);
        return;
    }  

    public Session getSession(String userId) {  
        return sessions.get(userId);
    } 

    public void removeSession(String userId) {
        sessions.remove(userId);
        return;
    }
}



