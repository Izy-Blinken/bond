/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

import bond.search.GlobalSearchBar;
import bond.search.GlobalSearchRegistry;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import bond.ui.UserSide.dashboard;

/**
 *
 * @author Erica
 */
public class EventFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EventFrame.class.getName());
    
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
 
 
    private void loadEvents() {
    java.util.List<java.awt.Component> toRemove = new java.util.ArrayList<>();
    for (java.awt.Component c : getContentPane().getComponents()) {
        if (c instanceof JLabel 
                && c != SearchBar 
                && c != EventsBG 
                && c != EventsPanel
                && c != ActiveTab
                && !"static".equals(((JLabel) c).getName())) {
            toRemove.add(c);
        }
    }
    for (java.awt.Component c : toRemove) {
        getContentPane().remove(c);
    }

    bond.dao.EventDAO dao = new bond.dao.EventDAO();
    java.util.List<bond.model.Event> events = dao.getAllEvents(bond.util.SessionManager.getCurrentOrgId());

    int[] rowY = {290, 340, 390, 440, 490};

    for (int i = 0; i < Math.min(events.size(), 5); i++) {
        bond.model.Event e = events.get(i);
        int y = rowY[i];

        JLabel lblName = new JLabel(truncate(e.getName(), 15));
        lblName.setBounds(280, y, 130, 40);
        lblName.setForeground(java.awt.Color.BLACK);
        lblName.setFont(new java.awt.Font("PlayfairDisplay", java.awt.Font.BOLD, 12));
        lblName.setToolTipText(e.getName());
        getContentPane().add(lblName);
        getContentPane().setComponentZOrder(lblName, 0);

        JLabel lblDate = new JLabel(e.getDate());
        lblDate.setBounds(430, y, 90, 40);
        lblDate.setForeground(java.awt.Color.BLACK);
        lblDate.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        getContentPane().add(lblDate);
        getContentPane().setComponentZOrder(lblDate, 0);

        String wrapped = "<html>" + e.getDescription().replaceAll("(.{30})", "$1<br>") + "</html>";
        JLabel lblDesc = new JLabel(truncate(e.getDescription(), 12));
        lblDesc.setBounds(560, y, 100, 40);
        lblDesc.setForeground(java.awt.Color.BLACK);
        lblDesc.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblDesc.setToolTipText(wrapped);
        getContentPane().add(lblDesc);
        getContentPane().setComponentZOrder(lblDesc, 0);
    }

    revalidate();
    repaint();

    int total = getContentPane().getComponentCount();
    getContentPane().setComponentZOrder(EventsBG, total - 1);
    getContentPane().setComponentZOrder(SearchBar, total - 2);
    getContentPane().setComponentZOrder(EventsPanel, total - 3);
    getContentPane().setComponentZOrder(ActiveTab, total - 4);
}
 
    public EventFrame() {
        this.setLocationRelativeTo(null);
        
        initComponents();    
        ActiveTab.setBounds(0, -2, 1000, 600); 
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


        javax.swing.JLabel lblAnnHeader = new javax.swing.JLabel("Events");
        lblAnnHeader.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 24));
        lblAnnHeader.setForeground(new java.awt.Color(15, 61, 34));
        lblAnnHeader.setBounds(244, 117, 300, 35);
        getContentPane().add(lblAnnHeader);
        getContentPane().setComponentZOrder(lblAnnHeader, 0);
        lblAnnHeader.setName("static");
        

        javax.swing.JLabel lblSubtitle = new javax.swing.JLabel("Add, update, or delete events — submitted events go to OSO for approval");
        lblSubtitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 16));
        lblSubtitle.setForeground(new java.awt.Color(122, 158, 140));
        lblSubtitle.setBounds(244, 151, 600, 25);
        getContentPane().add(lblSubtitle);
        getContentPane().setComponentZOrder(lblSubtitle, 0);
        lblSubtitle.setName("static");
        
        javax.swing.ImageIcon sbDefault = new javax.swing.ImageIcon(   
                getClass().getClassLoader().getResource("bond/assets/orgAdminImages/OrgAdmin_SearchBar.png"));
        javax.swing.ImageIcon sbHover = new javax.swing.ImageIcon(
                getClass().getClassLoader().getResource("bond/assets/orgAdminImages/OrgAdmin_SearchBarHover.png"));

        GlobalSearchBar searchBar = new GlobalSearchBar(this, result -> {
            switch (result.type) {
                case EVENT: navigateTo(new EventFrame()); break;
                case ANNOUNCEMENT: navigateTo(new AnnouncementFrame()); break;
                case MEMBER: navigateTo(new OrgProfileFrame()); break;
            }
    },
            () -> SearchBar.setIcon(sbHover),    // onFocusGained
            () -> SearchBar.setIcon(sbDefault)   // onFocusLost
        );
        searchBar.installInto(getContentPane());
 
        bond.util.SessionManager.loadSession();
        this.setSize(1000, 635);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        SearchBar.setBounds(0, 0, 1000, 600);

 
        JButton btnAdd = makeInvisibleButton();
        btnAdd.setBounds(850, 220, 80, 30);
        btnAdd.addActionListener(e -> showAddDialog());
        getContentPane().add(btnAdd);
 
        int[] rowY = {300, 350, 400, 450, 500};
 
        for (int i = 0; i < rowY.length; i++) {
            final int index = i;
 
            JButton editBtn = makeInvisibleButton();
            editBtn.setBounds(840, rowY[i], 20, 20);
            editBtn.addActionListener(e -> showEditDialog(index));
            getContentPane().add(editBtn);
 
            JButton delBtn = makeInvisibleButton();
            delBtn.setBounds(860, rowY[i], 20, 20);
            delBtn.addActionListener(e -> showDeleteDialog(index));
            getContentPane().add(delBtn);
        }
        
        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 13));
        lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);
        lblOrgAcronym.setName("static");
         

        loadEvents();
 
        JButton btnDashboard = makeInvisibleButton();
        btnDashboard.setBounds(0, 196, 210, 40);
        btnDashboard.addActionListener(e -> navigateTo(new DashboardFrame()));
        getContentPane().add(btnDashboard);
        getContentPane().setComponentZOrder(btnDashboard, 0);
 
        JButton btnAnnouncement = makeInvisibleButton();
        btnAnnouncement.setBounds(0, 270, 210, 40);
        btnAnnouncement.addActionListener(e -> navigateTo(new AnnouncementFrame()));
        getContentPane().add(btnAnnouncement);
        getContentPane().setComponentZOrder(btnAnnouncement, 0);
 
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
    
    private void showAddDialog() {
        JWindow overlay = createOverlay();
 
        JDialog dialog = new JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_AddEvent.png");
 
        if (imgURL != null) {
            JPanel panel = new JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            JLabel imgLabel = new JLabel(new ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);
            
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Add Events");
            HeaderAdd.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
            HeaderAdd.setForeground(new java.awt.Color(15, 61, 34));
            HeaderAdd.setBounds(325, 135, 260, 40);
            panel.add(HeaderAdd);
            HeaderAdd.setName("static");
            
            javax.swing.JLabel TitleAdd = new javax.swing.JLabel("Event Name");
            TitleAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            TitleAdd.setForeground(new java.awt.Color(15, 61, 34));
            TitleAdd.setBounds(325, 190, 360, 40);
            panel.add(TitleAdd);
            TitleAdd.setName("static");
            
            javax.swing.JLabel DateAdd = new javax.swing.JLabel("Date (YYYY-MM-DD)");
            DateAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DateAdd.setForeground(new java.awt.Color(15, 61, 34));
            DateAdd.setBounds(325, 265, 360, 40);
            panel.add(DateAdd);
            DateAdd.setName("static");
            
            javax.swing.JLabel DescAdd = new javax.swing.JLabel("Description");
            DescAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DescAdd.setForeground(new java.awt.Color(15, 61, 34));
            DescAdd.setBounds(325, 335, 360, 40);
            panel.add(DescAdd);
            DescAdd.setName("static");
 
 
            JTextField eventNameField = new JTextField();
            eventNameField.setBounds(330, 225, 340, 40);
            eventNameField.setOpaque(false);
            eventNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
            eventNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameField.setForeground(java.awt.Color.BLACK);
            panel.add(eventNameField);
 
            JTextField dateField = new JTextField();
            dateField.setBounds(330, 300, 360, 40);
            dateField.setOpaque(false);
            dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
            dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            dateField.setForeground(java.awt.Color.BLACK);
            panel.add(dateField);
 
            JTextArea descArea = new JTextArea();
            descArea.setBounds(330, 378, 340, 63);
            descArea.setOpaque(false);
            descArea.setBackground(new java.awt.Color(0, 0, 0, 0));
            descArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            descArea.setForeground(java.awt.Color.BLACK);
            descArea.setLineWrap(true);
            descArea.setWrapStyleWord(true);
            panel.add(descArea);
 
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(660, 147, 20, 30);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(530, 475, 75, 36);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            JButton btnSave = makeInvisibleButton();
            btnSave.setBounds(615, 475, 75, 36);
            btnSave.addActionListener(e -> {
                String name = eventNameField.getText().trim();
                String date = dateField.getText().trim();
                String desc = descArea.getText().trim();
 
                if (name.isEmpty() || date.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Please fill in Event Name and Date!", "Warning",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
 
                bond.model.Event event = new bond.model.Event(bond.util.SessionManager.getCurrentOrgId(), name, date, desc);
                boolean saved = new bond.dao.EventDAO().addEvent(event);

                if (saved) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Event added successfully!");
                    overlay.dispose();
                    dialog.dispose();
                    loadEvents();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to add event!", "Error",
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
 
 
    private void showEditDialog(int rowIndex) {
        bond.dao.EventDAO eventDao = new bond.dao.EventDAO();
        java.util.List<bond.model.Event> events = eventDao.getAllEvents(bond.util.SessionManager.getCurrentOrgId()); 
        
        if (rowIndex >= events.size()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No event found for this row!", "Warning",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        bond.model.Event existingEvent = events.get(rowIndex);
        JWindow overlay = createOverlay();
 
        JDialog dialog = new JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_EditEvent.png");
 
        if (imgURL != null) {
            JPanel panel = new JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            JLabel imgLabel = new JLabel(new ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);
 
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Edit Event");
            HeaderAdd.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 16));
            HeaderAdd.setForeground(new java.awt.Color(15, 61, 34));
            HeaderAdd.setBounds(325, 135, 260, 40);
            panel.add(HeaderAdd);
            HeaderAdd.setName("static");
            
            javax.swing.JLabel TitleAdd = new javax.swing.JLabel("Event Name");
            TitleAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            TitleAdd.setForeground(new java.awt.Color(15, 61, 34));
            TitleAdd.setBounds(325, 190, 360, 40);
            panel.add(TitleAdd);
            TitleAdd.setName("static");
            
            javax.swing.JLabel DateAdd = new javax.swing.JLabel("Date (YYYY-MM-DD)");
            DateAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DateAdd.setForeground(new java.awt.Color(15, 61, 34));
            DateAdd.setBounds(325, 265, 360, 40);
            panel.add(DateAdd);
            DateAdd.setName("static");
            
            javax.swing.JLabel DescAdd = new javax.swing.JLabel("Description");
            DescAdd.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
            DescAdd.setForeground(new java.awt.Color(15, 61, 34));
            DescAdd.setBounds(325, 335, 360, 40);
            panel.add(DescAdd);
            DescAdd.setName("static");
 
 
            JTextField eventNameField = new JTextField(existingEvent.getName());
            eventNameField.setBounds(330, 225, 340, 40);
            eventNameField.setOpaque(false);
            eventNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
            eventNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameField.setForeground(java.awt.Color.BLACK);
            panel.add(eventNameField);
 
            JTextField dateField = new JTextField(existingEvent.getDate());
            dateField.setBounds(330, 300, 360, 40);
            dateField.setOpaque(false);
            dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
            dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            dateField.setForeground(java.awt.Color.BLACK);
            panel.add(dateField);
 
            JTextArea descArea = new JTextArea(existingEvent.getDescription());
            descArea.setBounds(330, 378, 340, 63);
            descArea.setOpaque(false);
            descArea.setBackground(new java.awt.Color(0, 0, 0, 0));
            descArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            descArea.setForeground(java.awt.Color.BLACK);
            descArea.setLineWrap(true);
            descArea.setWrapStyleWord(true);
            panel.add(descArea);
 
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(660, 147, 20, 30);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(530, 475, 75, 36);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            JButton btnSave = makeInvisibleButton();
            btnSave.setBounds(615, 475, 75, 36);
            btnSave.addActionListener(e -> {
                String name = eventNameField.getText().trim();
                String date = dateField.getText().trim();
                String desc = descArea.getText().trim();
 
                if (name.isEmpty() || date.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Please fill in Event Name and Date!", "Warning",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
 
                existingEvent.setName(name);
                existingEvent.setDate(date);
                existingEvent.setDescription(desc);
                boolean updated = eventDao.updateEvent(existingEvent);
 
                if (updated) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Event updated successfully!");
                    overlay.dispose();
                    dialog.dispose();
                    loadEvents();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to update event!", "Error",
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
        bond.dao.EventDAO eventDao = new bond.dao.EventDAO();
        java.util.List<bond.model.Event> events = eventDao.getAllEvents(bond.util.SessionManager.getCurrentOrgId()); 
        
        if (rowIndex >= events.size()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No event found for this row!", "Warning",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        bond.model.Event existingEvent = events.get(rowIndex);
        JWindow overlay = createOverlay();
 
        JDialog dialog = new JDialog(this, "", true);
        dialog.setUndecorated(true);
        dialog.setSize(1000, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
        dialog.getRootPane().setOpaque(false);
 
        java.net.URL imgURL = getClass().getClassLoader()
            .getResource("bond/assets/orgAdminImages/OrgAdmin_DelEvent.png");
 
        if (imgURL != null) {
            JPanel panel = new JPanel(null) {
                @Override protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            JLabel imgLabel = new JLabel(new ImageIcon(imgURL));
            imgLabel.setBounds(0, 0, 1000, 600);
 
            javax.swing.JLabel HeaderAdd = new javax.swing.JLabel("Delete Event");
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
            
            javax.swing.JLabel ConfirmSubTitle = new javax.swing.JLabel("This event will be permanently removed.");
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
            btnDelete.setBounds(505, 388, 65, 30);            btnDelete.addActionListener(e -> {
                boolean deleted = eventDao.deleteEvent(existingEvent.getEventId());
 
                if (deleted) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Event deleted successfully!");
                    overlay.dispose();
                    dialog.dispose();
                    loadEvents();
                    GlobalSearchRegistry.getInstance().reload();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(dialog,
                        "Failed to delete event!", "Error",
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
        SearchBar = new javax.swing.JLabel();
        EventsPanel = new javax.swing.JLabel();
        EventsBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ActiveTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_EventsActiveTab.png"))); // NOI18N
        ActiveTab.setText("jLabel1");
        ActiveTab.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(ActiveTab);
        ActiveTab.setBounds(0, 0, 1000, 600);

        SearchBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_SearchBar.png"))); // NOI18N
        SearchBar.setText("jLabel2");
        SearchBar.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(SearchBar);
        SearchBar.setBounds(0, 0, 1000, 600);

        EventsPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_EventsPanel.png"))); // NOI18N
        EventsPanel.setText("jLabel3");
        EventsPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(EventsPanel);
        EventsPanel.setBounds(0, 0, 1000, 600);

        EventsBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Events.png"))); // NOI18N
        EventsBG.setText("jLabel1");
        EventsBG.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(EventsBG);
        EventsBG.setBounds(0, 0, 1000, 600);

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
        java.awt.EventQueue.invokeLater(() -> new EventFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActiveTab;
    private javax.swing.JLabel EventsBG;
    private javax.swing.JLabel EventsPanel;
    private javax.swing.JLabel SearchBar;
    // End of variables declaration//GEN-END:variables
}
