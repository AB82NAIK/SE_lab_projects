import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Button;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;
import java.awt.Color;


public class MovieTicketMain {
	CustomMethods cm=new CustomMethods();
	String master_user="deep";
	String master_pass="deep1234";

	private JFrame frmMovieticket;
	private JTextField textFieldLoginUserName;
	private JPasswordField passwordFieldLoginPass;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldNewPass;
	private JPasswordField passwordFieldConfirmPass;
	private JPanel loginPanel;
	private JPanel createUserPanel;
	private JPanel movieEventPanel;
	private JPanel moviePanel;
	private JPanel selectSeatPanel;
	private JPanel screenPanel;
	private JTable table_event;
	private JTextField textFieldEventId;
	private JSpinner timeSpinnerStartTime;
	private JSpinner timeSpinnerDuration;
	private JTextField textFieldPrice;
	private JLabel lblEventId;
	private JLabel lblMovieName;
	private JLabel lblStartTime;
	private JLabel lblScreenNumber;
	private JLabel lblPrice;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnSearch;
	private JTable tableMovie;
	private JTextField textFieldMovieId;
	private JTextField textFieldMovieName;
	private JTextField textFieldRatting;
	private JTextField textFieldGenere;
	private JTextField textFieldReleaseDate;
	private JButton btnAddMovie;
	private JButton btnDeleteMovie;
	private JButton btnUpdateMovie;
	private JButton btnBack;
	private JButton btnBackCreateUser;
	private JDateChooser dateChooserSearchDate;
	private JDateChooser dateChooserEventDate;
	private JComboBox comboBoxScreenNo;
	private JComboBox comboBoxMovieNames;
	private JDateChooser dateChooserReleaseDate;
	private JDateChooser dateChooserMovieSearch;
	private JComboBox comboBoxScreenType;
	private JList listAvailableSeats;
	private JList listDeletedSeats;
	private JButton btnUndoDelete;
	private JButton btnDeleteSeats;
	private CustomButton btnBook;
	private JButton btnSelectSeats;
	private JSpinner spinnerScreenNo;
	private JButton btnAquiredImg;
	private JButton btnDeletedImg;
	private JButton btnAvailableImg;
	private JButton btnSelectedImg;
	private JButton btnScreen;
	private JButton btnMovie;
	private JTextField textFieldLanguage;
	private JButton btnShowAll_1;
	private JTable table_screen;
	private JTextField textFieldScreenType;
	private JTextField textFieldTotalSeats;
	private JPanel panel;
	private JScrollPane scrollPane_5;
	private JButton btnBackSeatSelect;
	private JButton btnUpcoming;
	private JLabel labelBackground2;
	private JLabel labelBackground3;
	private JLabel labelBackground4;
	private JLabel labelBackground5;
	private JLabel labelBackground6;
	private JLabel label_1;
	private JLabel label_2;

	DefaultTableModel model_event=new DefaultTableModel();
	DefaultTableModel model_movie=new DefaultTableModel();
	DefaultTableModel model_screen=new DefaultTableModel();
	
	private ImageIcon background_img=new ImageIcon(this.getClass().getResource("background.png"));
	private ImageIcon logo_img=new ImageIcon(this.getClass().getResource("logo.png"));
	private ImageIcon aquired_seat_img=new ImageIcon(this.getClass().getResource("aquired_seat.png"));
	private ImageIcon deleted_seat_img=new ImageIcon(this.getClass().getResource("deleted_seat.png"));
	private ImageIcon available_seat_img=new ImageIcon(this.getClass().getResource("available_seat.png"));
	private ImageIcon selected_seat_img=new ImageIcon(this.getClass().getResource("selected_seat.png"));
	private ImageIcon big_button_img=new ImageIcon(this.getClass().getResource("big_button.png"));
	private ImageIcon small_button_img=new ImageIcon(this.getClass().getResource("small_button.png"));
	private ImageIcon left_button_img=new ImageIcon(this.getClass().getResource("left.png"));
	private ImageIcon right_button_img=new ImageIcon(this.getClass().getResource("right.png"));
	private ImageIcon gold_line_img=new ImageIcon(this.getClass().getResource("gold_line.png"));
	
	Image img = gold_line_img.getImage();
	Image newimg = img.getScaledInstance(1024,200,java.awt.Image.SCALE_SMOOTH);
	
	Image img2=small_button_img.getImage();
	Image small_img_scld = img2.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
	
	
	
	DefaultComboBoxModel movie_names = new DefaultComboBoxModel();
	DefaultComboBoxModel screen_no = new DefaultComboBoxModel();
	DefaultComboBoxModel screen_type = new DefaultComboBoxModel();
	
	DefaultListModel model_deleted_seats=new DefaultListModel();
	DefaultListModel model_available_seats=new DefaultListModel();
	
	List<SeatButton> button_list=new ArrayList<SeatButton>();
	SeatButton seat_button;
	
	List<Movie> movie_list=new ArrayList<Movie>();
	
	List<MovieEvent> event_list=new ArrayList<MovieEvent>();
	
	List<Screen> screen_list=new ArrayList<Screen>();
	
	List<Integer> selected_seats=new ArrayList<Integer>();
	
	SignUp signup;
	
	int selected_row_screen=-1;
	int selected_row_event=-1;
	int selected_row_movie=-1;
	
	int selected_event=-1;
	
	int row=0;
	int column=5;
	
	Rectangle big_ico_width_height=new Rectangle(0,0,270,120);
	Rectangle small_ico_width_height=new Rectangle(0,0,120,40);
	Rectangle info_ico_width_height=new Rectangle(0,0,64,64);
	Rectangle direction_ico_width_height=new Rectangle(0,0,128,128);
	
	Connection con=null;
	
	
	
	Object[] column_identifier_event={"EVENT ID","MOVIE NAME","START TIME","END TIME","SCREEN","PRICE","SEATS AVAILABLE"};
	Object[] column_identifier_movie={"MOVIE ID","MOVIE NAME","RATTING","GENERE","DURATION","RELEASE DATE","SCREEN TYPE","LANGUAGE"};
	Object[] column_identifier_screen={"SCREEN NO","SCREEN TYPE","TOTAL SEATS","DELETED SEATS","AVAILABLE SEATS"};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieTicketMain window = new MovieTicketMain();
					window.frmMovieticket.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MovieTicketMain() {

			initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		if(!testDb())
		{
			JOptionPane.showMessageDialog(null,
	                cm.report,
	                "ERROR",
	                JOptionPane.ERROR_MESSAGE);
		}

		frmMovieticket = new JFrame();
		frmMovieticket.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMovieticket.setTitle("MovieTicket");
		frmMovieticket.setIconImage(logo_img.getImage());
		frmMovieticket.setResizable(false);
		frmMovieticket.setBounds(10, 10, 1024, 700);
		frmMovieticket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMovieticket.getContentPane().setLayout(new CardLayout(0, 0));
		//-------------------------------------LOGIN PANEL------------------------------------------------------------------------------//
		loginPanel = new JPanel();
		frmMovieticket.getContentPane().add(loginPanel, "name_100184523749030");
		loginPanel.setLayout(null);
		
		textFieldLoginUserName = new JTextField();
		textFieldLoginUserName.setForeground(Color.BLACK);
		textFieldLoginUserName.setBackground(Color.WHITE);
		textFieldLoginUserName.setBounds(681, 222, 250, 40);
		loginPanel.add(textFieldLoginUserName);
		textFieldLoginUserName.setColumns(10);
		
		passwordFieldLoginPass = new JPasswordField();
		passwordFieldLoginPass.setForeground(Color.BLACK);
		passwordFieldLoginPass.setBackground(Color.WHITE);
		passwordFieldLoginPass.setBounds(681, 275, 250, 40);
		loginPanel.add(passwordFieldLoginPass);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserName.setBounds(551, 220, 120, 40);
		loginPanel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBackground(Color.LIGHT_GRAY);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(551, 273, 120, 40);
		loginPanel.add(lblPassword);
		
		CustomButton btnLogin =new CustomButton("Login",small_button_img,small_ico_width_height);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				LogIn login=new LogIn(textFieldLoginUserName.getText(),passwordFieldLoginPass.getText());
				if(login.sucess)
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            login.report,
                            "SUCESS",
                            JOptionPane.INFORMATION_MESSAGE);
					if(login.admin)
					{
						btnMovie.setVisible(true);
						btnScreen.setVisible(true);
						btnAdd.setEnabled(true);
						btnDelete.setEnabled(true);
						btnUpdate.setEnabled(true);
					}
					else
					{
						btnMovie.setVisible(false);
						btnScreen.setVisible(false);
						btnAdd.setEnabled(false);
						btnDelete.setEnabled(false);
						btnUpdate.setEnabled(false);
					}
					loginPanel.setVisible(false);
					movieEventPanel.setVisible(true);
					refreshEventTable(cm.getSystemDateTime(),false,true);
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            login.report,
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(681, 345, 120, 40);
		loginPanel.add(btnLogin);
		
