package com.smart4j.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.cc.util.StringUtil;

/**
* @ClassName: ClassUtil
* @Description: 类工具
* @author BEE 
* @date 2016-3-28 上午11:02:08
 */
public final class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
	/**
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 加载类，为了提高加载性能，可将isInitialized设为false
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> cls;
		try{
			cls = Class.forName(className,isInitialized,getClassLoader());
		}catch (ClassNotFoundException e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	
	/**
	 * 加载类，为了提高加载性能，可将isInitialized设为false
	 * ，则不会加载该类的静态块，使得Bean没有被初始化，如果使用则报空指针错误。
	 * 所以，如果要使用则isInitialized设为true
	 */
	public static Class<?> loadClass(String className){
		Class<?> cls;
		try{
			//System.out.println(" 正在加载 --- >" + className);
			//cls = Class.forName(className,false,getClassLoader());
			cls = Class.forName(className,true,getClassLoader());
		}catch (Exception e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	
	/**
	 * 获取指定包名下的所有类
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try{
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				if(url != null){
					String protocol = url.getProtocol();
					if(protocol.equals("file")){
						String packagePath = url.getPath().replaceAll("%20", " ");
						addClass(classSet,packagePath,packageName);
					}else if(protocol.equals("jar")){
						//System.out.println(" ------------- > " + url);
						JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
						if(jarURLConnection !=  null){
							JarFile jarFile = jarURLConnection.getJarFile();
							if(jarFile != null){
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while(jarEntries.hasMoreElements()){
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									//System.out.println(" jarEntryName------------------ > " + jarEntryName);
									if(jarEntryName.endsWith(".class")){
										String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replace("/", ".");
										//System.out.println(" className------------------ > " + className);
										/*if(jarEntryName.contains("MultiTenantConnectionProvider")){
											System.out.println(" jarEntryName------------------ > " + jarEntryName);
											System.out.println(" className------------------ > " + className);
										}*/
										doAddClass(classSet,className);
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("load class set failure",e);
			throw new RuntimeException(e);
		}
		return classSet;
	}
	
	private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				//只要class文件或者目录，其他文件不要
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		for(File file : files){
			String fileName = file.getName();
			if(file.isFile()){
				String className = fileName.substring(0,fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(packageName)){
					className = packageName + "." + className;
				}
				doAddClass(classSet,className);
			}else{
				String subPackagePath = fileName;
				if(StringUtil.isNotEmpty(packagePath)){
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if(StringUtil.isNotEmpty(packageName)){
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet,subPackagePath,subPackageName);
			}
		}
	}
	
	public static void doAddClass(Set<Class<?>> classSet,String className){
		Class<?> cls = loadClass(className,false);
		if(cls != null){
			classSet.add(cls);
		}

	}
}
