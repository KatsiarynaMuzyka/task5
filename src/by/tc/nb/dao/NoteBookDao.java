package by.tc.nb.dao;

import java.util.List;
import by.tc.nb.bean.entity.Note;

public interface NoteBookDao {

	void newNoteBook(int id);

	List<Note> findByContent(String content, int id);

	List<Note> findByDate(String date, int id);

	List<Note> show(int id);

	void add(String note, String date, int id);

}
