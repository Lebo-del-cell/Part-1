import java.util.regex.Pattern;
/*Login class for the chat Application.
*@author Lebo Moremi
*/
//Attributes
public class Login {
     String firstName;
     String lastName;
     String username;
     String password;
     String cellPhoneNumber;
    
    //Constructor
    public Login(String firstName, String lastName){this.firstName = firstName;
    this.lastName = lastName;
    }
    //Check username
    public boolean checkUserName(String username){
        if (username != null && username.contains("_") && username.length() <=5){return true;
        } else {
            return false;
        }
    }
    //Check password complexity
    public boolean CheckPasswordComplexity(String password){
        if (password == null || password.length() < 8) { return false;
    }
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        
        for(int i = 0; i < password.length(); i++){char character = password.charAt(i);
        
        if(Character.isUpperCase(character)) {hasCapitalLetter = true;}
        
                if(Character.isDigit(character)) {hasNumber = true;}
                
                         if(!Character.isLetterOrDigit(character)) {hasSpecialCharacter = true;}

        }
         
        if(hasCapitalLetter && hasNumber && hasSpecialCharacter){return true;
        } else {
            return false;
        }
        }
    //Check cellphone number
    public boolean checkCellPhoneNumber(String cellPhoneNumber){
        String cellPhoneRegex = "^\\+27[0-9]{9}$";
        
        if(cellPhoneNumber !=null && Pattern.matches(cellPhoneRegex,cellPhoneNumber )){
        return true;
    } else {
            return false;
            }
    }
    
        //  Return Register  for user
    public String registerUser(String username, String password, String cellPhoneNumber) {
        
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";}
            
           else if (!CheckPasswordComplexity (password)) {
        return "Password is not correctly formatted or does not contain an international code; please correct the number and try again."; }
       
           else if (!checkCellPhoneNumber(cellPhoneNumber)) {
        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";}
            
           else{
            this.username = username;
                        this.username = password;
            this.username = cellPhoneNumber;
            return "user registered successfully"
                    ;

    }
    }
    // login for User
    public boolean loginUser (String username, String password) {
        if(this.username !=null && this.password !=null && this.username.equals(username) && this.password.equals(password)) {
            return true;
        }else{
    return false;
        }
    }
    // Responds to login status
    public String returnLoginStatus (String username, String password){
        if (loginUser(username, password)) {
            return "Welcome " + firstName + "" + lastName +", it is great to see you";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}