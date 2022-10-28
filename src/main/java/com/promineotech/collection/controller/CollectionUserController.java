/*
 * Music Collection/Organizer App. - BESD Final Project
 * CollectionUserController.java (Interface)
 *   - This fills the project requirement for ONE CRUD operation per entity. 
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.collection.entity.User;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/users")
@OpenAPIDefinition(info = @Info(title = "Collection User Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server")})
public interface CollectionUserController {
	
	// @formatter:off
	@Operation(
		summary = "Returns a User's information",
		description = "Returns the current user's information",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "A user is returned",
				content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = User.class))),
			@ApiResponse(
				responseCode = "400",
				description = "The requested parameters are invalid",
				content = @Content(mediaType = "application/json")),
			@ApiResponse(
				responseCode = "404",
				description = "No users were found with the input criteria",
				content = @Content(mediaType = "application/json")),
			@ApiResponse(
				responseCode = "500",
				description = "An unexpected error occurred.",
				content = @Content(mediaType = "application/json"))		
		},
		parameters = {
			@Parameter(
				name = "userID",
				allowEmptyValue = false,
				required = false,
				description = "The User's userID"),		
		}
	)
	// @formatter:on
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<User> fetchUserInfo(
		@RequestParam(required = false) int userID
	);

}
