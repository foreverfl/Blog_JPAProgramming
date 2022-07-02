package com.example.MakingWebApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.MakingWebApplication.domain.Address;
import com.example.MakingWebApplication.domain.Member;
import com.example.MakingWebApplication.domain.Order;
import com.example.MakingWebApplication.domain.OrderStatus;
import com.example.MakingWebApplication.domain.item.Book;
import com.example.MakingWebApplication.domain.item.Item;
import com.example.MakingWebApplication.exception.NotEnoughStockException;
import com.example.MakingWebApplication.repository.OrderRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
//@TransactionConfiguration(defaultRollback = false)
public class OrderServiceTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception {

		// Given
		Member member = createMember();
		Item item = createBook("시골 JPA", 10000, 10); // 이름, 가격, 재고
		int orderCount = 2;

		// When
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		// Then
		Order getOrder = orderRepository.findOne(orderId);

		assertEquals("상품 주문시 상태는 주문(ORDER)이다.", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2, getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
	}

	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() throws Exception {

		// Given
		Member member = createMember();
		Item item = createBook("시골 JPA", 10000, 10); // 이름, 가격, 재고

		int orderCount = 11; // 재고 보다 많은 수량

		// When
		orderService.order(member.getId(), item.getId(), orderCount);

		// Then
		fail("재고 수량 부족 예외가 발생해야 한다.");
	}

	@Test
	public void 주문취소() {

		// Given
		Member member = createMember();
		Item item = createBook("시골 JPA", 10000, 10); // 이름, 가격, 재고
		int orderCount = 2;

		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		// When
		orderService.cancelOrder(orderId);

		// Then
		Order getOrder = orderRepository.findOne(orderId);

		assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}

	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setStockQuantity(stockQuantity);
		book.setPrice(price);
		em.persist(book);
		return book;
	}
}
