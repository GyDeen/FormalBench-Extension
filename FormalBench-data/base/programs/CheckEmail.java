import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckEmail {
    
    public static String checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex) ? "Valid Email" : "Invalid Email";
    }
}

