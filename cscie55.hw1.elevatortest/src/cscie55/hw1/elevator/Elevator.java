package cscie55.hw1.elevator;

/**
 * Elevator Java class
 *  
 * @author Andrew Ogah
 * @since 09-16-2015
 * @version 1.0
 */
public class Elevator {

	public static final int NO_OF_FLOORS = 7; // Static final field constant for
												// the number of floors in the
												// building

	private boolean ascending; // This field tracks the direction of travel.
								// Default is false

	private int[] destination_requests = { 0, 0, 0, 0, 0, 0, 0 };// This array
																	// tracks
																	// how many
																	// passengers
																	// are
																	// destined
																	// to each
																	// floor
	private int current_floor; // This field tracks the floor the elevator is
	// currently on.
	private int no_of_passengers; // This field tracks the number of passengers currently in the
									// elevator.

	/**
	 * No argument constructor that initializes the elevator's state.
	 */

	public Elevator() {
		current_floor = 0;
		no_of_passengers = 0;

	}

	/**
	 * This method moves the elevator by incrementing/decrementing the
	 * current_floor variable. It changes the direction of the elevator via
	 * changeDirection() every time it reaches the top or bottom floors.
	 */
	public void move() {
		if (destination_requests[current_floor] > 0) // Check if there are any
			// requests for the
			// Elevator to stop on
			// this floor.
			stop();

		if (ascending) // if Direction is UP...
		{

			if (current_floor == (NO_OF_FLOORS - 1)) // Upon reaching the last
				// floor (6), change
				// direction.
				changeDirection();
			else {
				System.out.println(this.toString());
				current_floor++;
			}
		}

		else // means we are descending.
		{

			if (current_floor == 0)
				changeDirection(); // Since we are in floor 0, change direction.
			else {
				System.out.println(this.toString());
				current_floor--;
			}
		}
	}

	/**
	 * This method stops the elevator at a requested floor.
	 */
	private void stop() {

		dischargePassengers();

	}

	/**
	 * This method discharges passengers from the elevator. It uses the
	 * destination_requests[] array in order to determine how many passengers
	 * should be discharged.
	 */
	private void dischargePassengers() {
		int unloading = destination_requests[current_floor]; // extracting value
																// of array as
																// it will
																// mutate inside
																// for loop.
		for (int i = 0; i < unloading; i++) {
			no_of_passengers--;
			destination_requests[current_floor]--;
		}
	}

	/**
	 * This method boards passengers destined to a given floor into the
	 * Elevator.
	 * 
	 * @param floor
	 * This parameter indicates the floor destination of the
	 * passenger
	 */
	public void boardPassenger(int floor) {
		no_of_passengers++;
		destination_requests[floor - 1]++; // update destinations. Using
											// (floor-1) since floor 0 is first
											// floor.
	}

	/**
	 * This method changes the direction of the Elevator.
	 */
	private void changeDirection() {
		ascending = !ascending;
		move();
	}

	/**
	 * Creates a String object for the class
	 */
	public String toString() {

		return String.format("Floor %d:" + " %d passengers", current_floor + 1, no_of_passengers);

	}

}
