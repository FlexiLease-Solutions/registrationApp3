package registrationapp2;

public class Login {
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;

    // Regex reference for cell check:
    // Adapted from: https://stackoverflow.com/questions/55172995/regex-for-south-african-phone-number
    // Validates SA international code +27 and 9 digits

    public Boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public Boolean checkPasswordComplexity(String password) {
        boolean length = password.length() >= 8;
        boolean capital = !password.equals(password.toLowerCase());
        boolean number = password.matches(".*\\d.*");
        boolean special = password.matches(".*[^a-zA-Z0-9].*");
        return length && capital && number && special;
    }

    public Boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password, String cell, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        this.storedUsername = username;
        this.storedPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;
        return "The two above conditions have been met and the user has been registered successfully.";
    }

    public Boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Helper messages for unit tests - exactly as per Page 10
    public String getUsernameMessage(String username) {
        if (checkUserName(username)) return "Username successfully captured.";
        else return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
    }
    public String getPasswordMessage(String password) {
        if (checkPasswordComplexity(password)) return "Password successfully captured.";
        else return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }
    public String getCellMessage(String cell) {
        if (checkCellPhoneNumber(cell)) return "Cell number successfully captured.";
        else return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }
}