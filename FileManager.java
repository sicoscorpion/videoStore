package VideoStore;

import java.io.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
 
public class FileManager {
 
	private static final String CSV_SEPARATOR = ",";
    private static HashMap<Point, String> _map = new HashMap<Point, String>();
    private static int _cols;
    private static int _rows;
 
    public static void open(File file) throws FileNotFoundException, IOException {
        open(file, ',');
    }
 
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
 
    public static void save(File file) throws IOException {
        save(file, ',');
    }
 
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
 
    public String get(int col, int row) {
        String val = "";
        Point key = new Point(col, row);
        if (_map.containsKey(key)) {
            val = _map.get(key);
        }
        return val;
    }
 
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
    
    public static int updateMovieInventory(String name, String val, String file, String type) throws IOException{
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
			return 1;
		}
		
		save(new File(file));
		System.out.println("Update Complete");
		return 0;
    }
    
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
    public static void addNewMovie(ArrayList<Movie> sampleList, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw, true);
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
        }
        pw.flush();
        pw.close();
        fw.close();
	}
    public static void addNewPerson(ArrayList<Person> sampleList, String file) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw, true);
		for (Person person : sampleList)
        {
			if (findOne(person.getFirstName(), file) == true) {
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
			pw.print(person.getRole());
			pw.print("\n");
        }
        pw.flush();
        pw.close();
        fw.close();
	}
    
    public static int updatePeopleList(String name, String val, String file, String type) throws IOException{
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
		
		if (type == "fname") {
			put(1, pos, val);
		} else if (type == "lname") {
			put(2, pos, val);
		} else if (type == "pass") {
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
    
}