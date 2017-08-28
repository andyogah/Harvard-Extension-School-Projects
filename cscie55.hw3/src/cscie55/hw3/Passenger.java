package cscie55.hw3;

/**
 * Passenger Java class
 * 
 * @author Andrew Ogah
 * @since 11-10-2015
 * @version 1.0
 */
public class Passenger {
	private static final int UNDEFINED_FLOOR = -1;
	private int currentFloor;
	private int destinationFloor;

	/**
	 * constructor with argument that calls the no argument constructor
	 * set the current floor to 1 and the destination floor to undefined.
	 * 
	 * @param destinationFloor
	 */
	public Passenger(int destinationFloor) {
		this();
		setCurrentFloor(1);
		setDestinationFloor(UNDEFINED_FLOOR);
		}

	/**
	 * No argument constructor that initializes the passenger's state.
	 * 
	 */
	public Passenger() {
		this.currentFloor = getCurrentFloor(); 
	}

	/**
	 * This method returns the current floor.
	 */
	public int currentFloor() {
		return getCurrentFloor();
	}

	/**
	 * This method returns the destination floor.
	 */
	public int destinationFloor() {
		return getDestinationFloor();
	}

	/**
	 * this method set a new destination floor to current destination floor
	 * 
	 * @param newDestinationFloor
	 * 
	 *            the newDestinationFloor to set
	 */
	public void waitForElevator(int newDestinationFloor) {
		setDestinationFloor(newDestinationFloor);

	}

	/**
	 * this method set the current floor to undefined
	 * 
	 */
	public void boardElevator() {
		setCurrentFloor(UNDEFINED_FLOOR);
	}

	/**
	 * this method set the current floor to destination floor
	 * 
	 * It then set the destination floor to undefined
	 * 
	 */
	public void arrive() {
		setCurrentFloor(getDestinationFloor());
		setDestinationFloor(UNDEFINED_FLOOR);
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
	 * @return the currentFloor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * @param currentFloor
	 *            the currentFloor to set
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * a toString method defined for the purpose of debugging
	 * 
	 */
	public String toString() {
		return String.format("current floor: %d%n" + "destination floor: %d", currentFloor, destinationFloor);

	}

}
