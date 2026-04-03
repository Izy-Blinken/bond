/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

import javax.swing.JButton;

/**
 *
 * @author Erica
 */
public class SettingsFrame extends javax.swing.JFrame {
    
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
                        "Image Files", "jpg", "jpeg", "png"
                    ));
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

    private javax.swing.JPanel contentPanel;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SettingsFrame.class.getName());

    /**
     * Creates new form SettingsFrame
     */
    public SettingsFrame() {
    initComponents();
    bond.util.SessionManager.loadSession(); 
    this.setSize(1000, 635);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    getContentPane().setLayout(null);

    contentPanel = new javax.swing.JPanel(null);
    contentPanel.setOpaque(false);
    contentPanel.setPreferredSize(new java.awt.Dimension(1000, 650));
    
    /*JButton btnAddMemberTest = new JButton("+ Add Member");
    btnAddMemberTest.setBounds(350,150, 190, 20);
    contentPanel.add(btnAddMemberTest);
    contentPanel.setComponentZOrder(btnAddMemberTest, 0);* pang debug lang i2*/
    
    // Background scroll image
    javax.swing.JLabel bgImage = new javax.swing.JLabel();
    bgImage.setIcon(new javax.swing.ImageIcon(
        getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SettingsScroll.png")));
    bgImage.setBounds(0, -60, 1000, 650);
    contentPanel.add(bgImage);
    
    javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
    lblOrgAcronym.setBounds(32, 135, 120, 25);
    lblOrgAcronym.setForeground(java.awt.Color.WHITE);
    lblOrgAcronym.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
    lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
    getContentPane().add(lblOrgAcronym);
    getContentPane().setComponentZOrder(lblOrgAcronym, 0);
    
    CircleProfilePanel circleProfile = new CircleProfilePanel();
    circleProfile.setBounds(274, 127, 70, 70); 
    contentPanel.add(circleProfile);
    contentPanel.setComponentZOrder(circleProfile, 0);

    javax.swing.JTextField fullNameField = new javax.swing.JTextField();
    fullNameField.setBounds (290,230, 350, 40);
    fullNameField.setOpaque(false);
    fullNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
    fullNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    fullNameField.setForeground(java.awt.Color.BLACK);
    fullNameField.setToolTipText("Full Name");
    contentPanel.add(fullNameField);

    javax.swing.JTextField emailField = new javax.swing.JTextField();
    emailField.setBounds (290,295, 350, 40);
    emailField.setOpaque(false);
    emailField.setBackground(new java.awt.Color(0, 0, 0, 0));
    emailField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    emailField.setForeground(java.awt.Color.BLACK);
    emailField.setToolTipText("Email");
    contentPanel.add(emailField);

    javax.swing.JPasswordField passwordField = new javax.swing.JPasswordField();
    passwordField.setBounds(290, 360, 350, 40);
    passwordField.setOpaque(false);
    passwordField.setBackground(new java.awt.Color(0, 0, 0, 0));
    passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    passwordField.setForeground(java.awt.Color.BLACK);
    contentPanel.add(passwordField);

    javax.swing.JPasswordField confirmPasswordField = new javax.swing.JPasswordField();
    confirmPasswordField.setBounds (290,426, 350, 40);
    confirmPasswordField.setOpaque(false);
    confirmPasswordField.setBackground(new java.awt.Color(0, 0, 0, 0));
    confirmPasswordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    confirmPasswordField.setForeground(java.awt.Color.BLACK);
    contentPanel.add(confirmPasswordField);

    javax.swing.JLabel lblAdminName = new javax.swing.JLabel();
    lblAdminName.setBounds (350,120, 190, 30);
    lblAdminName.setForeground(java.awt.Color.BLACK);
    lblAdminName.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
    lblAdminName.setToolTipText("Admin Name");
    contentPanel.add(lblAdminName);

    javax.swing.JLabel lblOrgName = new javax.swing.JLabel();
    lblOrgName.setBounds(350,150, 190, 20);
    lblOrgName.setForeground(java.awt.Color.BLACK);
    lblOrgName.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
    lblOrgName.setToolTipText("Organization");
    contentPanel.add(lblOrgName);

    javax.swing.JButton btnSave = new javax.swing.JButton();
    btnSave.setOpaque(false);
    btnSave.setContentAreaFilled(false);
    btnSave.setBorderPainted(false);
    btnSave.setFocusPainted(false);
    btnSave.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    btnSave.setBounds(530,490, 105, 30);
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

    // Z-order: bgImage at back
    contentPanel.setComponentZOrder(bgImage, contentPanel.getComponentCount() - 1);

    // SCROLL PANE
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(contentPanel) {
    @Override
    public void setBorder(javax.swing.border.Border border) {
        super.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }
    @Override
    protected void paintBorder(java.awt.Graphics g) {}
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

    // REMOVE BORDER
    scrollPane.getVerticalScrollBar().setBorder(null);
    scrollPane.getVerticalScrollBar().setOpaque(false);
    scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
    @Override
    protected void paintTrack(java.awt.Graphics g, javax.swing.JComponent c, java.awt.Rectangle r) {
        // transparent track
    }
    @Override
    protected void paintThumb(java.awt.Graphics g, javax.swing.JComponent c, java.awt.Rectangle r) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                            java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new java.awt.Color(180, 180, 180, 150));
        g2.fillRoundRect(r.x + 2, r.y, r.width - 4, r.height, 8, 8);
    }
    @Override
    protected javax.swing.JButton createDecreaseButton(int o) {
        javax.swing.JButton b = new javax.swing.JButton();
        b.setPreferredSize(new java.awt.Dimension(0, 0));
        return b;
    }
    @Override
    protected javax.swing.JButton createIncreaseButton(int o) {
        javax.swing.JButton b = new javax.swing.JButton();
        b.setPreferredSize(new java.awt.Dimension(0, 0));
        return b;
    }
});

    getContentPane().add(scrollPane);
    getContentPane().setComponentZOrder(scrollPane, 0);
    getContentPane().setComponentZOrder(jLabel1, getContentPane().getComponentCount() - 1);

    // SIDEBAR NAV
    javax.swing.JButton btnDashboard = makeInvisibleButton();
    btnDashboard.setBounds(0, 196, 210, 40);
    btnDashboard.addActionListener(e -> navigateTo(new DashboardFrame()));
    getContentPane().add(btnDashboard);
    getContentPane().setComponentZOrder(btnDashboard, 0);

    javax.swing.JButton btnEvents = makeInvisibleButton();
    btnEvents.setBounds(0, 230, 210, 40);
    btnEvents.addActionListener(e -> navigateTo(new EventFrame()));
    getContentPane().add(btnEvents);
    getContentPane().setComponentZOrder(btnEvents, 0);

    javax.swing.JButton btnAnnouncement = makeInvisibleButton();
    btnAnnouncement.setBounds(0, 270, 210, 40);
    btnAnnouncement.addActionListener(e -> navigateTo(new AnnouncementFrame()));
    getContentPane().add(btnAnnouncement);
    getContentPane().setComponentZOrder(btnAnnouncement, 0);

    javax.swing.JButton btnOrgProfile = makeInvisibleButton();
    btnOrgProfile.setBounds(0, 313, 210, 40);
    btnOrgProfile.addActionListener(e -> navigateTo(new OrgProfileFrame()));
    getContentPane().add(btnOrgProfile);
    getContentPane().setComponentZOrder(btnOrgProfile, 0);

    javax.swing.JButton btnExitAdmin = makeInvisibleButton();
    btnExitAdmin.setBounds(20, 540, 90, 40);
    btnExitAdmin.addActionListener(e -> {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this, "Are you sure you want to exit admin?",
            "Exit Admin", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // navigateTo(new LoginFrame());
        }
    });
    getContentPane().add(btnExitAdmin);
    getContentPane().setComponentZOrder(btnExitAdmin, 0);
    loadAdminData(fullNameField, emailField, lblAdminName, lblOrgName);
}

    private void loadAdminData(javax.swing.JTextField fullNameField,
                            javax.swing.JTextField emailField,
                            javax.swing.JLabel lblAdminName,
                            javax.swing.JLabel lblOrgName) {
            try {
                java.sql.Connection conn = bond.db.DBConnection.getConnection();

                // Load admin info
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "SELECT full_name, email FROM admins WHERE admin_id = 1"
                );
                java.sql.ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name  = rs.getString("full_name");
                    String email = rs.getString("email");
                    fullNameField.setText(name);
                    emailField.setText(email);
                    lblAdminName.setText(name);
                    lblAdminName.setToolTipText(name);
                }

                // Load org name
                java.sql.PreparedStatement ps2 = conn.prepareStatement(
                    "SELECT name FROM organizations WHERE org_id = 1"
                );
                java.sql.ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    String org = rs2.getString("name");
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

            if (newPassword != null) {
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "UPDATE admins SET full_name=?, email=?, password=? WHERE admin_id=1"
                );
                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.setString(3, newPassword);
                ps.executeUpdate();
            } else {
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "UPDATE admins SET full_name=?, email=? WHERE admin_id=1"
                );
                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.executeUpdate();
            }

            conn.close();
            javax.swing.JOptionPane.showMessageDialog(this, "Settings saved successfully!");
        } catch (Exception ex) {
            System.out.println("Save settings error: " + ex.getMessage());
        }
    }

    private javax.swing.JButton makeInvisibleButton() {
        javax.swing.JButton btn = new javax.swing.JButton();
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


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Settings.png"))); // NOI18N
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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
