package study.daydayup.wolf.business.uc.setting.agent;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.setting.agent.util.StatusUtil;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanyStatus;
import study.daydayup.wolf.business.uc.setting.api.enums.StatusEnum;
import study.daydayup.wolf.business.uc.setting.api.service.CompanyStatusService;

import java.util.BitSet;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
@Component
@Scope("prototype")
public class CompanyStatusAgent {
    private static final int STATUS_LENGTH = 20;
    private boolean isInit = false;

    private long orgId;
    private BitSet statusSet;

    @Reference
    private CompanyStatusService companyStatusService;

    public void init(long orgId) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("orgId can not less than 0");
        }

        if (isInit) {
            return;
        }

        CompanyStatus status = companyStatusService.find(orgId).notNullData();

        this.orgId = orgId;
        statusSet = StatusUtil.initStatus(status);

        this.isInit = true;
    }

    public boolean get(StatusEnum status) {
        int code = status.getCode();

        return statusSet.get(code);
    }

    public CompanyStatusAgent set(StatusEnum status) {
        return set(status, true);
    }

    public CompanyStatusAgent set(StatusEnum status, boolean state) {
        int code = status.getCode();
        statusSet.set(code, state);

        return this;
    }

    public void save() {
        long[] sArray = statusSet.toLongArray();
        CompanyStatus status = arrayToModel(sArray);

        companyStatusService.save(status);
    }

    private CompanyStatus arrayToModel(long[] s) {
        if (s.length != STATUS_LENGTH + 1) {
            throw new IllegalArgumentException("invalid status array format");
        }

        CompanyStatus status = new CompanyStatus();
        status.setOrgId(orgId);

        long[] sArray = StatusUtil.formatBitArray(s, STATUS_LENGTH);
        StatusUtil.setStatus(status, sArray);

        return status;
    }
}
