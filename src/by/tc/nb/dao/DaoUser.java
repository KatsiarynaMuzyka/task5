package by.tc.nb.dao;

import by.tc.nb.bean.entity.User;

public interface DaoUser {
	
	    User logination(String login, String password) throws InterruptedException;
	    boolean registration(String login, String password);
}
