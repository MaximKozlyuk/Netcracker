package jdbc;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseManager {

    private static DataBaseManager ourInstance = new DataBaseManager();

    private DataSource dataSource;

    public static DataBaseManager getInstance() {
        return ourInstance;
    }

    private DataBaseManager() {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        PGSimpleDataSource pgDs = new PGSimpleDataSource();
        pgDs.setServerName("127.0.0.1");
        pgDs.setPortNumber(12345);
        pgDs.setDatabaseName("postgres");
        pgDs.setUser("postgres");
        pgDs.setPassword("");
        this.dataSource = pgDs;
    }

    public Connection getConnection () {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("cant get connection from DataSource");
        }
    }

}
