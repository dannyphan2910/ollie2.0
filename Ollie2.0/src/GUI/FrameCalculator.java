package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import calculator.*;

@SuppressWarnings("serial")
public class FrameCalculator extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel[] row = new JPanel[5];
	private JButton[] button = new JButton[19];
	private String[] buttonString = { "7", "8", "9", "+",

									"4", "5", "6", "-",

									"1", "2", "3", "*",

									".", "/", "C", "√",

									"+/-", "=", "0" };

	private int[] dimW = { 300, 45, 100, 90 };
	private int[] dimH = { 35, 40 };

	private Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	private Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	private Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	private Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);

	private boolean doPlus = false;
	private boolean doMinus = false;
	private boolean doMulti = false;
	private boolean doDiv = false;

	private double[] temporary = {0, 0};

	private JTextArea display = new JTextArea(1, 20);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCalculator frame = new FrameCalculator();
					frame.setVisible(true);
					frame.setTitle("Calculator Application");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameCalculator() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		GridLayout grid = new GridLayout(5, 5);
		setLayout(grid);

		setSize(380, 250);
		setResizable(false);

		FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER);
		FlowLayout flow2 = new FlowLayout(FlowLayout.CENTER, 1, 1);

		for (int i = 0; i < 5; i++)
			row[i] = new JPanel();

		row[0].setLayout(flow1);

		for (int i = 1; i < 5; i++)
			row[i].setLayout(flow2);

		display.setEditable(false);
		display.setAlignmentX(Component.RIGHT_ALIGNMENT);
		display.setPreferredSize(displayDimension);

		createButtons();

		setVisible(true);

	}
	
	private void createButtons() {
		for (int i = 0; i < 19; i++) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].addActionListener(this);
		}
		
		for (int i = 0; i < 14; i++) {
			button[i].setPreferredSize(regularDimension);
		}

		for (int i = 14; i < 18; i++) {
			button[i].setPreferredSize(rColumnDimension);
		}

		button[18].setPreferredSize(zeroButDimension);
		row[0].add(display);
		add(row[0]);

		for (int i = 0; i < 4; i++) {
			row[1].add(button[i]);
		}

		row[1].add(button[14]);
		add(row[1]);

		for (int i = 4; i < 8; i++) {
			row[2].add(button[i]);
		}

		row[2].add(button[15]);
		add(row[2]);

		for (int i = 8; i < 12; i++) {
			row[3].add(button[i]);
		}

		row[3].add(button[16]);
		add(row[3]);

		row[4].add(button[18]);

		for (int i = 12; i < 14; i++) {
			row[4].add(button[i]);
		}

		row[4].add(button[17]);
		add(row[4]);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		displayNum(ae);
		
		doBasic(ae);
		
		doAdvanced(ae);

		if (ae.getSource() == button[14])
			clear();

	}
	
	private void doBasic(ActionEvent ae) {
		if (ae.getSource() == button[3]) {
			// add 
			temporary[0] = Double.parseDouble(display.getText());
			doPlus = true;
			display.setText("");
		}

		if (ae.getSource() == button[7]) {
			// subtract 
			temporary[0] = Double.parseDouble(display.getText());
			doMinus = true;
			display.setText("");
		}

		if (ae.getSource() == button[11]) {
			// multiply 
			temporary[0] = Double.parseDouble(display.getText());
			doMulti = true;
			display.setText("");
		}

		if (ae.getSource() == button[13]) {
			// divide 
			temporary[0] = Double.parseDouble(display.getText());
			doDiv = true;
			display.setText("");
		}
		
		if (ae.getSource() == button[17])
			getResult();
	}
	
	private void doAdvanced(ActionEvent ae) {
		if (ae.getSource() == button[15])
			getSqrt();

		if (ae.getSource() == button[16])
			getPosNeg();
	}
	
	private void displayNum(ActionEvent ae) {
		if (ae.getSource() == button[0])
			display.append("7");

		if (ae.getSource() == button[1])
			display.append("8");

		if (ae.getSource() == button[2])
			display.append("9");

		if (ae.getSource() == button[4])
			display.append("4");

		if (ae.getSource() == button[5])
			display.append("5");

		if (ae.getSource() == button[6])
			display.append("6");

		if (ae.getSource() == button[8])
			display.append("1");

		if (ae.getSource() == button[9])
			display.append("2");

		if (ae.getSource() == button[10])
			display.append("3");

		if (ae.getSource() == button[18])
			display.append("0");

		if (ae.getSource() == button[12])
			display.append(".");
	}

	private void getResult() {
		double result = 0; // variable for result
		temporary[1] = Double.parseDouble(display.getText()); // our second temporary number from display
		
		if (doMulti == true) // we start off with multiplication obviously
			result = new BasicOp().multiply(temporary);
		else if (doDiv == true) // now division
			try {
				result = new BasicOp().divide(temporary);
			} catch (IllegalArgumentException e) {
				return;
				
			}
		else if (doPlus == true) // now addition
			result = new BasicOp().add(temporary);
		else if (doMinus == true) // now subtraction
			result = new BasicOp().subtract(temporary);

		displayIntDouble(result);

		restartFunc();
	}

	private void getPosNeg() {
		double value = Double.parseDouble(display.getText()); 
		
		displayIntDouble(new ExpOp().switchSign(value));
	}

	private void getSqrt() {
		double value = Double.parseDouble(display.getText()); 
		if (value < 0) {
			return;
		}
		display.setText(Double.toString(new ExpOp().squareRoot(value))); 
	}

	private void clear() {
		display.setText(""); // Sets the display blank
		
		restartFunc();
		restartNum();
	}
	
	private void restartFunc() {
		// Sets the functions to false
		doMinus = false;
		doDiv = false;
		doPlus = false;
		doMinus = false;
		
	}
	
	private void restartNum() {
		for (int i = 0; i < 2; i++)
			temporary[i] = 0; // Sets our temporary variables back to 0
	}

	private void displayIntDouble(double num) {
		String toDisplay = Double.toString(num);

		if (num == Math.floor(num)) {
			toDisplay = Integer.toString((int) num);
		}

		display.setText(toDisplay);

	}

}
