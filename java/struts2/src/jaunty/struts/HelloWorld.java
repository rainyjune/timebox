package jaunty.struts;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {

	private static final long serialVersionUID = 4312387899976208020L;

	private String message;
	
	public String execute() throws Exception {
		setMessage("hello world");
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
