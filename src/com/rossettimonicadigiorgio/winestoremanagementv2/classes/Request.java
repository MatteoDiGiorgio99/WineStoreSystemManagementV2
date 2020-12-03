package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;
import java.util.List;

/**
 * The {@code Request} is a class that defines:
 * Request to the server
 * @author 297398
 *
 */
public class Request implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String value;
	
	private final List<Object> params;
	
	/**
	 * The method Request
	 * @param value of the method to call from server
	 * @param params of the request 
	 */
	public Request(final String value, final List<Object> params) {
		this.value = value;
		this.params = params;
	}
	
	/**
	 * Fetch the value 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Fetch the params
	 * @return params
	 */
	public List<Object> getParams() {
		return this.params;
	}
}
