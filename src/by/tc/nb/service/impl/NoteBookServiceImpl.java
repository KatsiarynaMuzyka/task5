package by.tc.nb.service.impl;

import by.tc.nb.bean.entity.Note;
import by.tc.nb.dao.FactoryDao;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;

import java.util.List;

public class NoteBookServiceImpl implements NoteBookService {

	@Override
	public void add(String note, String date, int id) throws ServiceException {
		if (note == null || note.equals("") || id < 0) {
			throw new ServiceException("Incorrect parameters");
		}
		FactoryDao.getInstance().getNoteBookDAO().add(note,date, id);
	}

	@Override
	public List<Note> findByContent(String note, int id) throws ServiceException {
		if (note == null || note.equals("") || id < 0) {
			throw new ServiceException("Incorrect parameters");
		}
		return FactoryDao.getInstance().getNoteBookDAO().findByContent(note, id);

	}

	@Override
	public List<Note> findByDate(String date, int id) throws ServiceException {
		if (date == null || id < 0) {
			throw new ServiceException("Incorrect parameters");
		}
		return FactoryDao.getInstance().getNoteBookDAO().findByDate(date, id);
	}

	@Override
	public List<Note> show(int id) throws ServiceException, IOException {
		if (id < 0) {
			throw new ServiceException("Incorrect parameters");
		}
		return FactoryDao.getInstance().getNoteBookDAO().show(id);
	}

	@Override
	public void newNoteBook(int id) {
		if (id < 0) {
			try {
				throw new ServiceException("Incorrect parameters");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		FactoryDao.getInstance().getNoteBookDAO().newNoteBook(id);

	}

}