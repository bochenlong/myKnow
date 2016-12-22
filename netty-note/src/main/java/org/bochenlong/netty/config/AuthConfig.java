package org.bochenlong.netty.config;

import org.bochenlong.netty.server.auth.UserDefineAuth;
import org.bochenlong.netty.server.auth.WhiteListAuth;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class AuthConfig {
    private static AuthType authType;
    
    public static AuthType authType() {
        if (authType == null) SINGLE_CON();
        return authType;
    }
    
    public static void SINGLE_CON() {
        authType = AuthType.SINGLE_CON;
    }
    
    public static void WHITE_LIST(Set<String> whiteList) {
        assert whiteList != null;
        authType = AuthType.WHITE_LIST;
        WhiteListAuth.me().setWhiteList(whiteList);
    }
    
    public static void USER_DEFINE(Predicate predicate, Consumer consumer) {
        assert predicate != null && consumer != null;
        authType = AuthType.USER_DEFINE;
        UserDefineAuth.me().setPredicate(predicate);
        UserDefineAuth.me().setConsumer(consumer);
    }
    
    public enum AuthType {
        WHITE_LIST,
        SINGLE_CON,
        USER_DEFINE
    }
}
