package VideoStore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class main {	
	public static void main(String[] args)  throws IOException {
  		storeGUI store = new storeGUI();
  		store.setVisible(true);
  		FileManager csv = new FileManager();
  		RentOperations rp = new RentOperations();
//  		csv.updateMovieInventory("The Day After Tomorrow", "20", "src/movies.csv", "num");
//        csv.put(3, 0, "3");
//        csv.save(new File("src/movies.csv"));

  		ArrayList<Person> people = new ArrayList<Person>();
  		Person p1 = new Person();
  		p1 = csv.findPerson("fadsy", "se1234", "src/people.csv");
  		ArrayList<Movie> movies = new ArrayList<Movie>();
  		Movie mov1 = new Movie();
  		mov1 = csv.findMovie("Mr Nice guy", "src/movies.csv");
  		BuyOperations.buyMovie(mov1, p1, 1, true);
//  		people.add(p1);
  		
//  		csv.updatePeopleList("fady", "se4344", "src/people.csv", "pass");
  		ArrayList<Movie> movieList = new ArrayList<Movie>();
  		Movie mov2 = new Movie();
  		mov2.setName("The Day After Tomorrow");
  		mov2.setBuyAmount(22.00);
  		mov2.setRentAmount(13.00);
  		mov2.setNbAvailable(16);
  		movieList.add(mov2);
//  		System.out.println(mov1);
  		csv.addNewMovie(movieList, "src/movies.csv");
	}
}