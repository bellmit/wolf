package study.daydayup.wolf.business.org.api.task.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.org.api.task.domain.entity.task
 *
 * @author Wingle
 * @since 2020/3/15 11:01 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTrade implements Model {
    private Long orgId;
    private Long partnerOrgId;
    private Long staffId;
    private Long taskId;

    private Long customerId;
    private String customerName;

    private Integer tradeType;
    private String tradeNo;
    private Integer installmentNo;

    private Integer currency;
    private BigDecimal amount;

    private BigDecimal paidAmount;
    private BigDecimal payingAmount;

    private BigDecimal interest;
    private BigDecimal interestRate;
    private Integer interestUnit;

    private LocalDate dueAt;
    private Integer dueDays;
    private Integer periodStrategy;
}
