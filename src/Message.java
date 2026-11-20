import java.util.Random;
public class Message
{
private String messageID;
private String recipient;
private String messageText;
private String messageHash;
private int messageNumber;
private String status;
private static int totalMessageSent = 0;
// Constructor
public Message(String recipient, String messageText, int messageNumber)
{
this.recipient = recipient;
this.messageText = messageText;
this.messageNumber = messageNumber;
this.messageID = generateMessageID();
this.messageHash = createMessageHash();
this.status = "Pending";
}
// Generate a random 10-digit message ID
private String generateMessageID()
{
Random random = new Random();
long number = 1000000000L + random.nextInt(900000000);
return String.valueOf(number);
}
// Check the recipient cellphone number
public String checkRecipientCell()
{
if (recipient != null && recipient.startsWith("+27")
&& recipient.length() == 12)
{
boolean digitsOnly = true;
for (int i = 3; i < recipient.length(); i++)
{
if (!Character.isDigit(recipient.charAt(i)))
{
digitsOnly = false;
}
}
if (digitsOnly)
{
return "Cell phone number successfully captured.";
}
}
return "Cell phone number is incorrectly formatted or does not contain an international code.";
}
// Check message length
public String checkMessageLength()
{
if (messageText != null && messageText.length() <= 250)
{
return "Message ready to send.";
}
else
{
int extraCharacters = messageText.length() - 250;
return "Message exceeds 250 characters by " +
extraCharacters + "; please reduce the size.";
}
}
// Create the message hash
public String createMessageHash()
{
String firstTwoDigits = messageID.substring(0, 2);
String cleanMessage = messageText.toUpperCase();
cleanMessage = cleanMessage.replaceAll("[^A-Z0-9 ]", "");
cleanMessage = cleanMessage.trim();
String[] words = cleanMessage.split("\\s+");
String firstWord = words[0];
String lastWord = words[words.length - 1];
return firstTwoDigits + ":"
+messageNumber + ":"
+firstWord + lastWord;
}
// Display message options
public String SentMessage()
{
System.out.println();
System.out.println("Choose what to do with this message:");
System.out.println("1. Send Message");
System.out.println("2. Disregard Message");
System.out.println("3. Store Message");
return "";
}
// Choose what to do with the message
public String chooseMessageOption(int choice)
{
if (choice == 1)
{
status = "sent";
totalMessageSent++;
return "Message successfully sent.";
}
else if (choice == 2)
{
status = "disregarded";
return "Press 0 to delete the message.";
}
else if (choice == 3)
{
status = "stored";
return storeMessage();
}
else
{
return "Invalid option. Please try again.";
}
}
// Display message details
public String printMessages()
{
return "Message ID: " + messageID

+"\nMessage Hash: " + messageHash

+"\nRecipient: " + recipient

+"\nMessage: " + messageText

+"\nStatus: " + status;
}
// Return total messages sent
public int returnTotalMessages()
{
return totalMessageSent;
}
// Get message ID
public String getMessageID()
{
return messageID;
}
// Get message text
public String getMessageText()
{
return messageText;
}
// Get message hash
public String getMessageHash()
{
return messageHash;
}
// Get status
public String getStatus()
{
return status;
}

// Store messages in JSON format
public String storeMessage()
{
try
{
java.io.File file = new java.io.File("messages.json");
char q = 34;
String jsonObject = "{"
+q + "messageID" + q + ":" + q + escapeJson(messageID) + q + ","
+q + "messageHash" + q + ":" + q + escapeJson(messageHash) + q + ","
+q + "recipient" + q + ":" + q + escapeJson(recipient) + q + ","
+q + "messageText" + q + ":" + q + escapeJson(messageText) + q + ","
+q + "status" + q + ":" + q + escapeJson(status) + q +
"}";
String existing = "";
if (file.exists())
{
existing = java.nio.file.Files.readString(file.toPath()).trim();
}
if (existing.startsWith("[") && existing.endsWith("]")
&& existing.length() > 2)
{
existing = existing.substring(0, existing.length() - 1)
+"," + jsonObject + "]";
}
else
{
existing = "[" + jsonObject + "]";
}
java.nio.file.Files.writeString(file.toPath(), existing);
return "Message successfully stored.";
}
catch (java.io.IOException e)
{
return "Error storing message: " + e.getMessage();
}
}
private String escapeJson(String text)
{
    StringBuilder result = new StringBuilder();

    char slash = 92;
    char quote = 34;

    for (int i = 0; i < text.length(); i++)
    {
        char c = text.charAt(i);

        if (c == quote)
        {
            result.append(slash).append(quote);
        }
        else if (c == slash)
        {
            result.append(slash).append(slash);
        }
        else if (c == 10)
        {
            result.append(slash).append("n");
        }
        else if (c == 13)
        {
            result.append(slash).append("r");
        }
        else if (c == 9)
        {
            result.append(slash).append("t");
        }
        else
        {
            result.append(c);
        }
    }

    return result.toString();
}
}