package hw.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class bookSql extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 加載數據庫驅動，註冊到驅動管理器
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 數據庫連接字符串
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Servlet_HW";
			// 數據庫用戶名
			String username = "sa";
			// 數據庫密碼
			String password = "passw0rd";
			// 創建Connection連接
			Connection conn = DriverManager.getConnection(url, username, password);
			// 添加圖書信息的SQL語句
			String sql = "select * from Product";
			// 獲取Statement
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			List<bookBean> list = new ArrayList<bookBean>();
			while (resultSet.next()) {

				Book book3 = new Book();
				book3.setProductID(resultSet.getInt("ProductID"));
				book3.setBookISBN(resultSet.getInt("BookISBN"));
				book3.setProductName(resultSet.getString("BookISBN"));
				book3.setBookNameZh(resultSet.getString("BookNameZh"));
				book3.setBookNameOri(resultSet.getString("BookNameOri"));
				book3.setAuthorZh(resultSet.getString("AuthorZh"));
				book3.setAuthorOri(resultSet.getString("AuthorOri"));
				book3.setTranslatorZh(resultSet.getString("TranslatorZh"));
				book3.setTranslatorOri(resultSet.getString("TranslatorOri"));
				book3.setBookLang(resultSet.getString("BookLang"));
				book3.setBookPublisher(resultSet.getString("BookPublisher"));
				book3.setBookPublishDate(resultSet.getString("BookPublishDate"));
				book3.setBookPrice(resultSet.getInt("BookPrice"));
				book3.setBookStockNum(resultSet.getInt("BookStockNum"));
				book3.setBookSeries(resultSet.getString("BookSeries"));
				book3.setBookPages(resultSet.getInt("BookPages"));
				book3.setBookVersion(resultSet.getString("BookVersion"));
				book3.setBookSize(resultSet.getString("BookSize"));
				book3.setBookWeight(resultSet.getInt("BookWeight"));
				book3.setProductImg(resultSet.getString("ProductImg"));
				book3.setBookSkin(resultSet.getString("BookSkin"));
				book3.setBookMenu(resultSet.getString("BookMenu"));
				book3.setBookIntro(resultSet.getString("BookIntro"));
				list.add(book3);

			}
			
			System.out.println(list);

			String sql2 = "select * from Author";
			Statement statement2 = conn.createStatement();

			ResultSet resultSet2 = statement2.executeQuery(sql2);

			List<bookBean> list2 = new ArrayList<bookBean>();
			while (resultSet2.next()) {
				Book book2 = new Book();
				book2.setAuthorID(resultSet2.getInt("AuthorID"));
				book2.setNameZh(resultSet2.getString("NameZh"));
				book2.setNameOri(resultSet2.getString("NameOri"));
				book2.setNickName(resultSet2.getString("NickName"));
				book2.setIntroAuthor(resultSet2.getString("IntroAuthor"));
				book2.setIntroTranslator(resultSet2.getString("IntroTranslator"));
				list.add(book2);
			}
			request.setAttribute("list2", list2);
			resultSet.close();
			statement.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("book_list.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}