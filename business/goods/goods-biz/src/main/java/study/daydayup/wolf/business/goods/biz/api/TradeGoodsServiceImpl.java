package study.daydayup.wolf.business.goods.biz.api;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.goods.api.dto.GoodsOption;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.trade.GoodsDTO;
import study.daydayup.wolf.business.goods.api.exception.InvalidTradeGoodsRequestException;
import study.daydayup.wolf.business.goods.api.service.TradeGoodsService;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsDAO;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsInstallmentDAO;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsLoanDAO;
import study.daydayup.wolf.business.goods.biz.dal.dao.SkuDAO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsLoanDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/18 11:33 上午
 **/
@RpcService
public class TradeGoodsServiceImpl implements TradeGoodsService {
    private GoodsOption option;
    private Long  orgId;
    private List<Long> goodsIds;

    @Resource
    private GoodsDAO goodsDAO;
    @Resource
    private SkuDAO skuDAO;
    @Resource
    private GoodsLoanDAO loanDAO;
    @Resource
    private GoodsInstallmentDAO installmentDAO;

    /**
     * @TODO: validate
     * @param requests
     * @return
     */
    @Override
    public Result<List<GoodsDTO>> find(List<TradeGoodsRequest> requests, GoodsOption option) {
        if (requests == null || requests.isEmpty()) {
            throw new IllegalArgumentException("TradeGoodsRequest can't be null");
        }

        List<GoodsDTO> responses = new ArrayList<>();

        findGoodList(requests, responses);
        findSkuList(requests, responses);
        findLoanList(requests, responses);
        findInstallmentList(requests, responses);

        return Result.ok(responses);
    }

    @Override
    public Result<List<GoodsDTO>> find(List<TradeGoodsRequest> requests) {
        return find(requests, null);
    }

    private void init(List<TradeGoodsRequest> requests, GoodsOption opt) {
        if (opt == null) {
            opt = new GoodsOption();
        }
        this.option = opt;

        orgId = requests.get(0).getOrgId();
        if (orgId <= 0) {
            throw  new InvalidTradeGoodsRequestException("orgId less than 0");
        }
    }

    private void findGoodList(List<TradeGoodsRequest> requests, List<GoodsDTO> responses) {
        List<Long> goodsIds = requests.stream()
                .map(TradeGoodsRequest::getGoodsId)
                .collect(Collectors.toList());

        if (goodsIds == null || goodsIds.isEmpty()) {
            throw new InvalidTradeGoodsRequestException("can't find goodsId");
        }

        this.goodsIds = goodsIds;
        this.orgId = requests.get(0).getOrgId();
        List<GoodsDO> goodsDOList = goodsDAO.selectSalableByIdIn(goodsIds, orgId);
        mergeGoodsToResponse(goodsDOList, responses);
    }

    private void mergeGoodsToResponse(List<GoodsDO> goodsDOList, List<GoodsDTO> responses) {
        if (goodsDOList == null || goodsDOList.isEmpty()) {
            return;
        }

        for (GoodsDO goodsDO: goodsDOList) {
            GoodsDTO response = new GoodsDTO();
            BeanUtils.copyProperties(goodsDO, response);
            responses.add(response);
        }
    }

    private void findSkuList(List<TradeGoodsRequest> requests, List<GoodsDTO> responses) {}

    private void findLoanList(List<TradeGoodsRequest> requests, List<GoodsDTO> responses) {
        List<GoodsLoanDO> loanDOList = loanDAO.selectByGoodsIdIn(goodsIds, orgId);
        if (loanDOList == null) {
            return;
        }

        Map<Long, GoodsLoanDO> loanMap;
        loanMap = loanDOList.stream().collect(
                Collectors.toMap(GoodsLoanDO::getGoodsId, Function.identity())
        );
        mergeLoanToResponse(loanMap, responses);
    }

    private void mergeLoanToResponse(Map<Long, GoodsLoanDO> loanMap, List<GoodsDTO> responses) {
        for (GoodsDTO response : responses) {
            GoodsLoanDO loanDO = loanMap.get(response.getId());
            setLoanToResponse(response, loanDO);
        }
    }

    private void setLoanToResponse(GoodsDTO response, GoodsLoanDO loanDO) {
        if (loanDO == null) {
            return ;
        }

        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDO, loan);
        response.setLoan(loan);

        List<Installment> installmentList = JSON.parseArray(loanDO.getInstallment(), Installment.class);
        response.setInstallmentList(installmentList);
    }

    private void findInstallmentList(List<TradeGoodsRequest> requests, List<GoodsDTO> responses) {

    }

}
