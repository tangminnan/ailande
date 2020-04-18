package com.gene.information.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.gene.common.annotation.Log;
import com.gene.common.utils.JavaToPdfHtmlFreeMarker;
import com.gene.common.utils.PageUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.ReportTalkDO;
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
	List<Object> edit2(@PathVariable("id") String id){
		List<Object> list2 = new ArrayList<>();
		List<CustomerPaperDO> list = customerPaperService.getUserList(id);
		if(list.size()>0){
			Map<String, Object> params = new HashMap<String, Object>();
//			System.out.println(JSON.toJSONString(list));
			for (CustomerPaperDO customerPaperDO2 : list) {
				params.put(customerPaperDO2.getContent(), customerPaperDO2.getTiankonganswer());
			}
			params.put("userId", id);
			//params.put("昵称", list.get(0).getUsername());
		list2.add(params);
		}
		return list2;		
	}
	
	
	/**
	 * 答题详情
	 */
	@GetMapping("/datixiangqing/{id}")
	String datixiangqing(@PathVariable("id") String id,Model model){
		model.addAttribute("id", id);
	    return "information/customerPaper/datixiangqing";
	}
	
	@ResponseBody
	@GetMapping("/datixiangqinglist")
	@RequiresPermissions("information:customerPaper:customerPaper")
	public PageUtils datixiangqinglist(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<AnswerDO> list = customerPaperService.listAnswerDO(params);
		int total = customerPaperService.countAnswerDO(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	
	/**
	 * 答题详情
	 */
	
	@GetMapping("/details/{id}")
	String details(@PathVariable("id") String id,Model model){
	//	CustomerPaperDO customerPaper = customerPaperService.get(Integer.valueOf(id));
		List<CustomerPaperDO> questionDetails = customerPaperService.queryUserQuestionDetails(id);
		/*for (CustomerPaperDO customerPaperDO : questionDetails) {
			String choiceId = customerPaperDO.getChoiceId();
			String[] split = choiceId.split(",");
			for (String string : split) {
				CustomerPaperDO choiceContent = customerPaperService.getChoiceContent(Integer.valueOf(string));
				customerPaperDO.setChoiceContent(choiceContent.getChoiceContent());
			}
		}*/
	//	model.addAttribute("customerPaper", customerPaper);
		model.addAttribute("questionDetails", questionDetails);
		model.addAttribute("id", id);
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
	
	
	
	/**********************************导出PDF******************************************/
	@GetMapping("/exportPDF")
	public void exportPDF(HttpServletResponse response,Integer productpaper,Integer product,String name){
		Map<String,Object> resultMap = createMap(productpaper,product,name);
		JavaToPdfHtmlFreeMarker javaToPdfHtmlFreeMarker=new JavaToPdfHtmlFreeMarker(resultMap);
		javaToPdfHtmlFreeMarker.exportPDF(response);
	}
	String talkName="";
	int yinshi600=0;
	public Map<String,Object> createMap(Integer productpaper,Integer product,String name){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Double> mapD  = new HashMap<String,Double>();
	    Map<String,Double> mapT = new HashMap<String,Double>();
	    List<String> fenleiParamsList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    for(String fenlei :fenleiParamsList){
    		Double rt=customerPaperService.getChoicedScores(productpaper,fenlei);
    		if(rt==null) rt=0.0;
    		mapD.put(fenlei,rt);
    		mapT.put(fenlei,customerPaperService.getAllChoicedScores(productpaper,product,fenlei));
    	}
    	int shenti80=(int)(mapD.get("SHENTI_ZHUANG")/mapT.get("SHENTI_ZHUANG")*100);
    	resultMap.put("shenti80", shenti80);
    	double defen=0,zongfen=0;
    	for(String fenlei :fenleiParamsList){
    		if(!"SHENTI_ZHUANG".equals(fenlei)){
    			defen+=mapD.get(fenlei);
    			zongfen+=mapT.get(fenlei);
    		}
    	}
    	int yinshi60 =(int)(defen/zongfen*100);
    	resultMap.put("yinshi60",yinshi60);
    	if("肠胃调理".equals(name)){
    		if(shenti80>=80)talkName="比较好";
    		else if(shenti80<80 && shenti80>=60) talkName="一般般";
    		else if(shenti80<60) talkName="不太好";
    		List<ReportTalkDO> reportTalkDOs = customerPaperService.listReportTalk(new HashMap<String,Object>());
    		List<ReportTalkDO> l=  reportTalkDOs.stream().filter(a -> "CHANGWEI".equals(a.getTalkType()))
							  .filter(c -> talkName.equals(c.getTalkName()))
							  .filter(b -> b.getScoreLittle()<=yinshi60 && b.getScoreBig() >= yinshi60)
							  .collect(Collectors.toList());
    		if(l.size()>0){
    			resultMap.put("huashu",l.get(0).getTalkContent());
    		}
    	}
    	if("科学瘦身".equals(name)){
    		CustomerPaperDO udo1 = customerPaperService.getCustomerPaperDOByUser(productpaper);
 		    Integer high = Integer.parseInt(udo1.getHigh());
    		float weight=  Float.parseFloat( udo1.getWeight()+"");  
    		DecimalFormat df = new DecimalFormat("0.0");
    		String bmi=  df.format(weight/high/high*10000);
    		resultMap.put("high",high);
    		resultMap.put("weight",weight);
    		resultMap.put("bmi",bmi);
    		List<String> str  = customerPaperService.getChoosedContentBySomeString(productpaper,"赘肉");
    		resultMap.put("zhuirou",str.size()>0?str.get(0):"没有赘肉");
    		double bbmmii =Double.parseDouble(bmi);
    		String zzhhuuiirroouu  = str.size()>0?str.get(0):"没有赘肉";
    	    if(bbmmii>=24 && zzhhuuiirroouu.equals("赘肉较多"))talkName="脂肪型肥胖";
    		else if(bbmmii>=24 && zzhhuuiirroouu.equals("有点赘肉"))talkName="混合型肥胖";
    		else if(bbmmii>=24 && zzhhuuiirroouu.equals("没有赘肉"))talkName="肌肉型";
    		else if(bbmmii>=18.5 && bbmmii<23.9 && (zzhhuuiirroouu.equals("有点赘肉") || zzhhuuiirroouu.equals("赘肉较多")))talkName="体重标准体脂偏高";
    		else if(bbmmii>=18.5 && bbmmii<23.9 && zzhhuuiirroouu.equals("没有赘肉"))talkName="标准体型";
    		else if(bbmmii<18.5)talkName="偏瘦";
    		List<ReportTalkDO> reportTalkDOs = customerPaperService.listReportTalk(new HashMap<String,Object>());
     		List<ReportTalkDO> l=  reportTalkDOs.stream().filter(a -> "TIXING".equals(a.getTalkType()))
     							  .filter(c -> talkName.equals(c.getTalkName()))
     							  .filter(b -> b.getScoreLittle()<=yinshi600 && b.getScoreBig() >= yinshi600)
     							  .collect(Collectors.toList());
     		if(l.size()>0){
     			  resultMap.put("huashu",l.get(0).getTalkContent());
     		}					  
    	}
    	getMyReportData(productpaper,resultMap);
    	System.out.println(resultMap);
		return resultMap;					  
	}
	/**
	 * 获取详细的数据
	 */
	public void getMyReportData(Integer productpaper,Map<String,Object> resultMap){
		
		DecimalFormat df = new DecimalFormat("0.0");
		CustomerPaperDO udo1 = customerPaperService.getCustomerPaperDOByUser(productpaper);
		float high = Float.parseFloat(udo1.getHigh());
		float weight=  Float.parseFloat( udo1.getWeight()+"");  
		float bmi=  Float.parseFloat(df.format(weight/high/high*10000));
		
	   
	    List<String> fenleiParamsList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    for(String fenlei:fenleiParamsList){
	    	List<QuestionDO> list = customerPaperService.getQuestionDOList(productpaper,fenlei);
	    	for(QuestionDO questionDO :list){
	    		if("YUN_DONG".equals(questionDO.getIfStop()) && bmi<29){
	    			questionDO.setTiaozheng(questionDO.getChoiceProductDOList().get(0).getTadfs());
	    			questionDO.getChoiceProductDOList().get(0).setTads(questionDO.getChoiceProductDOList().get(0).getTadss());
	    			questionDO.getChoiceProductDOList().get(0).setTadreason(questionDO.getChoiceProductDOList().get(0).getTadreasons());
	    			questionDO.getChoiceProductDOList().get(0).setTadjianyi(questionDO.getChoiceProductDOList().get(0).getTadjianyis());
	    		}else
	    			questionDO.setTiaozheng(questionDO.getChoiceProductDOList().get(0).getTadf());
	    	}
	    	resultMap.put(fenlei,list);
	    } 
	}
	
}
