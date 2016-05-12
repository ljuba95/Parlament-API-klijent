package model.interfaces;

import java.util.LinkedList;

import domain.Member;

public interface ParliamentInterface {

	public LinkedList<Member> getMembers();
	
	public void setMembers(LinkedList<Member> members);
}
