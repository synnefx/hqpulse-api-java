package com.hqpulse.helper.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Utils {

    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    private final static String SIGNATURE_ALGORITHM = "HmacSHA256";

    public static boolean allNotNull(Object... objects) {
        return Stream.of(objects)
                .noneMatch(Objects::isNull);
    }

    public static boolean isAccountIdValid(String id) {
        //return id != null && id.startsWith("MA") && id.length() == 20;
        return id != null && id.trim().length() > 10;  // && id.startsWith("MA") && id.length() == 20;
    }

    public static boolean anyNotNull(Object... objects) {
        return Stream.of(objects)
                .anyMatch(Objects::nonNull);
    }

    public static Map<String, Object> objectToMap(ObjectMapper objectMapper, Object object) {
        Map<String, Object> origMap = objectMapper.convertValue(object, Map.class);
        Map<String, Object> map = new LinkedHashMap<>();
        for (Entry<String, Object> entry : origMap.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> innerEntries = objectMapper.convertValue(entry.getValue(), Map.class);
                    for (Entry<String, Object> innerEntry : innerEntries.entrySet()) {
                        map.put(entry.getKey() + innerEntry.getKey(), innerEntry.getValue());
                    }
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return map;
    }

    public static String computeSignature(String url, String nonce, String authToken)
            throws NoSuchAlgorithmException, InvalidKeyException, MalformedURLException, UnsupportedEncodingException {
        if (!allNotNull(url, nonce, authToken)) {
            throw new IllegalArgumentException("url, nonce and authToken must be non-null");
        }

        URL parsedURL = new URL(url);
        String baseUrl = parsedURL.getProtocol() + "://" + parsedURL.getHost();
        if (parsedURL.getPort() != -1) {
            baseUrl += ":" + Integer.toString(parsedURL.getPort());
        }
        baseUrl += parsedURL.getPath();
        String payload = baseUrl + nonce;
        SecretKeySpec signingKey = new SecretKeySpec(authToken.getBytes("UTF-8"), SIGNATURE_ALGORITHM);
        Mac mac = Mac.getInstance(SIGNATURE_ALGORITHM);
        mac.init(signingKey);
        return new String(Base64.getEncoder().encode(mac.doFinal(payload.getBytes("UTF-8"))));
    }

    public static boolean validateSignature(String url, String nonce, String signature, String authToken)
            throws NoSuchAlgorithmException, InvalidKeyException, MalformedURLException, UnsupportedEncodingException {
        return computeSignature(url, nonce, authToken).equals(signature);
    }

    public static String formatCalendarToString(Calendar calendar) {
        return formatCalendarToString(calendar, "mm-dd-yyyy");
    }

    public static String formatCalendarToString(Calendar calendar, String format) {
        if (null == calendar)
            return null;
        if (null == format || "".equals(format.trim())) {
            throw new IllegalArgumentException("Invalid format");
        }
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        if (!UTC_TIME_ZONE.equals(calendar.getTimeZone())) {
            format1.setTimeZone(UTC_TIME_ZONE);
        }
        return format1.format(calendar.getTime());
    }

    public static Calendar setTimeZone(Calendar calendar, TimeZone zone) {
        if (null == zone)
            zone = UTC_TIME_ZONE;
        Calendar c = new GregorianCalendar(zone);
        if (null != calendar) {
            c.setTimeInMillis(calendar.getTimeInMillis());
            return c;
        } else {
            return null;
        }
    }


    public static Calendar setTimeZone(Calendar calendar) {
        return setTimeZone(calendar, UTC_TIME_ZONE);
    }

    public static boolean isFuture(Calendar futureDate) {
        if (null != futureDate) {
            Calendar testDate = Utils.setTimeZone(futureDate);
            return testDate.after(Calendar.getInstance(Utils.UTC_TIME_ZONE));
        }
        return false;
    }

}
