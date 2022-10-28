/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionCollectionDao.java (Interface)
 *   - This fills the project requirement for ALL CRUD operations on one entity.
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.dao;

import java.util.List;

import com.promineotech.collection.entity.Collection;

public interface CollectionCollectionDao {

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
	boolean updateCollectionName(int collectionID, String newCollectionName);

	/**
	 * 
	 * @param collectionID
	 * @return
	 */
	boolean deleteCollection(int collectionID);

}
