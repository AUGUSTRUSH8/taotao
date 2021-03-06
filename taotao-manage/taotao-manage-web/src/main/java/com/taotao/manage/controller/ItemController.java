package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.StringUtils;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;
@RequestMapping("item")
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item,@RequestParam("desc")String desc){
		try {
			if(StringUtils.isNullOrEmpty(item.getTitle())){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			//保存商品
			Boolean bool=this.itemService.saveItem(item,desc);
			if(!bool) {
				//保存失败
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
