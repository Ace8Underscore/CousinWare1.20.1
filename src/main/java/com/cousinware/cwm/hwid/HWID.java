package com.cousinware.cwm.hwid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HWID {

    public static URL pastebin;
    static List<String> hwids = new ArrayList<>();

    static {
        try {
            pastebin = new URL("https://cousinware.com/hwid.php");
        } catch (MalformedURLException e) {

        }
    }


    public HWID() throws MalformedURLException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(pastebin.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                hwids.add(inputLine);
                System.out.println(inputLine);
            }
        } catch (Exception e) {

        }
    }

    public static boolean isGoodHWID(String id) {
        return hwids.contains(id);
    }

}