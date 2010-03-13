package action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddBasket extends ActionSupport{

	private int goods_id;
	private int goods_count;
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}
	@Override
	public String execute() throws Exception {
		List<Integer> l=(List<Integer>)ActionContext.getContext().getSession().get("basket");
		if(l==null)
		{
			l=new ArrayList<Integer>();
		}
		l.add(goods_id);
		ActionContext.getContext().getSession().put("basket",l);
		return this.SUCCESS;
	}
}
