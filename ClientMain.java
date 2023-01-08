package com.briup.env.client;

import java.util.Collection;

import com.briup.env.common.entity.Environment;
import com.briup.env.common.interfaces.Client;
import com.briup.env.common.interfaces.Configuration;
import com.briup.env.common.interfaces.Gather;
import com.briup.env.common.interfaces.impl.ConfigurationImpl;

public class ClientMain {
	
	public static void main(String[] args) {
		
		Configuration configuration = new ConfigurationImpl();
		
		// 创建采集对象
		Gather gather = configuration.getGather();
		// 采集数据清单
		Collection<Environment> coll = gather.gather();
		// 创建客户端对象
		Client client = configuration.getClient();
		// 发送数据清单到服务器
		client.send(coll);
		// 合并成一行
		// new ClientImpl().send(new GatherImpl().gather());
	}
}