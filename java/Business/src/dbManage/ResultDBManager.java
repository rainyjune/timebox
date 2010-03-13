package dbManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Result;
import bean.ResultGoods;

public class ResultDBManager {

	private Statement st;
	private ResultSet rs;
	private String sql;
	private ResultGoods rg;
	public ResultDBManager() {
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
	public List<ResultGoods>getAll(int id)
	{
		List <ResultGoods>l=new ArrayList<ResultGoods>();
		if(id==0)
		{
			setSql("select * from result order by result_goods_price ASC");//…˝–Ú≈≈¡–
		}
		else
		{
			setSql("select * from result where user_id="+id+" order by result_goods_price ASC");//…˝–Ú≈≈¡–
		}
		setResultSet();
		ResultSet temp;
		try {
			
			while(rs.next())
			{
				rg=new ResultGoods();
				rg.setGoods_name(rs.getString("result_goods_name"));
				rg.setPrice(rs.getDouble("result_goods_price"));
				rg.setCreate_time(rs.getDate("result_create_time"));
				temp=ConnectionDB.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
					.executeQuery("select * from users where users_id="+rs.getInt("user_id"));
					while(temp.next())
					{
						rg.setUsername(temp.getString("users_name"));
					}
				l.add(rg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	public List<String>getAllGoodsName(int id)
	{
		List<String> l=new ArrayList<String>();
		setSql("select * from result where user_id="+id);
		setResultSet();
		try {
			while(rs.next())
			{
				
				l.add(rs.getString("result_goods_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	public boolean insert(String goods_name,double goods_price,int user_id)
	{
		setSql("insert into result(result_goods_name,result_goods_price,user_id) values('"+goods_name+"',"+goods_price+","+user_id+")");
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
