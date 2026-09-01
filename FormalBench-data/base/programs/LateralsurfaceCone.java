import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class LateralsurfaceCone {
    
    public static Double lateralsurfaceCone(int r, int h) {
        double l = Math.sqrt(r * r + h * h);
        double LSA = Math.PI * r * l;
        return LSA;
    }
}

