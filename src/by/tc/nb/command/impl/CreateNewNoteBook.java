package by.tc.nb.command.impl;

import by.tc.nb.bean.NewNoteBookRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

public class CreateNewNoteBook implements Command {

	@Override
	public Response execute(Request request) throws CommandException, ServiceException {
		NewNoteBookRequest req = null;

		if (request instanceof NewNoteBookRequest) {
			req = (NewNoteBookRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

		nbService.newNoteBook(req.getId());
		response.setErrorStatus(false);
		response.setResultMessage("Sucessful Finished!");

		return response;
	}

}
