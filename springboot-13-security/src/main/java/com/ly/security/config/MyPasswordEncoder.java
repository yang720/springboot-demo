package com.ly.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liuyang
 * @date 2020/3/13 19:07
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }

}
