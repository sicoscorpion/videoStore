package VideoStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class BuyOperations extends FileManager {
	
	private static final String CSV_SEPARATOR = ",";
	public static boolean buyMovie(Movie movie, Person person, 
			int quantity, boolean paid) throws IOException {
//		System.out.println("RENTED");
		FileManager csv = new FileManager();
		if (person.getUsername() == null || movie.getName() == null) {
			System.out.println("rent failed");
			return false;
		} else {
			if (movie.getNbAvailable() >= quantity && paid == true) {
				int remaining = movie.getNbAvailable() - quantity;
				double price = movie.getBuyAmount() * quantity;
				Bought bought = new Bought(movie.getName(), quantity, price, person.getUsername(), paid);
				boolean retBought = addNewBuy(bought, "src/boughtMovies.csv");
				if (retBought == true) {
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
	public static boolean addNewBuy(Bought bought, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw, true);
		FileManager csv = new FileManager();
		
		if (csv.findOne(bought.getMovie(), file) == true &&
			csv.findOne(bought.getUsername(), file) == true) {
			boolean ret = updateBuyList(bought, Integer.toString(bought.getQuantity()), file, "quantity");
			if (ret == true) {
				System.out.println("User already bought a copy, adding new copy to bought list");
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
	public static boolean updateBuyList(Bought bought, String val, String file, String type) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		int row = 0, pos = 0;
		FileManager csv = new FileManager();
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			for (int i = 0; i < items.length; i++){
				if (new String(items[i]).equals(bought.getMovie()) &&
						new String(items[3]).equals(bought.getUsername())) {
					System.out.println(items[i]);
					pos = row;
				}
			}
			row++;
		}
		reader.close();
		System.out.println(pos);
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
}