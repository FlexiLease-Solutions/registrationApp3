package registrationapp2;

public class Login {
    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 
            && password.matches(".*[A-Z].*") 
            && password.matches(".*[0-9].*") 
            && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }
    public boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27[0-9]{9}$");
    }
    public String registerUser(String u, String p, String cell, String f, String l) {
        if (!checkUserName(u)) return "Username is not correctly formatted";
        if (!checkPasswordComplexity(p)) return "Password is not correctly formatted";
        if (!checkCellPhoneNumber(cell)) return "Cell phone number is not correctly formatted";
        this.username = u; this.password = p; this.cellNumber = cell; this.firstName = f; this.lastName = l;
        return "User registered successfully";
    }
    public boolean loginUser(String u, String p) {
        return u.equals(username) && p.equals(password);
    }
    public String returnLoginStatus(boolean isLoggedIn) {
        return isLoggedIn ? "Welcome " + firstName + " " + lastName + " it is great to see you again." : "Username or password incorrect, please try again.";
    }
}
