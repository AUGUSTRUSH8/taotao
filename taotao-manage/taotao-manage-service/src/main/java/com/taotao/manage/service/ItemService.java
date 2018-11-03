package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
@Service
public class ItemService extends BaseService<Item> {
	@Autowired
	private ItemDescService itemDescService;
	public Boolean saveItem(Item item, String desc) {
		//保存商品大概数据
		item.setStatus(1);
		item.setId(null);
		Integer count1=super.save(item);
		//保存商品描述信息
		ItemDesc itemDesc=new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		Integer count2=this.itemDescService.save(itemDesc);
		return count1.intValue()==1&&count2.intValue()==1;
	}

}
