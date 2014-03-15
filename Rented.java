package VideoStore;

import java.util.Date;

public class Rented {

        private String movie;
        private int quantity;
        private double price;
        private Date expDate;
        private String username;
        
        public Rented() {}
        
        public Rented(String movie, int quantity, double price, Date expDate, String username) {   
        	this.movie = movie;
        	this.quantity = quantity;
        	this.price = price;
        	this.expDate = expDate;
        	this.username = username;
        }

        public String getMovie() {
                return movie;
        }

        public void setMovie(String movie) {
                this.movie = movie;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
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
        @Override
        public String toString() {
            return "ID: " + this.getUsername() + 
                   ", Name: " + this.getExpDate();
        }
}