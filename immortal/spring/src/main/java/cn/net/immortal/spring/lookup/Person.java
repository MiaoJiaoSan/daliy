package cn.net.immortal.spring.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author: miaojiaosan
 * @date: 2020/5/1
 */
@Component("person")
@ComponentScan
public abstract class Person {

  public String getPerson() {
    return getPeople().getPerson();
  }

  @Lookup
  public abstract People getPeople();



  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Person.class);
    Person person = (Person) context.getBean("person");
    System.out.println(person.getPerson());
  }

}

