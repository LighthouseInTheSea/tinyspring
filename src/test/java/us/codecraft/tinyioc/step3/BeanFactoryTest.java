package us.codecraft.tinyioc.step3;

import org.junit.Test;
import us.codecraft.tinyioc.step3.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.step3.factory.BeanFactory;

/**
 * @author: 祁文杰(灯塔)
 * @Date: 2022/2/9 13:11
 * @Description:
 */
public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("us.codecraft.tinyioc.step3.HelloWorldService");

        //3.设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text","Hello World!"));
        beanDefinition.setPropertyValues(propertyValues);

        //4.生成bean
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 5.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}