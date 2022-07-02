package com.example.MakingWebApplication.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MakingWebApplication.domain.Member;
import com.example.MakingWebApplication.domain.Order;
import com.example.MakingWebApplication.domain.OrderSearch;
import com.example.MakingWebApplication.domain.item.Item;
import com.example.MakingWebApplication.service.ItemService;
import com.example.MakingWebApplication.service.MemberService;
import com.example.MakingWebApplication.service.OrderService;

/**
 * User: HolyEyE Date: 2013. 12. 4. Time: 오후 9:07
 */
@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	MemberService memberService;
	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String createForm(Model model) {

		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();

		model.addAttribute("members", members);
		model.addAttribute("items", items);

		return "order/orderForm";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
			@RequestParam("count") int count) {

		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {

		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);

		return "order/orderList";
	}

	@RequestMapping(value = "/orders/{orderId}/cancel")
	public String processCancelBuy(@PathVariable("orderId") Long orderId) {

		orderService.cancelOrder(orderId);

		return "redirect:/orders";
	}
}
