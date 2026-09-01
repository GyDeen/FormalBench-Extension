import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class AreaPolygon {
    
    public static Double areaPolygon(int s, int l) {
        return s * (l * l) / (4 * Math.tan(Math.PI / s));
    }
}

