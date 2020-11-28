package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String value;
	
	private final List<Object> params;
	
	public Request(final String value, final List<Object> params) {
		this.value = value;
		this.params = params;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public List<Object> getParams() {
		return this.params;
	}
}
