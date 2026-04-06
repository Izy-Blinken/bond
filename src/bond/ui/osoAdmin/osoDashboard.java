/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.osoAdmin;

/**
 *
 * @author denis
 */
public class osoDashboard extends javax.swing.JFrame {

    /**
     * Creates new form osoDashboard
     */
    public osoDashboard() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        loadStats();
        setupExitButton();
    }
    
    private void loadStats() {
        
        
        try {
            
            java.sql.Connection conn = bond.db.DBConnection.getConnection();

            java.sql.ResultSet rs1 = conn.prepareStatement("SELECT COUNT(*) FROM organization WHERE status = 'Active'").executeQuery();
            if (rs1.next()) {
                totalOrgsCOUNT.setText(String.valueOf(rs1.getInt(1)));
            }

            java.sql.ResultSet rs2 = conn.prepareStatement("SELECT COUNT(*) FROM members").executeQuery();
            if (rs2.next()) {
                totalmemCOUNT.setText(String.valueOf(rs2.getInt(1)));
            }

            java.sql.ResultSet rs3 = conn.prepareStatement("SELECT COUNT(*) FROM event").executeQuery();
            if (rs3.next()) {
                eventsCOUNT.setText(String.valueOf(rs3.getInt(1)));
            }

            java.sql.ResultSet rs4 = conn.prepareStatement("SELECT COUNT(*) FROM registration_form WHERE review_status = 'Pending'").executeQuery();
            if (rs4.next()) {
                pendingsCOUNT.setText(String.valueOf(rs4.getInt(1)));
            }

            
            // load registered orgs dynamically below stats
            java.sql.PreparedStatement psOrg = conn.prepareStatement(
                "SELECT org_name, classification, status FROM organization ORDER BY org_name ASC"
            );
            java.sql.ResultSet rsOrg = psOrg.executeQuery();

            int baseY = 320;
            while (rsOrg.next()) {
                String oName   = rsOrg.getString("org_name");
                String oClass  = rsOrg.getString("classification");
                String oStatus = rsOrg.getString("status");

                javax.swing.JLabel lName = new javax.swing.JLabel(oName);
                lName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
                lName.setForeground(new java.awt.Color(28, 94, 56));
                jPanel1.add(lName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, baseY, 300, 20));

                javax.swing.JLabel lClass = new javax.swing.JLabel(oClass);
                lClass.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                lClass.setForeground(new java.awt.Color(100, 140, 120));
                jPanel1.add(lClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, baseY + 18, 280, 16));

                java.awt.Color sc = "Active".equals(oStatus)
                    ? new java.awt.Color(28, 94, 56)
                    : new java.awt.Color(180, 30, 30);
                javax.swing.JLabel lStatus = new javax.swing.JLabel("● " + oStatus);
                lStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lStatus.setForeground(sc);
                jPanel1.add(lStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, baseY + 8, 120, 20));

                baseY += 50;
            }

            int finalH = Math.max(750, baseY + 30);
            jPanel1.setPreferredSize(new java.awt.Dimension(798, finalH));
            jPanel1.revalidate();
            jPanel1.repaint();


            conn.close();
            
            regOrgPanel.setVisible(false);
            jPanel1.revalidate();
            jPanel1.repaint();
            
        } catch (Exception ex) {
            System.out.println("Load stats error: " + ex.getMessage());
        }
    }
    
    private void setupExitButton() {

        javax.swing.JButton btnExit = new javax.swing.JButton("Exit Admin");
        btnExit.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnExit.setForeground(new java.awt.Color(200, 40, 40));
        btnExit.setBackground(new java.awt.Color(248, 250, 249));
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.setBounds(10, 540, 130, 30);
        btnExit.addActionListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, "Exit OSO Admin?", "Exit",
                javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                bond.util.SessionManager.logout();
                new bond.ui.UserSide.loginChoices().setVisible(true);
                this.dispose();
            }
        });
        jPanel2.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 150, 30));
        jPanel2.setComponentZOrder(btnExit, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        adminprofBtn = new javax.swing.JButton();
        orgBtn = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();
        dashboardBtn = new javax.swing.JButton();
        navbar = new javax.swing.JLabel();
        sidebar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        icon4 = new javax.swing.JLabel();
        icon3 = new javax.swing.JLabel();
        icon2 = new javax.swing.JLabel();
        icon1 = new javax.swing.JLabel();
        pendings = new javax.swing.JLabel();
        eventsCOUNT = new javax.swing.JLabel();
        events = new javax.swing.JLabel();
        pendingsCOUNT1 = new javax.swing.JLabel();
        totalmemCOUNT = new javax.swing.JLabel();
        totalMembers = new javax.swing.JLabel();
        totalOrgsCOUNT = new javax.swing.JLabel();
        totalOrgs = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totalMembersSlot = new javax.swing.JLabel();
        eventsSlot = new javax.swing.JLabel();
        pendingsSlot = new javax.swing.JLabel();
        totalOrgSlot = new javax.swing.JLabel();
        pendingsCOUNT = new javax.swing.JLabel();
        regOrgPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminprofBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/adminprofBtn.png"))); // NOI18N
        adminprofBtn.setBorder(null);
        adminprofBtn.setBorderPainted(false);
        adminprofBtn.setContentAreaFilled(false);
        adminprofBtn.setFocusable(false);
        adminprofBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/adminprofHover.png"))); // NOI18N
        adminprofBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminprofBtnActionPerformed(evt);
            }
        });
        jPanel2.add(adminprofBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        orgBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/orgBtn.png"))); // NOI18N
        orgBtn.setBorder(null);
        orgBtn.setBorderPainted(false);
        orgBtn.setContentAreaFilled(false);
        orgBtn.setFocusable(false);
        orgBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/orgHover.png"))); // NOI18N
        orgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orgBtnActionPerformed(evt);
            }
        });
        jPanel2.add(orgBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        settingsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/settingsBtn.png"))); // NOI18N
        settingsBtn.setBorder(null);
        settingsBtn.setBorderPainted(false);
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setFocusable(false);
        settingsBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/settingsHover.png"))); // NOI18N
        settingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnActionPerformed(evt);
            }
        });
        jPanel2.add(settingsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, -1, -1));

        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/dashboardBtn.png"))); // NOI18N
        dashboardBtn.setBorder(null);
        dashboardBtn.setBorderPainted(false);
        dashboardBtn.setContentAreaFilled(false);
        dashboardBtn.setFocusable(false);
        dashboardBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/dashboardHover.png"))); // NOI18N
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });
        jPanel2.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        navbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/navBar.png"))); // NOI18N
        jPanel2.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        sidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/sidebar.png"))); // NOI18N
        jPanel2.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 210, 540));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(248, 250, 249));
        jPanel1.setPreferredSize(new java.awt.Dimension(798, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/mdi_account-pending.png"))); // NOI18N
        jPanel1.add(icon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        icon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/mdi_calendar.png"))); // NOI18N
        jPanel1.add(icon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 150, -1, -1));

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/mdi_people.png"))); // NOI18N
        jPanel1.add(icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 150, -1, -1));

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/la_university.png"))); // NOI18N
        jPanel1.add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        pendings.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        pendings.setForeground(new java.awt.Color(28, 94, 56));
        pendings.setText("Pendings");
        jPanel1.add(pendings, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 60, 30));

        eventsCOUNT.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 24)); // NOI18N
        eventsCOUNT.setForeground(new java.awt.Color(28, 94, 56));
        eventsCOUNT.setText("5");
        jPanel1.add(eventsCOUNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 30, 30));

        events.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        events.setForeground(new java.awt.Color(28, 94, 56));
        events.setText("Events");
        jPanel1.add(events, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, 20));

        pendingsCOUNT1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 24)); // NOI18N
        pendingsCOUNT1.setForeground(new java.awt.Color(28, 94, 56));
        pendingsCOUNT1.setText("5");
        jPanel1.add(pendingsCOUNT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 20, 30));

        totalmemCOUNT.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 24)); // NOI18N
        totalmemCOUNT.setForeground(new java.awt.Color(28, 94, 56));
        totalmemCOUNT.setText("5");
        jPanel1.add(totalmemCOUNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 30, 30));

        totalMembers.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        totalMembers.setForeground(new java.awt.Color(28, 94, 56));
        totalMembers.setText("Total Members");
        jPanel1.add(totalMembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, 20));

        totalOrgsCOUNT.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 24)); // NOI18N
        totalOrgsCOUNT.setForeground(new java.awt.Color(28, 94, 56));
        totalOrgsCOUNT.setText("0");
        jPanel1.add(totalOrgsCOUNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 30, 30));

        totalOrgs.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        totalOrgs.setForeground(new java.awt.Color(28, 94, 56));
        totalOrgs.setText("Total Orgs");
        jPanel1.add(totalOrgs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, 20));

        jLabel12.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(28, 94, 56));
        jLabel12.setText("Dashboard");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel11.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(28, 94, 56));
        jLabel11.setText("Overview of all student organizations");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        totalMembersSlot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/slot.png"))); // NOI18N
        jPanel1.add(totalMembersSlot, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 134, -1, -1));

        eventsSlot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/slot.png"))); // NOI18N
        jPanel1.add(eventsSlot, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 134, -1, -1));

        pendingsSlot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/slot.png"))); // NOI18N
        jPanel1.add(pendingsSlot, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 134, -1, -1));

        totalOrgSlot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/slot.png"))); // NOI18N
        jPanel1.add(totalOrgSlot, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 134, -1, -1));

        pendingsCOUNT.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        pendingsCOUNT.setForeground(new java.awt.Color(28, 94, 56));
        jPanel1.add(pendingsCOUNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 40, 30));

        regOrgPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/registeredOrgpanel.png"))); // NOI18N
        jPanel1.add(regOrgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jScrollPane1.setViewportView(jPanel1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 800, 520));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminprofBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminprofBtnActionPerformed
        // TODO add your handling code here:
      new osoAdminProfile().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminprofBtnActionPerformed

    private void orgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgBtnActionPerformed
        // TODO add your handling code here:

         new osoOrganizations().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_orgBtnActionPerformed

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        // TODO add your handling code here:\

         new osoSettings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardBtnActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(osoDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(osoDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(osoDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(osoDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new osoDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminprofBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JLabel events;
    private javax.swing.JLabel eventsCOUNT;
    private javax.swing.JLabel eventsSlot;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
    private javax.swing.JLabel icon4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel navbar;
    private javax.swing.JButton orgBtn;
    private javax.swing.JLabel pendings;
    private javax.swing.JLabel pendingsCOUNT;
    private javax.swing.JLabel pendingsCOUNT1;
    private javax.swing.JLabel pendingsSlot;
    private javax.swing.JLabel regOrgPanel;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel sidebar;
    private javax.swing.JLabel totalMembers;
    private javax.swing.JLabel totalMembersSlot;
    private javax.swing.JLabel totalOrgSlot;
    private javax.swing.JLabel totalOrgs;
    private javax.swing.JLabel totalOrgsCOUNT;
    private javax.swing.JLabel totalmemCOUNT;
    // End of variables declaration//GEN-END:variables
}
