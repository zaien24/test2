package com.adminproject.menu.service.impl;

import java.util.List;

import com.adminproject.menu.service.MenuVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("menuMapper")
public interface MenuMapper {

	List<MenuVO> selectMenuList(MenuVO menuVO);

	String selectMaxMenuCd(MenuVO menuVO);

	int insertMenu(MenuVO menuVO);

	int updateMenu(MenuVO menuVO);

}
