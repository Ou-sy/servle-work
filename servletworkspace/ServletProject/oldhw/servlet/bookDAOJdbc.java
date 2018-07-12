package hw.servlet;

import java.awt.print.Book;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class bookDAOJdbc implements bookDAO, Serializable {
	bookBean book1 = new bookBean();
	private String keyidjava;
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=Servlet_HW";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "passw0rd";

	Connection conn = null;
	
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		 bookDAOJdbc productDAO = new bookDAOJdbc();
		 productDAO.getConn();
		 productDAO.bookSearch();
		 ArrayList<bookBean> list1 = (ArrayList<bookBean>) productDAO.bookSearch();
		 
		 productDAO.printBookResult(list1);
//		 productDAO.printSearchResult(productDAO.printBookResult("本書", "書名"));
		 }

	@Override
	public void getConn() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		if (conn != null) {
			System.out.println("get connection!!");
		}

	}

	@Override
	public void closeConn() throws SQLException {

		if (conn != null) {
			conn.close();
			System.out.println("connection closed!!");
		}

	}

	private static final String SELECTID = "select * from Product where Product_ID = ?;";

	@Override
	// public List<ProductBean> keyWordSearch(String keyWord, String searchTag) {
	public List<bookBean> bookSearch() throws ClassNotFoundException {

		ArrayList<bookBean> list1 = new ArrayList<bookBean>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SELECTID);
			ResultSet resultSet = pstmt.executeQuery();
			
			getConn();
			String bkid = "1";
			book1.setProduct_ID(resultSet.getInt("Product_ID"));

			resultSet = pstmt.executeQuery();

			System.out.println(resultSet);

			while (resultSet.next()) {

				 book1 = new bookBean();
				book1.setProduct_ID(resultSet.getInt("ProductID"));
				book1.setBook_ISBN(resultSet.getInt("BookISBN"));
				book1.setProduct_name(resultSet.getString("BookISBN"));
				book1.setBook_name_zh(resultSet.getString("BookNameZh"));
				book1.setBook_name_ori(resultSet.getString("BookNameOri"));
				book1.setAuthor_zh(resultSet.getString("AuthorZh"));
				book1.setAuthor_ori(resultSet.getString("AuthorOri"));
				book1.setTranslator_zh(resultSet.getString("TranslatorZh"));
				book1.setTranslator_ori(resultSet.getString("TranslatorOri"));
				book1.setBook_lang(resultSet.getString("BookLang"));
				book1.setBook_publisher(resultSet.getString("BookPublisher"));
				book1.setBook_publish_date(resultSet.getString("BookPublishDate"));
				book1.setBook_price(resultSet.getInt("BookPrice"));
				book1.setBook_stockNum(resultSet.getInt("BookStockNum"));
				book1.setBook_series(resultSet.getString("BookSeries"));
				book1.setBook_pages(resultSet.getInt("BookPages"));
				book1.setBook_version(resultSet.getString("BookVersion"));
				book1.setBook_size(resultSet.getString("BookSize"));
				book1.setBook_weight(resultSet.getInt("BookWeight"));
				book1.setProduct_img(resultSet.getString("ProductImg"));
				book1.setBook_skin(resultSet.getString("BookSkin"));
				book1.setBook_menu(resultSet.getString("BookMenu"));
				book1.setBook_intro(resultSet.getString("BookIntro"));
				list1.add(book1);
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

		return list1;
	}

	@Override
	public void printBookResult(List<bookBean> searchResult) {

		for (bookBean tempProduct : searchResult) {

			System.out.print(tempProduct.getProduct_ID() + ",");
			System.out.print(tempProduct.getAuthor_zh() + ",");
			System.out.print(tempProduct.getBook_ISBN() + ",");
			System.out.print(tempProduct.getBook_intro() + ",");
			System.out.print(tempProduct.getBook_lang() + ",");
			System.out.print(tempProduct.getBook_menu() + ",");
			System.out.print(tempProduct.getBook_name_ori() + ",");
			System.out.print(tempProduct.getBook_name_zh() + ",");
			System.out.print(tempProduct.getBook_pages() + ",");
			System.out.print(tempProduct.getBook_price() + ",");
			System.out.print(tempProduct.getBook_publish_date() + ",");
			System.out.print(tempProduct.getBook_publisher() + ",");
			System.out.print(tempProduct.getBook_series() + ",");
			System.out.print(tempProduct.getBook_size() + ",");
			System.out.print(tempProduct.getBook_skin() + ",");
			System.out.print(tempProduct.getBook_stockNum() + ",");
			System.out.print(tempProduct.getBook_version() + ",");
			System.out.print(tempProduct.getBook_weight() + ",");
			System.out.print(tempProduct.getProduct_ID() + ",");
			System.out.print(tempProduct.getProduct_img() + ",");
			System.out.print(tempProduct.getProduct_name() + ",");
			System.out.print(tempProduct.getTranslator_ori() + ",");
			System.out.println(tempProduct.getTranslator_zh());

		}
		// System.out.println("searchtag:" + searchtag);
	}

	public String getkeyidjava() {
		return keyidjava;
	}

	public void setKeywordjava(String keyidjava) {
		this.keyidjava = keyidjava;
	}

	@Override
	public String showBookResult(List<bookBean> list1) {

		for (bookBean tempProduct : list1) {
			String[] bookData = new String[3];

			bookData[0] = tempProduct.getBook_name_zh();
			bookData[1] = tempProduct.getAuthor_zh();
			bookData[2] = tempProduct.getBook_publisher();

			String bookInfos = "書名:" + bookData[0] + " 作者:" + bookData[1] + " ISBN:" + bookData[2];
			return bookInfos;
		}

		return "";

	}



	



}
