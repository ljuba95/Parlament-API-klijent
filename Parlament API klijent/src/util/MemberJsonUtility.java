package util;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Member;

public class MemberJsonUtility {

	private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");

	public static JsonArray serializeMembers(LinkedList<Member> members) throws Exception {
		JsonArray membersArray = new JsonArray();

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);

			JsonObject memberJson = new JsonObject();
			memberJson.addProperty("id", m.getId());
			memberJson.addProperty("name", m.getName());
			memberJson.addProperty("lastName", m.getLastName());
			if (memberJson.get("birthDate") != null) {
				m.setBirthDate(format.parse(memberJson.get("birthDate").getAsString()));
			}
			membersArray.add(memberJson);
		}

		return membersArray;
	}

	public static LinkedList<Member> parseMembers(JsonArray membersJson) throws Exception {
		LinkedList<Member> members = new LinkedList<Member>();

		for (int i = 0; i < membersJson.size(); i++) {
			JsonObject memberJson = (JsonObject) membersJson.get(i);

			Member m = new Member();
			m.setId(memberJson.get("id").getAsInt());
			m.setName(memberJson.get("name").getAsString());
			m.setLastName(memberJson.get("lastName").getAsString());
			if (memberJson.get("birthDate") != null) {
				m.setBirthDate(format.parse(memberJson.get("birthDate").getAsString()));
			}
			members.add(m);
		}

		return members;
	}
}
