import java.util.Scanner;
/* Main class for the Chat Application.
*@author Moleboheng Moremi
*/
public class ChatApp {
    public static void main(String[] args) {
       
        //Create an input listener
        Scanner input= new Scanner(System.in);
        
        // Get personal information
        System.out.println("========================");
        System.out.println("   QuickCHAT REGISTRATION");
         System.out.println("========================");

         
         System.out.print("Enter your first name: ");
         String firstName = input.nextLine();
         
         System.out.print("Enter your last name: ");
         String lastName = input.nextLine();
         
         //Create Login object
         Login user = new Login(firstName, lastName);
         
         //Get registration information
         System.out.print("Enter your username: ");
         String username = input.nextLine();
         
         System.out.print("Enter your password: ");
         String password = input.nextLine();
         
         System.out.print("Enter your South African cell phone number: ");
         String cellPhoneNumber = input.nextLine();
         
         //Check registration details
         System.out.println();
         System.out.println("=========================");
         System.out.println(" Registration Results  ");
         System.out.println("========================");
         
         if (user.checkUserName(username)) {
         System.out.println("Username successfully captured. ");
    } else { 
    System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");}
    
       if (user.CheckPasswordComplexity(password)) {
    System.out.println("Password successfully captured. ");
    } else { 
    System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");}
   
    if (user.checkCellPhoneNumber(cellPhoneNumber)) {
System.out.println("Cell number successfully captured. ");
    } else { 
    System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");}
    
// Register user if all information is vaild
if (user.checkUserName(username) && user.CheckPasswordComplexity(password) && user.checkCellPhoneNumber(cellPhoneNumber)) {
    
    String registrationMessage = user.registerUser(
    username, password, cellPhoneNumber);
    System.out.println( registrationMessage);
    
    //  Login
    System.out.println();
    System.out.println("=========================");
    System.out.println("          LOGIN");
        System.out.println("=========================");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();
        
        String loginMessage = user.returnLoginStatus( loginUsername,loginPassword);
        System.out.println(loginMessage);
        
} else {
    //Registration failed
    System.out.println();
    System.out.println("Registration unsuccessful.");
    System.out.println("Please correct the invaild information and try again.");}

// Close scanner
input.close();
}
    }
