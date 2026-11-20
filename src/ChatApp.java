import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
/* Main class for the Chat Application.
*@author Moleboheng Moremi
*/
public class ChatApp {
    public static void main(String[] args) {
       
        //Create an input listener
        Scanner input= new Scanner(System.in);
        // Arrays for storing message information
String[] sentMessages = new String[100];
String[] disregardedMessages = new String[100];
String[] storedMessages = new String[100];

String[] messageHashes = new String[100];
String[] messageIDs = new String[100];
String[] recipients = new String[100];
String[] messageTexts = new String[100];

// Message counters
int sentCount = 0;
int disregardedCount = 0;
int storedCount = 0;
int allMessageCount = 0;
// Read stored messages from the JSON file
try
{
    String jsonData = Files.readString(Paths.get("messages.json"));

    if (!jsonData.trim().equals("[]"))
{
    String[] records = jsonData.substring(1,
            jsonData.length() - 1).split("\\},\\{");

    for (String record : records)
    {
        if (record.contains("\"status\":\"stored\""))
        {
           String id = record.split("\"messageID\":\"")[1].split("\"")[0];

String hash = record.split("\"messageHash\":\"")[1].split("\"")[0];

String recipient = record.split("\"recipient\":\"")[1].split("\"")[0];

String message = record.split("\"messageText\":\"")[1].split("\"")[0];

if (storedCount < 100)
{
    messageIDs[allMessageCount] = id;
    messageHashes[allMessageCount] = hash;
    recipients[allMessageCount] = recipient;
    messageTexts[allMessageCount] = message;

    storedMessages[storedCount] = message;

    storedCount++;
    allMessageCount++;
}
        }
    }
}
}
catch (Exception e)
{
    System.out.println("Error reading stored messages.");
}

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
       if (user.loginUser(loginUsername, loginPassword))
       {
           int choice = 0;
           while (choice !=3)
           {
               System.out.println();
               System.out.println("Welcome to QuickChat.");
               System.out.println("1. Send Messages");
               System.out.println("2. Show recently sent messages");
               System.out.println("3.Ouit");
                              System.out.println("4.Stored Messages");
               System.out.println("Choose an option:");
               
               choice = Integer.parseInt(input.nextLine());
               
               if (choice == 1)
               {
                   System.out.println("How many messages would you like to send? ");
                   int numberOfMessages = Integer.parseInt(input.nextLine());
                   
                   for (int i =0; i< numberOfMessages; i++)
                   {
                       System.out.println();
                                              System.out.println("Message " + (i + 1));
                                               System.out.print("Enter recipient cellphone number (+27): ");
                                               String recipient = input.nextLine();
                                               
                                               System.out.print("Enter your messager: ");
                                               String messageText = input.nextLine();
                                               Message newMessage = new Message(recipient, messageText, i);
                                               
                                               // Chech cellphone number
                                               System.out.println(newMessage.checkRecipientCell());
                                               if (!newMessage.checkMessageLength(). equals("Message ready to send."))
                                               {
                                                   System.out.println("Message was not sent. ");
                                                   continue;
                                               }
                //Display message length
                System.out.println(newMessage.checkMessageLength());
                if (! newMessage.checkMessageLength(). equals("Message ready to send."))
                {
                    System.out.println("Message was not sent.");
                    continue;
                }
                //Display message options 
                newMessage.SentMessage();
                
                System.out.print("Choose an option: ");
                int messageChoice = Integer.parseInt(input.nextLine());
                System.out.println(newMessage.chooseMessageOption(messageChoice));

if (messageChoice >= 1 && messageChoice <= 3)
{
    if (allMessageCount < 100)
    {
        messageIDs[allMessageCount] = newMessage.getMessageID();
        messageHashes[allMessageCount] = newMessage.getMessageHash();
        recipients[allMessageCount] = recipient;
        messageTexts[allMessageCount] = messageText;
        allMessageCount++;
    }
}

if (messageChoice == 1)
{
    sentMessages[sentCount] = messageText;
    sentCount++;

    System.out.println(newMessage.printMessages());
}
else if (messageChoice == 2)
{
    System.out.print("Enter 0 to delete the message: ");
    int deleteChoice = Integer.parseInt(input.nextLine());

    if (deleteChoice == 0)
    {
        System.out.println("Message successfully deleted.");
    }
    else
    {
        disregardedMessages[disregardedCount] = messageText;
        disregardedCount++;
        System.out.println("Message kept in disregarded messages.");
    }
}
else if (messageChoice == 3)
{
    storedMessages[storedCount] = messageText;
    storedCount++;

    
}

                                   }
                   System.out.println();
                   System.out.println("Total messages sent: " + new Message("", "", 0).returnTotalMessages());
               }
               else if (choice == 2)
               {
                   System.out.println("Coming Soon.");
               }
               else if (choice == 4)
{
    System.out.println("STORED MESSAGES");

    if (storedCount == 0)
    {
        System.out.println("No stored messages found.");
    }
    else
    {
        for (int i = 0; i < storedCount; i++)
        {
            System.out.println("Message ID: " + messageIDs[i]);
            System.out.println("Message Hash: " + messageHashes[i]);
            System.out.println("Recipient: " + recipients[i]);
            System.out.println("Message: " + storedMessages[i]);
            System.out.println("Status: stored");
            System.out.println("----------------------");
        }
        int longestIndex = 0;
for (int i = 1; i < storedCount; i++)
{
if (storedMessages[i].length()
> storedMessages[longestIndex].length())
{
longestIndex = i;
}
}
System.out.println("LONGEST STORED MESSAGE:");
System.out.println(storedMessages[longestIndex]);
System.out.println();
System.out.println("STORED MESSAGE OPTIONS");
System.out.println("1. Search by Message ID");
System.out.println("2. Search by Recipient");
System.out.println("3. Delete by Message Hash");
System.out.println("4. Return to Main Menu");
System.out.print("Choose an option: ");

int searchChoice = Integer.parseInt(input.nextLine());

if (searchChoice == 1)
{
    System.out.print("Enter Message ID: ");
    String searchID = input.nextLine();

    boolean found = false;

    for (int i = 0; i < allMessageCount; i++)
    {
        if (messageIDs[i].equals(searchID))
        {
            System.out.println("Recipient: " + recipients[i]);
            System.out.println("Message: " + messageTexts[i]);
            found = true;
        }
    }

    if (!found)
    {
        System.out.println("Message ID not found.");
    }
}

 
               else if (choice == 3)
               {
                   System.out.println("Thank you for using QuickChat.");
               }
               
                   else if (searchChoice == 2)
{
    System.out.print("Enter recipient number: ");
    String searchRecipient = input.nextLine();

    boolean found = false;

    for (int i = 0; i < allMessageCount; i++)
    {
        if (recipients[i] != null &&
            recipients[i].equals(searchRecipient))
        {
            System.out.println("Message: " + messageTexts[i]);
            found = true;
        }
    }

    if (!found)
    {
        System.out.println("No messages found.");
    }
}
else if (searchChoice == 3)
{
    System.out.print("Enter Message Hash: ");
    String deleteHash = input.nextLine();

    boolean deleted = false;

    for (int i = 0; i < allMessageCount; i++)
    {
        if (messageHashes[i] != null &&
            messageHashes[i].equals(deleteHash))
        {
            System.out.println("Message found: " + messageTexts[i]);
            System.out.println("Deletion feature needs to remove this message from storage.");
            deleted = true;
            break;
        }
    }

    if (!deleted)
    {
        System.out.println("Message Hash not found.");
    }
}
else if (searchChoice == 4)
{
    System.out.println("Returning to main menu.");
}
else
{
    System.out.println("Invalid option. Please try again.");
}

               {
System.out.println("Invaild option.Please try again.");
               }
    }     
 
} else{ 
     
         //Registration failed
         System.out.println();
         System.out.println("Registration unsuccessful.");
         System.out.println("Please correct the invaild information and try again.");
     }           }
// Close scanner
input.close();
}
           }
    }}

       
    
    
