package us.codecraft.tinyioc.step4.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import us.codecraft.tinyioc.step4.AbstractBeanDefinitionReader;
import us.codecraft.tinyioc.step4.BeanDefinition;
import us.codecraft.tinyioc.step4.PropertyValue;
import us.codecraft.tinyioc.step4.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author: 祁文杰(灯塔)
 * @Date: 2022/2/14 16:46
 * @Description:
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader){
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }
    
    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        //解析bean
        registerBeanDefinitions(document);
        inputStream.close();
    }

    public void registerBeanDefinitions(Document doc){
        Element root = doc.getDocumentElement();

        parseBeanDefinitions(root);

    }

    protected void parseBeanDefinitions(Element root){
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element){
                Element element = (Element)node;
                processBeanDefinition(element);
            }
        }
    }

    protected void processBeanDefinition(Element ele){
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele,beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name,beanDefinition);
    }


    private void processProperty(Element ele,BeanDefinition beanDefinition){
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
            }
        }
    }

}