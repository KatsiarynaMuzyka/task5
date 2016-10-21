package by.tc.nb.command.impl;

import by.tc.nb.bean.LoginationRequest;
import by.tc.nb.bean.LoginationResponce;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.User;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.UserService;
import by.tc.nb.service.exception.ServiceException;

public class Logination implements Command {
	@Override
	public Response execute(Request request) throws CommandException, ServiceException {
		LoginationRequest req = null;

		if (request instanceof LoginationRequest) {
			req = (LoginationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		String login = req.getLogin();
		String password = req.getPassword();

		ServiceFactory service = ServiceFactory.getInstance();
		UserService userService = service.getUserService();

		userService.authorization(login, password);

		LoginationResponce response = new LoginationResponce();

		User currentUser = null;

		currentUser = userService.authorization(login, password);

		if (currentUser == null) {
			response.setErrorStatus(true);
			response.setErrorMessage("User doesn't exist");
			return response;
		} else {
			response.setErrorStatus(false);
			response.setErrorMessage("Completed!");
			response.setUser(currentUser);
			return response;
		}
	}

}
