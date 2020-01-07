package com.tcbd07.mintproject.util;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;

public class UserAgentUtil {


    public static UASparser uaSparser=null;

    static {
        try {
            uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        String request="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36";
        try {
            UserAgentInfo userAgentInfo=UserAgentUtil.uaSparser.parse(request);

            System.out.println(userAgentInfo.getDeviceType());
            System.out.println(userAgentInfo.getUaFamily());
            System.out.println(userAgentInfo.getOsName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
