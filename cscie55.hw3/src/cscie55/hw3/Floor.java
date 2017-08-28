package cscie55.hw3;

import java.util.ArrayList;

/**
 * Floor Java class
 * 
 * @author Andrew Ogah
 * @since 11-10-2015
 * @version 1.0
 */
public class Floor {

	private int floornumber;
	private Building building;
	private int destinationFloor;
	private Passenger passenger;

	ArrayList<Passenger> waitingToGoUp = new ArrayList<Passenger>(); // collection
																		// of
																		// passengers
																		// waiting
																		// to go
																		// up.
	ArrayList<Passenger> waitingToGoDown = new ArrayList<Passenger>(); // collection
																		// of
																		// passengers
																		// waiting
																		// to go
																		// down.
	ArrayList<Passenger> residentOnFloor = new ArrayList<Passenger>(); // collection
																		// of
																		// passengers
																		// resident
																		// on
																		// floor.

	/**
	 * constructor that initializes the elevator's state.
	 * 
	 * @param building,
	 *            floornumber.
	 * 
	 */
	Floor(Building building, int floornumber) {
		this.setBuilding(building);
		this.setFloornumber(floornumber);
	}

	/**
	 * 
	 * this method adds or removes passenger object to or from any of the
	 * collections depending on the destination in relation to the current floor
	 * 
	 * @param passenger,
	 *            destinationFloor
	 */
	public void waitForElevator(Passenger passenger, int destinationFloor) {

		if (destinationFloor > passenger.getCurrentFloor()) {
			this.waitingToGoUp.add(passenger);

			passenger.setDestinationFloor(destinationFloor);

		} else if (destinationFloor < passenger.getCurrentFloor()) {

			waitingToGoDown.add(passenger);

			passenger.setDestinationFloor(destinationFloor);

		}
		if (residentOnFloor.contains(passenger)) {
			residentOnFloor.remove(residentOnFloor.indexOf(passenger));
		}

	}

	/**
	 * @param passenger
	 * 
	 *            this method returns true if a passenger object is contained in
	 *            resident on floor collection
	 */
	public boolean isResident(Passenger passenger) {

		return residentOnFloor.contains(passenger);

	}

	/**
	 * @param passenger
	 * 
	 *            this method adds the passenger object to the resident on floor
	 *            collection
	 */
	public void enterGroundFloor(Passenger passenger) {

		residentOnFloor.add(passenger);
	}

	/**
	 * @return the floor number
	 */
	public int getFloornumber() {
		return floornumber;
	}

	/**
	 * @param floor
	 *            number the floor number to set
	 */
	public void setFloornumber(int floornumber) {
		this.floornumber = floornumber;
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
	 * @return the destinationFloor
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * @param destinationFloor
	 *            the destinationFloor to set
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	/**
	 * @return the passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * @param passenger
	 *            the passenger to set
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

}
