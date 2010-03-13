package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dbManage.AdDBManager;
import dbManage.ResultDBManager;

public class AddAd extends ActionSupport {

	private String ad_company;
	private String ad_text;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String savePath;
	
	@Override
	public String execute() throws Exception {
		if(upload==null||!upload.getName().contains("."))
		{
			this.addFieldError("failAdd", "上传文件有问题");
			return this.INPUT;
		}
		String contextPath=ServletActionContext.getServletContext().getRealPath("/"+savePath);
		String nowFile=new Date().getTime()+uploadFileName;
		try {
			FileInputStream fis=new FileInputStream(upload);
			BufferedInputStream bis=new BufferedInputStream(fis);
						
			File f=new File(contextPath+"/"+nowFile);
			FileOutputStream fos=new FileOutputStream(f);
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			byte[] b=new byte[1024];
			while(bis.read(b)!=-1)
			{
				bos.write(b);
			}
			bos.close();
			bis.close();
			fos.close();
			fis.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.addFieldError("failAdd", "添加赞助商失败！");
			return this.INPUT;
		}
		
		AdDBManager adM=new AdDBManager();
		if(adM.insert(savePath+"/"+nowFile, ad_company, ad_text))
		{
			return this.SUCCESS;
		}
		this.addFieldError("failAdd", "添加赞助商失败！");
		return this.INPUT;
	}
	public String getAd_company() {
		return ad_company;
	}
	public void setAd_company(String ad_company) {
		this.ad_company = ad_company;
	}
	public String getAd_text() {
		return ad_text;
	}
	public void setAd_text(String ad_text) {
		if(ad_text==null)
		{
			ad_text="无";
		}
		this.ad_text = ad_text;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
