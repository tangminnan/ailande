package com.gene.information.controller;

import com.gene.common.annotation.Log;
import com.gene.common.utils.R;
import com.gene.common.utils.StringUtils;
import com.gene.information.domain.*;
import com.gene.information.service.PaperService;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/paper")
public class PaperController {
	@Autowired
	private PaperService paperService;

	@Log("跳转首页")
	@GetMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response,Model model){
		String str= new SimpleDateFormat("yyyyMMddhhMmss").format(new Date());//请求时间戳
		request.getSession().setAttribute("timeString", str);
		
		System.out.println("回调执行");
		System.out.println("回调执行");
		System.out.println("回调执行");
		System.out.println("回调执行");
		System.out.println("回调执行");
		String code = request.getParameter("code");
    	String openid = "";//暂定写死，之前是空字符串
    	if(StringUtils.isNotBlank(code)){
    		try {
				openid = WechatOAConfig.getAccessToken(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	model.addAttribute("openid", openid);
    	
		return "information/index";
	}
	
	@Log("获取所有的产品")
	@GetMapping("/getAllProduct")
	@ResponseBody
	public Map<String,Object> getAllProduct(String openid){
		System.out.println("openid-------------------------"+openid);
		System.out.println("openid-------------------------"+openid);
		System.out.println("openid-------------------------"+openid);
		System.out.println("openid-------------------------"+openid);
		System.out.println("openid-------------------------"+openid);
		System.out.println("openid-------------------------"+openid); 
		return paperService.getAllProduct(openid);
	}
	
	
	
	@Log("保存选择的产品")
	@GetMapping("/saveChoosedProduct")
	public String saveChoosedProduct( Integer[] products,HttpServletRequest request,String openid,Model model){
	//	openid="ocqxT5xl5XAidygc01PGGQngKV4Q";
		request.getSession().setAttribute("products", products);
		paperService.saveChoosedProduct(products,request,openid);
		model.addAttribute("openid", openid);
		return "information/yinshixiguan";
	}
	
	/*@Log("输入姓名")
	@GetMapping("/enterName")*/
	/*public String enterName(Model model,HttpServletRequest request){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		int count = paperService.getgetQuestionDOSize(products);
		model.addAttribute("count", count);
		model.addAttribute("index", 0);
		return "information/jibenxinxi";
	}*/

	@GetMapping("/getHistoryRecord")
	@ResponseBody
	public List<ProductpaperDO> getHistoryRecord(String openid,Integer product,@RequestParam(value="guanjianzi",required=false) String guanjianzi){
		/**
         * 获取检查的历史记录时间
         */
		 List<ProductpaperDO> list  = paperService.getHistoryRecord(openid,product);
		 if(!StringUtils.isBlank(guanjianzi))
			 list=list.stream().filter(a->guanjianzi.equals(a.getUsername())).collect(Collectors.toList());
		 return list;
	}
	
	@GetMapping("/getHistoryRecordByGuanjianzi")
	@ResponseBody
	public R getHistoryRecordByGuanjianzi(String openid,Integer product,String guanjianzi){
		String dateTime="2020-01-10 11:12:00";
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int count = paperService.getOldCouunt(openid,product,guanjianzi,date);
		if(count>0){
			paperService.updateOldOpenId(openid,product,guanjianzi,date);
			paperService.updateOldOIpenIdCustomer(openid,product,guanjianzi,date);
		}
		return R.ok(guanjianzi);
		
	}
		
    
	@Log("保存")
	@GetMapping("/saveYourName")
	public String saveYourName(
							   Integer index,Integer count,
							   String sex, Integer age,
							   String phone, String username,
							   String openid,
							  Model model){
		model.addAttribute("name",username);
		model.addAttribute("phone",phone);
		model.addAttribute("sex",sex);
		model.addAttribute("age",age);
		model.addAttribute("index",index);
		model.addAttribute("count", count);
		model.addAttribute("openid", openid);
		return "information/jibenxinxi2";
	}
	
	@Log("开始答题")
	@GetMapping("/beginAnswer")
	public String beginAnswer(Integer flag,
							 @RequestParam(value="count",required=false) Integer count,
							 @RequestParam(value="index",required=false) Integer index,
							 @RequestParam(value="username",required=false) String username,
							 @RequestParam(value="phone",required=false) String phone,
							 @RequestParam(value="sex",required=false) String sex,
							 @RequestParam(value="age",required=false) Integer age,
							 @RequestParam(value="high",required=false) Integer high,
							 @RequestParam(value="weight",required=false) Float weight,
							 String openid,
							 Model model,
							 HttpServletRequest request){
		List<ProductpaperDO> lp = paperService.getLatestProductpaperDO(openid);
		List<Integer> l = lp.stream().map(ProductpaperDO::getProduct).collect(Collectors.toList());
		Integer[] products=new Integer[l.size()];
		l.toArray(products);
		System.out.println("选择的产品============="+Arrays.toString(products));
		System.out.println("选择的产品============="+Arrays.toString(products));
		System.out.println("选择的产品============="+Arrays.toString(products));
//		Integer[] products = (Integer[])request.getSession().getAttribute("products");
		model.addAttribute("openid",openid);
		if(count==null){
			 count = paperService.getgetQuestionDOSize(products);
		}
		if(index==null) index=0;
		model.addAttribute("index", index);
		model.addAttribute("count", count);
		
		List<QuestionDO> list=new ArrayList<QuestionDO>();
		if(flag==0){//基本信息
			list=paperService.getQuestionDOType(products,"JIBEN_XINXI");
			model.addAttribute("list",list);
			model.addAttribute("LEI","JIBEN_XINXI");
			return "information/jibenxinxi4";
		}
		if(flag==1){//基本信息 （身高  体重）
			list=paperService.getQuestionDOType(products,"JIBEN_XINXI");
			model.addAttribute("list",list);
			model.addAttribute("LEI","JIBEN_XINXI");
			model.addAttribute("username",username);
			model.addAttribute("phone",phone);
			model.addAttribute("sex",sex);
			model.addAttribute("age",age);
			return "information/jibenxinxi44";
		}
		else if(flag==2){//身体状况  膳食习惯 生活方式 睡眠与压力 运动习惯
			List<String> fenleiList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");
			for(String fenlei :fenleiList){
				List<QuestionDO> ll = paperService.getQuestionDOType(products,fenlei);
				ll=ll.stream().sorted(Comparator.comparing(QuestionDO::getSort)).collect(Collectors.toList());
				list.addAll(ll);
			}
			model.addAttribute("list",list);
		}
		
		return "information/jibenxinxi6";
	}
	
	/**
	 * 进入报告页面
	
	 */
	
	@GetMapping("/getReportPage")
	public String getReportPage(HttpServletRequest request,HttpServletResponse response,Model model){
		
		String code = request.getParameter("code");
    	String openid = "";//暂定写死，之前是空字符串
    	if(StringUtils.isNotBlank(code)){
    		try {
				openid = WechatOAConfig.getAccessToken(code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	
        	model.addAttribute("openid", openid);
        
        															  
		return "information/baogao-1";
	}
	
	@Log("保存问卷答题")
	@PostMapping("/saveWenJuan")
	@ResponseBody
	public R saveWenJuan(@RequestParam(value="objs",required=false) String objs,HttpServletRequest request,String openid,
						 @RequestParam(value="username",required=false) String username,
						 @RequestParam(value="phone",required=false) String phone,
						 @RequestParam(value="sex",required=false) String sex,
						 @RequestParam(value="age",required=false) Integer age,
						 @RequestParam(value="high",required=false) Integer high,
						 @RequestParam(value="weight",required=false) Float weight){
		if(objs!=null)
			return paperService.saveWenJuan(objs,request,openid);
		else{
			List<ProductpaperDO> plist = paperService.getChoosedProductByOpenId(openid);
//			Integer[] products = (Integer[]) request.getSession().getAttribute("products");
//			System.out.println("products===="+products.length);
			for(ProductpaperDO pro : plist){
				Integer i = pro.getProduct();
				CustomerPaperDO customerPaperDO = new CustomerPaperDO();
				customerPaperDO.setCreateTime(new Date());
				customerPaperDO.setAge(age);
				customerPaperDO.setSex(sex);
				customerPaperDO.setPhone(phone);
				customerPaperDO.setWeight(weight);
				customerPaperDO.setHigh(high);
				customerPaperDO.setUsername(username);
				customerPaperDO.setOpenid(openid);
				customerPaperDO.setProductId(i);
				paperService.saveCustomerPaperDO(customerPaperDO);
//				ProductpaperDO productpaperDO = paperService.getLatestProductPaper(openid, i);
				int user = customerPaperDO.getId();
				Optional.ofNullable(pro).ifPresent(p ->{
					pro.setUser(user);
					paperService.updateProductPaper(pro);
				});
				
			}
			
			return R.ok();
		}
	}
	
	@Log("查看报告")
	@GetMapping("/lookCheckLog")
	public String lookCheckLog(Integer product,String name,String openid, Model model,HttpServletRequest request){
		System.out.println("送到页面的openid====================="+openid);
		System.out.println("送到页面的openid====================="+openid);
		model.addAttribute("product", product);
		model.addAttribute("name",name);
		model.addAttribute("openid",openid);
		if("肠胃调理".equals(name))
			return"information/baogao-2";
		if("科学瘦身".equals(name))
			return "information/baogao-5";
		return "";
		
//		return "information/lishibaogao";
		
	}
	
	@GetMapping("/startLOOK")
	public String startLOOK(Integer product,String name,String openid, Model model,HttpServletRequest request,Date date){
		System.out.println("送到页面的openid====================="+openid);
		System.out.println("送到页面的openid====================="+openid);
		
		CustomerPaperDO udo1 = paperService.getCustomerPaperDO(openid,product,date);
		   System.out.println(	"11111111111111======="+udo1.getHigh());
		   System.out.println(	"11111111111111======="+udo1.getWeight());
		   System.out.println(	"11111111111111======="+udo1.getUsername());
		
		model.addAttribute("userName",udo1.getUsername());
		model.addAttribute("product", product);
		model.addAttribute("name",name);
		model.addAttribute("openid",openid);
		model.addAttribute("date",date);
		if("肠胃调理".equals(name))
			return"information/baogao-2";
		if("科学瘦身".equals(name))
			return "information/baogao-5";
		return "";
	}
	
	String talkName="";
	@Log("阅读检测报告--肠胃调理")
	@GetMapping("/readMyReport")
	public String readMyReport(Model model,Integer product,String name,HttpServletRequest request,String openid){
		String timeString= (String)request.getSession().getAttribute("timeString");
//		List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
//		ProductpaperDO productpaperDO=productpaperDOList.get(0);
	    Map<String,Double> mapD  = new HashMap<String,Double>();
	    Map<String,Double> mapT = new HashMap<String,Double>();
	    DecimalFormat df = new DecimalFormat("0.0");
	   /**
	    * 根据openid  产品去拿最新的检测结果
	    */
	    
	    List<ProductpaperDO> productpaperDOList = paperService.getNewProductpaperDO(openid,product);
	    ProductpaperDO productpaperDO=productpaperDOList.get(0);
	    Date date = productpaperDO.getAnswerTime();
	    System.out.println("======================================");
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	    System.out.println("=======================================");
	    
	    if(productpaperDO!=null){//统计分值
	    	Integer productpaper = productpaperDO.getId();
	    	List<String> fenleiList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    	for(String fenlei :fenleiList){
	    		Double rt=paperService.getChoicedScores(productpaper,fenlei);
	    		if(rt==null) rt=0.0;
	    		mapD.put(fenlei,rt);
	    		mapT.put(fenlei,paperService.getAllChoicedScores(productpaper,product,fenlei));
	    	}	
	    	/**
	    	 * 计算身体现状80分得分
	    	 */
	    	int shenti80=(int)(mapD.get("SHENTI_ZHUANG")/mapT.get("SHENTI_ZHUANG")*100);
	    	model.addAttribute("shenti80", shenti80);
	    	/**
	    	 * 计算饮食状况 60分得分
	    	 */
	    	double defen=0,zongfen=0;
	    	for(String fenlei :fenleiList){
	    		
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			defen+=mapD.get(fenlei);
	    			zongfen+=mapT.get(fenlei);
	    		}
	    	}
	    	int yinshi60 =(int)(defen/zongfen*100);
    		model.addAttribute("yinshi60",yinshi60);
    		/**
    		 * 统计话术
    		 */
    		
    		if(shenti80>=80) {
    			talkName="比较好";
    		}
    		if(shenti80<80 && shenti80>=60) {
    			talkName="一般般";
    		}
    		if(shenti80<60) {
    			talkName="不太好";
    		}
    		List<ReportTalkDO> reportTalkDOs = paperService.listReportTalk(new HashMap<String,Object>());
    		List<ReportTalkDO> l=  reportTalkDOs.stream().filter(a -> "CHANGWEI".equals(a.getTalkType()))
    							  .filter(c -> talkName.equals(c.getTalkName()))
    							  .filter(b -> b.getScoreLittle()<=yinshi60 && b.getScoreBig() >= yinshi60)
    							  .collect(Collectors.toList());
    		if(l.size()>0){
    			  model.addAttribute("huashu",l.get(0).getTalkContent());
    		}					  
    		
    		
	    	/**
	    	 * 计算各个分类得分占比
	    	 */
	    	double zongfenzhanbi=0;
	    	Map<String,Double> mapa = new HashMap<String,Double>();
	    	for(String fenlei :fenleiList){
	    		mapa.put(fenlei+"zhanbi",0.0);
	    	}
	    	for(String fenlei :fenleiList){
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			mapa.put(fenlei+"zhanbi",mapa.get(fenlei+"zhanbi")+mapT.get(fenlei)-mapD.get(fenlei));
	    			zongfenzhanbi+=(mapT.get(fenlei)-mapD.get(fenlei));
	    		}
	    	}
	    	
	    	for(String fenlei :fenleiList){
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			model.addAttribute(fenlei+"zhanbi",df.format(mapa.get(fenlei+"zhanbi")/zongfenzhanbi*100));
	    		}
	    	}
	    }
	    model.addAttribute("openid", openid);
		model.addAttribute("product", product);
		model.addAttribute("date",date);
	    return "information/baogao-3";
	}
	int yinshi600=0;
	@Log("阅读检测报告--科学瘦身")
	@GetMapping("/kexueshoushens")
	public String kexueshoushen(Model model,Integer product,String name,HttpServletRequest request,String openid) {
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
	
	/*	List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
		ProductpaperDO productpaperDO = productpaperDOList.get(0);*/
		 List<ProductpaperDO> productpaperDOList = paperService.getNewProductpaperDO(openid,product);
		 ProductpaperDO productpaperDO=productpaperDOList.get(0);
		 Date date = productpaperDO.getAnswerTime();
		  System.out.println("======================================");
		    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		    System.out.println("=======================================");
		Map<String,Double> mapD  = new HashMap<String,Double>();
	    Map<String,Double> mapT = new HashMap<String,Double>();
	    DecimalFormat df = new DecimalFormat("0.0");
	    if(productpaperDO!=null){//统计分值
	    	Integer productpaper = productpaperDO.getId();
	    	List<String> fenleiList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    	for(String fenlei :fenleiList){
	    		Double rt=paperService.getChoicedScores(productpaper,fenlei);
	    		if(rt==null) rt=0.0;
	    		mapD.put(fenlei,rt);
	    		mapT.put(fenlei,paperService.getAllChoicedScores(productpaper,product,fenlei));
	    	}	
	    	/**
	    	 * 计算身体现状80分得分
	    	 */
	        int shenti80=(int)(mapD.get("SHENTI_ZHUANG")/mapT.get("SHENTI_ZHUANG")*100);
	    	model.addAttribute("shenti80", shenti80);
	    	/**
	    	 * 计算饮食状况 60分得分
	    	 */
	    	double defen=0,zongfen=0;
	    	for(String fenlei :fenleiList){
	    		
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			defen+=mapD.get(fenlei);
	    			zongfen+=mapT.get(fenlei);
	    		}
	    	}
	    	int yinshi60 =(int)(defen/zongfen*100);yinshi600=yinshi60;
    		model.addAttribute("yinshi60",yinshi60);
    		/**
	    	 * 计算各个分类得分占比
	    	 */
	    	Double zongfenzhanbi=0.0;
	    	Map<String,Double> mapa = new HashMap<String,Double>();
	    	for(String fenlei :fenleiList){
	    		mapa.put(fenlei+"zhanbi",0.0);
	    	}
	    	for(String fenlei :fenleiList){
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			mapa.put(fenlei+"zhanbi",mapa.get(fenlei+"zhanbi")+mapT.get(fenlei)-mapD.get(fenlei));
	    			zongfenzhanbi+=(mapT.get(fenlei)-mapD.get(fenlei));
	    		}
	    	}
	    	
	    	for(String fenlei :fenleiList){
	    		if(!"SHENTI_ZHUANG".equals(fenlei)){
	    			model.addAttribute(fenlei+"zhanbi",df.format(mapa.get(fenlei+"zhanbi")/zongfenzhanbi*100));
	    		}
	    	}
	    }
	    model.addAttribute("date",date);
		model.addAttribute("product", product);
	    
		
		/**
		 * 计算bmi
		 */
		
		CustomerPaperDO udo1 = paperService.getCustomerPaperDO(openid,product,date);
		   
		Integer high = udo1.getHigh();
		float weight=  Float.parseFloat( udo1.getWeight()+"");  
		String bmi=  df.format(weight/high/high*10000);
		model.addAttribute("high",high);
		model.addAttribute("weight",weight);
		model.addAttribute("bmi",bmi);
		/**
		 * 是否有赘肉
		 */
		
		List<String> str  = paperService.getChoosedContent(openid,product,"赘肉",date);
			
		model.addAttribute("openid", openid);
		model.addAttribute("zhuirou",str.size()>0?str.get(0):"没有赘肉");
		/**
		 * 统计话术
		 */
		
		 double bbmmii =Double.parseDouble(bmi);
		 String zzhhuuiirroouu  = str.size()>0?str.get(0):"没有赘肉";
		 if(bbmmii>=24 && zzhhuuiirroouu.equals("赘肉较多")){
			talkName="脂肪型肥胖";
		 }
		 else if(bbmmii>=24 && zzhhuuiirroouu.equals("有点赘肉")){
			 talkName="混合型肥胖";
		 }
		 else if(bbmmii>=24 && zzhhuuiirroouu.equals("没有赘肉")){
			talkName="肌肉型";
		 }
		 else if(bbmmii>=18.5 && bbmmii<23.9 && (zzhhuuiirroouu.equals("有点赘肉") || zzhhuuiirroouu.equals("赘肉较多"))){
			 talkName="体重正常体脂偏高";
		 }
		 else if(bbmmii>=18.5 && bbmmii<23.9 && zzhhuuiirroouu.equals("没有赘肉")) {
			talkName="标准体型";
		 }
		 else if(bbmmii<18.5) {
			talkName="偏瘦";
		 }
		 List<ReportTalkDO> reportTalkDOs = paperService.listReportTalk(new HashMap<String,Object>());
 		 List<ReportTalkDO> l=  reportTalkDOs.stream().filter(a -> "TIXING".equals(a.getTalkType()))
 							  .filter(c -> talkName.equals(c.getTalkName()))
 							  .filter(b -> b.getScoreLittle()<=yinshi600 && b.getScoreBig() >= yinshi600)
 							  .collect(Collectors.toList());
 		if(l.size()>0){
 			  model.addAttribute("huashu",l.get(0).getTalkContent());
 		}					  
		
		return "information/baogao-jianfei";
	}


	
	
	@Log("获取检测的数据")
	@GetMapping("/getMyReportData")
	@ResponseBody
	public Map<String,List<QuestionDO>> getMyReportData(Integer product,HttpServletRequest request,String openid,Date date){
		System.out.println("------------------------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		System.out.println("------------------------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		System.out.println("------------------------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		System.out.println("------------------------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		System.out.println("------------------------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		 DecimalFormat df = new DecimalFormat("0.0");
		 System.out.println("====openid对不对----==="+openid);
		CustomerPaperDO udo1 = paperService.getCustomerPaperDO(openid,product,date);
		float high = udo1.getHigh();
		float weight=  Float.parseFloat( udo1.getWeight()+"");  
		float bmi=  Float.parseFloat(df.format(weight/high/high*10000));
		String timeString= (String)request.getSession().getAttribute("timeString");
		List<ProductpaperDO> productpaperDOList = paperService.getProductpaperDOByOpenId(openid,product,date);
		System.out.println("===productpaperDOList======"+productpaperDOList.get(0).getOpenid());
		ProductpaperDO productpaperDO = productpaperDOList.get(0);
	    Map<String,List<QuestionDO>> map  = new HashMap<String,List<QuestionDO>>();
	    if(productpaperDO!=null){
	    	Integer productpaper = productpaperDO.getId();
	    	List<String> fenleiList = Arrays.asList("SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");//身体状况  膳食习惯  生活方式  睡眠压力  运动习惯
	    	for(String fenlei:fenleiList){
	    		List<QuestionDO> list = paperService.getQuestionDOList(productpaper,fenlei);
	    		for(QuestionDO questionDO :list){
	    			if("YUN_DONG".equals(questionDO.getIfStop()) && bmi<29){
	    				questionDO.setTiaozheng(questionDO.getChoiceProductDOList().get(0).getTadfs());
	    				questionDO.getChoiceProductDOList().get(0).setTads(questionDO.getChoiceProductDOList().get(0).getTadss());
	    				questionDO.getChoiceProductDOList().get(0).setTadreason(questionDO.getChoiceProductDOList().get(0).getTadreasons());
	    				questionDO.getChoiceProductDOList().get(0).setTadjianyi(questionDO.getChoiceProductDOList().get(0).getTadjianyis());
	    			}
	    			else
	    				questionDO.setTiaozheng(questionDO.getChoiceProductDOList().get(0).getTadf());
	    			
	    		}
	    		map.put(fenlei,list);
	    	}
	    }
	    return map;
	}
	
	
	 /**
     * 微信授权登录
     * */
    @Log("微信授权登录")
    @GetMapping("/weChatLogin")
    public void wx_denglu(HttpServletRequest request, HttpServletResponse response){
		String url = urlEncodeUTF8("http://wenjuan.biocareuk.com/weCahtCallBack"); //回调页面的路径
    	try {
    			String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WechatOAConfig.APP_ID+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base#wechat_redirect";
    			response.sendRedirect(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
    }
	
	 /**
     * 回调
     * */
    @Log("微信登录回调")
    @GetMapping("/weCahtCallBack")
    public Map<String, Object> getAccessToken(HttpServletRequest request, HttpServletResponse response)throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	String code = request.getParameter("code");
    	String openid = null;
    	if(StringUtils.isNotBlank(code)){
    		openid = WechatOAConfig.getAccessToken(code);
        	map.put("openid", openid);
    	}
		return map;
    }
    
    /**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
         try {
             result = java.net.URLEncoder.encode(source, "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         return result;
     }
}
