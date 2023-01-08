package com.briup.env.common.interfaces;

import java.util.Collection;

import com.briup.env.common.entity.Environment;

public interface Client extends EnvironmentInit{

	/**
	 * 客户端发送方法
	 * @param coll 代表采集到的并且整理好的数据清单
	 */
	public void send(Collection<Environment> coll);
}