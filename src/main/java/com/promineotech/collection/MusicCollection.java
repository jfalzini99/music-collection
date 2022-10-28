/*
 * Music Collection/Organizer App. - BESD Final Project
 * MusicCollection.java
 * Written by: Joseph Falzini
 */

package com.promineotech.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promineotech.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = { ComponentScanMarker.class })
public class MusicCollection {

	public static void main(String[] args) {
		SpringApplication.run(MusicCollection.class, args);
	}

}
