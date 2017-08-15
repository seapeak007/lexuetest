package com.lexue.util;

/**
 * Created by UI03 on 2017/8/4.
 */
public class UidUtil {

    private static String getUidBaseName(String key) {
        int keyInt = DJBHash(key);
        keyInt = keyInt % 23 + 1;
        StringBuilder sb = new StringBuilder();
        return sb.append("uidbase").append("_").append(keyInt).toString();
    }

    private static int DJBHash(String key) {
        int hash = 5381;
        int n = key.length();

        for(int i = 0; i < n; ++i) {
            hash += (hash << 5) + key.charAt(i);
        }

        return hash > 0?hash:-hash;
    }

    public static void  main(String args[]){
        String key="t" + "15901369410" ;
        System.out.print("db:"+getUidBaseName(key));

    }
}
