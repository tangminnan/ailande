package com.gene.information.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.iterators.ArrayListIterator;
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
import com.gene.common.utils.StringUtils;
import com.gene.information.domain.AccessToken;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.WechatOAConfig;
import com.gene.information.service.PaperService;

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
    	String openid = "o85JHw-iXn1_uxz7SgHxdJQf30LU";//暂定写死，之前是空字符串
    	if(StringUtils.isNotBlank(code)){
    		try {
				openid = WechatOAConfig.getAccessToken(code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	
	/*@Log("跳转答题分类页面")
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
	}*/
	
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
			for(String fenlei :fenleiList)
				list.addAll(paperService.getQuestionDOType(products,fenlei));
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
    	String openid = "o85JHw-iXn1_uxz7SgHxdJQf30LU";//暂定写死，之前是空字符串
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
	
	/*@Log("保存基本信息")
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
	}*/
	
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
			Integer[] products = (Integer[]) request.getSession().getAttribute("products");
			System.out.println("products===="+products.length);
			for(int i : products){
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
				ProductpaperDO productpaperDO = paperService.getLatestProductPaper(openid, i);
				int user = customerPaperDO.getId();
				Optional.ofNullable(productpaperDO).ifPresent(p ->{
					productpaperDO.setUser(user);
					paperService.updateProductPaper(productpaperDO);
				});
				
			}
			
			return R.ok();
		}
	}
	
	@Log("查看报告")
	@GetMapping("/lookCheckLog")
	public String lookCheckLog(Integer product,String name,String openid, Model model,HttpServletRequest request){
//		openid="ocqxT5xl5XAidygc01PGGQngKV4Q";
		System.out.println("送到页面的openid====================="+openid);
		System.out.println("送到页面的openid====================="+openid);
		
		CustomerPaperDO udo1 = paperService.getLatestCustomerPaperDO(openid,product);
		   System.out.println(	"11111111111111======="+udo1.getHigh());
		   System.out.println(	"11111111111111======="+udo1.getWeight());
		   System.out.println(	"11111111111111======="+udo1.getUsername());
		
		model.addAttribute("userName",udo1.getUsername());
		model.addAttribute("product", product);
		model.addAttribute("name",name);
		model.addAttribute("openid",openid);
		if("肠胃调理".equals(name))
			return"information/baogao-2";
		if("科学瘦身".equals(name))
			return "information/baogao-5";
		return "";
		
	}
	
	@Log("阅读检测报告--肠胃调理")
	@GetMapping("/readMyReport")
	public String readMyReport(Model model,Integer product,String name,HttpServletRequest request,String openid){
		String timeString= (String)request.getSession().getAttribute("timeString");
//		List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
//		ProductpaperDO productpaperDO=productpaperDOList.get(0);
	    Map<String,Integer> mapD  = new HashMap<String,Integer>();
	    Map<String,Integer> mapT = new HashMap<String,Integer>();
	    DecimalFormat df = new DecimalFormat("0.0");
	   /**
	    * 根据openid  产品去拿最新的检测结果
	    */
	    
	    List<ProductpaperDO> productpaperDOList = paperService.getNewProductpaperDO(openid,product);
	    ProductpaperDO productpaperDO=productpaperDOList.get(0);
	    
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
	    	int shenti80=(int)((float)mapD.get("SHENTI_ZHUANG")/mapT.get("SHENTI_ZHUANG")*100);
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
	    	int yinshi60 =(int)((float)defen/zongfen*100);
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
	    model.addAttribute("openid", openid);
		model.addAttribute("product", product);
	    return "information/baogao-3";
	}
	
	@Log("阅读检测报告--科学瘦身")
	@GetMapping("/kexueshoushens")
	public String kexueshoushen(Model model,Integer product,String name,HttpServletRequest request,String openid) {
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		System.out.println("科学瘦身================================"+openid);
		String timeString= (String)request.getSession().getAttribute("timeString");
	/*	List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
		ProductpaperDO productpaperDO = productpaperDOList.get(0);*/
		 List<ProductpaperDO> productpaperDOList = paperService.getNewProductpaperDO(openid,product);
		 ProductpaperDO productpaperDO=productpaperDOList.get(0);
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
	    	int shenti80=(int)((float)mapD.get("SHENTI_ZHUANG")/mapT.get("SHENTI_ZHUANG")*100);
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
	    	int yinshi60 =(int)((float)defen/zongfen*100);
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
	    
		
		/**
		 * 计算bmi
		 */
		
		CustomerPaperDO udo1 = paperService.getLatestCustomerPaperDO(openid,product);
		   
		Integer high = udo1.getHigh();
		float weight=  Float.parseFloat( udo1.getWeight()+"");  
		String bmi=  df.format(weight/high/high*10000);
		model.addAttribute("high",high);
		model.addAttribute("weight",weight);
		model.addAttribute("bmi",bmi);
		/**
		 * 是否有赘肉
		 */
		
		List<String> str  = paperService.getChoosedContent(openid,product,"赘肉");
			
		model.addAttribute("openid", openid);
		model.addAttribute("zhuirou",str.size()>0?str.get(0):"没有赘肉");
		return "information/baogao-jianfei";
	}


	
	
	@Log("获取检测的数据")
	@GetMapping("/getMyReportData")
	@ResponseBody
	public Map<String,List<QuestionDO>> getMyReportData(Integer product,HttpServletRequest request,String openid){
		 DecimalFormat df = new DecimalFormat("0.0");
		 System.out.println("====openid对不对----==="+openid);
		CustomerPaperDO udo1 = paperService.getLatestCustomerPaperDO(openid,product);
		float high = udo1.getHigh();
		float weight=  Float.parseFloat( udo1.getWeight()+"");  
		float bmi=  Float.parseFloat(df.format(weight/high/high*10000));
		String timeString= (String)request.getSession().getAttribute("timeString");
		List<ProductpaperDO> productpaperDOList = paperService.getProductpaperDOByOpenId(openid,product);
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
