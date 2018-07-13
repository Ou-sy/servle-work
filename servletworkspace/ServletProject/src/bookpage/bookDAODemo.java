package bookpage;

//在這裡引用WorkDAO的方法
import java.sql.SQLException;

public class bookDAODemo {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		IBookDAO wk = new bookDAO();
		wk.getConnection();
		// 1

		wk.printResult(wk.getBook(wk.search()));

		wk.closeConn();
		// 8

	}

}
