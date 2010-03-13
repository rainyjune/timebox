package action;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class I18N extends ActionSupport
{
	@Override
	public String execute() throws Exception {
		System.out.println("start");
		System.out.println(Locale.getDefault().getDisplayCountry()+Locale.getDefault().getDisplayName());
		System.out.println(ActionContext.getContext().getSession().get("WW_TRANS_I18N_LOCALE"));
		System.out.println();
		String s=(String)ActionContext.getContext().getSession().get("WW_TRANS_I18N_LOCALE");
		if(s==null)
		{
			ActionContext.getContext().getSession().put("WW_TRANS_I18N_LOCALE", Locale.US);
			return this.SUCCESS;
		}
		String[] ss=s.split("_");
		Locale l=new Locale(ss[0],ss[1]);
		if(l==Locale.CHINA)
		{
			ActionContext.getContext().getSession().put("WW_TRANS_I18N_LOCALE", Locale.US);
		}
		else if(l==Locale.US)
		{
			ActionContext.getContext().getSession().put("WW_TRANS_I18N_LOCALE", Locale.CHINA);
		}
		System.out.println();
		System.out.println(Locale.getDefault().getDisplayCountry()+Locale.getDefault().getDisplayName());
		return this.SUCCESS;
	}
}
