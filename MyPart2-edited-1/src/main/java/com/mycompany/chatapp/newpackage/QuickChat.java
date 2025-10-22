/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.newpackage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import javax.swing.JOptionPane;
import org.json.JSONArray;




/**
 *
 * @author RC_Student_Lab
 */
public class QuickChat {
     ArrayList<Message> sentMessages = new ArrayList<>();

    
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
                    sendMessages();
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
    public static void sendMessages() {
       String recipient = JOptionPane.showInputDialog("Enter recipient number (start with +27):");
        if (recipient == null) return;

        String content = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
        if (content == null) return;

        Message newMessage = new Message(recipient, content);

        // checks recipient number and length
        if (!newMessage.checkRecipientNumber(recipient)) {
            JOptionPane.showMessageDialog(null, "Invalid number! Must start with +27 and be 12 digits long.");
            return;
        }

        if (!newMessage.checkMessageLength()) {
            int extra = content.length() - 250;
            JOptionPane.showMessageDialog(null, "️ Message too long by " + extra + " characters.");
            return;
        }

        // Ask user what to do
        String[] options = {"Send", "Store", "Disregard"};
        int option = JOptionPane.showOptionDialog(null, "Choose what to do with this message:",
                "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (option == 0) {
            
           
            JOptionPane.showMessageDialog(null, "Message sent \n\n" + newMessage.getMessageDetails());
        } else if (option == 1) {
            
           
            JOptionPane.showMessageDialog(null, " Message Stored for later.");
        } else if (option == 2) {
            
            JOptionPane.showMessageDialog(null, " Message Disregarded.");
        }  
    }
   
     public void storedMessagesToJson() throws IOException {
        if (sentMessages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No sent messages to store.");
        return;
    }

    // Create JSON array of all messages
    JSONArray jsonArray = new JSONArray();
    for (Message messageTool : sentMessages) {
        JSONObject obj = new JSONObject();
        obj.put("MessageId", messageTool.getMessageId());
        obj.put("MessageHash", messageTool.getMessageHash());
        obj.put("Recipient", messageTool.getRecipientNumber());
        obj.put("Message", messageTool.getMessageDetails());
        jsonArray.put(obj);
    }
    // Save in user's home  (e.g. Documents folder)
    String path = System.getProperty("user.home") + "/sentMessages.json";

    try (FileWriter file = new FileWriter(path)) {
        file.write(jsonArray.toString(4));  
        file.flush();
        JOptionPane.showMessageDialog(null, 
            "Messages exported successfully!" + path);
    }
     }
}
   


