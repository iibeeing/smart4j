package com.smart4j.plugin.security;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import com.smart4j.plugin.security.realm.SmartCustomRealm;
import com.smart4j.plugin.security.realm.SmartJdbcRealm;

/**
@ClassName: SmartSecurityFilter
@Description: ��ȫ������
@author BEE 
@date 2016-4-11 ����10:46:36
 */
public class SmartSecurityFilter extends ShiroFilter {

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
		WebSecurityManager webSecurityManager = super.getSecurityManager();
		//����Realm����ͬʱ֧�ֶ��Realm���������Ⱥ�˳���ö��ŷָ�
		setRealms(webSecurityManager);
		//����Cache,���ڼ������ݿ��ѯ����������I/O����
		setCache(webSecurityManager);
	}
	
	private void setRealms(WebSecurityManager webSecurityManager){
		//��ȡsmart.plugin.security.reamls������
		String securityRealms = SecurityConfig.getRealms();
		if(securityRealms != null){
			//���ݶ��Ž��в��
			String[] securityRealmArray = securityRealms.split(",");
			if(securityRealmArray.length > 0){
				//ʹRealm�߱�Ψһ����˳����
				Set<Realm> realms = new LinkedHashSet<>();
				for(String securityRealm : securityRealmArray){
					if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
						//��ӻ���JDBC �� Realm�����������SQL��ѯ���
						addJdbcRealm(realms);
					}else if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
						//��ӻ��ڶ��ƻ���Realm����ʵ��SmartSecurity�ӿ�
						addCustomRealm(realms);
					}
				}
				
				RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
				realmSecurityManager.setRealms(realms);//����
			}
		}
	}
	
	private void addJdbcRealm(Set<Realm> realms){
		//����Լ�ʵ�ֵĻ���JDBC��Realm
		SmartJdbcRealm smartJdbcRealm = new SmartJdbcRealm();
		realms.add(smartJdbcRealm);
	}
	
	private void addCustomRealm(Set<Realm> realms){
		//��ȡsmart.plugin.security.custom.class������
		SmartSecurity smartSecurity = SecurityConfig.getSmartSecurity();
		//����Լ�ʵ�ֵ�Realm
		SmartCustomRealm smartCustomRealm = new SmartCustomRealm(smartSecurity);
		realms.add(smartCustomRealm);
	}
	
	private void setCache(WebSecurityManager webSecurityManager){
		//��ȡsmart.plugin.security.cache������
		if(SecurityConfig.isCache()){
			CachingSecurityManager cachingSecurityManager = (CachingSecurityManager)webSecurityManager;
			//ʹ�û����ڴ��CacheManager
			CacheManager cacheManager = new MemoryConstrainedCacheManager();
			cachingSecurityManager.setCacheManager(cacheManager);
		}
	}
}
