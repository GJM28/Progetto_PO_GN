package com.project.OOP.errors;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.OOP.model.Friend;
import com.project.OOP.model.FriendsCollection;

class FieldExceptionTest {

	private FriendsCollection s;
	private Friend f1;
	private Friend f2;
	private Friend f3;
	
	@BeforeEach
	void setUp() throws Exception {
		f1 = new Friend("federico", 345);
		f2 = new Friend("alessandro", 3);
		f3 = new Friend("stefano", 1000);
		ArrayList<Friend> friends = new ArrayList<>();
		friends.add(f1);
		friends.add(f2);
		friends.add(f3);
		s = new FriendsCollection(friends);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		FieldException ex = assertThrows(FieldException.class, ()->{s.Topranking("Top4");});
		assertEquals("campo field errato", ex.getMessage());
	}

}
