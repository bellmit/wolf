package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.framework.layer.domain.Entity;

/**
 * study.daydayup.wolf.business.trade.buy.loan
 *
 * @author Wingle
 * @since 2019/12/13 4:41 下午
 **/
public class LoanOrderEntity extends AbstractLoanOrder implements Entity  {
    public LoanOrderEntity(Contract contract) {
        super(contract);
    }
}
