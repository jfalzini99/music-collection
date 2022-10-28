/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionAlbumDao.java (Interface)  
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 *   - Stretch Goals: More search queries for albums (band name, number of songs, actual album name)
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.dao;

import java.util.List;

import com.promineotech.collection.entity.Album;
import com.promineotech.collection.entity.CollectionAlbum;

public interface CollectionAlbumDao {

	/**
	 * 
	 * @param releaseYear
	 * @return
	 */
	List<Album> fetchAlbumsByYear(int releaseYear);

	/**
	 * 
	 * @param artistName
	 * @return
	 */
	List<Album> fetchAlbumsByArtist(String artistName);

	/**
	 * 
	 * @param albumID
	 * @param collectionID
	 * @return
	 */
	boolean addAlbumToCollection(int albumID, int collectionID);

	/**
	 * 
	 * @param collectionID
	 * @return
	 */
	List<CollectionAlbum> fetchAlbumsByCollection(int collectionID);

}
