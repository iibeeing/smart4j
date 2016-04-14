package com.test;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//[users]
//shiro-xx = 201314
public class HelloShiro {

	private static final Logger logger = LoggerFactory.getLogger(HelloShiro.class);
	
	@Test
	public void TestShrio(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//��ȡ��ǰ�û�
		Subject subject = SecurityUtils.getSubject();
		//��¼
		//UsernamePasswordToken token = new UsernamePasswordToken("shiro-xx","201314");
		
		try{
			byte[] account=new byte[20];
			System.out.print("���������˻�: ");
			System.in.read(account);
			String str_account = new String(account);
			byte[] password=new byte[20];
			System.out.print("������������: ");
			System.in.read(password);
			String str_password = new String(password);
			UsernamePasswordToken token = new UsernamePasswordToken(str_account.trim(),str_password.trim());
			System.out.println("���������Ϣ�� �˻�" + str_account.trim() + ", ���� " + str_password.trim());
			subject.login(token);
		}catch (AuthenticationException e) {
			logger.info("��¼ʧ�ܣ�");
			return;
		}catch (IOException e) {
			logger.info("��¼ʧ�ܣ�");
			return;
		}
		logger.info("��¼�ɹ��� Hello " + subject.getPrincipal());
		//ע��
		subject.logout();
	}
}
