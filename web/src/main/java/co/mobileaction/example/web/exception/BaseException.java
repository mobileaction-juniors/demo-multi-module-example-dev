package co.mobileaction.example.web.exception;

/**
 * @author elif
 * @date 05.07.2023
 * @time 15.58
 */
public class BaseException extends Exception
{
    private final int DEFAULT_EXCEPTION_STATUS = 400;

    private int status;
    private String message;

    public BaseException()
    {
        super("Something went wrong \uD83D\uDE14");
        this.message = "Something went wrong \uD83D\uDE14";
        this.status = DEFAULT_EXCEPTION_STATUS;
    }

    public BaseException(String msg)
    {
        super(msg);
        this.message = msg;
        this.status = DEFAULT_EXCEPTION_STATUS;
    }

    public BaseException(String msg, int status)
    {
        super(msg);
        this.message = msg;
        this.status = status;
    }

    public int getStatus()
    {
        return this.status;
    }

    public String getMessage()
    {
        System.out.println(this.message);
        return this.message;
    }
}
