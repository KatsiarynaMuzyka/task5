package by.tc.nb.bean;

import java.util.List;

import by.tc.nb.bean.entity.Note;

public class ShowResponse extends Response {
	private List<Note> notes;

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
