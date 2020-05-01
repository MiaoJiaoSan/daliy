package cn.net.immortal.spring.lookup;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author: miaojiaosan
 * @date: 2020/5/1
 */
@Component("woman")
@Primary
public class Woman  extends People{

  @Override
  public String getPerson() {
    return "woman";
  }

}
