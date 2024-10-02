package model.exceptionClasses;
public class TitleNotUniqueException extends RuntimeException{
    public TitleNotUniqueException(){
        super("TitleNotUniqueException");
    }
    public TitleNotUniqueException(String message){
        super(message);
    }
}
