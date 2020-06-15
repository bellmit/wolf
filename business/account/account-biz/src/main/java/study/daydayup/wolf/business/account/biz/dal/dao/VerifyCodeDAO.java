package study.daydayup.wolf.business.account.biz.dal.dao;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.account.biz.dal.dataobject.VerifyCodeDO;

public interface VerifyCodeDAO {
    int deleteById(Long id);

    int insert(VerifyCodeDO record);

    int insertSelective(VerifyCodeDO record);

    VerifyCodeDO selectById(Long id);

    int updateByIdSelective(VerifyCodeDO record);

    int updateById(VerifyCodeDO record);

    VerifyCodeDO selectByMobile(@Param("mobile")String mobile);

    int updateExpiredAtById(@Param("id")Long id,@Param("updatedExpiredAt") LocalDateTime updatedExpiredAt);



}