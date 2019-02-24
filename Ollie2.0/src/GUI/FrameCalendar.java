package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FrameCalendar extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private Calendar calendar = new GregorianCalendar();
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCalendar frame = new FrameCalendar();
					frame.setVisible(true);
					frame.setTitle("Calendar Application");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameCalendar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 300, 205);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		calendar();

		currentInfo();
	}

	void currentInfo() {
		Calendar today = Calendar.getInstance();
		String todayLine = "Current date: " + today.getTime();
		JLabel dateToday = new JLabel(todayLine);

		JPanel panelInfo = new JPanel();
		panelInfo.add(dateToday);
		contentPane.add(panelInfo, BorderLayout.SOUTH);

	}

	void calendar() {
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JButton lastMonth = new JButton("<<<");
		lastMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				calendar.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});

		JButton nextMonth = new JButton(">>>");
		nextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				calendar.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(lastMonth, BorderLayout.WEST);
		panel.add(label, BorderLayout.CENTER);
		panel.add(nextMonth, BorderLayout.EAST);

		String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		model = new DefaultTableModel(null, columns);
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);

		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(pane, BorderLayout.CENTER);

		updateMonth();
	}

	void updateMonth() {
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = calendar.get(Calendar.YEAR);
		label.setText(month + " " + year);

		int startDay = calendar.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

		model.setRowCount(0);
		model.setRowCount(weeks);

		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			model.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}

	}

}
