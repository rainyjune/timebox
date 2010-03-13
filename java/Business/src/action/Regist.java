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
		jmail.setTitle("��ӭ����ٱ�վ");
		String content="<table>" +
							"<tr>" +
								"<tr><td>����û�����</td>" +"<td>"+user.getName()+"</td></tr>" +
								"<tr><td>������룺</td>" +"<td>"+user.getPassword()+"</td></tr>" +
								"<tr><td>������䣺</td>" +"<td>"+user.getEmail()+"</td></tr>" +
								"<tr><td>��ʾ��</td>" +"<td><font color=red>���ܺ��Լ���ע����Ϣ</font></td></tr>" +
							"</tr>" +
						"</table>";
		jmail.setContent(content);
		jmail.setTo(user.getEmail());
		
		if(userDB.exist(user.getName()))
		{
			this.addFieldError("regist.fail", "�û����Ѿ�ע������뻻����һ����");
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
				this.addFieldError("regist.fail", "ע��ʧ�ܣ�");
				return this.INPUT;
			}
		}else
		{
			this.addFieldError("regist.fail", "�ʼ�ϵͳ�����⣡���Ժ���ע��");
			return this.INPUT;
		}
	}

//	@Override
//	public void validate() {
//		// TODO Auto-generated method stub
//		super.validate();
//	}

}
