package action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dbManage.ResultDBManager;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


public class Chart extends ActionSupport {

	private JFreeChart chart;
	private ResultDBManager resultM;
	private int id;
	public JFreeChart getChart()
	{
		id=(Integer)ActionContext.getContext().getSession().get("id");
		resultM=new ResultDBManager();
		//chart=ChartFactory.createPieChart3D("产品消费统计表", getDateSet(), true, true, false);
		chart=ChartFactory.createBarChart3D("商品消费统计表", "", "商品数量", getBarDataset(), PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
	private CategoryDataset getBarDataset()
	{
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();

		HashMap map=new HashMap();
		List l=(ArrayList)resultM.getAllGoodsName(id);
		Object object;
		for(int i=0;i<l.size();i++)
		{
			object=l.get(i);
			if(map.containsKey(object))
			{
				map.put(object, (Integer)map.get(object)+1);
			}
			else
			{
				map.put(object, 1);
			}
		}
		for(Iterator i=map.keySet().iterator();i.hasNext();)
		{
			String  o=(String)i.next();
			dataset.addValue((Integer)map.get(o), "", o);
		}
		
		return dataset;
	}
	private DefaultPieDataset getDateSet()
	{
		return null;
	}
}
