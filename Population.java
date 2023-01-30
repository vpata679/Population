import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
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
		//prints intro and calls loadCities method to read in cities
		printIntroduction();
		loadCities();
		
		// makes an array of all 50 state names
		String[] states = new String[50];
		int counter = 0;
		for (int i=0; i<cities.size(); i++) 
		{
			if (i==0 || !cities.get(i).getState().equals(cities.get(i-1).getState())) 
			{
				states[counter] = cities.get(i).getState();
				counter++;
			}
		}
		
		int choice = 0;
		
		// code runs until user quits
		while (choice != 9) 
		{
			printMenu();
			choice = Prompt.getInt("\nEnter selection");
			
			while ((choice < 1 || choice > 6) && choice != 9)
				choice = Prompt.getInt("Enter selection");
			
			long startTime = System.currentTimeMillis();
			// if user chose 1
			if (choice == 1) 
			{
				asSelectionSort();

				System.out.println("Fifty least populous cities");
				System.out.printf("\n%10s%25s%25s%20s\n", "State", "City", "Type", "Population");
				for (int i = 1; i <= 50; i++)
				{
					City temp = cities.get(i -  1);
					System.out.printf("%3d: %-25s%-26s%-14s%10s\n", i, temp.getState(),
					 temp.getName(), temp.getType(), printConvert(temp.getPop()));
				}
			}
			// if user chose 2
			if (choice ==  2) 
			{
				deMergeSortPop(0, cities.size() - 1);

				System.out.println("Fifty most populous cities");
				System.out.printf("\n%10s%25s%25s%20s\n", "State", "City", "Type", "Population");
				for (int i = 1; i <= 50; i++) 
				{
					City temp = cities.get(i - 1);
					System.out.printf("%3d: %-25s%-26s%-14s%10s\n", i, temp.getState(),
					 temp.getName(), temp.getType(), printConvert(temp.getPop()));
				}
			}
			// if user chose 3
			if (choice == 3) 
			{
				asInsertionSort();

				System.out.println("Fifty cities sorted by name");
				System.out.printf("\n%10s%25s%25s%20s\n", "State", "City", "Type", "Population");
				for (int i = 1; i <= 50; i++) 
				{
					City temp = cities.get(i - 1);
					System.out.printf("%3d: %-25s%-26s%-14s%10s\n", i, temp.getState(),
					 temp.getName(), temp.getType(), printConvert(temp.getPop()));
				}
			}
			// if user chose 4
			if (choice==4)
			{
				deMergeSortName(0, cities.size() - 1);

				System.out.println("Fifty cities sorted by name descending");
				System.out.printf("\n%10s%25s%25s%20s\n", "State", "City", "Type", "Population");
				for (int i = 1; i <= 50; i++) 
				{
					City temp = cities.get(i - 1);
					System.out.printf("%3d: %-25s%-26s%-14s%10s\n", i, temp.getState(),
					 temp.getName(), temp.getType(), printConvert(temp.getPop()));
				}
			}
			long endTime = System.currentTimeMillis();
			// prints how long sorting took for choice 1-4
			if (choice < 5)
				System.out.println("\nElapsed Time "+(endTime - startTime) +" milliseconds\n");

			// if user 5
			if (choice==5)
			{
				//prompts user for state
				String stateName = Prompt.getString("Enter stateName name (i.e. Alabama)");
				boolean isValid = false;
				for (int i = 0; i < 50; i++)
				{
					if (states[i].equals(stateName))
					{
						isValid = true;
					}
				}
				// keep prompting until valid state
				while (!isValid) 
				{
					System.out.println("ERROR: " + stateName + " is not valid");
					stateName = Prompt.getString("Enter stateName name (i.e. Alabama)");
					isValid = false;
					for (int i = 0; i < 50; i++)
					{
						if (states[i].equals(stateName))
						{
							isValid = true;
						}
					}
				}
				// sorts array by state w mergeSort
				deMergeStateCity(stateName, 0, cities.size() - 1);
				
				System.out.println("Fifty most populous cities in "+stateName);
				System.out.printf("\n%10s%25s%25s%20s\n", "State", "City", "Type", "Population");
				for (int i = 1; i <= 50; i++) 
				{
					City temp = cities.get(i - 1);
					System.out.printf("%3d: %-25s%-26s%-14s%10s\n", i, temp.getState(),
					 temp.getName(), temp.getType(), printConvert(temp.getPop()));
				}
				System.out.println();
			}
			// if user chose 6
			if (choice == 6) 
			{
				// prompts user for city, then calls method to sort
				String cityName = Prompt.getString("\nEnter cityName name");
				deSelectionSortCity(cityName);

				System.out.println("City "+cityName + " by population");
				System.out.printf("\n%10s%25s%25s%20s\n", "State", "City", "Type", "Population");
				for (int i = 1; i <= cities.size() && cities.get(i - 1).getName().equals(cityName);
						i++) 
				{
					City temp = cities.get(i - 1);
					System.out.printf("%3d: %-25s%-26s%-14s%10s\n", i, temp.getState(),
					 temp.getName(), temp.getType(), printConvert(temp.getPop()));
				}
				System.out.println();
			}
		}
				
		System.out.println("\nThanks for using population!\n");	
			
	}
	
	//reads in city data from txt and imports data into our cities list
	public void loadCities()
	{
		int cityAmount = 0;
		Scanner input = FileUtils.openToRead("usPopData2017.txt");
		input = input.useDelimiter("[\t\n]");
		while (input.hasNext())
		{
			String stateName = input.next();
			String cityName = input.next();
			String cityType = input.next();
			int population = Integer.parseInt(input.next());
			cities.add(new City(stateName, cityName, cityType, population));
			cityAmount++;
		}  
		System.out.println("\n" + cityAmount + " cities in database\n");
	}

	/**
	 * @param int num - number to be converted
	 * @return num as a string with commas
	 * helper method that converts an integer to printable string with commas
	 */
	public String printConvert(int num) {
		String out = Integer.toString(num);
		int lo = out.length();
		for (int i=3; i<lo; i+=3)
			out = out.substring(0, lo-i)+","+out.substring(lo-i);
		return out;
	}
	
	/**
	 * sorts in ascending order with selectionSort by city
	 */
	public void asSelectionSort()
	{
		for (int i=0; i<cities.size(); i++) 
		{
			//searches for min
			int min = i;
			for (int j=1; j<i; j++) {
				if (cities.get(j).compareTo(cities.get(min))<0)
					min = j;
			}
			
			//swaps two locations
			City temp = cities.get(min);
			cities.set(min, cities.get(i));
			cities.set(i, temp);
		}
	}
	
	/**
	 * @param int low - lowest index, int high - highest index
	 * sorts cities by descending order with mergeSort by population
	 */
	public void deMergeSortPop(int low, int high) {
		int mid = (low+high)/2;
		if (low == high) // base case
			return;

		// recursively sort
		deMergeSortPop(low, mid);
		deMergeSortPop(mid+1, high);

		// keep track of sorted version
		City[] temp = new City[low - high +1];
		int lo = low;
		int middle = mid+1;
		int i = 0;
		while (lo<=mid || middle<=high) 
		{
			if (lo>mid) 
			{
				temp[i] = cities.get(middle);
				middle++;
			} 
			else if (middle > high) {
				temp[i] = cities.get(lo);
				lo++;
			} 
			else if (cities.get(lo).compareTo(cities.get(middle)) > 0) 
			{
				temp[i] = cities.get(lo);
				lo++;
			} 
			else 
			{
				temp[i] = cities.get(middle);
				middle++;
			}
			i++;
		}

		// apply sorted version
		for (i = 0; i < temp.length; i++)
			cities.set(low+i, temp[i]);
	}
	
	/**
	 * sorts in ascending order with Insertion Sort by city name
	 */
	public void asInsertionSort() {
		for (int outer = 1; outer < cities.size(); outer++) 
		{
			City temp = cities.get(outer);
			
			int inner = outer;
			while (inner > 0 && temp.compareTo(cities.get(inner-1)) < 0)
			{
				cities.set(inner, cities.get(inner-1));
				inner--;
			}
			cities.set(inner, temp);
		}
	}
	
	/**
	 * @param int low - lowest index, int high - highest index	 
	 * sorts in descending order with MergeSort by city name
	 */
	public void deMergeSortName(int low, int high) {
		int mid = (low + high)/2;
		if (low == high) // base case
			return;

		// recursively sort
		deMergeSortName(low, mid);
		deMergeSortName(mid+1, high);

		// keep track of sorted version
		City[] temp = new City[low - high +1];
		int lo = low;
		int middle = mid+1;
		int i = 0;
		while (lo <= mid || middle <= high) 
		{
			if (lo > mid) 
			{
				temp[i] = cities.get(middle);
				middle++;
			} 
			else if (middle >= high)
			{
				temp[i] = cities.get(lo);
				lo++;
			} 
			else if (cities.get(lo).compareToName(cities.get(middle)) > 0) 
			{
				temp[i] = cities.get(lo);
				lo++;
			} 
			else 
			{
				temp[i] = cities.get(middle);
				middle++;
			}
			i++;
		}

		for (i = 0; i < temp.length; i++)
			cities.set(low+i, temp[i]);
	}
	
	/**
	 * @param String stateName with priority, int low is lowest index, int high is highest index
	 * sorts cities in designated state by population in descending order w mergeSort
	 */
	public void deMergeStateCity(String stateName, int low, int high) 
	{
		int mid = (low + high)/2;
		if (low == high) // base case
			return;

		// recursion
		deMergeStateCity(stateName, low, mid);
		deMergeStateCity(stateName, mid+1, high);

		// sort into temp array
		City[] temp = new City[low - high +1];
		int lo = low;
		int middle = mid+1;
		int i = 0;
		while (lo <= mid || middle <= high) {
			// if city's state is same as input state, top of the list
			if (lo <= mid && cities.get(lo).getState().equals(stateName) &&
			 (middle > high || !cities.get(middle).getState().equals(stateName)))
			{
				temp[i] = cities.get(lo);
				lo++;
			} 
			else if ((lo > mid || !cities.get(lo).getState().equals(stateName))
			 && middle <= high && cities.get(middle).getState().equals(stateName)) 
			{
				temp[i] = cities.get(middle);
				middle++;
			} 
			else if (lo > mid) 
			{
				temp[i] = cities.get(middle);
				middle++;
			} 
			else if (middle > high) 
			{
				temp[i] = cities.get(lo);
				lo++;
			} 
			else if (cities.get(lo).compareTo(cities.get(middle)) > 0)
			{
				temp[i] = cities.get(lo);
				lo++;
			} 
			else
			{
				temp[i] = cities.get(middle);
				middle++;
			}
			i++;
		}

		// apply sorted array
		for (i = 0; i < temp.length; i++)
			cities.set(low + i, temp[i]);
	}
	
	/**
	 * @param cityName - cityName with priority
	 * Sorts all cities w designated names descending by population
	 */
	public void deSelectionSortCity(String cityName) 
	{
		for (int i = 0; i < cities.size(); i++) 
		{
			// find cities with name, compare with population next
			City temp = null;
			int in = 0;
			for (int j = i; j < cities.size(); j++)
			{
				if (cities.get(j).getName().equals(cityName) && (temp==null ||
				 cities.get(j).compareTo(temp) > 0)) 
				{
					temp = cities.get(j);
					in = j;
				}
			}
			// if no more cities with name, done
			if (temp == null)
				return;
			// swap
			cities.set(in, cities.get(i));
			cities.set(i, temp);
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
