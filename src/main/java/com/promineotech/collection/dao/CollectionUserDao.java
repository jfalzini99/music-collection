/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionUserDao.java (Interface)
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.dao;


import java.util.List;

import com.promineotech.collection.entity.User;

public interface CollectionUserDao {

	/**
	 * 
	 * @param userID
	 * @return
	 */
	List<User> fetchUserInfo(int userID);

}
