package cn.net.immortal.spring.lookup;

import org.springframework.stereotype.Component;

/**
 * @author: miaojiaosan
 * @date: 2020/5/1
 */
@Component("man")
public class Man extends People{

  @Override
  public String getPerson() {
    return "man";
  }
}
