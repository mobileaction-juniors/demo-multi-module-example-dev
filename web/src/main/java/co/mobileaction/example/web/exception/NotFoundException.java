package co.mobileaction.example.web.exception;

/**
 * @author elif
 * @date 05.08.2023
 * @time 10.50
 */
public class NotFoundException extends BaseException
{
    public NotFoundException(String itemName)
    {
        super(itemName.substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase() + " is not found!");
    }

    public NotFoundException(String itemName, String property, Object value)
    {
        super(String.format("%s with %s = %s is not found!",
                (itemName.substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase()), property, value));
    }
}
