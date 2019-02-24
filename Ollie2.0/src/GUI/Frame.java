package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;

public class Frame {

	private JFrame frame;
	private JTextArea textArea;
	private JButton button;
	private JScrollPane scrollPane;
	private JTextArea display;
	
	private JButton calendar;
	private JButton browser;
	private JButton weather;
	private JButton calculator;

	private static final String NEW_LINE = "\n";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 345, 667);
		frame.setTitle("OLLIE - ASK ME ANYTHING");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 480, 325, 100);
		frame.getContentPane().add(textArea);

		button = new JButton("Press Me");
		button.setBounds(110, 597, 117, 29);
		frame.getContentPane().add(button);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 325, 400);
		frame.getContentPane().add(scrollPane);

		display = new JTextArea();
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		scrollPane.setViewportView(display);
		display.setEditable(false);
		
		makeButtons();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text = textArea.getText();
					displayText(text);
					operation(text);
				
				} catch (Exception ex) {
					display.append("Ollie does not understand." + NEW_LINE);
				}
					
			}
		});

	}
	
	private void makeButtons() {
		calendar = new JButton("");
		calendar.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		calendar.setBounds(10, 6, 50, 50);
		frame.getContentPane().add(calendar);
		calendarButton();
		
		Image calendarIcon = new ImageIcon(this.getClass().getResource("/calendarICON.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		calendar.setIcon(new ImageIcon(calendarIcon));
		
		browser = new JButton("");
		browser.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		browser.setBounds(285, 6, 50, 50);
		frame.getContentPane().add(browser);
		
		Image browserIcon = new ImageIcon(this.getClass().getResource("/browserICON.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		browser.setIcon(new ImageIcon(browserIcon));
		
		weather = new JButton("");
		weather.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		weather.setBounds(95, 6, 50, 50);
		frame.getContentPane().add(weather);
		weatherButton();
		
		Image weatherIcon = new ImageIcon(this.getClass().getResource("/weatherICON.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		weather.setIcon(new ImageIcon(weatherIcon));
		
		calculator = new JButton("");
		calculator.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		calculator.setBounds(190, 6, 50, 50);
		frame.getContentPane().add(calculator);
		calculatorButton();
		
		Image calculatorIcon = new ImageIcon(this.getClass().getResource("/calculatorICON.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		calculator.setIcon(new ImageIcon(calculatorIcon));
	}
	
	private void calendarButton() {
		calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.append("Generating calendar application..." + NEW_LINE);
				new FrameCalendar().setVisible(true);	
			}
		});
	}
	
	private void weatherButton() {
		weather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.append("Generating weather application..." + NEW_LINE);
				new FrameWeather().setVisible(true);
			}
		});
	}
	
	private void calculatorButton() {
		calculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.append("Generating calculator application..." + NEW_LINE);
				new FrameCalculator().setVisible(true);
			}
		});
	}

	public void displayText(String text) {
		display.append("> " + text + NEW_LINE);
		textArea.setText(null);
	}

	public void operation(String text) {
		ArrayList<String> getResult = main.MainCall.main(text);
		
		if (getResult.get(0).equals("INVOKE WEATHER FUNCTION")) {
			display.append("Generating weather application..." + NEW_LINE);
			new FrameWeather().setVisible(true);
		} else if (getResult.get(0).equals("INVOKE CALENDAR FUNCTION")) {
			display.append("Generating calendar application..." + NEW_LINE);
			new FrameCalendar().setVisible(true);
		} else if (getResult.get(0).equals("INVOKE CALCULATOR FUNCTION")) {
			display.append("Generating calculator application..." + NEW_LINE);
			new FrameCalculator().setVisible(true);
		} else {
			String[] toDisplay = list2StringArr(getResult);

			for (String thisLine: toDisplay) {
				display.append(thisLine + NEW_LINE);
			}
		}
	}
	
	public String[] list2StringArr(ArrayList<String> arr) {
		 // Convert ArrayList to object array 
        Object[] objArr = arr.toArray(); 
  
        // convert Object array to String array 
        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class); 
  
        return str; 
	}
}
