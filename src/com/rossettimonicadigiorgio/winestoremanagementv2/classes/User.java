package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code User} is a class that defines:
 * the type of person that can:
 * login,register and buy products
 * @author 297398
 *
 */
public class User extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class constructor 
	 * @param idPerson of the User
	 * @param name of the User
	 * @param surname of the User
	 * @param email of the User
	 * @param password of the User
	 */
	public User(int idPerson, String name, String surname, String email, String password) {
		super(idPerson, name, surname, email, password);	
	}	
}
