package by.tc.nb.service;

import by.tc.nb.bean.entity.Note;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.util.List;

public interface NoteBookService {

	void newNoteBook(int id);

	void add(String note, String date,int id) throws ServiceException;

	List<Note> findByContent(String note,int id) throws ServiceException;

	List<Note> findByDate(String date,int id) throws ServiceException;

	List<Note> show(int id) throws ServiceException, IOException;

}
