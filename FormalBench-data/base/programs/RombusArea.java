import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RombusArea {
    
    public static int rombusArea(int p, int q) {
        int area=0;
        if(p<q){
            int d=p;
            p=q;
            q=d;
        }
        area=(p*q)/2;
        return area;
    }
}

