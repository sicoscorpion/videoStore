package VideoStore;
/**
 * A movie object definition
 * @author Fady Abdelmohsen 100106924
 * NOTE: This object is not yet complete, a genre field will be added
 */

public class Movie {
		// TODO add genre
        private String name;
        private double rentAmount;
        private double buyAmount;
        private int nbAvailable;
        
        public Movie() {}
        
        public Movie(String name, double rentAmount, double buyAmount, 
        		int nbAvailable) {   
        	this.name = name;
        	this.rentAmount = rentAmount;
        	this.buyAmount = buyAmount;
        	this.nbAvailable = nbAvailable;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getRentAmount() {
                return rentAmount;
        }

        public void setRentAmount(double rentAmount) {
                this.rentAmount = rentAmount;
        }
        public double getBuyAmount() {
            return buyAmount;
        }

        public void setBuyAmount(double buyAmount) {
            this.buyAmount = buyAmount;
        }

        public int getNbAvailable() {
                return nbAvailable;
        }

        public void setNbAvailable(int nbAvailable) {
                this.nbAvailable = nbAvailable;
        }
        @Override
        public String toString() {
            return "ID: " + this.getNbAvailable() + 
                   ", Name: " + this.getName();
        }
}