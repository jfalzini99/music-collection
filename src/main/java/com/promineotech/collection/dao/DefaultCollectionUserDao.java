/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionUserService.java (Interface)
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.promineotech.collection.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCollectionUserDao implements CollectionUserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public List<User> fetchUserInfo(int userID) {
		log.debug("DAO: userID = {}", userID);
		
		// @formatter:on
		String sql = ""
			+ "SELECT * "
			+ "FROM users "
			+ "WHERE user_id = :user_id"
				;
		// @formatter:off
		
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", userID);
		
		return jdbc.query(sql, params, new RowMapper<>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return User.builder()
			  	          .userID(rs.getInt("user_id"))
			  	          .firstName(rs.getString("first_name"))
			  	          .lastName(rs.getString("last_name"))
			  	          .collectionID(rs.getInt("collection_id"))
			  	          .build();	
				// @formatter:on
			}
		});
	}
	
}
