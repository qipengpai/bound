package com.qpp.utils.operation;

/**
 * @author qipengpai
 * @Title: StringToInt
 * @ProjectName bound
 * @Description: TODO (曾用于表情转换)
 * @date 17:33 2018/10/11
 */
public class StringToInt {

    public static void main(String[] args) {
        System.err.println(toInt("7IARf0WJF7RoSzBsrhFc69BwHO7ampx9"));;
    }

    public static String toInt(String str) {
        String name="";
        char[] one= str.toCharArray();
        for (int i = 0; i < one.length; i++) {
            int os=one[i];
            name=name+" "+os;
        }
        return name;
    }

    public static String toString(String str){
        String name="";
        String[] zf=str.split(" ");
        for (int i = 0; i < zf.length; i++) {
            if(!"".equals(zf[i])){
                char zic=(char) Integer.parseInt(zf[i]);
                name =name+zic;
            }
        }
        return name;
    }
}
