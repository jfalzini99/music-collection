package com.promineotech.collection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.promineotech.collection.entity.Collection;

@Component
public class DefaultCollectionCollectionDao implements CollectionCollectionDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public Collection createCollection(String collectionName, int userID) {
		// @formatter:off
		String sql = ""
			+ "INSERT INTO collections ("
			+ "user_id, collection_name"
			+ ") VALUES ("
			+ ":user_id, :collection_name"
			+ ")";
		// @formatter:on
		
		SqlParams params = new SqlParams();
		
		params.sql = sql;
		params.source.addValue("collection_name", collectionName);
		params.source.addValue("user_id", userID);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(params.sql, params.source, keyHolder);
		
		Integer collectionID = keyHolder.getKey().intValue();
		
		// @formatter:off
		return Collection.builder()
			.collectionID(collectionID)
			.collectionName(collectionName)
			.userID(userID)
			.build();
		// @formatter:on
	}
	
	@Override
	public List<Collection> fetchCollectionByCollectionID(int collectionID) {
		// @formatter:off
		String sql = ""
			+ "SELECT * "
			+ "FROM collections "
			+ "WHERE collection_id = :collection_id";
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("collection_id", collectionID);
		
		return jdbc.query(sql, params, new RowMapper<>() {
			
			@Override
			public Collection mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Collection.builder()
						.collectionID(rs.getInt("collection_id"))
						.userID(rs.getInt("user_id"))
						.collectionName(rs.getString("collection_name"))
						.build();
				// @formatter:on
			}
		});
	} 
	
	@Override
	public boolean updateCollectionName(int collectionID, String newCollectionName) {
		// @formatter:off
		String sql = ""
			+ "UPDATE collections SET "
			+ "collection_name = :collection_name "
			+ "WHERE collection_id = :collection_id";
		// @ formatter:on
		
		Map<String, Object> params =
			Map.of(
				"collection_id", collectionID,
				"collection_name", newCollectionName
				);	
		
		return jdbc.update(sql, params) == 1;
	}
	
	@Override
	public boolean deleteCollection(int collectionID) {
		String sql = ""
			+ "DELETE "
			+ "FROM collections "
			+ "WHERE collection_id = :collection_id";
		
		Map<String, Object> params = Map.of("collection_id", collectionID);
		
		return jdbc.update(sql, params) == 1;
	}
	
	class SqlParams {
	    String sql;
	    MapSqlParameterSource source = new MapSqlParameterSource();
	  }
}
