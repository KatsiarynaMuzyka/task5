package by.tc.nb.controller;

import by.tc.nb.bean.RegistrationRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.UserService;
import by.tc.nb.service.exception.ServiceException;

public class Registration implements Command {

	@Override
	public Response execute(Request request) throws CommandException, ServiceException {

		RegistrationRequest req = null;

		if (request instanceof RegistrationRequest) {
			req = (RegistrationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		String login = req.getLogin();
		String password = req.getPassword();

		ServiceFactory service = ServiceFactory.getInstance();
		UserService userService = service.getUserService();

		userService.registration(login, password);
		Response response = new Response();
		response.setErrorStatus(false);
		response.setResultMessage("All OK!");
		return response;
	}

}
