package cn.net.immortal.activity;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ActivitiTest3 {

  @Test
  public void generate(){

    ProcessEngineConfiguration configuration
        = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    //启动流程实例
    //act_hi_actinst 活动的流程实例 已经执行完，正在要去做的工作
    //act_hi_procinst
    //act_hi_identitylink 历史参与者
    //act_hi_taskinst 当前任务
    //act_ru_task 节点任务
    //act_ru_execution
    //act_ru_identitylink 当前参与者
    RuntimeService runtimeService = processEngine.getRuntimeService();
    ProcessInstance holiday = runtimeService.startProcessInstanceByKey("holiday");

  }
}
