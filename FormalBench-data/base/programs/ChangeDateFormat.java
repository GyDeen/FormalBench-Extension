import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class ChangeDateFormat {
    
    public static String changeDateFormat(String dt) {
        String[] dtArray = dt.split("-");
        String rw_dt = dtArray[2] + "-" + dtArray[1] + "-" + dtArray[0];
        return rw_dt;
    }
}

