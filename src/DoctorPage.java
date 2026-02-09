import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DoctorPage {  // <--- NAME NOW MATCHES THE FILE NAME

    public static JPanel createPanel() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(new EmptyBorder(40, 0, 40, 0));

        // =================================================================
        // 1. HEADER SECTION
        // =================================================================
        JLabel header = new JLabel("Top Doctor to Book");
        header.setFont(new Font("Segoe UI", Font.BOLD, 40));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subHeader = new JLabel("Providing accessible healthcare solutions for the University of mindanao doctors.");
        subHeader.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subHeader.setForeground(MindanaCare.TEXT_GRAY); // Using shared color from main file
        subHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        wrapper.add(header);
        wrapper.add(Box.createRigidArea(new Dimension(0, 10)));
        wrapper.add(subHeader);
        wrapper.add(Box.createRigidArea(new Dimension(0, 40)));

        // =================================================================
        // 2. DOCTOR CARDS SECTION
        // =================================================================
        JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        cardsPanel.setBackground(Color.WHITE);

        // Card 1
        cardsPanel.add(createDoctorCard("Dr. Vinese Yap", "Cardiologist", "pictures/doctor.jpg"));
        // Card 2
        cardsPanel.add(createDoctorCard("Dr. Aria Pelobello", "Neurologist", "pictures/doctor.jpg"));
        // Card 3
        cardsPanel.add(createDoctorCard("Dr. Celine Mohammad", "Orthopedic", "pictures/doctor.jpg"));

        wrapper.add(cardsPanel);
        wrapper.add(Box.createRigidArea(new Dimension(0, 50)));

        // =================================================================
        // 3. BOTTOM BANNER SECTION
        // =================================================================
        // Blue container
        JPanel banner = MindanaCare.createRoundedPanel(40, MindanaCare.BRAND_BLUE, false);
        banner.setPreferredSize(new Dimension(1000, 120));
        banner.setMaximumSize(new Dimension(1000, 120));
        banner.setLayout(new BorderLayout());
        banner.setBorder(new EmptyBorder(0, 50, 0, 50));

        // Text on the left
        JPanel bannerText = new JPanel();
        bannerText.setLayout(new BoxLayout(bannerText, BoxLayout.Y_AXIS));
        bannerText.setOpaque(false); // Transparent so blue shows
        bannerText.setBorder(new EmptyBorder(25, 0, 0, 0));

        JLabel banTitle = new JLabel("Book an appointment with Trusted UM Doctors");
        banTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        banTitle.setForeground(Color.WHITE);
        
        JLabel banSub = new JLabel("Providing accessible healthcare solutions for the University of Mindanao community");
        banSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        banSub.setForeground(new Color(220, 220, 255)); // Light blue-ish white

        bannerText.add(banTitle);
        bannerText.add(Box.createRigidArea(new Dimension(0, 5)));
        bannerText.add(banSub);

        // Button on the right
        JButton btnCreate = MindanaCare.createRoundedButton("Create Account", Color.WHITE, MindanaCare.BRAND_BLUE, false);
        btnCreate.setPreferredSize(new Dimension(160, 45));
        
        // Helper panel to center the button vertically
        JPanel btnWrapper = new JPanel(new GridBagLayout());
        btnWrapper.setOpaque(false);
        btnWrapper.add(btnCreate);

        banner.add(bannerText, BorderLayout.CENTER);
        banner.add(btnWrapper, BorderLayout.EAST);

        wrapper.add(banner);

        return wrapper;
    }

    // =================================================================
    // HELPER: Creates a Single Doctor Card (Blue Top, Info Bottom)
    // =================================================================
    private static JPanel createDoctorCard(String name, String type, String imgPath) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setPreferredSize(new Dimension(280, 360)); // Size of the card

        // -- 1. Blue Image Background (Top Half) --
        JPanel imgBg = MindanaCare.createRoundedPanel(30, MindanaCare.BRAND_BLUE, false);
        imgBg.setPreferredSize(new Dimension(280, 220));
        imgBg.setMaximumSize(new Dimension(280, 220));
        imgBg.setLayout(new GridBagLayout()); // Centers the image
        
        JLabel imgLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(imgPath);
            if(icon.getIconWidth() > -1) {
                // Scale image to fit nicely
                Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(img));
            } else {
                imgLabel.setText("No Image");
                imgLabel.setForeground(Color.WHITE);
            }
        } catch(Exception e){}
        imgBg.add(imgLabel);

        // -- 2. Info Section (Bottom Half) --
        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLbl.setForeground(MindanaCare.BRAND_BLUE); // Name is Blue
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel typeLbl = new JLabel(type);
        typeLbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeLbl.setForeground(MindanaCare.TEXT_DARK);
        typeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // "View Profile" Button (Light Blue bg, Blue text)
        JButton btnView = MindanaCare.createRoundedButton("♥  VIEW PROFILE ▼", Color.decode("#DBEAFE"), MindanaCare.BRAND_BLUE, false);
        btnView.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnView.setPreferredSize(new Dimension(200, 40));
        btnView.setMaximumSize(new Dimension(200, 40));
        btnView.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add parts to container
        container.add(imgBg);
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(nameLbl);
        container.add(typeLbl);
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(btnView);

        return container;
    }
}