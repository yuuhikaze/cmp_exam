package com.yuuhikaze.exam_02.model;

public class MySQLConnection extends DBConnection {
    public MySQLConnection(
        String host, String port, String database, String user, String password) {
        super(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://" + host + ":" + port + "/" + database,
            user,
            password);
    }

    public MySQLConnection(String host, String database, String user, String password) {
        super("com.mysql.jdbc.Driver", "jdbc:mysql://" + host + "/" + database, user, password);
    }
}
