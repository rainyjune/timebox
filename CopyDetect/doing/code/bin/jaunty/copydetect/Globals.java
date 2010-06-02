package jaunty.copydetect;

/**
 * 全局常量
 * 
 * @author hellojinjie
 * @date May 9, 2010 10:59:28 PM
 */
public final class Globals {

	/**
	 * 训练集的存放路径
	 */
	public static final String TRANING_DATA_PATH = "data/TrainingData";

	public static final String CHARSET_NAME = "utf8";
	
	/**
	 * 缩放因子
	 * 
	 * XXX 这个很关键，特别是在训练集的数据变化时，必须修改这个参数。
	 */
	public static final float ZOOM_FACTOR = 4000;
}
