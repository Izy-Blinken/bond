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
import Dashboard.Dashboard;

/**
 *
 * @author Erica
 */
public class OrgProfileFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OrgProfileFrame.class.getName());
    
    private javax.swing.JPanel contentPanel;
    private JTextField  emailField;
    private JTextField  typeField;
    private JTextArea   missionField;
    private JTextArea   visionField;
    private JTextField  adviserField;
    private JButton     btnEdit;
    private JButton     btnSave;
    private JButton     btnCancel;
 
 
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
        
        initComponents();
        ActiveTab.setBounds(0, 4, 1000, 600); 
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
 
        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);
        
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
        contentPanel.add(typeField);
 
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
                navigateTo(new Dashboard());
            }
        });
        getContentPane().add(btnExitAdmin);
        getContentPane().setComponentZOrder(btnExitAdmin, 0);
    
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
            java.sql.ResultSet rs = conn.prepareStatement(
                "SELECT * FROM organizations WHERE org_id = 1"
            ).executeQuery();
            if (rs.next()) {
                emailField.setText(rs.getString("name"));
                typeField.setText(rs.getString("type"));
                missionField.setText(rs.getString("mission"));
                visionField.setText(rs.getString("vision"));
                adviserField.setText(rs.getString("adviser"));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Load org error: " + ex.getMessage());
        }
    }

    private void enableEditing() {
        jLabel2.setVisible(true);
        contentPanel.setComponentZOrder(jLabel2, 3);
 
        emailField.setEditable(true);   emailField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        typeField.setEditable(true);    typeField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        missionField.setEditable(true); missionField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        visionField.setEditable(true);  visionField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        adviserField.setEditable(true); adviserField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
 
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        contentPanel.setComponentZOrder(btnSave,   1);
        contentPanel.setComponentZOrder(btnCancel, 2);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
 
    private void cancelEditing() {
        jLabel2.setVisible(false);
 
        emailField.setEditable(false);   emailField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        typeField.setEditable(false);    typeField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        missionField.setEditable(false); missionField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        visionField.setEditable(false);  visionField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        adviserField.setEditable(false); adviserField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
 
        emailField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        typeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        missionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        visionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        adviserField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
 
        btnEdit.setVisible(true);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
 
        loadOrgProfile();
    }
 
    private void saveProfile() {
        String name    = emailField.getText().trim();
        String type    = typeField.getText().trim();
        String mission = missionField.getText().trim();
        String vision  = visionField.getText().trim();
        String adviser = adviserField.getText().trim();
 
        if (name.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please fill in all fields!", "Warning",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE organizations SET name=?, type=?, mission=?, vision=?, adviser=? WHERE org_id=1"
            );
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, mission);
            ps.setString(4, vision);
            ps.setString(5, adviser);
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
    bond.dao.MemberDAO dao = new bond.dao.MemberDAO();
    java.util.List<bond.model.Member> all = dao.getAllMembers(1);

    java.util.List<java.awt.Component> toRemove = new java.util.ArrayList<>();
    for (java.awt.Component c : contentPanel.getComponents()) {
        if ("memberRow".equals(c.getName())) toRemove.add(c);
    }
    for (java.awt.Component c : toRemove) contentPanel.remove(c);

    int[] rowY = {596, 620, 644, 668, 692, 716, 740, 764, 788, 812};

    for (int i = 0; i < Math.min(all.size(), 10); i++) {
        bond.model.Member m = all.get(i);
        int y = rowY[i];

        javax.swing.JLabel lblName = makeMemberLabel(m.getName(), 320, y, 150);
        contentPanel.add(lblName);
        contentPanel.setComponentZOrder(lblName, 0);

        javax.swing.JLabel lblRole = makeMemberLabel(m.getRole(), 575, y, 150);
        contentPanel.add(lblRole);
        contentPanel.setComponentZOrder(lblRole, 0);

        javax.swing.JLabel lblCourse = makeMemberLabel(m.getCourse(), 795, y, 100);
        contentPanel.add(lblCourse);
        contentPanel.setComponentZOrder(lblCourse, 0);
    }

    contentPanel.revalidate();
    contentPanel.repaint();
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
 
            JTextField roleField = new JTextField();
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
                String role     = roleField.getText().trim();
                String course   = courseCombo.getSelectedItem().toString();
 
                if (fullName.isEmpty() || role.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Please fill in all fields!", "Warning",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
 
                bond.model.Member member = new bond.model.Member();
                member.setOrgId(1);
                member.setName(fullName);
                member.setRole(role);
                member.setCourse(course);
 
                boolean saved = new bond.dao.MemberDAO().addMember(member);
 
                if (saved) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Member added successfully!");
                    overlay.dispose();
                    dialog.dispose();
                    loadMembers();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to add member!", "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(btnAdd);
 
            panel.add(imgLabel);
            dialog.add(panel);
        }
 
        dialog.setVisible(true);
        overlay.dispose();
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
