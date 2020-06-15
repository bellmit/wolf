package study.daydayup.wolf.business.account.auth.agent.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.demo.ali.consumer
 *
 * @author Wingle
 * @since 2019/10/11 10:23 上午
 **/
@RestController
public class AccountController {
    @Reference
    private AccountService accountService;
    @Resource
    private Session session;

    @PutMapping("/auth/company/change/{orgId}")
    public Result<String> changeCompany(@PathVariable("orgId") Long orgId) {
        session.changeScope(orgId);
        return Result.ok("ok");
    }

    @RequestMapping("/account/create")
    public String create() {
        Account a = new Account();
        a.setAccount("wingle");

        long result = accountService.create(a);

        return  "generic result: " + result;
    }

    @RequestMapping("/account/show")
    public String show() {
        Object accountId = session.get("accountId");
        Object orgId = session.get("orgId");
        LocalDateTime expiredAt = DateUtil.asLocalDateTime(session.get("expiredAt", Long.class));
        LocalDateTime now = LocalDateTime.now();

        return "accountId:" + accountId + "; orgId:" + orgId
                + "\nexpiredAt:" + expiredAt
                + "\nnow:" + now
                ;
    }




}
