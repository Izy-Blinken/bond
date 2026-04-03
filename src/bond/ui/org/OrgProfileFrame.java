/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Erica
 */
public class OrgProfileFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OrgProfileFrame.class.getName());
    // Fields
    private javax.swing.JPanel contentPanel;
    private JTextField emailField;
    private JTextField typeField;
    private JTextArea missionField;
    private JTextArea visionField;
    private JTextField adviserField;
    private JButton btnEdit;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnAddMember;
    private java.util.List<bond.model.Member> currentMembers = new java.util.ArrayList<>();
    private String currentFilter = "All";
    private JButton btnAll;
    private JButton btnPending;
    private JButton btnApproved;

    /**
     * Creates new form OrgProfileFrame
     */
    public OrgProfileFrame() {
    initComponents();
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
    lblOrgAcronym.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
    lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
    getContentPane().add(lblOrgAcronym);
    getContentPane().setComponentZOrder(lblOrgAcronym, 0);

    // OVERLAY
    jLabel2.setBounds(0, -290, 1000, 995);
    jLabel2.setVisible(false);
    contentPanel.add(jLabel2);
    
    emailField = new JTextField();
    emailField.setBounds(287, 207, 290, 25);
    emailField.setOpaque(false);
    emailField.setBackground(new java.awt.Color(0, 0, 0, 0));
    emailField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    emailField.setForeground(java.awt.Color.BLACK);
    emailField.setEditable(false);
    emailField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    contentPanel.add(emailField);

    typeField = new JTextField();
    typeField.setBounds(632, 207, 290, 25);
    typeField.setOpaque(false);
    typeField.setBackground(new java.awt.Color(0, 0, 0, 0));
    typeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    typeField.setForeground(java.awt.Color.BLACK);
    typeField.setEditable(false);
    typeField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    contentPanel.add(typeField);

    missionField = new JTextArea();
    missionField.setBounds(287, 276, 298, 72);
    missionField.setOpaque(false);
    missionField.setBackground(new java.awt.Color(0, 0, 0, 0));
    missionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    missionField.setForeground(java.awt.Color.BLACK);
    missionField.setLineWrap(true);
    missionField.setWrapStyleWord(true);
    missionField.setEditable(false);
    missionField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    contentPanel.add(missionField);

    visionField = new JTextArea();
    visionField.setBounds(633, 276, 298, 72);
    visionField.setOpaque(false);
    visionField.setBackground(new java.awt.Color(0, 0, 0, 0));
    visionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    visionField.setForeground(java.awt.Color.BLACK);
    visionField.setLineWrap(true);
    visionField.setWrapStyleWord(true);
    visionField.setEditable(false);
    visionField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    contentPanel.add(visionField);

    adviserField = new JTextField();
    adviserField.setBounds(289, 387, 290, 25);
    adviserField.setOpaque(false);
    adviserField.setBackground(new java.awt.Color(0, 0, 0, 0));
    adviserField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    adviserField.setForeground(java.awt.Color.BLACK);
    adviserField.setEditable(false);
    adviserField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    contentPanel.add(adviserField);

    btnEdit = makeInvisibleButton();
    btnEdit.setBounds(876, 128, 54, 35);
    btnEdit.addActionListener(e -> enableEditing());
    contentPanel.add(btnEdit);

    btnSave = makeInvisibleButton();
    btnSave.setBounds(840, 400, 100, 35);
    btnSave.setVisible(false);
    btnSave.addActionListener(e -> saveProfile());
    contentPanel.add(btnSave);
    
    btnAddMember = makeInvisibleButton();
    btnAddMember.setBounds(556, 521, 120, 33);
    btnAddMember.addActionListener(e -> showAddMemberDialog());
    contentPanel.add(btnAddMember);

    btnCancel = makeInvisibleButton();
    btnCancel.setBounds(770, 400, 70, 35);
    btnCancel.setVisible(false);
    btnCancel.addActionListener(e -> cancelEditing());
    contentPanel.add(btnCancel);
    
    JButton btnAll = makeInvisibleButton();
    btnAll.setBounds(690, 521, 55, 33);
    btnAll.addActionListener(e -> loadMembers("All"));
    contentPanel.add(btnAll);

    JButton btnPending = makeInvisibleButton();
    btnPending.setBounds(755, 521, 78, 33); 
    btnPending.addActionListener(e -> loadMembers("Pending"));
    contentPanel.add(btnPending);

    JButton btnApproved = makeInvisibleButton();
    btnApproved.setBounds(850, 521, 80, 33);
    btnApproved.addActionListener(e -> loadMembers("Approved"));
    contentPanel.add(btnApproved);

    // Z-order
    contentPanel.setComponentZOrder(btnEdit,   0);
    contentPanel.setComponentZOrder(btnSave,   1);
    contentPanel.setComponentZOrder(btnCancel, 2);
    contentPanel.setComponentZOrder(jLabel2,   3);
    contentPanel.setComponentZOrder(bgImage,   contentPanel.getComponentCount() - 1);

    // Scroll
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

    // no border
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
    loadOrgProfile();
    loadMembers("All");
    
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
            "Exit Admin", javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            //navigateTo(new LoginFrame());
        }
    });
    getContentPane().add(btnExitAdmin);
    getContentPane().setComponentZOrder(btnExitAdmin, 0);

    }
    
    private void loadOrgProfile() {
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM organizations WHERE org_id = 1"
            );
            java.sql.ResultSet rs = ps.executeQuery();
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
     
    private void navigateTo(javax.swing.JFrame targetFrame) {
    targetFrame.setVisible(true);
    this.dispose();
}
    
    private void enableEditing() {
    jLabel2.setVisible(true);
    contentPanel.setComponentZOrder(jLabel2, 3);
    contentPanel.revalidate();
    contentPanel.repaint();

    emailField.setEditable(true);
    typeField.setEditable(true);
    missionField.setEditable(true);
    visionField.setEditable(true);
    adviserField.setEditable(true);

    emailField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    typeField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    missionField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    visionField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    adviserField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

    btnEdit.setVisible(false);
    btnSave.setVisible(true);
    btnCancel.setVisible(true);
    contentPanel.setComponentZOrder(btnSave, 1);
    contentPanel.setComponentZOrder(btnCancel, 2);
    contentPanel.revalidate();
    contentPanel.repaint();
}
 
    private void cancelEditing() {
    jLabel2.setVisible(false); 

    emailField.setEditable(false);
    typeField.setEditable(false);
    missionField.setEditable(false);
    visionField.setEditable(false);
    adviserField.setEditable(false);

    emailField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    typeField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    missionField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    visionField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    adviserField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    emailField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    typeField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    missionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    visionField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    adviserField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

    btnEdit.setVisible(true);
    btnSave.setVisible(false);
    btnCancel.setVisible(false);
    jLabel2.setVisible(false);

    loadOrgProfile();
}

    private void saveProfile() {
        String name = emailField.getText().trim();
        String type = typeField.getText().trim();
        String mission = missionField.getText().trim();
        String vision = visionField.getText().trim();
        String adviser = adviserField.getText().trim();
 
        if (name.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Please fill in all fields!",
                "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }
 
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE organizations SET name=?, type=?, mission=?, " +
                "vision=?, adviser=? WHERE org_id=1"
            );
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, mission);
            ps.setString(4, vision);
            ps.setString(5, adviser);
            boolean updated = ps.executeUpdate() > 0;
            conn.close();
 
            if (updated) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "Profile updated successfully!"
                );
                cancelEditing();
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "Failed to update profile!",
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception ex) {
            System.out.println("Save org error: " + ex.getMessage());
        }
    }
 
    private JButton makeInvisibleButton() {
        JButton btn = new JButton();
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void showAddMemberDialog() {
    javax.swing.JWindow overlay = new javax.swing.JWindow(this);
    overlay.setSize(1000, 600);
    overlay.setLocation(
        this.getLocationOnScreen().x,
        this.getLocationOnScreen().y + 35
    );
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
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
        javax.swing.JLabel imgLabel = new javax.swing.JLabel(icon);
        imgLabel.setBounds(0, 0, 1000, 600);

        javax.swing.JPanel panel = new javax.swing.JPanel(null) {
            @Override
            protected void paintComponent(java.awt.Graphics g) {}
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new java.awt.Dimension(1000, 600));

        // FULL NAME
        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(345, 235, 335, 30);
        fullNameField.setOpaque(false);
        fullNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
        fullNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        fullNameField.setForeground(java.awt.Color.BLACK);
        panel.add(fullNameField);

        // ROLE
        JTextField roleField = new JTextField();
        roleField.setBounds(345, 307, 335, 30);
        roleField.setOpaque(false);
        roleField.setBackground(new java.awt.Color(0, 0, 0, 0));
        roleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        roleField.setForeground(java.awt.Color.BLACK);
        panel.add(roleField);

        // COURSE CHEVRON (dropdown)
        String[] courses = {"BSIT", "BIT", "BSED", "BSHM", "BSTM"};
        javax.swing.JComboBox<String> courseCombo = new javax.swing.JComboBox<>(courses);
        courseCombo.setBounds(345, 380, 335, 30);
        courseCombo.setOpaque(false);
        courseCombo.setBackground(new java.awt.Color(0, 0, 0, 0));
        courseCombo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        courseCombo.setForeground(java.awt.Color.BLACK);
        panel.add(courseCombo);

        // X BUTTON
        JButton btnClose = makeInvisibleButton();
        btnClose.setBounds(670, 140, 25, 28);
        btnClose.addActionListener(e -> {
            overlay.dispose();
            dialog.dispose();
        });
        panel.add(btnClose);

        // CANCEL BUTTON
        JButton btnCancel = makeInvisibleButton();
        btnCancel.setBounds(520, 432, 65, 30);
        btnCancel.addActionListener(e -> {
            overlay.dispose();
            dialog.dispose();
        });
        panel.add(btnCancel);

        // ADD MEMBER BUTTON
        JButton btnAdd = makeInvisibleButton();
        btnAdd.setBounds(590, 432, 100, 30);
        btnAdd.addActionListener(e -> {
            String fullName = fullNameField.getText().trim();
            String role     = roleField.getText().trim();
            String course   = courseCombo.getSelectedItem().toString();

            if (fullName.isEmpty() || role.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(
                    dialog, "Please fill in all fields!",
                    "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            bond.model.Member member = new bond.model.Member();
            member.setOrgId(1);
            member.setName(fullName);
            member.setRole(role);
            member.setCourse(course);

            bond.dao.MemberDAO dao = new bond.dao.MemberDAO();
            boolean saved = dao.addMember(member);

            if (saved) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Member added successfully!");
                overlay.dispose();
                dialog.dispose();
                loadMembers(currentFilter); // refresh table
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    dialog, "Failed to add member!",
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        });
        panel.add(btnAdd);

        // IMAGE LAST
        panel.add(imgLabel);
        dialog.add(panel);

    } else {
        System.out.println("Add member image not found: OrgAdmin_OrgProfMember.png");
    }

    dialog.setVisible(true);
    overlay.dispose();
}
    
    private void loadMembers(String filter) {
    currentFilter = filter;
    bond.dao.MemberDAO dao = new bond.dao.MemberDAO();
    java.util.List<bond.model.Member> all = dao.getAllMembers(1);

    currentMembers.clear();
    for (bond.model.Member m : all) {
        if (filter.equals("All") || m.getStatus().equalsIgnoreCase(filter)) {
            currentMembers.add(m);
        }
    }

    // Remove old member rows
    java.util.List<java.awt.Component> toRemove = new java.util.ArrayList<>();
    for (java.awt.Component c : contentPanel.getComponents()) {
        if ("memberRow".equals(c.getName())) {
            toRemove.add(c);
        }
    }
    for (java.awt.Component c : toRemove) {
        contentPanel.remove(c);
    }

    // Row Y positions
    int[] rowY = {596, 620, 644, 668, 692, 716, 740, 764, 788, 812};

    for (int i = 0; i < Math.min(currentMembers.size(), 10); i++) {
        bond.model.Member m = currentMembers.get(i);
        int y = rowY[i];
        final bond.model.Member finalMember = m;

        // NAME
        javax.swing.JLabel lblName = new javax.swing.JLabel(m.getName());
        lblName.setBounds(295, y, 100, 20);
        lblName.setForeground(java.awt.Color.BLACK);
        lblName.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblName.setName("memberRow");
        contentPanel.add(lblName);
        contentPanel.setComponentZOrder(lblName, 0);

        // ROLE
        javax.swing.JLabel lblRole = new javax.swing.JLabel(m.getRole());
        lblRole.setBounds(445, y, 100, 20);
        lblRole.setForeground(java.awt.Color.BLACK);
        lblRole.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblRole.setName("memberRow");
        contentPanel.add(lblRole);
        contentPanel.setComponentZOrder(lblRole, 0);

        // COURSE
        javax.swing.JLabel lblCourse = new javax.swing.JLabel(m.getCourse());
        lblCourse.setBounds(585, y, 80, 20);
        lblCourse.setForeground(java.awt.Color.BLACK);
        lblCourse.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblCourse.setName("memberRow");
        contentPanel.add(lblCourse);
        contentPanel.setComponentZOrder(lblCourse, 0);

        // STATUS
        javax.swing.JLabel lblStatus = new javax.swing.JLabel(m.getStatus());
        lblStatus.setBounds(710, y, 80, 20);
        lblStatus.setForeground(java.awt.Color.BLACK);
        lblStatus.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblStatus.setName("memberRow");
        contentPanel.add(lblStatus);
        contentPanel.setComponentZOrder(lblStatus, 0);

        // ACTION BUTTONS — based on status
        if (m.getStatus().equalsIgnoreCase("Pending")) {
            // APPROVE
            JButton btnApprove = makeImageButton(
                "bond/assets/orgAdminImages/OrgAdmin_MemberAppbtn.png", 46, 20
            );
            btnApprove.setBounds(810, y, 46, 20);
            btnApprove.setName("memberRow");
            btnApprove.addActionListener(e -> updateMemberStatus(finalMember, "Approved"));
            contentPanel.add(btnApprove);
            contentPanel.setComponentZOrder(btnApprove, 0);

            // REJECT
            JButton btnReject = makeImageButton(
                "bond/assets/orgAdminImages/OrgAdmin_MemberRejbtn.png", 46, 20
            );
            btnReject.setBounds(860, y, 46, 20);
            btnReject.setName("memberRow");
            btnReject.addActionListener(e -> updateMemberStatus(finalMember, "Rejected"));
            contentPanel.add(btnReject);
            contentPanel.setComponentZOrder(btnReject, 0);

        } else if (m.getStatus().equalsIgnoreCase("Approved") 
                || m.getStatus().equalsIgnoreCase("Rejected")) {
            // DELETE ONLY
            JButton btnDelete = makeImageButton(
                "bond/assets/orgAdminImages/OrgAdmin_MemberDelbtn.png", 46, 20
            );
            btnDelete.setBounds(840, y, 46, 20);
            btnDelete.setName("memberRow");
            btnDelete.addActionListener(e -> removeMember(finalMember));
            contentPanel.add(btnDelete);
            contentPanel.setComponentZOrder(btnDelete, 0);
        }
    }

    contentPanel.revalidate();
    contentPanel.repaint();
}
    
    private void updateMemberStatus(bond.model.Member member, String status) {
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE members SET status = ? WHERE member_id = ?"
            );
            ps.setString(1, status);
            ps.setInt(2, member.getMemberId());
            boolean updated = ps.executeUpdate() > 0;
            conn.close();

            if (updated) {
                javax.swing.JOptionPane.showMessageDialog(this, "Member status updated to: " + status);
                loadMembers(currentFilter);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Failed to update status.",
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Update status error: " + ex.getMessage());
        }
    }

    private void removeMember(bond.model.Member member) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to remove " + member.getName() + "?",
            "Confirm Remove",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {
                java.sql.Connection conn = bond.db.DBConnection.getConnection();
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM members WHERE member_id = ?"
                );
                ps.setInt(1, member.getMemberId());
                boolean deleted = ps.executeUpdate() > 0;
                conn.close();

                if (deleted) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Member removed successfully.");
                    loadMembers(currentFilter);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Failed to remove member.",
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Remove member error: " + ex.getMessage());
            }
        }
    }

    private JButton makeImageButton(String resourcePath, int width, int height) {
        JButton btn = new JButton();
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        java.net.URL imgURL = getClass().getClassLoader().getResource(resourcePath);
        if (imgURL != null) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
            java.awt.Image scaled = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            btn.setIcon(new javax.swing.ImageIcon(scaled));
        }
        return btn;
    }
    
  
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
