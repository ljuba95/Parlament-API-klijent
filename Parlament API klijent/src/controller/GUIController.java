package controller;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import domain.Member;
import util.MemberJsonUtility;
import util.ParliamentAPICommunication;
import view.ParliamentGUI;

public class GUIController {
	private static ParliamentGUI mainWindow;
	private static LinkedList<Member> parliament;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new ParliamentGUI();
					mainWindow.setVisible(true);
					parliament = new LinkedList<>();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void writeJson() throws Exception{
		ParliamentAPICommunication comm = new ParliamentAPICommunication();
		parliament=(LinkedList<Member>)comm.getMembers();
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")))){
			JsonArray jsonArray = MemberJsonUtility.serializeMembers(parliament);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String membersString = gson.toJson(jsonArray);
			out.println(membersString);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static LinkedList<Member> getMembers() {
		try {
			FileReader in = new FileReader("data/serviceMembers.json");
			Gson gson = new GsonBuilder().create();
			JsonArray membersJson = gson.fromJson(in, JsonArray.class);
			parliament=MemberJsonUtility.parseMembers(membersJson);
			
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parliament;
	}

	public static void parseError(String text) {
		mainWindow.parseError(text);
	}

	public static void clearStatus() {
		mainWindow.clearStatus();
	}

	public static void saveModified(List<Member> members) {
		parliament = (LinkedList<Member>) members;
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/updatedMembers.json")))){
			JsonArray jsonArray = MemberJsonUtility.serializeMembers(parliament);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String membersString = gson.toJson(jsonArray);
			out.println(membersString);
			parseError("Modified data saved.");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
