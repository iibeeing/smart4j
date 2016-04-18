package com.smart4j.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StreamUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * 从输入流中获取字符串
	 */
	public static String getString(InputStream is){
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		}catch (Exception e) {
			LOGGER.error("get string failure",e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	/**
	@Description: 将输入流复制到输出流
	@param @param inputStream
	@param @param outputStream    设定文件
	@date 创建时间：2016-4-6 上午10:06:11 
	@version 1.0
	@return void    返回类型
	 */
	public static void copyStream(InputStream inputStream,
			OutputStream outputStream) {
		try{
			int length;
			byte[] buffer = new byte[4 * 1024];
			//将输入流中最多 buffer.length 个数据字节读入 buffer 数组。
			//但读取的字节也可能小于该值。以整数形式返回实际读取的字节数length。 
			while((length=inputStream.read(buffer,0,buffer.length)) != -1){
				outputStream.write(buffer,0,length);
			}
			outputStream.flush();
		}catch (Exception e) {
			LOGGER.error("copy stream failure",e);
			throw new RuntimeException(e);
		}finally{
			try{
				inputStream.close();
				outputStream.close();
			}catch (Exception e) {
				LOGGER.error("close stream failure",e);
			}
		}
	}
}
