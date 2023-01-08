package com.briup.env.common.interfaces;

import java.util.Collection;

import com.briup.env.common.entity.Environment;

public interface Server extends EnvironmentInit{

	/**
	 * 服务器接收方法
	 * @return	返回从客户端收到的数据清单
	 */
	public Collection<Environment> receive();
}