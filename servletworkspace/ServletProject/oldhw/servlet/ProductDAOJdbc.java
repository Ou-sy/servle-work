package iiiedu.cdpt.beans.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import iiiedu.cdpt.beans.IProductDAO;
import iiiedu.cdpt.beans.ProductBean;

public class ProductDAOJdbc implements IProductDAO, Serializable {

	private String keywordjava, searchtag;
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=Servlet_HW";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "passw0rd";

	Connection conn = null;

	// public static void main(String[] args) {
	//
	// ProductDAOJdbc productDAO = new ProductDAOJdbc();
	//
	// productDAO.printSearchResult(productDAO.keyWordSearch("本書", "書名"));
	// }

	public ProductDAOJdbc() {
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

	// private static final String SELECT_ISBN = "select * from Product where
	// Book_ISBN like '%?%'";
	private static final String SELECT_ISBN = "select * from Product where Book_ISBN like ?";
	private static final String SELECT_PRODUCT_NAME = "select * from Product where product_name like ? or Book_name_zh  like ?  or Book_name_ori like ?";
	private static final String SELECT_AUTHOR = "select * from Product where Author_zh like ? or Author_ori  like ?  or Translator_zh like ?";

	@Override
	// public List<ProductBean> keyWordSearch(String keyWord, String searchTag) {
	public List<ProductBean> keyWordSearch() throws ClassNotFoundException {

		ArrayList<ProductBean> searchResult = new ArrayList<ProductBean>();
		ProductBean product = null;
		try {
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			getConn();

			keywordjava = "%" + keywordjava + "%";

			if ("bookISBN".equals(searchtag)) {
				System.out.println("ISBN 查詢");
				pstmt = conn.prepareStatement(SELECT_ISBN);
				pstmt.setString(1, keywordjava);
			} else if ("authorName".equals(searchtag)) {
				System.out.println("作者 查詢");
				pstmt = conn.prepareStatement(SELECT_AUTHOR);
				pstmt.setString(1, keywordjava);
				pstmt.setString(2, keywordjava);
				pstmt.setString(3, keywordjava);
			} else {
				System.out.println("書名查詢");
				pstmt = conn.prepareStatement(SELECT_PRODUCT_NAME);
				pstmt.setString(1, keywordjava);
				pstmt.setString(2, keywordjava);
				pstmt.setString(3, keywordjava);
			}

			rs = pstmt.executeQuery();

			System.out.println(rs);

			while (rs.next()) {

				product = new ProductBean();

				product.setAuthor_ori(rs.getString("author_ori"));
				product.setAuthor_zh(rs.getString("author_zh"));
				product.setBook_ISBN(rs.getString("book_ISBN"));
				product.setBook_intro(rs.getString("book_intro"));
				product.setBook_lang(rs.getString("book_lang"));
				product.setBook_menu(rs.getString("book_menu"));
				product.setBook_name_ori(rs.getString("book_name_ori"));
				product.setBook_name_zh(rs.getString("book_name_zh"));
				product.setBook_pages(rs.getInt("book_pages"));
				product.setBook_price(rs.getInt("book_price"));
				product.setBook_publish_date(rs.getDate("book_publish_date"));
				product.setBook_publisher(rs.getString("book_publisher"));
				product.setBook_series(rs.getString("book_series"));
				product.setBook_size(rs.getString("book_size"));
				product.setBook_skin(rs.getString("book_skin"));
				product.setBook_stockNum(rs.getInt("book_stockNum"));
				product.setBook_tag(rs.getString("book_tag"));
				product.setBook_version(rs.getString("book_version"));
				product.setBook_weight(rs.getInt("book_weight"));
				product.setE_book_file(rs.getBytes("e_book_file"));
				product.setProduct_ID(rs.getInt("product_ID"));
				product.setProduct_img(rs.getBytes("product_img"));
				product.setProduct_name(rs.getString("product_name"));
				product.setTranslator_ori(rs.getString("translator_ori"));
				product.setTranslator_zh(rs.getString("translator_zh"));

				searchResult.add(product);

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

		return searchResult;
	}

	@Override
	public void printSearchResult(List<ProductBean> searchResult) {

		for (ProductBean tempProduct : searchResult) {

			System.out.print(tempProduct.getAuthor_ori() + ",");
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
			System.out.print(tempProduct.getBook_tag() + ",");
			System.out.print(tempProduct.getBook_version() + ",");
			System.out.print(tempProduct.getBook_weight() + ",");
			System.out.print(tempProduct.getE_book_file() + ",");
			System.out.print(tempProduct.getProduct_ID() + ",");
			System.out.print(tempProduct.getProduct_img() + ",");
			System.out.print(tempProduct.getProduct_name() + ",");
			System.out.print(tempProduct.getTranslator_ori() + ",");
			System.out.println(tempProduct.getTranslator_zh());

		}
		// System.out.println("searchtag:" + searchtag);
	}

	public String getKeywordjava() {
		return keywordjava;
	}

	public void setKeywordjava(String keywordjava) {
		this.keywordjava = keywordjava;
	}

	public String getSearchtag() {
		return searchtag;
	}

	public void setSearchtag(String searchtag) {
		this.searchtag = searchtag;
	}

	@Override
	public String showSearchResult(List<ProductBean> searchResult) {

		for (ProductBean tempProduct : searchResult) {
			String[] bookInfo = new String[3];

			bookInfo[0] = tempProduct.getBook_name_zh();
			bookInfo[1] = tempProduct.getAuthor_zh();
			bookInfo[2] = tempProduct.getBook_ISBN();

			String bookInfos = "書名:" + bookInfo[0] + " 作者:" + bookInfo[1] + " ISBN:" + bookInfo[2];
			return bookInfos;
		}

		return "";

	}

}
