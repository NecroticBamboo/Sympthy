package main.utils;

public interface IDatabaseManager {
    ConnectionInfo acquire();
    void release(ConnectionInfo connectionInfo);
}
