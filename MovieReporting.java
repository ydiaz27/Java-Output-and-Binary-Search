import java.util.Scanner;

public class MovieReporting {
    
	public static void main(String[] args) {
		MovieCollection movies = new MovieCollection();
		
		//Output the movie list 
		movies.printCollection();

		//Close the report 
		movies.endReport();

		//Ask User for company name + binary search
		Scanner input = new Scanner(System.in);
		boolean searchAgain = true;

		while (searchAgain) {
			System.out.println("Enter a Production Company to Search for: ");
			String companyName = input.nextLine();

			Movie result = movies.binarySearch(companyName);

			if (result != null) {
				System.out.println("Movie '" + result.getTitle() + "' was made by " + result.getProductionCompany() + ".");
			} else {
				System.out.println("Company " + companyName + " is not in the movie database.");
			}

			System.out.println("Would you like to look up another company (y/n)?");
			String response = input.nextLine().trim().toLowerCase();

			if (!response.equals("y")) {
				searchAgain = false;
				System.out.println("Exit Search.");
			}
		}

		input.close(); 

	}
}
