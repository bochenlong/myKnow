package org.bochenlong;

import java.security.SecureRandom;

/**
 * Created by bochenlong on 16-12-29.
 */
public class RandomUtil {
    
    /**
     *
     * @param min
     * @param max
     * @return [min-max)
     */
    private static int random(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(max - min) + min;
    }
    
    /**
     * @param max
     * @return [0-max)
     */
    private static int random(int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(max);
    }
    
}
