package VideoStore;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class RentOperations {
	
	private static final String CSV_SEPARATOR = ",";
	
	// TODO implement method pay(movie:Movie, person:Person)
	
	public static boolean rentMovie(Movie movie, Person person, 
			Date expDate, boolean paid) throws IOException {
		FileManager csv = new FileManager();
		if (person.getUsername() == null || movie.getName() == null) {
			System.out.println("rent failed");
			return false;
		} else {
			if (movie.getNbAvailable() >= 1 && paid == true) {
				int remaining = movie.getNbAvailable() - 1;
				
				Rented rented = new Rented(movie.getName(), 
					movie.getRentAmount(), expDate, person.getUsername(), paid);
				boolean retRented = addNewRent(rented, "src/rentedMovies.csv");
				if (retRented == true) {
					boolean retUpdate = csv.updateMovieInventory(movie.getName(), Integer.toString(remaining)
							, "src/movies.csv", "num");
					if(retUpdate == true) System.out.println("rent saved");
					else {
						System.out.println("rent failed");
						return false;
					}
				}
				else System.out.println("rent failed");
			} else {
				System.out.println("rent failed");
			}
		}
		return true;
	}
	public static boolean addNewRent(Rented rented, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw, true);
		FileManager csv = new FileManager();
		
		if (csv.findOne(rented.getMovie(), file) == true &&
			csv.findOne(rented.getUsername(), file) == true) {
			System.out.println("This Item exist, use the update function to update it");
			return false;
		}
		pw.append(rented.getMovie());
		pw.append(CSV_SEPARATOR);
		pw.print(rented.getPrice());
		pw.append(CSV_SEPARATOR);
		pw.print(rented.getExpDate());
		pw.append(CSV_SEPARATOR);
		pw.print(rented.getUsername());
		pw.append(CSV_SEPARATOR);
		pw.print(rented.getPaid());
		pw.print("\n");
		
        pw.flush();
        pw.close();
        fw.close();
        return true;
	}
	
	// TODO implement method generateRecipt(rented:Rented)

}