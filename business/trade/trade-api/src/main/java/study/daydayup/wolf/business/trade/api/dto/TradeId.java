package study.daydayup.wolf.business.trade.api.dto;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.exception.InvalidTradeIdException;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/12/25 9:33 下午
 **/
@Data
public class TradeId implements Request {
    @NotBlank
    private String tradeNo;

    private Long buyerId;
    private Long sellerId;

    public void valid() {
        if (tradeNo == null || tradeNo.length() < 20) {
            throw new InvalidTradeIdException("invalid tradeNo:" + tradeNo);
        }

        if (buyerId == null && sellerId == null) {
            throw new InvalidTradeIdException("BuyerId and SellerId can't both null");
        }
    }
}
