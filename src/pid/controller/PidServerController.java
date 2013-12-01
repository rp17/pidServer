package pid.controller;

import pid.ui.PidServerFrame;
import pid.Main;

public class PidServerController {
	private PidServerFrame frame;
	
	public void setView(PidServerFrame frame) {this.frame = frame;}
	public void operation(String op) {
		if(op == PidServerFrame.Close) {
			Main.shutdown();
		}
	}
	

}
