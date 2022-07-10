package com.example.MakingWebApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MakingWebApplication.domain.Member;
import com.example.MakingWebApplication.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public Long join(Member member) {

		validateDuplicateMember(member); // 중복 회원을 검증한다.
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
