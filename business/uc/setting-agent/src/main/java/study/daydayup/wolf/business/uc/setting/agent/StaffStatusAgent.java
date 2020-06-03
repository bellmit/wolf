package study.daydayup.wolf.business.uc.setting.agent;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.setting.agent.util.StatusUtil;
import study.daydayup.wolf.business.uc.setting.api.entity.StaffStatus;
import study.daydayup.wolf.business.uc.setting.api.enums.StatusEnum;
import study.daydayup.wolf.business.uc.setting.api.service.StaffStatusService;

import java.util.*;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
@Component
@Scope("prototype")
public class StaffStatusAgent {
    private static final int STATUS_LENGTH = 20;
    private boolean isInit = false;

    private long accountId;
    private long orgId;

    private BitSet statusSet;

    @Reference
    private StaffStatusService staffStatusService;

    public void init(long accountId, long orgId) {
        if (accountId <= 0 || orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0");
        }

        if (isInit) {
            return;
        }

        StaffStatus status = staffStatusService.find(accountId, orgId).notNullData();

        this.accountId = accountId;
        this.orgId = orgId;
        statusSet = StatusUtil.initStatus(status);

        this.isInit = true;
    }

    public boolean get(StatusEnum status) {
        int code = status.getCode();

        return statusSet.get(code);
    }

    public StaffStatusAgent set(StatusEnum status) {
        return set(status, true);
    }

    public StaffStatusAgent set(StatusEnum status, boolean state) {
        int code = status.getCode();
        statusSet.set(code, state);

        return this;
    }

    public void save() {
        long[] sArray = statusSet.toLongArray();
        StaffStatus status = arrayToModel(sArray);

        staffStatusService.save(status);
    }

    private StaffStatus arrayToModel(long[] s) {
        if (s.length != STATUS_LENGTH + 1) {
            throw new IllegalArgumentException("invalid status array format");
        }

        StaffStatus status = new StaffStatus();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        long[] sArray = StatusUtil.formatBitArray(s, STATUS_LENGTH);
        StatusUtil.setStatus(status, sArray);

        return status;
    }


}
