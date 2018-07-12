package modtv;
import java.io.IOException;
//在這裡引用WorkDAO的方法
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class WorkDAODemo {

	public static void main(String[] args) throws SQLException, IOException {
		IWorkDAO wk = new WorkDAO();
		wk.getConnection();
		//      1
		
		wk.insert(wk.importCsv("D:/Demo/tvuser.csv"));
		//    3          2
		
		wk.printResult(wk.OutputJson(wk.getAll(wk.search()), "D:/Demo/tvuser.json"));
		//     7             6           5           4                6

		wk.closeConn();
		//     8
		
	}

}
