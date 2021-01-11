package ru.Pegov.Macragge.models;

/**
 * A error message, extends Reply. Use it if if
 * @author Андрей
 */
public class ErrorMessage extends Reply{
    
    /**
     * Create a new error message, without body data.
     * @param message String, short description
     */
    public ErrorMessage(String message){
        super("error", message, null);
    }
    
    /**
     * reate a new error message, with body data.
     * @param message String, short description
     * @param data Object, body of message, contain main data
     */
    public ErrorMessage (String message, Object data){
        super("error", message, data);
    }
}
