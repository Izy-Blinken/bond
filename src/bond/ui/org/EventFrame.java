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
public class EventFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EventFrame.class.getName());
 
    public EventFrame() {
        initComponents();           
        bond.util.SessionManager.loadSession(); 
        loadEvents(); 
        this.setSize(1000, 635);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jLabel2.setBounds(0, 0, 1000, 600);
        getContentPane().setLayout(null);
        
        javax.swing.JLabel lblOrgAcronym = new javax.swing.JLabel();
        lblOrgAcronym.setBounds(32, 135, 120, 25);
        lblOrgAcronym.setForeground(java.awt.Color.WHITE);
        lblOrgAcronym.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        lblOrgAcronym.setText(bond.util.SessionManager.getOrgAcronym() + " Admin");
        getContentPane().add(lblOrgAcronym);
        getContentPane().setComponentZOrder(lblOrgAcronym, 0);

        // ADD EVENT BUTTON 
        JButton btnAdd = makeInvisibleButton();
        btnAdd.setBounds(850, 220, 80, 30);
        btnAdd.addActionListener(e -> showImageDialog(
            "bond/assets/orgAdminImages/OrgAdmin_AddEvent.png", 1000, 600
        ));
        getContentPane().add(btnAdd);

        // ROW1
        JButton editRow1 = makeInvisibleButton();
        editRow1.setBounds(840, 300, 20, 20);
        editRow1.addActionListener(e -> showEditDialog(0));
        getContentPane().add(editRow1);

        JButton delRow1 = makeInvisibleButton();
        delRow1.setBounds(860, 300, 20, 20);
        delRow1.addActionListener(e -> showDeleteDialog(0));
        getContentPane().add(delRow1);

        // ROW2
        JButton editRow2 = makeInvisibleButton();
        editRow2.setBounds(840, 350, 20, 20);
        editRow2.addActionListener(e -> showEditDialog(1));
        getContentPane().add(editRow2);

        JButton delRow2 = makeInvisibleButton();
        delRow2.setBounds(860, 350, 20, 20);
        delRow2.addActionListener(e -> showDeleteDialog(1));
        getContentPane().add(delRow2);

        // ROW3 
        JButton editRow3 = makeInvisibleButton();
        editRow3.setBounds(840, 400, 20, 20);
        editRow3.addActionListener(e -> showEditDialog(2));
        getContentPane().add(editRow3);

        JButton delRow3 = makeInvisibleButton();
        delRow3.setBounds(860, 400, 20, 20);
        delRow3.addActionListener(e -> showDeleteDialog(2));
        getContentPane().add(delRow3);

        // ROW4 
        JButton editRow4 = makeInvisibleButton();
        editRow4.setBounds(840, 450, 20, 20);
        editRow4.addActionListener(e -> showEditDialog(3));
        getContentPane().add(editRow4);

        JButton delRow4 = makeInvisibleButton();
        delRow4.setBounds(860, 450, 20, 20);
        delRow4.addActionListener(e -> showDeleteDialog(3));
        getContentPane().add(delRow4);

        // ROW5
        JButton editRow5 = makeInvisibleButton();
        editRow5.setBounds(840, 500, 20, 20);
        editRow5.addActionListener(e -> showEditDialog(4));
        getContentPane().add(editRow5);

        JButton delRow5 = makeInvisibleButton();
        delRow5.setBounds(860, 500, 20, 20);
        delRow5.addActionListener(e -> showDeleteDialog(4));
        getContentPane().add(delRow5);

        getContentPane().add(jLabel2);
        
        // SIDEBAR NAV
        javax.swing.JButton btnDashboard = makeInvisibleButton();
        btnDashboard.setBounds(0, 196, 210, 40);
        btnDashboard.addActionListener(e -> navigateTo(new DashboardFrame()));
        getContentPane().add(btnDashboard);
        getContentPane().setComponentZOrder(btnDashboard, 0);

        javax.swing.JButton btnAnnouncement = makeInvisibleButton();
        btnAnnouncement.setBounds(0, 270, 210, 40);
        btnAnnouncement.addActionListener(e -> navigateTo(new AnnouncementFrame()));
        getContentPane().add(btnAnnouncement);
        getContentPane().setComponentZOrder(btnAnnouncement, 0);

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
    
    private String truncate(String text, int maxLength) {
    if (text == null) return "";
    return text.length() > maxLength ? text.substring(0, maxLength) + "..." : text;
}

    private void loadEvents() {
    bond.dao.EventDAO dao = new bond.dao.EventDAO();
    java.util.List<bond.model.Event> events = dao.getAllEvents(1);

    for (java.awt.Component c : getContentPane().getComponents()) {
        if (c instanceof JLabel && c != jLabel2) {
            getContentPane().remove(c);
        }
    }

    int[][] rowY = {{290}, {340}, {390}, {440}, {490}};

    for (int i = 0; i < Math.min(events.size(), 5); i++) {
        bond.model.Event e = events.get(i);
        int y = rowY[i][0];

        // Event Name
        JLabel lblName = new JLabel(truncate(e.getName(), 15));
        lblName.setBounds(280, y, 130, 40);
        lblName.setForeground(java.awt.Color.BLACK);
        lblName.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblName.setToolTipText(e.getName());
        getContentPane().add(lblName);

        // Date
        JLabel lblDate = new JLabel(e.getDate());
        lblDate.setBounds(430, y, 90, 40);
        lblDate.setForeground(java.awt.Color.BLACK);
        lblDate.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        getContentPane().add(lblDate);

        // Description
        JLabel lblDesc = new JLabel(truncate(e.getDescription(), 12));
        lblDesc.setBounds(560, y, 100, 40);
        lblDesc.setForeground(java.awt.Color.BLACK);
        lblDesc.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));

        // Wrap tooltip
        String wrapped = "<html>" + e.getDescription().replaceAll("(.{30})", "$1<br>") + "</html>";
        lblDesc.setToolTipText(wrapped);

        getContentPane().add(lblDesc);
    }
    
    getContentPane().setComponentZOrder(jLabel2, 
    getContentPane().getComponentCount() - 1);
    revalidate();
    repaint();
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

    private void showImageDialog(String imagePath, int width, int height) {
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

      JDialog dialog = new JDialog(this, "", true);
      dialog.setUndecorated(true);
      dialog.setSize(width, height);
      dialog.setLocationRelativeTo(this);
      dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
      dialog.getRootPane().setOpaque(false);

      java.net.URL imgURL = getClass().getClassLoader().getResource(imagePath);
      if (imgURL != null) {
      ImageIcon icon = new ImageIcon(imgURL);
      JLabel imgLabel = new JLabel(icon);
      imgLabel.setBounds(0, 0, width, height);

      JPanel panel = new JPanel(null) {
          @Override
          protected void paintComponent(java.awt.Graphics g) {
              // transparent
          }
      };
      panel.setOpaque(false);
      panel.setPreferredSize(new Dimension(width, height));

      JTextField eventNameField = new JTextField();
      eventNameField.setBounds(320, 210, 360, 30);
      eventNameField.setOpaque(false);
      eventNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
      eventNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      eventNameField.setForeground(java.awt.Color.BLACK); 
      panel.add(eventNameField);

      JTextField dateField = new JTextField();
      dateField.setBounds(320, 300, 230, 30);
      dateField.setOpaque(false);
      dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
      dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      dateField.setForeground(java.awt.Color.BLACK);
      panel.add(dateField);

      JTextArea descArea = new JTextArea();
      descArea.setBounds(320, 390, 365,65);
      descArea.setOpaque(false);
      descArea.setBackground(new java.awt.Color(0, 0, 0, 0));
      descArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      descArea.setForeground(java.awt.Color.BLACK);
      descArea.setLineWrap(true);
      descArea.setWrapStyleWord(true);
      panel.add(descArea);

      // BUTTONS 
      JButton btnClose = makeInvisibleButton();
      btnClose.setBounds(670, 95, 30, 30);
      btnClose.addActionListener(e -> {
          overlay.dispose();
          dialog.dispose();
      });
      panel.add(btnClose);

      JButton btnCancel = makeInvisibleButton();
      btnCancel.setBounds(530, 475, 75, 36);
      btnCancel.addActionListener(e -> {
          overlay.dispose();
          dialog.dispose();
      });
      panel.add(btnCancel);

      JButton btnSave = makeInvisibleButton();
      btnSave.setBounds(615, 475, 75, 36);
      btnSave.addActionListener(e -> {
          String name = eventNameField.getText().trim();
          String date = dateField.getText().trim();
          String desc = descArea.getText().trim();

          if (name.isEmpty() || date.isEmpty()) {
              javax.swing.JOptionPane.showMessageDialog(
                  dialog, "Please fill in Event Name and Date!",
                  "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
              );
              return;
          }

          bond.model.Event event = new bond.model.Event(
              1, name, date, desc
          );
          bond.dao.EventDAO dao = new bond.dao.EventDAO();
          boolean saved = dao.addEvent(event);

          if (saved) {
              javax.swing.JOptionPane.showMessageDialog(
                  dialog, "Event added successfully!"
              );
              overlay.dispose();
              dialog.dispose();
              loadEvents();
          } else {
              javax.swing.JOptionPane.showMessageDialog(
                  dialog, "Failed to add event!",
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
      bond.dao.EventDAO eventDao = new bond.dao.EventDAO();
      java.util.List<bond.model.Event> events = eventDao.getAllEvents(1);

      if (rowIndex >= events.size()) {
          javax.swing.JOptionPane.showMessageDialog(
              this, "No event found for this row!",
              "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
          );
          return;
      }

      bond.model.Event existingEvent = events.get(rowIndex);

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

      JDialog dialog = new JDialog(this, "", true);
      dialog.setUndecorated(true);
      dialog.setSize(1000, 600);
      dialog.setLocationRelativeTo(this);
      dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
      dialog.getRootPane().setOpaque(false);

      java.net.URL imgURL = getClass().getClassLoader()
          .getResource("bond/assets/orgAdminImages/OrgAdmin_EditEvent.png");

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

          // DEETS
          JTextField eventNameField = new JTextField(existingEvent.getName());
          eventNameField.setBounds(320, 210, 360, 30);
          eventNameField.setOpaque(false);
          eventNameField.setBackground(new java.awt.Color(0, 0, 0, 0));
          eventNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          eventNameField.setForeground(java.awt.Color.BLACK);
          panel.add(eventNameField);

          JTextField dateField = new JTextField(existingEvent.getDate());
          dateField.setBounds(320, 300, 230, 30);
          dateField.setOpaque(false);
          dateField.setBackground(new java.awt.Color(0, 0, 0, 0));
          dateField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          dateField.setForeground(java.awt.Color.BLACK);
          panel.add(dateField);

          JTextArea descArea = new JTextArea(existingEvent.getDescription());
          descArea.setBounds(320, 390, 365, 65);
          descArea.setOpaque(false);
          descArea.setBackground(new java.awt.Color(0, 0, 0, 0));
          descArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          descArea.setForeground(java.awt.Color.BLACK);
          descArea.setLineWrap(true);
          descArea.setWrapStyleWord(true);
          panel.add(descArea);

          JButton btnClose = makeInvisibleButton();
          btnClose.setBounds(670, 95, 30, 30);
          btnClose.addActionListener(e -> {
              overlay.dispose();
              dialog.dispose();
          });
          panel.add(btnClose);

          JButton btnCancel = makeInvisibleButton();
          btnCancel.setBounds(530, 475, 75, 36);
          btnCancel.addActionListener(e -> {
              overlay.dispose();
              dialog.dispose();
          });
          panel.add(btnCancel);

          JButton btnSave = makeInvisibleButton();
          btnSave.setBounds(615, 475, 75, 36);
          btnSave.addActionListener(e -> {
              String name = eventNameField.getText().trim();
              String date = dateField.getText().trim();
              String desc = descArea.getText().trim();

              System.out.println("Name: " + name);
              System.out.println("Date: " + date);
              System.out.println("Desc: " + desc);

              if (name.isEmpty() || date.isEmpty()) {
                  javax.swing.JOptionPane.showMessageDialog(
                      dialog, "Please fill in Event Name and Date!",
                      "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
                  );
                  return;
              }

              bond.model.Event event = new bond.model.Event(
                  1, name, date, desc
              );
              existingEvent.setName(name);
              existingEvent.setDate(date);
              existingEvent.setDescription(desc);
              boolean saved = eventDao.updateEvent(existingEvent);
              System.out.println("Save result: " + saved);

              if (saved) {
                  javax.swing.JOptionPane.showMessageDialog(
                      dialog, "Event added successfully!"
                  );
                  overlay.dispose();
                  dialog.dispose();
                  loadEvents();
              } else {
                  javax.swing.JOptionPane.showMessageDialog(
                      dialog, "Failed to add event!",
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
      bond.dao.EventDAO eventDao = new bond.dao.EventDAO();
      java.util.List<bond.model.Event> events = eventDao.getAllEvents(1);

      if (rowIndex >= events.size()) {
          javax.swing.JOptionPane.showMessageDialog(
              this, "No event found for this row!",
              "Warning", javax.swing.JOptionPane.WARNING_MESSAGE
          );
          return;
      }

      bond.model.Event existingEvent = events.get(rowIndex);

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

      JDialog dialog = new JDialog(this, "", true);
      dialog.setUndecorated(true);
      dialog.setSize(1000, 600);
      dialog.setLocationRelativeTo(this);
      dialog.setBackground(new java.awt.Color(0, 0, 0, 0));
      dialog.getRootPane().setOpaque(false);

      java.net.URL imgURL = getClass().getClassLoader()
          .getResource("bond/assets/orgAdminImages/OrgAdmin_DelEvent.png");

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

          JButton btnClose = makeInvisibleButton();
          btnClose.setBounds(670, 150, 30, 20);
          btnClose.addActionListener(e -> {
              overlay.dispose();
              dialog.dispose();
          });
          panel.add(btnClose);

          JButton btnCancel = makeInvisibleButton();
          btnCancel.setBounds(415, 412, 85, 40);
          btnCancel.addActionListener(e -> {
              overlay.dispose();
              dialog.dispose();
          });
          panel.add(btnCancel);

          JButton btnDelete = makeInvisibleButton();
          btnDelete.setBounds(520, 412, 85, 40);
          btnDelete.addActionListener(e -> {
              boolean deleted = eventDao.deleteEvent(existingEvent.getEventId());

              if (deleted) {
                  javax.swing.JOptionPane.showMessageDialog(
                      dialog, "Event deleted successfully!"
                  );
                  overlay.dispose();
                  dialog.dispose();
                  loadEvents();
              } else {
                  javax.swing.JOptionPane.showMessageDialog(
                      dialog, "Failed to delete event!",
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

        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/orgAdminImages/OrgAdmin_Events.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1000, 600);

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
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
