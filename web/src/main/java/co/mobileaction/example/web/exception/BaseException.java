package co.mobileaction.example.web.exception;

/**
 * @author elif
 * @date 05.07.2023
 * @time 15.58
 */
public class BaseException extends RuntimeException
{
    public BaseException()
    {
        super("Something went wrong \uD83D\uDE14");
    }

    public BaseException(String msg)
    {
        super(msg);
    }
}
