package com.xuxiao.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxiao.crud.bean.Employee;
import com.xuxiao.crud.bean.EmployeeExample;
import com.xuxiao.crud.bean.EmployeeExample.Criteria;
import com.xuxiao.crud.dao.EmployeeMapper;
@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
    /**
     * �÷�����ѯ����Ա��
     * @return
     */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	/**
	 * Ա�����淽��
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);		
	}
	/**
	 * �����û����Ƿ����
	 * @param empName
	 * @return true:����    false��������
	 */
	public boolean checkUser(String empName) {
		//������ѯ����
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}
	/**
	 * ����Ա��id��ѯԱ��
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	/**
	 * ����Ա��
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		//updateByPrimaryKeySelective����������ѡ��ĸ��£�����ʲô��������ʲô����
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	/**
	 * ɾ��Ա��
	 * @param id
	 */
	public void deleteEmpById(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
		
	}
	public void deleteBatch(List<Integer> del_ids) {
		EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //delete from xxx where emp_id in(1,2,3)
        criteria.andEmpIdIn(del_ids);
        employeeMapper.deleteByExample(example);
		
	}

}
