package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/18 10:53 上午
 **/
@Data
public class TradeLoan implements VO  {
    private Integer handlingFeeRate;

    private Integer interestRate;
    private Integer penaltyRate;
    private Integer interestUnit;
    private Integer penaltyUnit;

    private Integer period;
    private Integer periodUnit;
    private Integer periodStrategy;

    private Integer repayStrategy;
    private Integer prepayStrategy;
    private Integer amountStrategy;
    private Integer feePayStrategy;
}
