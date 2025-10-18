/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.newpackage;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import javax.swing.JOptionPane;




/**
 *
 * @author RC_Student_Lab
 */
public class QuickChat {
    
    
   public static void startChat() {
       JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        // Ask user how many messages they want to send
        String countText = JOptionPane.showInputDialog("How many messages would you like to send?");
        if (countText == null) return; 

        int messageCount;
        try {
            messageCount = Integer.parseInt(countText);
            if (messageCount <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a number greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return;
        }

        boolean chatting = true;
        while (chatting) {
            String menu = "Manu:\n"
                        + "1) Send message\n"
                        + "2) Show recent message (coming soon)\n"
                        + "3) Quit";

            String choiceText = JOptionPane.showInputDialog(menu);
            if (choiceText == null) { 
                chatting = false;
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(choiceText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter 1, 2 or 3.");
                continue;
            }

            switch (choice) {
                case 1:
                    sendMessages(messageCount);
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "coming soon!");
                    break;

                case 3:
                    chatting = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
                    break;
            }
        }

        JOptionPane.showMessageDialog(null,
                "You created " + Message.getTotalMessages() + " message(s) in total.");
    }

    // Ask for recipient and message text, create Message object, and show details
    public static void sendMessages(int count) {
        for (int i = 0; i < count; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient phone number (e.g. +27...)");
            if (recipient == null) return; 

            String text = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
            if (text == null) return; 

            // Create Message (it will generate ID and hash )
            Message textmessage = new Message(recipient, text);

            // checks input using Message checks
            if (!textmessage.checkRecipientNumber(recipient)) {
                JOptionPane.showMessageDialog(null, "Invalid phone number. Make sure it starts with (+27)");
                i--; 
                continue;
            }

            if (!textmessage.checkMessageLength()) {
                JOptionPane.showMessageDialog(null, "Message too long. Keep it under 250 characters.");
                i--;
                continue;
            }

            // Show message details (includes hash)
            JOptionPane.showMessageDialog(null,"Message sent");
            JOptionPane.showMessageDialog(null, "Message sent\n\n" + textmessage.getMessageDetails());
        }
    }
   
     private static void saveMessageToJson(Message textmessage) {
         JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("MessageId", textmessage.getMessageId());
        jsonMessage.put("MessageHash", textmessage.getMessageHash());
        jsonMessage.put("Recipient", textmessage.getRecipientNumber());
        jsonMessage.put("Message", textmessage.getMessageDetails());

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonMessage.toString(4) + ",\n"); 
            file.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }
}
   


