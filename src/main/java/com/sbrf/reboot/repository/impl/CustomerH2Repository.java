package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "sa";
    private final String PASS = "";

    @SneakyThrows
    public CustomerH2Repository() {
        DriverManager.registerDriver((Driver) Class.forName(JDBC_DRIVER).newInstance());
        executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER (id bigint AUTO_INCREMENT, "+
                "name VARCHAR(256), eMail VARCHAR(256) UNIQUE, PRIMARY KEY (id))");
        deleteAll();//чтобы все тесты многократно прошел

    }

    @SneakyThrows
    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        customers=executeQuery("SELECT * FROM customer",result->{
            List<Customer> cust = new ArrayList<>();
            while (result.next())
                cust.add(new Customer(result.getLong("id"),
                        result.getString("name"),
                        result.getString("eMail")));
            return cust;
        });

        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        if (checkExist(eMail))
            return false;
        else
            executeUpdate("INSERT INTO CUSTOMER (name, email) " + "VALUES('" + name + "','" + eMail + "')");
        return true;
    }

    @SneakyThrows
    public void executeUpdate(String update){
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=conn.createStatement()) {
            stmt.executeUpdate(update);
        }
    }

    @SneakyThrows
    public <T> T executeQuery(String query, ResultHandler<T> handler){
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=conn.createStatement()) {
            try(ResultSet resultSet= stmt.executeQuery(query)) {
                return handler.handle(resultSet);
            }
        }
    }
    private interface ResultHandler<T> {
        T handle(ResultSet resultSet) throws SQLException;
    }

    //дополнительные методы
    @SneakyThrows
    public void deleteAll() {
        executeUpdate("DELETE FROM CUSTOMER");
    }

    @SneakyThrows
    public boolean checkExist(String eMail) {
        return executeQuery("SELECT COUNT(*) FROM CUSTOMER WHERE EMAIL='"+eMail+"'",result->{
            result.next();
            return result.getInt("COUNT(*)")==1;
        });
    }


}


