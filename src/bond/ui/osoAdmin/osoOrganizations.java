/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.osoAdmin;

/**
 *
 * @author denis
 */
public class osoOrganizations extends javax.swing.JFrame {

    /**
     * Creates new form osoOrganizations
     */
    public osoOrganizations() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        loadOrganizations();
        setupExitButton();
    }
    
    private void showCreateAdminDialog(int orgId, String orgName) {

        javax.swing.JDialog dialog = new javax.swing.JDialog(this, "Assign Org Admin — " + orgName, true);
        dialog.setSize(480, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(new java.awt.Color(248, 250, 249));

        javax.swing.JPanel header = new javax.swing.JPanel(null);
        header.setBackground(new java.awt.Color(28, 94, 56));
        header.setBounds(0, 0, 480, 65);
        dialog.add(header);

        javax.swing.JLabel lblTitle = new javax.swing.JLabel("Assign Org Admin");
        lblTitle.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 18));
        lblTitle.setForeground(java.awt.Color.WHITE);
        lblTitle.setBounds(20, 10, 300, 28);
        header.add(lblTitle);

        javax.swing.JLabel lblSub = new javax.swing.JLabel(orgName);
        lblSub.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        lblSub.setForeground(new java.awt.Color(180, 220, 200));
        lblSub.setBounds(20, 38, 400, 18);
        header.add(lblSub);

        // check if org already has admin
        try {
            java.sql.Connection chkConn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement chkPs = chkConn.prepareStatement(
                "SELECT username FROM org_admin WHERE org_id = ?"
            );
            chkPs.setInt(1, orgId);
            java.sql.ResultSet chkRs = chkPs.executeQuery();
            if (chkRs.next()) {
                javax.swing.JLabel lblExisting = new javax.swing.JLabel(
                    "Current admin: " + chkRs.getString("username"));
                lblExisting.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
                lblExisting.setForeground(new java.awt.Color(180, 100, 0));
                lblExisting.setBounds(20, 78, 440, 20);
                dialog.add(lblExisting);
            }
            chkConn.close();
        } catch (Exception ex) {
            System.out.println("Check admin error: " + ex.getMessage());
        }

        // officer dropdown
        javax.swing.JLabel lblOfficer = new javax.swing.JLabel("Select Officer (will be linked as admin)");
        lblOfficer.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblOfficer.setForeground(new java.awt.Color(28, 94, 56));
        lblOfficer.setBounds(20, 108, 360, 20);
        dialog.add(lblOfficer);

        javax.swing.JComboBox<String> officerCombo = new javax.swing.JComboBox<>();
        officerCombo.setBounds(20, 132, 440, 28);
        officerCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        officerCombo.setBackground(java.awt.Color.WHITE);

        java.util.List<int[]> officerIds = new java.util.ArrayList<>();
        try {
            java.sql.Connection conn2 = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps2 = conn2.prepareStatement(
                "SELECT o.officer_id, o.full_name, o.position FROM officer o " +
                "LEFT JOIN org_admin oa ON oa.officer_id = o.officer_id " +
                "WHERE o.org_id = ? AND oa.officer_id IS NULL"
            );
            ps2.setInt(1, orgId);
            java.sql.ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                officerCombo.addItem(rs2.getString("full_name") + " — " + rs2.getString("position"));
                officerIds.add(new int[]{rs2.getInt("officer_id")});
            }
            conn2.close();
        } catch (Exception ex) {
            System.out.println("Load officers error: " + ex.getMessage());
        }

        if (officerCombo.getItemCount() == 0) {
            officerCombo.addItem("No available officers");
        }
        dialog.add(officerCombo);

        javax.swing.JLabel lblUser = new javax.swing.JLabel("Username");
        lblUser.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblUser.setForeground(new java.awt.Color(28, 94, 56));
        lblUser.setBounds(20, 172, 200, 20);
        dialog.add(lblUser);

        javax.swing.JTextField userInput = new javax.swing.JTextField();
        userInput.setBounds(20, 194, 200, 28);
        userInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        userInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(userInput);

        javax.swing.JLabel lblEmail = new javax.swing.JLabel("Email");
        lblEmail.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblEmail.setForeground(new java.awt.Color(28, 94, 56));
        lblEmail.setBounds(240, 172, 200, 20);
        dialog.add(lblEmail);

        javax.swing.JTextField emailInput = new javax.swing.JTextField();
        emailInput.setBounds(240, 194, 220, 28);
        emailInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        emailInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(emailInput);

        javax.swing.JLabel lblPass = new javax.swing.JLabel("Password");
        lblPass.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblPass.setForeground(new java.awt.Color(28, 94, 56));
        lblPass.setBounds(20, 234, 200, 20);
        dialog.add(lblPass);

        javax.swing.JPasswordField passInput = new javax.swing.JPasswordField();
        passInput.setBounds(20, 256, 440, 28);
        passInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        passInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(passInput);

        javax.swing.JButton btnCreate = new javax.swing.JButton("Create Account");
        btnCreate.setBounds(290, 306, 170, 32);
        btnCreate.setBackground(new java.awt.Color(28, 94, 56));
        btnCreate.setForeground(java.awt.Color.WHITE);
        btnCreate.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCreate.setBorderPainted(false);
        btnCreate.setFocusPainted(false);
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.addActionListener(e -> {

            String username = userInput.getText().trim();
            String email    = emailInput.getText().trim();
            String password = new String(passInput.getPassword()).trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "All fields are required.");
                return;
            }

            if (officerIds.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "No officer selected.");
                return;
            }

            int selectedOfficerId = officerIds.get(officerCombo.getSelectedIndex())[0];

            try {

                java.sql.Connection conn3 = bond.db.DBConnection.getConnection();
                java.sql.PreparedStatement ps3 = conn3.prepareStatement(
                    "INSERT INTO org_admin (org_id, officer_id, created_by, username, password_hash, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?)"
                );
                ps3.setInt(1, orgId);
                ps3.setInt(2, selectedOfficerId);
                ps3.setInt(3, bond.util.SessionManager.getCurrentOsoAdmin().getOso_admin_id());
                ps3.setString(4, username);
                ps3.setString(5, password);
                ps3.setString(6, email);
                ps3.executeUpdate();
                conn3.close();

                javax.swing.JOptionPane.showMessageDialog(dialog,
                    "Org Admin account created.\nUsername: " + username + "\nPassword: " + password);
                dialog.dispose();

            } catch (Exception ex) {
                System.out.println("Create admin error: " + ex.getMessage());
                javax.swing.JOptionPane.showMessageDialog(dialog,
                    "Failed: " + ex.getMessage());
            }
        });
        dialog.add(btnCreate);

        javax.swing.JButton btnCancel = new javax.swing.JButton("Cancel");
        btnCancel.setBounds(200, 306, 80, 32);
        btnCancel.setBackground(new java.awt.Color(180, 210, 195));
        btnCancel.setForeground(new java.awt.Color(28, 94, 56));
        btnCancel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dialog.dispose());
        dialog.add(btnCancel);

        dialog.setVisible(true);
    }
    
    
    private void loadOrganizations() {

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.ResultSet rs = conn.prepareStatement("SELECT org_id, org_name, classification, status FROM organization ORDER BY org_name ASC").executeQuery();

            int y = 130;

            while (rs.next()) {

                int id = rs.getInt("org_id");
                String name = rs.getString("org_name");
                String classification = rs.getString("classification");
                String status = rs.getString("status");

                javax.swing.JLabel lblName = new javax.swing.JLabel(name);
                lblName.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 18));
                lblName.setForeground(new java.awt.Color(28, 94, 56));
                lblName.setBounds(130, y + 20, 400, 30);
                jPanel2.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, y + 20, 400, 30));

                javax.swing.JLabel lblInfo = new javax.swing.JLabel(classification);
                lblInfo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
                lblInfo.setForeground(new java.awt.Color(28, 94, 56));
                lblInfo.setBounds(130, y + 50, 400, 20);
                jPanel2.add(lblInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, y + 50, 400, 20));

                javax.swing.JLabel lblStatus = new javax.swing.JLabel("●  " + status);
                lblStatus.setForeground(new java.awt.Color(28, 94, 56));
                lblStatus.setBounds(670, y + 40, 100, 20);
                jPanel2.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, y + 40, 100, 20));
                javax.swing.JButton btn = new javax.swing.JButton();
                btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/orgsFrame.png")));
                btn.setBorder(null);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setBounds(30, y, 600, 100);
                final int orgId = id;
                btn.addActionListener(e -> {
                    bond.ui.osoAdmin.osoOrgInfos frame = new bond.ui.osoAdmin.osoOrgInfos(orgId);
                    frame.setVisible(true);
                    this.dispose();
                });
                jPanel2.add(btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, y, 600, 100));
                
                javax.swing.JButton btnCreateAdmin = new javax.swing.JButton("+ Assign Admin");
                btnCreateAdmin.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                btnCreateAdmin.setForeground(java.awt.Color.WHITE);
                btnCreateAdmin.setBackground(new java.awt.Color(28, 94, 56));
                btnCreateAdmin.setBorderPainted(false);
                btnCreateAdmin.setFocusPainted(false);
                btnCreateAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnCreateAdmin.setBounds(638, y + 37, 122, 26);
                final int thisOrgId = id;
                final String thisOrgName = name;
                btnCreateAdmin.addActionListener(e2 -> showCreateAdminDialog(thisOrgId, thisOrgName));
                jPanel2.add(btnCreateAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, y + 37, 122, 26));

                y += 110;
            }

            jPanel2.setPreferredSize(new java.awt.Dimension(780, y + 20));
            jPanel2.revalidate();
            jPanel2.repaint();
            conn.close();

        } catch (Exception ex) {
             ex.printStackTrace();
        }

        loadPendingRegistrations();
    }

    private void loadPendingRegistrations() {

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.ResultSet rs = conn.prepareStatement(
                "SELECT form_id, proposed_org_name, proposed_classification, contact_email, review_status FROM registration_form ORDER BY form_id DESC"
            ).executeQuery();

            int baseY = 0;

            // count existing children to know where to start Y
            for (java.awt.Component c : jPanel2.getComponents()) {
                baseY = Math.max(baseY, c.getY() + c.getHeight());
            }

            baseY += 30;

            javax.swing.JLabel sectionTitle = new javax.swing.JLabel("Registration Submissions");
            sectionTitle.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 18));
            sectionTitle.setForeground(new java.awt.Color(28, 94, 56));
            sectionTitle.setBounds(50, baseY, 400, 30);
            jPanel2.add(sectionTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, baseY, 400, 30));

            baseY += 40;

            boolean hasRows = false;

            while (rs.next()) {

                hasRows = true;
                int fId = rs.getInt("form_id");
                String orgN = rs.getString("proposed_org_name");
                String classif = rs.getString("proposed_classification");
                String email = rs.getString("contact_email");
                String status = rs.getString("review_status");

                javax.swing.JLabel lblOrg = new javax.swing.JLabel(orgN + " — " + classif);
                lblOrg.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
                lblOrg.setForeground(new java.awt.Color(28, 94, 56));
                lblOrg.setBounds(60, baseY + 10, 400, 20);
                jPanel2.add(lblOrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, baseY + 10, 400, 20));

                javax.swing.JLabel lblEmail = new javax.swing.JLabel(email);
                lblEmail.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                lblEmail.setForeground(new java.awt.Color(100, 130, 110));
                lblEmail.setBounds(60, baseY + 30, 350, 18);
                jPanel2.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, baseY + 30, 350, 18));

                java.awt.Color statusColor = status.equals("Approved") ? new java.awt.Color(28, 94, 56)
                        : status.equals("Rejected") ? new java.awt.Color(180, 30, 30)
                        : new java.awt.Color(180, 130, 0);

                javax.swing.JLabel lblStatus = new javax.swing.JLabel("● " + status);
                lblStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lblStatus.setForeground(statusColor);
                lblStatus.setBounds(500, baseY + 20, 120, 20);
                jPanel2.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, baseY + 20, 120, 20));

                if (status.equals("Pending")) {

                    javax.swing.JButton approveBtn = new javax.swing.JButton("Approve");
                    approveBtn.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                    approveBtn.setForeground(java.awt.Color.WHITE);
                    approveBtn.setBackground(new java.awt.Color(28, 94, 56));
                    approveBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));
                    approveBtn.setFocusPainted(false);
                    approveBtn.setBounds(630, baseY + 12, 80, 28);
                    final int formId = fId;
                    approveBtn.addActionListener(e -> {
                        updateFormStatus(formId, "Approved");
                    });
                    jPanel2.add(approveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, baseY + 12, 80, 28));

                    javax.swing.JButton rejectBtn = new javax.swing.JButton("Reject");
                    rejectBtn.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                    rejectBtn.setForeground(java.awt.Color.WHITE);
                    rejectBtn.setBackground(new java.awt.Color(180, 30, 30));
                    rejectBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));
                    rejectBtn.setFocusPainted(false);
                    rejectBtn.setBounds(715, baseY + 12, 70, 28);
                    rejectBtn.addActionListener(e -> {
                        updateFormStatus(formId, "Rejected");
                    });
                    jPanel2.add(rejectBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, baseY + 12, 70, 28));
                }

                baseY += 70;
            }

            if (!hasRows) {
                javax.swing.JLabel none = new javax.swing.JLabel("No registration submissions yet.");
                none.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                none.setForeground(new java.awt.Color(150, 150, 150));
                none.setBounds(60, baseY, 300, 20);
                jPanel2.add(none, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, baseY, 300, 20));
                baseY += 30;
            }

            jPanel2.setPreferredSize(new java.awt.Dimension(780, baseY + 60));
            jPanel2.revalidate();
            jPanel2.repaint();
            conn.close();

        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }

    private void updateFormStatus(int formId, String newStatus) {

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE registration_form SET review_status = ? WHERE form_id = ?"
            );
            ps.setString(1, newStatus);
            ps.setInt(2, formId);
            ps.executeUpdate();
            conn.close();

            javax.swing.JOptionPane.showMessageDialog(this, "Registration " + newStatus + ".");

            // reload
            jPanel2.removeAll();
            loadOrganizations();

        } catch (Exception ex) {
            System.out.println("Update form status error: " + ex.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JButton();
        adminprofBtn = new javax.swing.JButton();
        orgBtn = new javax.swing.JButton();
        dashboardBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        orgName = new javax.swing.JLabel();
        memberCount = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        active = new javax.swing.JLabel();
        orgInfoBtn = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(248, 250, 249));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/navBar.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/sidebar.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 210, 540));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(248, 250, 249));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orgName.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        orgName.setForeground(new java.awt.Color(28, 94, 56));
        orgName.setText("POSHED");
        jPanel2.add(orgName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, 30));

        memberCount.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 12)); // NOI18N
        memberCount.setForeground(new java.awt.Color(28, 94, 56));
        memberCount.setText("Information Technology · 52 members");
        jPanel2.add(memberCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, 20));

        logo.setText("logo here");
        jPanel2.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        active.setForeground(new java.awt.Color(28, 94, 56));
        active.setText("●  Active");
        jPanel2.add(active, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, -1, -1));

        orgInfoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/OSOimages/orgsFrame.png"))); // NOI18N
        orgInfoBtn.setBorder(null);
        orgInfoBtn.setBorderPainted(false);
        orgInfoBtn.setContentAreaFilled(false);
        orgInfoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orgInfoBtnActionPerformed(evt);
            }
        });
        jPanel2.add(orgInfoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(28, 94, 56));
        jLabel13.setText("Organizations");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel14.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(28, 94, 56));
        jLabel14.setText("Click an organization to view members, posts, and info");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 800, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        // TODO add your handling code here:\

        new osoSettings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_settingsBtnActionPerformed

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

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        // TODO add your handling code here:
                 new osoDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void orgInfoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgInfoBtnActionPerformed
        // TODO add your handling code here:
           new osoOrgInfos().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_orgInfoBtnActionPerformed

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
            java.util.logging.Logger.getLogger(osoOrganizations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(osoOrganizations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(osoOrganizations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(osoOrganizations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new osoOrganizations().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel active;
    private javax.swing.JButton adminprofBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel memberCount;
    private javax.swing.JButton orgBtn;
    private javax.swing.JButton orgInfoBtn;
    private javax.swing.JLabel orgName;
    private javax.swing.JButton settingsBtn;
    // End of variables declaration//GEN-END:variables
}
