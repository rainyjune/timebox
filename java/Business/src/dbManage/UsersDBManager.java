package dbManage;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UsersDBManager {
	private Statement st;
	private ResultSet rs;
	private String sql="select * from users";
	private int id;
	private User user;
	public UsersDBManager() {
		try{
			st=ConnectionDB.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}catch(Exception e){e.printStackTrace();}
	}
	private void setResultSet()
	{
		try
		{
			rs=st.executeQuery(sql);
		}catch(Exception e){e.printStackTrace();}
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public void setId(int id) {
		this.id = id;
	}
	private User getUser(String name,String password,String email)
	{	
		user=new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		return user;
	}
	private boolean doExecute()
	{
		try
		{
			st.execute(sql);
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	public User getAll(int id)
	{
		user=new User();
		setSql("select * from users where users_id="+id);
		setResultSet();
		try
		{
			while(rs.next())
			{
				user=getUser(rs.getString("users_name"),rs.getString("users_psssword"),rs.getString("users_email"));
			}
		}catch(Exception e){e.printStackTrace();}
		return user;
	}
	public String getUsername(int id)
	{
		setSql("select * from users where users_id="+id);
		setResultSet();
		try
		{
			while(rs.next())
			{
				return rs.getString("users_name");
			}
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	public User getAll(String name)
	{
		user=new User();
		setSql("select * from users where users_name="+name);
		setResultSet();
		try
		{
			while(rs.next())
			{
				getUser(rs.getString("users_name"),rs.getString("users_psssword"),rs.getString("users_email"));
			}
		}catch(Exception e){e.printStackTrace();}
		return user;
	}
	public User getAll(String name,String email)
	{
		user=new User();
		setSql("select * from users where users_name="+id+",users_email='"+email+"'");
		setResultSet();
		try
		{
			while(rs.next())
			{
				getUser(rs.getString("users_name"),rs.getString("users_psssword"),rs.getString("users_email"));
			}
		}catch(Exception e){e.printStackTrace();}
		return user;
	}
	public List<User> getAll()
	{
		List <User>users=new ArrayList<User>();
		setSql("select * from users");
		setResultSet();
		try
		{
			while(rs.next())
			{
				users.add(getUser(rs.getString("users_name"),rs.getString("users_psssword"),rs.getString("users_email")));
			}
		}catch(Exception e){e.printStackTrace();}
		return users;
	}
	public boolean delete(String name)
	{
		setSql("delete from users where where users_name="+name);
		return doExecute();
	}
	public boolean delete(int id)
	{
		setSql("delete from users where where users_id="+id);
		return doExecute();
	}
	public boolean insert(String name,String password,String email)
	{
		setSql("insert into users(users_name,users_password,users_email) values('"+name+"','"+password+"','"+email+"')");
		return doExecute();
	}
	public boolean update(String name,String password,String email)
	{
		setSql("update users set users_name="+name+",users_password="+password+",users_email="+email);
		return doExecute();
	}
	public boolean update(int id,String name,String password,String email)
	{
		setSql("update users set id="+id+",users_name="+name+",users_password="+password+",users_email="+email);
		return doExecute();
	}
	public int haveUser(String username,String password)
	{
		setSql("select * from users where users_name='"+username+"' AND users_password='"+password+"'");
		setResultSet();
		try
		{
			if(rs.next())
			{
				return rs.getInt("users_id");
			}
		}catch(Exception e){e.printStackTrace();}
		return 0;
	}
	public boolean exist(String username)
	{
		setSql("select * from users where users_name='"+username+"'");
		setResultSet();
		try
		{
			while(rs.next())
			{
				return true;
			}
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
}

