package com.smart4j.framework.ds.impl;

import javax.sql.DataSource;
import com.smart4j.framework.ConfigHelper;
import com.smart4j.framework.ds.DataSourceFactory;

/**
 * @ClassName: AbstractDataSourceFactory
 * @Description: ��������Դ����
 * @author BEE
 * @date 2016-4-13 ����2:51:12
 */
public abstract class AbstractDataSourceFactory<T extends DataSource>implements DataSourceFactory {
	protected final String driver = ConfigHelper
			.getString("smart.framework.jdbc.driver");
	protected final String url = ConfigHelper
			.getString("smart.framework.jdbc.url");
	protected final String username = ConfigHelper
			.getString("smart.framework.jdbc.username");
	protected final String password = ConfigHelper
			.getString("smart.framework.jdbc.password");

	@Override
	public final T getDataSource() {
		// ��������Դ����
		T ds = createDataSource();
		// ���û�������
		setDriver(ds, driver);
		setUrl(ds, url);
		setUsername(ds, username);
		setPassword(ds, password);
		// ���ø߼�����
		setAdvancedConfig(ds);
		return ds;
	}

	public abstract T createDataSource();

	public abstract void setDriver(T ds, String driver);

	public abstract void setUrl(T ds, String url);

	public abstract void setUsername(T ds, String username);

	public abstract void setPassword(T ds, String password);

	public abstract void setAdvancedConfig(T ds);
}
