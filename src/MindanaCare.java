import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MindanaCare {

    // SHARED COLORS (Public so DoctorPage can use them)
    public static final Color BRAND_BLUE = Color.decode("#3B82F6");
    public static final Color DARK_BLUE = Color.decode("#1E3A8A");
    public static final Color BG_LIGHT = Color.decode("#F0F6FF");
    public static final Color TEXT_DARK = Color.decode("#1F2937");
    public static final Color TEXT_GRAY = Color.decode("#6B7280");
    public static final Color BORDER_GRAY = Color.decode("#E5E7EB");

    static JPanel contentArea;
    static ArrayList<String> appointments = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mindana Care");
        frame.setSize(1280, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainWrapper = new JPanel(new BorderLayout());
        mainWrapper.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(mainWrapper);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        frame.add(scrollPane);

        // --- NAVBAR ---
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(Color.WHITE);
        navbar.setPreferredSize(new Dimension(1280, 80));
        navbar.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, BORDER_GRAY),
            new EmptyBorder(15, 40, 15, 40)
        ));

        JLabel logo = new JLabel("MINDANA CARE");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(DARK_BLUE);
        navbar.add(logo, BorderLayout.WEST);

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        menuPanel.setBackground(Color.WHITE);
        
        JButton btnHome = createNavButton("Home");
        JButton btnDoc = createNavButton("Doctor");
        // Other buttons...
        menuPanel.add(btnHome);
        menuPanel.add(createNavButton("User"));
        menuPanel.add(btnDoc);
        menuPanel.add(createNavButton("Appointments"));
        menuPanel.add(createNavButton("About"));
        menuPanel.add(createNavButton("Contact"));
        
        navbar.add(menuPanel, BorderLayout.CENTER);

        JPanel authPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        authPanel.setBackground(Color.WHITE);
        authPanel.add(createRoundedButton("Login", Color.WHITE, TEXT_DARK, true));
        authPanel.add(createRoundedButton("Sign in", BRAND_BLUE, Color.WHITE, false));
        navbar.add(authPanel, BorderLayout.EAST);

        mainWrapper.add(navbar, BorderLayout.NORTH);

        // --- CONTENT AREA ---
        contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(Color.WHITE);
        mainWrapper.add(contentArea, BorderLayout.CENTER);

        // Default Load
        loadPage(createHomePanel());

        // --- NAVIGATION LOGIC ---
        btnHome.addActionListener(e -> loadPage(createHomePanel()));
        
        // HERE WE CALL THE OTHER FILE
        btnDoc.addActionListener(e -> loadPage(DoctorPage.createPanel()));

        frame.setVisible(true);
    }

    public static void loadPage(JPanel newPage) {
        contentArea.removeAll();
        contentArea.add(newPage, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
    }

    // --- HOME PANEL ---
    public static JPanel createHomePanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(new EmptyBorder(20, 20, 20, 20)); 

        JPanel heroContent = createRoundedPanel(40, BG_LIGHT, false); 
        heroContent.setLayout(new GridLayout(1, 2, 20, 0)); 
        heroContent.setBorder(new EmptyBorder(40, 50, 40, 50)); 
        heroContent.setPreferredSize(new Dimension(1100, 600)); 

        // LEFT
        JPanel leftCol = new JPanel();
        leftCol.setLayout(new BoxLayout(leftCol, BoxLayout.Y_AXIS));
        leftCol.setOpaque(false); 

        JLabel title1 = new JLabel("Your Health,");
        title1.setFont(new Font("Segoe UI", Font.BOLD, 50));
        title1.setForeground(BRAND_BLUE);
        JLabel title2 = new JLabel("Our Priority");
        title2.setFont(new Font("Segoe UI", Font.BOLD, 50));
        title2.setForeground(Color.BLACK);

        JTextArea desc = new JTextArea("Experience accessible, reliable healthcare through University of Mindanao's digital appointment system.");
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        desc.setForeground(TEXT_GRAY);
        desc.setWrapStyleWord(true); desc.setLineWrap(true);
        desc.setOpaque(false); desc.setEditable(false);
        desc.setMaximumSize(new Dimension(600, 80)); 
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        btnRow.setOpaque(false);
        btnRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRow.setMaximumSize(new Dimension(800, 60));
        
        JButton btnBook = createRoundedButton("📅 Book Appointment ->", BRAND_BLUE, Color.WHITE, false);
        btnBook.setPreferredSize(new Dimension(220, 45));
        btnBook.addActionListener(e -> {
             String r = JOptionPane.showInputDialog("Reason for visit?");
             if(r!=null) appointments.add(r);
        });
        JButton btnFind = createRoundedButton("👤 Find Doctors", Color.WHITE, BRAND_BLUE, true);
        btnFind.setPreferredSize(new Dimension(160, 45));
        btnRow.add(btnBook); btnRow.add(btnFind);

        JPanel statsRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        statsRow.setOpaque(false);
        statsRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsRow.add(createStatCard("20+", "Medical\nProfessionals"));
        statsRow.add(createStatCard("500+", "Student\nConsultation"));
        statsRow.add(createStatCard("24/7", "System\nAccess Support"));

        leftCol.add(title1); leftCol.add(title2);
        leftCol.add(Box.createRigidArea(new Dimension(0, 15)));
        leftCol.add(desc); leftCol.add(Box.createRigidArea(new Dimension(0, 25)));
        leftCol.add(btnRow); leftCol.add(Box.createRigidArea(new Dimension(0, 40)));
        leftCol.add(statsRow);

        // RIGHT
        JLayeredPane rightCol = new JLayeredPane();
        JPanel imgCard = createRoundedPanel(30, new Color(0,0,0,0), false);
        imgCard.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon("pictures/doctor.jpg");
            if(icon.getIconWidth() > -1) {
                Image img = icon.getImage().getScaledInstance(350, 420, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } else imageLabel.setText("Image not found");
        } catch(Exception e){}
        imgCard.add(imageLabel);
        imgCard.setBounds(50, 0, 350, 420);
        rightCol.add(imgCard, JLayeredPane.DEFAULT_LAYER);

        JPanel expertCard = createExpertCard();
        expertCard.setBounds(0, 360, 230, 85);
        rightCol.add(expertCard, JLayeredPane.PALETTE_LAYER);

        heroContent.add(leftCol); heroContent.add(rightCol);
        wrapper.add(heroContent);
        return wrapper;
    }

    // =========================================================================
    // SHARED TOOLS (Used by DoctorPage.java)
    // =========================================================================

    public static JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(TEXT_DARK);
        btn.setBackground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(BRAND_BLUE); }
            public void mouseExited(MouseEvent e) { btn.setForeground(TEXT_DARK); }
        });
        return btn;
    }

    public static JPanel createStatCard(String number, String text) {
        JPanel card = createRoundedPanel(20, Color.WHITE, true);
        card.setPreferredSize(new Dimension(140, 90));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(10, 15, 10, 5));
        JLabel numLbl = new JLabel(number);
        numLbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        numLbl.setForeground(BRAND_BLUE);
        JTextArea txtLbl = new JTextArea(text);
        txtLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        txtLbl.setForeground(TEXT_DARK);
        txtLbl.setOpaque(false); txtLbl.setEditable(false);
        card.add(numLbl); card.add(Box.createRigidArea(new Dimension(0, 2))); card.add(txtLbl);
        return card;
    }

    public static JPanel createExpertCard() {
        JPanel card = createRoundedPanel(20, Color.WHITE, true);
        card.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        JLabel iconLbl = new JLabel();
        iconLbl.setIcon(new Icon() {
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#DBEAFE"));
                g2.fillOval(x, y, 45, 45);
                g2.setColor(BRAND_BLUE);
                g2.fillOval(x+15, y+10, 15, 15);
                g2.fillArc(x+10, y+28, 25, 20, 0, 180);
            }
            public int getIconWidth() { return 45; }
            public int getIconHeight() { return 45; }
        });
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        JLabel title = new JLabel("Expert Care");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        JLabel subtitle = new JLabel("Certified Professionals");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(TEXT_GRAY);
        textPanel.add(title); textPanel.add(subtitle);
        card.add(iconLbl); card.add(textPanel);
        return card;
    }

    // SHARED HELPER: Rounded Panel
    public static JPanel createRoundedPanel(int radius, Color bgColor, boolean drawStroke) {
        return new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor); 
                g2.fillRoundRect(0,0,getWidth()-1,getHeight()-1,radius,radius);
                if(drawStroke){ 
                    g2.setColor(BORDER_GRAY); g2.setStroke(new BasicStroke(2));
                    g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,radius,radius); 
                }
            }
            { setOpaque(false); }
        };
    }

    // SHARED HELPER: Rounded Button
    public static JButton createRoundedButton(String text, Color bg, Color fg, boolean isOutline) {
        JButton btn = new JButton(text) {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if(isOutline){ 
                    g2.setColor(Color.WHITE); g2.fillRoundRect(0,0,getWidth()-1,getHeight()-1,30,30);
                    g2.setColor(BRAND_BLUE); g2.setStroke(new BasicStroke(2));
                    g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,30,30); 
                } else { 
                    g2.setColor(bg); g2.fillRoundRect(0,0,getWidth(),getHeight(),30,30); 
                }
                super.paintComponent(g);
            }
        };
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setContentAreaFilled(false); btn.setFocusPainted(false); 
        btn.setBorderPainted(false); btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        btn.setBorder(new EmptyBorder(8,15,8,15));
        return btn;
    }
}