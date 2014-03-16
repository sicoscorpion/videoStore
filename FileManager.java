package VideoStore;
import java.io.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
 
/**
 * 
 * Class responsible for csv file operation
 * Disclaimer: Some of the functions used here are borrowed from my assignment6
 * for COMP2113 for Dr. Rick Giles (Fady Abdelmohsen)
 */

public class FileManager {
 
	private static final String CSV_SEPARATOR = ",";
    private static HashMap<Point, String> _map = new HashMap<Point, String>();
    private static int _cols;
    private static int _rows;
	private static PrintWriter pw;
	
	/**
	 * Open a csv file with a comma delimiter
	 * @param file the file to be opened
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
    public static void open(File file) throws FileNotFoundException, IOException {
        open(file, ',');
    }
    
    /**
     * Open a csv file with different delimiter
     * @param file
     * @param delimiter
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void open(File file, char delimiter)
            throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(Character.toString(delimiter));
 
        clear();
 
        while(scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(Character.toString(delimiter));
 
            int col = 0;
            for ( String value: values ) {
                _map.put(new Point(col, _rows), value);
                _cols = Math.max(_cols, ++col);
            }
            _rows++;
        }
        scanner.close();
    }
    /**
	 * Save a csv file with a comma delimiter
	 * @param file the file to be opened
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
    public static void save(File file) throws IOException {
        save(file, ',');
    }
 
    /**
     * Open a csv file with different delimiter
     * @param file
     * @param delimiter
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void save(File file, char delimiter) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
 
        for (int row = 0; row < _rows; row++) {
            for (int col = 0; col < _cols; col++) {
                Point key = new Point(col, row);
                if (_map.containsKey(key)) {
                    bw.write(_map.get(key));
                }
 
                if ((col + 1) < _cols) {
                    bw.write(delimiter);
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    /**
     * get a field value from csv file
     * @param col column number
     * @param row row number
     * @return field value
     */
    public String get(int col, int row) {
        String val = "";
        Point key = new Point(col, row);
        if (_map.containsKey(key)) {
            val = _map.get(key);
        }
        return val;
    }
    /**
     * put a field value from csv file
     * @param col column number
     * @param row row number
     * @param field value
     */
    public static void put(int col, int row, String value) {
        _map.put(new Point(col, row), value);
        _cols = Math.max(_cols, col+1);
        _rows = Math.max(_rows, row+1);
    }
    
    public static void clear() {
        _map.clear();
        _cols = 0;
        _rows = 0;
    }
 
    public int rows() {
        return _rows;
    }
 
    public int cols() {
        return _cols;
    }
    /**
     * Update movies in file (movies.csv)
     * @param name the name of the movie
     * @param val the update value
     * @param file the file to use (movies.csv)
     * @param type the type of field to be updated
     * @return ture on success; false otherwise
     * @throws IOException
     */
    public boolean updateMovieInventory(String name, String val, String file, String type) throws IOException{
    	BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		int row = 0, pos = 0;
		
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			for (int i = 0; i < items.length; i++){
				if (new String(items[i]).equals(name)) {
					System.out.println(items[i]);
					pos = row;
				}
			}
			row++;
		}
		reader.close();
		System.out.println(pos);
		open(new File(file));
		
