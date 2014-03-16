package VideoStore;

import java.util.Date;

public class Bought {

        private String movie;
        private int quantity;
        private double price;
        private String username;
        private boolean paid;
        
        public Bought() {}
        
        public Bought(String movie, int quantity, double price, String username, boolean paid) {   
        	this.movie = movie;
        	this.quantity = quantity;
        	this.price = price;
        	this.username = username;
        	this.paid = paid;
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
                   ", Name: " + this.getPrice();
        }
}