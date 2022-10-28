/*
 * Music Collection/Organizer App. - BESD Final Project
 * Collection.java
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Collection {
	private int collectionID;
	private int userID;
	private String collectionName;
}
