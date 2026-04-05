                                                                                                                                                                                                                                        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

import bond.search.GlobalSearchBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import bond.ui.UserSide.dashboard;
/**
 *
 * @author Erica
 */
public class SettingsFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SettingsFrame.class.getName());
    private javax.swing.JPanel contentPanel;

    class CircleProfilePanel extends javax.swing.JPanel {
        private java.awt.image.BufferedImage profileImage = null;

        public CircleProfilePanel() {
            setOpaque(false);
            setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            setToolTipText("Click to upload photo");
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
                    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Image Files", "jpg", "jpeg", "png"));
                    if (chooser.showOpenDialog(null) == javax.swing.JFileChooser.APPROVE_OPTION) {
                        try {
                            profileImage = javax.imageio.ImageIO.read(chooser.getSelectedFile());
                            repaint();
                        } catch (Exception ex) {
                            System.out.println("Image error: " + ex.getMessage());
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, w, h));
            if (profileImage != null) {
                g2.drawImage(profileImage, 0, 0, w, h, null);
            } else {
                g2.setColor(new java.awt.Color(200, 200, 200));
                g2.fillOval(0, 0, w, h);
                g2.setColor(java.awt.Color.DARK_GRAY);
                g2.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 10));
                java.awt.FontMetrics fm = g2.getFontMetrics();
                String txt = "Upload Photo";
                g2.drawString(txt, (w - fm.stringWidth(txt)) / 2, h / 2 + 4);
            }
            g2.setClip(null);
            g2.setColor(new java.awt.Color(120, 120, 120));
            g2.setStroke(new java.awt.BasicStroke(2));
            g2.drawOval(1, 1, w - 2, h - 2);
        }
    }
    

    /**
     * Creates new form SettingsFrame
     */
    public SettingsFrame() {
    initComponents();
    ActiveTab.setBounds(0, 7, 1000, 600); 
    javax.swing.JLabel lblBond = new javax.swing.JLabel("BOND");
        lblBond.setFont(new java.awt.Font("Playfair Display", java.awt.Font.PLAIN, 40));
        lblBond.setForeground(java.awt.Color.WHITE);
        lblBond.setBounds(28, 17, 200, 50);
        getContentPane().add(lblBond);
        getContentPane().setComponentZOrder(lblBond, 0);
        lblBond.setName("static");

        javax.swing.JLabel lblOrgAdmin = new javax.swing.JLabel("Org Admin");
        lblOrgAdmin.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 16));
        lblOrgAdmin.setForeground(java.awt.Color.WHITE);
        lblOrgAdmin.setBounds(30, 115, 160, 25);
        getContentPane().add(lblOrgAdmin);
        getContentPane().setComponentZOrder(lblOrgAdmin, 0);
        lblOrgAdmin.setName("static");

        javax.swing.JLabel lblDashboard = new javax.swing.JLabel("Dashboard");
        lblDashboard.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lblDashboard.setForeground(java.awt.Color.WHITE);
        lblDashboard.setBounds(28, 196, 160, 25);
        getContentPane().add(lblDashboard);
        getContentPane().setComponentZOrder(lblDashboard, 0);
        lblDashboard.setName("static");

        javax.swing.JLabel lblEvents = new javax.swing.JLabel("Events");
        lblEvents.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lblEvents.setForeground(java.awt.Color.WHITE);
        lblEvents.setBounds(28, 238, 160, 25);
        getContentPane().add(lblEvents);
        getContentPane().setComponentZOrder(lblEvents, 0);
        lblEvents.setName("static");

        javax.swing.JLabel lblAnnSidebar = new javax.swing.JLabel("Announcement");
        lblAnnSidebar.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lblAnnSidebar.setForeground(java.awt.Color.WHITE);
        lblAnnSidebar.setBounds(28, 280, 160, 25);
        getContentPane().add(lblAnnSidebar);
        getContentPane().setComponentZOrder(lblAnnSidebar, 0);
        lblAnnSidebar.setName("static");

        javax.swing.JLabel lblOrgProfile = new javax.swing.JLabel("Organization Profile");
        lblOrgProfile.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lblOrgProfile.setForeground(java.awt.Color.WHITE);
        lblOrgProfile.setBounds(28, 322, 180, 25);
        getContentPane().add(lblOrgProfile);
        getContentPane().setComponentZOrder(lblOrgProfile, 0);
        lblOrgProfile.setName("static");

        javax.swing.JLabel lblSettings = new javax.swing.JLabel("Settings");
        lblSettings.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lblSettings.setForeground(java.awt.Color.WHITE);
        lblSettings.setBounds(28, 364, 160, 25);
        getContentPane().add(lblSettings);
        getContentPane().setComponentZOrder(lblSettings, 0);
        lblSettings.setName("static");

        javax.swing.JLabel lblExitAdmin = new javax.swing.JLabel("Exit Admin");
        lblExitAdmin.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lblExitAdmin.setForeground(java.awt.Color.RED);
        lblExitAdmin.setBounds(28, 550, 160, 25);
        getContentPane().add(lblExitAdmin);
        getContentPane().setComponentZOrder(lblExitAdmin, 0);
        lblExitAdmin.setName("static");
        
        
        bond.util.SessionManager.loadSession();
        this.setSize(1000, 635);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        javax.swing.ImageIcon sbDefault = new javax.swing.ImageIcon(   
                getClass().getClassLoader().getResource("bond/assets/orgAdminImages/OrgAdmin_SearchBar.png"));
        javax.swing.ImageIcon sbHover = new javax.swing.ImageIcon(
                getClass().getClassLoader().getResource("bond/assets/orgAdminImages/OrgAdmin_SearchBarHover.png"));

        GlobalSearchBar searchBar = new GlobalSearchBar(this, result -> {
            switch (result.type) {
                case EVENT:        navigateTo(new EventFrame());        break;
                case ANNOUNCEMENT: navigateTo(new AnnouncementFrame()); break;
                case MEMBER:       navigateTo(new OrgProfileFrame());   break;
            }
    },
            () -> SearchBar.setIcon(sbHover),   
            () -> SearchBar.setIcon(sbDefault)   
        );
        
        searchBar.installInto(getContentPane());

        contentPanel = new javax.swing.JPanel(null);
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new java.awt.Dimension(1000, 650));

        JLabel bgImage = new JLabel();
        bgImage.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SettingsScroll.png")));
        bgImage.setBounds(0, -60, 1000, 650);
        contentPanel.add(bgImage);

        JLabel lblOrgAcronym = new JLabel(bond.util.SessionManager.getOrgAcronym() + " Admin");
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);
        lblOrgAcronym.setName("static");

        CircleProfilePanel circleProfile = new CircleProfilePanel();
        circleProfile.setBounds(274, 127, 70, 70);
        contentPanel.add(circleProfile);
        contentPanel.setComponentZOrder(circleProfile, 0);

        JLabel lblAdminName = new JLabel();
        lblAdminName.setBounds(350, 120, 190, 30);
        lblAdminName.setForeground(java.awt.Color.BLACK);
        lblAdminName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        contentPanel.add(lblAdminName);

        JLabel lblOrgName = new JLabel();
        lblOrgName.setBounds(350, 150, 190, 20);
        lblOrgName.setForeground(java.awt.Color.BLACK);
        lblOrgName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        contentPanel.add(lblOrgName);

        JTextField fullNameField = makeTextField(290, 230, 350, 40, "Full Name");
        contentPanel.add(fullNameField);

        JTextField emailField = makeTextField(290, 295, 350, 40, "Email");
        contentPanel.add(emailField);

        JPasswordField passwordField = makePasswordField(290, 360, 350, 40);
        contentPanel.add(passwordField);

        JPasswordField confirmPasswordField = makePasswordField(290, 426, 350, 40);
        contentPanel.add(confirmPasswordField);

        JButton btnSave = makeInvisibleButton();
        btnSave.setBounds(530, 490, 105, 30);
        btnSave.addActionListener(e -> {
            String fullName = fullNameField.getText().trim();
            String email    = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirm  = new String(confirmPasswordField.getPassword()).trim();

            if (fullName.isEmpty() || email.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Full name and email cannot be empty!", "Warning",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!password.isEmpty() && !password.equals(confirm)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Passwords do not match!", "Warning",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            saveSettings(fullName, email, password.isEmpty() ? null : password);
        });
        contentPanel.add(btnSave);

        contentPanel.setComponentZOrder(bgImage, contentPanel.getComponentCount() - 1);

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(contentPanel) {
            @Override public void setBorder(javax.swing.border.Border b) {
                super.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            }
            @Override protected void paintBorder(java.awt.Graphics g) {}
        };
        scrollPane.setBounds(0, 86, 1000, 549);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);
        scrollPane.getViewport().setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBackground(new java.awt.Color(0, 0, 0, 0));
        scrollPane.setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setBorder(null);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override protected void paintTrack(java.awt.Graphics g, javax.swing.JComponent c, java.awt.Rectangle r) {}
            @Override protected void paintThumb(java.awt.Graphics g, javax.swing.JComponent c, java.awt.Rectangle r) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new java.awt.Color(180, 180, 180, 150));
                g2.fillRoundRect(r.x + 2, r.y, r.width - 4, r.height, 8, 8);
            }
            @Override protected JButton createDecreaseButton(int o) {
                JButton b = new JButton();
                b.setPreferredSize(new java.awt.Dimension(0, 0));
                return b;
            }
            @Override protected JButton createIncreaseButton(int o) {
                JButton b = new JButton();
                b.setPreferredSize(new java.awt.Dimension(0, 0));
                return b;
            }
        });

        getContentPane().add(scrollPane);
        
        int total = getContentPane().getComponentCount();
        getContentPane().setComponentZOrder(scrollPane, 0);
        getContentPane().setComponentZOrder(jLabel1, getContentPane().getComponentCount() - 1);
        getContentPane().setComponentZOrder(ActiveTab, total - 2);
        
        JButton btnDashboard = makeInvisibleButton();
        btnDashboard.setBounds(0, 196, 210, 40);
        btnDashboard.addActionListener(e -> navigateTo(new DashboardFrame()));
        getContentPane().add(btnDashboard);
        getContentPane().setComponentZOrder(btnDashboard, 0);

        JButton btnEvents = makeInvisibleButton();
        btnEvents.setBounds(0, 230, 210, 40);
        btnEvents.addActionListener(e -> navigateTo(new EventFrame()));
        getContentPane().add(btnEvents);
        getContentPane().setComponentZOrder(btnEvents, 0);

        JButton btnAnnouncement = makeInvisibleButton();
        btnAnnouncement.setBounds(0, 270, 210, 40);
        btnAnnouncement.addActionListener(e -> navigateTo(new AnnouncementFrame()));
        getContentPane().add(btnAnnouncement);
        getContentPane().setComponentZOrder(btnAnnouncement, 0);

        JButton btnOrgProfile = makeInvisibleButton();
        btnOrgProfile.setBounds(0, 313, 210, 40);
        btnOrgProfile.addActionListener(e -> navigateTo(new OrgProfileFrame()));
        getContentPane().add(btnOrgProfile);
        getContentPane().setComponentZOrder(btnOrgProfile, 0);

        JButton btnExitAdmin = makeInvisibleButton();
        btnExitAdmin.setBounds(20, 540, 90, 40);
        btnExitAdmin.addActionListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, "Are you sure you want to exit admin?",
                "Exit Admin", javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
               navigateTo(new dashboard());
            }
        });
        getContentPane().add(btnExitAdmin);
        getContentPane().setComponentZOrder(btnExitAdmin, 0);

        loadAdminData(fullNameField, emailField, lblAdminName, lblOrgName);
    }
    
    private JTextField makeTextField(int x, int y, int w, int h, String tooltip) {
        JTextField f = new JTextField();
        f.setBounds(x, y, w, h);
        f.setOpaque(false);
        f.setBackground(new java.awt.Color(0, 0, 0, 0));
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        f.setForeground(java.awt.Color.BLACK);
        f.setToolTipText(tooltip);
        return f;
    }

    private JPasswordField makePasswordField(int x, int y, int w, int h) {
        JPasswordField f = new JPasswordField();
        f.setBounds(x, y, w, h);
        f.setOpaque(false);
        f.setBackground(new java.awt.Color(0, 0, 0, 0));
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        f.setForeground(java.awt.Color.BLACK);
        return f;
    }

    private JButton makeInvisibleButton() {
        JButton btn = new JButton();
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        return btn;
    }

    private void navigateTo(javax.swing.JFrame targetFrame) {
        targetFrame.setVisible(true);
        this.dispose();
    }

    private void loadAdminData(JTextField fullNameField, JTextField emailField,
                               JLabel lblAdminName, JLabel lblOrgName) {
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();

            int adminId = bond.util.SessionManager.getCurrentAdminId();
            int orgId = bond.util.SessionManager.getCurrentOrgId();

            java.sql.PreparedStatement ps1 = conn.prepareStatement(
                "SELECT username, email FROM org_admin WHERE org_admin_id = ?"
            );
            ps1.setInt(1, adminId);
            java.sql.ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                String name  = rs.getString("username");
                String email = rs.getString("email");
                fullNameField.setText(name);
                emailField.setText(email);
                lblAdminName.setText(name);
                lblAdminName.setToolTipText(name);
            }

            java.sql.PreparedStatement ps2 = conn.prepareStatement(
                "SELECT org_name FROM organization WHERE org_id = ?"
            );
            ps2.setInt(1, orgId);
            java.sql.ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String org = rs2.getString("org_name");
                lblOrgName.setText(org);
                lblOrgName.setToolTipText(org);
            }

            conn.close();
        } catch (Exception ex) {
            System.out.println("Load admin error: " + ex.getMessage());
        }
    }

    private void saveSettings(String fullName, String email, String newPassword) {
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            int adminId = bond.util.SessionManager.getCurrentAdminId();

            if (newPassword != null) {
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "UPDATE org_admin SET username=?, email=?, password_hash=? WHERE org_admin_id=?"
                );
                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.setString(3, newPassword);
                ps.setInt(4, adminId);
                ps.executeUpdate();
            } else {
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "UPDATE org_admin SET username=?, email=? WHERE org_admin_id=?"
                );
                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.setInt(3, adminId);
                ps.executeUpdate();
            }
            
            conn.close();
            javax.swing.JOptionPane.showMessageDialog(this, "Settings saved successfully!");
        } catch (Exception ex) {
            System.out.println("Save settings error: " + ex.getMessage());
        }
    }
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ActiveTab = new javax.swing.JLabel();
        SearchBar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ActiveTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SettingsActiveTab.png"))); // NOI18N
        ActiveTab.setText("jLabel3");
        ActiveTab.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(ActiveTab);
        ActiveTab.setBounds(0, 0, 1000, 600);

        SearchBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SearchBar.png"))); // NOI18N
        SearchBar.setText("jLabel2");
        SearchBar.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(SearchBar);
        SearchBar.setBounds(0, 0, 1000, 600);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Settings.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SettingsFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActiveTab;
    private javax.swing.JLabel SearchBar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
