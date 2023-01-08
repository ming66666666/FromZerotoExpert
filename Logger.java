package com.briup.env.common.interfaces;
/**
 * 日志模块
 * 根据不用的信息级别来输出日志
 * @author mastercgx
 *
 */
public interface Logger extends EnvironmentInit{
	/**
	 * 输出调试级别的日志
	 * @param msg
	 */
	void debug(Object msg);
	/**
	 * 输出信息级别的日志
	 * @param msg
	 */
	void info(Object msg);
	/**
	 * 输出警告级别的日志
	 * @param msg
	 */
	void warn(Object msg);
	/**
	 * 输出错误级别的日志
	 * @param msg
	 */
	void error(Object msg);
	/**
	 * 输出崩溃级别的日志
	 * @param msg
	 */
	void fatal(Object msg);
}