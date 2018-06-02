package com.pljay.bean;

public class DiscssNode {

	private int id;
	private String conten;
	private int parentid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConten() {
		return conten;
	}
	public void setConten(String conten) {
		this.conten = conten;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public DiscssNode(int id, String conten, int parentid) {
		super();
		this.id = id;
		this.conten = conten;
		this.parentid = parentid;
	}
	
}
