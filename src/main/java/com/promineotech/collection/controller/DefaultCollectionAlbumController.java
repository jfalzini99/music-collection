/*
 * Music Collection/Organizer App. - BESD Final Project
 * DefaultCollectionAlbumController.java 
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 *   - Stretch Goals: More search queries for albums (band name, number of songs, actual album name)
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.collection.entity.Album;
import com.promineotech.collection.entity.CollectionAlbum;
import com.promineotech.collection.service.CollectionAlbumService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCollectionAlbumController implements CollectionAlbumController {

	@Autowired
	private CollectionAlbumService collectionAlbumService;
	
	@Override
	public List<Album> fetchAlbumsByYear(int releaseYear) {
		log.debug("releaseYear = {}", releaseYear);
		return collectionAlbumService.fetchAlbumsByYear(releaseYear);
	}

	@Override
	public List<Album> fetchAlbumsByArtist(String artistName) {
		log.debug("artistName = {}", artistName);
		return collectionAlbumService.fetchAlbumsByArtist(artistName);
	}

	@Override
	public Map<String, Object> addAlbumToCollection(int albumID, int collectionID) {
		log.debug("albumID = {}, collectionID = {}", albumID, collectionID);
		collectionAlbumService.addAlbumToCollection(albumID, collectionID);
		return Map.of("Message", "The album with ID = " + albumID + " has successfully been added to colleciton with ID = " + collectionID);
	}

	@Override
	public List<CollectionAlbum> fetchAlbumsByCollection(int collectionID) {
		log.debug("collectionID = {}", collectionID);
		return collectionAlbumService.fetchAlbumsByCollection(collectionID);
	}

}
