package pid.ui;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import pid.controller.PidServerController;

public class PidServerFrame extends JFrame {
	public final static String Close = "Close";
	private Handler handler = new Handler();
	private  JPanel topPanel;
	private  JPanel textPanel;
	private  JPanel buttonPanel;
	private  JLabel courseLabel;
	private  JTextPane courseText;
	private  JButton cancelButton;
	private final PidServerController contr;
	
	public PidServerFrame(final PidServerController contr) {
		this.contr = contr;
		this.getContentPane().add(getContent());
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x, y);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent evt) {
		    	contr.operation(PidServerFrame.Close);
		    }
		});
		pack();
	}
	
	private JPanel getContent() {
		if (topPanel == null) {
			topPanel = new JPanel();
			GridBagLayout layout = new GridBagLayout();
			topPanel.setLayout(layout);
			//topPanel.setPreferredSize(new Dimension(300, 100));
			GridBagConstraints ps = new GridBagConstraints();
			ps.gridx = 0;
			ps.gridy = 0;
			ps.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints bs = new GridBagConstraints();
			bs.gridx = 0;
			bs.gridy = 1;
			bs.fill = GridBagConstraints.HORIZONTAL;
			topPanel.add(getTextFieldPanel(), ps);
			topPanel.add(getButtonPanel(), bs);
		}
		return topPanel;
	}
	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			GridBagConstraints bl = new GridBagConstraints();
			bl.gridx = 0;
			bl.gridy = 0;
			bl.fill = GridBagConstraints.HORIZONTAL;
			bl.insets = new Insets(15,15,15,5);
			
			GridBagConstraints bf = new GridBagConstraints();
			bf.gridx = 1;
			bf.gridy = 0;
			bf.fill = GridBagConstraints.HORIZONTAL;
			bf.insets = new Insets(15,5,15,15);
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			//textPanel.setPreferredSize(new Dimension(250, 50));
			courseLabel = new JLabel();
			courseLabel.setText("Course: ");
			courseLabel.setPreferredSize(new Dimension(50, 20));
			courseText = new JTextPane();
			courseText.setText("");
			courseText.setPreferredSize(new Dimension(50, 20));
			courseText.setEditable(false);
			textPanel.add(courseLabel, bl);
			textPanel.add(courseText, bf);
		}
		return textPanel;
	}
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			GridBagConstraints clButtonCtr = new GridBagConstraints();
			clButtonCtr.gridx = 0;
			clButtonCtr.gridy = 0;
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			cancelButton = new JButton(Close);
			cancelButton.addActionListener(handler);
			buttonPanel.add(cancelButton, clButtonCtr);
		}
		
		return buttonPanel;
	}
	public void setCourseText(int course) {
		courseText.setText(Integer.toString(course));
	}
	public void setCourseText(String msg) {
		courseText.setText(msg);
	}
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource() == cancelButton) {
				contr.operation(PidServerFrame.Close);
			}
		}
	}
}
