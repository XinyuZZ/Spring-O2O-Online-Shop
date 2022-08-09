package com.imooc.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession()
                .getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = (String) HttpServletRequestUtil.getString(request, "verifyCodeActual");
        return Objects.equals(verifyCodeActual.toLowerCase(), verifyCodeExpected.toLowerCase());
    }
}
