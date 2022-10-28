/*
 * Music Collection/Organizer App. - BESD Final Project
 * Album.java
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Album {
	private int albumID;
	private String artistName;
	private String albumTitle;
	private int numSongs;
	private int releaseYear;
}
