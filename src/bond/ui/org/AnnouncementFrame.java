/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.org;

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
    private void loadAnnouncements() {

        java.util.List<java.awt.Component> toRemove = new java.util.ArrayList<>();
        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof JLabel && c != jLabel1) {
                toRemove.add(c);
            }
        }
        for (java.awt.Component c : toRemove) {
            getContentPane().remove(c);
        }
 
        bond.dao.AnnouncementDAO dao = new bond.dao.AnnouncementDAO();
        java.util.List<bond.model.Announcement> list = dao.getAllAnnouncements(1);
 
        int[] yPositions = {290, 315, 340, 365, 390, 415, 440, 460, 490, 515};
 
        for (int i = 0; i < Math.min(list.size(), 10); i++) {
            bond.model.Announcement a = list.get(i);
            int y = yPositions[i];
 
            JLabel lblTitle = new JLabel(truncate(a.getTitle(), 20));
            lblTitle.setBounds(295, y, 220, 20);
            lblTitle.setForeground(java.awt.Color.BLACK);
            lblTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
            lblTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            final String fullTitle = a.getTitle();
            lblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        fullTitle,
                        "Title",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });
            getContentPane().add(lblTitle);
 
            JLabel lblDate = new JLabel(a.getDate());
            lblDate.setBounds(570, y, 120, 20);
            lblDate.setForeground(java.awt.Color.BLACK);
            lblDate.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
            getContentPane().add(lblDate);
        }
 
        getContentPane().setComponentZOrder(jLabel1,
            getContentPane().getComponentCount() - 1);
        revalidate();
        repaint();
    }

    /**
     * Creates new form AnnouncementFrame
     */
    public AnnouncementFrame() {
        initComponents();
        bond.util.SessionManager.loadSession(); 
        this.setSize(1000, 635);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        jLabel1.setBounds(0, 0, 1000, 600);
 
        // ADD ANNOUNCEMENT BTN
        JButton btnAdd = makeInvisibleButton();
        btnAdd.setBounds(870, 220, 60, 28);
        btnAdd.addActionListener(e -> showAddDialog());
        getContentPane().add(btnAdd);
 
        // ROW1
        JButton editRow1 = makeInvisibleButton();
        editRow1.setBounds(795, 290, 20, 20);
        editRow1.addActionListener(e -> showEditDialog(0));
        getContentPane().add(editRow1);
        JButton delRow1 = makeInvisibleButton();
        delRow1.setBounds(817, 290, 20, 20);
        delRow1.addActionListener(e -> showDeleteDialog(0));
        getContentPane().add(delRow1);
 
        // ROW2
        JButton editRow2 = makeInvisibleButton();
        editRow2.setBounds(795, 315, 20, 20);
        editRow2.addActionListener(e -> showEditDialog(1));
        getContentPane().add(editRow2);
        JButton delRow2 = makeInvisibleButton();
        delRow2.setBounds(817, 315, 20, 20);
        delRow2.addActionListener(e -> showDeleteDialog(1));
        getContentPane().add(delRow2);
 
        // ROW3
        JButton editRow3 = makeInvisibleButton();
        editRow3.setBounds(795, 340, 20, 20);
        editRow3.addActionListener(e -> showEditDialog(2));
        getContentPane().add(editRow3);
        JButton delRow3 = makeInvisibleButton();
        delRow3.setBounds(817, 340, 20, 20);
        delRow3.addActionListener(e -> showDeleteDialog(2));
        getContentPane().add(delRow3);
 
        // ROW4
        JButton editRow4 = makeInvisibleButton();
        editRow4.setBounds(795, 365, 20, 20);
        editRow4.addActionListener(e -> showEditDialog(3));
        getContentPane().add(editRow4);
        JButton delRow4 = makeInvisibleButton();
        delRow4.setBounds(817, 365, 20, 20);
        delRow4.addActionListener(e -> showDeleteDialog(3));
        getContentPane().add(delRow4);
 
        // ROW5
        JButton editRow5 = makeInvisibleButton();
        editRow5.setBounds(795, 387, 20, 20);
        editRow5.addActionListener(e -> showEditDialog(4));
        getContentPane().add(editRow5);
        JButton delRow5 = makeInvisibleButton();
        delRow5.setBounds(817, 387, 20, 20);
        delRow5.addActionListener(e -> showDeleteDialog(4));
        getContentPane().add(delRow5);
 
        // ROW6
        JButton editRow6 = makeInvisibleButton();
        editRow6.setBounds(795, 410, 20, 20);
        editRow6.addActionListener(e -> showEditDialog(5));
        getContentPane().add(editRow6);
        JButton delRow6 = makeInvisibleButton();
        delRow6.setBounds(817, 410, 20, 20);
        delRow6.addActionListener(e -> showDeleteDialog(5));
        getContentPane().add(delRow6);
 
        // ROW7
        JButton editRow7 = makeInvisibleButton();
        editRow7.setBounds(795, 435, 20, 20);
        editRow7.addActionListener(e -> showEditDialog(6));
        getContentPane().add(editRow7);
        JButton delRow7 = makeInvisibleButton();
        delRow7.setBounds(817, 435, 20, 20);
        delRow7.addActionListener(e -> showDeleteDialog(6));
        getContentPane().add(delRow7);
 
        // ROW8
        JButton editRow8 = makeInvisibleButton();
        editRow8.setBounds(795, 460, 20, 20);
        editRow8.addActionListener(e -> showEditDialog(7));
        getContentPane().add(editRow8);
        JButton delRow8 = makeInvisibleButton();
        delRow8.setBounds(817, 460, 20, 20);
        delRow8.addActionListener(e -> showDeleteDialog(7));
        getContentPane().add(delRow8);
 
        // ROW9
        JButton editRow9 = makeInvisibleButton();
        editRow9.setBounds(795, 490, 20, 20);
        editRow9.addActionListener(e -> showEditDialog(8));
        getContentPane().add(editRow9);
        JButton delRow9 = makeInvisibleButton();
        delRow9.setBounds(817, 490, 20, 20);
        delRow9.addActionListener(e -> showDeleteDialog(8));
        getContentPane().add(delRow9);
 
        // ROW10
        JButton editRow10 = makeInvisibleButton();
        editRow10.setBounds(795, 510, 20, 20);
        editRow10.addActionListener(e -> showEditDialog(9));
        getContentPane().add(editRow10);
        JButton delRow10 = makeInvisibleButton();
        delRow10.setBounds(817, 510, 20, 20);
        delRow10.addActionListener(e -> showDeleteDialog(9));
        getContentPane().add(delRow10);
 
        getContentPane().add(jLabel1);
        loadAnnouncements();
        
        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);
        
        // SIDEBAR NAV
        javax.swing.JButton btnDashboard = makeInvisibleButton();
        btnDashboard.setBounds(0, 196, 210, 40);
        btnDashboard.addActionListener(e -> navigateTo(new DashboardFrame()));
        getContentPane().add(btnDashboard);
        getContentPane().setComponentZOrder(btnDashboard, 0);

        javax.swing.JButton btnEvents = makeInvisibleButton();
        btnEvents.setBounds(0, 230, 210, 40);
        btnEvents.addActionListener(e -> navigateTo(new EventFrame()));
        getContentPane().add(btnEvents);
        getContentPane().setComponentZOrder(btnEvents, 0);

        javax.swing.JButton btnOrgProfile = makeInvisibleButton();
        btnOrgProfile.setBounds(0, 313, 210, 40);
        btnOrgProfile.addActionListener(e -> navigateTo(new OrgProfileFrame()));
        getContentPane().add(btnOrgProfile);
        getContentPane().setComponentZOrder(btnOrgProfile, 0);
    
        JButton btnSettings = makeInvisibleButton();
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
 
    private void navigateTo(javax.swing.JFrame targetFrame) {
    targetFrame.setVisible(true);
    this.dispose();
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
 
    private JWindow createOverlay() {
        JWindow overlay = new JWindow(this);
        overlay.setSize(1000, 600);
        overlay.setLocation(
            this.getLocationOnScreen().x,
            this.getLocationOnScreen().y + 35
        );
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
            ImageIcon icon = new ImageIcon(imgURL);
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBounds(0, 0, 1000, 600);
 
            JPanel panel = new JPanel(null) {
                @Override
                protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            // Title field
            JTextField titleField = new JTextField();
            titleField.setBounds(310, 257, 380, 30);
            titleField.setOpaque(false);
            titleField.setBackground(new java.awt.Color(0, 0, 0, 0));
            titleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            titleField.setForeground(java.awt.Color.BLACK);
            panel.add(titleField);

            // Date field
            JTextField dateField = new JTextField();
            dateField.setBounds(310, 345, 380, 30);
            dateField.setOpaque(false);
            dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
            dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            dateField.setForeground(java.awt.Color.BLACK);
            panel.add(dateField);
 
            // X button
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(675, 140, 20, 30);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);

            // Cancel button
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(530, 428, 75, 40);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);

            // Save button
            JButton btnSave = makeInvisibleButton();
            btnSave.setBounds(615, 428, 75, 40);
            btnSave.addActionListener(e -> {
                String title = titleField.getText().trim();
                String date = dateField.getText().trim();
               
 
                if (title.isEmpty() || date.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(
                        dialog, "Please fill in Title and Date!",
                        "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
 
                bond.model.Announcement ann = new bond.model.Announcement(1, title, "", date);
                bond.dao.AnnouncementDAO dao = new bond.dao.AnnouncementDAO();
                boolean saved = dao.addAnnouncement(ann);
 
                if (saved) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Announcement added!");
                    overlay.dispose();
                    dialog.dispose();
                    loadAnnouncements();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                        dialog, "Failed to add announcement!",
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE
                    );
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
        bond.dao.AnnouncementDAO annDao = new bond.dao.AnnouncementDAO();
        java.util.List<bond.model.Announcement> list = annDao.getAllAnnouncements(1);
 
        if (rowIndex >= list.size()) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "No announcement found for this row!",
                "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
            );
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
            ImageIcon icon = new ImageIcon(imgURL);
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBounds(0, 0, 1000, 600);
 
            JPanel panel = new JPanel(null) {
                @Override
                protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            // Title field
            JTextField titleField = new JTextField(existing.getTitle());
            titleField.setBounds(320, 230, 360, 28);
            titleField.setOpaque(false);
            titleField.setBackground(new java.awt.Color(0, 0, 0, 0));
            titleField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            titleField.setForeground(java.awt.Color.BLACK);
            panel.add(titleField);

            // Date field
            JTextField dateField = new JTextField(existing.getDate());
            dateField.setBounds(320, 318, 360, 28);
            dateField.setOpaque(false);
            dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
            dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            dateField.setForeground(java.awt.Color.BLACK);
            panel.add(dateField);

            // X button
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(670, 115, 30, 25);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);

            // Cancel button
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(527, 397, 80, 40);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);

            // Save button
            JButton btnSave = makeInvisibleButton();
            btnSave.setBounds(615, 397, 80, 40);
            btnSave.addActionListener(e -> {
                String title = titleField.getText().trim();
                String date = dateField.getText().trim();

                if (title.isEmpty() || date.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(
                        dialog, "Please fill in Title and Date!",
                        "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
                    );
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
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                        dialog, "Failed to update announcement!",
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE
                    );
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
        java.util.List<bond.model.Announcement> list = annDao.getAllAnnouncements(1);
 
        if (rowIndex >= list.size()) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "No announcement found for this row!",
                "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
            );
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
            ImageIcon icon = new ImageIcon(imgURL);
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBounds(0, 0, 1000, 600);
 
            JPanel panel = new JPanel(null) {
                @Override
                protected void paintComponent(java.awt.Graphics g) {}
            };
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(1000, 600));
 
            // X button
            JButton btnClose = makeInvisibleButton();
            btnClose.setBounds(670, 150, 30, 20);
            btnClose.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnClose);
 
            // Cancel button
            JButton btnCancel = makeInvisibleButton();
            btnCancel.setBounds(415, 412, 85, 40);
            btnCancel.addActionListener(e -> { overlay.dispose(); dialog.dispose(); });
            panel.add(btnCancel);
 
            // Delete/Confirm button
            JButton btnDelete = makeInvisibleButton();
            btnDelete.setBounds(520, 412, 85, 40);
            btnDelete.addActionListener(e -> {
                boolean deleted = annDao.deleteAnnouncement(existing.getAnnId());
                if (deleted) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Announcement deleted!");
                    overlay.dispose();
                    dialog.dispose();
                    loadAnnouncements();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                        dialog, "Failed to delete announcement!",
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE
                    );
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

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Ann.png"))); // NOI18N
        jLabel1.setText("jLabel1");
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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