		if (type == "rent") {
			put(1, pos, val);
		} else if (type == "buy") {
			put(2, pos, val);
		} else if (type == "num") {
			put(3, pos, val);
		} else {
			System.out.println("Unknow type: " + type);
			return false;
		}
		save(new File(file));
		System.out.println("Update Complete");
		return true;
    }
    /**
     * Check if an item exists in a csv file
     * @param match the item to match
     * @param file the file to look in
     * @return true on success; false on failure
     * @throws IOException
     */
    public static boolean findOne(String match, String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			for (int i = 0; i < items.length; i++){
				if (new String(items[i]).equals(match)) {
					reader.close();
					return true;
				}
			}
		}
		reader.close();
		return false;
	}
    /**
     * Adds new movies to file 
     * @param sampleList the list of movies
     * @param file the file to save in (movies.csv)
     * @throws IOException
     */
    public static void addNewMovie(ArrayList<Movie> sampleList, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		pw = new PrintWriter(fw, true);
		for (Movie movie : sampleList)
        {
			if (findOne(movie.getName(), file) == true) {
				System.out.println("This Item exist, use the update function to update it");
				return;
			}
			pw.append(movie.getName());
			pw.append(CSV_SEPARATOR);
			pw.print(movie.getRentAmount());
			pw.append(CSV_SEPARATOR);
			pw.print(movie.getBuyAmount());
			pw.append(CSV_SEPARATOR);
			pw.print(movie.getNbAvailable());
			pw.print("\n");
			System.out.println("Added: " + movie);
        }
        pw.flush();
        pw.close();
        fw.close();
	}
    /**
     * Adds persons to file
     * @param sampleList the list of people to add
     * @param file the file to use (people.csv)
     * @throws IOException
     */
    public static void addNewPerson(ArrayList<Person> sampleList, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		pw = new PrintWriter(fw, true);
		for (Person person : sampleList)
        {
			if (findOne(person.getUsername(), file) == true) {
				System.out.println("This Item exist, use the update function to update it");
				return;
			}
			pw.append(person.getFirstName());
			pw.append(CSV_SEPARATOR);
			pw.print(person.getLastName());
			pw.append(CSV_SEPARATOR);
			pw.print(person.getUsername());
			pw.append(CSV_SEPARATOR);
			pw.print(person.getPassword());
			pw.append(CSV_SEPARATOR);
			pw.print(person.getPhone());
			pw.append(CSV_SEPARATOR);
			pw.print(person.getEmail());
			pw.append(CSV_SEPARATOR);
			pw.print(person.getRole());
			pw.print("\n");
			System.out.println("Added: " + person);
        }
        pw.flush();
        pw.close();
        fw.close();
	}
    /**
     * Edit a person item in file 
     * This will be available for admin or logged in users
     * @param username the username 
     * @param val the value to be updated to
     * @param file the file to use (people.csv)
     * @param type the field to edit
     * @return
     * @throws IOException
     */
    public static int updatePeopleList(String username, String val, String file, String type) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		int row = 0, pos = 0;
		
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			if (new String(items[2]).equals(username)) {
				pos = row;
			}
			row++;
		}
		reader.close();
		System.out.println(pos);
		open(new File(file));
		
		if (type == "fname") {
			put(1, pos, val);
		} else if (type == "lname") {
			put(2, pos, val);
		} else if (type == "pass") {
			put(3, pos, val);
		} else if (type == "phone") {
			put(3, pos, val);
		} else if (type == "email") {
			put(3, pos, val);
		} else if (type == "role") {
			put(4, pos, val);
		} else {
			System.out.println("Unknow type: " + type);
			return 1;
		}
		
		save(new File(file));
		System.out.println("Update Complete");
		return 0;
    }
    /**
     * Find a person in file (people.csv)
     * This will be used for login purposes
     * @param username the person user name 
     * @param password the person's password
     * @param file the file to use
     * @return a Person object with the user profile
     * @throws IOException
     */
    public static Person findPerson(String username, String password, String file) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Person person = new Person();
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			for (int i = 0; i < items.length; i++){
				if (items[i].equals(username) && items[3].equals(password)) {
					person = new Person(items[0], items[1], items[2], 
							items[3], items[4], items[5], items[6]);
					reader.close();
					return person;
				}
			}
		}
		reader.close();
		return person;
    }
    /**
     * Find a movie in file (movies.csv)
     * @param name the name of the movie
     * @param file the file to look in
     * @return a Movie object with the movie profile
     * @throws IOException
     */
    public static Movie findMovie(String name, String file) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Movie movie = new Movie();
		while ((line = reader.readLine()) != null) {
			String[] items = line.split(CSV_SEPARATOR);
			for (int i = 0; i < items.length; i++){
				if (items[i].equals(name)) {
					movie = new Movie(items[0], Double.parseDouble(items[1])
							, Double.parseDouble(items[2]), Integer.parseInt(items[3]));
					reader.close();
					return movie;
				}
			}
		}
		reader.close();
		return movie;
    }
}