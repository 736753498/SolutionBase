package com.futurecreator.searchquestion.utils.pwdsalt;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PwdUtil {
    @Value("${pwd.salt}")
    private String salt;

    public String getSaltedPassword(String password){
        return DigestUtils.md5Hex(password+salt);
    }
}
