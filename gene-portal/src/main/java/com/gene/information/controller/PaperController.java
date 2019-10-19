package com.gene.information.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gene.common.annotation.Log;
import com.gene.common.utils.R;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.service.PaperService;

@Controller
@RequestMapping("/paper")
public class PaperController {
	@Autowired
	private PaperService paperService;

	@Log("跳转首页")
	@GetMapping("/index")
	public String index(){
		return "information/index";
	}
	
	@Log("获取所有的产品")
	@GetMapping("/getAllProduct")
	@ResponseBody
	public Map<String,Object> getAllProduct(){
		return paperService.getAllProduct();
	}
	
	
	
	@Log("保存选择的产品")
	@GetMapping("/saveChoosedProduct")
	public String saveChoosedProduct( Integer[] products,HttpServletRequest request){
		request.getSession().setAttribute("products", products);
		return "information/yinshixiguan";
	}
	
	@Log("输入姓名")
	@GetMapping("/enterName")
	public String enterName(){
		return "information/jibenxinxi";
	}
    
	@Log("保存")
	@GetMapping("/saveYourName")
	public String saveYourName(String name,HttpServletRequest request,Model model){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		CustomerPaperDO customerPaperDO = paperService.saveChoosedProduct(products,name);
		request.getSession().setAttribute("customerPaperDO", customerPaperDO);
		model.addAttribute("name", name);
		return "information/jibenxinxi2";
	}
	
	@Log("跳转答题分类页面")
	@GetMapping("/fenlei")
	public String fenlei(int flag,Model model){
		model.addAttribute("flag",flag);
		return "information/jibenxinxi3";
	}
	
	@Log("开始答题")
	@GetMapping("/beginAnswer")
	public String beginAnswer(Integer flag,Model model,HttpServletRequest request){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		List<QuestionDO> list=null;
		if(flag==0)//基本信息
			return "information/jibenxinxi4";
		else if(flag==1){//身体状况
			list=paperService.getQuestionDOType(products,"SHENTI_ZHUNGKUANG");
		}
		else if(flag==2){//饮食状况
			list=paperService.getQuestionDOType(products,"YINSHI_XIGUAN");
		}
		else{//生活习惯
			list=paperService.getQuestionDOType(products,"SHENGHUO_XIGUAN");
		}
		model.addAttribute("list",list);
		return "information/jibenxinxi6";
	}
	
	@Log("保存基本信息")
	@ResponseBody
	@PostMapping("/saveJiBenXinxi")
	public R saveJiBenXinxi(CustomerPaperDO customerPaperDO,HttpServletRequest request){
		CustomerPaperDO c = (CustomerPaperDO) request.getSession().getAttribute("customerPaperDO");
		if(c!=null){
			customerPaperDO.setId(c.getId());
			paperService.updateCustomerPaperDO(customerPaperDO);
			return R.ok();
		}
		else
			return R.error("当前页面停留时间太长，请重新进入...");
	}
	
	@Log("保存问卷答题")
	@PostMapping("/saveWenJuan")
	@ResponseBody
	public R saveWenJuan(String objs,HttpServletRequest request){
		return paperService.saveWenJuan(objs,request);
	}
}
