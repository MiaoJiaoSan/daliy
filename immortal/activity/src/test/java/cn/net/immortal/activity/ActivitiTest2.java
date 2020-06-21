package cn.net.immortal.activity;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class ActivitiTest2 {

  @Test
  public void generate(){
    //创建引擎
    ProcessEngineConfiguration configuration
        = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    //资源相关的service
    RepositoryService repositoryService = processEngine.getRepositoryService();
    //进行部署
    //act_re_deployment 部署信息
    //act_re_procdef 流程定义信息
    //act_ge_bytearray 流程文件
    //一个流程定义 可以有多个流程实例
    DeploymentBuilder deployment = repositoryService.createDeployment();
    deployment.addClasspathResource("holiday.bpmn")
        .addClasspathResource("holiday.png")
        .name("请假流程部署")
        .deploy();
    //
  }
}
