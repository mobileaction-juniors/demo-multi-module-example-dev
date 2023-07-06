package co.mobileaction.example.web.exception;

/**
 * @author elif
 * @date 05.07.2023
 * @time 16.10
 */
public class AlreadyExistException extends BaseException
{
    public AlreadyExistException(String itemName)
    {
        super(itemName.substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase() + " already exists!");
    }
}
