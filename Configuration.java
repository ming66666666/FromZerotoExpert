package com.briup.env.common.interfaces;

/**
 * 配置模块接口
 * 主要用于获得其他模块的对象
 * 在获得对象之前将一些基础的配置信息注入到对象里
 * @author mastercgx
 *
 */
public interface Configuration {

	Gather getGather();
	Client getClient();
	Server getServer();
	DbStore getDbStore();
	Logger getLogger();
}