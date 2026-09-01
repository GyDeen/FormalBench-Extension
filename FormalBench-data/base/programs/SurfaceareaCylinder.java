import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SurfaceareaCylinder {
    
    public static Double surfaceareaCylinder(int r, int h) {
        double surfacearea = ((2 * 3.1415 * r * r) + (2 * 3.1415 * r * h));
        return surfacearea;
    }
}

