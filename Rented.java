package VideoStore;
import java.util.Date;

/**
 * Rented objects definition
 * @author Fady Abdelmohsen 100106924
 * 
 */

public class Rented {

        private String movie;
        private double price;
        private Date expDate;
        private String username;
        private boolean paid;
        
        public Rented() {}
        
        public Rented(String movie, double price, Date expDate, String username, boolean paid) {   
        	this.movie = movie;
        	this.price = price;
        	this.expDate = expDate;
        	this.username = username;
        	this.paid = paid;
        }

        public String getMovie() {
        	return movie;
        }

        public void setMovie(String movie) {
        	this.movie = movie;
        }
        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
        public Date getExpDate() {
            return expDate;
        }

        public void setExpDate(Date expDate) {
            this.expDate = expDate;
        }
        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }
        
        public boolean getPaid() {
            return paid;
        }

        public void setpaid(boolean paid) {
            this.paid = paid;
        }
        
        @Override
        public String toString() {
            return "ID: " + this.getUsername() + 
                   ", Name: " + this.getExpDate();
        }
}