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
	public String index(HttpServletRequest request){
		String str= new SimpleDateFormat("yyyyMMddhhMmss").format(new Date());//请求时间戳
		request.getSession().setAttribute("timeString", str);
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
		paperService.saveChoosedProduct(products,request);
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
	public String saveYourName(String name,Integer index,Integer count,HttpServletRequest request,Model model){
		model.addAttribute("name", request.getSession().getAttribute("name"));
		model.addAttribute("index",index);
		model.addAttribute("count", count);
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
							 Model model,
							 HttpServletRequest request){
		Integer[] products = (Integer[])request.getSession().getAttribute("products");
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
	public String getReportPage(){
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
	public R saveWenJuan(String objs,HttpServletRequest request){
		return paperService.saveWenJuan(objs,request);
	}
	
	@Log("查看报告")
	@GetMapping("/lookCheckLog")
	public String lookCheckLog(Integer product,String name, Model model,HttpServletRequest request){
		
		model.addAttribute("userName",request.getSession().getAttribute("name"));
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
		String timeString= (String)request.getSession().getAttribute("timeString");
		List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
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
	    return "information/baogao-3";
	}
	
	@Log("阅读检测报告--科学瘦身")
	@GetMapping("/kexueshoushens")
	public String kexueshoushen(Model model,Integer product,String name,HttpServletRequest request) {
		String timeString= (String)request.getSession().getAttribute("timeString");
		List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
		ProductpaperDO productpaperDO = productpaperDOList.get(0);
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
		float high = paperService.getSingleJiBenXinxi(request.getSession().getId()+timeString,product, "身高").get(0);
		float weight =paperService.getSingleJiBenXinxi(request.getSession().getId()+timeString,product, "体重").get(0);
		String bmi=  df.format(weight/high/high*10000);
		model.addAttribute("high",high);
		model.addAttribute("weight",weight);
		model.addAttribute("bmi",bmi);
		/**
		 * 是否有赘肉
		 */
		
		List<String> str  = paperService.getChoosedContent(request.getSession().getId()+timeString,product,"赘肉");
			
		model.addAttribute("zhuirou",str.size()>0?str.get(0):"没有赘肉");
		return "information/baogao-jianfei";
	}


	
	
	@Log("获取检测的数据")
	@GetMapping("/getMyReportData")
	@ResponseBody
	public Map<String,List<QuestionDO>> getMyReportData(Integer product,HttpServletRequest request){
		float bmi=  Float.parseFloat((String) request.getSession().getAttribute("bmi"));
		String timeString= (String)request.getSession().getAttribute("timeString");
		List<ProductpaperDO> productpaperDOList = paperService.getProductPaperDO2(request.getSession().getId()+timeString,product);
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
		String url = urlEncodeUTF8("http://qinzi.jingtu99.com/weCahtCallBack"); //回调页面的路径
    	
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
    /*public Map<String, Object> getAccessToken(HttpServletRequest request, HttpServletResponse response)throws Exception{
    	String code = request.getParameter("code");
    	String access_token = null;
    	String openid = null;
    	if(StringUtils.isNotBlank(code)){
    		AccessToken accessToken = WechatOAConfig.getAccessTokenss(code);
        	if(accessToken == null || StringUtils.isBlank(accessToken.getOpenid())){
        		return R.error(40001,"微信授权失败");
    		}
        	access_token = accessToken.getAccess_token();
    		openid = accessToken.getOpenid();
    	}
		return null;	
    }*/
    
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
