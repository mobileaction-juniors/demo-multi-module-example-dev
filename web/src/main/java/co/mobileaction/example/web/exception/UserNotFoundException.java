package co.mobileaction.example.web.exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(){ super("User do not exists.");}
}
