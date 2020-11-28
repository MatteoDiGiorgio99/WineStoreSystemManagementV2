package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code Person} is a class that defines:
 * an Administrator
 * @author 297398
 *
 */

public class Administrator extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class constructor 
	 * @param idPerson of the Administrator
	 * @param name of the Administrator
	 * @param surname of the Administrator
	 * @param email of the Administrator
	 * @param password of the Administrator
	 */
	public Administrator(int idPerson, String name, String surname, String email, String password) {
		super(idPerson, name, surname, email, password);	
	}	
}
