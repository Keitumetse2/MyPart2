/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.newpackage;
import java.util.Random;
/**
 *
 * @author RC_Student_Lab
 */
public class Message {
// Message fields
    private String messageId;
    private String recipientNumber;
    private String messageText;
    private String messageHash;

    //  counter for how many Message objects were created
    private static int totalMessages = 0;

    // Constructor: generates a random ID and a message hash
    public Message(String recipientNumber, String messageText) {
        this.recipientNumber = recipientNumber;
        this.messageText = messageText;

        // Generate ID first
        this.messageId = generateRandomId();

        // Increase the message counter (each created message counts)
        totalMessages++;

        //  message hash using parts of ID, the count, and message letters
        this.messageHash = createMessageHash();
    }

    // Generate a random number ID as text (up to 9 digits)
    private String generateRandomId() {
        Random random = new Random();
        int idNumber = random.nextInt(1_000_000_000);
        return String.valueOf(idNumber);
    }

    // Create a message hash 
    private String createMessageHash() {
       
        String firstTwo;
        if (messageId.length() >= 2) {
            firstTwo = messageId.substring(0, 2);
        } else {
           
            firstTwo = messageId; 
        }

        // get first and last characters of the message text
        String firstChar = "_";
        String lastChar = "_";
        if (messageText != null && messageText.length() > 0) {
            firstChar = messageText.substring(0, 1);
            lastChar = messageText.substring(messageText.length() - 1);
        }

        // Example format: "12:5:Hd" and then uppercase it
        String format = firstTwo + ":" + totalMessages + ":" + firstChar + lastChar;
        return format.toUpperCase();
    }

  
 //checks the length if it a country code of the number
    public boolean checkRecipientNumber(String RecipientNumber) {
        String countrycode = "+27";
             int lengthCell = 12;
             
             if (RecipientNumber.length() != lengthCell) {
                 return false;
                 
             }
             for (int i = 0; i < countrycode.length();) {
                 if (RecipientNumber.charAt(i) != countrycode.charAt(i)) {
                     return false;
                 }
                 for ( i = countrycode.length(); i < RecipientNumber.length(); i++) {
                     if (!Character.isDigit(RecipientNumber.charAt(i))) {
                         return false;
                     }
                     
                 }
             }
                 return true;
        
    }
// maximize the message characters length
    public boolean checkMessageLength() {
        return messageText != null && messageText.length() <= 250;
    }

    // Getters for display 

    public String getMessageId() {
        return messageId;
    }
    public String getRecipientNumber() {
        return recipientNumber;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageDetails() {
        return "Message ID: " + messageId + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: " + recipientNumber + "\n"
             + "Message: " + messageText;
    }

    public static int getTotalMessages() {
        return totalMessages;
    }
     
}

