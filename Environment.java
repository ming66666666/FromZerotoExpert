package com.briup.env.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 环境实体类
 * @author mastercgx
 *
 */
public class Environment implements Serializable{
    
	private static final long serialVersionUID = 1L;
	// 属性
    // 环境种类的名称（温度、湿度、二氧化碳浓度、光照强度）
    private String name;
    // 电脑端id
    private String srcId;
    // 树莓派id
    private String desId;
    // 试验箱区域模块id
    private String devId;
    // 模块地址
    private String address;
    // 传感器个数
    private int count;
    // 指令编号
    private String cmd;
    // 状态
    private int status;
    // 监测到环境的具体数值
    private float data;
    // 采集时间
    private Timestamp time;
    public Environment() {}
	public Environment(String name, String srcId, String desId, String devId, String address, int count, String cmd,
			int status, float data, Timestamp time) {
		super();
		this.name = name;
		this.srcId = srcId;
		this.desId = desId;
		this.devId = devId;
		this.address = address;
		this.count = count;
		this.cmd = cmd;
		this.status = status;
		this.data = data;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getDesId() {
		return desId;
	}
	public void setDesId(String desId) {
		this.desId = desId;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getData() {
		return data;
	}
	public void setData(float data) {
		this.data = data;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Environment [name=" + name + ", srcId=" + srcId + ", desId=" + desId + ", devId=" + devId + ", address="
				+ address + ", count=" + count + ", cmd=" + cmd + ", status=" + status + ", data=" + data + ", time="
				+ time + "]";
	}
    
}