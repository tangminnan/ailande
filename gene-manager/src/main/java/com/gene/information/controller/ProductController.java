package com.gene.information.controller;

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

import com.gene.common.utils.PageUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.information.domain.PaperEntity;
import com.gene.information.domain.ProductDO;
import com.gene.information.domain.ProductPaperDO;
import com.gene.information.service.ProductPaperService;
import com.gene.information.service.ProductService;


/**
 * 产品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
 
@Controller
@RequestMapping("/information/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductPaperService productPaperService;
	
	@GetMapping()
	@RequiresPermissions("information:product:product")
	String Product(){
	    return "information/product/product";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:product:product")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:product:add")
	String add(Model model){
		List<PaperEntity> paperList = productService.paperList();
		model.addAttribute("paperList", paperList);
	    return "information/product/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:product:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProductDO product = productService.get(id);
		List<PaperEntity> paperList = productService.paperList();
		model.addAttribute("paperList", paperList);
		model.addAttribute("product", product);
	    return "information/product/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:product:add")
	public R save( ProductDO product){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", product.getCode());
		List<ProductDO> list = productService.list(params);
		if(list.size()>0){
			return R.error("编号已存在");
		}
		product.setCreateTime(new Date());
		if(productService.save(product)>0){
			ProductPaperDO productPaper = new ProductPaperDO();
			productPaper.setProductId(product.getId());
			productPaper.setPaperId(product.getPaperId());
			productPaper.setCreateTime(new Date());
			productPaperService.save(productPaper);
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:product:edit")
	public R update( ProductDO product){
		productService.update(product);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", product.getId());
		List<ProductPaperDO> list = productPaperService.list(params);
		Integer id = list.get(0).getId();
		ProductPaperDO productPaper = new ProductPaperDO();
		productPaper.setId(id);
		productPaper.setPaperId(product.getPaperId());
		productPaperService.update(productPaper);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:product:remove")
	public R remove( Integer id){
		if(productService.remove(id)>0){
			ProductDO product = new ProductDO();
			product.setId(id);
			product.setDelFlag("2");
			productService.update(product);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("productId", id);
			List<ProductPaperDO> list = productPaperService.list(params);
			Integer id2 = list.get(0).getId();
			productPaperService.remove(id2);
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:product:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		productService.batchRemove(ids);
		return R.ok();
	}
	
}
