package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code StatusEnum} defines the status of an order 
 * @author 297402
 *
 */
public enum StatusEnum implements Serializable {	
	/**
	 * if the user completed the order and it's waiting for shipment
	 */
    Confirmed(1), 
    /**
     * if the order has been shipped
     */
    Shipped(2);
	
	int id;
	
	/**
	 * Enum constructor
	 */
	StatusEnum(int id) {
		this.id = id;
	}
	
	public int GetID() {
		return this.id;
	}
}
