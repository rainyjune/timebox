package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dbManage.UsersDBManager;

public class Login extends ActionSupport {

	private String username;
	private String password;
	private String rand;
	private UsersDBManager userDB;
	@Override
	public String execute() throws Exception {
		String randT=(String)ActionContext.getContext().getSession().get("rand");
		if(randT.equals(rand))
		{
			userDB=new UsersDBManager();
			int id=userDB.haveUser(username, password);
			if(id!=0)
			{
				ActionContext.getContext().getSession().put("id", id);
				return this.SUCCESS;
			}else{this.addFieldError("fail", "用户名与密码错误，请重新登录！");}
			
		}else
		{this.addFieldError("different", "你必须输入相同的验证码");}
		return this.INPUT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setRand(String rand) {
		this.rand = rand.trim().toUpperCase();
	}

	public String getRand() {
		return rand;
	}

}
