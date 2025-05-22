import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserData class represents user data and provides methods to fetch and update user information.
 */
class UserData {
    private String name;
    private String fatherName;
    private String dob;
    private String email;
    private String city;
    private String state;
    private String pinCode;
    private String address;
    private String gender;
    private String maritalStatus;
    private String cardNumber;
    private String pin;
    private String startAmount;
    private String eCheck;
    private String accNum;

    // Constructor
    public UserData(String name, String fatherName, String dob, String email, String city, String state, String pinCode, String address, String gender, String maritalStatus, String cardNumber, String pin, String startAmount, String eCheck, String accNum) {
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.email = email;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.address = address;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.startAmount = startAmount;
        this.eCheck = eCheck;
        this.accNum = accNum;
    }

    // Getters and setters
    // Getters for UserData fields
    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public String getECheck() {
        return eCheck;
    }

    public String getAccNum() {
        return accNum;
    }

    // Setter for User's name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for User's father's name
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    // Setter for User's date of birth
    public void setDob(String dob) {
        this.dob = dob;
    }

    // Setter for User's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for User's city
    public void setCity(String city) {
        this.city = city;
    }

    // Setter for User's state
    public void setState(String state) {
        this.state = state;
    }

    // Setter for User's pin code
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    // Setter for User's address
    public void setAddress(String address) {
        this.address = address;
    }

    // Setter for User's gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Setter for User's marital status
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // Setter for User's card number
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Setter for User's PIN
    public void setPin(String pin) {
        this.pin = pin;
    }

    // Setter for User's starting balance
    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

    // Setter for User's email notification preference
    public void setECheck(String eCheck) {
        this.eCheck = eCheck;
    }

    // Setter for User's account number
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    // Fetch user data from file
    public static List<UserData> fetchUserData() {
        List<UserData> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            String accNum = null, name = null, fatherName = null, dob = null, email = null, city = null, state = null, pinCode = null, address = null, gender = null, maritalStatus = null, cardNumber = null, pin = null, startAmount = null, eCheck = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Account Number:")) {
                    accNum = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Name:")) {
                    name = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Father's Name:")) {
                    fatherName = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Date of Birth:")) {
                    dob = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Email:")) {
                    email = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("City:")) {
                    city = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("State:")) {
                    state = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Pin Code:")) {
                    pinCode = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Address:")) {
                    address = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Gender:")) {
                    gender = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Marital Status:")) {
                    maritalStatus = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Card Number:")) {
                    cardNumber = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("PIN:")) {
                    pin = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Start Amount:")) {
                    startAmount = line.substring(line.indexOf(":") + 2).trim();
                } else if (line.startsWith("Email Service:")) {
                    eCheck = line.substring(line.indexOf(":") + 2).trim();
                    userList.add(new UserData(name, fatherName, dob, email, city, state, pinCode, address, gender, maritalStatus, cardNumber, pin, startAmount, eCheck, accNum));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Get specific data field for a user
    public static String getData(String cardNumber, int i, List<UserData> userDataList) {
        for (UserData u : userDataList) {
            if (u.getCardNumber().equals(cardNumber)) {
                switch (i) {
                    case 1:
                        return u.getCardNumber(); // Card Number
                    case 2:
                        return u.getPin(); // PIN
                    case 3:
                        return u.getEmail(); // Email
                    case 4:
                        return u.getName(); // Name
                    case 5:
                        return u.getStartAmount(); // Start Amount
                    case 6:
                        return u.getFatherName(); // Father's Name
                    case 7:
                        return u.getDob(); // Date of Birth
                    case 8:
                        return u.getGender(); // Gender
                    case 9:
                        return u.getMaritalStatus(); // Marital Status
                    case 10:
                        return u.getCity(); // City
                    case 11:
                        return u.getState(); // State
                    case 12:
                        return u.getPinCode(); // Pin Code
                    case 13:
                        return u.getAddress(); // Address
                    case 14:
                        return u.getECheck(); // Email Check
                    case 15:
                        return u.getAccNum(); // Account Number
                }
            }
        }
        return null;
    }
    public static String getData(String accNumber, List<UserData> userDataList) {
        for (UserData u : userDataList) {
            if (u.getAccNum().equals(accNumber)) {
                    return u.getCardNumber(); // Account Number
                }
            }

        return null;
    }
    // Set specific data field for a user
    public static void setData(String cardNum, int index, String value, List<UserData> userDataList) {
        for (UserData userData : userDataList) {
            if (userData.getCardNumber().equals(cardNum)) {
                switch (index) {
                    case 2:
                        userData.setPin(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing PIN
                        break;
                    case 5:
                        userData.setStartAmount(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing start amount
                        break;
                    case 6:
                        userData.setName(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing name
                        break;
                    case 7:
                        userData.setFatherName(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing father's name
                        break;
                    case 8:
                        userData.setDob(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing date of birth
                        break;
                    case 9:
                        userData.setEmail(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing email
                        break;
                    case 10:
                        userData.setCity(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing city
                        break;
                    case 11:
                        userData.setState(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing state
                        break;
                    case 12:
                        userData.setPinCode(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing pin code
                        break;
                    case 13:
                        userData.setAddress(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing address
                        break;
                    case 14:
                        userData.setECheck(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing email check
                        break;
                    case 15:
                        userData.setAccNum(value);
                        updateUserDataFile(userDataList); // Update user_data.txt after changing account number
                        break;
                }
                break;
            }
        }
    }

    // Update user_data.txt file
    private static void updateUserDataFile(List<UserData> userDataList) {
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (UserData userData : userDataList) {
                writer.write("Account Number: " + userData.getAccNum() + "\n");
                writer.write("Name: " + userData.getName() + "\n");
                writer.write("Father's Name: " + userData.getFatherName() + "\n");
                writer.write("Date of Birth: " + userData.getDob() + "\n");
                writer.write("Email: " + userData.getEmail() + "\n");
                writer.write("City: " + userData.getCity() + "\n");
                writer.write("State: " + userData.getState() + "\n");
                writer.write("Pin Code: " + userData.getPinCode() + "\n");
                writer.write("Address: " + userData.getAddress() + "\n");
                writer.write("Gender: " + userData.getGender() + "\n");
                writer.write("Marital Status: " + userData.getMaritalStatus() + "\n");
                writer.write("Card Number: " + userData.getCardNumber() + "\n");
                writer.write("PIN: " + userData.getPin() + "\n");
                writer.write("Start Amount: " + userData.getStartAmount() + "\n");
                writer.write("Email Service: " + userData.getECheck() + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean exitsAccNo(String accNo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Account Number:")) {
                    String existingAccNo = line.substring(line.indexOf(":") + 2).trim();
                    if (existingAccNo.equals(accNo)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
