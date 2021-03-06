package us.codecraft.tinyioc.step1;

import org.junit.Test;

/**
 * @author: 祁文杰(灯塔)
 * @Date: 2022/2/9 10:53
 * @Description: IoC最基本的角色有两个：容器(BeanFactory)和Bean本身。这里使用BeanDefinition来封装了bean对象，这样可以保存一些额外的元信息。测试代码
 */
public class BeanFactoryTest {

    @Test
    public void test() {
        //1.初始化beanfactory
        BeanFactory beanFactory = new BeanFactory();

        //2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        beanFactory.registerBeanDefinition("helloWorldService",beanDefinition);

        //3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}