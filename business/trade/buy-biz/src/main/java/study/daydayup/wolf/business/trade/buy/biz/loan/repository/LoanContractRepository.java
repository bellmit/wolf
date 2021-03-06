package study.daydayup.wolf.business.trade.buy.biz.loan.repository;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanContractEntity;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;


/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.repository
 *
 * @author Wingle
 * @since 2019/12/26 8:36 下午
 **/
@Component
public class LoanContractRepository extends AbstractRepository implements Repository {
    @Reference
    private ContractService contractService;

    public void add(LoanContractEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return;
        }

        contractService.create(entity.getModel());
        fire(entity.getEventList());
    }

    public void save(LoanContractEntity entity) {
        if (entity == null
                || null == entity.getKey()
                || null == entity.getChanges()) {
            return;
        }

        contractService.modify(entity.getKey(), entity.getChanges());
        fire(entity.getEventList());
    }

    public LoanContractEntity find(TradeId tradeId) {
        tradeId.valid();

        Contract contract = contractService.find(tradeId).getData();
        if (contract == null) {
            return null;
        }

        return new LoanContractEntity(contract, false);
    }
}
