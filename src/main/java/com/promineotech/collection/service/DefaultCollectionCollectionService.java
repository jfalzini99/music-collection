/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionCollectionService.java 
 *   - This fills the project requirement for ALL CRUD operations on one entity.
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.collection.dao.CollectionCollectionDao;
import com.promineotech.collection.entity.Collection;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCollectionCollectionService implements CollectionCollectionService {

	@Autowired
	private CollectionCollectionDao collectionCollectionDao;
	
	@Override
	public Collection createCollection(String collectionName, int userID) {
		log.info("The createCollection method was called with collectionName = {} and userID = {}", collectionName, userID);
		return collectionCollectionDao.createCollection(collectionName, userID);
	}

	@Override
	public List<Collection> fetchCollectionByCollectionID(int collectionID) {
		log.info("The fetchCollectionByCollectionID method was called with collectionID = {}", collectionID);
		
		List<Collection> collection = collectionCollectionDao.fetchCollectionByCollectionID(collectionID);
		
		if (collection.isEmpty()) {
			String msg = String.format("No collections found with collectionID = %s", collectionID);
			throw new NoSuchElementException(msg);
		}
		
		return collection;
	}

	@Override
	public void updateCollectionName(int collectionID, String newCollectionName) {
		log.info("The updateCollectionName method was called with collectionID = {} and newCollectionName = {}", collectionID, newCollectionName);
		if (!collectionCollectionDao.updateCollectionName(collectionID, newCollectionName)) {
			throw new NoSuchElementException("Collection with collectionID = " + collectionID + " does not exist.");
		} 
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteCollection(int collectionID) {
		log.info("The deleteCollection method was called with collectionID = {}", collectionID);
		
		if (!collectionCollectionDao.deleteCollection(collectionID)) {
			throw new NoSuchElementException("Collection with ID " + collectionID + " does not exist.");
		}
	}

}
