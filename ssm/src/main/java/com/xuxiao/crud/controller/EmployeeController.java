package com.xuxiao.crud.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuxiao.crud.bean.Employee;
import com.xuxiao.crud.bean.Msg;
import com.xuxiao.crud.service.EmployeeService;
/**
 * ����crud����
 * @author xuxiao
 */
@Controller
@CrossOrigin 
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	/**
	 * ������������һ
	 * @param id
	 * @return
	 */
	@DeleteMapping("/emp/{ids}")
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //��װid�ļ���
            for (String string : str_ids){
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmpById(id);
        }
        return Msg.success();
    }
	/**
	 *  �������
     * Ҫ��֧��ֱ�ӷ���PUT֮������󣬻�Ҫ��װ�������е�����
     * 1��������HttpputFormContentFilter��
     * 2�����ã����������е����ݽ�����װ��һ��map��
     * 3��request�����°�װ��request.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 * �������Ա����Ϣ
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		System.out.println(employee);
		return Msg.success();
	}
	/**
	 * ����id��ѯԱ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	/**
	 * ��֤�û����Ƿ����
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuse(@RequestParam("empName")String empName) {
		boolean result = employeeService.checkUser(empName);
		if(result) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	/**
	 * �����û�
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if (result.hasErrors()){
            Map<String,Object> map = new HashMap<>();
            //У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError:errors){
                System.out.println("������ֶ�����"+fieldError.getField());
                System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
	}
	/**
     * responsebody��Ҫ����jackson��
     * @param pn
     * @return
     */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/empss")
	public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn,5);
		PageHelper.orderBy("emp_id asc");
        List<Employee> emps = employeeService.getAll();
        @SuppressWarnings("unchecked")
		PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo", page);
        
	}
	
	/**
	 * ��ѯԱ������
	 * @return
	 */
      @RequestMapping("/emps")
	  public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
    	  //�ⲻ��һ����ҳ��ѯ
    	  //����pageHelper��ҳ���
    	  //�ڲ�ѯ֮ǰֻ��Ҫ����,pnָ�ӵڼ�ҳ�飬��ҳ�룬5ָһҳ��5������
    	  PageHelper.startPage(pn,5);
    	  //startPage�����Ĳ�ѯ����һ����ҳ��ѯ  	    	  
    	  List<Employee>emps = employeeService.getAll();
    	  //ʹ��PageInfo��װ���ǵĲ�ѯ�����ֻ��Ҫ��PageInfo����ҳ�������
    	  //PageInfo��װ����ϸ�ķ�ҳ��Ϣ���������ǲ�ѯ��������,5��ʾ������ʾ5ҳ
    	  @SuppressWarnings({ "rawtypes", "unchecked" })
		PageInfo page = new PageInfo(emps,5);
    	  model.addAttribute("pageInfo",page);
		  return "list";
	  }
}
