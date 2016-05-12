package util;

import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Member;



public class MemberJsonUtility {

	public static JsonArray serializeMembers(LinkedList<Member> members) {
		JsonArray membersArray = new JsonArray();

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);

			JsonObject memberJson = new JsonObject();
			memberJson.addProperty("id", m.getId());
			memberJson.addProperty("name", m.getName());
			memberJson.addProperty("lastName", m.getLastName());

			membersArray.add(memberJson);
		}

		return membersArray;
	}
}
