package com.example.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Connection {

    private final int id;
    public Connection(int id) {
        this.id = id;
    }


    public void execute(String sql) {
        System.out.println("Executing on connection " + id + " : " + sql);
    }

    public int getId() {
        return this.id;
    }
}





public class CustomConnectionPool {
    
    private int capacity;
    BlockingQueue<Connection> pool;

    public CustomConnectionPool(int capacity) {
        this.capacity = capacity;
        pool = new ArrayBlockingQueue<>(this.capacity);
        for (int i = 0; i < capacity; i++) {
            pool.add(new Connection(i));
        }
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(Connection conn) {
        if(conn != null) {
            pool.offer(conn);
        }
    }

    public void shutdown() {
        pool.clear();
    }

}
