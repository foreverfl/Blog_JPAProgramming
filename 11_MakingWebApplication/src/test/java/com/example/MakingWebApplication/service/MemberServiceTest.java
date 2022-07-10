package com.example.MakingWebApplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.MakingWebApplication.domain.Member;
import com.example.MakingWebApplication.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception {

		// Given
		Member member = new Member();
		member.setName("kim");

		// When
		Long saveId = memberService.join(member);

		// Then
		assertEquals(member, memberRepository.findOne(saveId));
	}

	@Test
	public void 중복_회원_예외() throws Exception {

		// Given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");

		// When
		memberService.join(member1);

		// Then
		assertThrows(IllegalStateException.class, () -> memberService.join(member2));
	}

}