package com.gene.information.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gene.common.utils.PageUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.service.CustomerPaperService;


/**
 * 客户问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 11:20:31
 */
 
@Controller
@RequestMapping("/information/customerPaper")
public class CustomerPaperController {
	@Autowired
	private CustomerPaperService customerPaperService;
	
	@GetMapping()
	@RequiresPermissions("information:customerPaper:customerPaper")
	String CustomerPaper(){
	    return "information/customerPaper/customerPaper";
	}
	
	@ResponseBody
	@GetMapping("/userList")
	public List userList(@RequestParam Map<String, Object> params){
		//查询列表数据
//		Query query = new Query(params);
		List<Object> userList = customerPaperService.userList();
//		PageUtils pageUtils = new PageUtils(userList, userList.size());
		return userList;
			
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:customerPaper:customerPaper")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CustomerPaperDO> customerPaperList = customerPaperService.list(query);
		int total = customerPaperService.count(query);
		PageUtils pageUtils = new PageUtils(customerPaperList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:customerPaper:add")
	String add(){
	    return "information/customerPaper/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:customerPaper:edit")
	String edit(@PathVariable("id") Integer id,Model model){	
		CustomerPaperDO customerPaper = customerPaperService.get(id);
		model.addAttribute("customerPaper", customerPaper);
	    return "information/customerPaper/edit";
	}
	
	@ResponseBody
	@GetMapping("/useredit/{id}")
	List<Object> edit2(@PathVariable("id") Long id){
		List<Object> list2 = new ArrayList<>();
		List<CustomerPaperDO> list = customerPaperService.getUserList(id);
		if(list.size()>0){
			Map<String, Object> params = new HashMap<String, Object>();
//			System.out.println(JSON.toJSONString(list));
			for (CustomerPaperDO customerPaperDO2 : list) {
				params.put(customerPaperDO2.getContent(), customerPaperDO2.getTiankonganswer());
			}
			params.put("userId", id);
			params.put("昵称", list.get(0).getUsername());
		list2.add(params);
		}
		return list2;		
	}
	
	
	@GetMapping("/details/{id}")
	String details(@PathVariable("id") Integer id,Model model){
		CustomerPaperDO customerPaper = customerPaperService.get(id);
		List<CustomerPaperDO> questionDetails = customerPaperService.queryUserQuestionDetails(id);
		/*for (CustomerPaperDO customerPaperDO : questionDetails) {
			String choiceId = customerPaperDO.getChoiceId();
			String[] split = choiceId.split(",");
			for (String string : split) {
				CustomerPaperDO choiceContent = customerPaperService.getChoiceContent(Integer.valueOf(string));
				customerPaperDO.setChoiceContent(choiceContent.getChoiceContent());
			}
		}*/
		model.addAttribute("customerPaper", customerPaper);
		model.addAttribute("questionDetails", questionDetails);
	    return "information/customerPaper/details";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:customerPaper:add")
	public R save( CustomerPaperDO customerPaper){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", customerPaper.getCode());
		List<CustomerPaperDO> list = customerPaperService.list(params);
		if(list.size()>0){
			return R.error("检测码已存在");
		}
		customerPaper.setCreateTime(new Date());
		if(customerPaperService.save(customerPaper)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:customerPaper:edit")
	public R update( CustomerPaperDO customerPaper){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", customerPaper.getCode());
		List<CustomerPaperDO> list = customerPaperService.list(params);
		if(list.size()>0){
			return R.error("检测码已存在");
		}
		customerPaperService.update(customerPaper);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:customerPaper:remove")
	public R remove( Integer id){
		if(customerPaperService.remove(id)>0){
			CustomerPaperDO customerPaper = new CustomerPaperDO();
			customerPaper.setId(id);
			customerPaper.setDelFlag("2");
			customerPaperService.update(customerPaper);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:customerPaper:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		customerPaperService.batchRemove(ids);
		return R.ok();
	}
	
}
