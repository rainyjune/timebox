package jaunty.beyes;

/**
* 分类结果
*/
public class ClassifyResult 
{
	public double p;//分类的概率
	public String classification;//分类
	
	public ClassifyResult()
	{
		this.p = 0;
		this.classification = null;
	}
}
