package co.mobileaction.example.web.util;

/**
 * @author sa
 * @date 17.05.2021
 * @time 18:19
 */
public class SecurityUtils
{
    private static final String ROLE_PREFIX = "ROLE_";

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String ROLE_ADMIN = ROLE_PREFIX + ADMIN;
    public static final String ROLE_USER = ROLE_PREFIX + USER;
}
