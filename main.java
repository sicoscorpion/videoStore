package VideoStore;

/**
 * VideoStore project test
 * @author Fady Abdelmohsen 
 * 		   Rayan Alamer
 * 		   Bill Chen
 */


import java.io.*;
import java.awt.EventQueue;
import java.util.ArrayList;

public class main {	
	public static void main(final String[] args)  throws IOException {
		// GUI test
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				storeGUI store = new storeGUI();
				store.setVisible(true);
			}
		});
  		FileManager csv = new FileManager();
  		// adding a person
  		ArrayList<Person> people = new ArrayList<Person>();
  		Person p1 = new Person("Fady", "Abdelmohsen", "fed", "se1p", 
  				"9023334445", "fady@email.com", "user");
  		people.add(p1);
  		csv.addNewPerson(people, "src/people.csv");
  		// adding a movie
  		ArrayList<Movie> movieList = new ArrayList<Movie>();
  		Movie mov1 = new Movie();
  		mov1.setName("The Day After Tomorrow");
  		mov1.setBuyAmount(22.00);
  		mov1.setRentAmount(13.00);
  		mov1.setNbAvailable(16);
  		movieList.add(mov1);
  		csv.addNewMovie(movieList, "src/movies.csv");
  		// buying a movie
		p1 = csv.findPerson("fed", "se1p", "src/people.csv");
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie mov2 = new Movie();
		mov2 = csv.findMovie("The Day After Tomorrow", "src/movies.csv");
		BuyOperations.buyMovie(mov2, p1, 1, true);
	}
}