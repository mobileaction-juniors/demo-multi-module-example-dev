package co.mobileaction.example.web.exception;

public class UserFoundException extends RuntimeException
{
    public UserFoundException(){ super("User already exists with same username or email."); }
}
