import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class AndTuples {
    
    public static List<Integer> andTuples(List<Integer> testTup1, List<Integer> testTup2) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < testTup1.size(); i++) {
            ans.add(testTup1.get(i) & testTup2.get(i));
        }
        return ans;
    }
}

