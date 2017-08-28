package cscie55.hw2;

/**
 * Elevator Java class
 * 
 * @author Andrew Ogah
 * @since 10-14-2015
 * @version 1.0
 * 
 */
public class Elevator {

	public static final int CAPACITY = 10; // This field tracks the capacity of
											// the elevator

	private boolean ascending; // This field tracks the direction of travel.
								// Default is false

	private int[] destinationRequests = { 0, 0, 0, 0, 0, 0, 0 };// This array
																// tracks
																// how many
																// passengers
																// are
																// destined
																// to each
																// floor
	private int currentFloor; // This field tracks the floor the elevator is
	// currently on.
	private int passengers; // This field tracks the number of passengers on
							// the elevator

	private int destinationFloorNumber;

	private Building building;

	/**
	 * constructor that initializes the elevator's state.
	 * 
	 * @param building
	 *            the building object associated with this elevator
	 */

	public Elevator(Building building) {
		this.building = building;
	}

	private void addWaitingPassengers() {
		int peopleWaitingatFloor = building.floor(currentFloor + 1).passengersWaiting();
		if (peopleWaitingatFloor > 0) {
			System.out.println("People waiting at floor:" + (currentFloor + 1) + "is " + peopleWaitingatFloor);
			try {
				for (int i = 1; i <= peopleWaitingatFloor; i++) {

					boardPassenger(1); // May throw ElevatorFullException
					building.floor(currentFloor + 1).decrementNumPassWaiting();
				} // The passenger boarded successfully

			} catch (ElevatorFullException e) {
				// The passenger was unable to board because the elevator is
				// full.
				System.out.println("Exception thrown");

			}
		}
	}

	/**
	 * This method moves the elevator by incrementing/decrementing the
	 * currentFloor variable. It changes the direction of the elevator via
	 * changeDirection() every time it reaches the top or bottom floors.
	 */
	public void move() {
		
		System.out.println("Current Floor is:" + currentFloor());
		if (destinationRequests[currentFloor] > 0) // Check if there are any
		// requests for the
		// Elevator to stop on
		// this floor.
			//System.out.println("Current Floor is:" + currentFloor());
		{
			stop();
		}

		if (ascending) // if Direction is UP...
		{

			if (currentFloor == (Building.FLOORS - 1)) // Upon reaching
														// the last
				// floor (6), change
				// direction.
				changeDirection();
			else {

				currentFloor++;
				System.out.println("Ascending to" + currentFloor());
				if (destinationRequests[currentFloor] > 0) // Check if there are
															// any
				// requests for the
				// Elevator to stop on
				// this floor.
				{
					stop();
				}
				addWaitingPassengers();
			}
		}

		else // means we are descending.
		{

			if (currentFloor == 0)
				changeDirection(); // Since we are in floor 0, change direction.
			else {

				currentFloor--;
				System.out.println("Descending to" + currentFloor());
				if (destinationRequests[currentFloor] > 0) // Check if there are
															// any
				// requests for the
				// Elevator to stop on
				// this floor.
				{
					stop();
				}
				addWaitingPassengers();
			}
		}
	}

	/**
	 * This method returns the current floor.
	 */

	public int currentFloor() {

		return currentFloor + 1;
	}

	/**
	 * This method returns the passengers object.
	 */

	public int passengers() {
		return passengers;
	}

	/**
	 * This method stops the elevator at a requested floor and discharges
	 * passengers
	 */
	public void stop() {

		System.out.println("Stopping at floor" + currentFloor());
		dischargePassengers();

	}

	/**
	 * This method discharges passengers from the elevator. It uses the
	 * destination_requests[] array in order to determine how many passengers
	 * should be discharged.
	 */
	private void dischargePassengers() {
		int unloading = destinationRequests[currentFloor]; // extracting value
															// of array as
															// it will
															// mutate inside
															// for loop.
		for (int i = 0; i < unloading; i++) {
			passengers--;
			destinationRequests[currentFloor]--;
		}
	}

	/**
	 * This method boards passengers destined to a given floor into the
	 * Elevator.
	 * 
	 * @param destinationFloorNumber
	 *            This parameter indicates the number of the destination floor
	 *            of the passenger
	 * @throws ElevatorFullException
	 */
	public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException {

		if (passengers >= CAPACITY) {

			throw new ElevatorFullException();

		}

		passengers++;

		destinationRequests[destinationFloorNumber - 1]++; // Update
															// destinations.
															// Using
															// (floor-1) since
															// floor 0 is first
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
	 * @return the destinationFloorNumber
	 */
	public int getDestinationFloorNumber() {
		return destinationFloorNumber;
	}

	/**
	 * @param destinationFloorNumber
	 *            the destinationFloorNumber to set
	 */
	public void setDestinationFloorNumber(int destinationFloorNumber) {
		this.destinationFloorNumber = destinationFloorNumber;
	}

}
