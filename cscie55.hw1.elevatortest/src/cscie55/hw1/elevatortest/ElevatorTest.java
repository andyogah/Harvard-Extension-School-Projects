package cscie55.hw1.elevatortest;

import cscie55.hw1.elevator.Elevator;

public class ElevatorTest {
	/**
	 * main method
	 * 
	 * @param args
	 * @author Andrew Ogah
	 * @since 09/16/2015
	 * @version 1.0
	 */

	public static void main(String[] args) {
		Elevator myElevator = new Elevator(); // Calling the no argument
												// constructor

		/**
		 * Boarding two passengers for the 3rd floor and one for the 5th floor
		 */

		myElevator.boardPassenger(3);
		myElevator.boardPassenger(3);
		myElevator.boardPassenger(5);

		for (int i = 1; i < ((Elevator.NO_OF_FLOORS) * 2); i++) {

			myElevator.move(); // Calling the Move method on the instantiated
								// objected

		}

	}

}