/*
 * Music Collection/Organizer App. - BESD Final Project
 * User.java
 * Written by: Joseph Falzini
 *  */


package com.promineotech.collection.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int userID;
	private int collectionID;
	private String firstName;
	private String lastName;
}
