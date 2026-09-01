import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class AreaPentagon {
    
    public static Double areaPentagon(int a) {
        double area = (5 * Math.pow(a, 2)) / (4 * Math.tan(Math.PI / 5));
        return area;
    }
}

