/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

import bond.search.GlobalSearchBar;
import bond.search.GlobalSearchRegistry;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import bond.ui.UserSide.dashboard;

/**
 *
 * @author Erica
 */
public class OrgProfileFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OrgProfileFrame.class.getName());
    
    private javax.swing.JPanel contentPanel;
    private JTextField emailField;
    private JTextField typeField;
    private JTextArea missionField;
    private JTextArea visionField;
    private JTextField adviserField;
    private JButton btnEdit;
    private JButton btnSave;
    private JButton btnCancel;
 
 
    private JButton makeInvisibleButton() {
        JButton btn = new JButton();
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
 
    private javax.swing.ImageIcon loadScaledIcon(String path, int w, int h) {
    java.net.URL url = getClass().getClassLoader().getResource(path);
    if (url == null) return null;
    return new javax.swing.ImageIcon(
        new javax.swing.ImageIcon(url).getImage()
            .getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH));
}
 
    private void navigateTo(javax.swing.JFrame targetFrame) {
        targetFrame.setVisible(true);
        this.dispose();
    }
    /**
     * Creates new form OrgProfileFrame
     */
    public OrgProfileFrame() {
        this.setLocationRelativeTo(null);
        initComponents();
        ActiveTab.setBounds(0, 4, 1000, 600); 
        javax.swing.JLabel lblBond = new javax.swing.JLabel("BOND");
        lblBond.setFont(new java.awt.Font("Playfair Display", java.awt.Font.PLAIN, 40));
        lblBond.setForeground(java.awt.Color.WHITE);
        lblBond.setBounds(28, 17, 200, 50);
        getContentPane().add(lblBond);
        getContentPane().setComponentZOrder(lblBond, 0);
        lblBond.setName("static");

        javax.swing.JLabel lblOrgAdmin = new javax.swing.JLabel(bond.util.SessionManager.getOrgName());
        lblOrgAdmin.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblOrgAdmin.setForeground(java.awt.Color.WHITE);
        lblOrgAdmin.setBounds(30, 115, 160, 25);
        getContentPane().add(lblOrgAdmin);
        getContentPane().setComponentZOrder(lblOrgAdmin, 0);
        lblOrgAdmin.setName("static");

        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel("Org Admin");
        lblOrgAcronym.setBounds(32, 135, 160, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);

        javax.swing.JLabel lblDashboard = new javax.swing.JLabel("Dashboard");
        lblDashboard.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
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
 
        bond.util.SessionManager.loadSession();
        this.setSize(1000, 635);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().remove(jLabel2);
 
        contentPanel = new javax.swing.JPanel(null);
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new java.awt.Dimension(1000, 995));
 
        javax.swing.JLabel bgImage = new javax.swing.JLabel();
        bgImage.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_OrgProfScroll.png")));
        bgImage.setBounds(0, -90, 1000, 995);
        contentPanel.add(bgImage);
 
        
        javax.swing.JLabel lblAnnHeader = new javax.swing.JLabel("Organization Profile");
        lblAnnHeader.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 24));
        lblAnnHeader.setForeground(new java.awt.Color(15, 61, 34));
        lblAnnHeader.setBounds(244, 32, 300, 35);
        contentPanel.add(lblAnnHeader);
        contentPanel.setComponentZOrder(lblAnnHeader, 0);
        lblAnnHeader.setName("static");
        

        javax.swing.JLabel lblSubtitle = new javax.swing.JLabel("Manage your org's information and members");
        lblSubtitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 16));
        lblSubtitle.setForeground(new java.awt.Color(122, 158, 140));
        lblSubtitle.setBounds(244, 58, 600, 25);
        contentPanel.add(lblSubtitle);
        contentPanel.setComponentZOrder(lblSubtitle, 0);
        lblSubtitle.setName("static");
        
        javax.swing.JLabel lblOrgInfo = new javax.swing.JLabel("Org Information");
        lblOrgInfo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 15));
        lblOrgInfo.setForeground(new java.awt.Color(000000));
        lblOrgInfo.setBounds(280, 130, 150, 20);
        contentPanel.add(lblOrgInfo);
        contentPanel.setComponentZOrder(lblOrgInfo, 0);
        lblOrgInfo.setName("static");
        
        javax.swing.JLabel lblColEAdd = new javax.swing.JLabel("EMAIL ADDRESS");
        lblColEAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblColEAdd.setForeground(new java.awt.Color(0x226B43));
        lblColEAdd.setBounds(280, 180, 150, 20);
        contentPanel.add(lblColEAdd);
        contentPanel.setComponentZOrder(lblColEAdd, 0);
        lblColEAdd.setName("static");

        javax.swing.JLabel lblType = new javax.swing.JLabel("TYPE");
        lblType.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblType.setForeground(new java.awt.Color(0x226B43));
        lblType.setBounds(630, 180, 150, 20);
        contentPanel.add(lblType);
        contentPanel.setComponentZOrder(lblType, 0);
        lblType.setName("static");

        javax.swing.JLabel lblMission = new javax.swing.JLabel("MISSION");
        lblMission.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblMission.setForeground(new java.awt.Color(0x226B43));
        lblMission.setBounds(280, 245, 100, 20);
        contentPanel.add(lblMission);
        contentPanel.setComponentZOrder(lblMission, 0);
        lblMission.setName("static");
        
        javax.swing.JLabel lblVision = new javax.swing.JLabel("VISION");
        lblVision.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblVision.setForeground(new java.awt.Color(0x226B43));
        lblVision.setBounds(630, 245, 100, 20);
        contentPanel.add(lblVision);
        contentPanel.setComponentZOrder(lblVision, 0);
        lblVision.setName("static");
 
        javax.swing.JLabel lblAdviser = new javax.swing.JLabel("ADVISER");
        lblAdviser.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblAdviser.setForeground(new java.awt.Color(0x226B43));
        lblAdviser.setBounds(280, 360, 100, 20);
        contentPanel.add(lblAdviser);
        contentPanel.setComponentZOrder(lblAdviser, 0);
        lblAdviser.setName("static");
        
        javax.swing.JLabel lblMembers = new javax.swing.JLabel("Members");
        lblMembers.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 15));
        lblMembers.setForeground(new java.awt.Color(000000));
        lblMembers.setBounds(280, 530, 100, 20);
        contentPanel.add(lblMembers);
        contentPanel.setComponentZOrder(lblMembers, 0);
        lblMembers.setName("static");
        
        javax.swing.JLabel lblName = new javax.swing.JLabel("Name");
        lblName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblName.setForeground(new java.awt.Color(0x226B43));
        lblName.setBounds(350, 575, 100, 20);
        contentPanel.add(lblName);
        contentPanel.setComponentZOrder(lblName, 0);
        lblName.setName("static");
        
        javax.swing.JLabel lblRole = new javax.swing.JLabel("Role");
        lblRole.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblRole.setForeground(new java.awt.Color(0x226B43));
        lblRole.setBounds(580, 575, 100, 20);
        contentPanel.add(lblRole);
        contentPanel.setComponentZOrder(lblRole, 0);
        lblRole.setName("static");
        
        javax.swing.JLabel lblCourse = new javax.swing.JLabel("Course");
        lblCourse.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblCourse.setForeground(new java.awt.Color(0x226B43));
        lblCourse.setBounds(785, 575, 100, 20);
        contentPanel.add(lblCourse);
        contentPanel.setComponentZOrder(lblCourse, 0);
        lblCourse.setName("static");
        
       
        jLabel2.setBounds(0, -290, 1000, 995);
        jLabel2.setVisible(false);
        contentPanel.add(jLabel2);

        
        emailField = makeTextField(287, 207, 290);
        contentPanel.add(emailField);
 
        typeField = makeTextField(632, 207, 290);
        typeField.setVisible(false); // hidden; replaced by combo below
        contentPanel.add(typeField);

        javax.swing.JComboBox<String> typeCombo = new javax.swing.JComboBox<>(
            new String[]{"Academic","Civic & Cultural","Religious","Media & Publications","Sports & Recreation"});
        typeCombo.setBounds(632, 207, 290, 25);
        typeCombo.setOpaque(false);
        typeCombo.setBackground(new java.awt.Color(255,255,255));
        typeCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        typeCombo.setForeground(java.awt.Color.BLACK);
        typeCombo.setEnabled(false);
        // Sync combo <-> hidden typeField
        typeCombo.addActionListener(e -> {
            if (typeCombo.getSelectedItem() != null)
                typeField.setText(typeCombo.getSelectedItem().toString());
        });
        contentPanel.add(typeCombo);
        contentPanel.setComponentZOrder(typeCombo, 0);
 
        adviserField = makeTextField(289, 387, 290);
        contentPanel.add(adviserField);
 
        missionField = makeTextArea(287, 276, 298, 72);
        contentPanel.add(missionField);
 
        visionField = makeTextArea(633, 276, 298, 72);
        contentPanel.add(visionField);
 
        btnEdit = makeInvisibleButton();
        btnEdit.setBounds(876, 128, 54, 35);
        btnEdit.addActionListener(e -> enableEditing());
        contentPanel.add(btnEdit);


        btnSave = makeInvisibleButton();
        btnSave.setBounds(840, 400, 100, 35);
        btnSave.setVisible(false);
        btnSave.addActionListener(e -> saveProfile());
        contentPanel.add(btnSave);
 
        btnCancel = makeInvisibleButton();
        btnCancel.setBounds(770, 400, 70, 35);
        btnCancel.setVisible(false);
        btnCancel.addActionListener(e -> cancelEditing());
        contentPanel.add(btnCancel);
 
        javax.swing.ImageIcon iconAddNormal = loadScaledIcon(
            "bond/assets/orgAdminImages/OrgProf_btnAdd.png", 124, 30);
        javax.swing.ImageIcon iconAddHover  = loadScaledIcon(
            "bond/assets/orgAdminImages/OrgProf_btnAddHover.png", 124, 30);

        JButton btnAddMember = new JButton();
        btnAddMember.setOpaque(false);
        btnAddMember.setContentAreaFilled(false);
        btnAddMember.setBorderPainted(false);
        btnAddMember.setFocusPainted(false);
        btnAddMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (iconAddNormal != null) btnAddMember.setIcon(iconAddNormal);
        if (iconAddHover  != null) {
            btnAddMember.setRolloverIcon(iconAddHover);
            btnAddMember.setPressedIcon(iconAddHover);
        }
        btnAddMember.setBounds(800, 521, 126, 30);
        btnAddMember.addActionListener(e -> showAddMemberDialog());
        contentPanel.add(btnAddMember);
        
        javax.swing.ImageIcon iconOfficerNormal = loadScaledIcon(
            "bond/assets/orgAdminImages/OrgProf_btnAdd.png", 140, 30);

        JButton btnManageOfficers = new JButton("Manage Officers");
        btnManageOfficers.setOpaque(true);
        btnManageOfficers.setContentAreaFilled(true);
        btnManageOfficers.setBorderPainted(false);
        btnManageOfficers.setFocusPainted(false);
        btnManageOfficers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnManageOfficers.setBackground(new java.awt.Color(28, 94, 56));
        btnManageOfficers.setForeground(java.awt.Color.WHITE);
        btnManageOfficers.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnManageOfficers.setBounds(800, 460, 140, 30);
        btnManageOfficers.addActionListener(e -> showOfficerDialog());
        contentPanel.add(btnManageOfficers);
        contentPanel.setComponentZOrder(btnManageOfficers, 0);
       
        contentPanel.setComponentZOrder(btnEdit,   0);
        contentPanel.setComponentZOrder(btnSave,   1);
        contentPanel.setComponentZOrder(btnCancel, 2);
        contentPanel.setComponentZOrder(jLabel2,   3);
        contentPanel.setComponentZOrder(bgImage,   contentPanel.getComponentCount() - 1);
 
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
        
        int total = getContentPane().getComponentCount();
        getContentPane().setComponentZOrder(scrollPane, 0);
        getContentPane().setComponentZOrder(jLabel1, getContentPane().getComponentCount() - 1);
        getContentPane().setComponentZOrder(ActiveTab, total - 2);

 
        loadOrgProfile();
        loadMembers();
 
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
 
        JButton btnSettings = makeInvisibleButton();
        btnSettings.setBounds(0, 355, 210, 40);
        btnSettings.addActionListener(e -> navigateTo(new SettingsFrame()));
        getContentPane().add(btnSettings);
        getContentPane().setComponentZOrder(btnSettings, 0);
 
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
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    
    }
    
    private JTextField makeTextField(int x, int y, int w) {
        JTextField f = new JTextField();
        f.setBounds(x, y, w, 25);
        f.setOpaque(false);
        f.setBackground(new java.awt.Color(0, 0, 0, 0));
        f.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        f.setForeground(java.awt.Color.BLACK);
        f.setEditable(false);
        f.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return f;
    }
 
    private JTextArea makeTextArea(int x, int y, int w, int h) {
        JTextArea a = new JTextArea();
        a.setBounds(x, y, w, h);
        a.setOpaque(false);
        a.setBackground(new java.awt.Color(0, 0, 0, 0));
        a.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        a.setForeground(java.awt.Color.BLACK);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setEditable(false);
        a.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return a;
    }
 
 
    private void loadOrgProfile() {
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            int orgId = bond.util.SessionManager.getCurrentOrgId();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT o.org_name, o.classification, o.mission, o.vision, " +
                "(SELECT adv.full_name FROM adviser adv WHERE adv.org_id = o.org_id ORDER BY adv.adviser_id DESC LIMIT 1) AS adviser " +
                "FROM organization o WHERE o.org_id = ?"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                try {
                    java.sql.Connection connEmail = bond.db.DBConnection.getConnection();
                    java.sql.PreparedStatement psEmail = connEmail.prepareStatement(
                        "SELECT email FROM org_admin WHERE org_id = ? LIMIT 1"
                    );
                    psEmail.setInt(1, orgId);
                    java.sql.ResultSet rsEmail = psEmail.executeQuery();
                    emailField.setText(rsEmail.next() ? rsEmail.getString("email") : "");
                    connEmail.close();
                } catch (Exception ex) {
                    emailField.setText("");
                }

                String classification = rs.getString("classification");
                typeField.setText(classification != null ? classification : "");

                for (java.awt.Component c : contentPanel.getComponents()) {
                    if (c instanceof javax.swing.JComboBox) {
                        @SuppressWarnings("unchecked")
                        javax.swing.JComboBox<String> combo = (javax.swing.JComboBox<String>) c;
                        if (classification != null) combo.setSelectedItem(classification);
                    }
                }

                String mission = rs.getString("mission");
                missionField.setText(mission != null ? mission : "");

                String vision = rs.getString("vision");
                visionField.setText(vision != null ? vision : "");

                String adv = rs.getString("adviser");
                adviserField.setText(adv != null ? adv : "");
            }
            rs.close();
            ps.close();
            conn.close();

            // Force repaint so fields actually show the loaded data
            contentPanel.revalidate();
            contentPanel.repaint();

        } catch (Exception ex) {
            System.out.println("Load org error: " + ex.getMessage());
        }
    }
    
   private void loadOfficerRowsFiltered(javax.swing.JPanel listPanel, int[] listHeight,
                                      javax.swing.JScrollPane scroll, java.awt.Window parentWindow,
                                      String ayFilter) {
        // Same as loadOfficerRows but with optional ay filter
        try {
 
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            String sql;
            if ("All Academic Years".equals(ayFilter)) {
                sql = "SELECT o.officer_id, o.full_name, o.position, ay.year_label " +
                      "FROM officer o LEFT JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                      "WHERE o.org_id = ? ORDER BY o.officer_id ASC";
            } else {
                sql = "SELECT o.officer_id, o.full_name, o.position, ay.year_label " +
                      "FROM officer o LEFT JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                      "WHERE o.org_id = ? AND ay.year_label = ? ORDER BY o.officer_id ASC";
            }
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bond.util.SessionManager.getCurrentOrgId());
            if (!"All Academic Years".equals(ayFilter)) ps.setString(2, ayFilter);
            java.sql.ResultSet rs = ps.executeQuery();
            
            int y = 4;
            boolean hasRows = false;
 
            while (rs.next()) {
 
                hasRows = true;
                int offId = rs.getInt("officer_id");
                             
                String pos     = rs.getString("position");
                String name    = rs.getString("full_name"); 
                if ("President".equalsIgnoreCase(pos)) {
                    try {
                        java.sql.Connection cReg = bond.db.DBConnection.getConnection();
                        java.sql.PreparedStatement psReg = cReg.prepareStatement(
                            "SELECT appointed_by FROM registration_form WHERE form_id = " +
                            "(SELECT form_id FROM organization WHERE org_id = ?) LIMIT 1"
                        );
                        psReg.setInt(1, bond.util.SessionManager.getCurrentOrgId());
                        java.sql.ResultSet rsReg = psReg.executeQuery();
                        if (rsReg.next()) {
                            String appointed = rsReg.getString("appointed_by");
                            if (appointed != null && !appointed.trim().isEmpty()) {
                                name = appointed.trim();
                            }
                        }
                        cReg.close();
                    } catch (Exception ex) {
                        System.out.println("Load appointed name error: " + ex.getMessage());
                    }
                }
                String contact = rs.getString("year_label");
                if (contact == null) contact = "—";
 
                javax.swing.JLabel lName = new javax.swing.JLabel(name);
                lName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lName.setForeground(new java.awt.Color(40, 40, 40));
                lName.setBounds(14, y + 4, 200, 20);
                listPanel.add(lName);
 
                javax.swing.JLabel lPos = new javax.swing.JLabel(pos);
                lPos.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lPos.setForeground(new java.awt.Color(40, 40, 40));
                lPos.setBounds(230, y + 4, 180, 20);
                listPanel.add(lPos);
 
                javax.swing.JLabel lContact = new javax.swing.JLabel(contact);
                lContact.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lContact.setForeground(new java.awt.Color(40, 40, 40));
                lContact.setBounds(420, y + 4, 160, 20);
                listPanel.add(lContact);
 
                javax.swing.JButton btnDel = new javax.swing.JButton("Remove");
                btnDel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                btnDel.setForeground(new java.awt.Color(180, 30, 30));
                btnDel.setBackground(new java.awt.Color(255, 240, 240));
                btnDel.setBorderPainted(false);
                btnDel.setFocusPainted(false);
                btnDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnDel.setBounds(582, y, 80, 26);
                final int oid = offId;
                final String finalName = name;
                btnDel.addActionListener(e -> {
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        parentWindow, "Remove " + finalName + "?", "Confirm",
                        javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        try {
                            java.sql.Connection c2 = bond.db.DBConnection.getConnection();
 
                            java.sql.PreparedStatement chk = c2.prepareStatement(
                                "SELECT COUNT(*) FROM org_admin WHERE officer_id = ?"
                            );
                            chk.setInt(1, oid);
                            java.sql.ResultSet chkRs = chk.executeQuery();
                            chkRs.next();
                            if (chkRs.getInt(1) > 0) {
                                javax.swing.JOptionPane.showMessageDialog(parentWindow,
                                    "Cannot remove this officer — they are linked to an Org Admin account.");
                                c2.close();
                                return;
                            }
 
                            java.sql.PreparedStatement ps2 = c2.prepareStatement(
                                "DELETE FROM officer WHERE officer_id = ?"
                            );
                            ps2.setInt(1, oid);
                            ps2.executeUpdate();
                            c2.close();
                            listPanel.removeAll();
                            listHeight[0] = 0;
                            loadOfficerRows(listPanel, listHeight, scroll, parentWindow);
                        } catch (Exception ex) {
                            System.out.println("Remove officer error: " + ex.getMessage());
                        }
                    }
                });
                listPanel.add(btnDel);
 
                y += 34;
            }
 
            if (!hasRows) {
                javax.swing.JLabel none = new javax.swing.JLabel("No officers added yet.");
                none.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.ITALIC, 12));
                none.setForeground(new java.awt.Color(160, 160, 160));
                none.setBounds(14, 8, 300, 20);
                listPanel.add(none);
                y += 30;
            }
 
            listHeight[0] = y + 10;
            listPanel.setPreferredSize(new java.awt.Dimension(670, listHeight[0]));
            listPanel.revalidate();
            listPanel.repaint();
            conn.close();
 
        } catch (Exception ex) {
            System.out.println("Load officers error: " + ex.getMessage());
        }
    }
    
    private void enableEditing() {
        jLabel2.setVisible(true);
        contentPanel.setComponentZOrder(jLabel2, 3);

        emailField.setEditable(true);
        emailField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        for (java.awt.Component c : contentPanel.getComponents()) {
            if (c instanceof javax.swing.JComboBox) {
                ((javax.swing.JComboBox<?>) c).setEnabled(false);
            }
            if ("btnUploadLogo".equals(c.getName())) {
                c.setVisible(true);
            }
        }

        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        contentPanel.setComponentZOrder(btnSave,   1);
        contentPanel.setComponentZOrder(btnCancel, 2);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
 
    private void cancelEditing() {
        jLabel2.setVisible(false);

        emailField.setEditable(false);
        emailField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        emailField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        for (java.awt.Component c : contentPanel.getComponents()) {
            if ("btnUploadLogo".equals(c.getName())) {
                c.setVisible(false);
            }
        }

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        loadOrgProfile();
    }
 
    private void saveProfile() {
        String name = emailField.getText().trim(); 

        if (name.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Org name cannot be empty!", "Warning",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            int orgId = bond.util.SessionManager.getCurrentOrgId();

            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE org_admin SET email=? WHERE org_id=?"
            );
            ps.setString(1, name);
            ps.setInt(2, orgId);

            boolean updated = ps.executeUpdate() > 0;
            conn.close();

            if (updated) {
                javax.swing.JOptionPane.showMessageDialog(this, "Profile updated successfully!");
                cancelEditing();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Failed to update profile!", "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Save org error: " + ex.getMessage());
        }
    }
 
    private void loadMembers() {
        loadMembers("All");
    }
 
    private void loadMembers(String roleFilter) {
        java.util.List<java.awt.Component> toRemove = new java.util.ArrayList<>();
        for (java.awt.Component c : contentPanel.getComponents()) {
            if ("memberRow".equals(c.getName())) toRemove.add(c);
        }
        for (java.awt.Component c : toRemove) contentPanel.remove(c);
 
        // Filter buttons
        javax.swing.JButton btnAll = new javax.swing.JButton("All");
        btnAll.setBounds(280, 560, 60, 24);
        btnAll.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        btnAll.setBackground("All".equals(roleFilter) ? new java.awt.Color(28,94,56) : new java.awt.Color(200,220,210));
        btnAll.setForeground("All".equals(roleFilter) ? java.awt.Color.WHITE : new java.awt.Color(28,94,56));
        btnAll.setBorderPainted(false); btnAll.setFocusPainted(false);
        btnAll.setName("memberRow");
        btnAll.addActionListener(e -> loadMembers("All"));
        contentPanel.add(btnAll); contentPanel.setComponentZOrder(btnAll, 0);
 
        javax.swing.JButton btnOfficer = new javax.swing.JButton("Officer");
        btnOfficer.setBounds(348, 560, 70, 24);
        btnOfficer.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        btnOfficer.setBackground("Officer".equals(roleFilter) ? new java.awt.Color(28,94,56) : new java.awt.Color(200,220,210));
        btnOfficer.setForeground("Officer".equals(roleFilter) ? java.awt.Color.WHITE : new java.awt.Color(28,94,56));
        btnOfficer.setBorderPainted(false); btnOfficer.setFocusPainted(false);
        btnOfficer.setName("memberRow");
        btnOfficer.addActionListener(e -> loadMembers("Officer"));
        contentPanel.add(btnOfficer); contentPanel.setComponentZOrder(btnOfficer, 0);
 
        javax.swing.JButton btnMember = new javax.swing.JButton("Member");
        btnMember.setBounds(426, 560, 72, 24);
        btnMember.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        btnMember.setBackground("Member".equals(roleFilter) ? new java.awt.Color(28,94,56) : new java.awt.Color(200,220,210));
        btnMember.setForeground("Member".equals(roleFilter) ? java.awt.Color.WHITE : new java.awt.Color(28,94,56));
        btnMember.setBorderPainted(false); btnMember.setFocusPainted(false);
        btnMember.setName("memberRow");
        btnMember.addActionListener(e -> loadMembers("Member"));
        contentPanel.add(btnMember); contentPanel.setComponentZOrder(btnMember, 0);
 
        java.util.List<bond.model.Member> all = new bond.dao.MemberDAO().getAllMembers(
            bond.util.SessionManager.getCurrentOrgId());
 
        int startY = 596;
        int rowH = 28;
        int count = 0;
 
        for (bond.model.Member m : all) {
            if (!"All".equals(roleFilter) && !roleFilter.equals(m.getRole())) continue;
            int y = startY + (count * rowH);
 
            javax.swing.JLabel lblName = makeMemberLabel(m.getName(), 320, y, 200);
            contentPanel.add(lblName); contentPanel.setComponentZOrder(lblName, 0);
 
            javax.swing.JLabel lblRole = makeMemberLabel(m.getRole(), 575, y, 180);
            contentPanel.add(lblRole); contentPanel.setComponentZOrder(lblRole, 0);
 
            javax.swing.JLabel lblCourse = makeMemberLabel(
                m.getCourse() != null && !m.getCourse().isEmpty() ? m.getCourse() : "—",
                795, y, 100);
            contentPanel.add(lblCourse); contentPanel.setComponentZOrder(lblCourse, 0);
 
            // Edit button
            final int mid = m.getMemberId();
            final String mName = m.getName();
            final String mRole = m.getRole();
            final String mCourse = m.getCourse() != null ? m.getCourse() : "";
            javax.swing.JButton btnEdit = new javax.swing.JButton("Edit");
            btnEdit.setBounds(900, y, 50, 22);
            btnEdit.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 10));
            btnEdit.setBackground(new java.awt.Color(28, 94, 56));
            btnEdit.setForeground(java.awt.Color.WHITE);
            btnEdit.setBorderPainted(false); btnEdit.setFocusPainted(false);
            btnEdit.setName("memberRow");
            btnEdit.addActionListener(e -> showEditMemberDialog(mid, mName, mRole, mCourse, roleFilter));
            contentPanel.add(btnEdit); contentPanel.setComponentZOrder(btnEdit, 0);
 
            // Remove button
            javax.swing.JButton btnRem = new javax.swing.JButton("✕");
            btnRem.setBounds(956, y, 30, 22);
            btnRem.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 10));
            btnRem.setBackground(new java.awt.Color(200, 50, 50));
            btnRem.setForeground(java.awt.Color.WHITE);
            btnRem.setBorderPainted(false); btnRem.setFocusPainted(false);
            btnRem.setName("memberRow");
            btnRem.addActionListener(e -> {
                int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                    "Remove " + mName + "?", "Confirm", javax.swing.JOptionPane.YES_NO_OPTION);
                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    try {
                        java.sql.Connection c = bond.db.DBConnection.getConnection();
                        c.prepareStatement("DELETE FROM members WHERE member_id = " + mid).executeUpdate();
                        c.close();
                        loadMembers(roleFilter);
                    } catch (Exception ex) { System.out.println("Remove member error: " + ex.getMessage()); }
                }
            });
            contentPanel.add(btnRem); contentPanel.setComponentZOrder(btnRem, 0);
 
            count++;
        }
 
        int newHeight = Math.max(995, 596 + (count * rowH) + 40);
        contentPanel.setPreferredSize(new java.awt.Dimension(1000, newHeight));
        contentPanel.revalidate();
        contentPanel.repaint();
    }
 
    private void showEditMemberDialog(int memberId, String name, String role, String course, String currentFilter) {
        javax.swing.JDialog d = new javax.swing.JDialog(this, "Edit Member", true);
        d.setSize(400, 280);
        d.setLocationRelativeTo(this);
        d.setLayout(null);
        d.getContentPane().setBackground(new java.awt.Color(248, 250, 249));
 
        javax.swing.JTextField fName = new javax.swing.JTextField(name);
        fName.setBounds(20, 50, 350, 28);
        fName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        fName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180,210,195)));
        d.add(fName);
        javax.swing.JLabel lName = new javax.swing.JLabel("Full Name");
        lName.setBounds(20, 30, 200, 20);
        lName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        lName.setForeground(new java.awt.Color(28,94,56));
        d.add(lName);
 
        javax.swing.JComboBox<String> fRole = new javax.swing.JComboBox<>(new String[]{"Officer","Member"});
        fRole.setSelectedItem(role);
        fRole.setBounds(20, 110, 160, 28);
        fRole.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        d.add(fRole);
        javax.swing.JLabel lRole = new javax.swing.JLabel("Role");
        lRole.setBounds(20, 90, 200, 20);
        lRole.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        lRole.setForeground(new java.awt.Color(28,94,56));
        d.add(lRole);
 
        javax.swing.JComboBox<String> fCourse = new javax.swing.JComboBox<>(new String[]{"BSIT","BIT","BSED","BSHM","BSTM"});
        fCourse.setSelectedItem(course);
        fCourse.setBounds(200, 110, 170, 28);
        fCourse.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        d.add(fCourse);
        javax.swing.JLabel lCourse = new javax.swing.JLabel("Course");
        lCourse.setBounds(200, 90, 200, 20);
        lCourse.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
        lCourse.setForeground(new java.awt.Color(28,94,56));
        d.add(lCourse);
 
        javax.swing.JButton btnSave = new javax.swing.JButton("Save");
        btnSave.setBounds(260, 180, 110, 30);
        btnSave.setBackground(new java.awt.Color(28,94,56));
        btnSave.setForeground(java.awt.Color.WHITE);
        btnSave.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnSave.setBorderPainted(false); btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> {
            String newName = fName.getText().trim();
            String newRole = (String) fRole.getSelectedItem();
            String newCourse = (String) fCourse.getSelectedItem();
            if (newName.isEmpty()) { javax.swing.JOptionPane.showMessageDialog(d, "Name cannot be empty."); return; }
            try {
                java.sql.Connection c = bond.db.DBConnection.getConnection();
                java.sql.PreparedStatement ps = c.prepareStatement(
                    "UPDATE members SET full_name=?, role=?, course=? WHERE member_id=?");
                ps.setString(1, newName); ps.setString(2, newRole);
                ps.setString(3, newCourse); ps.setInt(4, memberId);
                ps.executeUpdate(); c.close();
                d.dispose();
                loadMembers(currentFilter);
            } catch (Exception ex) { javax.swing.JOptionPane.showMessageDialog(d, "Save failed: " + ex.getMessage()); }
        });
        d.add(btnSave);
 
        javax.swing.JButton btnCancel = new javax.swing.JButton("Cancel");
        btnCancel.setBounds(160, 180, 90, 30);
        btnCancel.setBackground(new java.awt.Color(180,210,195));
        btnCancel.setForeground(new java.awt.Color(28,94,56));
        btnCancel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
        btnCancel.setBorderPainted(false); btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(e -> d.dispose());
        d.add(btnCancel);
 
        d.setVisible(true);
    }
 
    private javax.swing.JLabel makeMemberLabel(String text, int x, int y, int w) {
        javax.swing.JLabel lbl = new javax.swing.JLabel(text);
        lbl.setBounds(x, y, w, 20);
        lbl.setForeground(java.awt.Color.BLACK);
        lbl.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
        lbl.setName("memberRow");
        return lbl;
    }
 
    
    private void showAddMemberDialog() {
        
        javax.swing.JWindow overlay = new javax.swing.JWindow(this);
        overlay.setSize(1000, 600);
        
        overlay.setLocation(
            this.getLocationOnScreen().x,
            this.getLocationOnScreen().y + 35);
        
        overlay.setBackground(new java.awt.Color(0, 0, 0, 120));
        
        javax.swing.JPanel overlayPanel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                g.setColor(new java.awt.Color(0, 0, 0, 120));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
        overlayPanel.setOpaque(false);
        overlay.add(overlayPanel);
        overlay.setVisible(true);
 
        javax.swing.JDialog dialog = new javax.swing.JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_OrgProfMember.png");
 
        if (imgURL != null) {
            javax.swing.JPanel panel = new javax.swing.JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new java.awt.Dimension(1000, 600));
 
            javax.swing.JLabel imgLabel = new javax.swing.JLabel(new javax.swing.ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);
            
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Add Member");
            HeaderAdd.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
            HeaderAdd.setForeground(new java.awt.Color(15, 61, 34));
            HeaderAdd.setBounds(325, 135, 260, 40);
            panel.add(HeaderAdd);
            HeaderAdd.setName("static");
            
            javax.swing.JLabel TitleAdd = new javax.swing.JLabel("Full Name (First Name, Middle Name, Last Name)");
            TitleAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            TitleAdd.setForeground(new java.awt.Color(15, 61, 34));
            TitleAdd.setBounds(325, 190, 360, 40);
            panel.add(TitleAdd);
            TitleAdd.setName("static");
            
            javax.swing.JLabel DateAdd = new javax.swing.JLabel("Role");
            DateAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DateAdd.setForeground(new java.awt.Color(15, 61, 34));
            DateAdd.setBounds(325, 265, 360, 40);
            panel.add(DateAdd);
            DateAdd.setName("static");
            
            javax.swing.JLabel DescAdd = new javax.swing.JLabel("Course");
            DescAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DescAdd.setForeground(new java.awt.Color(15, 61, 34));
            DescAdd.setBounds(325, 335, 360, 40);
            panel.add(DescAdd);
            DescAdd.setName("static");
 
            JTextField fullNameField = new JTextField();
            fullNameField.setBounds(338, 235, 335, 30);
            fullNameField.setOpaque(false);
            fullNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
            fullNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            fullNameField.setForeground(java.awt.Color.BLACK);
            panel.add(fullNameField);
 
            javax.swing.JComboBox<String> roleField = new javax.swing.JComboBox<>(new String[]{"Officer", "Member"});
            roleField.setBounds(338, 307, 335, 30);
            roleField.setOpaque(false);
            roleField.setBackground(new java.awt.Color(0, 0, 0, 0));
            roleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            roleField.setForeground(java.awt.Color.BLACK);
            panel.add(roleField);
 
            String[] courses = {"BSIT", "BIT", "BSED", "BSHM", "BSTM"};
            javax.swing.JComboBox<String> courseCombo = new javax.swing.JComboBox<>(courses);
            courseCombo.setBounds(325, 375, 347, 30);
            courseCombo.setOpaque(false);
            courseCombo.setBackground(new java.awt.Color(0, 0, 0, 0));
            courseCombo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            courseCombo.setForeground(java.awt.Color.BLACK);
            panel.add(courseCombo);
 
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(670, 140, 25, 28);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(520, 432, 65, 30);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            JButton btnAdd = makeInvisibleButton();
            btnAdd.setBounds(590, 432, 100, 30);
            btnAdd.addActionListener(e -> {
                String fullName = fullNameField.getText().trim();
                String role = roleField.getSelectedItem().toString();                String course = courseCombo.getSelectedItem().toString();
 
                if (fullName.isEmpty() || role.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Please fill in all fields!", "Warning",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
 
                bond.model.Member member = new bond.model.Member();
                member.setOrgId(bond.util.SessionManager.getCurrentOrgId());
                member.setName(fullName);
                member.setRole(role);
                member.setCourse(course);
 
                boolean saved = new bond.dao.MemberDAO().addMember(member);
 
                if (saved) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Member added successfully!");
                    overlay.dispose();
                    dialog.dispose();
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        loadMembers();
                        contentPanel.revalidate();
                        contentPanel.repaint();
                    });
                    
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to add member!", "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(btnAdd);
 
            panel.add(imgLabel);
            dialog.add(panel);
            panel.setComponentZOrder(imgLabel, panel.getComponentCount() - 1); // already done for imgLabel
            panel.setComponentZOrder(courseCombo, 0);
        }
 
        dialog.setVisible(true);
        overlay.dispose();
    }
    
  public void showOfficerDialog() {
       javax.swing.JDialog dialog = new javax.swing.JDialog(this, "Manage Officers", true);
    dialog.setSize(720, 560);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(null);
    dialog.getContentPane().setBackground(new java.awt.Color(248, 250, 249));

    // header bar
    javax.swing.JPanel header = new javax.swing.JPanel(null);
    header.setBackground(new java.awt.Color(28, 94, 56));
    header.setBounds(0, 0, 700, 70);
    dialog.add(header);

    javax.swing.JLabel lblTitle = new javax.swing.JLabel("Officer Management");
    lblTitle.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 20));
    lblTitle.setForeground(java.awt.Color.WHITE);
    lblTitle.setBounds(24, 18, 320, 34);
    header.add(lblTitle);

    javax.swing.JLabel lblSub = new javax.swing.JLabel("Current academic year officers");
    lblSub.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
    lblSub.setForeground(new java.awt.Color(180, 220, 200));
    lblSub.setBounds(24, 44, 320, 20);
    header.add(lblSub);

    // column headers
    javax.swing.JLabel colName = new javax.swing.JLabel("Full Name");
    colName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
    colName.setForeground(new java.awt.Color(28, 94, 56));
    colName.setBounds(24, 84, 200, 20);
    dialog.add(colName);

    javax.swing.JLabel colPos = new javax.swing.JLabel("Position");
    colPos.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
    colPos.setForeground(new java.awt.Color(28, 94, 56));
    colPos.setBounds(240, 84, 180, 20);
    dialog.add(colPos);

    javax.swing.JLabel colContact = new javax.swing.JLabel("Academic Year");
    colContact.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
    colContact.setForeground(new java.awt.Color(28, 94, 56));
    colContact.setBounds(430, 84, 160, 20);
    dialog.add(colContact);

    // divider
    javax.swing.JSeparator sep = new javax.swing.JSeparator();
    sep.setBounds(20, 106, 660, 2);
    sep.setForeground(new java.awt.Color(200, 220, 210));
    dialog.add(sep);

    // scroll panel for officers list
    javax.swing.JPanel listPanel = new javax.swing.JPanel(null);
    listPanel.setBackground(new java.awt.Color(248, 250, 249));

    javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(listPanel);
    scroll.setBounds(10, 110, 680, 280);
    scroll.setBorder(null);
    scroll.getViewport().setBackground(new java.awt.Color(248, 250, 249));
    dialog.add(scroll);

    //  AY filter combo 
    javax.swing.JComboBox<String> ayFilterCombo = new javax.swing.JComboBox<>();
    ayFilterCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
    ayFilterCombo.setBackground(java.awt.Color.WHITE);
    ayFilterCombo.setBounds(500, 80, 180, 24);
    dialog.add(ayFilterCombo);

    final String[] ALL_POSITIONS = {
        "President", "Vice President", "Secretary", "Assistant Secretary",
        "Treasurer", "Assistant Treasurer", "Auditor", "Business Manager", "Org Representative"
    };
    javax.swing.JComboBox<String> posCombo = new javax.swing.JComboBox<>();
    posCombo.setBounds(182, 435, 155, 28);
    posCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
    posCombo.setBackground(java.awt.Color.WHITE);
    posCombo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
    dialog.add(posCombo);

    javax.swing.JLabel lblYearTerm = new javax.swing.JLabel("Year Term");
    lblYearTerm.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
    lblYearTerm.setForeground(new java.awt.Color(28, 94, 56));
    lblYearTerm.setBounds(345, 415, 120, 18);
    dialog.add(lblYearTerm);

    javax.swing.JComboBox<String> yearCombo = new javax.swing.JComboBox<>();
    for (int start = 2015; start <= 2060; start++) {
        yearCombo.addItem(start + "-" + (start + 1));
    }
    yearCombo.setBounds(345, 435, 150, 28);
    yearCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
    yearCombo.setBackground(java.awt.Color.WHITE);
    yearCombo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
    dialog.add(yearCombo);

    Runnable refreshPosCombo = () -> {
        String selectedYear = yearCombo.getSelectedItem() != null
                ? yearCombo.getSelectedItem().toString() : "";

        java.util.Set<String> taken = new java.util.HashSet<>();
        if (!selectedYear.isEmpty()) {
            try {
                java.sql.Connection connPos = bond.db.DBConnection.getConnection();
                java.sql.PreparedStatement psTaken = connPos.prepareStatement(
                    "SELECT o.position FROM officer o " +
                    "JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                    "WHERE o.org_id = ? AND ay.year_label = ?"
                );
                psTaken.setInt(1, bond.util.SessionManager.getCurrentOrgId());
                psTaken.setString(2, selectedYear);
                java.sql.ResultSet rsTaken = psTaken.executeQuery();
                while (rsTaken.next()) taken.add(rsTaken.getString("position"));
                connPos.close();
            } catch (Exception ex) {
                System.out.println("Refresh pos combo error: " + ex.getMessage());
            }
        }

        // Suppress listeners while rebuilding
        java.awt.event.ActionListener[] posListeners = posCombo.getActionListeners();
        for (java.awt.event.ActionListener l : posListeners) posCombo.removeActionListener(l);

        posCombo.removeAllItems();
        for (String p : ALL_POSITIONS) {
            if (!taken.contains(p)) posCombo.addItem(p);
        }

        for (java.awt.event.ActionListener l : posListeners) posCombo.addActionListener(l);
    };

    yearCombo.addActionListener(e -> refreshPosCombo.run());

    refreshPosCombo.run();

    Runnable refreshAyFilter = () -> {
        String currentSelection = (String) ayFilterCombo.getSelectedItem();

        java.awt.event.ActionListener[] listeners = ayFilterCombo.getActionListeners();
        for (java.awt.event.ActionListener l : listeners) ayFilterCombo.removeActionListener(l);

        ayFilterCombo.removeAllItems();
        ayFilterCombo.addItem("All Academic Years");
        try {
            java.sql.Connection connAY = bond.db.DBConnection.getConnection();
            java.sql.ResultSet rsAY = connAY.prepareStatement(
                "SELECT year_label FROM academic_year ORDER BY academic_year_id DESC"
            ).executeQuery();
            while (rsAY.next()) ayFilterCombo.addItem(rsAY.getString("year_label"));
            connAY.close();
        } catch (Exception ex) {
            System.out.println("AY filter refresh error: " + ex.getMessage());
        }

        if (currentSelection != null) {
            ayFilterCombo.setSelectedItem(currentSelection);
            if (!currentSelection.equals(ayFilterCombo.getSelectedItem())) {
                ayFilterCombo.setSelectedIndex(0);
            }
        }

        for (java.awt.event.ActionListener l : listeners) ayFilterCombo.addActionListener(l);
    };

    // Load officers list, then populate both combos from DB
    int[] listHeight = {0};
    loadOfficerRows(listPanel, listHeight, scroll, dialog);
    refreshAyFilter.run();

    // Wire AY filter → reload officer list
    ayFilterCombo.addActionListener(e -> {
        String selectedAY = (String) ayFilterCombo.getSelectedItem();
        if (selectedAY == null) return;
        listPanel.removeAll();
        int[] lhRef = {0};
        loadOfficerRowsFiltered(listPanel, lhRef, scroll, dialog, selectedAY);
        listPanel.revalidate();
        listPanel.repaint();
    });

    // add officer section
    javax.swing.JSeparator sep2 = new javax.swing.JSeparator();
    sep2.setBounds(20, 398, 660, 2);
    sep2.setForeground(new java.awt.Color(200, 220, 210));
    dialog.add(sep2);

    javax.swing.JLabel lblAdd = new javax.swing.JLabel("Add Officer");
    lblAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
    lblAdd.setForeground(new java.awt.Color(28, 94, 56));
    lblAdd.setBounds(24, 406, 120, 24);
    dialog.add(lblAdd);

    javax.swing.JTextField nameInput = new javax.swing.JTextField();
    nameInput.setBounds(24, 435, 150, 28);
    nameInput.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
    nameInput.setForeground(new java.awt.Color(28, 94, 56));
    nameInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 195)));
    nameInput.setToolTipText("Full Name");
    dialog.add(nameInput);

    javax.swing.JButton btnAdd = new javax.swing.JButton("Add");
    btnAdd.setBounds(505, 435, 80, 28);
    btnAdd.setBackground(new java.awt.Color(28, 94, 56));
    btnAdd.setForeground(java.awt.Color.WHITE);
    btnAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
    btnAdd.setBorderPainted(false);
    btnAdd.setFocusPainted(false);
    btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnAdd.addActionListener(e -> {
        String name      = nameInput.getText().trim();
        String pos       = posCombo.getSelectedItem() != null ? posCombo.getSelectedItem().toString() : "";
        String yearLabel = yearCombo.getSelectedItem() != null ? yearCombo.getSelectedItem().toString() : "";

        if (name.isEmpty() || pos.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(dialog, "Name and Position are required.");
            return;
        }

        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();

            // Find or create the academic_year row
            java.sql.PreparedStatement psFind = conn.prepareStatement(
                "SELECT academic_year_id FROM academic_year WHERE year_label = ?"
            );
            psFind.setString(1, yearLabel);
            java.sql.ResultSet rsFind = psFind.executeQuery();

            int ayId;
            if (rsFind.next()) {
                ayId = rsFind.getInt("academic_year_id");
            } else {
                String[] parts = yearLabel.split("-");
                java.sql.PreparedStatement psInsert = conn.prepareStatement(
                    "INSERT INTO academic_year (year_label, start_date, end_date) VALUES (?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS
                );
                psInsert.setString(1, yearLabel);
                psInsert.setString(2, parts[0].trim() + "-08-01");
                psInsert.setString(3, parts[1].trim() + "-07-31");
                psInsert.executeUpdate();
                java.sql.ResultSet keys = psInsert.getGeneratedKeys();
                keys.next();
                ayId = keys.getInt(1);
            }

            java.sql.PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO officer (org_id, academic_year_id, full_name, position, contact_number) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setInt(1, bond.util.SessionManager.getCurrentOrgId());
            ps.setInt(2, ayId);
            ps.setString(3, name);
            ps.setString(4, pos);
            ps.setString(5, null);
            ps.executeUpdate();
            conn.close();

            nameInput.setText("");

            // Refresh officer list
            listPanel.removeAll();
            listHeight[0] = 0;
            loadOfficerRows(listPanel, listHeight, scroll, dialog);

            // Refresh AY filter (new year may have been created)
            refreshAyFilter.run();

            // Refresh position combo for the still-selected year term
            refreshPosCombo.run();

        } catch (Exception ex) {
            System.out.println("Add officer error: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(dialog, "Failed to add officer: " + ex.getMessage());
        }
    });
    dialog.add(btnAdd);

    javax.swing.JButton btnClose = new javax.swing.JButton("Close");
    btnClose.setBounds(580, 476, 80, 28);
    btnClose.setBackground(new java.awt.Color(180, 210, 195));
    btnClose.setForeground(new java.awt.Color(28, 94, 56));
    btnClose.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
    btnClose.setBorderPainted(false);
    btnClose.setFocusPainted(false);
    btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnClose.addActionListener(e -> dialog.dispose());
    dialog.add(btnClose);

    dialog.setVisible(true);
   }
 
    private void loadOfficerRows(javax.swing.JPanel listPanel, int[] listHeight,
                                  javax.swing.JScrollPane scroll, java.awt.Window parentWindow) {
        try {
 
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT o.officer_id, o.full_name, o.position, ay.year_label " +
                "FROM officer o " +
                "LEFT JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                "WHERE o.org_id = ? ORDER BY o.officer_id ASC"
            );
            ps.setInt(1, bond.util.SessionManager.getCurrentOrgId());
            java.sql.ResultSet rs = ps.executeQuery();
 
            int y = 4;
            boolean hasRows = false;
 
            while (rs.next()) {
 
                hasRows = true;
                int offId = rs.getInt("officer_id");
                String pos     = rs.getString("position");
                String name    = rs.getString("full_name");
                if ("President".equalsIgnoreCase(pos)) {
                    try {
                        java.sql.Connection cReg = bond.db.DBConnection.getConnection();
                        java.sql.PreparedStatement psReg = cReg.prepareStatement(
                            "SELECT appointed_by FROM registration_form WHERE form_id = " +
                            "(SELECT form_id FROM organization WHERE org_id = ?) LIMIT 1"
                        );
                        psReg.setInt(1, bond.util.SessionManager.getCurrentOrgId());
                        java.sql.ResultSet rsReg = psReg.executeQuery();
                        if (rsReg.next()) {
                            String appointed = rsReg.getString("appointed_by");
                            if (appointed != null && !appointed.trim().isEmpty()) {
                                name = appointed.trim();
                            }
                        }
                        cReg.close();
                    } catch (Exception ex) {
                        System.out.println("Load appointed name error: " + ex.getMessage());
                    }
                }
                
                
                String contact = rs.getString("year_label");
                if (contact == null) contact = "—";
 
                javax.swing.JLabel lName = new javax.swing.JLabel(name);
                lName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lName.setForeground(new java.awt.Color(40, 40, 40));
                lName.setBounds(14, y + 4, 200, 20);
                listPanel.add(lName);
 
                javax.swing.JLabel lPos = new javax.swing.JLabel(pos);
                lPos.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lPos.setForeground(new java.awt.Color(40, 40, 40));
                lPos.setBounds(230, y + 4, 180, 20);
                listPanel.add(lPos);
 
                javax.swing.JLabel lContact = new javax.swing.JLabel(contact);
                lContact.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
                lContact.setForeground(new java.awt.Color(40, 40, 40));
                lContact.setBounds(420, y + 4, 160, 20);
                listPanel.add(lContact);
 
                // Tag if this officer is the Org Admin
                try {
                    java.sql.Connection cTag = bond.db.DBConnection.getConnection();
                    java.sql.PreparedStatement psTag = cTag.prepareStatement(
                        "SELECT COUNT(*) FROM org_admin WHERE officer_id = ?"
                    );
                    psTag.setInt(1, offId);
                    java.sql.ResultSet rsTag = psTag.executeQuery();
                    rsTag.next();
                    if (rsTag.getInt(1) > 0) {
                        javax.swing.JLabel lAdmin = new javax.swing.JLabel("★ Org Admin");
                        lAdmin.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 10));
                        lAdmin.setForeground(new java.awt.Color(180, 130, 0));
                        lAdmin.setBounds(590, y + 4, 80, 20);
                        listPanel.add(lAdmin);
                    }
                    cTag.close();
                } catch (Exception ex) {}
 
                javax.swing.JButton btnDel = new javax.swing.JButton("Remove");
                btnDel.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                btnDel.setForeground(new java.awt.Color(180, 30, 30));
                btnDel.setBackground(new java.awt.Color(255, 240, 240));
                btnDel.setBorderPainted(false);
                btnDel.setFocusPainted(false);
                btnDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnDel.setBounds(582, y, 80, 26);
                final int oid = offId;
                final String finalName = name;
                btnDel.addActionListener(e -> {
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        parentWindow, "Remove " + finalName + "?", "Confirm",
                        javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        try {
                            java.sql.Connection c2 = bond.db.DBConnection.getConnection();
 
                            java.sql.PreparedStatement chk = c2.prepareStatement(
                                "SELECT COUNT(*) FROM org_admin WHERE officer_id = ?"
                            );
                            chk.setInt(1, oid);
                            java.sql.ResultSet chkRs = chk.executeQuery();
                            chkRs.next();
                            if (chkRs.getInt(1) > 0) {
                                javax.swing.JOptionPane.showMessageDialog(parentWindow,
                                    "Cannot remove this officer — they are linked to an Org Admin account.");
                                c2.close();
                                return;
                            }
 
                            java.sql.PreparedStatement ps2 = c2.prepareStatement(
                                "DELETE FROM officer WHERE officer_id = ?"
                            );
                            ps2.setInt(1, oid);
                            ps2.executeUpdate();
                            c2.close();
                            listPanel.removeAll();
                            listHeight[0] = 0;
                            loadOfficerRows(listPanel, listHeight, scroll, parentWindow);
                        } catch (Exception ex) {
                            System.out.println("Remove officer error: " + ex.getMessage());
                        }
                    }
                });
                listPanel.add(btnDel);
 
                y += 34;
            }
 
            if (!hasRows) {
                javax.swing.JLabel none = new javax.swing.JLabel("No officers added yet.");
                none.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.ITALIC, 12));
                none.setForeground(new java.awt.Color(160, 160, 160));
                none.setBounds(14, 8, 300, 20);
                listPanel.add(none);
                y += 30;
            }
 
            listHeight[0] = y + 10;
            listPanel.setPreferredSize(new java.awt.Dimension(670, listHeight[0]));
            listPanel.revalidate();
            listPanel.repaint();
            conn.close();
 
        } catch (Exception ex) {
            System.out.println("Load officers error: " + ex.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ActiveTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_OrgProfActiveTab.png"))); // NOI18N
        ActiveTab.setText("jLabel4");
        ActiveTab.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(ActiveTab);
        ActiveTab.setBounds(0, 0, 1000, 600);

        SearchBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SearchBar.png"))); // NOI18N
        SearchBar.setText("jLabel3");
        SearchBar.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(SearchBar);
        SearchBar.setBounds(0, 0, 1000, 600);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_EditOrgProf.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1000, 600);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_OrgProf.png"))); // NOI18N
        jLabel1.setText("jLabel3");
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
       
        //</editor-fold>

        /* Create and display the form */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new OrgProfileFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActiveTab;
    private javax.swing.JLabel SearchBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
