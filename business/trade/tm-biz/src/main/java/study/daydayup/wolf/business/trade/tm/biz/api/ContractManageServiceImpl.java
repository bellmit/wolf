package study.daydayup.wolf.business.trade.tm.biz.api;

import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.ContractRequest;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.api.service.tm.ContractManageService;
import study.daydayup.wolf.business.trade.tm.biz.engine.ContractQueryEngine;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.tm.biz.api
 *
 * @author Wingle
 * @since 2020/1/10 12:20 下午
 **/
@RpcService(protocol = "dubbo")
public class ContractManageServiceImpl implements ContractManageService {
    @Reference
    private ContractService contractService;
    @Resource
    private ContractQueryEngine engine;

    @Override
    public Result<Page<Contract>> find(ContractRequest request) {
        return engine.query(request);
    }

    public Result<Contract> find(TradeId tradeId) {
        tradeId.valid();

        Contract contract = contractService.find(tradeId).getData();
        return Result.ok(contract);
    }

}