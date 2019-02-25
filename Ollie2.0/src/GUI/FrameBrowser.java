package GUI;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class FrameBrowser extends JFrame {

	private JPanel contentPane;
	private JTextField url;
	private JButton browse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBrowser frame = new FrameBrowser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameBrowser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 85);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(15, 16, 355, 30);
		contentPane.add(scrollPane);

		url = new JTextField();
		scrollPane.setViewportView(url);
		url.setColumns(10);
		
		browse = new JButton("Browse");
		browse.setBounds(376, 16, 117, 29);
		contentPane.add(browse);
		
		browserButton();
		
		getRootPane().setDefaultButton(browse);
	}

	private void browserButton() {
		
			browse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String link = url.getText();
					
					if (link != null) {
						
						try {
							Desktop desktop = Desktop.getDesktop();
							
							if (!link.startsWith("https://")) {
								link = "https://" + link;
							}
							
							URI uri = new URI(link);
							
							desktop.browse(uri);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					

				}
			});
		
	}
	

}
