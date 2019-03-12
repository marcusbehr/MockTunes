package edu.century.finalproject.mocktunes;

public class Driver {
	public static void main(String[] args) {
		LibraryGUI run = new LibraryGUI();

		run.setLocationRelativeTo(null);
		run.setVisible(true);
	}
}
/*****************************************************************
 * Always run from driver now or the program will not run at all.*
 ****************************************************************/

/*****************************************************************************************
 * @author Marcus Behr, Tom McDonald, and Jacob Gerval                                   *
 * This program allows a user to pick a desired song from the list of songs and plays it *
 * using a linked list. It also allows the user to buy songs from the store and then add *
 * them to their play list that uses a queue. Another part of the program is the reviews *
 * of the songs that use recursion and a linked list.                                    *
 * This program was built for the Data Structures and Algorithms class 2082.90          *
 ****************************************************************************************/