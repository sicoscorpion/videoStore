package VideoStore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class main {	
	public static void main(String[] args)  throws IOException {
//  		storeGUI rectObj = new storeGUI();
  		FileManager csv = new FileManager();
//  		csv.updateItem("Gone With the wind", "20", "src/people.csv", "num");
//        csv.put(3, 0, "3");
//        csv.save(new File("src/movies.csv"));

  		ArrayList<Person> people = new ArrayList<Person>();
  		Person p1 = new Person("Fady", "Abdelmohsen", "fady", "se1234", "user");
  		people.add(p1);
  		csv.updatePeopleList("Fady", "se4444", "src/people.csv", "pass");
//  		Movie mov2 = new Movie();
//  		mov2.setName("The Hulk");
//  		mov2.setBuyAmount(28.00);
//  		mov2.setRentAmount(12.00);
//  		mov2.setNbAvailable(14);
//  		movieList.add(mov2);
  		System.out.println(people);
//  		csv.addNewPerson(people, "src/people.csv");
  		
  	}
}