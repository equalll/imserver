package com.cniao5.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * token缓存
 *
 * @author sony
 */
public class UserTokenCache {

    private static Map<String, Long> tokenMap = new HashMap<String, Long>();

    public static void put(String token, Long id) {
        tokenMap.put(token, id);
    }

    public static Long get(String token) {
        return tokenMap.get(token);
    }

}
