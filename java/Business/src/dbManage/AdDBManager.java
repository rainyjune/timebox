package dbManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Ad;

public class AdDBManager {
	private Statement st;
	private ResultSet rs;
	private String sql;
	private Ad ad;

	public AdDBManager() {
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

	public List<Ad> getAll() {
		List<Ad> ads = new ArrayList<Ad>();
		setSql("select * from ad");
		setResultSet();
		try {
			while (rs.next()) {
				ad = new Ad();
				ad.setCompany(rs.getString("ad_company"));
				ad.setImg(rs.getString("ad_img"));
				ad.setText(rs.getString("ad_text"));
				ads.add(ad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ads;
	}

	public boolean insert(String img, String company, String text) {
		if (text == null) {
			text = "";
		}
		setSql("insert into ad(ad_img,ad_company,ad_text) values('" + img
				+ "','" + company + "','" + text + "')");
		try {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
