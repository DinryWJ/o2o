package com.wjj.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjj.o2o.dto.ImageHolder;
import com.wjj.o2o.dto.ShopExecution;
import com.wjj.o2o.entity.Area;
import com.wjj.o2o.entity.PersonInfo;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.entity.ShopCategory;
import com.wjj.o2o.enums.ShopStateEnum;
import com.wjj.o2o.exceptions.ShopOperationException;
import com.wjj.o2o.service.AreaService;
import com.wjj.o2o.service.ShopCategoryService;
import com.wjj.o2o.service.ShopService;
import com.wjj.o2o.utils.CodeUtil;
import com.wjj.o2o.utils.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/shoplist");
			} else {
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		PersonInfo user = new PersonInfo();
		user.setUserId(9L);
		user.setName("test");
		request.getSession().setAttribute("user", user);
		user = (PersonInfo) request.getSession().getAttribute("user");
		// long employeeId = user.getUserId();
		// if (hasAccountBind(request, employeeId)) {
		// modelMap.put("hasAccountBind", true);
		// } else {
		// modelMap.put("hasAccountBind", false);
		// }
		List<Shop> list = new ArrayList<Shop>();
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
			list = shopExecution.getShopList();
			modelMap.put("shopList", list);
			modelMap.put("user", user);
			modelMap.put("success", true);
			// 列出店铺成功之后，将店铺放入session中作为权限验证依据，即该帐号只能操作它自己的店铺
			// request.getSession().setAttribute("shopList", list);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = new ArrayList<Area>();
				areaList = areaService.getAreaList();
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
				modelMap.put("shop", shop);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		modelMap.put("areaList", areaList);
		modelMap.put("success", true);
		return modelMap;
	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}

		// 1接受并转化相应的参数，包括店铺信息以及图片信息
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		// 2注册店铺
		if (shop != null && shopImg != null) {
			try {
				PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
				shop.setOwner(owner);
				ShopExecution se;
				try {
					ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
					se = shopService.addShop(shop, imageHolder);
					if (se.getState() == ShopStateEnum.CHECK.getState()) {
						modelMap.put("success", true);
						// 若shop创建成功，则加入session中，作为权限使用

						@SuppressWarnings("unchecked")
						List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
						if (shopList == null || shopList.size() == 0) {
							shopList = new ArrayList<Shop>();
						}
						shopList.add(se.getShop());
						request.getSession().setAttribute("shopList", shopList);
					} else {
						modelMap.put("success", false);
						modelMap.put("errMsg", se.getStateInfo());
					}
				} catch (ShopOperationException e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.toString());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}

		// 1接受并转化相应的参数，包括店铺信息以及图片信息
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
		}
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		// 2修改店铺信息
		if (shop != null && shop.getShopId() != null) {
			try {
				ShopExecution se;
				try {
					if (shopImg == null) {
						se = shopService.modifyShop(shop, null);
					} else {
						ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),
								shopImg.getInputStream());
						se = shopService.modifyShop(shop, imageHolder);
					}
					if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
						modelMap.put("success", true);
					} else {
						modelMap.put("success", false);
						modelMap.put("errMsg", se.getStateInfo());

					}
				} catch (ShopOperationException e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.toString());

				}

			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());

			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺ID");
			return modelMap;
		}

	}
}
