import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class PerimeterPolygon {
    
    public static int perimeterPolygon(int s, int l) {
        int perimeter = 0;
        for (int i = 0; i < s; i++) {
            perimeter += l;
        }
        return perimeter;
    }
}

