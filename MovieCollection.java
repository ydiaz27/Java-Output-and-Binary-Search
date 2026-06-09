import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MovieCollection {
    private Movie[] movies;
	private int numMovies;
	private PrintWriter fileOut;
	
	public static final int MAX_MOVIES = 500;
	
	public MovieCollection() {
		movies = new Movie[MAX_MOVIES];
		loadArray();
		sortCollection();
		try {
			fileOut = new PrintWriter("movieReport.txt");
		} catch (Exception ex) {
			System.out.println("Error: Unable to create report.");
			ex.printStackTrace();
		}
	}

	private void loadArray() {
		try {
			numMovies = 0;
			Scanner fileIn = new Scanner(new File("movies.txt"));
			fileIn.useDelimiter(";|\r\n|\n");
			while (fileIn.hasNext()) {
				String title = fileIn.next();
				int year = fileIn.nextInt();
				int hours = fileIn.nextInt();
				int minutes = fileIn.nextInt();
				String production = fileIn.next();
				
				movies[numMovies] = new Movie(title, production, year, hours, minutes);
				numMovies++;
			}
			
			fileIn.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	//Selection Sort 
	private void sortCollection() {
		for (int i = 0; i < numMovies - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < numMovies; j++) {
				if (compareMovies(movies[j], movies[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				Movie tmp = movies[i];
				movies[i] = movies[minIndex];
				movies[minIndex] = tmp;
			}
		}
	}

	private int compareMovies(Movie candidate, Movie bestSoFar) {
		int byCompany = candidate.getProductionCompany().compareToIgnoreCase(bestSoFar.getProductionCompany());
		if (byCompany != 0) return byCompany;
		return Integer.compare(bestSoFar.getYear(), candidate.getYear());
	}

	//Change Production + Year
	//Print Production Company Once 
	//Company + Year the Same as Prev Movie
	public void printCollection() {
		fileOut.printf("%-35s  %4s  %-55s  %8s  %n", "Production Company", "Year", "Title", "Duration");
		fileOut.printf("%-35s  %4s  %-55s  %8s  %n", "==================", "====", "=====", "========");
		
		String lastCompany = "";
		int lastYear = -1;

		for (int i = 0; i < numMovies; i++) {
			String currentCompany = movies[i].getProductionCompany();
			int currentYear = movies[i].getYear();
			String title = movies[i].getTitle();
			int duration = movies[i].getDuration();

			boolean finalCompany = !currentCompany.equals(lastCompany);
			boolean finalYear = finalCompany || currentYear != lastYear; 

			if (finalCompany) {
				fileOut.printf("%-35s  %4s  %-55s  %8s  %n", currentCompany, currentYear, title, duration);
			} else if (finalYear) {
				//If the same production company is shown
				fileOut.printf("%-35s  %4s  %-55s  %8s  %n", "", currentYear, title, duration);
			} else {
				//If the same production company and year is shown
				fileOut.printf("%-35s  %4s  %-55s  %8s  %n", "", "", title, duration);
			}

			lastCompany = currentCompany;
			lastYear = currentYear;
		}
		fileOut.println();
		fileOut.println("Total Number of Movies: " + numMovies);
		fileOut.println();
	}
	
	//Binary Search 
	public Movie binarySearch(String targetCompany) {
		int low = 0;
		int high = numMovies - 1;
		targetCompany = targetCompany.toLowerCase();

		while (low <= high) {
			int mid = low + (high - low) / 2; 
			String midCompany = movies[mid].getProductionCompany().toLowerCase();

			int comparison = targetCompany.compareTo(midCompany);

			if (comparison == 0) {
				return movies[mid]; //Found match, return Movie 
			} else if (comparison < 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return null; // Not found 
	}

	public void printCompanies() {
		String[] companies = new String[numMovies];
		int numCompanies = 0;
		
		for (int i = 0; i < numMovies; i++) {
			boolean found = false;
			for (int j = 0; j < numCompanies; j++) {
				if (companies[j].equals(movies[i].getProductionCompany())) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				companies[numCompanies] = movies[i].getProductionCompany();
				numCompanies++;
			}
		}
		
		fileOut.println("Production companies in collection:");
		for (int i = 0; i < numCompanies; i++) {
			fileOut.println(companies[i]);
		}
		
		fileOut.println();
		fileOut.println();
	}
	
	public void printMoviesByCompany(String company) {
		boolean found = false;
		
		for (int i = 0; i < numMovies; i++) {
			if (movies[i].getProductionCompany().equals(company)) {
				if (!found) {
					fileOut.println("Movies by company " + company + ":");
				}
				
				found = true;
				fileOut.println(movies[i].toString());
			}
		}
		
		if (!found) {
			fileOut.println("Company " + company + " not found.");
		}
	}
	
	public void endReport() {
		fileOut.close();
	}
}