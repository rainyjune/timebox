package dbManage;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
public class ConnectionDB {

                    //	Properties�����ļ���ʱ���Ҳ���·������֪����ô���أ������Ժ�Ľ���

	
	private static String driver=null;
	private static String jdbc=null;
	private static String username=null;
	private static String password=null;
	
//	private static String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
//	private static String jdbc="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=business";
//	private static String username="sa";
//	private static String password="zwc0913";	
	
	private static Connection conn;
	private ConnectionDB(){}
	public static Connection getConnection()
	{
		if(conn==null){
			setProperties();
			try
			{
				Class.forName(driver);
				conn=DriverManager.getConnection(jdbc,username,password);
			}catch(Exception e){e.printStackTrace();}
		}
		return conn;
	}
	private static void setProperties()
	{
		Properties p=new Properties();
		String path=ServletActionContext.getServletContext().getRealPath("/")+"WEB-INF\\classes\\properties";
		try
		{
			p.load(new FileInputStream(new File(path+"\\db.properties")));
			driver=p.getProperty("driver");
			jdbc=p.getProperty("jdbc");
			username=p.getProperty("username");
			password=p.getProperty("password");
		}catch(Exception e){e.printStackTrace();}
	}
}
