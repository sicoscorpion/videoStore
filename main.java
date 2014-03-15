package VideoStore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class main {	
	public static void main(String[] args)  throws IOException {
//  		storeGUI rectObj = new storeGUI();
  		FileManager csv = new FileManager();
  		RentOperations rp = new RentOperations();
//  		csv.updateItem("Gone With the wind", "20", "src/movies.csv", "num");
//        csv.put(3, 0, "3");
//        csv.save(new File("src/movies.csv"));

  		ArrayList<Person> people = new ArrayList<Person>();
  		Person p1 = new Person();
  		p1 = csv.findPerson("fady", "se4344", "src/people.csv");
  		ArrayList<Movie> movies = new ArrayList<Movie>();
  		Movie mov1 = new Movie();
  		mov1 = csv.findMovie("The Amazing Spiderman", "src/movies.csv");
  		rp.rentMovie(mov1, p1, new Date(12, 02, 2014), 1);
//  		people.add(p1);
  		
//  		csv.updatePeopleList("fady", "se4344", "src/people.csv", "pass");
//  		Movie mov2 = new Movie();
//  		mov2.setName("The Hulk");
//  		mov2.setBuyAmount(28.00);
//  		mov2.setRentAmount(12.00);
//  		mov2.setNbAvailable(14);
//  		movieList.add(mov2);
//  		System.out.println(mov1);
//  		csv.addNewPerson(people, "src/people.csv");
  		
  	}
}