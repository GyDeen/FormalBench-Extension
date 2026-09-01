import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class VolumeSphere {
    
    public static Double volumeSphere(int r) {
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(r, 3);
        return volume;
    }
}

