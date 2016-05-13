package view.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.GUIController;
import domain.Member;

public class MemberTableModel extends AbstractTableModel{

	private final String [] col = new String []{ "ID", "Name","Last name","Birth date" };
	private List<Member> members;
	
	public MemberTableModel(List<Member> members) {
		if(members == null){
			this.members = new LinkedList<>();
		}else{
			this.members = members;
		}
	}
	@Override
	public int getRowCount() {
		if(members == null) return 0;
		return members.size();
	}

	@Override
	public int getColumnCount() {
		return col.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Member m = members.get(rowIndex);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
		switch(columnIndex){
		case 0:
			return m.getId();
		case 1:
			return m.getName();
		case 2:
			return m.getLastName();
		case 3:
			if(m.getBirthDate()!=null)
				return format.format(m.getBirthDate());
			return null;
		default:
			return "N/A";	
		}
	}
	@Override
	public String getColumnName(int column) {
		return col[column];
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0) return false;
		return true;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex){
		Member m = members.get(rowIndex);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
		String value = (String)aValue;
		switch(columnIndex){
		case 0:
			break;
		case 1:
			if(value.isEmpty()){
				GUIController.parseError("Invalid name format.");
				return;
			}
			m.setName((String)aValue);
			break;
		case 2:
			if(value.isEmpty()){
				GUIController.parseError("Invalid last name format.");
				return;
			}
			m.setLastName(value);
			break;
		case 3:
			try {
				m.setBirthDate(format.parse(value));
				GUIController.clearStatus();
				break;
			} catch (ParseException e) {
				GUIController.parseError("Invalid date format.");
				return;
			}
		}
		GUIController.clearStatus();
	}
	public List<Member> getMembers(){
		return members;
	}
}
