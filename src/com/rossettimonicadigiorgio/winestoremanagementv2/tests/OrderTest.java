package com.rossettimonicadigiorgio.winestoremanagementv2.tests;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.rossettimonicadigiorgio.winestoremanagementv2.classes.Order;

public class OrderTest {
	private static ArrayList<Object> params = new ArrayList<Object>();
	
	@BeforeEach
	static void init() {
		params.clear();
	}
	
	@ParameterizedTest
	@CsvSource({ "vino1", "vino2" })
	static void testInsertOrder(Order order) {
		
	}
}
