package com.ihesen.think.utils.net.exception;

/**
 * @author YaoWeihui on 2016/5/16.
 */
public class TokenException extends Exception {
    public TokenException() {
        super("token失效");
    }
}
