import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class LateralsurfaceCuboid {
    
    public static int lateralsurfaceCuboid(int l, int w, int h) {
        int res = (w * h) * 2;
        res = res + (l * 2 * h);
        return res;
    }
}

