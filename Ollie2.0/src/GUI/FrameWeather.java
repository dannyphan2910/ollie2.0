package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FrameWeather extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWeather frame = new FrameWeather();
					frame.setVisible(true);
					frame.setTitle("Weather Application");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameWeather() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 525, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(20, 20, 325, 340);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(353, 48, 166, 32);
		contentPane.add(textField);
		textField.setColumns(100);
		
		JLabel label = new JLabel("ENTER CITY NAME");
		label.setBounds(377, 20, 117, 16);
		contentPane.add(label);
		
		JButton submit = new JButton("SUBMIT");
		submit.setBounds(377, 92, 117, 29);
		contentPane.add(submit);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String city = textField.getText().toLowerCase().trim();
					getWeather(city);
				} catch (Exception ex) {
					textArea.setText("ERROR. TRY AGAIN.");
				}
				
			}
			
		});
		
	}
	
	private void getWeather(String place) {
		ArrayList<String> weatherInfo = weather.WeatherFunction.getWeather(place);
		
		for (String thisInfo: weatherInfo) {
			textArea.append(thisInfo + "\n"); 
		}
	}

}
