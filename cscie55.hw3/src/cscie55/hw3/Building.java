package cscie55.hw3;

/**
 * Building Java class
 * 
 * @author Andrew Ogah
 * @since 11-10-2015
 * @version 1.0
 */
public class Building {

	public static final int FLOORS = 7; // Static final field constant for
	private Elevator elevator;
	private Floor[] floors; // the number of floors in the building

	/**
	 * no name constructor that initializes the elevator's state.
	 * 
	 */
	public Building() {

		setElevator(new Elevator(this));
		floors = new Floor[FLOORS];
		for (int i = 0; i < FLOORS; i++)
			floors[i] = new Floor(this, i + 1);
	}

	/**
	 * @return the elevator
	 */
	public Elevator elevator() {
		return elevator;

	}

	/**
	 * @return floor
	 */
	public Floor floor(int floorNumber) {

		return floors[floorNumber - 1];

	}

	/**
	 * @return the destinationFloor
	 */
	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * @param elevator
	 *            the elevator to set
	 */
	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}

	/**
	 * @param passenger
	 * 
	 *            the destinationFloor
	 */
	public void enter(Passenger passenger) {
		floors[0].enterGroundFloor(passenger);

	}

}
