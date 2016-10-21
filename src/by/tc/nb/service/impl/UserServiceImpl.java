package by.tc.nb.service.impl;

import by.tc.nb.bean.entity.User;
import by.tc.nb.dao.FactoryDao;
import by.tc.nb.service.UserService;
import by.tc.nb.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	@Override
	public User authorization(String login, String password) {
		if (login == null || login.equals("") || password == null || password.equals("")) {
		}
		try {
			return FactoryDao.getInstance().getUserDAO().logination(login, password);
		} catch (InterruptedException e) {
			return null;
		}
	}

	@Override
	public boolean registration(String login, String password) throws ServiceException {
		if (login == null || login.equals("") || password == null || password.equals("")) {
			throw new ServiceException("Invalid parameters");
		}
		return FactoryDao.getInstance().getUserDAO().registration(login, password);
	}
}
