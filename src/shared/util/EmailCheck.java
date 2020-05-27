package shared.util;

import java.util.regex.Pattern;
/**
 * A class that checks the validity of emails
 * @author Group 2
 */
public class EmailCheck
{
  public static boolean isValid(String email)
  {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    if (email == null)
      return false;
    return pat.matcher(email).matches();
  }
}
