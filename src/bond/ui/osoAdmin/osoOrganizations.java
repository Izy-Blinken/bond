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
        dialog.setSize(480, 460);
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

        String currentUsername    = null;
        String currentDisplayName = null;
        String currentEmail       = null;
        try {
            java.sql.Connection chk = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = chk.prepareStatement(
                "SELECT username, display_name, email FROM org_admin WHERE org_id = ?");
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                currentUsername    = rs.getString("username");
                currentDisplayName = rs.getString("display_name");
                currentEmail       = rs.getString("email");
            }
            chk.close();
        } catch (Exception ex) {
            System.out.println("Check admin error: " + ex.getMessage());
        }

        if (currentUsername != null) {
            javax.swing.JLabel lblExisting = new javax.swing.JLabel(
                "Current admin: " + currentUsername + " (" + currentEmail + ")");
            lblExisting.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
            lblExisting.setForeground(new java.awt.Color(180, 100, 0));
            lblExisting.setBounds(20, 74, 440, 20);
            dialog.add(lblExisting);
        }

        javax.swing.JLabel lblDisplayName = new javax.swing.JLabel("Admin Display Name");
        lblDisplayName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblDisplayName.setForeground(new java.awt.Color(28, 94, 56));
        lblDisplayName.setBounds(20, 104, 360, 20);
        dialog.add(lblDisplayName);

        javax.swing.JTextField displayNameInput = new javax.swing.JTextField(
            currentDisplayName != null ? currentDisplayName : "");
        displayNameInput.setBounds(20, 126, 440, 28);
        displayNameInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        displayNameInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(displayNameInput);

        javax.swing.JLabel lblOfficer = new javax.swing.JLabel("Link to Officer (optional)");
        lblOfficer.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblOfficer.setForeground(new java.awt.Color(28, 94, 56));
        lblOfficer.setBounds(20, 164, 360, 20);
        dialog.add(lblOfficer);

        javax.swing.JComboBox<String> officerCombo = new javax.swing.JComboBox<>();
        officerCombo.addItem("— None —");
        officerCombo.setBounds(20, 186, 440, 28);
        officerCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        officerCombo.setBackground(java.awt.Color.WHITE);

        java.util.List<Integer> officerIds = new java.util.ArrayList<>();
        officerIds.add(-1);
        try {
            java.sql.Connection conn2 = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps2 = conn2.prepareStatement(
                "SELECT officer_id, full_name, position FROM officer WHERE org_id = ?");
            ps2.setInt(1, orgId);
            java.sql.ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                officerCombo.addItem(rs2.getString("full_name") + " — " + rs2.getString("position"));
                officerIds.add(rs2.getInt("officer_id"));
            }
            conn2.close();
        } catch (Exception ex) {
            System.out.println("Load officers error: " + ex.getMessage());
        }
        dialog.add(officerCombo);

        javax.swing.JLabel lblUser = new javax.swing.JLabel("Username");
        lblUser.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblUser.setForeground(new java.awt.Color(28, 94, 56));
        lblUser.setBounds(20, 226, 200, 20);
        dialog.add(lblUser);

        javax.swing.JTextField userInput = new javax.swing.JTextField(
            currentUsername != null ? currentUsername : "");
        userInput.setBounds(20, 248, 200, 28);
        userInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        userInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(userInput);

        javax.swing.JLabel lblEmail = new javax.swing.JLabel("Email");
        lblEmail.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblEmail.setForeground(new java.awt.Color(28, 94, 56));
        lblEmail.setBounds(240, 226, 200, 20);
        dialog.add(lblEmail);

        javax.swing.JTextField emailInput = new javax.swing.JTextField(
            currentEmail != null ? currentEmail : "");
        emailInput.setBounds(240, 248, 220, 28);
        emailInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        emailInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(emailInput);

        javax.swing.JLabel lblPass = new javax.swing.JLabel(
            currentUsername != null ? "New Password (leave blank to keep)" : "Password");
        lblPass.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        lblPass.setForeground(new java.awt.Color(28, 94, 56));
        lblPass.setBounds(20, 288, 440, 20);
        dialog.add(lblPass);

        javax.swing.JPasswordField passInput = new javax.swing.JPasswordField();
        passInput.setBounds(20, 310, 440, 28);
        passInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        passInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        dialog.add(passInput);

        final boolean isUpdate = (currentUsername != null);

        javax.swing.JButton btnCreate = new javax.swing.JButton(isUpdate ? "Update Account" : "Create Account");
        btnCreate.setBounds(280, 360, 180, 32);
        btnCreate.setBackground(new java.awt.Color(28, 94, 56));
        btnCreate.setForeground(java.awt.Color.WHITE);
        btnCreate.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCreate.setBorderPainted(false);
        btnCreate.setFocusPainted(false);
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.addActionListener(e -> {
            String displayName = displayNameInput.getText().trim();
            String username    = userInput.getText().trim();
            String email       = emailInput.getText().trim();
            String password    = new String(passInput.getPassword()).trim();

            if (displayName.isEmpty() || username.isEmpty() || email.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Display name, username, and email are required.");
                return;
            }
            if (!isUpdate && password.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Password is required for new accounts.");
                return;
            }

            try {
                java.sql.Connection conn3 = bond.db.DBConnection.getConnection();
                if (isUpdate) {
                    if (password.isEmpty()) {
                        java.sql.PreparedStatement ps3 = conn3.prepareStatement(
                            "UPDATE org_admin SET username=?, display_name=?, email=? WHERE org_id=?");
                        ps3.setString(1, username); ps3.setString(2, displayName); ps3.setString(3, email); ps3.setInt(4, orgId);
                        ps3.executeUpdate();
                    } else {
                        java.sql.PreparedStatement ps3 = conn3.prepareStatement(
                            "UPDATE org_admin SET username=?, display_name=?, email=?, password_hash=? WHERE org_id=?");
                        ps3.setString(1, username); ps3.setString(2, displayName); ps3.setString(3, email);
                        ps3.setString(4, bond.util.PasswordHasher.hash(password)); ps3.setInt(5, orgId);
                        ps3.executeUpdate();
                    }
                } else {
                    int osoId = bond.util.SessionManager.getCurrentOsoAdmin().getOso_admin_id();
                    java.sql.PreparedStatement psOfficer = conn3.prepareStatement(
                        "INSERT INTO officer (org_id, academic_year_id, full_name, position) " +
                        "VALUES (?, (SELECT academic_year_id FROM academic_year WHERE is_current=1 LIMIT 1), ?, 'President')",
                        java.sql.Statement.RETURN_GENERATED_KEYS);
                    psOfficer.setInt(1, orgId);
                    psOfficer.setString(2, username);
                    psOfficer.executeUpdate();
                    java.sql.ResultSet gkO = psOfficer.getGeneratedKeys();
                    int newOfficerId = gkO.next() ? gkO.getInt(1) : 0;

                    java.sql.PreparedStatement ps3 = conn3.prepareStatement(
                        "INSERT INTO org_admin (org_id, officer_id, created_by, username, display_name, password_hash, email) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps3.setInt(1, orgId);
                    ps3.setInt(2, newOfficerId);
                    ps3.setInt(3, osoId);
                    ps3.setString(4, username);
                    ps3.setString(5, displayName);
                    ps3.setString(6, bond.util.PasswordHasher.hash(password));
                    ps3.setString(7, email);
                    ps3.executeUpdate();
                }
                conn3.close();
                javax.swing.JOptionPane.showMessageDialog(dialog,
                    isUpdate ? "Admin account updated!" :
                    "Org Admin account created.\nUsername: " + username + "\nPassword: " + password);
                dialog.dispose();
                refresh();
            } catch (Exception ex) {
                System.out.println("Save admin error: " + ex.getMessage());
                javax.swing.JOptionPane.showMessageDialog(dialog, "Failed: " + ex.getMessage());
            }
        });
        dialog.add(btnCreate);

        javax.swing.JButton btnCancel = new javax.swing.JButton("Cancel");
        btnCancel.setBounds(190, 360, 80, 32);
        btnCancel.setBackground(new java.awt.Color(180, 210, 195));
        btnCancel.setForeground(new java.awt.Color(28, 94, 56));
        btnCancel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCancel.setBorderPainted(false); btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dialog.dispose());
        dialog.add(btnCancel);

        dialog.setVisible(true);
    }
    

    private void showEditOrgDialog(int orgId, String[] row) {
        javax.swing.JDialog d = new javax.swing.JDialog(this, "Edit Organization — " + row[1], true);
        d.setSize(540, 600);
        d.setLocationRelativeTo(this);
        d.setLayout(null);
        d.getContentPane().setBackground(new java.awt.Color(248, 250, 249));

        javax.swing.JPanel hdr = new javax.swing.JPanel(null);
        hdr.setBackground(new java.awt.Color(28, 94, 56));
        hdr.setBounds(0, 0, 540, 60);
        d.add(hdr);
        javax.swing.JLabel lTitle = new javax.swing.JLabel("Edit Organization Details");
        lTitle.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
        lTitle.setForeground(java.awt.Color.WHITE);
        lTitle.setBounds(16, 16, 500, 28);
        hdr.add(lTitle);

        // Fetch full org details from DB
        String[] dbVals = {"", "Academic", "Active", "", "", "", ""};
        
        try {
            java.sql.Connection c = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = c.prepareStatement(
                "SELECT o.org_name, o.classification, o.status, " +
                "COALESCE(o.mission,'') AS mission, " +
                "COALESCE(o.vision,'') AS vision, COALESCE(o.description,'') AS description, " +
                "(SELECT adv.full_name FROM adviser adv WHERE adv.org_id = o.org_id ORDER BY adv.adviser_id DESC LIMIT 1) AS adviser " +
                "FROM organization o WHERE o.org_id=?"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dbVals[0] = rs.getString("org_name");
                dbVals[1] = rs.getString("classification");
                dbVals[2] = rs.getString("status");
                dbVals[3] = rs.getString("adviser") != null ? rs.getString("adviser") : "";
                dbVals[4] = rs.getString("mission");
                dbVals[5] = rs.getString("vision");
                dbVals[6] = rs.getString("description");
            }
            c.close();
        } catch (Exception ex) { ex.printStackTrace(); }

        javax.swing.JScrollPane formScroll;
        javax.swing.JPanel formPanel = new javax.swing.JPanel(null);
        formPanel.setBackground(new java.awt.Color(248, 250, 249));

        String[] labels   = {"Org Name", "Classification", "Status", "Adviser", "Mission", "Vision", "Description"};
        boolean[] isLong  = {false,       false,             false,    false,     true,      true,     true};
        boolean[] isCombo = {false,       true,              true,     false,     false,     false,    false};

        javax.swing.JComponent[] inputs = new javax.swing.JComponent[labels.length];
        int dy = 10;

        for (int i = 0; i < labels.length; i++) {
            javax.swing.JLabel lbl = new javax.swing.JLabel(labels[i] + ":");
            lbl.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
            lbl.setForeground(new java.awt.Color(28, 94, 56));
            lbl.setBounds(16, dy, 120, 18);
            formPanel.add(lbl);

            if (isCombo[i]) {
                String[] options = i == 1
                    ? new String[]{"Academic", "Civic & Cultural", "Religious", "Media & Publications", "Sports & Recreation"}
                    : new String[]{"Active", "Inactive"};
                javax.swing.JComboBox<String> combo = new javax.swing.JComboBox<>(options);
                combo.setSelectedItem(dbVals[i]);
                
                combo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                combo.setBackground(java.awt.Color.WHITE);
                combo.setBounds(145, dy, 360, 26);
                formPanel.add(combo);
                inputs[i] = combo;
                dy += 34;
            } else if (isLong[i]) {
                javax.swing.JTextArea ta = new javax.swing.JTextArea(dbVals[i]);
                ta.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                ta.setLineWrap(true);
                ta.setWrapStyleWord(true);
                javax.swing.JScrollPane taSp = new javax.swing.JScrollPane(ta);
                taSp.setBounds(145, dy, 360, 70);
                taSp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
                formPanel.add(taSp);
                inputs[i] = ta;
                dy += 78;
            } else {
                javax.swing.JTextField tf = new javax.swing.JTextField(dbVals[i]);
                tf.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                tf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
                tf.setBounds(145, dy, 360, 26);
                formPanel.add(tf);
                inputs[i] = tf;
                dy += 34;
            }
        }

        formPanel.setPreferredSize(new java.awt.Dimension(510, dy + 20));
        formScroll = new javax.swing.JScrollPane(formPanel);
        formScroll.setBounds(10, 68, 515, 430);
        formScroll.setBorder(null);
        formScroll.getVerticalScrollBar().setUnitIncrement(12);
        d.add(formScroll);

        javax.swing.JButton btnSave = new javax.swing.JButton("Save Changes");
        btnSave.setBounds(330, 510, 160, 32);
        btnSave.setBackground(new java.awt.Color(28, 94, 56));
        btnSave.setForeground(java.awt.Color.WHITE);
        btnSave.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnSave.setBorderPainted(false); btnSave.setFocusPainted(false);
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(e -> {
            String newName    = ((javax.swing.JTextField)  inputs[0]).getText().trim();
            String newClass   = (String) ((javax.swing.JComboBox<?>) inputs[1]).getSelectedItem();
            String newStatus  = (String) ((javax.swing.JComboBox<?>) inputs[2]).getSelectedItem();
            String newAdviser = ((javax.swing.JTextField)  inputs[3]).getText().trim();
            String newMission = ((javax.swing.JTextArea)   inputs[4]).getText().trim();
            String newVision  = ((javax.swing.JTextArea)   inputs[5]).getText().trim();
            String newDesc    = ((javax.swing.JTextArea)   inputs[6]).getText().trim();

            if (newName.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(d, "Org name cannot be empty.");
                return;
            }
            try {
                java.sql.Connection conn = bond.db.DBConnection.getConnection();

                // Update organization
                java.sql.PreparedStatement psOrg = conn.prepareStatement(
                    "UPDATE organization SET org_name=?, classification=?, mission=?, " +
                    "vision=?, description=?, status=? WHERE org_id=?");
                psOrg.setString(1, newName);
                psOrg.setString(2, newClass);
                psOrg.setString(3, newMission);
                psOrg.setString(4, newVision);
                psOrg.setString(5, newDesc);
                psOrg.setString(6, newStatus);
                psOrg.setInt(7, orgId);
                psOrg.executeUpdate();

                // Update or insert adviser
                if (!newAdviser.isEmpty()) {
                    java.sql.PreparedStatement psChk = conn.prepareStatement(
                        "SELECT adviser_id FROM adviser WHERE org_id=? " +
                        "ORDER BY adviser_id DESC LIMIT 1");
                    psChk.setInt(1, orgId);
                    java.sql.ResultSet rsChk = psChk.executeQuery();
                    if (rsChk.next()) {
                        java.sql.PreparedStatement psAdv = conn.prepareStatement(
                            "UPDATE adviser SET full_name=? WHERE adviser_id=?");
                        psAdv.setString(1, newAdviser);
                        psAdv.setInt(2, rsChk.getInt("adviser_id"));
                        psAdv.executeUpdate();
                    } else {
                        java.sql.PreparedStatement psAdv = conn.prepareStatement(
                            "INSERT INTO adviser (org_id, academic_year_id, full_name) " +
                            "VALUES (?, (SELECT academic_year_id FROM academic_year WHERE is_current=1 LIMIT 1), ?)");
                        psAdv.setInt(1, orgId);
                        psAdv.setString(2, newAdviser);
                        psAdv.executeUpdate();
                    }
                }

                // Sync org_admin is_active with status
                java.sql.PreparedStatement psAct = conn.prepareStatement(
                    "UPDATE org_admin SET is_active=? WHERE org_id=?");
                psAct.setInt(1, "Active".equals(newStatus) ? 1 : 0);
                psAct.setInt(2, orgId);
                psAct.executeUpdate();

                conn.close();

                // Update row[] for immediate display refresh
                row[1]  = newName;
                row[2]  = newClass;
                row[3]  = newStatus;
                row[4]  = newAdviser;
                row[9]  = newMission;
                row[10] = newVision;

                javax.swing.JOptionPane.showMessageDialog(d, "Organization updated successfully!");
                d.dispose();
                expandedOrgs.put(orgId, true);
                refresh();
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(d, "Error: " + ex.getMessage());
            }
        });
        d.add(btnSave);

        javax.swing.JButton btnCancel = new javax.swing.JButton("Cancel");
        btnCancel.setBounds(240, 510, 80, 32);
        btnCancel.setBackground(new java.awt.Color(180, 210, 195));
        btnCancel.setForeground(new java.awt.Color(28, 94, 56));
        btnCancel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCancel.setBorderPainted(false); btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(e -> d.dispose());
        d.add(btnCancel);

        d.setVisible(true);
    }
    
    private void refresh() {
        jPanel2.removeAll();
        loadOrganizations();
    }

    private java.util.Map<Integer, Boolean> expandedOrgs = new java.util.HashMap<>();

    private void loadOrganizations() {
        // Hide all hardcoded generated labels/buttons
        orgName.setVisible(false);
        memberCount.setVisible(false);
        logo.setVisible(false);
        active.setVisible(false);
        orgInfoBtn.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);

        javax.swing.JLabel lTitle = new javax.swing.JLabel("Organizations");
        lTitle.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(28, 94, 56));
        jPanel2.add(lTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 400, 35));

        javax.swing.JLabel lSub = new javax.swing.JLabel("Approved organizations — click to expand details");
        lSub.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
        lSub.setForeground(new java.awt.Color(28, 94, 56));
        jPanel2.add(lSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 58, 600, 20));

        javax.swing.JTextField searchField = new javax.swing.JTextField();
        searchField.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        searchField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)),
            javax.swing.BorderFactory.createEmptyBorder(2, 6, 2, 6)));
        searchField.putClientProperty("JTextField.placeholderText", "Search organizations…");
        jPanel2.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 280, 28));

        javax.swing.JPanel orgListPanel = new javax.swing.JPanel(null);
        orgListPanel.setBackground(new java.awt.Color(248, 250, 249));
        jPanel2.add(orgListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 760, Integer.MAX_VALUE));

        java.util.List<String[]> orgRows = new java.util.ArrayList<>();
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.ResultSet rs = conn.prepareStatement(
                "SELECT o.org_id, o.org_name, o.classification, o.status, " +
                "COALESCE(o.description,'') AS description, " +
                "COALESCE(o.mission,'') AS mission, " +
                "COALESCE(o.vision,'') AS vision, " +
                "DATE_FORMAT(o.created_at,'%Y-%m-%d') AS created_at, " +
                "COALESCE(o.logo_path,'') AS logo_path, " +
                "oa.display_name AS admin_name, oa.username AS admin_username, oa.email AS admin_email, " +
                "(SELECT COUNT(*) FROM members m WHERE m.org_id = o.org_id) AS member_count, " +
                "(SELECT adv.full_name FROM adviser adv " +
                " WHERE adv.org_id = o.org_id " +
                " ORDER BY adv.adviser_id DESC LIMIT 1) AS adviser_name " +
                "FROM organization o LEFT JOIN org_admin oa ON oa.org_id = o.org_id " +
                "WHERE o.status IN ('Active','Inactive') " +
                "ORDER BY o.org_name ASC"
            ).executeQuery();
            while (rs.next()) {
                String createdAt = rs.getString("created_at");
                String photoPath = rs.getString("logo_path");
                orgRows.add(new String[]{
                    String.valueOf(rs.getInt("org_id")),
                    rs.getString("org_name"),
                    rs.getString("classification"),
                    rs.getString("status"),
                    rs.getString("adviser_name") != null ? rs.getString("adviser_name") : "—",
                    rs.getString("admin_name") != null ? rs.getString("admin_name") : null,
                    rs.getString("admin_email")  != null ? rs.getString("admin_email")  : "—",
                    String.valueOf(rs.getInt("member_count")),
                    createdAt != null ? createdAt : "—",
                    rs.getString("mission") != null ? rs.getString("mission") : "—",
                    rs.getString("vision")  != null ? rs.getString("vision")  : "—",
                    "—",
                    photoPath != null ? photoPath : ""
                });
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Track the orgListPanel's rendered height so loadPendingRegistrations can place itself below it
        final int[] orgListRenderedHeight = {600};

        final Runnable[] renderOrgsHolder = new Runnable[1];
        renderOrgsHolder[0] = new Runnable() {
            public void run() {
                String query = searchField.getText().trim().toLowerCase();
                orgListPanel.removeAll();
                int y = 0;
                boolean any = false;

                for (String[] row : orgRows) {
                    String name  = row[1];
                    String cls   = row[2];
                    String adm   = row[5] != null ? row[5] : "";
                    if (!query.isEmpty() &&
                        !name.toLowerCase().contains(query) &&
                        !cls.toLowerCase().contains(query) &&
                        !adm.toLowerCase().contains(query)) continue;

                    any = true;
                    final int oid  = Integer.parseInt(row[0]);
                    boolean expanded = expandedOrgs.getOrDefault(oid, false);
                    String status    = row[3];
                    String photoPath = row[12];

                    javax.swing.JPanel rowPanel = new javax.swing.JPanel(null);
                    rowPanel.setBackground(new java.awt.Color(237, 245, 240));
                    rowPanel.setBorder(javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(180, 210, 195)));
                    rowPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                    int photoSize = 42;
                    boolean photoLoaded = false;
                    if (photoPath != null && !photoPath.isEmpty()) {
                        try {
                            java.net.URL url = getClass().getClassLoader().getResource(photoPath);
                            if (url != null) {
                                java.awt.Image img = new javax.swing.ImageIcon(url).getImage()
                                    .getScaledInstance(photoSize, photoSize, java.awt.Image.SCALE_SMOOTH);
                                javax.swing.JLabel photoLabel = new javax.swing.JLabel(new javax.swing.ImageIcon(img));
                                photoLabel.setBounds(10, 14, photoSize, photoSize);
                                rowPanel.add(photoLabel);
                                photoLoaded = true;
                            }
                        } catch (Exception ignored) {}
                    }
                    if (!photoLoaded) {
                        final String initLetter = name.substring(0, 1).toUpperCase();
                        javax.swing.JLabel circleLabel = new javax.swing.JLabel() {
                            @Override protected void paintComponent(java.awt.Graphics g) {
                                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
                                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                                g2.setColor(new java.awt.Color(28, 94, 56, 60));
                                g2.fillOval(0, 0, photoSize - 1, photoSize - 1);
                                g2.setColor(new java.awt.Color(28, 94, 56));
                                g2.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 16));
                                java.awt.FontMetrics fm = g2.getFontMetrics();
                                g2.drawString(initLetter,
                                    (photoSize - fm.stringWidth(initLetter)) / 2,
                                    (photoSize + fm.getAscent() - fm.getDescent()) / 2);
                            }
                        };
                        circleLabel.setBounds(10, 14, photoSize, photoSize);
                        rowPanel.add(circleLabel);
                    }

                    javax.swing.JLabel lblName = new javax.swing.JLabel(name);
                    lblName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
                    lblName.setForeground(new java.awt.Color(28, 94, 56));
                    lblName.setBounds(62, 10, 360, 24);
                    rowPanel.add(lblName);

                    javax.swing.JLabel lblInfo = new javax.swing.JLabel(cls + " · " + row[7] + " members");
                    lblInfo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                    lblInfo.setForeground(new java.awt.Color(100, 140, 120));
                    lblInfo.setBounds(62, 36, 380, 18);
                    rowPanel.add(lblInfo);

                    java.awt.Color sc = "Active".equals(status)
                        ? new java.awt.Color(28, 94, 56) : new java.awt.Color(180, 30, 30);
                    javax.swing.JLabel lblStatus = new javax.swing.JLabel("● " + status);
                    lblStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                    lblStatus.setForeground(sc);
                    lblStatus.setBounds(530, 28, 110, 20);
                    rowPanel.add(lblStatus);

                    javax.swing.JLabel lblArrow = new javax.swing.JLabel(expanded ? "▲" : "▼");
                    lblArrow.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                    lblArrow.setForeground(new java.awt.Color(28, 94, 56));
                    lblArrow.setBounds(700, 28, 20, 20);
                    rowPanel.add(lblArrow);

                    final String[] fRow = row;
                    rowPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            expandedOrgs.put(oid, !expandedOrgs.getOrDefault(oid, false));
                            renderOrgsHolder[0].run();
                        }
                        public void mouseEntered(java.awt.event.MouseEvent e) {
                            rowPanel.setBackground(new java.awt.Color(210, 235, 220)); }
                        public void mouseExited(java.awt.event.MouseEvent e) {
                            rowPanel.setBackground(new java.awt.Color(237, 245, 240)); }
                    });

                    orgListPanel.add(rowPanel);
                    rowPanel.setBounds(30, y, 720, 72);
                    y += 80;

                    if (expanded) {
                        javax.swing.JPanel expPanel = new javax.swing.JPanel(null);
                        expPanel.setBackground(new java.awt.Color(248, 252, 250));
                        expPanel.setBorder(javax.swing.BorderFactory.createLineBorder(
                            new java.awt.Color(200, 225, 210)));

                        String[][] details = {
                            {"Org Name",       row[1]},
                            {"Classification", row[2]},
                            {"Adviser",        row[4]},
                            {"Org Admin",      row[5] != null ? row[5] : "Not assigned"},
                            {"Admin Email",    row[6]},
                            {"Members",        row[7]},
                            {"Created",        row[8]},
                            {"Mission",        row[9]},
                            {"Vision",         row[10]},
                            {"Status",         row[3]},
                        };
                        int dy = 10;
                        for (String[] dr : details) {
                            javax.swing.JLabel lk = new javax.swing.JLabel(dr[0] + ":");
                            lk.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                            lk.setForeground(new java.awt.Color(28, 94, 56));
                            lk.setBounds(16, dy, 130, 18);
                            expPanel.add(lk);
                            
                            boolean isLongField = dr[0].equals("Mission") || dr[0].equals("Vision");
                            if (isLongField) {
                                javax.swing.JTextArea ta = new javax.swing.JTextArea(dr[1] != null ? dr[1] : "—");
                                ta.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                                ta.setForeground(new java.awt.Color(40, 40, 40));
                                ta.setBackground(new java.awt.Color(248, 252, 250));
                                ta.setLineWrap(true);
                                ta.setWrapStyleWord(true);
                                ta.setEditable(false);
                                ta.setBorder(null);
                                ta.setBounds(155, dy, 380, 55);
                                expPanel.add(ta);
                                dy += 60;
                            } else {
                                javax.swing.JLabel lv = new javax.swing.JLabel(
                                    "<html>" + (dr[1] != null ? dr[1] : "—") + "</html>");
                                lv.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                                lv.setForeground(new java.awt.Color(40, 40, 40));
                                lv.setBounds(155, dy, 380, 18);
                                expPanel.add(lv);
                                dy += 22;
                            }
                        }

                        boolean isActive = "Active".equals(status);
                        javax.swing.JButton btnToggle = new javax.swing.JButton(
                            isActive ? "Set Inactive" : "Set Active");
                        btnToggle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                        btnToggle.setForeground(java.awt.Color.WHITE);
                        btnToggle.setBackground(isActive
                            ? new java.awt.Color(180, 30, 30) : new java.awt.Color(28, 94, 56));
                        btnToggle.setBorderPainted(false); btnToggle.setFocusPainted(false);
                        btnToggle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnToggle.setBounds(540, 10, 120, 26);
                        btnToggle.addActionListener(e -> {
                            String newSt = isActive ? "Inactive" : "Active";
                            try {
                                java.sql.Connection cT = bond.db.DBConnection.getConnection();
                                java.sql.PreparedStatement psT = cT.prepareStatement(
                                    "UPDATE organization SET status=? WHERE org_id=?");
                                psT.setString(1, newSt); psT.setInt(2, oid); psT.executeUpdate();
                                java.sql.PreparedStatement psA = cT.prepareStatement(
                                    "UPDATE org_admin SET is_active=? WHERE org_id=?");
                                psA.setInt(1, "Active".equals(newSt) ? 1 : 0);
                                psA.setInt(2, oid); psA.executeUpdate();
                                cT.close();
                                fRow[3] = newSt;
                                expandedOrgs.put(oid, true);
                                renderOrgsHolder[0].run();
                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(null,
                                    "Toggle error: " + ex.getMessage());
                            }
                        });
                        expPanel.add(btnToggle);

                        javax.swing.JButton btnAdmin = new javax.swing.JButton(
                            row[5] != null ? "Edit Admin" : "+ Assign Admin");
                        btnAdmin.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                        btnAdmin.setForeground(java.awt.Color.WHITE);
                        btnAdmin.setBackground(new java.awt.Color(28, 94, 56));
                        btnAdmin.setBorderPainted(false); btnAdmin.setFocusPainted(false);
                        btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAdmin.setBounds(540, 44, 120, 26);
                        btnAdmin.addActionListener(e -> showCreateAdminDialog(oid, fRow[1]));
                        expPanel.add(btnAdmin);
                        
                        javax.swing.JButton btnEdit = new javax.swing.JButton("✎ Edit Details");
                        btnEdit.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                        btnEdit.setForeground(java.awt.Color.WHITE);
                        btnEdit.setBackground(new java.awt.Color(70, 130, 180));
                        btnEdit.setBorderPainted(false); btnEdit.setFocusPainted(false);
                        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnEdit.setBounds(540, 78, 120, 26);
                        btnEdit.addActionListener(e -> showEditOrgDialog(oid, fRow));
                        expPanel.add(btnEdit);

                        int expH = Math.max(dy + 60, 120);
                        orgListPanel.add(expPanel);
                        expPanel.setBounds(30, y, 720, expH);
                        y += expH + 8;
                    }
                    y += 8;
                }

                if (!any) {
                    javax.swing.JLabel none = new javax.swing.JLabel("No organizations found.");
                    none.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.ITALIC, 13));
                    none.setForeground(new java.awt.Color(160, 160, 160));
                    orgListPanel.add(none);
                    none.setBounds(40, 20, 400, 24);
                    y += 40;
                }

                orgListPanel.setPreferredSize(new java.awt.Dimension(760, y + 20));
                orgListPanel.revalidate();
                orgListPanel.repaint();

                int newHeight = y + 20;
                orgListRenderedHeight[0] = newHeight;
                orgListPanel.setBounds(0, 90, 760, newHeight);

                jPanel2.setPreferredSize(new java.awt.Dimension(780, 90 + newHeight + 100));
                jPanel2.revalidate();
                jPanel2.repaint();
            }
        };

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { renderOrgsHolder[0].run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { renderOrgsHolder[0].run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { renderOrgsHolder[0].run(); }
        });

        renderOrgsHolder[0].run();

        // Pass the fixed baseY so pending registrations render below the org list
        loadPendingRegistrations(90 + orgListRenderedHeight[0] + 30);
    }


    private void loadPendingRegistrations(int baseY) {
        javax.swing.JLabel sectionTitle = new javax.swing.JLabel("Registration Submissions");
        sectionTitle.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 18));
        sectionTitle.setForeground(new java.awt.Color(28, 94, 56));
        jPanel2.add(sectionTitle,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(50, baseY, 500, 30));
        baseY += 40;

        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.ResultSet rs = conn.prepareStatement(
                "SELECT form_id, proposed_org_name, proposed_classification, " +
                "contact_email, COALESCE(objectives,'') AS description, " +
                "COALESCE(mission,'') AS mission, COALESCE(vision,'') AS vision, " +
                "COALESCE(adviser,'') AS adviser, COALESCE(appointed_by,'') AS appointed_by " +
                "FROM registration_form WHERE review_status = 'Pending' ORDER BY form_id DESC"
            ).executeQuery();

            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                final int    fId        = rs.getInt("form_id");
                final String orgN       = rs.getString("proposed_org_name");
                final String classif    = rs.getString("proposed_classification");
                final String email      = rs.getString("contact_email");
                final String fDesc      = rs.getString("description");
                final String fMission   = rs.getString("mission");
                final String fVision    = rs.getString("vision");
                final String fAdviser   = rs.getString("adviser");
                final String fAppointed = rs.getString("appointed_by");

                javax.swing.JPanel rowPanel = new javax.swing.JPanel(null);
                rowPanel.setBackground(new java.awt.Color(237, 245, 240));
                rowPanel.setBorder(javax.swing.BorderFactory.createLineBorder(
                    new java.awt.Color(180, 210, 195)));
                rowPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                javax.swing.JLabel lblOrg = new javax.swing.JLabel(orgN + " — " + classif);
                lblOrg.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
                lblOrg.setForeground(new java.awt.Color(28, 94, 56));
                lblOrg.setBounds(15, 8, 400, 20);
                rowPanel.add(lblOrg);

                javax.swing.JLabel lblEmail = new javax.swing.JLabel(email);
                lblEmail.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                lblEmail.setForeground(new java.awt.Color(100, 130, 110));
                lblEmail.setBounds(15, 30, 350, 18);
                rowPanel.add(lblEmail);

                javax.swing.JLabel lblStatus = new javax.swing.JLabel("● Pending");
                lblStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lblStatus.setForeground(new java.awt.Color(180, 130, 0));
                lblStatus.setBounds(560, 20, 120, 20);
                rowPanel.add(lblStatus);

                rowPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        showRegistrationDialog(fId, orgN, classif, email, fDesc,
                            fMission, fVision, fAdviser, fAppointed);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        rowPanel.setBackground(new java.awt.Color(210, 235, 220)); }
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        rowPanel.setBackground(new java.awt.Color(237, 245, 240)); }
                });

                jPanel2.add(rowPanel,
                    new org.netbeans.lib.awtextra.AbsoluteConstraints(40, baseY, 720, 60));
                baseY += 70;
            }

            if (!hasRows) {
                javax.swing.JLabel none = new javax.swing.JLabel("No pending registration submissions.");
                none.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.ITALIC, 12));
                none.setForeground(new java.awt.Color(150, 150, 150));
                jPanel2.add(none,
                    new org.netbeans.lib.awtextra.AbsoluteConstraints(60, baseY, 400, 20));
                baseY += 30;
            }

            jPanel2.setPreferredSize(new java.awt.Dimension(780, baseY + 80));
            jPanel2.revalidate();
            jPanel2.repaint();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void showRegistrationDialog(int formId, String orgName, String classification,
        String email, String description, String mission, String vision,
        String adviser, String appointedBy) {

        javax.swing.JDialog d = new javax.swing.JDialog(this, "Registration — " + orgName, true);
        d.setSize(560, 520);
        d.setLocationRelativeTo(this);
        d.setLayout(null);
        d.getContentPane().setBackground(new java.awt.Color(248, 250, 249));

        javax.swing.JPanel hdr = new javax.swing.JPanel(null);
        hdr.setBackground(new java.awt.Color(28, 94, 56));
        hdr.setBounds(0, 0, 560, 60);
        d.add(hdr);
        javax.swing.JLabel lTitle = new javax.swing.JLabel("Registration Proposal — " + orgName);
        lTitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
        lTitle.setForeground(java.awt.Color.WHITE);
        lTitle.setBounds(16, 16, 520, 28);
        hdr.add(lTitle);

        javax.swing.JPanel details = new javax.swing.JPanel(null);
        details.setBackground(new java.awt.Color(248, 250, 249));
        javax.swing.JScrollPane sp = new javax.swing.JScrollPane(details);
        sp.setBounds(10, 68, 530, 300);
        sp.setBorder(null);
        d.add(sp);

        String[][] rows = {
            {"Org Name",       orgName},
            {"Classification", classification},
            {"Contact Email",  email},
            {"Adviser",        adviser != null && !adviser.isEmpty() ? adviser : "—"},
            {"Org Admin",      appointedBy != null && !appointedBy.isEmpty() ? appointedBy : "—"},
            {"Mission",        mission != null && !mission.isEmpty() ? mission : "—"},
            {"Vision",         vision  != null && !vision.isEmpty()  ? vision  : "—"},
            {"Description",    description != null && !description.isEmpty() ? description : "—"},
        };
        int dy = 10;
        for (String[] row : rows) {
            javax.swing.JLabel lk = new javax.swing.JLabel(row[0] + ":");
            lk.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
            lk.setForeground(new java.awt.Color(28, 94, 56));
            lk.setBounds(10, dy, 130, 18);
            details.add(lk);

            boolean isLong = row[0].equals("Mission") || row[0].equals("Vision") || row[0].equals("Description");
            if (isLong) {
                javax.swing.JTextArea ta = new javax.swing.JTextArea(row[1]);
                ta.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                ta.setForeground(new java.awt.Color(40, 40, 40));
                ta.setBackground(new java.awt.Color(248, 250, 249));
                ta.setLineWrap(true);
                ta.setWrapStyleWord(true);
                ta.setEditable(false);
                ta.setBorder(null);
                ta.setBounds(145, dy, 360, 60);
                details.add(ta);
                dy += 66;
            } else {
                javax.swing.JLabel lv = new javax.swing.JLabel(
                    "<html><body style='width:340px'>" + row[1] + "</body></html>");
                lv.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                lv.setForeground(new java.awt.Color(40, 40, 40));
                lv.setBounds(145, dy, 360, 48);
                details.add(lv);
                dy += 54;
            }
        }
        details.setPreferredSize(new java.awt.Dimension(520, dy + 20));

        javax.swing.JLabel lDN = new javax.swing.JLabel("Admin Display Name:");
        lDN.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        lDN.setForeground(new java.awt.Color(28, 94, 56));
        lDN.setBounds(12, 358, 160, 18);
        d.add(lDN);

        javax.swing.JTextField fDisplayName = new javax.swing.JTextField(
            appointedBy != null && !appointedBy.isEmpty() ? appointedBy : "");
        fDisplayName.setBounds(12, 378, 160, 26);
        fDisplayName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        fDisplayName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        d.add(fDisplayName);

        javax.swing.JLabel lUH = new javax.swing.JLabel("Login Username:");
        lUH.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        lUH.setForeground(new java.awt.Color(28, 94, 56));
        lUH.setBounds(182, 358, 130, 18);
        d.add(lUH);

        javax.swing.JTextField fUser = new javax.swing.JTextField("");
        fUser.setBounds(182, 378, 130, 26);
        fUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        fUser.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        d.add(fUser);

        javax.swing.JLabel lPH = new javax.swing.JLabel("Password:");
        lPH.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        lPH.setForeground(new java.awt.Color(28, 94, 56));
        lPH.setBounds(322, 358, 100, 18);
        d.add(lPH);

        javax.swing.JPasswordField fPass = new javax.swing.JPasswordField();
        fPass.setBounds(322, 378, 220, 26);
        fPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
        fPass.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        d.add(fPass);

        javax.swing.JButton btnAccept = new javax.swing.JButton("✓ Accept");
        btnAccept.setBounds(10, 442, 120, 32);
        btnAccept.setBackground(new java.awt.Color(28, 94, 56));
        btnAccept.setForeground(java.awt.Color.WHITE);
        btnAccept.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnAccept.setBorderPainted(false); btnAccept.setFocusPainted(false);
        btnAccept.addActionListener(e -> {
            String displayName = fDisplayName.getText().trim();
            String uname       = fUser.getText().trim();
            String pass        = new String(fPass.getPassword()).trim();
            if (displayName.isEmpty() || uname.isEmpty() || pass.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(d, "Display name, username, and password are all required.");
                return;
            }
            try {
                java.sql.Connection conn = bond.db.DBConnection.getConnection();
                int osoId = bond.util.SessionManager.getCurrentOsoAdmin().getOso_admin_id();

                java.sql.PreparedStatement psO = conn.prepareStatement(
                    "INSERT INTO organization (form_id, org_name, classification, status, description, mission, vision, created_by) " +
                    "VALUES (?, ?, ?, 'Active', ?, ?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);
                psO.setInt(1, formId);
                psO.setString(2, orgName);
                psO.setString(3, classification);
                psO.setString(4, description != null ? description : "");
                psO.setString(5, mission    != null ? mission    : "");
                psO.setString(6, vision     != null ? vision     : "");
                psO.setInt(7, osoId);
                psO.executeUpdate();
                java.sql.ResultSet gk = psO.getGeneratedKeys();
                int newOrgId = gk.next() ? gk.getInt(1) : 0;

                java.sql.PreparedStatement psOfficer = conn.prepareStatement(
                    "INSERT INTO officer (org_id, academic_year_id, full_name, position) " +
                    "VALUES (?, (SELECT academic_year_id FROM academic_year WHERE is_current=1 LIMIT 1), ?, 'President')",
                    java.sql.Statement.RETURN_GENERATED_KEYS);
                psOfficer.setInt(1, newOrgId);
                psOfficer.setString(2, displayName);
                psOfficer.executeUpdate();
                java.sql.ResultSet gkO = psOfficer.getGeneratedKeys();
                int newOfficerId = gkO.next() ? gkO.getInt(1) : 0;

                java.sql.PreparedStatement psA = conn.prepareStatement(
                    "INSERT INTO org_admin (org_id, officer_id, created_by, username, display_name, password_hash, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
                psA.setInt(1, newOrgId);
                psA.setInt(2, newOfficerId);
                psA.setInt(3, osoId);
                psA.setString(4, uname);
                psA.setString(5, displayName);
                psA.setString(6, bond.util.PasswordHasher.hash(pass));
                psA.setString(7, email);
                psA.executeUpdate();

                if (adviser != null && !adviser.isEmpty()) {
                    java.sql.PreparedStatement psAdv = conn.prepareStatement(
                        "INSERT INTO adviser (org_id, academic_year_id, full_name) " +
                        "VALUES (?, (SELECT academic_year_id FROM academic_year WHERE is_current=1 LIMIT 1), ?)");
                    psAdv.setInt(1, newOrgId);
                    psAdv.setString(2, adviser);
                    psAdv.executeUpdate();
                }

                java.sql.PreparedStatement psR = conn.prepareStatement(
                    "UPDATE registration_form SET review_status='Approved', " +
                    "reviewed_by=?, reviewed_at=NOW() WHERE form_id=?");
                psR.setInt(1, osoId);
                psR.setInt(2, formId);
                psR.executeUpdate();
                conn.close();

                javax.swing.JOptionPane.showMessageDialog(d,
                    "Organization approved!\nAdmin Name: " + displayName + "\nLogin Username: " + uname + "\nEmail: " + email);
                d.dispose();
                refresh();
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(d, "Error: " + ex.getMessage());
            }
        });
        d.add(btnAccept);

        javax.swing.JButton btnReject = new javax.swing.JButton("✗ Reject");
        btnReject.setBounds(140, 442, 100, 32);
        btnReject.setBackground(new java.awt.Color(180, 30, 30));
        btnReject.setForeground(java.awt.Color.WHITE);
        btnReject.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnReject.setBorderPainted(false); btnReject.setFocusPainted(false);
        btnReject.addActionListener(e -> {
            int c = javax.swing.JOptionPane.showConfirmDialog(d,
                "Reject this registration proposal?",
                "Confirm", javax.swing.JOptionPane.YES_NO_OPTION);
            if (c == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    java.sql.Connection conn = bond.db.DBConnection.getConnection();
                    int osoId = bond.util.SessionManager.getCurrentOsoAdmin().getOso_admin_id();
                    java.sql.PreparedStatement ps = conn.prepareStatement(
                        "UPDATE registration_form SET review_status='Rejected', " +
                        "reviewed_by=?, reviewed_at=NOW() WHERE form_id=?");
                    ps.setInt(1, osoId);
                    ps.setInt(2, formId);
                    ps.executeUpdate();
                    conn.close();
                    d.dispose();
                    refresh();
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(d, "Error: " + ex.getMessage());
                }
            }
        });
        d.add(btnReject);

        javax.swing.JButton btnCancel = new javax.swing.JButton("Cancel");
        btnCancel.setBounds(250, 442, 80, 32);
        btnCancel.setBackground(new java.awt.Color(180, 210, 195));
        btnCancel.setForeground(new java.awt.Color(28, 94, 56));
        btnCancel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCancel.setBorderPainted(false); btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(e -> d.dispose());
        d.add(btnCancel);

        d.setVisible(true);
    }

    private void setupExitButton() {
        javax.swing.JButton btnExit = new javax.swing.JButton("Exit Admin");
        btnExit.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnExit.setForeground(new java.awt.Color(200, 40, 40));
        btnExit.setBackground(new java.awt.Color(248, 250, 249));
        btnExit.setBorderPainted(false); btnExit.setFocusPainted(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, "Exit OSO Admin?", "Exit", javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                bond.util.SessionManager.logout();
                new bond.ui.UserSide.loginChoices().setVisible(true);
                this.dispose();
            }
        });
        jPanel1.add(btnExit,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 150, 30));
        jPanel1.setComponentZOrder(btnExit, 0);
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
