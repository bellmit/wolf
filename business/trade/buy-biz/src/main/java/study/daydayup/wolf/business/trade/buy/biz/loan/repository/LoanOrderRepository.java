package study.daydayup.wolf.business.trade.buy.biz.loan.repository;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanOrderEntity;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.repository
 *
 * @author Wingle
 * @since 2019/12/26 8:36 下午
 **/
@Component
public class LoanOrderRepository extends AbstractRepository implements Repository {
    @Reference
    private OrderService orderService;

    public LoanOrderEntity find(@NonNull TradeId tradeId) {
        return find(tradeId, null);
    }

    public LoanOrderEntity find(@NonNull TradeId tradeId, OrderOption option) {
        tradeId.valid();

        Order order = orderService.find(tradeId).notNullData();
        return new LoanOrderEntity(order);
    }

    public void save(LoanOrderEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return;
        }

        if (entity.isNew()) {
            createEntity(entity);
            return;
        }

        updateEntity(entity);
    }

    private void updateEntity(LoanOrderEntity entity) {
        Order key = entity.getKey();
        Order changes = entity.getChanges();

        if (key == null || null == changes) {
            return;
        }

        orderService.modify(key, changes);
    }

    private void createEntity(LoanOrderEntity entity) {
        orderService.create(entity.getModel());
    }

}
