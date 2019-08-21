package com.adminproject.menu.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.adminproject.menu.service.MenuService;
import com.adminproject.menu.service.MenuVO;

@Controller
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Resource(name = "menuService")
	private MenuService menuService;

	@Resource(name = "jsonView")
	private View ajaxJsonView;

	/**
	 * 메뉴관리 화면
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuList.do")
	public String menuList(@ModelAttribute MenuVO menuVO, Model model) {

		menuVO.setMenuLv(1);
		List<MenuVO> menuList = menuService.selectMenuList(menuVO);
		
		model.addAttribute("menuList", menuList);
		
		return "menu/menuList.tiles";
	}

	/**
	 * 1차 메뉴등록 레이어 팝업 화면
	 * @param
	 * @return String
	 */
	@RequestMapping(value = "/layer/firstMenuRegist.do")
	public String menuFirstRegist() {
		return "menu/firstMenuRegist";
	}

	/**
	 * 2차 메뉴등록 레이어 팝업 화면
	 * @param
	 * @return String
	 */
	@RequestMapping(value = "/layer/secondMenuRegist.do")
	public String secondMenuRegist(@ModelAttribute MenuVO menuVO, Model model) {

		model.addAttribute("upprMenuCd", menuVO.getUpprMenuCd());
		model.addAttribute("menuLv", menuVO.getMenuLv());

		return "menu/secondMenuRegist";
	}

	/**
	 * 1차 메뉴등록 처리
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertFirstMenu.do")
	public View insertFirstMenu(@ModelAttribute MenuVO menuVO, Model model) {
		
		logger.info("insertFirstMenu 접근");
		logger.info(menuVO.getMenuCd());
		
		HashMap<String, Object> resultMap = menuService.insertMenu(menuVO);
		
		model.addAttribute("resultMap", resultMap);
		
		return ajaxJsonView;
	}

	/**
	 * 1차 메뉴수정 처리
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFirstMenu.do")
	public View updateFirstMenu(@ModelAttribute MenuVO menuVO, Model model) {
		logger.info("message", "updateFirstMenu 접근");
		logger.info("message", menuVO.getUpprMenuCd());
		logger.info("message", menuVO.getMenuCd());
		logger.info("message", menuVO.getMenuLv());
		logger.info("message", menuVO.getMenuNm());
		logger.info("message", menuVO.getMenuUrl());
		logger.info("message", menuVO.getUseYn());
		logger.info("message", menuVO.getDpYn());
		logger.info("message", menuVO.getSortSeq());

		HashMap<String, Object> resultMap = menuService.updateMenu(menuVO);

		model.addAttribute("resultMap", resultMap);

		return ajaxJsonView;
	}



	/**
	 * 2차 메뉴등록 처리
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertSecondMenu.do")
	public View insertSecondMenu(@ModelAttribute MenuVO menuVO, Model model) {
		logger.info("message", "insertSecondMenu 접근");
		logger.info("message", menuVO.getUpprMenuCd());
		logger.info("message", menuVO.getMenuCd());
		logger.info("message", menuVO.getMenuLv());
		logger.info("message", menuVO.getMenuNm());
		logger.info("message", menuVO.getMenuUrl());
		logger.info("message", menuVO.getUseYn());
		logger.info("message", menuVO.getDpYn());
		logger.info("message", menuVO.getSortSeq());

		HashMap<String, Object> resultMap = menuService.insertMenu(menuVO);

		model.addAttribute("resultMap", resultMap);

		return ajaxJsonView;
	}

	/**
	 * 2차 메뉴수정 처리
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSecondMenu.do")
	public View updateSecondMenu(@ModelAttribute MenuVO menuVO, Model model) {

		HashMap<String, Object> resultMap = menuService.updateMenu(menuVO);

		model.addAttribute("upprMenuCd", menuVO.getUpprMenuCd());
		model.addAttribute("resultMap", resultMap);

		return ajaxJsonView;
	}

	/**
	 * 메뉴관리 화면
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectMenuList.do")
	public View selectMenuList(@ModelAttribute MenuVO menuVO, Model model) {

		List<MenuVO> menuList = menuService.selectMenuList(menuVO);

		model.addAttribute("menuList", menuList);

		return ajaxJsonView;
	}

	/**
	 * 1차 메뉴등록 레이어 팝업 화면
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/layer/firstMenuModify.do")
	public String firstMenuModify(@ModelAttribute MenuVO menuVO, Model model) {
		logger.info("message", "firstMenuModify 접근");
		logger.info("message", menuVO.getUpprMenuCd());
		logger.info("message", menuVO.getMenuCd());
		logger.info("message", menuVO.getMenuLv());
		logger.info("message", menuVO.getMenuNm());
		logger.info("message", menuVO.getMenuUrl());
		logger.info("message", menuVO.getUseYn());
		logger.info("message", menuVO.getDpYn());
		logger.info("message", menuVO.getSortSeq());


		List<MenuVO> menuList = menuService.selectMenuList(menuVO);
		model.addAttribute("menuVO", menuList.get(0));

		return "menu/firstMenuModify";
	}

	/**
	 * 1차 메뉴등록 레이어 팝업 화면
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/layer/secondMenuModify.do")
	public String secondMenuModify(@ModelAttribute MenuVO menuVO, Model model) {
		logger.info("message", "secondMenuModify 접근");
		logger.info("message", menuVO.getUpprMenuCd());
		logger.info("message", menuVO.getMenuCd());
		logger.info("message", menuVO.getMenuLv());
		logger.info("message", menuVO.getMenuNm());
		logger.info("message", menuVO.getMenuUrl());
		logger.info("message", menuVO.getUseYn());
		logger.info("message", menuVO.getDpYn());
		logger.info("message", menuVO.getSortSeq());

		model.addAttribute("menuCd", menuVO.getMenuCd());
		model.addAttribute("menuLv", menuVO.getMenuLv());

		List<MenuVO> menuList = menuService.selectMenuList(menuVO);
		model.addAttribute("menuVO", menuList.get(0));

		return "menu/secondMenuModify";
	}

}
