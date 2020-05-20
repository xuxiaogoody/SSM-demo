package com.xuxiao.crud.bean;

import java.util.*;

/**
 * ͨ�õķ�����
 * 
 * @author xuxiao
 *
 */
public class Msg {
	private static final int SUCCESS = 100;
	private static final int FAIL = 200;
	// ״̬�룺100�ɹ���200ʧ��
	private int code;
	// ��ʾ��Ϣ
	private String msg;
//���ص�����
	private Map<String, Object> extend = new HashMap<String, Object>();

	public static Msg success() {
		Msg result = new Msg();
		result.setCode(SUCCESS);
		result.setMsg("�ɹ�");
		return result;
	}

	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(FAIL);
		result.setMsg("ʧ��");
		return result;
	}
	public  Msg add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}
