package cscie55.hw2;


import cscie55.hw2.Floor;

/**
 * Building Java class
 * 
 * @author Andrew Ogah
 * @since 10-14-2015
 * @version 1.0
 */

public class Building {

	public static final int FLOORS = 7; // Static final field constant for
	private Elevator elevator;
	private Floor[] floors = new Floor[FLOORS]; // the number of floors in the
	                                            // building

	public Building() {
		setElevator(new Elevator(this));
		for (int i = 0; i < FLOORS; i++)
			floors[i] = new Floor(this, i + 1);
	}

	
	/**
	 * This method returns the elevator.
	 */
	public Elevator elevator() {
		return elevator;

	}
	
	
	/**
	 * This method returns the floor object for each floors .
	 */

	public Floor floor(int floorNumber) {

		return floors[floorNumber - 1];

	}

	/**
	 * @return the elevator
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

}
