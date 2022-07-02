package com.example.MakingWebApplication.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 엔티티 저장시 구분 칼럼에 입력할 값을 지정한다.
public class Album extends Item {

	private String artist;
	private String etc;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "Album{" + "artist='" + artist + '\'' + ", etc='" + etc + '\'' + '}';
	}
}
