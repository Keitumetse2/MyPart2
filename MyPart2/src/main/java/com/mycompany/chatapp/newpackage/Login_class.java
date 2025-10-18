/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.newpackage;

import com.mycompany.chatapp.ChatApp;

/**
 *
 * @author RC_Student_lab
 */
public class Login_class {
    public boolean loginUser(String username, String password) {
            return username.equals(ChatApp.registeredUsername) && password.equals(ChatApp.registeredpassword);
       
    }
        public String returnLoginStatus(String username, String password) {
            if (loginUser(username,password)) {
                return "Welcome" +" "+ ChatApp.firstname +  "," + ChatApp.Lastname +" "+"it is great to see you again.";
            }else {
                return "Username or password incorrect, please try again";
            }
        }
}
