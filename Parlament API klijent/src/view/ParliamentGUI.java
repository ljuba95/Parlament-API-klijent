package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import controller.GUIController;
import view.models.MemberTableModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParliamentGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnGetMembers;
	private JButton btnFillTable;
	private JButton btnUpdate;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JTextArea txtStatus;

	

	/**
	 * Create the frame.
	 */
	public ParliamentGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.EAST);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(140, 100));
			panel.add(getBtnGetMembers());
			panel.add(getBtnFillTable());
			panel.add(getBtnUpdate());
		}
		return panel;
	}
	private JButton getBtnGetMembers() {
		if (btnGetMembers == null) {
			btnGetMembers = new JButton("GET Members");
			btnGetMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						GUIController.writeJson();
						txtStatus.setText("Got members from service.");
					} catch (Exception e1) {
						e1.printStackTrace();
						txtStatus.setText(e1.getMessage());
					}
				}
			});
			btnGetMembers.setPreferredSize(new Dimension(135, 30));
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.setModel(new MemberTableModel(GUIController.getMembers()));
					txtStatus.setText("Table filled with data.");
				}
			});
			btnFillTable.setPreferredSize(new Dimension(135, 30));
		}
		return btnFillTable;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("Update members");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MemberTableModel model = (MemberTableModel) table.getModel();
					GUIController.saveModified(model.getMembers());
				}
			});
			btnUpdate.setPreferredSize(new Dimension(135, 30));
		}
		return btnUpdate;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable_1());
		}
		return scrollPane;
	}
	private JTable getTable_1() {
		if (table == null) {
			table = new JTable();
			table.setFillsViewportHeight(true);
			
		}
		return table;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 80));
			panel_1.setBorder(new TitledBorder(null, "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setLayout(new GridLayout(1, 0, 0, 0));
			panel_1.add(getScrollPane_1());
		}
		return panel_1;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTxtStatus());
		}
		return scrollPane_1;
	}
	private JTextArea getTxtStatus() {
		if (txtStatus == null) {
			txtStatus = new JTextArea();
		}
		return txtStatus;
	}
	public void parseError(String text) {
		txtStatus.setText(text);
	}
	public void clearStatus() {
		txtStatus.setText("");
	}
}
