package by.tc.nb.dao;

import by.tc.nb.dao.impl.NoteBookDaoIml;
import by.tc.nb.dao.impl.DaoUserImpl;

public class FactoryDao {
	 
	private final static FactoryDao instance = new FactoryDao();

	    private final NoteBookDao noteBookDAO =new NoteBookDaoIml();
	    private final DaoUser userDAO = new DaoUserImpl();

	    private FactoryDao() {
	    }

	    public static FactoryDao getInstance() {
	        return instance;
	    }

	    public NoteBookDao getNoteBookDAO() {
	        return noteBookDAO;
	    }

	    public DaoUser getUserDAO() {
	        return userDAO;
	    }
	
	
}
