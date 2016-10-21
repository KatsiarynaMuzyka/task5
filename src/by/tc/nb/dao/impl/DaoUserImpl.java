package by.tc.nb.dao.impl;

import by.tc.nb.dao.DaoUser;
import by.tc.nb.dao.connection.ConnectionsPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.tc.nb.bean.entity.User;

public class DaoUserImpl implements DaoUser {

	 @Override
	    public User logination(String login, String password) throws InterruptedException{
	        User user = null;
	        Connection connection = null;
	    
	            connection = ConnectionsPool.getInstance().getConnection();
	            try(Statement statement = connection.createStatement()) {
	                ResultSet resultSet = statement.executeQuery("SELECT id, login FROM users WHERE login='"
	                        + login + "' and password='" + password + "';");

	                if(resultSet.next()) {
	                    user = new User(resultSet.getInt(1), resultSet.getString(2));
	                }

	            } catch (SQLException e1) {
					e1.printStackTrace();
				}
	       
	            try {
	            	ConnectionsPool.getInstance().connectionReturn(connection);
	            } catch (SQLException | InterruptedException e) {
	                e.printStackTrace();
	            }
	        
	        return user;
	    }

	 @Override
	    public boolean registration(String login, String password) {
	        Connection connection = null;
	        try {
	            connection = ConnectionsPool.getInstance().getConnection();
	            try(Statement statement = connection.createStatement()) {
	                int result = statement.executeUpdate("INSERT INTO users (login,password) VALUES('"
	                        + login +"', '" + password +"');");
	                return (result != 0)? true : false;
	            }
	        } catch (InterruptedException | SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	            	ConnectionsPool.getInstance().connectionReturn(connection);
	            } catch (SQLException | InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
			return false;
	    } 
	 
	}
