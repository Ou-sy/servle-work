package bookpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookpage.bookBean;

public class bookDAO implements IBookDAO, Serializable {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=Servlet_HW";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "passw0rd";

	Connection conn = null;

	// 1.連線
	@Override
	public void getConnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		if (conn != null) {
			System.out.println("-[1.資料庫已連線]");
		}

	}

	// 2.輸入查詢條件
	@Override
	public String search() throws SQLException {
		String bookId1 = null;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("\n4.請輸入書籍編號：");
			bookId1 = input.readLine(); // 輸入的來源
			if (bookId1 != null) {
				System.out.println("    您查詢的ID為：" + bookId1 + "\n");
			} else { // 直接enter =null
				System.out.println("    您未輸入縣市名稱，為您查詢全國資料\n");
				bookId1 = "1";
			}
		} catch (IOException e) {
		}
		return bookId1;
	}

	// 3.從SQL查詢資料
	@Override
	public List<bookBean> getAll(String search) {
		bookBean bB = null;
		List<bookBean> bookData = new ArrayList<bookBean>();

		try {

			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM Product WHERE Product_ID = '" + search + "'");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bB = new bookBean();
				bB.setProduct_ID(rs.getInt("Product_ID"));
				bB.setBook_ISBN(rs.getInt("Book_ISBN"));
				bB.setProduct_name(rs.getString("Product_name"));
				bB.setBook_name_zh(rs.getString("Book_name_zh"));
				bB.setBook_name_ori(rs.getString("Book_name_ori"));
				bB.setAuthor_zh(rs.getString("Author_zh"));
				bB.setAuthor_ori(rs.getString("Author_ori"));
				bB.setTranslator_zh(rs.getString("Translator_zh"));
				bB.setTranslator_ori(rs.getString("Translator_ori"));
				bB.setBook_lang(rs.getString("Book_lang"));
				bB.setBook_publisher(rs.getString("Book_publisher"));
				bB.setBook_publish_date(rs.getString("Book_publish_date"));
				bB.setBook_price(rs.getInt("Book_price"));
				bB.setBook_stockNum(rs.getInt("Book_stockNum"));
				bB.setBook_series(rs.getString("Book_series"));
				bB.setBook_pages(rs.getInt("Book_pages"));
				bB.setBook_version(rs.getString("Book_version"));
				bB.setBook_size(rs.getString("Book_size"));
				bB.setBook_weight(rs.getInt("Book_weight"));
				bB.setProduct_img(rs.getString("Product_img"));
				bB.setBook_skin(rs.getString("Book_skin"));
				bB.setBook_menu(rs.getString("Book_menu"));
				bB.setBook_intro(rs.getString("Book_intro"));
				bookData.add(bB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("--[5.SQL搜尋完成]");
		return bookData;
	}

	// 4以JSON格式匯出查詢結果

	// 7.先看一下查詢結果
	@Override
	public void printResult(List<bookBean> bookData) {
		for (bookBean temp : bookData) {
			System.out.print(temp.getProduct_ID() + ",");
			System.out.print(temp.getBook_ISBN() + ",");
			System.out.print(temp.getProduct_name() + ",");
			System.out.print(temp.getBook_name_zh() + ",");
			System.out.print(temp.getBook_name_ori() + ",");
			System.out.print(temp.getAuthor_zh() + ",");
			System.out.print(temp.getAuthor_ori() + ",");
			System.out.print(temp.getTranslator_zh() + ",");
			System.out.print(temp.getTranslator_ori() + ",");
			System.out.print(temp.getBook_lang() + ",");
			System.out.print(temp.getBook_publisher() + ",");
			System.out.print(temp.getBook_publish_date() + ",");
			System.out.print(temp.getBook_price() + ",");
			System.out.print(temp.getBook_stockNum() + ",");
			System.out.print(temp.getBook_series() + ",");
			System.out.print(temp.getBook_pages() + ",");
			System.out.print(temp.getBook_version() + ",");
			System.out.print(temp.getBook_size() + ",");
			System.out.print(temp.getBook_weight() + ",");
			System.out.print(temp.getProduct_img() + ",");
			System.out.print(temp.getBook_skin() + ",");
			System.out.print(temp.getBook_menu() + ",");
			System.out.print(temp.getBook_intro() + ",");
		}
	}

	// 8.結束連線
	@Override
	public void closeConn() throws SQLException {
		conn.close();
		System.out.println("-[8.資料庫已離線]");
	}

}
