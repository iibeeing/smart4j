package com.smart4j.framework.ds;

import javax.sql.DataSource;

/**
@ClassName: DataSourceFactory
@Description: 数据源工厂
@author BEE 
@date 2016-4-13 下午2:45:31
 */
public interface DataSourceFactory {
	/**
	@Description: 获取数据源
	@param @return    设定文件
	@date 创建时间：2016-4-13 下午2:45:22 
	@version 1.0
	@return DataSource    返回类型
	 */
    DataSource getDataSource();
}
