/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.newpackage;

/**
 *
 * @author RC_Student_lab
 */
public class Register_Class {
    
    //This method checks if the username reaches thr right requirement meaning it checks length and if it has an underscore
    public static boolean isUsernameValid(String USERNAME) {
           
        boolean UserLength = USERNAME.length() <= 5;
       boolean CheckUnderscore = USERNAME.contains("_");
        return UserLength && CheckUnderscore;
          }       
            
        
        //method to check password if the password reaches the correct requirements
        public static boolean CheckPasswordComplexity(String password){
              boolean passw0rd = false;
              boolean hasNumber = false;
              boolean hasCap = false;
              boolean hasChar = false;
              char current;
          
          if (password.length() >= 8) {
              for (int i = 0 ; i < password.length();i++) {
                  current = password.charAt(i);
                  
                  if (Character.isUpperCase(current)) {
                     hasCap = true; 
                  }
                  if (Character.isDigit(current)) {
                      hasNumber = true;
                  }
                  if (Character.isLetterOrDigit(current)) {
                      hasChar = true;
                  }
              }
              if (hasCap && hasNumber && hasChar) {
                  passw0rd = true;
              }
              return passw0rd;
          }
          return false;
        }
        
        //checks if the cellphone number has a +27
        public static boolean isCellphoneValid(String phoneNumber){
            
             String countrycode = "+27";
             int lengthCell = 12;
             
             if (phoneNumber.length() != lengthCell) {
                 return false;
                 
             }
             for (int i = 0; i < countrycode.length();) {
                 if (phoneNumber.charAt(i) != countrycode.charAt(i)) {
                     return false;
                 }
                 for ( i = countrycode.length(); i < phoneNumber.length(); i++) {
                     if (!Character.isDigit(phoneNumber.charAt(i))) {
                         return false;
                     }
                     
                 }
             }
                 return true;
                 
             }
        
        public String registerUser(String USERNAME, String password, String phoneNumber){
           StringBuilder message = new StringBuilder();
            
            if (!isUsernameValid(USERNAME)) {
                return "Username is not correctly formatted, please ensure that"
                   +" "+ "your username contains an underscore and no more than five characters in length";
            }       
            message.append("Username successfully captured.\n");
            
            if (!CheckPasswordComplexity(password)) {
                return "Password is not correctly formatted please ensure that the passowrd contains"
                        +" "+ "at least eigth characters, a capital letter ,a number and a special character";
                
            }
            message.append("Password successfully captured.\n");
            
            if (!isCellphoneValid(phoneNumber)) {
                return "Cell number is incorrectly formatted or does not contain an international code ,please correct the number and try again";
            }
            message.append("Cellphone number successfully added.\n");
            
            System.out.println(message.toString());
            
            return "Registration successfull";
        }
       
}
