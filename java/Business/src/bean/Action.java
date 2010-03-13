package bean;

import java.util.Date;

public class Action {
	private String text;
	private Date endtime;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
