package modtv;

import java.io.IOException;
//Interface DAO 介面：所有存取資料庫的方法，透過DAO實作
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IWorkDAO {
	public void getConnection() throws SQLException;//1.連線
	
	public ArrayList<String> importCsv(String pathOfFile); //2.從CSV匯入資料

	public int insert(ArrayList<String> arl1) throws SQLException;//3.INSERT資料
	
	public String search() throws SQLException;//4.輸入查詢條件

	public List<workVO> getAll(String str2) throws SQLException;//5.查詢資料
	
	public List<workVO> OutputJson(List<workVO> wks, String outFilePath) throws IOException; //6.匯出JSON資料

	public void printResult(List<workVO> wks); //7.顯示資料

	public void closeConn() throws SQLException;//8.結束連線

} 