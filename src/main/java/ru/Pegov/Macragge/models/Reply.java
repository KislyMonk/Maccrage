package ru.Pegov.Macragge.models;

/**
 * Abstract class, prototype of all outcomming mtssage
 * @author Андрей
 */
public abstract class Reply {
    private String type;
    private String message;
    private Object data;
    
    /**
     * Create a new message
     * @param type String, type of message return,error etc.
     * @param message String, short description 
     * @param data Object, body of message, contain main data
     */
    public Reply(String type, String message, Object data){
        this.data = data;
        this.message = message;
        this.type = type;
    }

    /**
     * Set short description 
     * @param message String, short description
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Set body of message, contain main data
     * @param data Object, body of message, contain main data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     *
     * @return String, type of message return,error etc.
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return String, short description
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return Object, body of message, contain main data
     */
    public Object getData() {
        return data;
    }
}
