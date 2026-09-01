import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MonthSeason {
    
    public static String monthSeason(String month, int days) {
        month = month.toLowerCase();

        if (month.equals("march") && days >= 20 || month.equals("april") || month.equals("may") || 
            (month.equals("june") && days <= 20)) {
            return "spring";
        } else if (month.equals("june") && days >= 21 || month.equals("july") || month.equals("august") || 
                   (month.equals("september") && days <= 22)) {
            return "summer";
        } else if (month.equals("september") && days >= 23 || month.equals("october") || month.equals("november") || 
                   (month.equals("december") && days <= 20)) {
            return "autumn";
        } else {
            return "winter";
        }
    }
}

