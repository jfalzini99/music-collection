/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionUserService.java (Interface)
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.service;

import java.util.List;

import com.promineotech.collection.entity.User;

public interface CollectionUserService {

	/**
	 * 
	 * @param userID
	 * @return
	 */
	List<User> fetchUserInfo(int userID);

}
