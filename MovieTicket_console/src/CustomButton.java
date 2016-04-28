import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton{
	
	public CustomButton(String name,ImageIcon img_icon,Rectangle r)
	{
		this.setBounds(r);
		this.setText(name);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setBorder(null);
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setContentAreaFilled(false);
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(CENTER);	
		Image img = img_icon.getImage();
		Image newimg = img.getScaledInstance(r.width, r.height,  java.awt.Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(newimg));
	}

}
