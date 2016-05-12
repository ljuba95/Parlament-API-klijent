package controller;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import domain.Member;
import model.Parliament;
import util.MemberJsonUtility;
import util.ParliamentAPICommunication;
import view.ParliamentGUI;

public class GUIController {
	private static ParliamentGUI mainWindow;
	private static Parliament parliament;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new ParliamentGUI();
					mainWindow.setVisible(true);
					parliament = new Parliament();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void writeJson() throws Exception{
		ParliamentAPICommunication comm = new ParliamentAPICommunication();
		parliament.setMembers((LinkedList<Member>)comm.getMembers());
		JsonArray jsonArray = MemberJsonUtility.serializeMembers(parliament.getMembers());
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")))){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String membersString = gson.toJson(jsonArray);
			out.println(membersString);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
