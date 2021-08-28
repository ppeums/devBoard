import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

public class File2DB {

	public static void main(String args[]) {
		try {
			ThreadFile threadFile = new ThreadFile();
			Thread t = new Thread(threadFile);
			t.start();
			Scanner sc = new Scanner(System.in);
			sc.next();
			sc.close();
			t.interrupt();
		} catch (Exception e) {
			System.out.println("end");
		}
	}
}

class ThreadFile implements Runnable {
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				// File
				BufferedReader bin = null;
				FileReader freader = null;

				// DB
				Connection conn = null;
				Statement stmt = null;

				try {

					// File 읽기
					bin = new BufferedReader(
							new FileReader("C:/KERIS/test.txt"));
					StringTokenizer st = null;
					String b = null;

					// DB 연결
					Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클
																		// JDBC
																		// 드라이버
																		// 세팅
					conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:ORA9", "sinsul",
							"sinsul");

					stmt = conn.createStatement();

					// File로 부터 한줄씩 데이터를 읽어 들임
					while ((b = bin.readLine()) != null) {
						// Stringtokenizer를 이용해 공백마다 토큰으로 분리
						st = new StringTokenizer(b, " "); // 따옴표(")사이 공백 하나

						String str[] = new String[5];

						int i = 0;

						// 각 분리된 토큰을 배열에 넣음
						while (st.hasMoreTokens()) {
							str[i++] = st.nextToken();
						}

						// DB에 insert
						String squery = null;

						squery = "insert into TB_TEST(col1,col2,col3,col4,col5)values('"
								+ str[0]
								+ "','"
								+ str[1]
								+ "','"
								+ str[2]
								+ "','" + str[3] + "','" + str[4] + "')";

						// null값이 어떤 필드에도 없을때 쿼리수행
						// 여기서는 txt파일내 빈줄이 있을때 DB에 넣지 않기 위함
						if (str[0] != null || str[1] != null || str[2] != null
								|| str[3] != null || str[4] != null)
							stmt.executeUpdate(squery);
					}

					System.out.println("DB insert 완료");

					// usleep(100); 시간 지연

				} catch (ClassNotFoundException cnfe) {
					System.out.println("클래스 찾을 수 없음 :" + cnfe);
				} catch (SQLException se) {
					System.out.println("SQL Exception :" + se);
				} catch (FileNotFoundException fnfe) {
					System.out.println("파일을 찾을 수 없음 :" + fnfe);
				} catch (IOException ioe) {
					System.out.println("파일 입출력 오류 :" + ioe);
				} finally {
					// DB 관련 close
					try {
						stmt.close();
					} catch (Exception ee) {
					}

					try {
						conn.close();
					} catch (Exception eee) {
					}

					// file 관련 close
					try {
						bin.close();
					} catch (Exception e) {
						System.out.println("오류 :" + e);
					}
				}
				Thread.sleep(3000);
			}
		} catch (InterruptedException e1) {
			System.out.println("입력종료");
		}
	}
}
//
//import java.io.*;
//import java.util.*;
//import java.sql.*;
//
//public class File2DB implements Runnable {
//
//public static void main(String args[])
//{
//		FileReader freader = null;
//
//		//DB
//		Connection conn = null;
//		Statement stmt = null;
//
//		try{
//
//				//DB 연결
//				Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클 JDBC 드라이버 세팅
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORA9","sinsul","sinsul");
//
//				stmt = conn.createStatement();
//				while(true) {
//					try {
//						while (!Thread.currentThread().isInterrupted()) { 
//							Thread.sleep(3000);
//							new File2DB().execute(stmt); 
//						}
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}catch(ClassNotFoundException cnfe){
//			System.out.println("클래스 찾을 수 없음 :" +cnfe);
//			}catch(SQLException se){
//			System.out.println("SQL Exception :"+se);
//			}catch(FileNotFoundException fnfe){
//			System.out.println("파일을 찾을 수 없음 :" +fnfe);
//			}catch(IOException ioe){
//			System.out.println("파일 입출력 오류 :"+ioe);
//			}
//			finally
//			{
//				//DB 관련 close
//				try{stmt.close();}catch(Exception ee){}
//
//				try{conn.close();}catch(Exception eee){}
//
//			}
//		}
//
//		private void execute(Statement stmt) throws IOException, SQLException {
//			//File
//			BufferedReader bin = null;
//			try{
//				//File 읽기
//				bin = new BufferedReader( new FileReader("C:/KERIS/test.txt"));
//				StringTokenizer st = null;
//				String b = null;
//				
//				//File로 부터 한줄씩 데이터를 읽어 들임
//				while((b = bin.readLine())!= null)
//				{
//					//Stringtokenizer를 이용해 공백마다 토큰으로 분리
//					st = new StringTokenizer(b," "); // 따옴표(")사이 공백 하나
//	
//					String str[] = new String[5];
//					
//					int i = 0;
//	
//					//각 분리된 토큰을 배열에 넣음
//					while(st.hasMoreTokens()){
//						str[i++] = st.nextToken();
//					}
//	
//					//DB에 insert
//					String squery = null;
//	
//					squery = "insert into TB_TEST(col1,col2,col3,col4,col5)values('"
//					+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+str[4]+"')";
//	
//					//null값이 어떤 필드에도 없을때 쿼리수행
//					//여기서는 txt파일내 빈줄이 있을때 DB에 넣지 않기 위함
//					if(str[0]!= null||str[1]!=null||str[2]!=null||str[3]!=null||str[4]!=null)
//						stmt.executeUpdate(squery);
//				}
//				System.out.println("DB insert 완료");
//			}catch(IOException ioe){
//				System.out.println("파일 입출력 오류 :"+ioe);
//			}catch(SQLException ioe) {
//				System.out.println("디비 입출력 오류 :"+ioe);
//			}
//			finally
//			{
//				//file 관련 close
//				try{bin.close();}catch(Exception e){System.out.println("오류 :"+ e);}
//			}
//		}
//
//	@Override
//	public void run() {
//
//	}
//
//
//
//
//
//
//
//}