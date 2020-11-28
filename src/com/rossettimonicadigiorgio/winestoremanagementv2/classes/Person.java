package com.rossettimonicadigiorgio.winestoremanagementv2.classes;

import java.io.Serializable;

/**
 * The {@code Person} is a class that defines:
 * details of every person that can access to the system
 * @author 297398
 *
 */
public abstract class Person implements Serializable {
	 private static final long serialVersionUID = 1L;
	 
	 private int idPerson;
	 
	 private String name;
	 private String surname;
	 private String email;
	 private String password;
	
	 /**
	  * Class constructor
	  * @param name of the person
	  * @param surname of the person
	  * @param email of the person
	  * @param password of the person
	  */
	 public Person(int idPerson, String name, String surname, String email, String password)
	 {
		 this.idPerson = idPerson;
		 this.name = name;
		 this.surname = surname;
		 this.email = email;
		 this.password = password;
	 }

	 /**
	  * Fetch name of the person
	  * @return name of the person
	  */
	 public int getIDPerson() {return this.idPerson;}
	 
	 /**
	  * Fetch name of the person
	  * @return name of the person
	  */
	 public String getName() {return this.name;}
	 
	 /**
	  * Fetch surname of the person
	  * @return surname of the person
	  */
	 public String getSurname() { return this.surname; }
	 
	 /**
	  * Fetch email of the person
	  * @return email of the person
	  */
	 public String getEmail() { return this.email; }
	 
	 protected void setEmail(String email) { this.email = email;}
	 
	 /**
	  * Fetch password of the person
	  * @return password of the person
	  */
	 public String getPassword() { return this.password; }
	 
	 
	 protected void setPassword(String password) { this.password = password;}
}