package com.example.project;

import java.io.*;
import java.util.*;

public class Cube {

	public static void main(final String[] args) 
	throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Rubiks r = new Rubiks();

		boolean consoleArgs = false;
		boolean exit = false;
		int argCounter = 0;

		if (args.length > 0) {
			consoleArgs = true;
		}

		while(exit != true) {
			String command = "";
			System.out.print("Cmd: ");
			if (consoleArgs == true) {
				command = args[argCounter];
				System.out.println(command);

				argCounter++;

				if (argCounter >= args.length) {
					exit = true;
				}
			} else {
				command = reader.readLine();
				if (command.contains("q") == true) {
					break;
				}
			}



			r.move(command);
			r.showCube();
			r.listCommands();
		}
	
	}

}

