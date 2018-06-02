package com.pljay.bean;

import java.util.List;

/**
 * 
 * @author PLJay
 *
 */
public class Pages {

	 private Integer total=12;  
	 private Integer offset=12; 
	 private Integer limit=12; 
	 private List data;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Pages(Integer total, Integer offset, Integer limit, List data) {
		super();
		this.total = total;
		this.offset = offset;
		this.limit = limit;
		this.data = data;
	} 
	
}
