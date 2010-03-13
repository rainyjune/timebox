package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserInterceptor extends AbstractInterceptor {


	private static final long serialVersionUID = 4181133196316451692L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext ac=arg0.getInvocationContext();
		Map session=(Map)ac.getSession();
		if(session.get("id")==null||session.isEmpty())
		{
			return "stop";
		}
		return arg0.invoke();
	}

}
