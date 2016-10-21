package by.tc.nb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.tc.nb.bean.entity.Note;
import by.tc.nb.dao.NoteBookDao;
import by.tc.nb.dao.connection.ConnectionsPool;

public class NoteBookDaoIml implements NoteBookDao {

	@Override
	public void newNoteBook(int id) {
		java.sql.Connection connection = null;
		try {
			connection = ConnectionsPool.getInstance().getConnection();
			try (java.sql.Statement statement = connection.createStatement()) {
				statement.executeUpdate("DELETE FROM notes WHERE owner_id=" + id + ";");
			}
		} catch (InterruptedException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionsPool.getInstance().connectionReturn(connection);
			} catch (SQLException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("dd"+ id);
	}

	@Override
	public void add(String note,String date, int id) {
		java.sql.Connection connection = null;
		try {
			connection = ConnectionsPool.getInstance().getConnection();
			try (java.sql.Statement statement = connection.createStatement()) {
				statement.executeUpdate("INSERT INTO notes(owner_id, message, date) VALUES(" + id + ",'"
						+ note + "','" + date + "');");
				System.out.println(note+" "+ date);
			}
		} catch (InterruptedException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ConnectionsPool.getInstance().connectionReturn(connection);
			} catch (SQLException | InterruptedException e) {
				System.out.println("SQLException | InterruptedException");
			}
		}

	}

	@Override
	public List<Note> findByContent(String message, int id) {
		java.sql.Connection connection = null;
		List<Note> resultNotes = new ArrayList<>();
		try {
			ResultSet resultSet = null;
			connection = ConnectionsPool.getInstance().getConnection();
			try (java.sql.Statement statement = connection.createStatement()) {
				resultSet = statement.executeQuery("SELECT date, message FROM notes WHERE owner_id=" + id
						+ " and message LIKE '%" + message + "%';");
				while (resultSet.next()) {
					resultNotes.add(new Note(resultSet.getString(1), resultSet.getString(2)));
				}
			}
		} catch (InterruptedException | SQLException e) {
			e.printStackTrace();
		}
		try {
			ConnectionsPool.getInstance().connectionReturn(connection);
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		return resultNotes;
	}

	@Override
	public List<Note> findByDate(String date, int id) {
		java.sql.Connection connection = null;
		List<Note> List = new ArrayList<>();
		try {
			ResultSet resultSet = null;
			connection = ConnectionsPool.getInstance().getConnection();
			try (java.sql.Statement statement = connection.createStatement()) {
				resultSet = statement.executeQuery(
						"SELECT date, message FROM notes WHERE owner_id=" + id + " AND date='" + date + "';");
				while (resultSet.next()) {
					List.add(new Note(resultSet.getString(1), resultSet.getString(2)));
				}
			}

		} catch (InterruptedException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionsPool.getInstance().connectionReturn(connection);
			} catch (SQLException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		return List;
	}

	@Override
	public List<Note> show(int id) {
		java.sql.Connection connection = null;
		List<Note> list = new ArrayList<>();
		try {
			ResultSet resultSet = null;
			connection = ConnectionsPool.getInstance().getConnection();
			try (java.sql.Statement statement = connection.createStatement()) {
				resultSet = statement.executeQuery("SELECT date, message FROM notes WHERE owner_id='" + id + "';");

				while (resultSet.next()) {
					list.add(new Note(resultSet.getString(1), resultSet.getString(2)));
				}
			}

		} catch (InterruptedException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionsPool.getInstance().connectionReturn(connection);
			} catch (SQLException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
}
