package cscie55.hw3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Elevator Java class
 * 
 * @author Andrew Ogah
 * @since 11-10-2015
 * @version 1.0
 */
public class Elevator {

	public static final int CAPACITY = 10;

	private boolean ascending; // This field tracks the direction of travel.
								// Default is false

	private int currentFloor; // This field tracks the floor the elevator is
	// currently on.
	private int destinationFloorNumber;
	private Building building;
	private int passengerCount = 0; // This field keeps a count of passengers
									// waiting
									// to board, which are compared to elevator
									// capacity

	ArrayList<ArrayList<Passenger>> destinationRequests = new ArrayList<ArrayList<Passenger>>(); // ArrayList
																									// of
																									// ArrayList
																									// to
																									// track
																									// a
																									// set
																									// of
																									// passenger
																									// objects
																									// in
																									// the
																									// elevator
																									// going
																									// to
																									// each
																									// floor.
	ArrayList<Passenger> waitingToGoUp;
	ArrayList<Passenger> waitingToGoDown;

	/**
	 * No argument constructor that initializes the elevator's state.
	 * 
	 * @param expectedFloorNumber
	 */
	public Elevator() {
		this.currentFloor = 1;
		ascending = true;
		for (int i = 0; i < Building.FLOORS; i++)
			destinationRequests.add(new ArrayList<Passenger>());
	}

	/**
	 * this constructor calls the no argument constructor and also takes in a .
	 * 
	 * @param expectedFloorNumber
	 */
	public Elevator(Building building) {
		this();
		this.building = building;
	}

	/**
	 * this method checks if a passenger on floor is waiting to board
	 * 
	 * throws elevator full exception to prevent passengers from boarding when
	 * elevator is full
	 */
	@SuppressWarnings("unchecked")
	protected void checkWaitingOnFloor() throws ElevatorFullException {

		if (goingUp()) {
			Floor f = building.floor(currentFloor);

			waitingToGoUp = (ArrayList<Passenger>) f.waitingToGoUp.clone();
			ArrayList<Passenger> actualWaiting = f.waitingToGoUp;

			if (waitingToGoUp.size() > 0) {

				for (Passenger p : waitingToGoUp) {
					if (passengerCount >= Elevator.CAPACITY) {
						throw new ElevatorFullException();
					}

					ArrayList<Passenger> passList = destinationRequests.get(p.destinationFloor() - 1);
					passengerCount = passengerCount + 1;
					passList.add(p);
					int index = actualWaiting.indexOf(p);
					actualWaiting.remove(index);

				}
				waitingToGoUp.clear();

			}

		} else {
			waitingToGoDown = (ArrayList<Passenger>) building.floor(currentFloor).waitingToGoDown.clone(); // Returns
																											// a
																											// shallow
																											// copy
																											// of
																											// this
																											// ArrayList
																											// instance.
																											// (The
																											// elements
																											// themselves
																											// are
																											// not
																											// copied.)
			ArrayList<Passenger> actualWaiting = building.floor(currentFloor).waitingToGoDown;
			if (waitingToGoDown.size() > 0) {

				for (Passenger p : waitingToGoDown) {

					if (passengerCount >= Elevator.CAPACITY) {
						throw new ElevatorFullException();
					}
					passengerCount = passengerCount + 1;
					destinationRequests.get(p.destinationFloor() - 1).add(p);
					int index = actualWaiting.indexOf(p);
					actualWaiting.remove(index);

				}
				waitingToGoDown.clear();

			}

		}

	}

	/**
	 * This method moves the elevator by incrementing/decrementing the
	 * currentFloor variable. It changes the direction of the elevator via
	 * changeDirection() every time it reaches the top or bottom floors.
	 */
	public void move() {

		if (destinationRequests.get(currentFloor - 1).size() > 0) // Check if
																	// there are
																	// any
		// requests for the
		// Elevator to stop on
		// this floor.
		{
			stop();
		}

		if (ascending) // if Direction is UP...
		{

			currentFloor++;

			if (currentFloor == (Building.FLOORS)) // Upon reaching
			// the last
			// floor (6), change
			// direction.
			{

				changeDirection();

				try {
					checkWaitingOnFloor();
				} catch (ElevatorFullException e) {
					e.printStackTrace();
				}

			}

			if (destinationRequests.get(currentFloor - 1).size() > 0) // Check
																		// if
																		// there
																		// are
																		// any
			// requests for the
			// Elevator to stop on
			// this floor.
			{
				stop();
			}
			try {
				checkWaitingOnFloor();
			} catch (ElevatorFullException e) {
				e.printStackTrace();
			}

		}

		else // means we are descending.
		{
			currentFloor--;

			if (currentFloor == 1) {
				changeDirection(); // Since we are in floor 0, change direction.
				try {
					checkWaitingOnFloor();
				} catch (ElevatorFullException e) {
					e.printStackTrace();
				}

			}
			if (destinationRequests.get(currentFloor - 1).size() > 0) // Check
																		// if
																		// there
																		// are
																		// any
			// requests for the
			// Elevator to stop on
			// this floor.
			{
				stop();
			}
			try {
				checkWaitingOnFloor();
			} catch (ElevatorFullException e) {
				e.printStackTrace();
			}

		}
	}

	public int currentFloor() {

		return this.currentFloor;
	}

	/**
	 * This method stops the elevator at a requested floor.
	 */
	public void stop() {

		dischargePassengers();

	}

	/**
	 * This method discharges passengers from the elevator. It uses the
	 * destination_requests[] array in order to determine how many passengers
	 * should be discharged.
	 */
	public void dischargePassengers() {

		ArrayList<Passenger> currentFloorPassengers = destinationRequests.get(currentFloor - 1);
		int unloading = currentFloorPassengers.size();

		for (int i = 0; i < unloading; i++) {
			Passenger p = destinationRequests.get(currentFloor - 1).get(i);
			p.arrive();
			passengerCount = passengerCount - 1;
			building.floor(currentFloor).residentOnFloor.add(p);
		}

		destinationRequests.get(currentFloor - 1).clear();

	}

	/**
	 * This method changes the direction of the Elevator.
	 */
	public void changeDirection() {
		ascending = !ascending;

	}

	/**
	 * @return the destination floor number
	 */
	public int getDestinationFloorNumber() {
		return destinationFloorNumber;
	}

	/**
	 * @param destinationFloorNumber
	 *            the destination floor number to set
	 */
	public void setDestinationFloorNumber(int destinationFloorNumber) {
		this.destinationFloorNumber = destinationFloorNumber;
	}

	/**
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @param building
	 *            the building to set
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * 
	 * this method returns true if the elevator is going up
	 * 
	 */
	public boolean goingUp() {
		if (ascending) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * this method returns true if the elevator is going down
	 * 
	 */
	public boolean goingDown() {
		if (ascending) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * this method returns a Set<Passenger> containing all of the Passengers on
	 * the elevator
	 * 
	 */
	public Set<Passenger> passengers() {

		Set<Passenger> residentOnElevator = new HashSet<Passenger>();

		for (ArrayList<Passenger> floorList : destinationRequests)
			for (Passenger p : floorList) {

				residentOnElevator.add(p);
			}
		return residentOnElevator;
	}

}
