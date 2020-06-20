package study.daydayup.wolf.business.pay.biz.dal.dao;

import study.daydayup.wolf.business.pay.biz.dal.dataobject.PaymentLogDO;

public interface PaymentLogDAO {
    int deleteById(Long id);

    int insert(PaymentLogDO record);

    int insertSelective(PaymentLogDO record);

    PaymentLogDO selectById(Long id);

    int updateByIdSelective(PaymentLogDO record);

    int updateById(PaymentLogDO record);
}