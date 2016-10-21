package by.tc.nb.command.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import by.tc.nb.bean.AddNoteRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

public class AddNewNote implements Command {
	private Date dateD = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private String date = dateFormat.format(dateD);

	@Override
	public Response execute(Request request) throws CommandException {
		AddNoteRequest req = null;
		if (request instanceof AddNoteRequest) {
			req = (AddNoteRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

		String addingNote = req.getNote();
		try {
			nbService.add(addingNote, date, req.getId());

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		Response response = new Response();
		response.setErrorStatus(false);
		response.setResultMessage("All OK!");

		return response;
	}

}
