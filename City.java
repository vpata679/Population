/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	
 *	@since	
 */
public class City implements Comparable<City> {
	
	// fields
	private String city, state, cityType; //name of city, state, & city type
	private int population;
	
	// constructor, initializes fields with input
	public City(String cityName, String stateName, String typeOfCity, int popAmount)
	{
		city = cityName; state = stateName; cityType = typeOfCity;
		population = popAmount;
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
		if (this.population != other.population)
		{
			return this.population - other.population;
		}
 		else if (!this.state.equals(other.state))
			return this.state.compareTo(other.state)
		return this.name.compareTo(other.name)
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other)
	 {
		 if (this.city.equals(other.city) && this.state.equals(other.state))
			return true;
		return false;
	 }
	
	/**	Accessor methods */
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getCityType()
	{
		return cityType;
	}
	
	public int getPopulation()
	{
		return population;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
