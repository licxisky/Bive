package com.lichaoxi.bive.utils;

import com.lichaoxi.bive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    static {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public static User bcrypt(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

    public static boolean matches(String password, String passhash) {
        return bCryptPasswordEncoder.matches(password, passhash);
    }

}
