package com.ahmedukamel.problemsolver.util;


public class CustomFormatter {
    public static String formatPhone(String phone) {
        String cleanedPhoneNumber = phone.replaceAll("\\D", "");
        return cleanedPhoneNumber.replaceFirst("(\\d{2})(\\d{2})(\\d{3})(\\d{4})", "+20 $1 $2 $3 $4");
    }
}
