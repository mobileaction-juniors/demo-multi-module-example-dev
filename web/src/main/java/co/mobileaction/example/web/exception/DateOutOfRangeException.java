package co.mobileaction.example.web.exception;

public class DateOutOfRangeException extends RuntimeException
{
    public DateOutOfRangeException (String message)
    {
        super(message);
    }
}
