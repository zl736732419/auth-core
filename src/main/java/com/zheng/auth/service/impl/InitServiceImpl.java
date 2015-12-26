package com.zheng.auth.service.impl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.annotation.WebInitParam;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.zheng.auth.listener.SpringManager;
import com.zheng.auth.service.IInitService;

@Service
public class InitServiceImpl implements IInitService {

	@Autowired
	private ApplicationContext ctx;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initEntityByXml(String filename) {
		Element root = readRootElement(filename);
		String pname = root.attributeValue("package");

		List<Element> initEntities = root.selectNodes("/entities/initEntity");
		for (Element e : initEntities) {
			// 如果这个实体存在就不添加
			if (e.attributeValue("exist").equals("1")) {
				continue;
			}

			String cname = e.attributeValue("class");
			cname = pname + "." + cname;// 获取类的权限类名

			String method = e.attributeValue("method");
			List<Element> entities = (List<Element>) e.selectNodes("entity");
			addElements(cname, method, entities);
		}

	}

	/**
	 * 获取文档根节点
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 上午10:00:10
	 * @param filename
	 * @return
	 */
	private Element readRootElement(String filename) {
		SAXReader reader = new SAXReader();
		Element root = null;
		try {
			Document document = reader.read(InitServiceImpl.class
					.getClassLoader().getResourceAsStream(
							"init" + File.separator + filename));
			root = document.getRootElement();

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return root;
	}

	/**
	 * 将指定权限资源实体添加到数据库中
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 上午10:09:41
	 * @param cname
	 * @param method
	 * @param entities
	 */
	private void addElements(String cname, String method, List<Element> entities) {
		for (Element e : entities) {
			addElement(cname, method, e, null);
		}
	}

	/**
	 * 添加某一个权限资源对象
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 上午10:13:52
	 * @param cname
	 * @param method
	 * @param e
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	private void addElement(String cname, String method, Element e,
			Object parent) {
		try {
			// 获取所有的属性
			List<Attribute> atts = (List<Attribute>) e.attributes();
			Object obj = Class.forName(cname).newInstance();
			String[] ms = method.split("\\.");
			if (ms.length != 2)
				throw new RuntimeException("方法格式不正确");
			String sname = ms[0];
			String mname = ms[1];
			for (Attribute att : atts) {
				String name = att.getName();
				String value = att.getValue();
				BeanUtils.copyProperty(obj, name, value);
			}
			if (parent != null) {
				BeanUtils.copyProperty(obj, "parent", parent);
			}
			Object service = ctx.getBean(sname);
			Method m = service.getClass().getMethod(mname, Object.class);
			m.invoke(service, obj);
			List<Element> es = e.selectNodes("entity");
			for (Element ele : es) {
				addElement(cname, method, ele, obj);
			}
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
