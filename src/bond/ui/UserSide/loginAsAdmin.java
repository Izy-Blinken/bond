/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.UserSide;

public class loginAsAdmin extends javax.swing.JFrame {

    // back method
    private void setupBackButton() {
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);
 
        backBtn.setForeground(java.awt.Color.WHITE);
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
 
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backBtn.setForeground(new java.awt.Color(200, 230, 210));
            }
 
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backBtn.setForeground(java.awt.Color.WHITE);
            }
        });
    }

    // method for placeholders
    private void setupPlaceholders() {
 
        // USERNAME
        usernameInput.setOpaque(false);
        usernameInput.setBorder(null);
        usernameInput.setBackground(new java.awt.Color(0, 0, 0, 0));
 
        usernameInput.setText("Enter your username");
        usernameInput.setForeground(new java.awt.Color(120, 120, 120));
 
        usernameInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (usernameInput.getText().equals("Enter your username")) {
                    usernameInput.setText("");
                    usernameInput.setForeground(new java.awt.Color(28, 94, 56));
                }
            }
 
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (usernameInput.getText().isEmpty()) {
                    usernameInput.setText("Enter your username");
                    usernameInput.setForeground(new java.awt.Color(120, 120, 120));
                }
            }
        });

         // PASSWORD
        passwordInput.setText("Enter your password");
        passwordInput.setEchoChar((char) 0);
        passwordInput.setForeground(new java.awt.Color(120, 120, 120));
 
        passwordInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordInput.getPassword()).equals("Enter your password")) {
                    passwordInput.setText("");
                    passwordInput.setEchoChar('•');
                    passwordInput.setForeground(new java.awt.Color(28, 94, 56));
                }
            }
 
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordInput.getPassword()).isEmpty()) {
                    passwordInput.setText("Enter your password");
                    passwordInput.setEchoChar((char) 0);
                    passwordInput.setForeground(new java.awt.Color(120, 120, 120));
                }
            }
        });
    }

    public loginAsAdmin() {
        initComponents();

        setLocationRelativeTo(null);
        setupBackButton();
        setupPlaceholders();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        usernameLbl = new javax.swing.JLabel();
        LoginAsOrgAdmin = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        passwordLbl = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        backBtn = new javax.swing.JButton();
        login = new javax.swing.JButton();
        inputIcon2 = new javax.swing.JLabel();
        inputIcon = new javax.swing.JLabel();
        navbar = new javax.swing.JLabel();
        dark = new javax.swing.JLabel();
        bsu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usernameLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        usernameLbl.setForeground(new java.awt.Color(255, 255, 255));
        usernameLbl.setText("USERNAME");
        jPanel1.add(usernameLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 120, 30));

        LoginAsOrgAdmin.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        LoginAsOrgAdmin.setForeground(new java.awt.Color(255, 255, 255));
        LoginAsOrgAdmin.setText("Login As Org Admin");
        jPanel1.add(LoginAsOrgAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        usernameInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        usernameInput.setBorder(null);
        usernameInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        usernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameInputActionPerformed(evt);
            }
        });
        jPanel1.add(usernameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 254, 420, 40));

        passwordLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        passwordLbl.setForeground(new java.awt.Color(255, 255, 255));
        passwordLbl.setText("PASSWORD");
        jPanel1.add(passwordLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 100, 30));

        passwordInput.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        passwordInput.setBorder(null);
        jPanel1.add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 420, 40));

        backBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("< Back");
        backBtn.setBorder(null);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 80, 30));

        login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/loginUserbtn.png"))); // NOI18N
        login.setBorder(null);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/loginHover.png"))); // NOI18N
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, -1, -1));

        inputIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/input.png"))); // NOI18N
        jPanel1.add(inputIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        inputIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/input.png"))); // NOI18N
        jPanel1.add(inputIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        navbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/navBar_1.png"))); // NOI18N
        jPanel1.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        dark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/dark_1.png"))); // NOI18N
        jPanel1.add(dark, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 80, 1020, 520));

        bsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bulsu.png"))); // NOI18N
        jPanel1.add(bsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 560));

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

    private void usernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameInputActionPerformed

    }//GEN-LAST:event_usernameInputActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        new loginChoices().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_backBtnActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed

        String username = usernameInput.getText().trim();
        String password = String.valueOf(passwordInput.getPassword()).trim();
 
        if (username.isEmpty() || username.equals("Enter your username")
                || password.isEmpty() || password.equals("Enter your password")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }
 
        bond.model.OrgAdmin admin = new bond.dao.OrgAdminDAO().login(username, password);
 
        if (admin != null) {
            bond.util.SessionManager.loginOrg(admin);
            new bond.ui.org.DashboardFrame().setVisible(true);
            this.dispose();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }


    }//GEN-LAST:event_loginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginAsAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LoginAsOrgAdmin;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel bsu;
    private javax.swing.JLabel dark;
    private javax.swing.JLabel inputIcon;
    private javax.swing.JLabel inputIcon2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JLabel navbar;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JTextField usernameInput;
    private javax.swing.JLabel usernameLbl;
    // End of variables declaration//GEN-END:variables
}
