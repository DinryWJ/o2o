package com.wjj.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjj.o2o.dto.ShopExecution;
import com.wjj.o2o.entity.Area;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.entity.ShopCategory;
import com.wjj.o2o.service.AreaService;
import com.wjj.o2o.service.ShopCategoryService;
import com.wjj.o2o.service.ShopService;
import com.wjj.o2o.utils.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
	@Autowired
	private AreaService areaService;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/listshoppageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			try {
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if ((pageIndex > -1) && (pageSize > -1)) {
			Long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			Long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			Long areaId = HttpServletRequestUtil.getLong(request, "areaId");
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
			ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}

	private Shop compactShopCondition4Search(Long parentId, Long shopCategoryId, Long areaId, String shopName) {
		Shop shopCondition = new Shop();
		if(parentId!=-1L){
			ShopCategory childcategory = new ShopCategory();
			ShopCategory parentcategory = new ShopCategory();
			parentcategory.setShopCategoryId(parentId);
			childcategory.setParent(parentcategory);
			shopCondition.setShopCategory(childcategory);
		}
		if(shopCategoryId!=-1L){
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if(areaId !=-1L){
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		if(shopName !=null){
			shopCondition.setShopName(shopName);
		}
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
}
