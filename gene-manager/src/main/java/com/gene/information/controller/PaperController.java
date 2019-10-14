package com.gene.information.controller;
import com.gene.common.utils.PageUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.information.domain.PaperDO;
import com.gene.information.service.PaperService;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



/**
 * 问卷 控制层
 *
 * @author gene
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/information/questionnaire")
public class PaperController{
 
    @Autowired
    private PaperService paperService;


    /**
     * 列表页
     */
    @RequiresPermissions("information:questionnaire:questionnaire")
    @GetMapping
    public String paper() {
        return "information/questionnaire/paper";
    }

    /**
     * 新增页
     */
    @GetMapping("/add")
    @RequiresPermissions("information:questionnaire:add")
    public String add() {
        return "information/questionnaire/add";
    }

    /**
     * 编辑页
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("information:questionnaire:edit")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        PaperDO paper = paperService.getPaperById(id);
        mmap.put("paper", paper);
        return "information/questionnaire/edit";
    }

    /**
     * 查询问卷列表
     */
    @RequiresPermissions("information:questionnaire:questionnaire")
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params) {
    	
    	Query query = new Query(params);
		List<PaperDO> list = paperService.list(query);
		int total = paperService.count(query);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
      
    }

    /**
     * 保存问卷
     */
    @RequiresPermissions("information:questionnaire:add")
   
    @PostMapping("/addSave")
    @ResponseBody
    public R addSave(@RequestBody PaperDO paper) {
    	PaperDO P =  paper;
    	return paperService.save(paper);
    }

    /**
     * 修改保存问卷
     */
    @RequiresPermissions("information:questionnaire:edit")
      @PostMapping("/update")
    @ResponseBody
    public R editSave(@RequestBody PaperDO paper) {
    	System.out.println(paper);
		return null;
      
    }

    /**
     * 删除问卷
     */
    @RequiresPermissions("information:questionnaire:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
    	PaperDO paperDO=new PaperDO();
    	paperDO.setId(id);
    	paperDO.setDelFlag("1");
    	if(paperService.update(paperDO)>0)
    		return R.ok();
    	return R.error();
    }
    
    /**
     * 修改问卷状态
     */
    @RequiresPermissions("information:questionnaire:edit")
    @PostMapping("/edit2")
    @ResponseBody
    public R edit2(Long id,String flag) {
    	PaperDO paperDO=new PaperDO();
    	paperDO.setId(id);
    	paperDO.setStatus(flag);
    	if(paperService.update(paperDO)>0)
    		return R.ok();
    	return R.error();
    }


    /**
     * 问卷状态修改
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public R changeStatus(PaperDO paper) {
		return null;
//        return toAjax(paperService.updateById(paper));
    }
}
