package com.gene.information.controller;

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

import com.gene.common.utils.PageUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.information.domain.ReportTalkDO;
import com.gene.information.service.ReportTalkService;


/**
 * 报告话术
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-03-18 10:49:48
 */
 
@Controller
@RequestMapping("/information/reportTalk")
public class ReportTalkController {
	@Autowired
	private ReportTalkService reportTalkService;
	
	@GetMapping("/Talkcw")
	@RequiresPermissions("information:reportTalk:reportTalk")
	String ReportTalkcw(){
	    return "information/reportTalk/reportTalkcw";
	}
	
	@GetMapping("/Talktx")
	@RequiresPermissions("information:reportTalk:reportTalk")
	String ReportTalktx(){
	    return "information/reportTalk/reportTalktx";
	}
	
	/*@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:reportTalk:reportTalk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ReportTalkDO> reportTalkList = reportTalkService.list(query);
		int total = reportTalkService.count(query);
		PageUtils pageUtils = new PageUtils(reportTalkList, total);
		return pageUtils;
	}*/
	
	@ResponseBody
	@GetMapping("/listcw")
	@RequiresPermissions("information:reportTalk:reportTalk")
	public PageUtils listcw(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        query.put("talkType", "CHANGWEI");
		List<ReportTalkDO> reportTalkList = reportTalkService.list(query);
		int total = reportTalkService.count(query);
		PageUtils pageUtils = new PageUtils(reportTalkList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/listtx")
	@RequiresPermissions("information:reportTalk:reportTalk")
	public PageUtils listtx(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        query.put("talkType", "TIXING");
		List<ReportTalkDO> reportTalkList = reportTalkService.list(query);
		int total = reportTalkService.count(query);
		PageUtils pageUtils = new PageUtils(reportTalkList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:reportTalk:add")
	String add(){
	    return "information/reportTalk/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:reportTalk:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ReportTalkDO reportTalk = reportTalkService.get(id);
		model.addAttribute("reportTalk", reportTalk);
	    return "information/reportTalk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:reportTalk:add")
	public R save( ReportTalkDO reportTalk){
		if(reportTalkService.save(reportTalk)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:reportTalk:edit")
	public R update( ReportTalkDO reportTalk){
		reportTalkService.update(reportTalk);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:reportTalk:remove")
	public R remove( Integer id){
		if(reportTalkService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:reportTalk:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		reportTalkService.batchRemove(ids);
		return R.ok();
	}
	
}
