package action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OutBasket extends ActionSupport {
	private int goods_id;
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	@Override
	public String execute() throws Exception {
		List<Integer> l=(List<Integer>)ActionContext.getContext().getSession().get("basket");
		l.remove(goods_id);
		ActionContext.getContext().getSession().put("basket",l);
		return this.SUCCESS;
	}
}
