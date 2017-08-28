package cscie55.hw2;

/**
 * Floor Java class
 * 
 * 
 * @author Andrew Ogah
 * @since 10-14-2015
 * @version 1.0
 */
public class Floor {

	private int floornumber;
	private Building building;
	private int numPassWaiting;

	/**
	 * No argument constructor that initializes the floor's state.
	 * 
	 */
	Floor() {

		numPassWaiting = 0;

	}

	/**
	 * constructor that initializes the floor's state.
	 * 
	 * @param building
	 *            the building object associated with this elevator
	 * @param floornumber
	 *            signifies the number associated with each floors
	 */

	Floor(Building building, int floornumber) {
		this.setBuilding(building);
		this.setFloornumber(floornumber);

	}

	/**
	 * @return the number of passengers waiting on each floor
	 */
	public int passengersWaiting() {
		return this.numPassWaiting;

	}

	/**
	 * @param numPass
	 *            the numPass to set
	 */

	public void setnumPassWaiting(int numPass) {
		this.numPassWaiting = numPass;

	}

	/**
	 * this method decrement the number of passengers waiting on the floor
	 * 
	 */

	public void decrementNumPassWaiting() {
		this.numPassWaiting -= 1;

	}

	/**
	 * this method is called when a passenger on the floor wants to wait for
	 * elevator.
	 * 
	 */
	public void waitForElevator() {
		numPassWaiting = numPassWaiting + 1;

	}

	/**
	 * @return the floor number.
	 */
	public int getFloornumber() {
		return floornumber;
	}

	/**
	 * @param floornumber
	 *            the floor number to set
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

}
