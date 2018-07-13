package bookpage;

import java.io.IOException;
//Interface DAO 介面：所有存取資料庫的方法，透過DAO實作
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IBookDAO {
	public void getConnection() throws SQLException, ClassNotFoundException;// 1.連線

	public String search() throws SQLException;// 4.輸入查詢條件

	public List<bookBean> getBook(String search);// 5.查詢書籍資料

	public void printResult(List<bookBean> bookData); // 7.顯示產品+作者資料

	public void closeConn() throws SQLException;// 8.結束連線
}