package action;

import java.util.Date;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import dbManage.GoodsDBManager;

public class AddGoods extends ActionSupport {

	private static final long serialVersionUID = 7567516613101463253L;
	private String goods_name;
	private double goods_price;
	private Date goods_endtime;
	private String goods_text;
	private GoodsDBManager goodsM;

	public AddGoods() {
		goodsM = new GoodsDBManager();
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public Date getGoods_endtime() {
		return goods_endtime;
	}

	public void setGoods_endtime(Date goods_endtime) {
		this.goods_endtime = goods_endtime;
	}

	public String getGoods_text() {
		return goods_text;
	}

	public void setGoods_text(String goods_text) {
		this.goods_text = goods_text;
	}

	@Override
	public String execute() throws Exception {
		if (goodsM.insert(goods_name, goods_price, goods_endtime, goods_text)) {
			return Action.SUCCESS;
		}
		return Action.INPUT;
	}
}
