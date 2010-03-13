package bean;

public class Ad {
	private String img;
	private String company;
	private String text;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if(text==null)
		{
			this.text="";
		}
		else
		{
			this.text = text;
		}
		
	}

}
