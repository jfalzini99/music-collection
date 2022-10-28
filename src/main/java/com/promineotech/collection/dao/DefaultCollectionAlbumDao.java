/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionAlbumDao.java
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 *   - Stretch Goals: More search queries for albums (band name, number of songs, actual album name)
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

import com.promineotech.collection.entity.Album;
import com.promineotech.collection.entity.CollectionAlbum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCollectionAlbumDao implements CollectionAlbumDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public List<Album> fetchAlbumsByYear(int releaseYear) {
		log.debug("DAO: releaseYear = {}", releaseYear);
		
		// @formatter:off
		String sql = ""
			+ "SELECT * "
			+ "FROM albums "
			+ "WHERE release_year = :release_year";
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();	
		params.put("release_year", releaseYear);
		
		return jdbc.query(sql, params, new RowMapper<>() {
			
			@Override
			public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Album.builder()
						.albumID(rs.getInt("album_id"))
						.artistName(rs.getString("artist"))
						.albumTitle(rs.getString("title"))
						.numSongs(rs.getInt("num_songs"))
						.releaseYear(rs.getInt("release_year"))
						.build();
				// @formatter:on
			}
		});
	}

	@Override
	public List<Album> fetchAlbumsByArtist(String artistName) {
		log.debug("DAO: artistName = {}", artistName);

		// @formatter:off
		String sql = ""
			+ "SELECT * "
			+ "FROM albums "
			+ "WHERE artist = :artist";
		// @formatter:on

		Map<String, Object> params = new HashMap<>();
		params.put("artist", artistName);

		return jdbc.query(sql, params, new RowMapper<>() {

			@Override
			public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return Album.builder()
						.albumID(rs.getInt("album_id"))
						.artistName(rs.getString("artist"))
						.albumTitle(rs.getString("title"))
						.numSongs(rs.getInt("num_songs"))
						.releaseYear(rs.getInt("release_year"))
						.build();
				// @formatter:on
			}
		});
	}

	@Override
	public boolean addAlbumToCollection(int albumID, int collectionID) {
		String sql = ""
			+ "INSERT INTO collection_albums ("
			+ "collection_id, album_id"
			+ ") VALUES ("
			+ ":collection_id, :album_id"
			+ ")";
		
		Map<String, Object> params =
				Map.of(
					"collection_id", collectionID,
					"album_id", albumID
					);	
			
			return jdbc.update(sql, params) == 1;
	}

	@Override
	public List<CollectionAlbum> fetchAlbumsByCollection(int collectionID) {
		String sql = ""
			+ "SELECT * "
			+ "FROM collection_albums "
			+ "WHERE collection_id = :collection_id";
		
		Map<String, Object> params = new HashMap<>();	
		params.put("collection_id", collectionID);
		
		return jdbc.query(sql, params, new RowMapper<>() {
			
			@Override
			public CollectionAlbum mapRow(ResultSet rs, int rowNum) throws SQLException {
				// @formatter:off
				return CollectionAlbum.builder()
						.collectionID(rs.getInt("collection_id"))
						.albumID(rs.getInt("album_id"))
						.build();
				// @formatter:on
			}
		});
	}
}
