package com.yuuhikaze.exam_02;

import com.yuuhikaze.exam_02.model.MySQLConnection;

public class ConnectionSingleton implements AutoCloseable  {

    private ConnectionSingleton() {}

    public static MySQLConnection connection =
        new MySQLConnection(
            "localhost",
            "mdb_cmp_exam",
            "CMPEXAMUSER",
            "kHP5Dx2YubIBS778oBqn");

    @Override
    public void close() throws Exception {
        connection.closeConnection();
    }
}
