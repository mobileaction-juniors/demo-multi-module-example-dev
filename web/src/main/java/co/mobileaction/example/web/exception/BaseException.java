package co.mobileaction.example.web.exception;

import lombok.Data;

/**
 * @author elif
 * @date 05.07.2023
 * @time 15.58
 */
public class BaseException extends Exception
{
    private final int DEFAULT_EXCEPTION_STATUS = 400;

    private int status;
    public BaseException() {
        super("Something went wrong \uD83D\uDE14");
        this.status = DEFAULT_EXCEPTION_STATUS;
    }

    public BaseException(String msg) {
        super(msg);
        this.status = DEFAULT_EXCEPTION_STATUS;
    }

    public BaseException(String msg, int status) {
        super(msg);
        this.status = status;
    }
}
