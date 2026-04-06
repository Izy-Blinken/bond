/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.osoAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author denis
 */
public class osoOrgInfos extends javax.swing.JFrame {

    private void setupUI() {

        tabBtn.setBorder(null);
        tabBtn.setForeground(new Color(28, 94, 56));
    }

    private void setupTabs() {

        tabBtn.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {

            @Override
            protected void paintText(Graphics g, int tabPlacement, Font font,
                    FontMetrics metrics, int tabIndex, String title,
                    Rectangle textRect, boolean isSelected) {

                g.setFont(new Font("Segoe UI", Font.BOLD, 13));

                if (isSelected) {
                    g.setColor(new Color(60, 130, 85));
                } else {
                    g.setColor(new Color(28, 94, 56));
                }

                g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
            }

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {

            }
        });

        tabBtn.setTitleAt(0, "   Info   ");
        tabBtn.setTitleAt(1, "   Members   ");
        tabBtn.setTitleAt(2, "   Pending Posts   ");

    }

    private int orgId;

    public osoOrgInfos(int orgId) {
        this.orgId = orgId;
        initComponents();
        setLocationRelativeTo(null);
        setupUI();
        setupTabs();
        loadOrgInfo();
        tabBtn.addChangeListener(e -> {
            int idx = tabBtn.getSelectedIndex();
            if (idx == 1) loadMembers();
            else if (idx == 2) loadPendingForms();
        });
    }

    public osoOrgInfos() {
        this(0);
    }
    
    private int selectedMemberId = -1;
    private int selectedFormId = -1;

   private void loadMembers() {

        jPanel3.removeAll();

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/membersTable.png")));
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 660, 190));
        jPanel3.setComponentZOrder(jLabel5, jPanel3.getComponentCount() - 1);

        int[] rowY = {52, 92, 132, 172, 212};
        int i = 0;
        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT member_id, name, role, status FROM members WHERE org_id = ? ORDER BY member_id ASC"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();

            int y = 60;
            while (rs.next()) {

                int mId = rs.getInt("member_id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String date = rs.getString("status");

                javax.swing.JLabel lblName = new javax.swing.JLabel(name);
                lblName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lblName.setForeground(new java.awt.Color(28, 94, 56));
                jPanel3.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, rowY[i] + 10, 180, 20));

                javax.swing.JLabel lblRole = new javax.swing.JLabel(role != null ? role : "—");
                lblRole.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lblRole.setForeground(new java.awt.Color(28, 94, 56));
                jPanel3.add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, rowY[i] + 10, 150, 20));

                javax.swing.JLabel lblStatus = new javax.swing.JLabel("Active");
                lblStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lblStatus.setForeground(new java.awt.Color(28, 94, 56));
                jPanel3.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, rowY[i] + 10, 100, 20));

                final int mid = mId;
                javax.swing.JLabel lblSelect = new javax.swing.JLabel("▶ Select");
                lblSelect.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lblSelect.setForeground(new java.awt.Color(28, 94, 56));
                lblSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lblSelect.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        selectedMemberId = mid;
                        lblSelect.setForeground(new java.awt.Color(200, 40, 40));
                    }
                });
                jPanel3.add(lblSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, rowY[i] + 10, 80, 20));

                i++;
            }

            conn.close();

        } catch (Exception ex) {
            System.out.println("Load members error: " + ex.getMessage());
        }

        jPanel3.revalidate();
        jPanel3.repaint();
    }

    private void loadPendingForms() {

        jPanel4.removeAll();

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/pendingTable.png")));
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 660, 190));
        jPanel4.setComponentZOrder(jLabel6, jPanel4.getComponentCount() - 1);

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT form_id, proposed_org_name, contact_email, review_status FROM registration_form ORDER BY form_id DESC"
            );
            java.sql.ResultSet rs = ps.executeQuery();

            int[] rowY = {52, 92, 132};
            int i = 0;

            while (rs.next() && i < 3) {
                int fId = rs.getInt("form_id");
                String orgN   = rs.getString("proposed_org_name");
                String email  = rs.getString("contact_email");
                String status = rs.getString("review_status");

                javax.swing.JLabel lblName = new javax.swing.JLabel(orgN);
                lblName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lblName.setForeground(new java.awt.Color(28, 94, 56));
                jPanel4.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, rowY[i] + 10, 200, 20));

                javax.swing.JLabel lblEmail = new javax.swing.JLabel(email);
                lblEmail.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                lblEmail.setForeground(new java.awt.Color(100, 140, 120));
                jPanel4.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, rowY[i] + 10, 200, 20));

                java.awt.Color sc = "Approved".equals(status) ? new java.awt.Color(28, 94, 56)
                        : "Rejected".equals(status) ? new java.awt.Color(180, 30, 30)
                        : new java.awt.Color(180, 130, 0);
                javax.swing.JLabel lblStatus = new javax.swing.JLabel(status);
                lblStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lblStatus.setForeground(sc);
                jPanel4.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, rowY[i] + 10, 90, 20));

                final int fid = fId;
                javax.swing.JLabel lblSelect = new javax.swing.JLabel("▶ Select");
                lblSelect.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lblSelect.setForeground(new java.awt.Color(28, 94, 56));
                lblSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lblSelect.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        selectedFormId = fid;
                        lblSelect.setForeground(new java.awt.Color(200, 40, 40));
                    }
                });
                jPanel4.add(lblSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, rowY[i] + 10, 80, 20));

                i++;
            }

            conn.close();

        } catch (Exception ex) {
            System.out.println("Load pending forms error: " + ex.getMessage());
        }

        jPanel4.revalidate();
        jPanel4.repaint();
    }

    private void loadOrgInfo() {

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT org_name, classification, status FROM organization WHERE org_id = ?"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                orgName.setText(rs.getString("org_name"));
                memberCount.setText(rs.getString("classification"));
                activeOrinactive.setSelected(rs.getString("status").equals("Inactive"));
            }

            java.sql.PreparedStatement ps2 = conn.prepareStatement(
                "SELECT COUNT(*) FROM members WHERE org_id = ?"
            );
            ps2.setInt(1, orgId);
            java.sql.ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                int count = rs2.getInt(1);
                memberCount.setText(memberCount.getText() + " · " + count + " members");
            }

            conn.close();

        } catch (Exception ex) {
            System.out.println("Load org info error: " + ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        tabBtn = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        icon1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        activeOrinactive = new javax.swing.JToggleButton();
        orgName = new javax.swing.JLabel();
        memberCount = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dashboardBtn = new javax.swing.JButton();
        orgBtn = new javax.swing.JButton();
        adminprofBtn = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();
        navbar = new javax.swing.JLabel();
        sidebar = new javax.swing.JLabel();
        infoframe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(248, 250, 249));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabBtn.setBackground(new java.awt.Color(237, 242, 239));
        tabBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(237, 242, 239));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/infoTable.png"))); // NOI18N
        jPanel2.add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 660, 190));

        tabBtn.addTab("Info", jPanel2);

        jPanel3.setBackground(new java.awt.Color(237, 242, 239));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/rejectBtn.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/rejectHover.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/approveBtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/approveHover.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/deleteBtn.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/deleteHover.png"))); // NOI18N
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/membersTable.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 660, 190));

        tabBtn.addTab("Members", jPanel3);

        jPanel4.setBackground(new java.awt.Color(237, 242, 239));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/rejectBtn.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/rejectHover.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/approveBtn.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/approveHover.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/pendingTable.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        tabBtn.addTab("Pending Posts", jPanel4);

        jPanel1.add(tabBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 700, 240));
        tabBtn.getAccessibleContext().setAccessibleName("Info");

        activeOrinactive.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        activeOrinactive.setForeground(new java.awt.Color(28, 94, 56));
        activeOrinactive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/activeBtn.png"))); // NOI18N
        activeOrinactive.setBorder(null);
        activeOrinactive.setBorderPainted(false);
        activeOrinactive.setContentAreaFilled(false);
        activeOrinactive.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/inactiveBtn.png"))); // NOI18N
        activeOrinactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeOrinactiveActionPerformed(evt);
            }
        });
        jPanel1.add(activeOrinactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 270, 70, 20));

        orgName.setFont(new java.awt.Font("Playfair Display", 1, 22)); // NOI18N
        orgName.setForeground(new java.awt.Color(28, 94, 56));
        orgName.setText("POSHED");
        jPanel1.add(orgName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        memberCount.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 10)); // NOI18N
        memberCount.setForeground(new java.awt.Color(28, 94, 56));
        memberCount.setText("Information Technology · 52 members");
        jPanel1.add(memberCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, 20));

        logo.setText("logo here");
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        jLabel16.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(28, 94, 56));
        jLabel16.setText("Click an organization to view members, posts, and info");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, 20));

        jLabel15.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(28, 94, 56));
        jLabel15.setText("Organizations");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

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
        jPanel1.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

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
        jPanel1.add(orgBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

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
        jPanel1.add(adminprofBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

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
        jPanel1.add(settingsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, -1, -1));

        navbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/navBar.png"))); // NOI18N
        jPanel1.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        sidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/sidebar.png"))); // NOI18N
        jPanel1.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 520));

        infoframe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/whitePanelforOrgs.png"))); // NOI18N
        jPanel1.add(infoframe, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        // TODO add your handling code here:
        new osoDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void orgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgBtnActionPerformed
        // TODO add your handling code here:

        new osoOrganizations().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_orgBtnActionPerformed

    private void adminprofBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminprofBtnActionPerformed
        // TODO add your handling code here:
        new osoAdminProfile().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminprofBtnActionPerformed

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        // TODO add your handling code here:\

        new osoSettings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void activeOrinactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeOrinactiveActionPerformed
        // TODO add your handling code here:
        String newStatus = activeOrinactive.isSelected() ? "Inactive" : "Active";

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE organization SET status = ? WHERE org_id = ?"
            );
            ps.setString(1, newStatus);
            ps.setInt(2, orgId);
            int rows = ps.executeUpdate();
            conn.close();

            if (rows > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Status updated to: " + newStatus);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Failed. org_id = " + orgId, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_activeOrinactiveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (selectedMemberId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a member first.");
            return;
        }

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM members WHERE member_id = ?"
            );
            ps.setInt(1, selectedMemberId);
            ps.executeUpdate();
            conn.close();
            selectedMemberId = -1;
            loadMembers();

        } catch (Exception ex) {
            System.out.println("Approve member error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (selectedMemberId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a member first.");
            return;
        }

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM members WHERE member_id = ?"
            );
            ps.setInt(1, selectedMemberId);
            ps.executeUpdate();
            conn.close();
            selectedMemberId = -1;
            loadMembers();

        } catch (Exception ex) {
            System.out.println("Reject member error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (selectedFormId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a pending form first.");
            return;
        }

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE registration_form SET review_status = 'Rejected' WHERE form_id = ?"
            );
            ps.setInt(1, selectedFormId);
            ps.executeUpdate();
            conn.close();
            selectedFormId = -1;
            loadPendingForms();

        } catch (Exception ex) {
            System.out.println("Reject form error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (selectedFormId == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a pending form first.");
            return;
        }

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE registration_form SET review_status = 'Approved' WHERE form_id = ?"
            );
            ps.setInt(1, selectedFormId);
            ps.executeUpdate();
            conn.close();
            selectedFormId = -1;
            loadPendingForms();

        } catch (Exception ex) {
            System.out.println("Approve form error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(osoOrgInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(osoOrgInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(osoOrgInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(osoOrgInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new osoOrgInfos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton activeOrinactive;
    private javax.swing.JButton adminprofBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel infoframe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel memberCount;
    private javax.swing.JLabel navbar;
    private javax.swing.JButton orgBtn;
    private javax.swing.JLabel orgName;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel sidebar;
    private javax.swing.JTabbedPane tabBtn;
    // End of variables declaration//GEN-END:variables
}
