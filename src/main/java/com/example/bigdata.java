package com.example;

public class bigdata {
    private static String to;
    private static String msg = "User who sent you this is asleep right now";

    public static String getmessage(){
        return msg;
    }

    public static void setmessage(String message){
        msg = message;
    }
    
    public static String getrecipient(){
        return to;
    }

    public static void setrecipient(String recipient){
        to = recipient;
    }
}
