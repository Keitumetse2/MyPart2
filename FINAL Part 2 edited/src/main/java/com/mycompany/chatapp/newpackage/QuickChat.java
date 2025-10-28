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
    private static final ArrayList<Message> sentMessages = new ArrayList<>();

    
   public static void startChat() {
       JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        boolean running = true;
        while (running) {
            String menu = "Menu:\n" +
                         "1) Send message\n" +
                         "2) Show recent message (coming soon)\n" +
                         "3) Quit";

            String pick = JOptionPane.showInputDialog(menu);
            if (pick == null) break;

            if (pick.equals("1")) {
                sendMessagesMenu();
            } else if (pick.equals("2")) {
                JOptionPane.showMessageDialog(null, "Coming soon");
            } else if (pick.equals("3")) {
                running = false;
            } else {
                JOptionPane.showMessageDialog(null, "Type 1 to 3 only!");
            }
        }

        JOptionPane.showMessageDialog(null,
                "You created " + Message.getTotalMessages() + " message(s) in total.");
         JOptionPane.showMessageDialog(null,"Goodbye");
    }

    public static void sendMessagesMenu() {
        String input = JOptionPane.showInputDialog("How many messages?");
        if (input == null) return;

        input = input.trim();

        if (input.isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Type a number!");
            return;
        }

        int count = Integer.parseInt(input);
        if (count <= 0) {
            JOptionPane.showMessageDialog(null, "Enter > 0");
            return;
        }

        int current = 1;
        while (current <= count) {
            String phone = JOptionPane.showInputDialog("Please enter recipients number " + current + ": Phone (+27...):");
            if (phone == null) break;

            String text = JOptionPane.showInputDialog("Please enter your Message " + current + ": Your text:");
            if (text == null) break;

            Message messagetool = new Message(phone, text);

            if (!messagetool.checkRecipientNumber(phone)) {
                JOptionPane.showMessageDialog(null, "Invalid recipients number! Must be +27 + 10 digits.");
                continue;
            }
            if (!messagetool.checkMessageLength()) {
                int extra = text.length() - 250;
                JOptionPane.showMessageDialog(null, "Message too long by " + extra + " characters.");
                continue;
            }

            String[] options = {"Send", "Store", "Disregard"};
            int option = JOptionPane.showOptionDialog(null, "Choose what to do with this message:",
                    "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (option == 0) {
                sentMessages.add(messagetool); 
               JOptionPane.showMessageDialog(null,
        "Message Sent!\n\n" + messagetool.getMessageDetails(),
        "Message Info",
        JOptionPane.INFORMATION_MESSAGE);
            } else if (option == 1) {
                JOptionPane.showMessageDialog(null, "Message Stored.");
                
            } else if (option == 2) {
                JOptionPane.showMessageDialog(null, "Message Disregarded.");
                
            }
            current++;
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
   



