/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionAlbumController.java (Interface)
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 *   - Stretch Goals: More search queries for albums (band name, number of songs, actual album name)
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.collection.entity.Album;
import com.promineotech.collection.entity.CollectionAlbum;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/albums")
@OpenAPIDefinition(info = @Info(title = "Collection Album Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server")})
public interface CollectionAlbumController {
	
	// @formatter:off
	@Operation(
		summary = "Returns all albums given a specific release year",
		description = "Returns all current albums stored in the Database that were released in a given year",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "A list of albums is returned",
				content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = Album.class))),
			@ApiResponse(
				responseCode = "400",
				description = "The requested parameters are invalid",
				content = @Content(mediaType = "application/json")),
			@ApiResponse(
				responseCode = "404",
				description = "No albums were found with the input year",
				content = @Content(mediaType = "application/json")),
			@ApiResponse(
				responseCode = "500",
				description = "An unexpected error occurred.",
				content = @Content(mediaType = "application/json"))		
		},
		parameters = {
			@Parameter(
				name = "releaseYear",
				allowEmptyValue = false,
				required = false,
				description = "The year the given album was released"),		
		}
	)
	// @formatter:on
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	
	List<Album> fetchAlbumsByYear(@RequestParam(required = false) int releaseYear);
	
	
	// @formatter:off
		@Operation(
			summary = "Returns all albums given a specific artist",
			description = "Returns all current albums stored in the Database created by a given artist",
			responses = {
				@ApiResponse(
					responseCode = "200",
					description = "A list of albums is returned",
					content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = Album.class))),
				@ApiResponse(
					responseCode = "400",
					description = "The requested parameters are invalid",
					content = @Content(mediaType = "application/json")),
				@ApiResponse(
					responseCode = "404",
					description = "No albums were found with the input year",
					content = @Content(mediaType = "application/json")),
				@ApiResponse(
					responseCode = "500",
					description = "An unexpected error occurred.",
					content = @Content(mediaType = "application/json"))		
			},
			parameters = {
				@Parameter(
					name = "artistName",
					allowEmptyValue = false,
					required = false,
					description = "The artist's name"),		
			}
		)
		// @formatter:on
		
	@GetMapping("/by-artist")
	@ResponseStatus(HttpStatus.OK)
	List<Album> fetchAlbumsByArtist(@RequestParam(required = false) String artistName);
		
	// @formatter:off
		@Operation(
			summary = "Adds an album to an collection",
			description = "Adds a given album into a given collection via collectionID",
			responses = {
				@ApiResponse(
					responseCode = "200",
					description = "The album is added to the collection",
					content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = Album.class))),
				@ApiResponse(
					responseCode = "400",
					description = "The requested parameters are invalid",
					content = @Content(mediaType = "application/json")),
				@ApiResponse(
					responseCode = "404",
					description = "Invalid Input Criteria",
					content = @Content(mediaType = "application/json")),
				@ApiResponse(
					responseCode = "500",
					description = "An unexpected error occurred.",
					content = @Content(mediaType = "application/json"))		
			},
			parameters = {
				@Parameter(
					name = "collectionID",
					allowEmptyValue = false,
					required = false,
					description = "The collectionID"),
				@Parameter(
						name = "albumID",
						allowEmptyValue = false,
						required = false,
						description = "The albumID")
			}
		)
	// @formatter:on
				
	@PostMapping("/add-to-collection")
	Map<String, Object> addAlbumToCollection(@RequestParam int albumID, @RequestParam int collectionID);
	
		
// @formatter:off
		@Operation(
			summary = "Returns all albums in a given collection",
			description = "Returns all current albums stored in a given collection",
			responses = {
				@ApiResponse(
					responseCode = "200",
					description = "A list of albums is returned",
					content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = CollectionAlbum.class))),
				@ApiResponse(
					responseCode = "400",
					description = "The requested parameters are invalid",
					content = @Content(mediaType = "application/json")),
				@ApiResponse(
					responseCode = "404",
					description = "No albums were found with the input year",
					content = @Content(mediaType = "application/json")),
				@ApiResponse(
					responseCode = "500",
					description = "An unexpected error occurred.",
					content = @Content(mediaType = "application/json"))		
			},
			parameters = {
				@Parameter(
					name = "collectionID",
					allowEmptyValue = false,
					required = false,
					description = "The CollectionID"),		
			}
		)
		// @formatter:on
				
	@GetMapping("/albums-in-collection")
	@ResponseStatus(HttpStatus.OK)
	List<CollectionAlbum>fetchAlbumsByCollection(@RequestParam int collectionID);
}
