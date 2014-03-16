package VideoStore;

/**
 * Class responsible for all buy operations and data storing
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BuyOperations extends FileManager {
	
	private static final String CSV_SEPARATOR = ",";
	
	// TODO implement method pay(movie:Movie, person:Person, quantity:int)
	/**
	 * Implements the buying function
	 * @param movie a Movie object
	 * @param person a Person object
	 * @param quantity the quantity to be bought
	 * @param paid the output of the pay function
	 * @return true on success; false otherwise
	 * @throws IOException
	 */
	public static boolean buyMovie(Movie movie, Person person, 
			int quantity, boolean paid) throws IOException {
		FileManager csv = new FileManager();
		if (person.getUsername() == null || movie.getName() == null) {
			System.out.println("Buy failed");
			return false;
		} else {
			if (movie.getNbAvailable() >= quantity && paid == true) {
				int remaining = movie.getNbAvailable() - quantity;
				double price = movie.getBuyAmount() * quantity;
				Bought bought = new Bought(movie.getName(), quantity, price,
						person.getUsername(), paid);
				boolean retBought = addNewBuy(bought, "src/boughtMovies.csv");
				if (retBought == true) {
					boolean retUpdate = csv.updateMovieInventory(movie.getName(), 
							Integer.toString(remaining)
							, "src/movies.csv", "num");
					if(retUpdate == true) System.out.println("Buy saved");
					else {
						System.out.println("Buy failed");
						return false;
					}
				}
				else System.out.println("Buy failed");
			} else {
				System.out.println("Buy failed");
			}
		}
		return true;
	}
	/**
	 * Add to bought list (bought.csv)
	 * @param bought the Bought object
	 * @param file to be save in (bought.csv)
	 * @return true on success; false otherwise
	 * @throws IOException
	 */
	public static boolean addNewBuy(Bought bought, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw, true);
		FileManager csv = new FileManager();
		
		if (csv.findOne(bought.getMovie(), file) == true &&
			csv.findOne(bought.getUsername(), file) == true) {
			boolean ret = updateBuyList(bought, Integer.toString(bought.getQuantity()), 
					file, "quantity");
			if (ret == true) {
				System.out.println("User already bought a copy, adding "
						+ "new copy to bought list");
				return true;
			} else {
				System.out.println("failed to update bought list");
				return false;
			}
		}
		pw.append(bought.getMovie());
		pw.append(CSV_SEPARATOR);
		pw.print(bought.getQuantity());
		pw.append(CSV_SEPARATOR);
		pw.print(bought.getPrice());
		pw.append(CSV_SEPARATOR);
		pw.print(bought.getUsername());
		pw.append(CSV_SEPARATOR);
		pw.print(bought.getPaid());
		pw.print("\n");
		
        pw.flush();
        pw.close();
        fw.close();
        return true;
	}
	/**
	 * Updates the bought list (bought.csv) 
	 * used if the same user bought the same movie twice or by admin
	 * 
	 * @param bought the bought object
	 * @param val the amount of movies
	 * @param file the
	 * @param file to be save in (bought.csv)
	 * @return true on success; false otherwise
	 * @throws IOException
	 */
	public static boolean updateBuyList(Bought bought, String val, String file
			, String type) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		int row = 0, pos = 0;
		FileManager csv = new FileManager();
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			for (int i = 0; i < items.length; i++){
				if (new String(items[i]).equals(bought.getMovie()) &&
						new String(items[3]).equals(bought.getUsername())) {
					pos = row;
				}
			}
			row++;
		}
		reader.close();
		csv.open(new File(file));
		
		if (type == "quantity") {
			csv.put(1, pos, val);
			Double oldPrice = Double.parseDouble(csv.get(2, pos));
			Double newPrice = oldPrice + (Integer.parseInt(val) * bought.getPrice());
			csv.put(2, pos, Double.toString(newPrice));
		} else {
			System.out.println("Unknow type: " + type);
			return false;
		}
		csv.save(new File(file));
		System.out.println("Update Complete");
		return true;
	}
	
	// TODO implement method generateRecipt(bought:Bought)
}