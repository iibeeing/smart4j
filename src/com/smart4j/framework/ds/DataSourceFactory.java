package com.smart4j.framework.ds;

import javax.sql.DataSource;

/**
@ClassName: DataSourceFactory
@Description: ����Դ����
@author BEE 
@date 2016-4-13 ����2:45:31
 */
public interface DataSourceFactory {
	/**
	@Description: ��ȡ����Դ
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-13 ����2:45:22 
	@version 1.0
	@return DataSource    ��������
	 */
    DataSource getDataSource();
}
