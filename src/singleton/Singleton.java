package singleton;
import java.sql.SQLException;

public class Singleton {
	public static void main(String[] args) throws SQLException {

		Thread thread1 = new Thread(() -> {
			Database foo = Database.getInstance();
			foo.query("SELECT from Thread 1");
		});

		Thread thread2 = new Thread(() -> {
			Database bar = Database.getInstance();
			bar.query("SELECT from Thread 2");
		});

		Thread thread3 = new Thread(() -> {
			Database bar = Database.getInstance();
			bar.query("SELECT from Thread 3");
		});

		Thread thread4 = new Thread(() -> {
			Database bar = Database.getInstance();
			bar.query("SELECT from Thread 4");
		});

		Thread thread5 = new Thread(() -> {
			Database bar = Database.getInstance();
			bar.query("SELECT from Thread 5");
		});

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class Database {
	private static Database instance;

	private Database() {
		try {
			System.out.println("Criou DataBase");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Database getInstance() {
		if (instance == null) {
			synchronized (Database.class) {
				if (instance == null) {
					instance = new Database();
				}
			}
		}
		return instance;
	}

	public void query(String sql) {
		System.out.println("Executando consulta: " + sql);
	}
}