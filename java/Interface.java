
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


/**
 * Sets up the GUI. 
 * Creates pages, components and calls search methods to populate
 * the buttons and tables.
 * @author Caitlin Tumulty
 */
public class Interface implements ActionListener, ItemListener {

	public ArrayList<JPanel> panels;
	Stack<JPanel> stack;
	JPanel contentPane;
	JPanel home;
	JPanel libraries;
	JPanel mans;
	JPanel chants;
	JTable table;
	JTable cTable;
	JTable rTable;
	JTable mTable;
	JLabel welcome; 
	JLabel enterCountry;
	JLabel enterCity;
	JLabel enterLibrary;
	JLabel enterGenre;
	JLabel enterFeast;
	JLabel man;
	JLabel man2;
	JLabel chant;
	JLabel chant2;
	JLabel background;
	ImageIcon icon;
	DefaultTableModel libraryTable;
	DefaultTableModel chantTable;
	DefaultTableModel refineTable;
	DefaultTableModel manTable;
	JButton search;
	JButton searchChant;
	JButton back;
	JButton backL;
	JButton refine;
	JButton refineChant;
	JScrollPane libraryHeader;
	JScrollPane chantHeader;
	JScrollPane refineHeader;
	JScrollPane manHeader;
	Choice countryList;
	Choice cityList;
	Choice libraryList;
	Choice genreList;
	Choice feastList;
	Connection conn;
	LibrarySearch libraryQuery = new LibrarySearch();
	ChantSearch chantQuery = new ChantSearch();
	ManuscriptSearch manQuery = new ManuscriptSearch();
	
