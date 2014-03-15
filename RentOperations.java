package VideoStore;

import java.util.ArrayList;
import java.util.Date;

public class RentOperations {
	public static boolean rentMovie(Movie movie, Person person, 
			Date expDate, int quantity) {
		if (movie.getNbAvailable() <= quantity) {
			Rented rented = new Rented(movie.getName(), quantity, 
					movie.getRentAmount(), expDate, person.getUsername());
			System.out.println(rented);
		}
		return true;
	}
}