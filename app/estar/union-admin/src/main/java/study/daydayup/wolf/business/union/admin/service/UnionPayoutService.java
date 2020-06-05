package study.daydayup.wolf.business.union.admin.service;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.payout.PayoutFailException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.service.PayoutService;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.admin.service
 *
 * @author Wingle
 * @since 2020/3/23 7:04 下午
 **/
@Component
public class UnionPayoutService {
    @Reference(timeout = 10000)
    private PayoutService payoutService;

    public void payout(@NonNull Order order) {
        PayoutRequest request = createRequest(order);

        Result<PayoutResponse> result = payoutService.payout(request);
        if (!result.isSuccess()) {
            throw new PayoutFailException();
        }
    }

    private PayoutRequest createRequest(@NonNull Order order) {
        return PayoutRequest.builder()
                .paymentMethod(PaymentChannelEnum.RAZORPAY_PAYOUT.getCode())
                .tradeNo(order.getTradeNo())
                .duplicateCheck(true)

                .payerId(order.getBuyerId())
                .payerName(order.getBuyerName())
                .payeeId(order.getSellerId())
                .payeeName(order.getSellerName())

                .amount(order.getAmount())
                .currency(order.getCurrency())

                .build();
    }
}
