/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Vibhav Pata
 *	@since	Jan 16
 */
public class City implements Comparable<City>
 {
	// fields
	private String cityName, stateName, cityType;
	private int population;
	
	// constructor
	public City(String state, String city, String type, int pop)
	{
		cityName = city;
		stateName = state;
		cityType = type;
		population = pop;
	} 
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) 
	{
		if (population != other.population)
			return population - other.population;
			
		else if (stateName != other.stateName)
			return stateName.compareTo(other.stateName);
			
		else 
			return cityName.compareTo(other.cityName);
	}

	/**
	 * Compares two cities names
	 * @param other - the city comparing to
	 * @return compare value
	 */
	public int compareToName(City other) 
	{
		if (!cityName.equals(other.cityName)) 
			return cityName.compareTo(other.cityName);
			
		else if (population!=other.population)
			return other.population-population;
			
		else 
			return stateName.compareTo(other.stateName);
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other) {
		if (cityName.equals(other.cityName) && stateName.equals(other.stateName))
			return true;
		return false;
	}
	
	/**	Accessor methods */
	public String getName() {return cityName;}
	
	public String getState() {return stateName;}
	
	public String getType() {return cityType;}
	
	public int getPop() {return population;}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}
