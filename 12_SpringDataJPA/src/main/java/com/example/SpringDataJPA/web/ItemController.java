package com.example.SpringDataJPA.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.SpringDataJPA.domain.item.Book;
import com.example.SpringDataJPA.domain.item.Item;
import com.example.SpringDataJPA.service.ItemService;

@Controller
@SessionAttributes("item")
public class ItemController {

	@Autowired
	ItemService itemService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/items/new", method = RequestMethod.GET)
	public String createForm() {
		return "items/createItemForm";
	}

	@RequestMapping(value = "/items/new", method = RequestMethod.POST)
	public String create(Book item) {

		itemService.saveItem(item);
		return "redirect:/items";
	}

	/**
	 * 상품 수정 폼
	 */
	@RequestMapping(value = "/items/{itemId}/edit", method = RequestMethod.GET)
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {

		Item item = itemService.findOne(itemId);
		model.addAttribute("item", item);
		return "items/updateItemForm";
	}

	/**
	 * 상품 수정
	 */
	@RequestMapping(value = "/items/{itemId}/edit", method = RequestMethod.POST)
	public String updateItem(@ModelAttribute("item") Book item, SessionStatus status) {

		itemService.saveItem(item);
		status.setComplete();
		return "redirect:/items";
	}

	/**
	 * 상품 목록
	 */
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String list(Model model) {

		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}

}
