package VideoStore;

/**
 * A person object definition
 * @author Fady Abdelmohsen 100106924
 * Note: There was no need to seperate a reqular user object from 
 * an admin. The role field is added for distinction
 */

public class Person {
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String phone;
        private String email;
        private String role;
        
        public Person() {}
        
        public Person(String firstName, String lastName, String username, 
        		String password, String phone, String email, String role) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.username = username;
                this.password = password;
                this.phone = phone;
                this.email = email;
                this.role = role;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
        
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
        @Override
        public String toString() {
            return "Username: " + this.getUsername() + 
                   ", Role: " + this.getRole() + ", email: " + this.getEmail();
        }
}
