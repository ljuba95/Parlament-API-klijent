package model;

import java.util.LinkedList;

import domain.Member;
import model.interfaces.ParliamentInterface;

public class Parliament implements ParliamentInterface{

	private LinkedList<Member> members = new LinkedList<>();

	@Override
	public LinkedList<Member> getMembers() {
		return members;
	}

	@Override
	public void setMembers(LinkedList<Member> members) {
		this.members.addAll(members);
	}
	
	
}
