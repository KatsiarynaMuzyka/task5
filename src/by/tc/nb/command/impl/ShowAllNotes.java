package by.tc.nb.command.impl;

import java.io.IOException;
import java.util.List;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.ShowRequest;
import by.tc.nb.bean.ShowResponse;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;


public class ShowAllNotes implements Command{

	@Override
	public Response execute(Request request) throws CommandException, ServiceException, IOException {
		
		ShowRequest req = null;
		if (request instanceof ShowRequest) {
		    req = (ShowRequest) request;
		} else {
		    throw new CommandException("Wrong request");
		}
		
		ShowResponse response = new ShowResponse();
		List<Note> result = null;
	
		ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        nbService.show(req.getId());
		
        try {
            result = nbService.show(req.getId());
        } catch (ServiceException e) {
            throw new CommandException();
        }

        response.setErrorStatus(false);
        response.setNotes(result);
        response.setResultMessage("All OK!");
        return response;
	}

}

