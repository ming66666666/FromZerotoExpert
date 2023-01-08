package com.briup.env.common.interfaces;
/**
 * 主要提供采集功能的接口
 * @author mastercgx
 *
 */

import java.util.Collection;

import com.briup.env.common.entity.Environment;

public interface Gather extends EnvironmentInit{
	/**
	 * 采集环境数据
	 * @return 环境对象的集合
	 */
	public Collection<Environment> gather();
}