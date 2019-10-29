package com.gene.information.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gene.common.annotation.Log;
import com.gene.common.utils.R;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
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
	public Map<String,Object> getAllProduct(HttpServletRequest request){
		return paperService.getAllProduct(request);
	}
	
	
	
	@Log("保存选择的产品")
	@GetMapping("/saveChoosedProduct")
	public String saveChoosedProduct( Integer[] products,HttpServletRequest request){
		request.getSession().setAttribute("products", products);
		return "information/yinshixiguan";
	}
	
	@Log("输入姓名")
	@GetMapping("/enterName")
	public String enterName(Model model,HttpServletRequest request){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		int count = paperService.getgetQuestionDOSize(products);
		model.addAttribute("count", count);
		model.addAttribute("index", 0);
		return "information/jibenxinxi";
	}
    
	@Log("保存")
	@GetMapping("/saveYourName")
	public String saveYourName(String name,Integer index,Integer count,HttpServletRequest request,Model model){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		CustomerPaperDO customerPaperDO = paperService.saveChoosedProduct(products,name,request);
		request.getSession().setAttribute("customerPaperDO", customerPaperDO);
		model.addAttribute("name", name);
		model.addAttribute("index",index);
		model.addAttribute("count", count);
		return "information/jibenxinxi2";
	}
	
	@Log("跳转答题分类页面")
	@GetMapping("/fenlei")
	public String fenlei(@RequestParam(value="products",required=false) Integer[] products,int flag,Integer count,Model model,Integer index,HttpServletRequest request){
		if(products!=null){
			request.getSession().setAttribute("products", products);
			paperService.saveChoosedProduct(products,null,request);
		}
		model.addAttribute("flag",flag);
		model.addAttribute("count", count);
		model.addAttribute("index", index);
		return "information/jibenxinxi3";
	}
	
	@Log("开始答题")
	@GetMapping("/beginAnswer")
	public String beginAnswer(Integer flag,Integer count,Integer index,Model model,HttpServletRequest request){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		model.addAttribute("index", index);
		List<QuestionDO> list=null;
		
		model.addAttribute("count", count);
		if(flag==0){//基本信息
			list=paperService.getQuestionDOType(products,"JIBEN_XINXI");
			model.addAttribute("list",list);
			model.addAttribute("LEI","JIBEN_XINXI");
			return "information/jibenxinxi4";
		}
		else if(flag==1){//身体状况
			model.addAttribute("LEI","SHENTI_ZHUANG");
			list=paperService.getQuestionDOType(products,"SHENTI_ZHUANG");
		}
		else if(flag==2){//膳食习惯
			model.addAttribute("LEI","SHANSHI_XIGUAN");
			list=paperService.getQuestionDOType(products,"SHANSHI_XIGUAN");
		}
		else if(flag==3){//生活方式
			model.addAttribute("LEI","SHENGHUO_FANGSHI");
			list=paperService.getQuestionDOType(products,"SHENGHUO_FANGSHI");
		}
		else if(flag==4){//睡眠与压力
			model.addAttribute("LEI","SHUIMIAN_XIGUAN");
			list=paperService.getQuestionDOType(products,"SHUIMIAN_XIGUAN");
		}
		else if(flag==5){//运动习惯
			model.addAttribute("LEI","YUNDONG_XIGUANG");
			list=paperService.getQuestionDOType(products,"YUNDONG_XIGUANG");
		}
		else if(flag==6){//查看报告
			return "information/baogao-1";
		}
		model.addAttribute("list",list);
		
		model.addAttribute("flag", flag);
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
	
	@Log("查看报告")
	@GetMapping("/lookCheckLog")
	public String lookCheckLog(Integer product,String name, Model model,HttpServletRequest request){
		CustomerPaperDO c = (CustomerPaperDO) request.getSession().getAttribute("customerPaperDO");
		model.addAttribute("userName",c.getUsername());
		model.addAttribute("product", product);
		model.addAttribute("name",name);
		if("肠胃调理".equals(name))
			return"information/baogao-2";
		if("科学瘦身".equals(name))
			return "information/baogao-5";
		return "";
		
	}
	
	@Log("阅读检测报告--肠胃调理")
	@GetMapping("/readMyReport")
	public String readMyReport(Model model,Integer product,String name,HttpServletRequest request){
		CustomerPaperDO c = (CustomerPaperDO) request.getSession().getAttribute("customerPaperDO");
		ProductpaperDO productpaperDO = paperService.getProductPaperDO(c.getId(),product);
	    Map<String,Integer> mapD  = new HashMap<String,Integer>();
	    Map<String,Integer> mapT = new HashMap<String,Integer>();
	    DecimalFormat df = new DecimalFormat("0.0");
	    if(productpaperDO!=null){//统计分值
	    	Integer productpaper = productpaperDO.getId();
	    	List<String> fenleiList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    	for(String fenlei :fenleiList){
	    		Integer rt=paperService.getChoicedScores(productpaper,fenlei);
	    		if(rt==null) rt=0;
	    		mapD.put(fenlei,rt);
	    		mapT.put(fenlei,paperService.getAllChoicedScores(productpaper,product,fenlei));
	    	}	
	    	/**
	    	 * 计算身体现状80分得分
	    	 */
	    	String shenti80=df.format((float)mapD.get("SHENTI_ZHUANG")/mapT.get("SHENTI_ZHUANG")*100);
	    	model.addAttribute("shenti80", shenti80);
	    	/**
	    	 * 计算饮食状况 60分得分
	    	 */
	    	Integer defen=0,zongfen=0;
	    	for(String fenlei :fenleiList){
	    		
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			defen+=mapD.get(fenlei);
	    			zongfen+=mapT.get(fenlei);
	    		}
	    	}
	    	String yinshi60 =df.format((float)defen/zongfen*100);
    		model.addAttribute("yinshi60",yinshi60);
	    	/**
	    	 * 计算各个分类得分占比
	    	 */
	    	Integer zongfenzhanbi=0;
	    	Map<String,Integer> mapa = new HashMap<String,Integer>();
	    	for(String fenlei :fenleiList){
	    		mapa.put(fenlei+"zhanbi",0);
	    	}
	    	for(String fenlei :fenleiList){
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			mapa.put(fenlei+"zhanbi",mapa.get(fenlei+"zhanbi")+mapT.get(fenlei)-mapD.get(fenlei));
	    			zongfenzhanbi+=(mapT.get(fenlei)-mapD.get(fenlei));
	    		}
	    	}
	    	
	    	for(String fenlei :fenleiList){
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			model.addAttribute(fenlei+"zhanbi",df.format((float)mapa.get(fenlei+"zhanbi")/zongfenzhanbi*100));
	    		}
	    	}
	    }
		model.addAttribute("product", product);
	    return "information/baogao-3";
	}
	
	@Log("阅读检测报告--科学瘦身")
	@GetMapping("/kexueshoushens")
	public String kexueshoushen(Model model,Integer product,String name,HttpServletRequest request) {
		model.addAttribute("product", product);
		
		return "information/baogao-jianfei";
	}


	
	
	@Log("获取检测的数据")
	@GetMapping("/getMyReportData")
	@ResponseBody
	public Map<String,List<QuestionDO>> getMyReportData(Integer product,HttpServletRequest request){
		CustomerPaperDO c = (CustomerPaperDO) request.getSession().getAttribute("customerPaperDO");
		ProductpaperDO productpaperDO = paperService.getProductPaperDO(c.getId(),product);
	    Map<String,List<QuestionDO>> map  = new HashMap<String,List<QuestionDO>>();
	    if(productpaperDO!=null){
	    	Integer productpaper = productpaperDO.getId();
	    	List<String> fenleiList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    	for(String fenlei:fenleiList){
	    		List<QuestionDO> list = paperService.getQuestionDOList(productpaper,fenlei);
	    		for(QuestionDO questionDO :list){
	    			questionDO.setTiaozheng(questionDO.getChoiceProductDOList().get(0).getTadf());
	    		}
	    		map.put(fenlei,list);
	    	}
	    }
	    return map;
	}
}