	/**
	 * Passes the connection to database that was created in Main.java.
	 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Creates pages and page elements.
	 */
	public void run() {
		panels = new ArrayList<JPanel>();
		stack = new Stack<JPanel>();
		JFrame frame;
		JButton librarySearch;
		JButton chantSearch;
		JButton manSearch;
		
		
		Rectangle bounds = new Rectangle(0, 0, 1600, 900);
		
		cityList = new Choice();
		
		//Create Main Frame
		frame = new JFrame("Cantus");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = (JPanel) frame.getContentPane();
	    contentPane.setLayout(null);
	    frame.setSize(1600, 900);
	    
	    //Create Panels
	    home = new JPanel();
	    home.setLayout(null);
	    home.setBounds(bounds);
	    home.setBackground(Color.decode("#e2dbc5"));
	    
	    libraries = new JPanel();
	    libraries.setLayout(null);
	    libraries.setBounds(bounds);
	    libraries.setBackground(Color.decode("#e2dbc5"));
	    
	    chants = new JPanel();
	    chants.setLayout(null);
	    chants.setBounds(bounds);
	    chants.setBackground(Color.decode("#e2dbc5"));
	    
	    mans = new JPanel();
	    mans.setLayout(null);
	    mans.setBounds(bounds);
	    mans.setBackground(Color.decode("#eacfa1"));
	    
	    panels.add(home);
	    panels.add(libraries);
	    panels.add(chants);
	    panels.add(mans);
	    
	    background = new JLabel();
	    background.setBounds(bounds);
	    icon = new ImageIcon("bunny.jpg");
	    ImageIcon icon2 = new ImageIcon(icon.getImage().getScaledInstance(1600, 900, 0));
	    background.setIcon(icon);
	    
	    //Create Labels
	    welcome = new JLabel("Welcome to the Cantus Database!");
	    welcome.setBounds(507, 70, 600, 100);
	    welcome.setForeground(Color.white);
	    welcome.setFont(Font.decode("Arial-BOLD-35"));
	    
	    enterCountry = new JLabel("Select a Country");
	    enterCountry.setBounds(100, 75, 200, 30);
	    
	    enterCity = new JLabel("Select a City");
	    enterCity.setBounds(300, 75, 200, 30);
	    enterCity.setVisible(false);
	    
	    enterLibrary = new JLabel("Select a Library");
	    enterLibrary.setBounds(500, 75, 200, 30);
	    enterLibrary.setVisible(false);
	    
	    enterGenre = new JLabel("Select a Genre");
	    enterGenre.setBounds(100, 75, 200, 30);
	    
	    enterFeast = new JLabel("Select a Feast");
	    enterFeast.setBounds(300, 75, 200, 30);
	    
	    //Create Navigation Buttons
	    librarySearch = new JButton("Search by Library");
	    librarySearch.setBounds(640,225,300,100);
	    librarySearch.setBackground(Color.white);
	    librarySearch.setFont(Font.decode("Arial-20"));
	    librarySearch.setBorder(BorderFactory.createBevelBorder(0));
	    librarySearch.addActionListener(this);
	    
	    chantSearch = new JButton("Search by Chant");
	    chantSearch.setBounds(640,375,300,100);
	    chantSearch.setBackground(Color.white);
	    chantSearch.setBorder(BorderFactory.createBevelBorder(0));
	    chantSearch.setFont(Font.decode("Arial-20"));
	    chantSearch.addActionListener(this);
	    
	    manSearch = new JButton("Search by Manuscript");
	    manSearch.setBounds(300,400,300,100);
	    manSearch.addActionListener(this);
	    
	    back = new JButton("Back");
	    back.setBounds(1400,50,75,30);
	    back.addActionListener(this);
	    back.setVisible(true);
	    
	    backL = new JButton("Back");
	    backL.setBounds(1400,50,75,30);
	    backL.addActionListener(this);
	    backL.setVisible(true);
	    
	    refine = new JButton("Show Chants");
	    refine.setBounds(100, 625, 200, 30);
	    refine.addActionListener(this);
	    refine.setVisible(false);
	    
	    refineChant = new JButton("Show Manuscripts");
	    refineChant.setBounds(100, 625, 200, 30);
	    refineChant.addActionListener(this);
	    refineChant.setVisible(false);
	    
	    search = new JButton("Search");
	    search.setBounds(700,100,100,30);
	    search.addActionListener(this);
	    search.setVisible(false);
	    
	    searchChant = new JButton("Search");
	    searchChant.setBounds(500,100,100,30);
	    searchChant.addActionListener(this);
	    searchChant.setVisible(true);
		
		//Create Result Tables
	    man = new JLabel("Manuscripts in Selected Libraries");
	    man.setBounds(50, 225, 400, 30);
	    man.setVisible(false);
	    
	    chant = new JLabel("Chants in Selected Manuscript");
	    chant.setBounds(50, 225, 400, 30);
	    chant.setVisible(false);
	    
	    libraryTable = new DefaultTableModel();
	    libraryTable.addColumn("country ID");
	    libraryTable.addColumn("libSiglum");
	    libraryTable.addColumn("msSiglum");
	    libraryTable.addColumn("msType");
	    libraryTable.addColumn("leaves");
	    libraryTable.addColumn("foliated");
	    libraryTable.addColumn("vellum");
	    libraryTable.addColumn("binding");
	    libraryTable.addColumn("city");
	    libraryTable.addColumn("library");
	    table = new JTable(libraryTable);
	    table.setBounds(50, 250, 1500, 350);
	    libraryHeader = new JScrollPane(table);
	    libraryHeader.setBounds(50, 250, 1500, 350);
	    libraryHeader.setVisible(false);
	    
	    refineTable = new DefaultTableModel();
	    refineTable.addColumn("Chant ID");
	    refineTable.addColumn("Genre ID");
	    refineTable.addColumn("Feast");
	    refineTable.addColumn("Incipit");
	    rTable = new JTable(refineTable);
	    rTable.setBounds(50, 250, 1500, 350);
	    refineHeader = new JScrollPane(rTable);
	    refineHeader.setBounds(50, 250, 1500, 350);
	    refineHeader.setVisible(false);
	    
	    chant2 = new JLabel("Chants with Selected Genre and Feast");
	    chant2.setBounds(50, 225, 400, 30);
	    chant2.setVisible(false);
	    
	    man2 = new JLabel("Manuscripts with Selected Chant");
	    man2.setBounds(50, 225, 400, 30);
	    man2.setVisible(false);
	    
	    chantTable = new DefaultTableModel();
	    chantTable.addColumn("Chant ID");
	    chantTable.addColumn("Incipit");
	    chantTable.addColumn("Genre");
	    chantTable.addColumn("Feast");
	    cTable = new JTable(chantTable);
	    cTable.setBounds(50, 250, 1500, 350);
	    chantHeader = new JScrollPane(cTable);
	    chantHeader.setBounds(50, 250, 1500, 350);
	    chantHeader.setVisible(false);
	    
	    manTable = new DefaultTableModel();
	    manTable.addColumn("Chant ID");
	    manTable.addColumn("msSiglum");
	    manTable.addColumn("libSiglum");
	    manTable.addColumn("Incipit");
	    manTable.addColumn("Image Link");
	    mTable = new JTable(manTable);
	    mTable.setBounds(50, 250, 1500, 350);
	    manHeader = new JScrollPane(mTable);
	    manHeader.setBounds(50, 250, 1500, 350);
	    manHeader.setVisible(false);
	    
	    //Create Drop-down Buttons
	    countryList = new Choice();
	    countryList.setBounds(100,100,150,30);
	    countryList.setBackground(Color.decode("#ffffff"));
	    countryList.addItemListener(this);
	    ResultSet countries = libraryQuery.getCountries(conn);
	    try {
			while(countries.next()) {
				countryList.addItem(countries.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    countryList.setVisible(true);
	    
	    cityList = new Choice();
	    cityList.setBounds(300,100,150,30);
	    cityList.setBackground(Color.decode("#ffffff"));
	    cityList.addItemListener(this);
	    cityList.setVisible(false);
	    
	    libraryList = new Choice();
	    libraryList.setBounds(500,100,150,30);
	    libraryList.setBackground(Color.decode("#ffffff"));
	    libraryList.addItemListener(this);
	    libraryList.setVisible(false);
	    
	    genreList = new Choice();
	    genreList.setBounds(100,100,150,30);
	    genreList.setBackground(Color.decode("#ffffff"));
	    genreList.addItemListener(this);
	    ResultSet genres = chantQuery.getGenre(conn);
	    try {
			while(genres.next()) {
				genreList.addItem(genres.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    genreList.setVisible(true);
	    
	    feastList = new Choice();
	    feastList.setBounds(300,100,150,30);
	    feastList.setBackground(Color.decode("#ffffff"));
	    feastList.addItemListener(this);
	    ResultSet feasts = chantQuery.getFeasts(conn);
	    try {
			while(feasts.next()) {
				feastList.addItem(feasts.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    feastList.setVisible(true);
	 
	    //Add Elements to Panels
	    libraries.add(backL);
	    libraries.add(countryList);
	    libraries.add(cityList);
	    libraries.add(libraryList);
	    libraries.add(search);
	    libraries.add(libraryHeader);
	    libraries.add(refineHeader);
	    libraries.add(refine);
	    libraries.add(enterCountry);
	    libraries.add(enterCity);
	    libraries.add(enterLibrary);
	    libraries.add(man);
	    libraries.add(chant);
	    
	    
	    chants.add(feastList);
	    chants.add(genreList);
	    chants.add(back);
	    chants.add(searchChant);
	    chants.add(chantHeader);
	    chants.add(manHeader);
	    chants.add(enterGenre);
	    chants.add(enterFeast);
	    chants.add(chant2);
	    chants.add(man2);
	    chants.add(refineChant);
	    
	
	    home.add(welcome);
	    home.add(librarySearch);
	    home.add(chantSearch);
	    home.add(background); 
	   
	    //Add Panels to Content Pane
	    contentPane.add(home);
	    contentPane.add(libraries);
	    contentPane.add(chants);
	    
	    this.setActivePanel(home);
	    
	    frame.setVisible(true);
	    
	}
	
	/**
	 * Switch between pages
	 * @param panel The page to be displayed currently
	 */
	public void setActivePanel(JPanel panel) {
		for(JPanel p : panels) {
			if(p.isVisible()) {
				p.setVisible(false);
				stack.push(p);
			}
		}
		
		panel.setVisible(true);
	}
	
	/**
	 * Back Button
	 */
	public void goBack(){
		
		JPanel panel = (JPanel) stack.pop();
		for(JPanel p : panels) {
			p.setVisible(false);
		}
			
		panel.setVisible(true);
		
	}
	
	
	/**
	 * Button Events
	 * @param event The button that was clicked
	 */
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		
		switch (action) {
			case ("Search by Library"):
				this.setActivePanel(libraries);
				break;
			case ("Search by Chant"):
				this.setActivePanel(chants);
				break;
			case ("Search by Manuscript"):
				this.setActivePanel(mans);
				break;
			case ("Back"):
				this.goBack();
				break;
			case ("Show Chants"):
					libraryHeader.setVisible(false);
					int selectedRow = table.getSelectedRow();
					refineTable.setRowCount(0);
					String lib = libraryTable.getValueAt(selectedRow, 1).toString();
					String ms = libraryTable.getValueAt(selectedRow, 2).toString();
					ResultSet refineResults = manQuery.getManuscriptsRefined(lib, ms, conn);
				try {
					
					while (refineResults.next()) {
						Object [] row = {refineResults.getString(1),refineResults.getString(2),refineResults.getString(3),refineResults.getString(4)};
						refineTable.addRow(row);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				refineHeader.setVisible(true);
				man.setVisible(false);
				chant.setVisible(true);
				break;
			case ("Show Manuscripts"):
				chantHeader.setVisible(false);
				int chantRow = cTable.getSelectedRow();
				manTable.setRowCount(0);
				if(chantRow == -1) {
					System.out.println(" indexing error terming process");
					System.exit(0);
				}
				String chantID = chantTable.getValueAt(chantRow, 0).toString();
				ResultSet manResults = manQuery.getManuscriptFromChant(chantID, conn);
			try {
				
				while (manResults.next()) {
					Object [] row = {manResults.getString(1),manResults.getString(2),manResults.getString(3),
							manResults.getString(4),manResults.getString(5)};
					manTable.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			manHeader.setVisible(true);
			chant2.setVisible(false);
			man2.setVisible(true);
			break;
			case ("Search"):
			if(libraries.isVisible()) {
				libraryTable.setRowCount(0);
				String country = countryList.getSelectedItem();
				String city = cityList.getSelectedItem();
				String library = libraryList.getSelectedItem();
				ResultSet libraryResults = manQuery.getManuscripts(library, city, country, conn);
			try {
				
				while (libraryResults.next()) {
					Object [] row = {libraryResults.getString(1),libraryResults.getString(2),libraryResults.getString(3),libraryResults.getString(4),
							libraryResults.getString(6),libraryResults.getString(7),libraryResults.getString(8),
							libraryResults.getString(9),
							libraryResults.getString(13), libraryResults.getString(14)};
					libraryTable.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			libraryHeader.setVisible(true);
			man.setVisible(true);
			chant.setVisible(false);
			refine.setVisible(true);
			}
			if(chants.isVisible()) {
				chantTable.setRowCount(0);
				String feast = feastList.getSelectedItem();
				String genre = genreList.getSelectedItem();
				ResultSet chantResults = chantQuery.getChants(genre, feast, conn);
			try {
				
				while (chantResults.next()) {
					Object [] row = {chantResults.getString(1), chantResults.getString(2),chantResults.getString(3),chantResults.getString(4)};
					chantTable.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			chantHeader.setVisible(true);
			chant2.setVisible(true);
			refineChant.setVisible(true);
			man2.setVisible(false);
			}
				break;
		}

	}
	
	public void clearChoices(Choice choice) {
			choice.removeAll();
	}

	/**
	 * Dropdown Events
	 * @param e The selected choice from dropdown
	 */
	public void itemStateChanged(ItemEvent e) {
		Choice selected = (Choice) e.getSource();
		if (selected == countryList) {
			String country = countryList.getSelectedItem();
			ResultSet rs = libraryQuery.getCityFromCountry(country, conn);
			clearChoices(cityList);
			try {
				cityList.add("Any");
				while (rs.next()) {
					cityList.addItem(rs.getString(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			cityList.setVisible(true);
			enterCity.setVisible(true);
		}
		if (selected == cityList) {
			String country = countryList.getSelectedItem();
			String city = cityList.getSelectedItem();
			ResultSet rs = libraryQuery.getLibraries(city, country, conn);
			clearChoices(libraryList);
			try {
			    libraryList.add("Any");
				while (rs.next()) {
					libraryList.addItem(rs.getString(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			libraryList.setVisible(true);
			enterLibrary.setVisible(true);
		}
		if (selected == libraryList) {
			search.setVisible(true);
		}

		
	}



}
