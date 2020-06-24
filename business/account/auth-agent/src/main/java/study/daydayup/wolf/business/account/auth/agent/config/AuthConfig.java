package study.daydayup.wolf.business.account.auth.agent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:42 上午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.auth")
public class AuthConfig {
    private boolean enable = true;

    private CaptchaConfig captcha;

    private String denyCode     = "110500";
    private String denyMessage  = "Access Denied, You don’t have permission to access on this Server";

    private String sessionKey = "WSESSIONID";

    private String mode = "exclude";
    private String authPath = "/auth/**/*";
    private String excludedPaths = "/auth/**/*";

    private int codeExpiredIn = 15*60;
    private int expiredIn = 60*60*24*30;
    private int refreshExpiredIn = 60*60*24*30;

    public CaptchaConfig getCaptcha() {
        if (captcha != null) {
            return captcha;
        }

        captcha = new CaptchaConfig();
        return captcha;
    }

}
