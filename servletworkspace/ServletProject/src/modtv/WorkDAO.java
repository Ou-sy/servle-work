package modtv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkDAO implements IWorkDAO {

	Connection conn = null;

	@Override
	public void getConnection() throws SQLException {
		String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=modtv";
		conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
		System.out.println("-[1.資料庫已連線]");

	}
	//1.連線
	@Override
	public ArrayList<String> importCsv(String pathOfFile) {
		ArrayList<String> arl1 = null;
		BufferedReader bf1 = null;

		try {
			bf1 = new BufferedReader(new InputStreamReader(new FileInputStream(pathOfFile), "UTF-8"));

			arl1 = new ArrayList<String>();

			String tempStr = bf1.readLine();
			while (tempStr != null) {
				arl1.add(tempStr);
				tempStr = bf1.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bf1 != null) {
					bf1.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// System.out.println(arl1.get(1));
		System.out.println("--[2.已取得CSV檔案]");
		return arl1;
	}
	//2.匯入CSV資料
	@Override
	public int insert(ArrayList<String> arl1) throws SQLException {
		//

		String[] tempStr3 = null;

		int updateCount = 0;
		PreparedStatement pstmt1 = conn.prepareStatement("DELETE tvuser WHERE area is not null");
		updateCount = pstmt1.executeUpdate();
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tvuser VALUES(?,?,?,?,?,?,?,?)");
		int countResult = 1;

		System.out.println("   (共有:" + arl1.size() + "筆資料)");
		while (countResult != arl1.size()) {

			tempStr3 = arl1.get(countResult).split(",");
			pstmt.setInt(1, Integer.parseInt(tempStr3[0]));
			pstmt.setString(2, tempStr3[1]);
			pstmt.setString(3, tempStr3[2]);
			pstmt.setInt(4, Integer.parseInt(tempStr3[3]));
			pstmt.setInt(5, Integer.parseInt(tempStr3[4]));
			pstmt.setFloat(6, Float.parseFloat(tempStr3[5]));
			pstmt.setInt(7, Integer.parseInt(tempStr3[6]));
			pstmt.setFloat(8, Float.parseFloat(tempStr3[7]));
			updateCount = pstmt.executeUpdate();
			countResult++;

		}
		System.out.println("--[3.匯入資料庫成功]");
		return updateCount;
	}
	//3.INSERT所有資料進SQL
	@Override
	public String search() throws SQLException {
		String str2 = null;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("\n4.請輸入查詢縣市：");
			String inputStr = input.readLine(); // 輸入的來源
			if (inputStr.length() >= 2) {       // 有輸入二個字以上
				str2 = inputStr.substring(0, 2);// 取前二字
				if (!inputStr.matches("[0-9]+")) // 非數字、null
				{
					str2 = str2.replace("台", "臺"); // 台-臺轉換
					System.out.println("    您查詢的縣市為：" + str2 + "\n");					
				} else {                        //輸入數字 =null
					System.out.println("    您輸入的名稱有誤，為您查詢全國資料\n");
					str2 = null;
				}
			} else {                           //直接enter =null
				System.out.println("    您未輸入縣市名稱，為您查詢全國資料\n");
				str2 = null;
			}
		} catch (IOException e) {}
		return str2;
	}
	//4.輸入查詢條件
	@Override
	public List<workVO> getAll(String str2) throws SQLException {
		workVO wk = null;
		List<workVO> wks = new ArrayList<workVO>();
		if (str2 == null) {

			PreparedStatement pstmt = conn.prepareStatement("SELECT * from tvuser ORDER BY area DESC");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wk = new workVO();
				wk.setDate(rs.getInt("date"));
				wk.setCompany(rs.getString("company"));
				wk.setArea(rs.getString("area"));
				wk.setAlluser(rs.getInt("alluser"));
				wk.setSubuser(rs.getInt("subuser"));
				wk.setShare(rs.getFloat("share"));
				wk.setModuser(rs.getInt("moduser"));
				wk.setModshare(rs.getFloat("modshare"));
				wks.add(wk);
			}
		} else {
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM tvuser WHERE area LIKE '" + str2 + "%'  ORDER BY area DESC");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wk = new workVO();
				wk.setDate(rs.getInt("date"));
				wk.setCompany(rs.getString("company"));
				wk.setArea(rs.getString("area"));
				wk.setAlluser(rs.getInt("alluser"));
				wk.setSubuser(rs.getInt("subuser"));
				wk.setShare(rs.getFloat("share"));
				wk.setModuser(rs.getInt("moduser"));
				wk.setModshare(rs.getFloat("modshare"));
				wks.add(wk);
			}
		}
		System.out.println("--[5.SQL搜尋完成]");
		return wks;
	}
	//5.從SQL查詢資料
	@Override
	public List<workVO> OutputJson(List<workVO> wks, String outFilePath) throws IOException {
		FileWriter fr1 = new FileWriter(outFilePath);
		BufferedWriter bw1 = new BufferedWriter(fr1);

		int countNum = 0;
		String tempStrOfFile = "[";
		for (workVO temp : wks) {
			tempStrOfFile = tempStrOfFile + "\n{\n\"民國年月\":" + temp.getDate() + "," + "\"地區\":" + "\"" + temp.getArea()
					+ "\",\n" + "\"公司\":" + "\"" + temp.getCompany() + "\",\n" + "\"訂戶數\":" + temp.getSubuser() + ",\n"
					+ "\"總戶數\":" + temp.getAlluser() + ",\n" + "\"佔有率\":" + temp.getShare() + ",\n" + "\"數位機上盒訂戶數\":"
					+ temp.getModuser() + ",\n" + "\"數位機上盒普及率\":" + temp.getModshare() + "\n}";

			if (countNum < wks.size() - 1) {            //最後加逗號
				tempStrOfFile = tempStrOfFile + ",";
			}
			countNum++;
		}
		tempStrOfFile = tempStrOfFile + "\n]";
		bw1.write(tempStrOfFile);
		bw1.flush();
		bw1.close();
		System.out.println("--[6.JSON存檔完成]");
		return wks;

	}
	//6.以JSON格式匯出查詢結果
	@Override
	public void printResult(List<workVO> wks) {
		System.out.println("\n7.資料顯示共有" + wks.size() + "家公司");
		for (workVO temp : wks) {
			System.out.print(temp.getDate() + ",");
			System.out.print(temp.getCompany() + ",");
			System.out.print(temp.getArea() + ",");
			System.out.print(temp.getAlluser() + ",");
			System.out.print(temp.getSubuser() + ",");
			System.out.print(temp.getShare() + ",");
			System.out.print(temp.getModuser() + ",");
			System.out.println(temp.getModshare() + ",");
		}
	}
	//7.先看一下查詢結果
	@Override
	public void closeConn() throws SQLException {
		conn.close();
		System.out.println("-[8.資料庫已離線]");
	}
	//8.結束連線
}
