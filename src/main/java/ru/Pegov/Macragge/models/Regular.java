/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.models;

/**
 * Regular respons object. Create if need to send data.
 * @author Андрей
 */
public class Regular extends Reply{
    
    /**
     * Create a new regular message, with description and body data.
     * @param message String, short description
     * @param data Object, body of message, contain main data
     */
    public Regular(String message, Object data){
       super("regular", message, data);
    }
}
