/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

import bond.search.GlobalSearchBar;
import bond.search.GlobalSearchRegistry;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JTextField;
import bond.ui.UserSide.dashboard;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author Erica
 */
public class AnnouncementFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AnnouncementFrame.class.getName());
    
    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() > maxLength ? text.substring(0, maxLength) + "..." : text;
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
 
    private void navigateTo(javax.swing.JFrame targetFrame) {
        targetFrame.setVisible(true);
        this.dispose();
    }
    
    private void loadAnnouncements() { 
        
        java.util.List<java.awt.Component> toRemove = new java.util.ArrayList<>();
        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof JButton) {
                int x = c.getBounds().x;
                if (x == 795 || x == 817) {
                    toRemove.add(c);
                }
            }
            if (c instanceof JLabel 
                    && c != jLabel1 
                    && c != SearchBar
                    && c != AnnTable 
                    && c != AnnActions 
                    && c != ActiveTab 
                    && !"static".equals(((JLabel) c).getName())) {  
                toRemove.add(c);
            }
        }

        for (java.awt.Component c : toRemove) {
            getContentPane().remove(c);
        }

        bond.dao.AnnouncementDAO dao = new bond.dao.AnnouncementDAO();
        java.util.List<bond.model.Announcement> list = dao.getAllAnnouncements(bond.util.SessionManager.getCurrentOrgId());
        int[] yPositions = {290, 315, 340, 365, 390, 415, 440, 465, 490, 515};

        for (int i = 0; i < Math.min(list.size(), 10); i++) {
            bond.model.Announcement a = list.get(i);
            int y = yPositions[i];
            final String fullTitle = a.getTitle();

            JLabel lblTitle = new JLabel(truncate(fullTitle, 20));
            lblTitle.setBounds(295, y, 220, 20);
            lblTitle.setForeground(java.awt.Color.BLACK);
            lblTitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
            lblTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    javax.swing.JOptionPane.showMessageDialog(
                            null, fullTitle, "Title",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            });
            getContentPane().add(lblTitle);
            getContentPane().setComponentZOrder(lblTitle, 0);

            JLabel lblDate = new JLabel(a.getDate());
            lblDate.setBounds(570, y, 120, 20);
            lblDate.setForeground(java.awt.Color.BLACK);
            lblDate.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
            getContentPane().add(lblDate);
            getContentPane().setComponentZOrder(lblDate, 0);
        }


        int[] rowY = {290, 315, 340, 365, 390, 415, 440, 465, 490, 515};
        for (int i = 0; i < rowY.length; i++) {
            final int index = i;

            JButton editBtn = makeInvisibleButton();
            editBtn.setBounds(795, rowY[i], 20, 20);
            editBtn.addActionListener(e -> showEditDialog(index));
            getContentPane().add(editBtn);
            getContentPane().setComponentZOrder(editBtn, 0);

            JButton delBtn = makeInvisibleButton();
            delBtn.setBounds(817, rowY[i], 20, 20);
            delBtn.addActionListener(e -> showDeleteDialog(index));
            getContentPane().add(delBtn);
            getContentPane().setComponentZOrder(delBtn, 0);
        }

        revalidate();
        repaint();

        int total = getContentPane().getComponentCount();
        getContentPane().setComponentZOrder(jLabel1, total - 1);
        getContentPane().setComponentZOrder(SearchBar, total - 2);
        getContentPane().setComponentZOrder(AnnTable, total - 3);
        getContentPane().setComponentZOrder(AnnActions, total - 4);
        getContentPane().setComponentZOrder(ActiveTab, total - 5);

}

    /**
     * Creates new form AnnouncementFrame
     */
    
    public AnnouncementFrame() {
        initComponents();
        ActiveTab.setBounds(0, 2, 1000, 600); 
        getContentPane().setComponentZOrder(jLabel1, getContentPane().getComponentCount() - 1);
        getContentPane().setComponentZOrder(SearchBar, getContentPane().getComponentCount() - 2);
        getContentPane().setComponentZOrder(AnnTable, 1);
        getContentPane().setComponentZOrder(AnnActions, 2);
      
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


        javax.swing.JLabel lblAnnHeader = new javax.swing.JLabel("Announcement");
        lblAnnHeader.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 24));
        lblAnnHeader.setForeground(new java.awt.Color(15, 61, 34));
        lblAnnHeader.setBounds(244, 117, 300, 35);
        getContentPane().add(lblAnnHeader);
        getContentPane().setComponentZOrder(lblAnnHeader, 0);
        lblAnnHeader.setName("static");

        javax.swing.JLabel lblSubtitle = new javax.swing.JLabel("Post and manage announcements for your organizations");
        lblSubtitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 16));
        lblSubtitle.setForeground(new java.awt.Color(122, 158, 140));
        lblSubtitle.setBounds(244, 151, 600, 25);
        getContentPane().add(lblSubtitle);
        getContentPane().setComponentZOrder(lblSubtitle, 0);
        lblSubtitle.setName("static");
        
        javax.swing.JLabel AnnbigHeader = new javax.swing.JLabel("Announcement");
        AnnbigHeader.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 24));
        AnnbigHeader.setForeground(new java.awt.Color(15, 61, 34));
        AnnbigHeader.setBounds(244, 117, 300, 35);
        getContentPane().add(AnnbigHeader);
        getContentPane().setComponentZOrder(AnnbigHeader, 0);
        AnnbigHeader.setName("static");

        javax.swing.JLabel lblAnnTitle = new javax.swing.JLabel("Announcement");
        lblAnnTitle.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", java.awt.Font.BOLD, 15));
        lblAnnTitle.setForeground(new java.awt.Color(000000));
        lblAnnTitle.setBounds(270, 224, 180, 25);
        getContentPane().add(lblAnnTitle);
        getContentPane().setComponentZOrder(lblAnnTitle, 0);
        lblAnnTitle.setName("static");

        javax.swing.JLabel lblColTitle = new javax.swing.JLabel("Title");
        lblColTitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblColTitle.setForeground(new java.awt.Color(0x226B43));
        lblColTitle.setBounds(360, 264, 100, 20);
        getContentPane().add(lblColTitle);
        getContentPane().setComponentZOrder(lblColTitle, 0);
        lblColTitle.setName("static");

        javax.swing.JLabel lblColDate = new javax.swing.JLabel("Date");
        lblColDate.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblColDate.setForeground(new java.awt.Color(0x226B43));
        lblColDate.setBounds(575, 264, 100, 20);
        getContentPane().add(lblColDate);
        getContentPane().setComponentZOrder(lblColDate, 0);
        lblColDate.setName("static");

        javax.swing.JLabel lblColAction = new javax.swing.JLabel("Action");
        lblColAction.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblColAction.setForeground(new java.awt.Color(0x226B43));
        lblColAction.setBounds(800, 266, 100, 20);
        getContentPane().add(lblColAction);
        getContentPane().setComponentZOrder(lblColAction, 0);
        lblColAction.setName("static");
        
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
        jLabel1.setBounds(0, 0, 1000, 600);
 
        JButton btnAdd = makeInvisibleButton();
        btnAdd.setBounds(870, 220, 60, 28);
        btnAdd.addActionListener(e -> showAddDialog());
        getContentPane().add(btnAdd);
        getContentPane().setComponentZOrder(btnAdd, 0);
 
 
        loadAnnouncements();
 
        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);
         
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
 
        JButton btnOrgProfile = makeInvisibleButton();
        btnOrgProfile.setBounds(0, 313, 210, 40);
        btnOrgProfile.addActionListener(e -> navigateTo(new OrgProfileFrame()));
        getContentPane().add(btnOrgProfile);
        getContentPane().setComponentZOrder(btnOrgProfile, 0);
 
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
        
    }
    
    private JWindow createOverlay() {
        JWindow overlay = new JWindow(this);
        overlay.setSize(1000, 600);
        overlay.setLocation(
                this.getLocationOnScreen().x,
                this.getLocationOnScreen().y + 35);
        overlay.setBackground(new java.awt.Color(0, 0, 0, 120));
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                    g.setColor(new java.awt.Color(0, 0, 0, 120));
                    g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlayPanel.setOpaque(false);
        overlay.add(overlayPanel);
        overlay.setVisible(true);
        return overlay;
    }
    
     private void showAddDialog() {
        JWindow overlay = createOverlay();
        JDialog dialog = new JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_AddAnn.png");
 
        if (imgURL != null) {
            JPanel panel = new JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            JLabel imgLabel = new JLabel(new ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);
            

            /* JButton btnAddMemberTest = new JButton("+ Add Member");
            btnAddMemberTest.setBounds(612, 402, 65, 30);
            panel.add(btnAddMemberTest);  */
            
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Add Announcement");
            HeaderAdd.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
            HeaderAdd.setForeground(new java.awt.Color(15, 61, 34));
            HeaderAdd.setBounds(325, 170, 260, 40);
            panel.add(HeaderAdd);
            HeaderAdd.setName("static");
            
            javax.swing.JLabel TitleAdd = new javax.swing.JLabel("Title");
            TitleAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            TitleAdd.setForeground(new java.awt.Color(15, 61, 34));
            TitleAdd.setBounds(325, 225, 360, 40);
            panel.add(TitleAdd);
            TitleAdd.setName("static");
            
            javax.swing.JLabel DateAdd = new javax.swing.JLabel("Date (YYYY-MM-DD)");
            DateAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DateAdd.setForeground(new java.awt.Color(15, 61, 34));
            DateAdd.setBounds(325, 297, 360, 40);
            panel.add(DateAdd);
            DateAdd.setName("static");
 
            JTextField titleField = new JTextField();
            titleField.setBounds(330, 260, 360, 40);
            titleField.setOpaque(false);
            titleField.setBackground(new java.awt.Color(0, 0, 0, 0));
            titleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            titleField.setForeground(java.awt.Color.BLACK);
            panel.add(titleField);
 
            JTextField dateField = new JTextField();
            dateField.setBounds(330, 332, 360, 40);
            dateField.setOpaque(false);
            dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
            dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            dateField.setForeground(java.awt.Color.BLACK);
            panel.add(dateField);
 
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(660, 170, 20, 30);
            btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(540, 402, 65, 30);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            JButton btnSave = makeInvisibleButton();
            btnSave.setBounds(612, 402, 65, 30);
            btnSave.addActionListener(e -> {
                String title = titleField.getText().trim();
                String date  = dateField.getText().trim();
 
                if (title.isEmpty() || date.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                            "Please fill in Title and Date!", "Warning",
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
 
                bond.model.Announcement ann = new bond.model.Announcement(bond.util.SessionManager.getCurrentOrgId(), title, "", date);
                boolean saved = new bond.dao.AnnouncementDAO().addAnnouncement(ann);

                if (saved) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Announcement added!");
                    overlay.dispose();
                    dialog.dispose();
                    loadAnnouncements();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                            "Failed to add announcement!", "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(btnSave);
 
            panel.add(imgLabel);
            panel.setComponentZOrder(imgLabel, panel.getComponentCount() - 1);
            dialog.add(panel);
        }
 
        dialog.setVisible(true);
        overlay.dispose();
    }
 
     private void showEditDialog(int rowIndex) {
        bond.dao.AnnouncementDAO annDao = new bond.dao.AnnouncementDAO();
        java.util.List<bond.model.Announcement> list = annDao.getAllAnnouncements(bond.util.SessionManager.getCurrentOrgId()); 
        
        if (rowIndex >= list.size()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No announcement found for this row!", "Warning",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        bond.model.Announcement existing = list.get(rowIndex);
        JWindow overlay = createOverlay();
 
        JDialog dialog = new JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_EditAnn.png");
 
        if (imgURL != null) {
            JPanel panel = new JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            JLabel imgLabel = new JLabel(new ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);
 
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Edit Announcement");
            HeaderAdd.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
            HeaderAdd.setForeground(new java.awt.Color(15, 61, 34));
            HeaderAdd.setBounds(325, 170, 260, 40);
            panel.add(HeaderAdd);
            HeaderAdd.setName("static");
            
            javax.swing.JLabel TitleAdd = new javax.swing.JLabel("Title");
            TitleAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            TitleAdd.setForeground(new java.awt.Color(15, 61, 34));
            TitleAdd.setBounds(325, 225, 360, 40);
            panel.add(TitleAdd);
            TitleAdd.setName("static");
            
            javax.swing.JLabel DateAdd = new javax.swing.JLabel("Date (YYYY-MM-DD)");
            DateAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DateAdd.setForeground(new java.awt.Color(15, 61, 34));
            DateAdd.setBounds(325, 297, 360, 40);
            panel.add(DateAdd);
            DateAdd.setName("static");
 
            JTextField titleField = new JTextField(existing.getTitle());
            titleField.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
            titleField.setBounds(330, 260, 360, 40);
            titleField.setOpaque(false);
            titleField.setBackground(new java.awt.Color(0, 0, 0, 0));
            titleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            titleField.setForeground(java.awt.Color.BLACK);
            panel.add(titleField);
 
            JTextField dateField = new JTextField(existing.getDate());
            dateField.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
            dateField.setBounds(330, 332, 360, 40);
            dateField.setOpaque(false);
            dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
            dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            dateField.setForeground(java.awt.Color.BLACK);
            panel.add(dateField);
 
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(660, 170, 20, 30);
            btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(540, 402, 65, 30);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            JButton btnSave = makeInvisibleButton();
            btnSave.setBounds(612, 402, 65, 30);
            btnSave.addActionListener(e -> {
                String title = titleField.getText().trim();
                String date  = dateField.getText().trim();
 
                if (title.isEmpty() || date.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Please fill in Title and Date!", "Warning",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
 
                existing.setTitle(title);
                existing.setDate(date);
                boolean updated = annDao.updateAnnouncement(existing);
 
                if (updated) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Announcement updated!");
                    overlay.dispose();
                    dialog.dispose();
                    loadAnnouncements();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to update announcement!", "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(btnSave);
 
            panel.add(imgLabel);
            dialog.add(panel);
        }
 
        dialog.setVisible(true);
        overlay.dispose();
    }

    private void showDeleteDialog(int rowIndex) {
        bond.dao.AnnouncementDAO annDao = new bond.dao.AnnouncementDAO();
        java.util.List<bond.model.Announcement> list = annDao.getAllAnnouncements(bond.util.SessionManager.getCurrentOrgId()); 
        
        if (rowIndex >= list.size()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No announcement found for this row!", "Warning",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        bond.model.Announcement existing = list.get(rowIndex);
        JWindow overlay = createOverlay();
 
        JDialog dialog = new JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_DelAnn.png");
 
        if (imgURL != null) {
            JPanel panel = new JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            JLabel imgLabel = new JLabel(new ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);         
            
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Delete Announcement");
            HeaderAdd.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
            HeaderAdd.setForeground(new java.awt.Color(15, 61, 34));
            HeaderAdd.setBounds(325, 170, 260, 40);
            panel.add(HeaderAdd);
            HeaderAdd.setName("static");
            
            javax.swing.JLabel ConfirmTitle = new javax.swing.JLabel("Are you sure?");
            ConfirmTitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 16));
            ConfirmTitle.setForeground(new java.awt.Color(61, 102, 82));
            ConfirmTitle.setBounds(450, 310, 260, 40);
            panel.add(ConfirmTitle);
            ConfirmTitle.setName("static");
            
            javax.swing.JLabel ConfirmSubTitle = new javax.swing.JLabel("This record will be permanently removed.");
            ConfirmSubTitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 14));
            ConfirmSubTitle.setForeground(new java.awt.Color(61, 102, 82));
            ConfirmSubTitle.setBounds(365, 335, 285, 40);
            panel.add(ConfirmSubTitle);
            ConfirmSubTitle.setName("static");
            
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(660, 170, 20, 30);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(430, 388, 65, 30);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            JButton btnDelete = makeInvisibleButton();
            btnDelete.setBounds(505, 388, 65, 30);
            btnDelete.addActionListener(e -> {
                boolean deleted = annDao.deleteAnnouncement(existing.getAnnId());
 
                if (deleted) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Announcement deleted!");
                    overlay.dispose();
                    dialog.dispose();
                    loadAnnouncements();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to delete announcement!", "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(btnDelete);
 
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
        AnnActions = new javax.swing.JLabel();
        AnnTable = new javax.swing.JLabel();
        SearchBar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ActiveTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_AnnActiveTab.png"))); // NOI18N
        ActiveTab.setText("jLabel2");
        ActiveTab.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(ActiveTab);
        ActiveTab.setBounds(0, 0, 1000, 600);

        AnnActions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_ActionBtns.png"))); // NOI18N
        AnnActions.setText("jLabel3");
        getContentPane().add(AnnActions);
        AnnActions.setBounds(0, 0, 1000, 600);

        AnnTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_AnnTable.png"))); // NOI18N
        AnnTable.setText("jLabel2");
        AnnTable.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(AnnTable);
        AnnTable.setBounds(0, 0, 1000, 600);

        SearchBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SearchBar.png"))); // NOI18N
        SearchBar.setText("jLabel2");
        SearchBar.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(SearchBar);
        SearchBar.setBounds(0, 0, 1000, 600);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Ann.png"))); // NOI18N
        jLabel1.setText("jLabel2");
        jLabel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

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
        java.awt.EventQueue.invokeLater(() -> new AnnouncementFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActiveTab;
    private javax.swing.JLabel AnnActions;
    private javax.swing.JLabel AnnTable;
    private javax.swing.JLabel SearchBar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
