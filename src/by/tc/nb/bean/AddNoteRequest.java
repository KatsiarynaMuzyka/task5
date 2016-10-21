package by.tc.nb.bean;

public class AddNoteRequest extends Request {
	private String note;
	private int id;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	 public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }

}
