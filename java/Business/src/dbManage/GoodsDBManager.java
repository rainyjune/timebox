package dbManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Goods;

public class GoodsDBManager {
	private Statement st;
	private ResultSet rs;
	private String sql;
	private Goods goods;

	public GoodsDBManager() {
		try {
			st = ConnectionDB.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setResultSet() {
		try {
			rs = st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	private boolean doExecute() {
		try {
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Goods> getAll() {
		List<Goods> l = new ArrayList<Goods>();
		setSql("select * from goods");
		setResultSet();
		try {
			while (rs.next()) {
				goods = new Goods();
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setPrice(rs.getDouble("goods_price"));
				goods.setText(rs.getString("goods_text"));
				goods.setEndtime(rs.getDate("goods_endtime"));
				l.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<Goods> getAll(String goods_name) {
		List<Goods> l = new ArrayList<Goods>();
		setSql("select * from goods where goods_name='" + goods_name + "'");
		setResultSet();
		try {
			while (rs.next()) {
				goods = new Goods();
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setPrice(rs.getDouble("goods_price"));
				goods.setText(rs.getString("goods_text"));
				goods.setEndtime(rs.getDate("goods_endtime"));
				l.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<Goods> getAll(int goods_id) {
		List<Goods> l = new ArrayList<Goods>();
		setSql("select * from goods where goods_id=" + goods_id);
		setResultSet();
		try {
			while (rs.next()) {
				goods = new Goods();
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setPrice(rs.getDouble("goods_price"));
				goods.setText(rs.getString("goods_text"));
				goods.setEndtime(rs.getDate("goods_endtime"));
				l.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public Goods getOne(int goods_id) {
		setSql("select * from goods where goods_id='" + goods_id + "'");
		setResultSet();
		try {
			if (rs.next()) {
				goods = new Goods();
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setPrice(rs.getDouble("goods_price"));
				goods.setText(rs.getString("goods_text"));
				goods.setEndtime(rs.getDate("goods_endtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	public List<Goods> getAllLike(String goods_name) {
		List<Goods> l = new ArrayList<Goods>();
		setSql("select * from goods where goods_name like '%"
				+ goods_name.trim() + "%'");
		setResultSet();
		try {
			while (rs.next()) {
				goods = new Goods();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setPrice(rs.getDouble("goods_price"));
				goods.setText(rs.getString("goods_text"));
				goods.setEndtime(rs.getDate("goods_endtime"));
				l.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean delete(int goods_id) {
		setSql("delete from goods where goods_id=" + goods_id);
		return doExecute();
	}

	public boolean delete(String goods_name) {
		setSql("delete from goods where goods_name='" + goods_name + "'");
		return doExecute();
	}

	public boolean insert(String goods_name, double goods_price,
			Date goods_endtime, String goods_text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (goods_text == null) {
			goods_text = "";
		}
		setSql("insert into goods(goods_name,goods_price,goods_endtime,goods_text) values('"
				+ goods_name
				+ "',"
				+ goods_price
				+ ","
				+ sdf.format(goods_endtime) + ",'" + goods_text + "')");
		return doExecute();
	}

	public boolean update(String goods_name, double goods_price,
			Date goods_endtime, String goods_text) {
		if (goods_text == null) {
			goods_text = "";
		}
		setSql("update goods set goods_price=" + goods_price
				+ ",goods_endtime=" + goods_endtime + ",goods_text='"
				+ goods_text + "' where goods_name='" + goods_name + "'");
		return doExecute();
	}

	public boolean update(int goods_id, double goods_price, Date goods_endtime,
			String goods_text) {
		if (goods_text == null) {
			goods_text = "";
		}
		setSql("update goods set goods_price=" + goods_price
				+ ",goods_endtime=" + goods_endtime + ",goods_text='"
				+ goods_text + "' where goods_id='" + goods_id + "'");
		return doExecute();
	}
}