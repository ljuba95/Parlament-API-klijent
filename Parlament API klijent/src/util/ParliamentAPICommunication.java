package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Member;
public class ParliamentAPICommunication {

	private static final String membersUrl = "http://147.91.128.71:9090/parlament/api/members";
	private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
	
	public List<Member> getMembers() throws Exception{
		String result = sendGet(membersUrl);
		Gson gson = new GsonBuilder().create();
		JsonArray membersJson = gson.fromJson(result, JsonArray.class);
		List<Member> members = new LinkedList<>();
		
		for (int i = 0; i < membersJson.size(); i++) {
			JsonObject memberJson = (JsonObject) membersJson.get(i);
			Member m = new Member();
			m.setId(memberJson.get("id").getAsInt());
			m.setName(memberJson.get("name").getAsString());
			m.setLastName(memberJson.get("lastName").getAsString());
			if(memberJson.get("birthDate")!= null){
				m.setBirthDate(format.parse(memberJson.get("birthDate").getAsString()));
			}
			
			members.add(m);
		}
		return members;
		
	}
	private String sendGet(String stringurl)throws Exception{
		URL url = new URL(stringurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("GET");
		String response = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		boolean end = false;
		while(!end){
			String line = in.readLine();
			if(line == null)
				end = true;
			else
				response += line;
		}
		in.close();
		return response;
	}
}
