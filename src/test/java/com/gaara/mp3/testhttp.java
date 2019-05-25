package com.gaara.mp3;

import com.gaara.mp3.utils.HttpRequest;

/**
 * Created by Gaara_Xu
 * on 2019/5/25.
 */
public class testhttp {

    public static void main(String[] args) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.sendGet("http://39.97.186.148:8082/psi/services/utils/ping","");
    }
}
