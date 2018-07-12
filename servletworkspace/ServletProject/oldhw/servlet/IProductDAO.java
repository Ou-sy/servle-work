package iiiedu.cdpt.beans;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {

	public abstract void getConn() throws SQLException, ClassNotFoundException;

	public abstract void closeConn() throws SQLException;

//	public abstract List<ProductBean> keyWordSearch(String keyWord, String searchTag);
	public abstract List<ProductBean> keyWordSearch() throws ClassNotFoundException;

	public abstract void printSearchResult(List<ProductBean> searchResult);
	
	public abstract String showSearchResult(List<ProductBean> searchResult);

}
