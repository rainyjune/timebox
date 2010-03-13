package dbManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Action;


public class ActionDBManager {
	private Statement st;
	private ResultSet rs;
	private String sql;
	private Action action;
	public ActionDBManager() {
		try
		{
			st=ConnectionDB.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}catch(Exception e){e.printStackTrace();}
	}
	private void setResultSet()
	{
		try
		{
			rs = st.executeQuery(sql);
		} catch (Exception e) {e.printStackTrace();}
	}
	public List<Action>getAll()
	{
		List <Action>actions=new ArrayList<Action>();
		setSql("select * from action");
		setResultSet();
		try {
			while(rs.next())
			{
				action=new Action();
				action.setText(rs.getString("action_text"));
				action.setEndtime(rs.getDate("action_endtime"));
				actions.add(action);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actions;
	}
	public boolean insert(String text,Date endtime)
	{
		setSql("insert into action(action_text,action_endtime) values('"+text+"',"+endtime);
		try {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
