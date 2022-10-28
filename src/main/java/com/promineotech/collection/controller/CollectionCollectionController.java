/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionCollectionController.java (Interface)
 *   - This fills the project requirement for ALL CRUD operations on one entity.
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.collection.entity.Collection;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/collections")
@OpenAPIDefinition(info = @Info(title = "Collection Collection Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server")})
public interface CollectionCollectionController {
	
	  /*
	   * CREATE OPERATION(S): createCollection() method
	   *   - This operation allows the user to create a collection (primarily giving it a unique name)
	   *   - Stretch Goal - some sort of login system for different user-created collections.
	   *      - Current functionality: one created user in DB, will be used for collection creation.
	   *   - Stretch Goal (cont.): easer, nicer way to add albums to collections (associated with the login system to specify user)
	   *      - User can only edit or delete collections associated with their userID 
	   *   	  - Current functionality: album added based off name and supplied userID; not done automatically. 
	   */
	// @formatter:off
	  @Operation(
	      summary = "Create an collection",
	      description = "Returns the created Collection",
	      responses = {
	          @ApiResponse(
	              responseCode = "201", 
	              description = "The created Collection is returned", 
	              content = @Content(
	                  mediaType = "application/json", 
	                  schema = @Schema(implementation = Collection.class))),
	          @ApiResponse(
	              responseCode = "400", 
	              description = "The request parameters are invalid", 
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "404", 
	              description = "A Collection component was not found with the input criteria", 
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "500", 
	              description = "An unplanned error occurred.", 
	              content = @Content(mediaType = "application/json"))
	      },
	      parameters = {
	          @Parameter(
	              name = "collectionName", 
	              required = true, 
	              description = "The unique name given to the collection"),
	          @Parameter(
	        	  name = "userID",
	        	  required = true,
	        	  description = "The userID assosciated with the collection")

	      }
	  )
	// @formatter:on
	  
	  @PostMapping("/create")
	  @ResponseStatus(code = HttpStatus.CREATED)
	  Collection createCollection(
			  @RequestParam(required = false)
			  @Length(max = 255) 
			  	String collectionName, 
			  @RequestParam(required = false) 
			  	int userID);
	  	  
	  /*
	   * READ OPERATION(S): fetchCollection() method
	   *   - This operation allows the user to view an existing collection in the DB. 
	   *     - Queried via collectionID.
	   *   - Stretch Goals - Query via userID, collectionID
	   */
	  // @formatter:off
	  @Operation(
		      summary = "Retrieves a collection by collectionID",
		      description = "Returns the requested collection",
		      responses = {
		          @ApiResponse(
		              responseCode = "200", 
		              description = "The requested Collection is returned", 
		              content = @Content(
		                  mediaType = "application/json", 
		                  schema = @Schema(implementation = Collection.class))),
		          @ApiResponse(
		              responseCode = "400", 
		              description = "The request parameters are invalid", 
		              content = @Content(mediaType = "application/json")),
		          @ApiResponse(
		              responseCode = "404", 
		              description = "A Collection wass not found with the input criteria", 
		              content = @Content(mediaType = "application/json")),
		          @ApiResponse(
		              responseCode = "500", 
		              description = "An unplanned error occurred.", 
		              content = @Content(mediaType = "application/json"))
		      },
		      parameters = {
		          @Parameter(
		        	  name = "collectionID",
		        	  required = false,
		        	  description = "The collectionID requested by the user")

		      }
		  )
		// @formatter:on
	  
	  @GetMapping("/get-collection")
	  @ResponseStatus(code = HttpStatus.OK)
	  List<Collection> fetchCollectionByCollectionID(@RequestParam(required = false) int collectionID);
	  
	  /*
	   * UPDATE OPERATION: changeCollectionName() method.
	   *   - This operation allows the user to change the name of their collection. 
	   *     - Queried by collectionID. 
	   *   - Stretch Goals: No need for querying off ID, user will be able to see their collection and edit it (login system needed). 
	   */
	  // @formatter:off
	  @Operation(
		      summary = "Updates a collection's name based off of collectionID",
		      description = "The given collection's name is updated.",
		      responses = {
		          @ApiResponse(
		              responseCode = "200", 
		              description = "The requested Collection is updated.", 
		              content = @Content(
		                  mediaType = "application/json", 
		                  schema = @Schema(implementation = Collection.class))),
		          @ApiResponse(
		              responseCode = "400", 
		              description = "The request parameters are invalid", 
		              content = @Content(mediaType = "application/json")),
		          @ApiResponse(
		              responseCode = "404", 
		              description = "A Collection wass not found with the input criteria", 
		              content = @Content(mediaType = "application/json")),
		          @ApiResponse(
		              responseCode = "500", 
		              description = "An unplanned error occurred.", 
		              content = @Content(mediaType = "application/json"))
		      },
		      parameters = {
		          @Parameter(
		        	  name = "collectionID",
		        	  required = false,
		        	  description = "The collectionID requested by the user"),
		          @Parameter(
		        	  name = "newCollectionName",
		        	  description = "The new collection name given by the user")
		      }
		  )
		// @formatter:on
	  
	  @PutMapping("/update-collection")
	  @ResponseStatus(code = HttpStatus.ACCEPTED)
	  Map<String, Object> updateCollectionName(@RequestParam int collectionID, @RequestParam @Length(max = 75) String newCollectionName);
	  
	  	/*
		 * DELETE OPERATION: deleteCollection() method. - This operation allows a user
		 * to delete a collection by it's collectionID. - Stretch Goal: Delete by
		 * collection name, user only can delete his/her collection (based off login)
		 */

		// @formatter:off
		  @Operation(
			      summary = "Deletes a collection's based off the given collectionID",
			      description = "The collection is deleted.",
			      responses = {
			          @ApiResponse(
			              responseCode = "200", 
			              description = "The requested Collection is updated.", 
			              content = @Content(
			                  mediaType = "application/json", 
			                  schema = @Schema(implementation = Collection.class))),
			          @ApiResponse(
			              responseCode = "400", 
			              description = "The request parameters are invalid", 
			              content = @Content(mediaType = "application/json")),
			          @ApiResponse(
			              responseCode = "404", 
			              description = "A Collection wass not found with the input criteria", 
			              content = @Content(mediaType = "application/json")),
			          @ApiResponse(
			              responseCode = "500", 
			              description = "An unplanned error occurred.", 
			              content = @Content(mediaType = "application/json"))
			      },
			      parameters = {
			          @Parameter(
			        	  name = "collectionID",
			        	  required = false,
			        	  description = "The collectionID requested by the user")
			      }
			  )
			// @formatter:on
		  
	  @DeleteMapping("/delete-collection")
	  Map<String, Object> deleteCollection(@RequestParam int collectionID);
}
