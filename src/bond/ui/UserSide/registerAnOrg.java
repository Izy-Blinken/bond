/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.UserSide;

/**
 *
 * @author denis
 */
public class registerAnOrg extends javax.swing.JFrame {

    private javax.swing.JFrame previousFrame;

    // back method
    private void setupBackButton() {
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);

        backBtn.setForeground(new java.awt.Color(28, 94, 56));
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backBtn.setForeground(new java.awt.Color(200, 230, 210));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backBtn.setForeground(new java.awt.Color(28, 94, 56));
            }
        });
    }

    private void addPlaceholder(javax.swing.text.JTextComponent field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(new java.awt.Color(120, 120, 120));

        field.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(new java.awt.Color(0, 0, 0));
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(new java.awt.Color(120, 120, 120));
                }
            }
        });

    }

    private String savedEmail = "";
    private String savedName = "";
    private String savedType = "";
    private String savedAppointed = "";
    private String savedMission = "";
    private String savedVision = "";
    private String savedObjectives = "";
    private String savedTarget = "";
    private String savedProposed = "";
    private String savedAdviser = "";

    // helper methodersss
    private boolean isValidInput(javax.swing.text.JTextComponent field, String placeholder) {
        String text = field.getText().trim();
        return !text.isEmpty() && !text.equals(placeholder);
    }

    private void clearForm() {
        emailInput.setText("org@gmail.com");
        nameInput.setText("Official name");
        appointedInput.setText("Full name of admin officer");

        missionInput.setText("Describe the organization's mission...");
        visionInput.setText("Describe the organization's vision...");
        objectivesInput.setText("List the key objectives...");
        targetInput.setText("Target members...");
        proposedInput.setText("List all officers with positions and names...");
        adviserInput.setText("Faculty adviser name");
    }
    

    private void initCustom() {
    setLocationRelativeTo(null);

    setupBackButton();

    missionInput.setBackground(java.awt.Color.WHITE);
    visionInput.setBackground(java.awt.Color.WHITE);
    objectivesInput.setBackground(java.awt.Color.WHITE);
    proposedInput.setBackground(java.awt.Color.WHITE);

    jScrollPane3.getViewport().setBackground(java.awt.Color.WHITE);
    jScrollPane4.getViewport().setBackground(java.awt.Color.WHITE);
    jScrollPane2.getViewport().setBackground(java.awt.Color.WHITE);
    jScrollPane5.getViewport().setBackground(java.awt.Color.WHITE);

    addPlaceholder(emailInput, "org@gmail.com");
    addPlaceholder(nameInput, "Official name");
    addPlaceholder(appointedInput, "Full name of admin officer");

    addPlaceholder(missionInput, "Describe the organization's mission...");
    addPlaceholder(visionInput, "Describe the organization's vision...");
    addPlaceholder(objectivesInput, "List the key objectives...");
    addPlaceholder(targetInput, "Target members...");
    addPlaceholder(proposedInput, "List all officers with positions and names...");
    addPlaceholder(adviserInput, "Faculty adviser name");
}
    
    
    public registerAnOrg() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        initCustom();
        jPanel2.setComponentZOrder(orgTypeDropdown, 0);
        orgTypeDropdown.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
       
    }

    public registerAnOrg(javax.swing.JFrame prev) {
        initComponents();

        this.previousFrame = prev;
        initCustom();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        navbar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        adviserInput = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        proposedInput = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        objectivesInput = new javax.swing.JTextArea();
        orgTypeDropdown = new javax.swing.JComboBox<>();
        targetInput = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        visionInput = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        missionInput = new javax.swing.JTextArea();
        appointedInput = new javax.swing.JTextField();
        emailInput = new javax.swing.JTextField();
        nameInput = new javax.swing.JTextField();
        fill = new javax.swing.JLabel();
        reg = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/navBar_1.png"))); // NOI18N
        jPanel1.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/submitBtn.png"))); // NOI18N
        submitBtn.setBorder(null);
        submitBtn.setBorderPainted(false);
        submitBtn.setContentAreaFilled(false);
        submitBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/submitHover.png"))); // NOI18N
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        jPanel2.add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 1070, -1, -1));

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/cancelBtn.png"))); // NOI18N
        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/cancelHover.png"))); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel2.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1070, -1, -1));

        adviserInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        adviserInput.setForeground(new java.awt.Color(28, 94, 56));
        adviserInput.setBorder(null);
        adviserInput.setOpaque(true);
        jPanel2.add(adviserInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 990, 380, 40));

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane5.setOpaque(false);

        proposedInput.setColumns(20);
        proposedInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        proposedInput.setForeground(new java.awt.Color(24, 94, 56));
        proposedInput.setLineWrap(true);
        proposedInput.setRows(5);
        proposedInput.setWrapStyleWord(true);
        proposedInput.setBorder(null);
        proposedInput.setMargin(new java.awt.Insets(5, 5, 5, 5));
        proposedInput.setOpaque(false);
        proposedInput.setPreferredSize(new java.awt.Dimension(488, 104));
        jScrollPane5.setViewportView(proposedInput);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 800, 390, 130));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setOpaque(false);

        objectivesInput.setColumns(20);
        objectivesInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        objectivesInput.setForeground(new java.awt.Color(24, 94, 56));
        objectivesInput.setLineWrap(true);
        objectivesInput.setRows(5);
        objectivesInput.setWrapStyleWord(true);
        objectivesInput.setBorder(null);
        objectivesInput.setMargin(new java.awt.Insets(5, 5, 5, 5));
        objectivesInput.setOpaque(false);
        objectivesInput.setPreferredSize(new java.awt.Dimension(490, 104));
        jScrollPane4.setViewportView(objectivesInput);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 545, 390, 90));

        orgTypeDropdown.setForeground(new java.awt.Color(28, 94, 56));
        orgTypeDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Academic", "Civic & Cultural", "Religious", "Media & Publications", "Sports & Recreation" }));
        orgTypeDropdown.setBorder(null);
        orgTypeDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orgTypeDropdownActionPerformed(evt);
            }
        });
        jPanel2.add(orgTypeDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 415, 430, 40));

        targetInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        targetInput.setForeground(new java.awt.Color(28, 94, 56));
        targetInput.setBorder(null);
        targetInput.setOpaque(true);
        jPanel2.add(targetInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 700, 380, 40));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setOpaque(false);

        visionInput.setColumns(20);
        visionInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        visionInput.setForeground(new java.awt.Color(24, 94, 56));
        visionInput.setLineWrap(true);
        visionInput.setRows(5);
        visionInput.setWrapStyleWord(true);
        visionInput.setBorder(null);
        visionInput.setMargin(new java.awt.Insets(5, 5, 5, 5));
        visionInput.setOpaque(false);
        visionInput.setPreferredSize(new java.awt.Dimension(490, 104));
        jScrollPane3.setViewportView(visionInput);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 390, 90));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        missionInput.setColumns(20);
        missionInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        missionInput.setForeground(new java.awt.Color(24, 94, 56));
        missionInput.setLineWrap(true);
        missionInput.setRows(5);
        missionInput.setWrapStyleWord(true);
        missionInput.setBorder(null);
        missionInput.setMargin(new java.awt.Insets(5, 5, 5, 5));
        missionInput.setOpaque(false);
        missionInput.setPreferredSize(new java.awt.Dimension(490, 104));
        jScrollPane2.setViewportView(missionInput);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 235, 390, 90));

        appointedInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        appointedInput.setForeground(new java.awt.Color(28, 94, 56));
        appointedInput.setBorder(null);
        appointedInput.setOpaque(true);
        jPanel2.add(appointedInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 508, 430, 40));

        emailInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        emailInput.setForeground(new java.awt.Color(28, 94, 56));
        emailInput.setToolTipText("");
        emailInput.setBorder(null);
        emailInput.setOpaque(true);
        emailInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailInputActionPerformed(evt);
            }
        });
        jPanel2.add(emailInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 430, 40));

        nameInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        nameInput.setForeground(new java.awt.Color(28, 94, 56));
        nameInput.setBorder(null);
        nameInput.setOpaque(true);
        nameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameInputActionPerformed(evt);
            }
        });
        jPanel2.add(nameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 322, 430, 40));

        fill.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        fill.setForeground(new java.awt.Color(122, 158, 140));
        fill.setText("Fill out the form below. Submissions are reviewed by the OSO Admin.");
        jPanel2.add(fill, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        reg.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        reg.setForeground(new java.awt.Color(28, 94, 56));
        reg.setText("Register an Organization");
        jPanel2.add(reg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 453, -1));

        backBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(122, 158, 140));
        backBtn.setText("< Back");
        backBtn.setBorder(null);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel2.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/RegisteranOrg.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 998, 1130));

        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1000, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        if (previousFrame != null) {
            previousFrame.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void emailInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailInputActionPerformed

    }//GEN-LAST:event_emailInputActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed

        String email = emailInput.getText().trim();
        String name = nameInput.getText().trim();
        String type = (String) orgTypeDropdown.getSelectedItem();
        String appointed = appointedInput.getText().trim();

        String mission = missionInput.getText().trim();
        String vision = visionInput.getText().trim();
        String objectivesText = objectivesInput.getText().trim();
        String target = targetInput.getText().trim();
        String proposed = proposedInput.getText().trim();
        String adviser = adviserInput.getText().trim();

        if (!isValidInput(emailInput, "org@gmail.com")
                || !isValidInput(nameInput, "Official name")
                || !isValidInput(appointedInput, "Full name of admin officer")
                || !isValidInput(missionInput, "Describe the organization's mission...")
                || !isValidInput(visionInput, "Describe the organization's vision...")
                || !isValidInput(objectivesInput, "List the key objectives...")
                || !isValidInput(targetInput, "Target members...")
                || !isValidInput(proposedInput, "List all officers with positions and names...")
                || !isValidInput(adviserInput, "Faculty adviser name")) {

            javax.swing.JOptionPane.showMessageDialog(this, "Please complete all fields!");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid email format!");
            return;
        }

        if (!savedEmail.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Organization already registered!");
            return;
        }

        try {

            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO registration_form (proposed_org_name, proposed_classification, contact_email, mission, vision, objectives, target_members, proposed_officers, adviser, appointed_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, email);
            ps.setString(4, mission);
            ps.setString(5, vision);
            ps.setString(6, objectivesText);
            ps.setString(7, target);
            ps.setString(8, proposed);
            ps.setString(9, adviser);
            ps.setString(10, appointed);
            ps.executeUpdate();
            conn.close();

            javax.swing.JOptionPane.showMessageDialog(this, "Registration submitted successfully!");
            clearForm();

        } catch (Exception ex) {
            System.out.println("Submit error: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, "Submission failed. Please try again.");
        }

    }//GEN-LAST:event_submitBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        clearForm();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void nameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameInputActionPerformed

    private void orgTypeDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgTypeDropdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orgTypeDropdownActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registerAnOrg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adviserInput;
    private javax.swing.JTextField appointedInput;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel fill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea missionInput;
    private javax.swing.JTextField nameInput;
    private javax.swing.JLabel navbar;
    private javax.swing.JTextArea objectivesInput;
    private javax.swing.JComboBox<String> orgTypeDropdown;
    private javax.swing.JTextArea proposedInput;
    private javax.swing.JLabel reg;
    private javax.swing.JButton submitBtn;
    private javax.swing.JTextField targetInput;
    private javax.swing.JTextArea visionInput;
    // End of variables declaration//GEN-END:variables
}
