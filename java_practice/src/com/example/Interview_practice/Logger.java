package com.example.Interview_practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance;
    private final BufferedWriter writer;

    private Logger() throws IOException {
        instance = new Logger();
        writer = new BufferedWriter(new FileWriter("app.log", true));        
    }

    public static Logger getInstance() throws IOException {
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public synchronized void log(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }
}
