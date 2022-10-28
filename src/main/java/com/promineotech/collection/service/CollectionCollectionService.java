/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionCollectionService.java (Interface)
 *   - This fills the project requirement for ALL CRUD operations on one entity.
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.service;

import java.util.List;

import com.promineotech.collection.entity.Collection;

public interface CollectionCollectionService {

	/**
	 * 
	 * @param collectionName
	 * @param userID
	 * @return
	 */
	Collection createCollection(String collectionName, int userID);

	/**
	 * 
	 * @param collectionID
	 * @return
	 */
	List<Collection> fetchCollectionByCollectionID(int collectionID);

	/**
	 * 
	 * @param collectionID
	 * @param newCollectionName
	 * @return
	 */
	void updateCollectionName(int collectionID, String newCollectionName);

	/**
	 * 
	 * @param collectionID
	 */
	void deleteCollection(int collectionID);

}
