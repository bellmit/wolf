package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.business.pay.biz.service.india.cashfree.CashfreeService;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api.india
 *
 * @author Wingle
 * @since 2020/4/26 6:40 下午
 **/
@Component
public class DLocalServiceImpl implements CashfreeService {
    @Resource
    private DLocalCreator dLocalCreator;
    @Resource
    private DLocalSubscriber dLocalSubscriber;

    @Override
    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        PaymentCreateResponse response = dLocalCreator.create(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return null;
    }

    @Override
    public Result<SubscribeResponse> subscribe(SubscribeRequest request) {
        if (StringUtil.isBlank(request.getData())) {
            throw  new InvalidPayRequestException("cashfree data can't be blank");
        }
        int code = dLocalSubscriber.subscribe(request);
        SubscribeResponse response = SubscribeResponse.builder()
                .code(code)
                .build();
        return Result.ok(response);
    }

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        return null;
    }
}
