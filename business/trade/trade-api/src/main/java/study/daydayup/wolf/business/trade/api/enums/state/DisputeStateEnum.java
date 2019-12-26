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
public enum DisputeStateEnum implements CodeBasedEnum {
    REFUND(30, "退款"),
    REQUEST_HANDLING(20, "维权处理中"),
    REQUEST_MADE(10, "发起维权")
    ;

    private Integer code;
    private String desc;
    DisputeStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
