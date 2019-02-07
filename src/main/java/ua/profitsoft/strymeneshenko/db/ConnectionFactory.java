package ua.profitsoft.strymeneshenko.db;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private static ConnectionFactory instance;

    public static synchronized ConnectionFactory getInstance(){
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    private ConnectionFactory(){
        Properties p = new Properties();
        try {
            InputStream input = new FileInputStream("src/main/resources/db.properties");
            LOGGER.info("input file open!");
            p.load(input);
        } catch (FileNotFoundException e) {
            LOGGER.error("File doesn't exist!", e);
        } catch (IOException e) {
            LOGGER.error("Can't load properties", e);
        }
        p.setProperty("url", "jdbc:mysql://" + p.getProperty("db.host") + "/" + p.getProperty("db.name") + "?serverTimezone=" + p.getProperty("db.serverTimezone"));
        try {
            dataSource = BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            LOGGER.error("Can't create DataSource!", e);
        }
    }

    public Connection getMySQLConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        con.setAutoCommit(false);
        return con;
    }
}
