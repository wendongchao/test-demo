package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.Iterator;


public class MyTest3 {
    /**
     * 解析xml
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //创建DOM4J解析器对象
        SAXReader saxReader = new SAXReader();
        try {
            //读取xml文件，并生成document对象 现可通过document来操作文档
            Document document = saxReader.read("C:\\Users\\dong\\Desktop\\jbo_customer.xml");
            //获取到文档的根节点
            Element rootElement = document.getRootElement();
            System.out.println("根节点的名字是:" + rootElement.getName());
            //获取子节点列表
            Iterator it = rootElement.elementIterator();
            while (it.hasNext()) {
                Element fistChild = (Element) it.next();
                //System.out.println(fistChild.getName());
                //获取节点的属性值
                System.out.println("取节点的属性值:"+fistChild.attribute("name").getValue());
                //获取子节点的下一级节点
                Iterator iterator = fistChild.elementIterator();
                while (iterator.hasNext()) {
                    Element element = (Element) iterator.next();
                    System.out.println("\t" + element.attributeValue("name")+"  "+element.attributeValue("label"));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
