package study.daydayup.wolf.business.trade.api.enums.state;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum CompletedStateEnum implements CodeBasedEnum {
    REFUND(20, "退款关闭"),
    PAY_EXPIRED(10, "支付超时关闭")
    ;

    private Integer code;
    private String desc;
    CompletedStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
