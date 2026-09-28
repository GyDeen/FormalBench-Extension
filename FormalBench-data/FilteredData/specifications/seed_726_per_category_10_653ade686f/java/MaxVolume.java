import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MaxVolume {
    
    /*@
    public normal_behavior
      ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
    model public static pure \bigint fb_wrap(\bigint x);

        public normal_behavior
          assignable \nothing;
          diverges s == Integer.MAX_VALUE;
          ensures s < Integer.MAX_VALUE;
          ensures \result >= 0;
          ensures (\forall int l, b; 1 <= l <= s && 1 <= b <= (\bigint)s - l + 1;
            fb_wrap((\bigint)l * b * ((\bigint)s - l - b)) <= \result);
          ensures \result == 0 || (\exists int l, b; 1 <= l <= s && 1 <= b <= (\bigint)s - l + 1;
            \result == fb_wrap((\bigint)l * b * ((\bigint)s - l - b)));
    @*/
    //@ code_java_math
    public static int maxVolume(int s) {
        int maxVolume = 0;

        /*@
        loop_invariant maxVolume >= 0;
        loop_invariant s < Integer.MAX_VALUE ==> 1 <= l && (\bigint)l <= (s < 1 ? 1 : (\bigint)s + 1);
        loop_invariant s < Integer.MAX_VALUE ==> (\forall int p, q; 1 <= p < l && 1 <= q <= (\bigint)s - p + 1; fb_wrap((\bigint)p * q * ((\bigint)s - p - q)) <= maxVolume);
        loop_invariant s < Integer.MAX_VALUE ==> maxVolume == 0 || (\exists int p, q; 1 <= p < l && 1 <= q <= (\bigint)s - p + 1; maxVolume == fb_wrap((\bigint)p * q * ((\bigint)s - p - q)));
        loop_writes l, maxVolume;
        @*/
        for (int l = 1; l <= s; l++) {
            /*@
            loop_invariant maxVolume >= 0;
            loop_invariant s < Integer.MAX_VALUE ==> 1 <= b <= (\bigint)s - l + 2;
            loop_invariant s < Integer.MAX_VALUE ==> (\forall int p, q; 1 <= p <= l && 1 <= q <= (\bigint)s - p + 1 && (p < l || q < b); fb_wrap((\bigint)p * q * ((\bigint)s - p - q)) <= maxVolume);
            loop_invariant s < Integer.MAX_VALUE ==> maxVolume == 0 || (\exists int p, q; 1 <= p <= l && 1 <= q <= (\bigint)s - p + 1 && (p < l || q < b); maxVolume == fb_wrap((\bigint)p * q * ((\bigint)s - p - q)));
            loop_writes b, maxVolume;
            @*/
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

