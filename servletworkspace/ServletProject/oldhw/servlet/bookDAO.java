package hw.servlet;

import java.sql.SQLException;
import java.util.List;

public interface bookDAO {

	public abstract void getConn() throws SQLException, ClassNotFoundException;

	public abstract void closeConn() throws SQLException;

	public abstract List<bookBean> bookSearch() throws ClassNotFoundException;

	public abstract void printBookResult(List<bookBean> searchResult);
	
	public abstract String showBookResult(List<bookBean> searchResult);

}
