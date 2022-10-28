/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionAlbumService.java 
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 *   - Stretch Goals: More search queries for albums (band name, number of songs, actual album name)
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.collection.dao.CollectionAlbumDao;
import com.promineotech.collection.entity.Album;
import com.promineotech.collection.entity.CollectionAlbum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCollectionAlbumService implements CollectionAlbumService {

	@Autowired
	private CollectionAlbumDao collectionAlbumDao;
	
	@Override
	public List<Album> fetchAlbumsByYear(int releaseYear) {
		log.info("The fetchAlbumsByYear method was called with releaseYear = {}", releaseYear);
		
		List<Album> albums = collectionAlbumDao.fetchAlbumsByYear(releaseYear);
		
		if (albums.isEmpty()) {
			String msg = String.format("No albums found with releaseYear=%s", releaseYear);
			throw new NoSuchElementException(msg);
		}
		
		//Collections.sort(albums);
		
		return albums;
	}

	@Override
	public List<Album> fetchAlbumsByArtist(String artistName) {
		log.info("The fetchAlbumsByArtist method has been called with artistName = {}", artistName);
		
		List<Album> albums = collectionAlbumDao.fetchAlbumsByArtist(artistName);
		
		if (albums.isEmpty()) {
			String msg = String.format("No albums found with artistName = %s", artistName);
			throw new NoSuchElementException(msg);
		}
		
		return albums;
	}

	@Override
	public void addAlbumToCollection(int albumID, int collectionID) {
		log.info("The addAlbumToCollection method was called with albumID = {} and collectionID = {}", albumID, collectionID);
		if (!collectionAlbumDao.addAlbumToCollection(albumID, collectionID)) {
			throw new NoSuchElementException("No such albumName = " + albumID + " or No such collectionID = " + collectionID);
		}
	}

	@Override
	public List<CollectionAlbum> fetchAlbumsByCollection(int collectionID) {
		log.info("The fetchAlbumsByCollection method was called with collectionID = {}", collectionID);
		
		List<CollectionAlbum> albums = collectionAlbumDao.fetchAlbumsByCollection(collectionID);
		
		if (albums.isEmpty()) {
			String msg = String.format("No albums added into collectionID = %s", collectionID);
			throw new NoSuchElementException(msg);
		}
		
		return albums;
	}

}
