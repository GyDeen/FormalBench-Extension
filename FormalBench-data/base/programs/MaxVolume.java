import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MaxVolume {
    
    public static int maxVolume(int s) {
        int maxVolume = 0;

        for (int l = 1; l <= s; l++) {
            for (int b = 1; b <= s - l + 1; b++) {
                int h = s - l - b;
                int volume = l * b * h;
                if (volume > maxVolume) {
                    maxVolume = volume;
                }
            }
        }
        return maxVolume;
    }
}

