package com.example.project;

public class Cube {

	public static void main(final String[] args) {
		System.out.println("Creating cube class");
		Rubiks r = new Rubiks(true);

		r.move("u");
		r.move("d");
		r.move("r");
		r.move("l");
		r.move("f");
		r.move("b");
		
		// r.move("l");

		// r.move("f");

		// r.move("r");
	
	}

}

