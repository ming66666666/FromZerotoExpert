package com.briup.env.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import org.junit.Test;

import com.briup.env.common.entity.Environment;
import com.briup.env.common.interfaces.Client;
import com.briup.env.common.interfaces.Configuration;
import com.briup.env.common.interfaces.Logger;

public class ClientImpl implements Client{

	private String ip;
	private int port;
	private Logger logger;
	@Override
	public void config(Configuration configuration) {
		logger = configuration.getLogger();
	}
	
	@Override
	public void init(Properties properties) {
		// 问题：这种方式为什么可以获得数据呢？
		ip = properties.getProperty("ip");
		port = Integer.parseInt(properties.getProperty("port"));
		
	}
	
	@Override
	public void send(Collection<Environment> coll) {
		// 定义网络模块的客户端
		Socket socket = null;
		
		// 对象输出流
		ObjectOutputStream oos = null;
		try {
			logger.info("客户端正在访问<"+ip+":"+port+">服务");
			socket = new Socket(ip,port);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(coll);
			oos.flush();
			oos.close();
			logger.info("数据清单发送成功！");
			
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally {
			if(socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
		}
	}
	
	@Test
	public void test() {
		new ClientImpl().send(null);
	}




}