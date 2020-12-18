package com.rossettimonicadigiorgio.winestoremanagementv2.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Request;
import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Wine;
import com.rossettimonicadigiorgio.winestoremanagementv2.frontend.Client;

public class WineTest {
	private static ArrayList<Object> params = new ArrayList<Object>();
	
	@BeforeEach
	static void init() {
		params.clear();
	}
	
	@Test
	static void testGetAllWines() {
		ArrayList<Wine> result = (ArrayList<Wine>) new Client().run(new Request("listWines", params)).getValue();
		
		assertNull(result);
		assertTrue(result.size() >= 0);
	}
	
	@ParameterizedTest
	@CsvSource({ "vino1", "vino2" })
	static void testReserchWines(String query) {
		params.add(query);
		
		ArrayList<Wine> result = (ArrayList<Wine>) new Client().run(new Request("listWines", params)).getValue();
		
		assertNull(result);
		assertTrue(result.size() >= 0);
	}
}
