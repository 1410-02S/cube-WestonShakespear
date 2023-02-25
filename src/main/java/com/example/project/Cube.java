package com.example.project;

public class Cube {

	public static void main(final String[] args) {
		System.out.println("Creating cube class");
		Rubiks r = new Rubiks(true);

		// r.move("u");
		// r.move("d");
		// r.showCube();
		r.move("b");
		
		r.move("r");
	
		r.move("f");
	
		r.move("f");
	
		r.move("r'");
	
		r.move("u");
		

		r.move("b");
	
		// // r.move("b");
		

		r.move("l");
		r.move("f");
		r.move("b");
		

		r.move("b");
		r.move("r");
		r.move("f");

		r.move("l'");
		r.move("u'");
		r.move("d'");

		r.move("f'");
		r.move("b'");

		r.move("r");
		r.move("f");


		r.move("l'");
		r.move("u'");
		r.move("d'");

		r.move("b");

		r.move("r");

		r.move("f");




		r.move("l'");
		r.move("u'");

		r.move("b");
		r.move("r");
		r.move("u");

		r.move("b");
		r.move("r");
		r.move("u");


		r.showCube();

		
		// r.move("f'");

		// // System.out.println(r.getStateString());
		// r.showCube();
		
		// r.move("l");

		// r.move("f");

		// r.move("r");
	
	}

}

