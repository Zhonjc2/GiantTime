package main;

public class TimeOutException extends Exception{
    public TimeOutException(long millis){
        super("超时："+millis);
    }
}
