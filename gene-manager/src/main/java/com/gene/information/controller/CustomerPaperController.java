package com.gene.information.controller;

import java.io.File;
import java.io.IOException;
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
    	String yinshizhuangkuang=  "";
    	if(yinshi60>=80) yinshizhuangkuang="较好";
    	else if(yinshi60<80 && yinshi60>=60)yinshizhuangkuang="一般";
    	else if(yinshi60<60) yinshizhuangkuang="较低";
    	resultMap.put("yinshi60",yinshizhuangkuang);
    	CustomerPaperDO udo1 = customerPaperService.getCustomerPaperDOByUser(productpaper);
		    Integer high = Integer.parseInt(udo1.getHigh());
		float weight=  Float.parseFloat( udo1.getWeight()+"");  
		DecimalFormat df = new DecimalFormat("0.0");
		String bmi=  df.format(weight/high/high*10000);
		resultMap.put("high",high);
		resultMap.put("weight",weight);
		resultMap.put("phone", udo1.getPhone());
		resultMap.put("username", udo1.getUsername());
		resultMap.put("bmi",bmi);
		resultMap.put("productName",name);
    	if("肠胃调理".equals(name)){
    		if(shenti80>=80)talkName="比较好";
    		else if(shenti80<80 && shenti80>=60) talkName="一般般";
    		else if(shenti80<60) talkName="不太好";
    		resultMap.put("talkName", talkName);
    		List<ReportTalkDO> reportTalkDOs = customerPaperService.listReportTalk(new HashMap<String,Object>());
    		List<ReportTalkDO> l=  reportTalkDOs.stream().filter(a -> "CHANGWEI".equals(a.getTalkType()))
							  .filter(c -> talkName.equals(c.getTalkName()))
							  .filter(b -> b.getScoreLittle()<=yinshi60 && b.getScoreBig() >= yinshi60)
							  .collect(Collectors.toList());
    		if(l.size()>0){
    			resultMap.put("huashu",l.get(0).getTalkContent());
    		}
    	}
    	if("科学瘦身".equals(name)){//饮食   较好  一般  较低
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
    	    resultMap.put("talkName", talkName);
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
		List<String> paibianpinlvxingzhuang = new ArrayList<String>();
		List<String> paibianpinlvshucaishuiguoculiang = new ArrayList<String>();
		List<String> futongfuzhangfansuanwixhangjibbing = new ArrayList<String>();
		Map<String,String> tuijianarryMap = new HashMap<String,String>();//保存推荐的产品和原因
    	Map<String,String> haikexuanzeMap = new HashMap<String,String>();//还可选择的产品
		float bmi =Float.parseFloat((String) resultMap.get("bmi"));
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
	    		//产品推荐逻辑
	    		//通常的排便频率？   大部分时候便便的形态？  平均每天吃几份蔬菜？ 平均每天吃几份水果？ 平均每天吃几份粗粮？ 腹胀   疾病史
	    		String content = questionDO.getContent();
	    		String text = questionDO.getChoiceProductDOList().get(0).getChoicecontent();
	    		if(content.indexOf("通常的排便频率？")>=0 || content.indexOf("大部分时候便便的形态？")>=0){
					paibianpinlvxingzhuang.add(content+"："+text);
				}
				if(content.indexOf("通常的排便频率？")>=0 || content.indexOf("平均每天吃几份蔬菜？") >=0|| content.indexOf("平均每天吃几份水果？")>=0 || content.indexOf("平均每天吃几份粗粮？")>=0){
					 paibianpinlvshucaishuiguoculiang.add(content+"："+text);
				}
				if(content.indexOf("腹胀")>=0 || content.indexOf("疾病史")>=0){
					futongfuzhangfansuanwixhangjibbing.add(content+"："+text);
				} 
	    	}
	    	resultMap.put(fenlei,list);
	    }
	    	String productName = (String) resultMap.get("productName");
	    	boolean  flag=false;
	    	String yuanyin="";//推荐原因
	    	if("肠胃调理".equals(productName)){               
	    		String[] jianyibuchong={"通常的排便频率？：大于3次/天","通常的排便频率？: 有便秘倾向","大部分时候便便的形态？：不成型的软便","大部分时候便便的形态？：硬块","大部分时候便便的形态？：软硬掺杂","大部分时候便便的形态？：水状"};
	    		for(int i=0;i<paibianpinlvxingzhuang.size();i++){
	    			 String l = paibianpinlvxingzhuang.get(i);
	    			 for(int j=0;j<jianyibuchong.length;j++){
	    				 if(l.equals(jianyibuchong[j])){
	    					 flag=true;yuanyin=l;break;
	    				 }
	    			 }
	    			 if(flag==true) break;
	    		 }
	    		 if(flag==true){
	    			 flag=false;
	    			 tuijianarryMap.put(yuanyin,"速七活力益生菌胶囊");
	    		 } 
	    		 flag=false;
	    		 for(int i=0;i< paibianpinlvxingzhuang.size();i++){
	    			 String l = paibianpinlvxingzhuang.get(i);
	    			 if("通常的排便频率？：1-3次/天".equals(l))
	    				 flag=true;
	    		 }
	    		 if(flag){
	    			 flag=false;
	    		 	for(int i=0;i< paibianpinlvxingzhuang.size();i++){
	    			 	String l = paibianpinlvxingzhuang.get(i);
	    			 	if("大部分时候便便的形态？：香蕉型".equals(l)){
	    				 	tuijianarryMap.put("通常的排便频率？：1-3次/天,大部分时候便便的形态？：香蕉型","每日活力益生菌");
	    			 	}else if("大部分时候便便的形态？：成型的软便".equals(l)){
	    			 		tuijianarryMap.put("通常的排便频率？：1-3次/天,大部分时候便便的形态？：成型的软便","每日活力益生菌"); 
	    			 	}
	    		 	}
	    		 }
	    		 //判断低聚果糖益生元粉
	    		 String shengxiade="";
	    		 jianyibuchong=new String[]{"通常的排便频率？: 有便秘倾向","通常的排便频率？：大于3次/天","平均每天吃几份蔬菜？ （一份约为1个拳头体积的煮熟的蔬菜，不包括藕、土豆、玉米、山药、芋头，它们是主食，不是蔬菜）：小于2份","平均每天吃几份蔬菜？ （一份约为1个拳头体积的煮熟的蔬菜，不包括藕、土豆、玉米、山药、芋头，它们是主食，不是蔬菜）：2-3份","平均每天吃几份水果？ （1份水果的可食部约为半个拳头的大小，或半个中号苹果大小）：小于2份","平均每天吃几份粗粮？ （一份约为1个拳头体积的煮熟的粗粮，包括全谷物、杂豆类、薯类，例如藕、土豆、玉米、山药、芋头都是主食）：几乎不吃","平均每天吃几份粗粮？ （一份约为1个拳头体积的煮熟的粗粮，包括全谷物、杂豆类、薯类，例如藕、土豆、玉米、山药、芋头都是主食）：小于1份"};
	    		 for(int i=0;i< paibianpinlvshucaishuiguoculiang.size();i++){
	    			 String l = paibianpinlvshucaishuiguoculiang.get(i);
	    			 for(int j=0;j<jianyibuchong.length;j++){
	    				 if(l.equals(jianyibuchong[j])){
	    					 flag=true;
	    					 yuanyin=l;
	    				 	 break;
	    				 }
	    			 }
	    			 if(flag==true)
	    				 break;
	    		 }
	    		  if(flag==true){
	    			 flag=false;
	    			 tuijianarryMap.put(yuanyin,"低聚果糖益生元粉");
	    			 shengxiade="低聚果糖益生元粉";
	    		 }
	    		 
	    		//您还可以选择   低聚果糖益生元粉
	    		if("".equals(shengxiade)&& paibianpinlvshucaishuiguoculiang.size()>0){
	    			haikexuanzeMap.put("除常备以外的答案","低聚果糖益生元粉");
	    		}
	    		 
	    		 jianyibuchong=new String[]{"通常腹痛、腹胀、胃胀、胃痛、反酸频率？：经常有","胃肠道疾病史：有","通常腹痛、腹胀、胃胀、胃痛、反酸频率？：偶尔有"};
	    		 for(int i=0;i< futongfuzhangfansuanwixhangjibbing.size();i++){
	    			 String l = futongfuzhangfansuanwixhangjibbing.get(i);
	    			 for(int j=0;j<jianyibuchong.length;j++){
	    				 if(l.equals(jianyibuchong[j])){
	    					 flag=true;
	    					 yuanyin=l;
	    				 	 break;
	    				 }
	    			 }
	    		 }
	    		 if(flag==true){
	    			 flag=false;
	    			 tuijianarryMap.put(yuanyin,"Permatrol®肠胃安益胶囊    Polyzyme Forte®复合消化酶胶囊");
	    		 }
	    	}else{                       																       
	    		String[] jianyibuchong={"通常的排便频率？：有便秘倾向","通常的排便频率？：大于3次/天"};
	    		for(int i=0;i<paibianpinlvxingzhuang.size();i++){
	    			 String l = paibianpinlvxingzhuang.get(i);
	    			 for(int j=0;j<jianyibuchong.length;j++){
	    				 if(l.equals(jianyibuchong[j])){
	    					 flag=true;
	    					 yuanyin=l;
	    					 break;
	    				 }
	    			 }
	    			 if(flag==true)
	    				 break;
	    		 }
	    		 if(flag==true){
	    			 flag=false;
	    			 tuijianarryMap.put(yuanyin,"速七活力益生菌胶囊");
	    		 } 
	    		 
	    		//判断每日活力益生菌
	    		   flag=false;
	    		   jianyibuchong=new String[]{"通常的排便频率？：1-3次/天"};
	    		   for(int i=0;i< paibianpinlvxingzhuang.size();i++){
	    		   		String l = paibianpinlvxingzhuang.get(i);
	    			 	for(int j=0;j<jianyibuchong.length;j++){
	    				 	if(l.equals(jianyibuchong[j])){
	    						 flag=true;
	    						 yuanyin=l;
	    						 break;
	    					 }
	    			 	}
	    			 	if(flag==true)
	    			 		break;
	    		 	}
	    		 if(flag==true){flag=false;
	    			 tuijianarryMap.put(yuanyin,"每日活力益生菌");
	    		 } 
	    		  
	    		 //膳食纤维低聚果糖
	    		  String shengxiades="";
	    		  jianyibuchong=new String[]{"通常的排便频率？：有便秘倾向","通常的排便频率？：大于3次/天","平均每天吃几份蔬菜？ （一份约为1个拳头体积的煮熟的蔬菜，不包括藕、土豆、玉米、山药、芋头，它们是主食，不是蔬菜）：小于2份","平均每天吃几份蔬菜？ （一份约为1个拳头体积的煮熟的蔬菜，不包括藕、土豆、玉米、山药、芋头，它们是主食，不是蔬菜）：2-3份","平均每天吃几份水果？ （1份水果的可食部约为半个拳头的大小，或半个中号苹果大小）：小于2份","体型现状：脂肪型肥胖","体型现状：体重正常体脂偏高","平均每天吃几份粗粮？ （一份约为1个拳头体积的煮熟的粗粮，包括全谷物、杂豆类、薯类，例如藕、土豆、玉米、山药、芋头都是主食）：几乎不吃","平均每天吃几份粗粮？ （一份约为1个拳头体积的煮熟的粗粮，包括全谷物、杂豆类、薯类，例如藕、土豆、玉米、山药、芋头都是主食）：小于1份"};
	    		  for(int i=0;i< paibianpinlvshucaishuiguoculiang.size();i++){
	    			 String l = paibianpinlvshucaishuiguoculiang.get(i);
	    			 for(int j=0;j<jianyibuchong.length;j++){
	    				 if(l.equals(jianyibuchong[j])){
	    					 flag=true;
	    					 yuanyin=l;
	    				 	 break;
	    				 }
	    			 }
	    			 if(flag==true)
	    				 break;
	    		 }
	    		 if(flag==true){
	    			 flag=false;
	    			 tuijianarryMap.put(yuanyin,"膳食纤维低聚果糖");
	    			 shengxiades="膳食纤维低聚果糖";
	    		 }
	    		 


	    		 //判断还可选择   膳食纤维低聚果糖
	    		 if("".equals(shengxiades) && paibianpinlvshucaishuiguoculiang.size()>0){
	    			 haikexuanzeMap.put("除常备以外的答案","膳食纤维低聚果糖");
	    		}
	    		 
	    		 tuijianarryMap.put("对所有用户推荐常备","藤黄果提取物");
	    	}
	    	
	    
	    resultMap.put("tuijianarryMap",tuijianarryMap);
    	resultMap.put("haikexuanzeMap",haikexuanzeMap);
	}	
}
