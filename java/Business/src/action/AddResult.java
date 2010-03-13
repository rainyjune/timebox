package action;

import java.util.ArrayList;
import java.util.List;

import bean.Goods;
import bean.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dbManage.GoodsDBManager;
import dbManage.ResultDBManager;

public class AddResult extends ActionSupport
{

	private Result result;
	private Goods goods;
	private GoodsDBManager goodsM;
	private ResultDBManager resultM;
	public AddResult()
	{
		goodsM=new GoodsDBManager();
		resultM=new ResultDBManager();
		goods=new Goods();
		result=new Result();
	}
	@Override
	public String execute() throws Exception {
		List<Integer>l=(ArrayList<Integer>)ActionContext.getContext().getSession().get("basket");
		Integer temp=(Integer)ActionContext.getContext().getSession().get("id");
		if(temp==null)
		{
			return "fail";
		}
		if(l==null)
		{
			return this.INPUT;
		}
		int id;
		for(int i=0;i<l.size();i++)
		{
			id=l.get(i);
			goods=goodsM.getOne(id);
			resultM.insert(goods.getGoods_name(), goods.getPrice(), temp);
		}
		return this.SUCCESS;
	}	
}