		CustomButton btnSignUp = new CustomButton("Sign Up",small_button_img,small_ico_width_height);
		btnSignUp.setForeground(Color.BLACK);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn login=new LogIn(textFieldLoginUserName.getText(),passwordFieldLoginPass.getText());
				if(textFieldLoginUserName.getText().equals(master_user)&&passwordFieldLoginPass.getText().equals(master_pass) || login.admin )
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            "Welcome "+textFieldLoginUserName.getText(),
                            "SUCESS",
                            JOptionPane.INFORMATION_MESSAGE);
					loginPanel.setVisible(false);
					createUserPanel.setVisible(true);	
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            "Enter Master Credentials",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSignUp.setBounds(811, 345, 120, 40);
		loginPanel.add(btnSignUp);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(logo_img);
		labelLogo.setBounds(10, -70, 569, 649);
		loginPanel.add(labelLogo);
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setIcon(background_img);
		labelBackground.setBounds(0, 0, 1018, 671);
		loginPanel.add(labelBackground);
		//-----------------------------------------------------------CREATE USER PANEL---------------------------------------------------------//
		createUserPanel = new JPanel();
		frmMovieticket.getContentPane().add(createUserPanel, "name_100200751244356");
		createUserPanel.setLayout(null);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setForeground(Color.BLACK);
		textFieldUserName.setBackground(Color.WHITE);
		textFieldUserName.setBounds(495, 208, 250, 40);
		createUserPanel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		passwordFieldNewPass = new JPasswordField();
		passwordFieldNewPass.setForeground(Color.BLACK);
		passwordFieldNewPass.setBackground(Color.WHITE);
		passwordFieldNewPass.setBounds(495, 274, 250, 40);
		createUserPanel.add(passwordFieldNewPass);
		
		passwordFieldConfirmPass = new JPasswordField();
		passwordFieldConfirmPass.setForeground(Color.BLACK);
		passwordFieldConfirmPass.setBackground(Color.WHITE);
		passwordFieldConfirmPass.setBounds(495, 338, 250, 40);
		createUserPanel.add(passwordFieldConfirmPass);
		
		CustomButton btnCreateUser = new CustomButton("Create User",small_button_img,small_ico_width_height);
		btnCreateUser.setForeground(Color.BLACK);
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				signup=new SignUp();
				signup.addUser(textFieldUserName.getText(), passwordFieldConfirmPass.getText(),passwordFieldNewPass.getText(),false);
				
				if(signup.sucess)
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            signup.report,
                            "SUCESS",
                            JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            signup.report,
                            "PASSWORD ERROR",
                            JOptionPane.ERROR_MESSAGE);
				}
		

			}
		});
		btnCreateUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreateUser.setBounds(632, 389, 120, 40);
		createUserPanel.add(btnCreateUser);
		
		JLabel lblUserName_1 = new JLabel("User Name");
		lblUserName_1.setForeground(Color.BLACK);
		lblUserName_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserName_1.setBounds(290, 208, 180, 40);
		createUserPanel.add(lblUserName_1);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewPassword.setBounds(290, 272, 180, 40);
		createUserPanel.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(Color.BLACK);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfirmPassword.setBounds(290, 336, 180, 40);
		createUserPanel.add(lblConfirmPassword);
		
		btnBackCreateUser = new CustomButton("Back",small_button_img,small_ico_width_height);
		btnBackCreateUser.setForeground(Color.BLACK);
		btnBackCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordFieldLoginPass.setText(null);
				createUserPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		btnBackCreateUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBackCreateUser.setBounds(10, 627, 120, 40);
		createUserPanel.add(btnBackCreateUser);
		
		JButton btnCreateAdmin = new CustomButton("Create Admin",small_button_img,small_ico_width_height);
		btnCreateAdmin.setForeground(Color.BLACK);
		btnCreateAdmin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCreateAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				signup=new SignUp();
				signup.addUser(textFieldUserName.getText(), passwordFieldConfirmPass.getText(),passwordFieldNewPass.getText(),true);
				
				if(signup.sucess)
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            signup.report,
                            "SUCESS",
                            JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            signup.report,
                            "PASSWORD ERROR",
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCreateAdmin.setBounds(495, 389, 120, 40);
		createUserPanel.add(btnCreateAdmin);
		
		labelBackground2 = new JLabel("");
		labelBackground2.setIcon(background_img);
		labelBackground2.setBounds(0, 0, 1018, 671);
		createUserPanel.add(labelBackground2);
		//-------------------------------------------------------MOVIE EVENT PANEL-----------------------------------------------------------------//
	
		updateMovieNamesComboBox();
		
		Movie movie=new Movie();
		movie_list.clear();
		movie_list=movie.getMovie(-1,movie_names.getElementAt(0).toString(),null);
		String[] release_date=movie_list.get(0).release_date.toString().split(" ");
		String initial_screen_type=movie_list.get(0).screen_type;
		
		setScreenNoComboBox(initial_screen_type);
		
		movieEventPanel = new JPanel();
		movieEventPanel.setBackground(Color.LIGHT_GRAY);
		frmMovieticket.getContentPane().add(movieEventPanel, "name_100219304578962");
		movieEventPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 998, 331);
		scrollPane.setBackground(Color.LIGHT_GRAY);
		movieEventPanel.add(scrollPane);
		
		table_event = new JTable();
		table_event.setForeground(Color.BLACK);
		table_event.setBackground(Color.WHITE);
		model_event.setColumnIdentifiers(column_identifier_event);
		table_event.setModel(model_event);
		scrollPane.setViewportView(table_event);
		
		table_event.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				selected_row_event=table_event.getSelectedRow();
				MovieEvent movie_event=new MovieEvent();
				Movie movie=new Movie();
				
				movie_list.clear();
				event_list.clear();
				
				textFieldEventId.setText(model_event.getValueAt(selected_row_event, 0).toString());
				event_list=movie_event.getMovieEvent(Integer.parseInt(model_event.getValueAt(selected_row_event, 0).toString()),-1,null);
				movie_list=movie.getMovie(event_list.get(0).movie_id,null, null);
				Object movie_name=movie_list.get(0).name;

				comboBoxMovieNames.setSelectedItem(movie_name);
				
				dateChooserEventDate.setDate(event_list.get(0).start_time);
				timeSpinnerStartTime.setValue(event_list.get(0).start_time);
				
				Object screen_no=event_list.get(0).screen_no;
				comboBoxScreenNo.setSelectedItem(screen_no);
				
				textFieldPrice.setText(Float.toString(event_list.get(0).price));
				setScreenNoComboBox(movie_list.get(0).screen_type);
				
				if(Integer.parseInt(model_event.getValueAt(selected_row_event, 6).toString())>0)
				{
					btnSelectSeats.setEnabled(true);
				}
				else
				{
					btnSelectSeats.setEnabled(false);
				}
				
				
			}
		});
		
		textFieldEventId = new JTextField();
		textFieldEventId.setForeground(Color.BLACK);
		textFieldEventId.setBackground(Color.WHITE);
		textFieldEventId.setBounds(36, 438, 80, 20);
		textFieldEventId.setEditable(false);
		movieEventPanel.add(textFieldEventId);
		textFieldEventId.setColumns(10);
		
		timeSpinnerStartTime = new JSpinner( new SpinnerDateModel() );
		timeSpinnerStartTime.setForeground(Color.BLACK);
		timeSpinnerStartTime.setBackground(Color.WHITE);
		JSpinner.DateEditor de_timeSpinnerStartTime = new JSpinner.DateEditor(timeSpinnerStartTime, "HH:mm");
		timeSpinnerStartTime.setEditor(de_timeSpinnerStartTime);
		timeSpinnerStartTime.setValue(new Date()); 
		timeSpinnerStartTime.setBounds(678, 438, 100, 20);
		movieEventPanel.add(timeSpinnerStartTime);
		

		
		textFieldPrice = new JTextField();
		textFieldPrice.setForeground(Color.BLACK);
		textFieldPrice.setBackground(Color.WHITE);
		textFieldPrice.setBounds(898, 438, 100, 20);
		movieEventPanel.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		lblEventId = new JLabel("EVENT ID");
		lblEventId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEventId.setForeground(Color.BLACK);
		lblEventId.setBackground(Color.LIGHT_GRAY);
		lblEventId.setBounds(36, 413, 80, 14);
		movieEventPanel.add(lblEventId);
		
		lblMovieName = new JLabel("MOVIE NAME");
		lblMovieName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMovieName.setBackground(Color.LIGHT_GRAY);
		lblMovieName.setForeground(Color.BLACK);
		lblMovieName.setBounds(126, 413, 130, 14);
		movieEventPanel.add(lblMovieName);
		
		lblStartTime = new JLabel("START TIME");
		lblStartTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStartTime.setForeground(Color.BLACK);
		lblStartTime.setBounds(678, 413, 100, 14);
		movieEventPanel.add(lblStartTime);
		
		lblScreenNumber = new JLabel("SCREEN");
		lblScreenNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScreenNumber.setForeground(Color.BLACK);
		lblScreenNumber.setBounds(788, 413, 100, 14);
		movieEventPanel.add(lblScreenNumber);
		
		lblPrice = new JLabel("PRICE");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setBounds(898, 413, 100, 14);
		movieEventPanel.add(lblPrice);
		
		comboBoxMovieNames = new JComboBox(movie_names);
		comboBoxMovieNames.setForeground(Color.BLACK);
		comboBoxMovieNames.setBackground(Color.WHITE);
		comboBoxMovieNames.setSelectedIndex(0);
		comboBoxMovieNames.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Object selected_mv_name=comboBoxMovieNames.getSelectedItem();
				if(selected_mv_name!=null)
				{
					Movie movie2=new Movie();
					List<Movie> movie_list2=new ArrayList<Movie>();
					movie_list2=movie2.getMovie(-1,selected_mv_name.toString(),null);
					String[] release_date=movie_list2.get(0).release_date.toString().split(" ");
					textFieldReleaseDate.setText(release_date[0]);
					setScreenNoComboBox(movie_list2.get(0).screen_type);
				}
			}
		});

		comboBoxMovieNames.setBounds(126, 438, 280, 20);
		movieEventPanel.add(comboBoxMovieNames);
		
		btnAdd = new CustomButton("Add",small_button_img,small_ico_width_height);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validEventInput())
				{
					cm=new CustomMethods();
					MovieEvent movie_event=new MovieEvent();
					Movie movie=new Movie();
					movie_list.clear();
					movie_list=movie.getMovie(-1, comboBoxMovieNames.getSelectedItem().toString(),null);
					movie_event.movie_id=movie_list.get(0).id;
					SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
					String[] event_date=ft.format(dateChooserEventDate.getDate()).toString().split(" ");
					String[] event_time=timeSpinnerStartTime.getValue().toString().split(" ");
					movie_event.start_time=cm.stringToTimeStamp(event_date[0]+" "+event_time[3]);
					movie_event.screen_no=Integer.parseInt(comboBoxScreenNo.getSelectedItem().toString());
					movie_event.price=Float.parseFloat(textFieldPrice.getText());
					
					movie_event.createMovieEvent(movie_event);
					if(movie_event.sucess)
					{
						JOptionPane.showMessageDialog(frmMovieticket,
								movie_event.report,
	                            "SUCESS",
	                            JOptionPane.INFORMATION_MESSAGE);
						refreshEventTable(cm.getSystemDateTime(),false,true);
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
	                            movie_event.report,
	                            "ERROR",
	                            JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
	                        "Provide Valid Input",
	                        "ERROR",
	                        JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnAdd.setBounds(10, 503, 109, 36);
		movieEventPanel.add(btnAdd);
		
		btnDelete = new CustomButton("Delete",small_button_img,small_ico_width_height);
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String delete_message="Deleting Event Will Delete Releated All Ticket Info\n Continue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,delete_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					if(validEventInput())
					{
						MovieEvent movie_event=new MovieEvent();
						
						movie_event.deleteMovieEvent(Integer.parseInt(model_event.getValueAt(selected_row_event, 0).toString()));
						
						if(movie_event.sucess)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
		                            movie_event.report,
		                            "SUCESS",
		                            JOptionPane.INFORMATION_MESSAGE);
							refreshEventTable(cm.getSystemDateTime(),false,true);
						}
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
		                            movie_event.report,
		                            "ERROR",
		                            JOptionPane.ERROR_MESSAGE);
						}
	
	
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
		                        "Select Record First",
		                        "ERROR",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.setBounds(120, 503, 109, 36);
		movieEventPanel.add(btnDelete);
		
		btnUpdate = new CustomButton("Update",small_button_img,small_ico_width_height);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String update_message=" Event Updation May Result In Conflicts With Customers Booking \n Continue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,update_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					if(validEventInput())
					{
						cm=new CustomMethods();
						MovieEvent movie_event=new MovieEvent();
						Movie movie=new Movie();
						event_list.clear();
						movie_list.clear();
						movie_event.id=Integer.parseInt(model_event.getValueAt(selected_row_event, 0).toString());
						
						movie_list=movie.getMovie(-1,comboBoxMovieNames.getSelectedItem().toString() , null);
						movie_event.movie_id=movie_list.get(0).id;
						System.out.println("selected mv id"+movie_event.movie_id);
						
						SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
						String[] start_date=ft.format(dateChooserEventDate.getDate()).toString().split(" ");
						String[] event_time=timeSpinnerStartTime.getValue().toString().split(" ");
						movie_event.start_time=cm.stringToTimeStamp(start_date[0]+" "+event_time[3]);
						
						movie_event.screen_no=Integer.parseInt(comboBoxScreenNo.getSelectedItem().toString());
						
						movie_event.price=Float.parseFloat(textFieldPrice.getText());
						movie_event.updateMovieEvent(movie_event);
						
						if(movie_event.sucess)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
									 movie_event.report,
		                            "SUCESS",
		                            JOptionPane.INFORMATION_MESSAGE);
							refreshEventTable(cm.getSystemDateTime(),false,true);
						}
						
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
		                            movie_event.report,
		                            "ERROR",
		                            JOptionPane.ERROR_MESSAGE);
						}
						
	
						}
					
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
		                        "Provide Valid Input",
		                        "ERRORs",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});
		btnUpdate.setBounds(230, 503, 109, 36);
		movieEventPanel.add(btnUpdate);
		
		btnSearch = new CustomButton("Search",small_button_img,small_ico_width_height);
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cm=new CustomMethods();
				SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
				String search_date=ft.format(dateChooserSearchDate.getDate()).toString();
				refreshEventTable(cm.stringToTimeStamp(search_date),false,false);
			}
		});
		btnSearch.setBounds(467, 503, 109, 36);
		movieEventPanel.add(btnSearch);
		
		dateChooserSearchDate = new JDateChooser();
		dateChooserSearchDate.setIcon(new ImageIcon(small_img_scld));
		dateChooserSearchDate.setBackground(Color.LIGHT_GRAY);
		dateChooserSearchDate.setBounds(349, 503, 109, 36);
		dateChooserSearchDate.setDate(cm.getSystemDateTime());
		movieEventPanel.add(dateChooserSearchDate);
		
		dateChooserEventDate = new JDateChooser();
		dateChooserEventDate.setForeground(Color.BLACK);
		dateChooserEventDate.setBackground(Color.WHITE);
		dateChooserEventDate.setBounds(548, 438, 120, 20);
		dateChooserEventDate.setDate(cm.getSystemDateTime());
		movieEventPanel.add(dateChooserEventDate);
		
		JLabel lblDate = new JLabel("START DATE");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setForeground(Color.BLACK);
		lblDate.setBounds(548, 413, 120, 14);
		movieEventPanel.add(lblDate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 551, 988, 6);
		movieEventPanel.add(separator);
		
		JLabel lblSettings = new JLabel("SETTINGS");
		lblSettings.setForeground(Color.BLACK);
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSettings.setBounds(10, 584, 100, 30);
		movieEventPanel.add(lblSettings);
		
		btnScreen = new CustomButton("Screen",small_button_img,small_ico_width_height);
		btnScreen.setForeground(Color.BLACK);
		btnScreen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movieEventPanel.setVisible(false);
				screenPanel.setVisible(true);
				refreshScreenTable();
			}
		});
		btnScreen.setBounds(10, 624, 109, 36);
		movieEventPanel.add(btnScreen);
		
		btnMovie = new CustomButton("Movie",small_button_img,small_ico_width_height);
		btnMovie.setForeground(Color.BLACK);
		btnMovie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				movieEventPanel.setVisible(false);
				moviePanel.setVisible(true);
				refreshMovieTable(cm.getSystemDateTime(),true);
			}
		});
		btnMovie.setBounds(120, 624, 109, 36);
		movieEventPanel.add(btnMovie);
		
		btnSelectSeats = new CustomButton("Select Seats",big_button_img,big_ico_width_height);
		btnSelectSeats.setForeground(Color.BLACK);
		btnSelectSeats.setEnabled(false);
		btnSelectSeats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				cm=new CustomMethods();
				selected_event=Integer.parseInt(model_event.getValueAt(selected_row_event, 0).toString());
				MovieEvent movie_event=new MovieEvent();
				event_list.clear();
				event_list=movie_event.getMovieEvent(selected_event,-1, null);
	
				if(cm.getSystemDateTime().compareTo(event_list.get(0).start_time)>0)
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            "Choose Event Greater or Equal to Current date",
                            "FAILED",
                            JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					movieEventPanel.setVisible(false);
					selectSeatPanel.setVisible(true);
					btnBook.setEnabled(true);
					createSeatButtons(selected_event,event_list.get(0).screen_no);	
				}
				
				
			}
		});
		btnSelectSeats.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSelectSeats.setBounds(738, 551, 270, 120);
		movieEventPanel.add(btnSelectSeats);
		
		JButton btnLogOut = new CustomButton("LogOut",small_button_img,small_ico_width_height);
		btnLogOut.setForeground(Color.BLACK);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movieEventPanel.setVisible(false);
				loginPanel.setVisible(true);
				textFieldLoginUserName.setText(null);
				passwordFieldLoginPass.setText(null);
			}
		});
		btnLogOut.setBounds(889, 10, 109, 36);
		movieEventPanel.add(btnLogOut);
		
		comboBoxScreenNo = new JComboBox(screen_no);
		comboBoxScreenNo.setForeground(Color.BLACK);
		comboBoxScreenNo.setBackground(Color.WHITE);
		comboBoxScreenNo.setBounds(788, 438, 100, 20);
		movieEventPanel.add(comboBoxScreenNo);
		


		
		textFieldReleaseDate = new JTextField();
		textFieldReleaseDate.setForeground(Color.BLACK);
		textFieldReleaseDate.setBackground(Color.WHITE);
		textFieldReleaseDate.setBounds(418, 438, 120, 20);
		textFieldReleaseDate.setText(release_date[0]);
		textFieldReleaseDate.setEditable(false);
		movieEventPanel.add(textFieldReleaseDate);
		textFieldReleaseDate.setColumns(10);
		
		JLabel lblReleaseDate = new JLabel("RELEASE DATE");
		lblReleaseDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReleaseDate.setBackground(Color.LIGHT_GRAY);
		lblReleaseDate.setForeground(Color.BLACK);
		lblReleaseDate.setBounds(418, 413, 120, 14);
		movieEventPanel.add(lblReleaseDate);
		
		btnShowAll_1 = new CustomButton("Show All",small_button_img,small_ico_width_height);
		btnShowAll_1.setForeground(Color.BLACK);
		btnShowAll_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnShowAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				refreshEventTable(cm.getSystemDateTime(),true,false);
			}
		});
		btnShowAll_1.setBounds(577, 503, 109, 36);
		movieEventPanel.add(btnShowAll_1);
		
		btnUpcoming = new CustomButton("Upcoming",small_button_img,small_ico_width_height);
		btnUpcoming.setForeground(Color.BLACK);
		btnUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpcoming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				refreshEventTable(cm.getSystemDateTime(),false,true);
			}
		});
		btnUpcoming.setBounds(687, 503, 109, 36);
		movieEventPanel.add(btnUpcoming);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(newimg));
		label.setBounds(0, 386, 1018, 95);
		movieEventPanel.add(label);
		
		labelBackground3 = new JLabel("");
		labelBackground3.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelBackground3.setBackground(Color.LIGHT_GRAY);
		labelBackground3.setIcon(background_img);
		labelBackground3.setBounds(0, 0, 1018, 671);
		movieEventPanel.add(labelBackground3);
		


		//-------------------------------------------------------MOVIE PANEL----------------------------------------------------------------------//
		Screen screen=new Screen();
		screen_type.removeAllElements();
		screen_list=screen.getScreen(-1,null);
		List tmp_screen_type=new ArrayList();
		for(int i=0;i<screen_list.size();i++)
		{
			tmp_screen_type.add(screen_list.get(i).screen_type);
		}
		List tmp_screen_type2=new ArrayList(new HashSet(tmp_screen_type));
		for(int i=0;i<tmp_screen_type2.size();i++)
		{
			screen_type.addElement(tmp_screen_type2.get(i));
		}
		
		
		
		model_movie.setColumnIdentifiers(column_identifier_movie);
		
		moviePanel = new JPanel();
		moviePanel.setBackground(Color.LIGHT_GRAY);
		frmMovieticket.getContentPane().add(moviePanel, "name_100236228331351");
		moviePanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 998, 372);
		moviePanel.add(scrollPane_1);
		
		tableMovie = new JTable();
		tableMovie.setForeground(Color.BLACK);
		tableMovie.setBackground(Color.WHITE);
		tableMovie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cm=new CustomMethods();
				selected_row_movie=tableMovie.getSelectedRow();
				textFieldMovieId.setText(model_movie.getValueAt(selected_row_movie, 0).toString());
				textFieldMovieName.setText(model_movie.getValueAt(selected_row_movie, 1).toString());
				textFieldRatting.setText(model_movie.getValueAt(selected_row_movie, 2).toString());
				textFieldGenere.setText(model_movie.getValueAt(selected_row_movie, 3).toString());
				String duration="2016-11-11 "+model_movie.getValueAt(selected_row_movie, 4).toString();
				timeSpinnerDuration.setValue(cm.stringToTimeStamp(duration));
				String release_date=model_movie.getValueAt(selected_row_movie, 5).toString();
				dateChooserReleaseDate.setDate(cm.stringToTimeStamp(release_date));
				textFieldLanguage.setText(model_movie.getValueAt(selected_row_movie, 7).toString());
				comboBoxScreenType.setSelectedItem(model_movie.getValueAt(selected_row_movie, 6));
				
			}
		});
		tableMovie.setModel(model_movie);
		scrollPane_1.setViewportView(tableMovie);
		
		textFieldMovieId = new JTextField();
		textFieldMovieId.setForeground(Color.BLACK);
		textFieldMovieId.setBackground(Color.WHITE);
		textFieldMovieId.setBounds(10, 448, 86, 20);
		textFieldMovieId.setEnabled(false);
		moviePanel.add(textFieldMovieId);
		textFieldMovieId.setColumns(10);
		
		textFieldMovieName = new JTextField();
		textFieldMovieName.setForeground(Color.BLACK);
		textFieldMovieName.setBackground(Color.WHITE);
		textFieldMovieName.setBounds(106, 448, 200, 20);
		moviePanel.add(textFieldMovieName);
		textFieldMovieName.setColumns(10);
		
		textFieldRatting = new JTextField();
		textFieldRatting.setForeground(Color.BLACK);
		textFieldRatting.setBackground(Color.WHITE);
		textFieldRatting.setBounds(316, 448, 90, 20);
		moviePanel.add(textFieldRatting);
		textFieldRatting.setColumns(10);
		
		textFieldGenere = new JTextField();
		textFieldGenere.setForeground(Color.BLACK);
		textFieldGenere.setBackground(Color.WHITE);
		textFieldGenere.setBounds(416, 448, 90, 20);
		moviePanel.add(textFieldGenere);
		textFieldGenere.setColumns(10);
		
		timeSpinnerDuration = new JSpinner( new SpinnerDateModel() );
		timeSpinnerDuration.setForeground(Color.BLACK);
		timeSpinnerDuration.setBackground(Color.WHITE);
		JSpinner.DateEditor de_timeSpinnerDuration = new JSpinner.DateEditor(timeSpinnerDuration, "HH:mm:ss");
		timeSpinnerDuration.setEditor(de_timeSpinnerDuration);
		cm=new CustomMethods();
		timeSpinnerDuration.setValue(cm.stringToTimeStamp("2016-9-4 1:10:40"));
		timeSpinnerDuration.setBounds(516, 448, 100, 20);
		moviePanel.add(timeSpinnerDuration);
		
		dateChooserReleaseDate = new JDateChooser();
		dateChooserReleaseDate.setForeground(Color.BLACK);
		dateChooserReleaseDate.setBackground(Color.WHITE);
		cm=new CustomMethods();
		dateChooserReleaseDate.setDate(cm.getSystemDateTime());
		dateChooserReleaseDate.setBounds(626, 448, 100, 20);
		moviePanel.add(dateChooserReleaseDate);
		
		comboBoxScreenType = new JComboBox(screen_type);
		comboBoxScreenType.setForeground(Color.BLACK);
		comboBoxScreenType.setBackground(Color.WHITE);
		comboBoxScreenType.setBounds(736, 448, 80, 20);
		moviePanel.add(comboBoxScreenType);
		
		btnAddMovie = new CustomButton("Add",small_button_img,small_ico_width_height);
		btnAddMovie.setForeground(Color.BLACK);
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validMovieInput())
				{
					cm=new CustomMethods();
					Movie movie=new Movie();
					movie.name=textFieldMovieName.getText();
					movie.ratting=textFieldRatting.getText();
					movie.genere=textFieldGenere.getText();
					String[] duration=timeSpinnerDuration.getValue().toString().split(" ");
					duration=duration[3].split(":");
					movie.duration[0]=duration[0];
					movie.duration[1]=duration[1];
					SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
					String release_date=ft.format(dateChooserReleaseDate.getDate()).toString();
					movie.release_date=cm.stringToTimeStamp(release_date);
					movie.screen_type=comboBoxScreenType.getSelectedItem().toString();
					movie.language=textFieldLanguage.getText();
					
					movie.addMovie(movie);
					if(movie.sucess)
					{
					JOptionPane.showMessageDialog(frmMovieticket,
	                        movie.report,
	                        "SUCESS",
	                        JOptionPane.INFORMATION_MESSAGE);
					refreshMovieTable(cm.getSystemDateTime(),true);
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
								movie.report,
		                        "ERROR",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
	                        "Provide Valid Input",
	                        "ERROR",
	                        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddMovie.setBounds(10, 514, 109, 36);
		moviePanel.add(btnAddMovie);
		
		btnDeleteMovie = new CustomButton("Delete",small_button_img,small_ico_width_height);
		btnDeleteMovie.setForeground(Color.BLACK);
		btnDeleteMovie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				String delete_message="Deleting Movie Will Remove All Events Related To It\n Continue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,delete_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					if(validMovieInput())
					{
						Movie movie=new Movie();
						movie.deleteMovie(Integer.parseInt(model_movie.getValueAt(selected_row_movie,0).toString()));
						if(movie.sucess)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        movie.report,
			                        "SUCESS",
			                        JOptionPane.INFORMATION_MESSAGE);
							refreshMovieTable(cm.getSystemDateTime(),true);
							
						}
						
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        movie.report,
			                        "ERROR",
			                        JOptionPane.ERROR_MESSAGE);
						}
	
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
		                        "Select Record First",
		                        "FAILED",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDeleteMovie.setBounds(139, 514, 109, 36);
		moviePanel.add(btnDeleteMovie);
		
		btnUpdateMovie = new CustomButton("Update",small_button_img,small_ico_width_height);
		btnUpdateMovie.setForeground(Color.BLACK);
		btnUpdateMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String update_message="Updating Movie Will Be Afective For New Events Only\n Continue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,update_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					if(validMovieInput())
					{
						cm=new CustomMethods();
						Movie movie =new Movie();
						movie_list.clear();
						movie.id=Integer.parseInt(model_movie.getValueAt(selected_row_movie, 0).toString());
						movie.name=textFieldMovieName.getText();
						movie.ratting=textFieldRatting.getText();
						movie.genere=textFieldGenere.getText();
						String[] duration=timeSpinnerDuration.getValue().toString().split(" ");
						duration=duration[3].split(":");
						movie.duration[0]=duration[0];
						movie.duration[1]=duration[1];
						SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
						String release_date=ft.format(dateChooserReleaseDate.getDate()).toString();
						movie.release_date=cm.stringToTimeStamp(release_date);
						movie.screen_type=comboBoxScreenType.getSelectedItem().toString();
						movie.language=textFieldLanguage.getText();
						
						movie.updateMovie(movie);
						if(movie.sucess)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        movie.report,
			                        "SUCESS",
			                        JOptionPane.INFORMATION_MESSAGE);
							refreshMovieTable(cm.getSystemDateTime(),true);
						}
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        movie.report,
			                        "ERROR",
			                        JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
		                        "Provide Valid Input",
		                        "ERROR",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});
		btnUpdateMovie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateMovie.setBounds(269, 514, 109, 36);
		moviePanel.add(btnUpdateMovie);
		
		dateChooserMovieSearch = new JDateChooser();
		dateChooserMovieSearch.setIcon(new ImageIcon(small_img_scld));
		dateChooserMovieSearch.setBackground(Color.LIGHT_GRAY);
		dateChooserMovieSearch.setDate(cm.getSystemDateTime());
		dateChooserMovieSearch.setBounds(399, 514, 109, 36);
		moviePanel.add(dateChooserMovieSearch);
		
		JButton btnSearch_1 = new CustomButton("Search",small_button_img,small_ico_width_height);
		btnSearch_1.setForeground(Color.BLACK);
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
				String release_date=ft.format(dateChooserMovieSearch.getDate()).toString();
				refreshMovieTable(cm.stringToTimeStamp(release_date),false);
			}
		});
		btnSearch_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch_1.setBounds(529, 514, 109, 36);
		moviePanel.add(btnSearch_1);
		
		JLabel lblMovieId = new JLabel("MOVIE ID");
		lblMovieId.setForeground(Color.BLACK);
		lblMovieId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMovieId.setBounds(10, 423, 86, 14);
		moviePanel.add(lblMovieId);
		
		JLabel lblRatting = new JLabel("RATTING");
		lblRatting.setForeground(Color.BLACK);
		lblRatting.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRatting.setBounds(316, 423, 90, 14);
		moviePanel.add(lblRatting);
		
		JLabel lblGenere = new JLabel("GENERE");
		lblGenere.setForeground(Color.BLACK);
		lblGenere.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenere.setBounds(416, 423, 90, 14);
		moviePanel.add(lblGenere);
		
		JLabel lblDuration = new JLabel("DURATION");
		lblDuration.setForeground(Color.BLACK);
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuration.setBounds(516, 423, 100, 14);
		moviePanel.add(lblDuration);
		
		JLabel lblReleseDate = new JLabel("RELESE DATE");
		lblReleseDate.setForeground(Color.BLACK);
		lblReleseDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReleseDate.setBounds(626, 423, 100, 14);
		moviePanel.add(lblReleseDate);
		
		JLabel lblScreen = new JLabel("SCREEN");
		lblScreen.setForeground(Color.BLACK);
		lblScreen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScreen.setBounds(736, 423, 80, 14);
		moviePanel.add(lblScreen);
		
		JLabel lblLanguage = new JLabel("LANGUAGE");
		lblLanguage.setForeground(Color.BLACK);
		lblLanguage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLanguage.setBounds(824, 423, 170, 14);
		moviePanel.add(lblLanguage);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(106, 423, 200, 14);
		moviePanel.add(lblName);
		
		btnBack = new CustomButton("Back",small_button_img,small_ico_width_height);
		btnBack.setForeground(Color.BLACK);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				btnSelectSeats.setEnabled(false);
				moviePanel.setVisible(false);
				movieEventPanel.setVisible(true);
				updateMovieNamesComboBox();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(7, 627, 109, 36);
		moviePanel.add(btnBack);
		
		textFieldLanguage = new JTextField();
		textFieldLanguage.setForeground(Color.BLACK);
		textFieldLanguage.setBackground(Color.WHITE);
		textFieldLanguage.setBounds(826, 448, 168, 20);
		moviePanel.add(textFieldLanguage);
		textFieldLanguage.setColumns(10);
		
		JButton btnShowAll = new CustomButton("Show All",small_button_img,small_ico_width_height);
		btnShowAll.setForeground(Color.BLACK);
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				refreshMovieTable(cm.getSystemDateTime(),true);
			}
		});
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnShowAll.setBounds(659, 514, 109, 36);
		moviePanel.add(btnShowAll);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(newimg));
		label_1.setBounds(0, 394, 1018, 95);
		moviePanel.add(label_1);
		
		labelBackground4 = new JLabel("");
		labelBackground4.setIcon(background_img);
		labelBackground4.setBounds(0, 0, 1018, 671);
		moviePanel.add(labelBackground4);
		//-------------------------------------------------------SCREEN PANEL--------------------------------------------------------------------//
		screenPanel = new JPanel();
		frmMovieticket.getContentPane().add(screenPanel, "name_100260115566437");
		screenPanel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 988, 350);
		screenPanel.add(scrollPane_2);
		
		table_screen = new JTable();
		table_screen.setForeground(Color.BLACK);
		table_screen.setBackground(Color.WHITE);
		table_screen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected_row_screen=table_screen.getSelectedRow();
				btnDeleteSeats.setEnabled(true);
				btnUndoDelete.setEnabled(true);
				Object screen_no=model_screen.getValueAt(selected_row_screen, 0);
				spinnerScreenNo.setValue(screen_no);
				textFieldScreenType.setText(model_screen.getValueAt(selected_row_screen, 1).toString());
				textFieldTotalSeats.setText(model_screen.getValueAt(selected_row_screen, 2).toString());

				setDeleteAvailableSeatList(selected_row_screen);


			}
		});
		

	

		model_screen.setColumnIdentifiers(column_identifier_screen);
		table_screen.setModel(model_screen);
		scrollPane_2.setViewportView(table_screen);
		
		textFieldScreenType = new JTextField();
		textFieldScreenType.setForeground(Color.BLACK);
		textFieldScreenType.setBackground(Color.WHITE);
		textFieldScreenType.setBounds(120, 426, 100, 20);
		screenPanel.add(textFieldScreenType);
		textFieldScreenType.setColumns(10);
		
		textFieldTotalSeats = new JTextField();
		textFieldTotalSeats.setForeground(Color.BLACK);
		textFieldTotalSeats.setBackground(Color.WHITE);
		textFieldTotalSeats.setBounds(230, 426, 100, 20);
		screenPanel.add(textFieldTotalSeats);
		textFieldTotalSeats.setColumns(10);
		
		JLabel lblScreenNo = new JLabel("SCREEN NO");
		lblScreenNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScreenNo.setForeground(Color.BLACK);
		lblScreenNo.setBounds(10, 401, 100, 14);
		screenPanel.add(lblScreenNo);
		
		JLabel lblScreenType = new JLabel("SCREEN TYPE");
		lblScreenType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScreenType.setForeground(Color.BLACK);
		lblScreenType.setBounds(120, 401, 100, 14);
		screenPanel.add(lblScreenType);
		
		JLabel lblTotalSeats = new JLabel("TOTAL SEATS");
		lblTotalSeats.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalSeats.setForeground(Color.BLACK);
		lblTotalSeats.setBounds(230, 401, 100, 14);
		screenPanel.add(lblTotalSeats);
		
		JButton btnAdd_1 = new CustomButton("Add",small_button_img,small_ico_width_height);
		btnAdd_1.setForeground(Color.BLACK);
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean error=false;
				if(validScreenInput())
				{
					Screen screen=new Screen();
					screen.screen_no=Integer.parseInt(spinnerScreenNo.getValue().toString());
					screen.screen_type=textFieldScreenType.getText();
					screen.total_seats=Integer.parseInt(textFieldTotalSeats.getText());
					
					screen.setScreen(screen);
					if(screen.sucess)
					{
						JOptionPane.showMessageDialog(frmMovieticket,
	                            screen.report,
	                            "SUCESS",
	                            JOptionPane.INFORMATION_MESSAGE);
						refreshScreenTable();
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
	                            screen.report,
	                            "ERROR",
	                            JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
	                        "Provide Valid Input",
	                        "ERROR",
	                        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd_1.setBounds(10, 489, 109, 36);
		screenPanel.add(btnAdd_1);
		
		JButton btnDelete_1 = new CustomButton("Delete",small_button_img,small_ico_width_height);
		btnDelete_1.setForeground(Color.BLACK);
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delete_message="Deleting Screen Will Remove All Related Event Data\n Continue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,delete_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					if(validScreenInput())
					{
						Screen screen=new Screen();
						screen.deleteScreen(Integer.parseInt(model_screen.getValueAt(selected_row_screen, 0).toString()));
						if(screen.sucess)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        screen.report,
			                        "SUCESS",
			                        JOptionPane.INFORMATION_MESSAGE);
							refreshScreenTable();
						}
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        screen.report,
			                        "ERROR",
			                        JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
		                        "Select Record First",
		                        "ERROR",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete_1.setBounds(120, 489, 109, 36);
		screenPanel.add(btnDelete_1);
		
		JButton btnUpdate_1 = new CustomButton("Update",small_button_img,small_ico_width_height);
		btnUpdate_1.setForeground(Color.BLACK);
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String update_message="Screen Updation Effect Will Be Reflected In New Event Only. \nContinue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,update_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					if(validScreenInput())
					{
						Screen screen=new Screen();
						screen.screen_no=Integer.parseInt(model_screen.getValueAt(selected_row_screen, 0).toString());
						screen.screen_type=textFieldScreenType.getText();
						screen.total_seats=Integer.parseInt(textFieldTotalSeats.getText());
						screen.updateScreen(screen);
						if(screen.sucess)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        screen.report,
			                        "SUCESS",
			                        JOptionPane.INFORMATION_MESSAGE);	
							setDeleteAvailableSeatList(selected_row_screen);
							refreshScreenTable();	
						}
						
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
			                        screen.report,
			                        "ERROR",
			                        JOptionPane.ERROR_MESSAGE);	
						}
	
					}
					else
					{
						JOptionPane.showMessageDialog(frmMovieticket,
		                        "Provide Valide Input",
		                        "ERROR",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate_1.setBounds(230, 489, 109, 36);
		screenPanel.add(btnUpdate_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(402, 392, 150, 258);
		screenPanel.add(scrollPane_3);
		
		listAvailableSeats = new JList();
		listAvailableSeats.setForeground(Color.BLACK);
		listAvailableSeats.setBackground(Color.WHITE);
		listAvailableSeats .setModel(model_available_seats);
		listAvailableSeats.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_3.setViewportView(listAvailableSeats);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(692, 392, 150, 258);
		screenPanel.add(scrollPane_4);
		
		listDeletedSeats = new JList();
		listDeletedSeats.setForeground(Color.BLACK);
		listDeletedSeats.setBackground(Color.WHITE);
		listDeletedSeats.setModel(model_deleted_seats);
		scrollPane_4.setViewportView(listDeletedSeats);
		listDeletedSeats .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		JLabel lblAvailableSeats = new JLabel("AVAILABLE SEATS");
		lblAvailableSeats.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvailableSeats.setForeground(Color.BLACK);
		lblAvailableSeats.setBounds(402, 372, 150, 14);
		screenPanel.add(lblAvailableSeats);
		
		JLabel lblDeletedSeats = new JLabel("DELETED SEATS");
		lblDeletedSeats.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDeletedSeats.setForeground(Color.BLACK);
		lblDeletedSeats.setBounds(692, 372, 150, 14);
		screenPanel.add(lblDeletedSeats);

		btnDeleteSeats = new CustomButton("",right_button_img,direction_ico_width_height);
		btnDeleteSeats.setEnabled(false);
		btnDeleteSeats.setToolTipText("DELETE SEATS");
		btnDeleteSeats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String delete_message="Seat Deletion Will Remove Seats Which Are Not Booked \nContinue?";
				int result = JOptionPane.showConfirmDialog(frmMovieticket,delete_message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION)
				{
					int[] selectedIx=listAvailableSeats.getSelectedIndices();
					
					int screen_no=Integer.parseInt(model_screen.getValueAt(selected_row_screen, 0).toString());
					List deleting_seat=new ArrayList();
					List valid_seat=new ArrayList();
					
					for(int i=0;i<selectedIx.length;i++)
					{
					int seat=Integer.parseInt(listAvailableSeats.getModel().getElementAt(selectedIx[i]).toString());
					deleting_seat.add(seat);
					}
					
					Screen screen=new Screen();
					valid_seat=screen.validateDeleteSeats(deleting_seat,screen_no);
					
					for(int i=0;i<valid_seat.size();i++)
					{
						screen=new Screen();
						screen.deleteSeats(Integer.parseInt(valid_seat.get(i).toString()),screen_no);
					}
					String delete_complete_message=valid_seat.size()+"/"+selectedIx.length+" seats are deleted";
					JOptionPane.showMessageDialog(frmMovieticket,
							delete_complete_message,
	                        "SUCESS",
	                        JOptionPane.INFORMATION_MESSAGE);
					setDeleteAvailableSeatList(selected_row_screen);
					refreshScreenTable();
				}
			}
		});
		btnDeleteSeats.setBounds(577, 409, 80, 100);
		screenPanel.add(btnDeleteSeats);
		
		btnUndoDelete = new CustomButton("",left_button_img,direction_ico_width_height);
		btnUndoDelete.setEnabled(false);
		btnUndoDelete.setToolTipText("UNDO DELETE");
		btnUndoDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedIx=listDeletedSeats.getSelectedIndices();

				int screen_no=Integer.parseInt(model_screen.getValueAt(selected_row_screen, 0).toString());
				
				for(int i=0;i<selectedIx.length;i++)
				{
				int seat=Integer.parseInt(listDeletedSeats.getModel().getElementAt(selectedIx[i]).toString());
				Screen screen=new Screen();
				screen.undoDeleteSeat(seat,screen_no);
				}
				setDeleteAvailableSeatList(selected_row_screen);
				refreshScreenTable();
			}
		});
		
		btnUndoDelete.setBounds(577, 532, 80, 100);
		screenPanel.add(btnUndoDelete);
		
		JButton btnBack_1 = new CustomButton("Back",small_button_img,small_ico_width_height);
		btnBack_1.setForeground(Color.BLACK);
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm=new CustomMethods();
				refreshEventTable(cm.getSystemDateTime(),false,true);
				updateMovieNamesComboBox();
				btnSelectSeats.setEnabled(false);
				screenPanel.setVisible(false);
				movieEventPanel.setVisible(true);

			}
		});
		btnBack_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack_1.setBounds(10, 624, 109, 36);
		screenPanel.add(btnBack_1);
		
		spinnerScreenNo = new JSpinner();
		spinnerScreenNo.setForeground(Color.BLACK);
		spinnerScreenNo.setBackground(Color.LIGHT_GRAY);
		spinnerScreenNo.setBounds(10, 426, 100, 20);
		screenPanel.add(spinnerScreenNo);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(newimg));
		label_2.setBounds(0, 372, 339, 95);
		screenPanel.add(label_2);
		
		labelBackground5 = new JLabel("");
		labelBackground5.setIcon(background_img);
		labelBackground5.setBounds(0, 0, 1018, 671);
		screenPanel.add(labelBackground5);
		//-----------------------------------------------------SEAT SELECT PANEL------------------------------------------------------------------//
		
		selectSeatPanel = new JPanel();
		frmMovieticket.getContentPane().add(selectSeatPanel, "name_100274391100067");
		selectSeatPanel.setLayout(null);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 11, 998, 489);
		selectSeatPanel.add(scrollPane_5);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		scrollPane_5.setViewportView(panel);
		panel.setLayout(new GridLayout(row,column,2,2));
	

		btnBook =new CustomButton("BOOK",big_button_img,big_ico_width_height); //new JButton("BOOK");
		btnBook.setForeground(Color.BLACK);
		btnBook.setText("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_seats.clear();
				for(int i=0;i<button_list.size();i++)
				{
					if(button_list.get(i).selected)
					{
						selected_seats.add(button_list.get(i).button_no);
					}
				}
				
				if(selected_seats.size()>0 && selected_event>=0)
				{
					MovieEvent movie_event=new MovieEvent();
					event_list.clear();
					
					event_list=movie_event.getMovieEvent(selected_event, -1,null);
					double total=selected_seats.size()*event_list.get(0).price;
					String message=" EVENT IDt : "+event_list.get(0).id+"\n MOVIE NAME : "+model_event.getValueAt(selected_row_event, 1)+"\n TICKETS : "+selected_seats.size()
							+"\n PRICE : "+event_list.get(0).price+"\n TOTAL : "+total+"\n Continue?";
					int result = JOptionPane.showConfirmDialog(frmMovieticket,message,"Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(result==JOptionPane.YES_OPTION)
					{
						boolean error=false;
						String report=null;
						Ticket ticket;
						for(int i=0;i<selected_seats.size();i++)
						{
							ticket=new Ticket();
							ticket.bookTicket(selected_event, selected_seats.get(i));
						    if(!ticket.sucess)
						    {
						    	error=true;
						    	report=ticket.report;
						    	break;
						    }
						    else
						    	report=ticket.report;
						}
						if(!error)
						{
							JOptionPane.showMessageDialog(frmMovieticket,
		                            report,
		                            "SUCESS",
		                            JOptionPane.INFORMATION_MESSAGE);
							btnBook.setEnabled(false);
						}
						else
						{
							JOptionPane.showMessageDialog(frmMovieticket,
		                            report,
		                            "ERROR",
		                            JOptionPane.ERROR_MESSAGE);	
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frmMovieticket,
                            "Select Seat First",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnBook.setBounds(741, 550, 267, 110);
		selectSeatPanel.add(btnBook);
		
		btnBackSeatSelect = new CustomButton("Back",small_button_img,small_ico_width_height);
		btnBackSeatSelect.setForeground(Color.BLACK);
		btnBackSeatSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshEventTable(cm.getSystemDateTime(),false,true);
				selectSeatPanel.setVisible(false);
				movieEventPanel.setVisible(true);
				button_list.clear();
				btnBook.setEnabled(true);
				panel.removeAll();		}
		});
		btnBackSeatSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBackSeatSelect.setBounds(10, 624, 109, 36);
		selectSeatPanel.add(btnBackSeatSelect);
		
		btnDeletedImg = new CustomButton("",deleted_seat_img,info_ico_width_height);
		btnDeletedImg.setBounds(212, 511, 60, 60);
		selectSeatPanel.add(btnDeletedImg);
		
		btnAquiredImg = new CustomButton("",aquired_seat_img,info_ico_width_height);
		btnAquiredImg.setBounds(377, 514, 60, 60);
		selectSeatPanel.add(btnAquiredImg);
		
		btnSelectedImg = new CustomButton("",selected_seat_img,info_ico_width_height);
		btnSelectedImg.setBounds(377, 603, 60, 60);
		selectSeatPanel.add(btnSelectedImg);
		
		btnAvailableImg = new CustomButton("",available_seat_img,info_ico_width_height);
		btnAvailableImg.setBounds(212, 603, 60, 60);
		selectSeatPanel.add(btnAvailableImg);
		
		JLabel lblDeleted = new JLabel("DELETED");
		lblDeleted.setBackground(Color.WHITE);
		lblDeleted.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDeleted.setForeground(Color.BLACK);
		lblDeleted.setBounds(272, 535, 80, 14);
		selectSeatPanel.add(lblDeleted);
		
		JLabel lblAvailable = new JLabel("AVAILABLE");
		lblAvailable.setBackground(Color.WHITE);
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvailable.setForeground(Color.BLACK);
		lblAvailable.setBounds(272, 627, 80, 14);
		selectSeatPanel.add(lblAvailable);
		
		JLabel lblBooked = new JLabel("BOOKED");
		lblBooked.setBackground(Color.WHITE);
		lblBooked.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBooked.setForeground(Color.BLACK);
		lblBooked.setBounds(437, 535, 80, 14);
		selectSeatPanel.add(lblBooked);
		
		JLabel lblSelected = new JLabel("SELECTED");
		lblSelected.setBackground(Color.WHITE);
		lblSelected.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelected.setForeground(Color.BLACK);
		lblSelected.setBounds(437, 627, 80, 14);
		selectSeatPanel.add(lblSelected);
		
		labelBackground6 = new JLabel("");
		labelBackground6.setIcon(background_img);
		labelBackground6.setBounds(0, 0, 1028, 671);
		selectSeatPanel.add(labelBackground6);
	}
	
	
	public void refreshEventTable(java.sql.Timestamp date,boolean all,boolean latest)
	{
		cm=new CustomMethods();
		model_event.setRowCount(0);
		Object[] row=new Object[7];
		String[] dateString=date.toString().split(" ");
		MovieEvent movie_event=new MovieEvent();
		Movie movie=new Movie();
		event_list.clear();
		movie_event=new MovieEvent();
		
		event_list=movie_event.getMovieEvent(-1,-1,null);
		
		for(int i=0;i<event_list.size();i++)
		{
			movie_event=new MovieEvent();
			movie_event.updateAvailableSeats(event_list.get(i).id);
		}
		
		movie=new Movie();
		movie_event=new MovieEvent();
		movie_list.clear();
		event_list.clear();
		
		movie_list=movie.getMovie(-1,null,null);
		if(!all && !latest)
		{
			event_list=movie_event.getMovieEvent(-1, -1,dateString[0]);
		}
		else
		{
			event_list=movie_event.getMovieEvent(-1,-1,null);
		}

		
		for(int i=0;i<event_list.size();i++)
		{
			row[0]=event_list.get(i).id;
			for(int k=0;k<movie_list.size();k++)
			{
				if(movie_list.get(k).id==event_list.get(i).movie_id)
				{
					row[1]=movie_list.get(k).name;
				}
			}
			row[2]=event_list.get(i).start_time;
			row[3]=event_list.get(i).end_time;
			row[4]=event_list.get(i).screen_no;
			row[5]=event_list.get(i).price;
			row[6]=event_list.get(i).seats_available;
			if(!all && latest)
			{
				if(cm.getSystemDateTime().compareTo(event_list.get(i).start_time)<0)
				{
					model_event.addRow(row);
				}
			}
			else
			{
				model_event.addRow(row);
			}
		}

		
		
	}
	public void updateMovieNamesComboBox()
	{
		movie_list.clear();
		Movie movie=new Movie();
		movie_list=movie.getMovie(-1, null,null);
		movie_names.removeAllElements();
		for(int i=0;i<movie_list.size();i++)
		{
			movie_names.addElement(movie_list.get(i).name);
		}

				
		
	}
	public void setScreenNoComboBox(String screen_type)
	{
		Screen screen=new Screen();
		screen_list.clear();
		screen_list=screen.getScreen(-1,screen_type);
		screen_no.removeAllElements();
		for(int i=0;i<screen_list.size();i++)
		{
			screen_no.addElement(screen_list.get(i).screen_no);
		}
	}
	
	public void refreshMovieTable(java.sql.Timestamp date,boolean all)
	{
	
		model_movie.setRowCount(0);
		Object[] row=new Object[8];
		Movie movie=new Movie();
		movie_list.clear();
		String[] dateString=date.toString().split(" ");
		dateString=dateString[0].split("-");
		String[] month_year={dateString[1],dateString[0]};
		
		if(all)
		{
			movie_list=movie.getMovie(-1,null, null);
		}
		else
		{
			movie_list=movie.getMovie(-1,null, month_year);
		}
		for(int i=0;i<movie_list.size();i++)
		{
			row[0]=movie_list.get(i).id;
			row[1]=movie_list.get(i).name;
			row[2]=movie_list.get(i).ratting;
			row[3]=movie_list.get(i).genere;
			String[] mduration=movie_list.get(i).duration;
			row[4]=mduration[0]+":"+mduration[1];
			row[5]=movie_list.get(i).release_date;
			row[6]=movie_list.get(i).screen_type;
			row[7]=movie_list.get(i).language;
			model_movie.addRow(row);
			
		}
	}
	
	public void refreshScreenTable()
	{
		model_screen.setRowCount(0);
		
		Screen screen1=new Screen();
		screen_list.clear();
		
		List<Screen> screen_list2=new ArrayList<Screen>();
		
		Object[] row=new Object[5];
		screen_list=screen1.getScreen(-1,null);
		
		for(int i=0;i<screen_list.size();i++)
		{
			
			row[0]=screen_list.get(i).screen_no;
			row[1]=screen_list.get(i).screen_type;
			row[2]=screen_list.get(i).total_seats;
			
			screen_list2.clear();
			Screen screen2=new Screen();
			screen_list2=screen2.getDeletedSeats(screen_list.get(i).screen_no);
			
			row[3]=screen_list2.size();
			
			Screen screen3=new Screen();
			row[4]=screen3.getAvailableSeats(screen_list.get(i).screen_no);
			
			model_screen.addRow(row);
		}
	}
	
	public void setDeleteAvailableSeatList(int selected_row)
	{
		model_deleted_seats.clear();
		model_available_seats.clear();
		Screen screen=new Screen();
		screen_list.clear();
		screen_list=screen.getDeletedSeats(Integer.parseInt(model_screen.getValueAt(selected_row, 0).toString()));
		for(int i=0;i<screen_list.size();i++)
		{
			model_deleted_seats.addElement(screen_list.get(i).seat_no);
		}
		
		List available_seat_no=new ArrayList();
		available_seat_no=screen.getAvailableSeatNo(Integer.parseInt(model_screen.getValueAt(selected_row, 0).toString()));
		for(int i=0;i<available_seat_no.size();i++)
		{
			model_available_seats.addElement(available_seat_no.get(i));
		}
	}
	
	public void createSeatButtons(int event_id,int screen_no)
	{
		Screen screen=new Screen();
		screen_list.clear();
		screen_list=screen.getScreen(screen_no, null);
		int total_seats=screen_list.get(0).total_seats;
		List<Integer> booked_list=new ArrayList<Integer>();
		screen_list=screen.getDeletedSeats(screen_no);
		
		Ticket ticket=new Ticket();
		booked_list=ticket.getBookedTickets(event_id);
		
		button_list.clear();
		for(int i=1;i<=total_seats;i++)
		{
			button_list.add(new SeatButton(i,screen_list,booked_list));
		}
		
		
		for(int i=0;i<button_list.size();i++)
		{
			panel.add(button_list.get(i));
		}	
	}
	
	public boolean validEventInput()
	{
		
		if(textFieldPrice.getText().isEmpty())
			return false;
		else if(!isFloat(textFieldPrice.getText()))
			return false;
		else
			return true;
	}
	
	public boolean validMovieInput()
	{
		if(textFieldMovieName.getText().isEmpty())
			return false;
		else if(textFieldGenere.getText().isEmpty())
			return false;
		else if(textFieldRatting.getText().isEmpty())
			return false;
		else if(textFieldLanguage.getText().isEmpty())
			return false;
		else
			return true;
	}
	public boolean validScreenInput()
	{
		if(textFieldScreenType.getText().isEmpty())
			return false;
		else if(textFieldTotalSeats.getText().isEmpty())
			return false;
		else if(!isInteger(textFieldTotalSeats.getText()))
			return false;
		else if(!isInteger(spinnerScreenNo.getValue().toString()))
			return false;
		else
			return true;

	}
	
	public boolean isInteger(String s)
	{
		try
		{
			int a=Integer.parseInt(s);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean isFloat(String s)
	{
		try
		{
			float a=Float.parseFloat(s);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public JButton removeButtonBackground(JButton button)
	{
		button.setBorder(null);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		return button;
	}
	
	public boolean testDb()
	{
		cm=new CustomMethods();
		cm.connectDb();
		return cm.sucess;
	}
}
