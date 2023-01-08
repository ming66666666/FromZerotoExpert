package com.briup.env.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.briup.env.common.entity.Environment;
import com.briup.env.common.interfaces.Configuration;
import com.briup.env.common.interfaces.Gather;
import com.briup.env.common.interfaces.Logger;

public class GatherImpl implements Gather{
	
	private String fileName;
	// 配置日志模块
	private Logger logger;

	@Override
	public void config(Configuration configuration) {
		// 引入其他模块
		logger = configuration.getLogger();
	}
	
	@Override
	public void init(Properties properties) {
		fileName = properties.getProperty("fileName");
	}
	
	// 定义一个数字和环境名称的关系的集合
	private static Map<String, String> map;
	
	static {
		map = new HashMap<>();
		map.put("16", "温湿度");
		map.put("256", "光照强度");
		map.put("1280", "CO2浓度");
	}

	@Override
	public Collection<Environment> gather() {
		// 提前定义一个集合，用来做返回
		Collection<Environment> coll = new LinkedList<>();
		// 使用适当的流读取日志文件
		// 声明流
		BufferedReader br = null;
		FileReader fr = null;
		try {
			// 创建流
			fr = new FileReader(new File(fileName));
			br = new BufferedReader(fr);
			// 使用流
			String s = null;
			while((s = br.readLine())!=null) {
				// s就代表了文件中每一行的内容
				// 根据数据格式，按照 ｜ 进行分割
				String[] value = s.split("[|]");
				// 新建一个Environment对象，将value的数据对号入座
				Environment environment = new Environment();
				// 定义传感器类型的变量
				String address = value[3];
				// 定义环境数值的变量
				String data = value[6];
				// 给environment的属性赋值
				// 不需要处理的数据，直接保证格式正确即可放入
				environment.setSrcId(value[0]);
				environment.setDesId(value[1]);
				environment.setDevId(value[2]);
				environment.setAddress(address); // 该值可以确定是哪个传感器的数据
				environment.setCount(Integer.parseInt(value[4]));
				environment.setCmd(value[5]);
				environment.setStatus(Integer.parseInt(value[7]));
				environment.setTime(new Timestamp(Long.parseLong(value[8])));
				// name和data数据需要做处理
				if("16".equals(address)) {
					// 16的数据需要拆成两个Environment对象
					environment.setName("温度");
					// 截取数据
					int v1 = Integer.parseInt(data.substring(0, 4), 16);
					float f1 = (float) (((float)v1*0.00268127)-46.85);
					environment.setData(f1);
					
					// 得在这里重新创建一个Environment对象
					Environment env = new Environment();
					env.setSrcId(value[0]);
					env.setDesId(value[1]);
					env.setDevId(value[2]);
					env.setAddress(address);
					env.setCount(Integer.parseInt(value[4]));
					env.setCmd(value[5]);
					env.setStatus(Integer.parseInt(value[7]));
					env.setTime(new Timestamp(Long.parseLong(value[8])));
					env.setName("湿度");
					int v2 = Integer.parseInt(data.substring(4, 8), 16);
					float f2 = (float) (((float)v2*0.00190735)-6);
					env.setData(f2);
					coll.add(env);
					
				}else {
					// 光照强度和CO2浓度的名字直接从map中取
					environment.setName(map.get(address));
					int v = Integer.parseInt(data.substring(0,4),16);
					environment.setData(v);
				}
				
				/*
				environment.setName(""); // 这里没有办法从文件数据中获得，需要判断输入
				environment.setData(0); // 这里需要计算
				*/
				
				// 每循环一次，至少添加一次
				coll.add(environment);
			}
			
			// 统计输出
			// 拓展：统计输出以下几个数据（7分钟）
			// 1、温度数据多少条
			// 2、湿度数据多少条
			// 3、光照强度数据多少条
			// 4、CO2浓度数据多少条
			// 5、共多少条
			// 6、采集共花费多长时间
			
			// 之前使用输出语句
			// System.out.println("【本次采集数据共："+coll.size()+"条】");
			// 后来我们换成了日志（运行日志）
			logger.info("本次采集数据共："+coll.size()+"条");
			
		} catch (FileNotFoundException e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			// 流的关闭
			try {
				fr.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return coll;
	}
	
	// 对上面的方法进行单元测试
	@Test
	public void test() {
		// jdk8的遍历
		Collection<Environment> coll = new GatherImpl().gather();
		coll.forEach(System.out::println);
		System.out.println("总的数据清单的条数：");
		System.out.println(coll.size());
	}


}