package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code Person} is a class that defines:
 * an Employee
 * @author 297398
 *
 */
public class Employee extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class constructor 
	 * @param idPerson of the Employee
	 * @param name of the Employee
	 * @param surname of the Employee
	 * @param email of the Employee
	 * @param password of the Employee
	 */
	public Employee(int idPerson, String name, String surname, String email, String password) {
		super(idPerson, name, surname, email, password);	
	}	
}
