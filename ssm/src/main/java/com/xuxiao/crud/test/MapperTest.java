package com.xuxiao.crud.test;



import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xuxiao.crud.bean.Employee;
import com.xuxiao.crud.bean.EmployeeExample;
import com.xuxiao.crud.bean.EmployeeExample.Criteria;
import com.xuxiao.crud.dao.DepartmentMapper;
import com.xuxiao.crud.dao.EmployeeMapper;

/**
 * spring��Ŀ����spring �ĵ�Ԫ���ԣ������Զ�ע��������Ҫ���
 * 1.����spring test��
 *2.@ContextConfigurationָ��spring�����ļ���λ��
 *3.ֱ��autoWired�������
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
     /**
      * ����Department.Mpper
      */
	@Autowired
     DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
     public void testCRUD() {
	   System.out.println(departmentMapper);
	   //���ɼ�������
	   //departmentMapper.insertSelective(new Department(null,"������"));
	   //departmentMapper.insertSelective(new Department(null,"���Բ�"));
	   //����Ա������
	   //employeeMapper.insertSelective(new Employee(null,"Jerry","M","jerry@qq.com",1));
	   //��������Ա��
	   //EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
	  // for(int i=0;i<1000;i++) {
		//   String uid=UUID.randomUUID().toString().substring(0, 5);//ͨ��UUID���������Ա����������ȡ5���ֶ�
	//	   mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
	//   }
	 //  System.out.println("�������");
	   
	   
}
	/**
	 * ����example������Ա��id������֮��
	 */
	@Test
	public void testExample_andEmpIdBetween() {
		  EmployeeExample example = new EmployeeExample();
		   Criteria criteria = example.createCriteria();
		   criteria.andEmpIdBetween(3, 5);
		   List<Employee> result = employeeMapper.selectByExample(example);
		   for (Employee employee : result) {
			System.out.println(employee);
		}
	}
	@Test
	public void testExample() {
		 EmployeeExample example = new EmployeeExample();
		   Criteria criteria = example.createCriteria();
		   criteria.andEmpIdLessThan(5);
	}
}
