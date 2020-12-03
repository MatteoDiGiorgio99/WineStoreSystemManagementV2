package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code Response} is a class that defines:
 * Response of the server
 * @author 297398
 *
 */
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Object value;
	
	/**
	 * The method Response
	 * @param value from the server
	 */
	public Response(final Object value) {
		this.value = value;
	}
	
	/**
	 * Fetch the value
	 * @return the value
	 */
	public Object getValue() {
		return this.value;
	}
}
