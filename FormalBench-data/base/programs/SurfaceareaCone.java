import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SurfaceareaCone {
    
    public static Double surfaceareaCone(int r, int h) {
        double l = Math.sqrt(r * r + h * h);
        double SA = Math.PI * r * (r + l);
        return SA;
    }
}

