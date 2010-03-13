package jaunty.struts1.action;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SayHelloSubmitAction extends Action{
	
	private static final Log log = LogFactory.getLog(SayHelloSubmitAction.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			log.info(BeanUtils.getProperty(actionForm, "message"));
			log.info(BeanUtils.getProperty(actionForm, "year"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		request.setAttribute("message", actionForm);
		
		return actionMapping.findForward("sayHello");
	}
}