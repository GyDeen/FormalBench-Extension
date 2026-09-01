
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {
    
    public static String removeParenthesis(String[] items) {
        if (items == null || items.length == 0) {
            return "";
        }

        String item = items[0];
        item = item.replaceAll(" ?\\([^)]+\\)", "");

        return item;
    }
}

