/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionCollectionController.java   
 *   - This fills the project requirement for ALL CRUD operations for an entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.collection.entity.Collection;
import com.promineotech.collection.service.CollectionCollectionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCollectionCollectionController implements CollectionCollectionController {

	@Autowired
	private CollectionCollectionService collectionCollectionService;
	
	@Override
	public Collection createCollection(String collectionName, int userID) {
		log.debug("collectionName = {}, userID = {}", collectionName, userID);
		return collectionCollectionService.createCollection(collectionName, userID);
	}

	@Override
	public List<Collection> fetchCollectionByCollectionID(int collectionID) {
		log.debug("collectionID = {}", collectionID);
		return collectionCollectionService.fetchCollectionByCollectionID(collectionID);
	}

	@Override
	public Map<String, Object> updateCollectionName(int collectionID, String newCollectionName) {
		log.debug("collectionID = {}", collectionID);
		collectionCollectionService.updateCollectionName(collectionID, newCollectionName);
		return Map.of("Message", "Collection with ID = " + collectionID + " successfully updated.");
	}

	@Override
	public Map<String, Object> deleteCollection(int collectionID) {
		log.debug("collectionID = {}", collectionID);
		collectionCollectionService.deleteCollection(collectionID);
		return Map.of("Message", "Collection with ID = " + collectionID + " successfully deleted.");
	}

}
