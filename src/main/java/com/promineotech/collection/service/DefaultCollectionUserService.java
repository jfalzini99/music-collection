/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionUserService.java 
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.collection.dao.CollectionUserDao;
import com.promineotech.collection.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCollectionUserService implements CollectionUserService {

	@Autowired
	private CollectionUserDao collectionUserDao;

	@Transactional(readOnly = false)
	@Override
	public List<User> fetchUserInfo(int userID) {
		log.info("The fetchUserInfo method was called with userID={}", userID);
		
		List<User> users = collectionUserDao.fetchUserInfo(userID);
		
		if (users.isEmpty()) {
			String msg = String.format("No users found with userID = %s", userID);
			throw new NoSuchElementException(msg);
		}
		
		return users;
	}

}
