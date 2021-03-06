package study.daydayup.wolf.common.lang.enums;

import java.io.Serializable;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 10:21 PM
 **/
public interface CodeBasedEnum extends Serializable {
    int getCode();
    String getName();
}
