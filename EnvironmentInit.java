package com.briup.env.common.interfaces;

import java.util.Properties;

/**
 * 抽象为整个项目的根接口
 * 用来定义整个其他模块的公共行为
 * @author mastercgx
 *
 */
public interface EnvironmentInit {
	/**
	 * 给子模块注入配置信息
	 * properties就是一个小map
	 * @param properties
	 */
	public void init(Properties properties);
	/**
	 * 如果某个子模块中要用到其他的子模块
	 * 可以通过参数的配置模块进行获取
	 * @param configuration
	 */
	public void config(Configuration configuration);
}