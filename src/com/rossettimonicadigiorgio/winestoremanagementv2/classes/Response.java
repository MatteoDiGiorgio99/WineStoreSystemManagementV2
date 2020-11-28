package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Object value;
	
	public Response(final Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return this.value;
	}
}
