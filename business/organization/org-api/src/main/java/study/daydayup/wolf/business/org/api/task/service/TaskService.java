package study.daydayup.wolf.business.org.api.task.service;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.domain.event.TaskEvent;
import study.daydayup.wolf.business.org.api.task.dto.TaskId;
import study.daydayup.wolf.business.org.api.task.dto.TaskOption;
import study.daydayup.wolf.business.org.api.task.dto.request.task.ProjectRequest;
import study.daydayup.wolf.business.org.api.task.dto.request.task.TaskTypeRequest;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;


/**
 * study.daydayup.wolf.business.org.api.task.service
 *
 * @author Wingle
 * @since 2020/3/15 11:24 下午
 **/
public interface TaskService extends Service {
    Result<Task> find(Long taskId, Long orgId);
    Result<Task> find(Long taskId, Long orgId, TaskOption option);

    Result<Integer> add(Task task);
    Result<Integer> assign(Long taskId, Long orgId, Long staffId);
    Result<Integer> modify(TaskEvent event);

    Result<Page<Task>> findSubTasks(TaskId taskId, PageRequest pageRequest);
    Result<Page<Task>> findByTaskType(TaskTypeRequest typeRequest, PageRequest pageRequest);
    Result<Page<Task>> findByProject(ProjectRequest projectRequest, PageRequest pageRequest);
}