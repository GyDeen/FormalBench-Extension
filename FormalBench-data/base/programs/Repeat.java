import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class Repeat {
    
    public static List<Integer> repeat(List<Integer> x) {
        int k = 0;
        List<Integer> ans = new LinkedList<Integer>();
        for(int i = 0; i < x.size(); i++) {
            for(int j = 0; j < x.size(); j++) {
                if(i != j) {
                    if(x.get(i) == x.get(j) && !ans.contains(x.get(i))) {
                        ans.add(x.get(i));
                    }
                }
            }
        }
        return ans;
    }
}

