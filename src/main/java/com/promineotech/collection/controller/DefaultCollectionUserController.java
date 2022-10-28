/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionUserController.java 
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.collection.entity.User;
import com.promineotech.collection.service.CollectionUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCollectionUserController implements CollectionUserController {
	
	@Autowired
	private CollectionUserService collectionUserService;

	@Override
	public List<User> fetchUserInfo(int userID) {
		log.debug("userID = {}");
		return collectionUserService.fetchUserInfo(userID);
	}

}
