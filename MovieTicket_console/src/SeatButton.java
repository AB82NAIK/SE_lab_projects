import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SeatButton extends JButton implements ActionListener{
	
	public boolean selected=false;
	public int button_no;
	private ImageIcon aquired_seat=new ImageIcon(this.getClass().getResource("aquired_seat.png"));
	private ImageIcon deleted_seat=new ImageIcon(this.getClass().getResource("deleted_seat.png"));
	private ImageIcon available_seat=new ImageIcon(this.getClass().getResource("available_seat.png"));
	private ImageIcon selected_seat=new ImageIcon(this.getClass().getResource("selected_seat.png"));
	
	public SeatButton(int button_no,List<Screen> deleted_list,List booked_list)
	{	
		this.addActionListener(this);
		this.setText(Integer.toString(button_no));
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setBorder(null);
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setContentAreaFilled(false);
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(CENTER);
		this.setForeground(Color.BLACK);
		Rectangle r=new Rectangle();
		r.width=100;
		r.height=100;

		this.setBounds(r);
		for(int i=0;i<deleted_list.size();i++)
		{
			if(button_no==deleted_list.get(i).seat_no)
				this.setIcon(deleted_seat);
		}
		Object tempBtn=button_no;
		for(int i=0;i<booked_list.size();i++)
		{
			if(booked_list.get(i).equals(tempBtn))
			{
				this.setIcon(aquired_seat);
			}
		}
		
		if(this.getIcon()==null)
			this.setIcon(available_seat);
		
		this.button_no=button_no;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{

		if(getIcon()==available_seat)
		{
			setIcon(selected_seat);
			this.selected=true;
		}
		
		else if(getIcon()==selected_seat)
		{
			setIcon(available_seat);
			this.selected=false;
		}
	}
	
	

}
