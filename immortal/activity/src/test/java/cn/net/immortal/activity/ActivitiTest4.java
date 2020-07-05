package cn.net.immortal.activity;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.List;

public class ActivitiTest4 {

  @Test
  public void generate(){

    ProcessEngineConfiguration configuration
        = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
    TaskService taskService = processEngine.getTaskService();
    TaskQuery taskQuery = taskService.createTaskQuery();
    TaskQuery zhangsan = taskQuery.processVariableValueEqualsIgnoreCase("assignee","zhangsan");
    System.out.println(zhangsan.list());
  }
}
