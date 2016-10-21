package by.tc.nb.dao.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionsPool {
    public static final ConnectionsPool instance = new ConnectionsPool();
    private BlockingQueue<Connection> cPool = new ArrayBlockingQueue<>(5);

    private ConnectionsPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < cPool.remainingCapacity(); i++) {
				cPool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/notebook?useSSL=false", "root",
						"123456"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void connectionReturn(Connection connection) throws SQLException, InterruptedException {
		if (connection == null) {
			return;
		}
		connection.setAutoCommit(true);
		cPool.put(connection);
	}

	public Connection getConnection() throws InterruptedException {
		return cPool.take();
	}

	public static ConnectionsPool getInstance() {
		return instance;
	}

	public void closePool() {
		for (Connection c : cPool) {
			try {
				c.close();
			} catch (SQLException e) {
			}
		}
	}
    
}