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
public class DashboardFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardFrame.class.getName());

    /**
     * Creates new form DashboardFrame
     */
    public DashboardFrame() {
        initComponents();
        bond.util.SessionManager.loadSession(); 
        this.setSize(1000, 635);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);
         
        //Scroll
        javax.swing.JPanel contentPanel = new javax.swing.JPanel(null);
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new java.awt.Dimension(1000, 750));

        javax.swing.JLabel bgImage = new javax.swing.JLabel();
        bgImage.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_DashboardScroll.png")));
        bgImage.setBounds(0, -60, 1000, 750);
        contentPanel.add(bgImage);
        
        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);

        javax.swing.JLabel lblOrgOverview = new javax.swing.JLabel();
        lblOrgOverview.setBounds(243, 48, 720, 30);
        lblOrgOverview.setForeground(new java.awt.Color(122, 158, 140));
        lblOrgOverview.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 16));
        contentPanel.add(lblOrgOverview);                       
        contentPanel.setComponentZOrder(lblOrgOverview, 0);

        javax.swing.JLabel lblTotalMembers = new javax.swing.JLabel("0");
        lblTotalMembers.setBounds(228, 155, 100, 30);
        lblTotalMembers.setForeground(java.awt.Color.BLACK);
        lblTotalMembers.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        lblTotalMembers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(lblTotalMembers);
        contentPanel.setComponentZOrder(lblTotalMembers, 0);

        javax.swing.JLabel lblTotalEvents = new javax.swing.JLabel("0");
        lblTotalEvents.setBounds(410, 155, 100, 30);
        lblTotalEvents.setForeground(java.awt.Color.BLACK);
        lblTotalEvents.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        lblTotalEvents.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(lblTotalEvents);
        contentPanel.setComponentZOrder(lblTotalEvents, 0);

        javax.swing.JLabel lblTotalPending = new javax.swing.JLabel("0");
        lblTotalPending.setBounds(595, 155, 100, 30);
        lblTotalPending.setForeground(java.awt.Color.BLACK);
        lblTotalPending.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        lblTotalPending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(lblTotalPending);
        contentPanel.setComponentZOrder(lblTotalPending, 0);

        javax.swing.JLabel lblTotalAnnouncements = new javax.swing.JLabel("0");
        lblTotalAnnouncements.setBounds(780, 155, 100, 30);
        lblTotalAnnouncements.setForeground(java.awt.Color.BLACK);
        lblTotalAnnouncements.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        lblTotalAnnouncements.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(lblTotalAnnouncements);
        contentPanel.setComponentZOrder(lblTotalAnnouncements, 0);

        // RECENT ACTIVITY
        int[] activityY = {345, 375, 405, 435, 465, 495, 525, 555, 585, 615};
        javax.swing.JLabel[] lblActivities = new javax.swing.JLabel[10];
        javax.swing.JLabel[] lblDates = new javax.swing.JLabel[10];

        for (int i = 0; i < 10; i++) {
            lblActivities[i] = new javax.swing.JLabel("-");
            lblActivities[i].setBounds(300, activityY[i], 300, 25);
            lblActivities[i].setForeground(java.awt.Color.BLACK);
            lblActivities[i].setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
            lblActivities[i].setToolTipText("");
            contentPanel.add(lblActivities[i]);
            contentPanel.setComponentZOrder(lblActivities[i], 0);

            lblDates[i] = new javax.swing.JLabel("-");
            lblDates[i].setBounds(695, activityY[i], 150, 25);
            lblDates[i].setForeground(java.awt.Color.BLACK);
            lblDates[i].setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
            contentPanel.add(lblDates[i]);
            contentPanel.setComponentZOrder(lblDates[i], 0);
        }

        contentPanel.setComponentZOrder(bgImage, contentPanel.getComponentCount() - 1);
        loadDashboardData(lblOrgAcronym, lblOrgOverview,
                          lblTotalMembers, lblTotalEvents,
                          lblTotalPending, lblTotalAnnouncements,
                          lblActivities, lblDates);

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
        scrollPane.getVerticalScrollBar().setBorder(null);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void paintTrack(java.awt.Graphics g, javax.swing.JComponent c, java.awt.Rectangle r) {}
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

        javax.swing.JButton btnSettings = makeInvisibleButton();
        btnSettings.setBounds(0, 355, 210, 40);
        btnSettings.addActionListener(e -> navigateTo(new SettingsFrame()));
        getContentPane().add(btnSettings);
        getContentPane().setComponentZOrder(btnSettings, 0);

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
    }
    
    private void loadDashboardData(
        javax.swing.JLabel lblOrgAcronym,
        javax.swing.JLabel lblOrgOverview,
        javax.swing.JLabel lblTotalMembers,
        javax.swing.JLabel lblTotalEvents,
        javax.swing.JLabel lblTotalPending,
        javax.swing.JLabel lblTotalAnnouncements,
        javax.swing.JLabel[] lblActivities,
        javax.swing.JLabel[] lblDates) {
    try {
        java.sql.Connection conn = bond.db.DBConnection.getConnection();

        // Org name
        java.sql.ResultSet rs = conn.prepareStatement(
            "SELECT name FROM organizations WHERE org_id = 1"
        ).executeQuery();
        if (rs.next()) {
            String orgName = rs.getString("name");
            lblOrgOverview.setText("Overview of " + orgName);
            lblOrgOverview.setToolTipText("Overview of " + orgName);          
        }

        // Total members (approved)
        java.sql.ResultSet rsM = conn.prepareStatement(
            "SELECT COUNT(*) FROM members WHERE org_id = 1 AND status = 'Approved'"
        ).executeQuery();
        if (rsM.next()) lblTotalMembers.setText(String.valueOf(rsM.getInt(1)));

        // Total events
        java.sql.ResultSet rsE = conn.prepareStatement(
            "SELECT COUNT(*) FROM events WHERE org_id = 1"
        ).executeQuery();
        if (rsE.next()) lblTotalEvents.setText(String.valueOf(rsE.getInt(1)));

        // Pending members
        java.sql.ResultSet rsP = conn.prepareStatement(
            "SELECT COUNT(*) FROM members WHERE org_id = 1 AND status = 'Pending'"
        ).executeQuery();
        if (rsP.next()) lblTotalPending.setText(String.valueOf(rsP.getInt(1)));

        // Total announcements
        java.sql.ResultSet rsA = conn.prepareStatement(
            "SELECT COUNT(*) FROM announcements WHERE org_id = 1"
        ).executeQuery();
        if (rsA.next()) lblTotalAnnouncements.setText(String.valueOf(rsA.getInt(1)));

        // Recent activity 
        java.sql.ResultSet rsAct = conn.prepareStatement(
            "SELECT name, created_at FROM members WHERE org_id = 1 " +
            "ORDER BY created_at DESC LIMIT 10"
        ).executeQuery();
        int i = 0;
        while (rsAct.next() && i < 10) {
            String activity = rsAct.getString("name") + " joined";
            String date = rsAct.getString("created_at");
            lblActivities[i].setText(activity);
            lblActivities[i].setToolTipText(activity); // hover for full text
            lblDates[i].setText(date != null ? date.substring(0, 10) : "-");
            i++;
        }

        conn.close();
    } catch (Exception ex) {
        System.out.println("Dashboard load error: " + ex.getMessage());
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Dashboard.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1042, 600);

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
        java.awt.EventQueue.invokeLater(() -> new DashboardFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
