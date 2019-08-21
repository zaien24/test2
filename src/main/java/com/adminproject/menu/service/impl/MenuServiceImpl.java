package com.adminproject.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adminproject.menu.service.MenuService;
import com.adminproject.menu.service.MenuVO;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Resource(name = "menuMapper")
	private MenuMapper menuMapper;

	@Override
	public List<MenuVO> selectMenuList(MenuVO menuVO) {
		List<MenuVO> menuList = null;

		try {
			menuList = menuMapper.selectMenuList(menuVO);
		} catch (Exception e) {
			logger.info("message", e);
		}

		return menuList;
	}

	@Override
	public HashMap<String, Object> insertMenu(MenuVO menuVO) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String menuCd = menuMapper.selectMaxMenuCd(menuVO);
			menuVO.setMenuCd(menuCd);
			
			int result = menuMapper.insertMenu(menuVO);
			
			if (result > 0) {
				resultMap.put("result", "SUCCESS");
				resultMap.put("msg", "메뉴 등록에 성공하였습니다.");
			} else {
				resultMap.put("result", "FAIL");
				resultMap.put("msg", "메뉴 등록에 실패하였습니다.");
			}
			
		} catch (Exception e) {
			logger.info("message", e);
		}

		return resultMap;
	}

	@Override
	public HashMap<String, Object> updateMenu(MenuVO menuVO) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			int result = menuMapper.updateMenu(menuVO);

			if (result > 0) {
				resultMap.put("result", "SUCCESS");
			} else {
				resultMap.put("result", "FAIL");
			}

		} catch (Exception e) {
			logger.info("message", e);
		}

		return resultMap;
	}

}
