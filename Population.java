import java.util.List;
import java.util.Scanner;

/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Vibhav Pata
 *	@since	1-17-23
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	//constructor initializes field
	public Population()
	{
		cities = new ArrayList<>();
	}
	
	//main method, exits static
	public static void main(String[] args)
	{
		Population pp = new Population();
		pp.run();
	}
	
	//bulk of population code is here
	public void run()
	{
		loadCities();	
	}
	
	//reads in city data from txt and imports data into our cities list
	public void loadCities()
	{
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input = input.useDelimiter("[\t\n]");
		while(input.hasNext())
		{
			String stateName = input.next();
			String cityName = input.next();
			String cityType = input.next();
			int cityPopulation = input.next();
			cities.add(City(cityName, stateName, cityType, cityPopulation));
		}
	}
	
	/** Sort population based on ascedning order using selection sort
	 *	@param City arr		
	 *  @return void
	 */
    public void sortPopAscending(City arr)
    {
		
		for (int outer = arr.size(); outer > 1; outer--)
		{
			int max = 0;
			for (int inner = 1; inner < outer; inner++)
			{
				if(/*arr.get(max) < arr.get(inner])*/ arr.compareTo(cities.get(inner)) < 0)
				{
					max = inner;
				}
			}
			swap(arr, max, outer-1);
		}
	}

	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	
	
}
