package action;

import jmail.JMail;
import bean.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dbManage.ConnectionDB;
import dbManage.UsersDBManager;

public class Regist extends ActionSupport 
{
	private User user;
	private UsersDBManager userDB;
	private JMail jmail;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {		
		userDB=new UsersDBManager();
		jmail=new JMail();
		jmail.setTitle("欢迎你光临本站");
		String content="<table>" +
							"<tr>" +
								"<tr><td>你的用户名：</td>" +"<td>"+user.getName()+"</td></tr>" +
								"<tr><td>你的密码：</td>" +"<td>"+user.getPassword()+"</td></tr>" +
								"<tr><td>你的邮箱：</td>" +"<td>"+user.getEmail()+"</td></tr>" +
								"<tr><td>提示：</td>" +"<td><font color=red>保管好自己的注册信息</font></td></tr>" +
							"</tr>" +
						"</table>";
		jmail.setContent(content);
		jmail.setTo(user.getEmail());
		
		if(userDB.exist(user.getName()))
		{
			this.addFieldError("regist.fail", "用户名已经注册过，请换另外一个！");
			return this.INPUT;
		}
		
		
		if(jmail.sendToYou())
		{
			boolean result=userDB.insert(user.getName(), user.getPassword(), user.getEmail());
			if(result)
			{
				return this.SUCCESS;
			}
			else
			{
				this.addFieldError("regist.fail", "注册失败！");
				return this.INPUT;
			}
		}else
		{
			this.addFieldError("regist.fail", "邮件系统出问题！请稍候再注册");
			return this.INPUT;
		}
	}

//	@Override
//	public void validate() {
//		// TODO Auto-generated method stub
//		super.validate();
//	}

}
