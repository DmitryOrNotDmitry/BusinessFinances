package ru.dmytrium.main.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.dmytrium.main.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Component
public class Lab3UserRepository {

    @Autowired
    private DataSource dataSource;

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from \"user\"");

            while (resultSet.next()) {
                User newUser = new User();

                newUser.setUserId(resultSet.getLong("user_id"));
                newUser.setName(resultSet.getString("user_name"));
                newUser.setEmail(resultSet.getString("email"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setCreatedAt(resultSet.getDate("created_at"));

                users.add(newUser);
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return users;
    }


    public List<User> findUsers(String userName) {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from \"user\" where user_name = ?");
            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User newUser = new User();

                newUser.setUserId(resultSet.getLong("user_id"));
                newUser.setName(resultSet.getString("user_name"));
                newUser.setEmail(resultSet.getString("email"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setCreatedAt(resultSet.getDate("created_at"));

                users.add(newUser);
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return users;
    }


    public List<Map<String, Object>> findSmthUsers(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowMap.put(columnName, columnValue);
                }

                resultList.add(rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        return resultList;
    }




}
