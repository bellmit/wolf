package study.daydayup.wolf.business.uc.api.setting.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range(1 ~ 20)
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum CustomerInfoEnum implements StatusEnum {
    INFO_COMPLETE(19, "customer.info.complete"),

    KYC(9, "customer.info.kyc"),
    BANK_CARD(8, "customer.info.bankCard"),
    VOTER(7, "customer.info.voter"),
    DIVING_LICENSE(6, "customer.info.drivingLicense"),
    PASSPORT(5, "customer.info.passport"),
    PAN(4, "customer.info.pan"),
    AADHAAR(3, "customer.info.aadhaar"),
    LIVENESS(2, "customer.info.liveness"),
    BASIC_INFO(1, "customer.info.basicInfo")
    ;

    private int code;
    private String desc;
    CustomerInfoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
