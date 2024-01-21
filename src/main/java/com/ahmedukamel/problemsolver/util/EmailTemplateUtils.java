package com.ahmedukamel.problemsolver.util;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateUtils {
    public static String getVerificationEmail(String name, String url) {
        return String.format("""
                Hello %s,

                You registered an account on AUK Problem Solver, before being able to use your account you need to verify that this is your email address by clicking below:
                %s

                Kind Regards,
                Ahmed Kamel""", name, url);
    }
}
