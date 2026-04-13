/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.UserSide;

/**
 *
 * @author denis
 */
public class studOrg extends javax.swing.JFrame {
    
    private int[] orgIds = new int[5];
    private javax.swing.JPanel orgCardPanel;
    private javax.swing.JScrollPane orgListScroll;
    private String currentCategoryFilter = "";

    private void setupHover(javax.swing.JButton btn) {

        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setForeground(java.awt.Color.WHITE);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(new java.awt.Color(150, 220, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.WHITE);
            }
        });

        darkPanel.setBackground(new java.awt.Color(0, 0, 0, 150));
        darkPanel.setVisible(false);
        settingsPanel.setVisible(false);

    }

    public studOrg() {
        initComponents();
        this.setLocationRelativeTo(null);
        searchInput1.setOpaque(false);
        searchInput1.setBorder(null);
        searchInput1.setBackground(new java.awt.Color(0, 0, 0, 0));
        javax.swing.JButton[] buttons = {
            dashboardBtn,
            studOrgBtn,
            aboutBtn,
            settingsBtn
        };
        for (javax.swing.JButton btn : buttons) {
            setupHover(btn);
        }

        jPanel1.setComponentZOrder(settingsBtn, 0);
        jPanel1.setComponentZOrder(aboutBtn, 1);
        jPanel1.setComponentZOrder(studOrgBtn, 2);
        jPanel1.setComponentZOrder(dashboardBtn, 3);
        jPanel1.setComponentZOrder(searchInput1, 4);
        searchInput1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { loadOrgs(searchInput1.getText().trim()); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { loadOrgs(searchInput1.getText().trim()); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { loadOrgs(searchInput1.getText().trim()); }
        });
        
        // Hide old course-label filter buttons
        bsitBtn.setVisible(false);
        bsedBtn.setVisible(false);
        bshmBtn.setVisible(false);
        bstmBtn.setVisible(false);
        bitBtn.setVisible(false);
        allBtn.setVisible(false);

        // Add a category dropdown
        String[] categories = {
            "All Categories",
            "Academic",
            "Civic & Cultural",
            "Religious",
            "Media & Publications",
            "Sports & Recreation"
        };
        javax.swing.JComboBox<String> categoryCombo = new javax.swing.JComboBox<>(categories);
        categoryCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
        categoryCombo.setBackground(java.awt.Color.WHITE);
        categoryCombo.setForeground(new java.awt.Color(28, 94, 56));
        categoryCombo.setBorder(javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(180, 210, 195)));
        jPanel1.add(categoryCombo,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 230, 30));
        jPanel1.setComponentZOrder(categoryCombo, 0);

        categoryCombo.addActionListener(e -> {
            String selected = (String) categoryCombo.getSelectedItem();
            String cat = "All Categories".equals(selected) ? "" : selected;
            loadOrgs(searchInput1.getText().trim(), cat);
        });

        // org cards
        org1.setVisible(false); org2.setVisible(false);
        org3.setVisible(false); org4.setVisible(false); org5.setVisible(false);

        // Create scroll 
        orgListScroll = new javax.swing.JScrollPane();
        orgListScroll.setHorizontalScrollBarPolicy(
            javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        orgListScroll.setBorder(null);
        orgListScroll.setOpaque(false);
        orgListScroll.getViewport().setOpaque(false);

        orgCardPanel = new javax.swing.JPanel(null);
        orgCardPanel.setOpaque(false);
        orgListScroll.setViewportView(orgCardPanel);

        // Position scroll panel 
        jPanel1.add(orgListScroll,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 880, 360));
        jPanel1.setComponentZOrder(orgListScroll, 1);        
        loadOrgs();
    }

    
    private void loadOrgs() {
        loadOrgs("", currentCategoryFilter);
    }

    private void loadOrgs(String query) {
        loadOrgs(query, currentCategoryFilter);
    }

    private void loadOrgs(String query, String category) {
        currentCategoryFilter = category;
        
        if (orgCardPanel == null) return;
        orgCardPanel.removeAll();

        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            String sql =
                "SELECT o.org_id, o.org_name, o.classification, o.status, o.logo_path, " +
                "(SELECT COUNT(*) FROM members m WHERE m.org_id = o.org_id) AS member_count " +
                "FROM organization o WHERE (o.org_name LIKE ? OR o.classification LIKE ? OR o.description LIKE ?)";
            if (category != null && !category.isEmpty()) sql += " AND o.classification = ?";
            sql += " ORDER BY o.org_name ASC";

            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
            
            if (category != null && !category.isEmpty()) {
                ps.setString(4, category);
            }
            java.sql.ResultSet rs = ps.executeQuery();

            java.util.List<String[]> rows = new java.util.ArrayList<>();
            while (rs.next()) {
                rows.add(new String[]{
                    String.valueOf(rs.getInt("org_id")),
                    rs.getString("org_name"),
                    rs.getString("classification"),
                    rs.getString("status"),
                    rs.getString("logo_path") != null ? rs.getString("logo_path") : "",
                    String.valueOf(rs.getInt("member_count"))
                });
            }
            conn.close();

            if (rows.isEmpty()) {
                javax.swing.JLabel none = new javax.swing.JLabel("No results found");
                none.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.ITALIC, 14));
                none.setForeground(new java.awt.Color(180, 180, 180));
                none.setBounds(20, 20, 400, 28);
                orgCardPanel.add(none);
                orgCardPanel.setPreferredSize(new java.awt.Dimension(860, 80));
                orgCardPanel.revalidate(); orgCardPanel.repaint();
                return;
            }

            int y = 8;
            for (String[] row : rows) {
                final int oid    = Integer.parseInt(row[0]);
                final String name      = row[1];
                final String cls       = row[2];
                final String status    = row[3];
                final String photoPath = row[4];
                final String members   = row[5];

                javax.swing.JPanel card = new javax.swing.JPanel(null);
                card.setBackground(new java.awt.Color(237, 245, 240));
                card.setBorder(javax.swing.BorderFactory.createLineBorder(
                    new java.awt.Color(180, 210, 195)));
                card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                card.setBounds(8, y, 840, 68);

                // Avatar / logo
                int photoSize = 42;
                boolean loaded = false;
                if (!photoPath.isEmpty()) {
                    try {
                        java.net.URL url = getClass().getClassLoader().getResource(photoPath);
                        if (url != null) {
                            java.awt.Image img = new javax.swing.ImageIcon(url).getImage()
                                .getScaledInstance(photoSize, photoSize, java.awt.Image.SCALE_SMOOTH);
                            javax.swing.JLabel pl = new javax.swing.JLabel(new javax.swing.ImageIcon(img));
                            pl.setBounds(10, 13, photoSize, photoSize);
                            card.add(pl);
                            loaded = true;
                        }
                    } catch (Exception ignored) {}
                }
                if (!loaded) {
                    final String letter = name.substring(0, 1).toUpperCase();
                    javax.swing.JLabel circle = new javax.swing.JLabel() {
                        @Override protected void paintComponent(java.awt.Graphics g) {
                            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
                            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                            g2.setColor(new java.awt.Color(28, 94, 56, 60));
                            g2.fillOval(0, 0, photoSize - 1, photoSize - 1);
                            g2.setColor(new java.awt.Color(28, 94, 56));
                            g2.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 16));
                            java.awt.FontMetrics fm = g2.getFontMetrics();
                            g2.drawString(letter,
                                (photoSize - fm.stringWidth(letter)) / 2,
                                (photoSize + fm.getAscent() - fm.getDescent()) / 2);
                        }
                    };
                    circle.setBounds(10, 13, photoSize, photoSize);
                    card.add(circle);
                }

                javax.swing.JLabel lblName = new javax.swing.JLabel(name);
                lblName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 14));
                lblName.setForeground(new java.awt.Color(28, 94, 56));
                lblName.setBounds(62, 10, 500, 24);
                card.add(lblName);

                javax.swing.JLabel lblInfo = new javax.swing.JLabel(cls + " · " + members + " members");
                lblInfo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 11));
                lblInfo.setForeground(new java.awt.Color(100, 140, 120));
                lblInfo.setBounds(62, 36, 450, 18);
                card.add(lblInfo);

                java.awt.Color sc = "Active".equals(status)
                    ? new java.awt.Color(28, 94, 56) : new java.awt.Color(180, 30, 30);
                javax.swing.JLabel lblStatus = new javax.swing.JLabel("● " + status);
                lblStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 11));
                lblStatus.setForeground(sc);
                lblStatus.setBounds(700, 24, 120, 20);
                card.add(lblStatus);

                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        new studOrgClicked(oid).setVisible(true);
                        dispose();
                    }
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        card.setBackground(new java.awt.Color(210, 235, 220));
                    }
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        card.setBackground(new java.awt.Color(237, 245, 240));
                    }
                });

                orgCardPanel.add(card);
                y += 76;
            }

            orgCardPanel.setPreferredSize(new java.awt.Dimension(860, y + 16));
            orgCardPanel.revalidate();
            orgCardPanel.repaint();

        } catch (Exception ex) {
            System.out.println("Load orgs error: " + ex.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        darkPanel = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        settings = new javax.swing.JPanel();
        settingsLabel = new javax.swing.JLabel();
        exBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        explore = new javax.swing.JLabel();
        studOrg = new javax.swing.JLabel();
        org5 = new javax.swing.JButton();
        org4 = new javax.swing.JButton();
        org3 = new javax.swing.JButton();
        org2 = new javax.swing.JButton();
        org1 = new javax.swing.JButton();
        bstmBtn = new javax.swing.JButton();
        bshmBtn = new javax.swing.JButton();
        bsedBtn = new javax.swing.JButton();
        bitBtn = new javax.swing.JButton();
        bsitBtn = new javax.swing.JButton();
        allBtn = new javax.swing.JButton();
        searchInput1 = new javax.swing.JTextField();
        searchIcon = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JButton();
        aboutBtn = new javax.swing.JButton();
        studOrgBtn = new javax.swing.JButton();
        dashboardBtn = new javax.swing.JButton();
        navbar = new javax.swing.JLabel();
        dark = new javax.swing.JLabel();
        bsu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        darkPanel.setBackground(new java.awt.Color(0, 0, 0));

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));
        settingsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settings.setBackground(new java.awt.Color(28, 94, 56));

        settingsLabel.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        settingsLabel.setForeground(new java.awt.Color(255, 255, 255));
        settingsLabel.setText("SETTINGS");

        exBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/exit_1.png"))); // NOI18N
        exBtn.setBorder(null);
        exBtn.setBorderPainted(false);
        exBtn.setContentAreaFilled(false);
        exBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/exitHover_1.png"))); // NOI18N
        exBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(exBtn)
                .addGap(17, 17, 17))
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsLayout.createSequentialGroup()
                        .addComponent(exBtn)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsLayout.createSequentialGroup()
                        .addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );

        settingsPanel.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 250, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/regOrg.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/regOrgHover.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        settingsPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 87, 210, 60));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/login.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/lohinSettingshover.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        settingsPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 143, 210, -1));

        javax.swing.GroupLayout darkPanelLayout = new javax.swing.GroupLayout(darkPanel);
        darkPanel.setLayout(darkPanelLayout);
        darkPanelLayout.setHorizontalGroup(
            darkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkPanelLayout.createSequentialGroup()
                .addGap(0, 790, Short.MAX_VALUE)
                .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        darkPanelLayout.setVerticalGroup(
            darkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(darkPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        explore.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        explore.setForeground(new java.awt.Color(255, 255, 255));
        explore.setText("Explore different organizations!");
        jPanel1.add(explore, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        studOrg.setFont(new java.awt.Font("Playfair Display", 1, 24)); // NOI18N
        studOrg.setForeground(new java.awt.Color(255, 255, 255));
        studOrg.setText("Student Organizations");
        jPanel1.add(studOrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 132, 360, 40));

        org5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshed.png"))); // NOI18N
        org5.setBorder(null);
        org5.setBorderPainted(false);
        org5.setContentAreaFilled(false);
        org5.setFocusable(false);
        org5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshedHover.png"))); // NOI18N
        org5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                org5ActionPerformed(evt);
            }
        });
        jPanel1.add(org5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 110, -1));

        org4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshed.png"))); // NOI18N
        org4.setBorder(null);
        org4.setBorderPainted(false);
        org4.setContentAreaFilled(false);
        org4.setFocusable(false);
        org4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshedHover.png"))); // NOI18N
        org4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                org4ActionPerformed(evt);
            }
        });
        jPanel1.add(org4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 110, -1));

        org3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshed.png"))); // NOI18N
        org3.setBorder(null);
        org3.setBorderPainted(false);
        org3.setContentAreaFilled(false);
        org3.setFocusable(false);
        org3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshedHover.png"))); // NOI18N
        org3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                org3ActionPerformed(evt);
            }
        });
        jPanel1.add(org3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, 110, -1));

        org2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshed.png"))); // NOI18N
        org2.setBorder(null);
        org2.setBorderPainted(false);
        org2.setContentAreaFilled(false);
        org2.setFocusable(false);
        org2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshedHover.png"))); // NOI18N
        org2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                org2ActionPerformed(evt);
            }
        });
        jPanel1.add(org2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 110, -1));

        org1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshed.png"))); // NOI18N
        org1.setBorder(null);
        org1.setBorderPainted(false);
        org1.setContentAreaFilled(false);
        org1.setFocusable(false);
        org1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/poshedHover.png"))); // NOI18N
        org1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                org1ActionPerformed(evt);
            }
        });
        jPanel1.add(org1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 110, -1));

        bstmBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bstm.png"))); // NOI18N
        bstmBtn.setBorder(null);
        bstmBtn.setBorderPainted(false);
        bstmBtn.setContentAreaFilled(false);
        bstmBtn.setFocusable(false);
        bstmBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bstmHover.png"))); // NOI18N
        jPanel1.add(bstmBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, -1, -1));

        bshmBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bshm.png"))); // NOI18N
        bshmBtn.setBorder(null);
        bshmBtn.setBorderPainted(false);
        bshmBtn.setContentAreaFilled(false);
        bshmBtn.setFocusable(false);
        bshmBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bshmHover.png"))); // NOI18N
        jPanel1.add(bshmBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, -1, -1));

        bsedBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bsed.png"))); // NOI18N
        bsedBtn.setBorder(null);
        bsedBtn.setBorderPainted(false);
        bsedBtn.setContentAreaFilled(false);
        bsedBtn.setFocusable(false);
        bsedBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bsedHover.png"))); // NOI18N
        jPanel1.add(bsedBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, -1, -1));

        bitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bit.png"))); // NOI18N
        bitBtn.setBorder(null);
        bitBtn.setBorderPainted(false);
        bitBtn.setContentAreaFilled(false);
        bitBtn.setFocusable(false);
        bitBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bitHover.png"))); // NOI18N
        bitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(bitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, -1, -1));

        bsitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bsit.png"))); // NOI18N
        bsitBtn.setBorder(null);
        bsitBtn.setBorderPainted(false);
        bsitBtn.setContentAreaFilled(false);
        bsitBtn.setFocusable(false);
        bsitBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bsitHover.png"))); // NOI18N
        jPanel1.add(bsitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        allBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/all.png"))); // NOI18N
        allBtn.setBorder(null);
        allBtn.setBorderPainted(false);
        allBtn.setContentAreaFilled(false);
        allBtn.setFocusable(false);
        allBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/allHover.png"))); // NOI18N
        allBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBtnActionPerformed(evt);
            }
        });
        jPanel1.add(allBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 60, -1));

        searchInput1.setBackground(new java.awt.Color(0, 0, 0));
        searchInput1.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        searchInput1.setBorder(null);
        searchInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInput1ActionPerformed(evt);
            }
        });
        jPanel1.add(searchInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 290, 30));

        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/searchBar_1.png"))); // NOI18N
        jPanel1.add(searchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 25, -1, -1));

        settingsBtn.setBackground(new java.awt.Color(28, 94, 56));
        settingsBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 16)); // NOI18N
        settingsBtn.setForeground(new java.awt.Color(255, 255, 255));
        settingsBtn.setText("SETTINGS");
        settingsBtn.setBorder(null);
        settingsBtn.setBorderPainted(false);
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setFocusable(false);
        settingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnActionPerformed(evt);
            }
        });
        jPanel1.add(settingsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, -1, 50));

        aboutBtn.setBackground(new java.awt.Color(28, 94, 56));
        aboutBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 16)); // NOI18N
        aboutBtn.setForeground(new java.awt.Color(255, 255, 255));
        aboutBtn.setText("ABOUT");
        aboutBtn.setBorder(null);
        aboutBtn.setBorderPainted(false);
        aboutBtn.setContentAreaFilled(false);
        aboutBtn.setFocusPainted(false);
        aboutBtn.setFocusable(false);
        aboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutBtnActionPerformed(evt);
            }
        });
        jPanel1.add(aboutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, 50));

        studOrgBtn.setBackground(new java.awt.Color(28, 94, 56));
        studOrgBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 16)); // NOI18N
        studOrgBtn.setForeground(new java.awt.Color(255, 255, 255));
        studOrgBtn.setText("STUDENT ORGS");
        studOrgBtn.setBorder(null);
        studOrgBtn.setBorderPainted(false);
        studOrgBtn.setContentAreaFilled(false);
        studOrgBtn.setFocusPainted(false);
        studOrgBtn.setFocusable(false);
        studOrgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studOrgBtnActionPerformed(evt);
            }
        });
        jPanel1.add(studOrgBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, 50));

        dashboardBtn.setBackground(new java.awt.Color(28, 94, 56));
        dashboardBtn.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 16)); // NOI18N
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setText("DASHBOARD");
        dashboardBtn.setBorder(null);
        dashboardBtn.setContentAreaFilled(false);
        dashboardBtn.setFocusPainted(false);
        dashboardBtn.setFocusable(false);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });
        jPanel1.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 170, 30));

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

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        darkPanel.setVisible(true);
        settingsPanel.setVisible(true);
        settingsPanel.setLocation(750, 0);
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void aboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutBtnActionPerformed
        new about().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutBtnActionPerformed

    private void studOrgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studOrgBtnActionPerformed

    }//GEN-LAST:event_studOrgBtnActionPerformed

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        new dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void searchInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInput1ActionPerformed
        String query = searchInput1.getText().trim();
        if (!query.isEmpty()) {
            loadOrgs(query);
        }
    }//GEN-LAST:event_searchInput1ActionPerformed

    private void exBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exBtnActionPerformed
        darkPanel.setVisible(false);
        settingsPanel.setVisible(false);
    }//GEN-LAST:event_exBtnActionPerformed

    private void bitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitBtnActionPerformed
        loadOrgs("", "Civic & Cultural");

    
    }//GEN-LAST:event_bitBtnActionPerformed

    private void allBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnActionPerformed
        searchInput1.setText("");
        loadOrgs("", "");
    }//GEN-LAST:event_allBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        new registerAnOrg(this).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.setVisible(false);
        loginChoices lc = new loginChoices(this);
        lc.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void org1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_org1ActionPerformed
        new studOrgClicked(orgIds[0]).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_org1ActionPerformed

    private void org2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_org2ActionPerformed
        if (orgIds[1] > 0) { 
            new studOrgClicked(orgIds[1]).setVisible(true);
            dispose(); 
        }

    }//GEN-LAST:event_org2ActionPerformed

    private void org5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_org5ActionPerformed
        if (orgIds[4] > 0) { 
        new studOrgClicked(orgIds[4]).setVisible(true);
        dispose(); 
        }

    }//GEN-LAST:event_org5ActionPerformed

    private void org3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_org3ActionPerformed
        if (orgIds[2] > 0) { 
            new studOrgClicked(orgIds[2]).setVisible(true); 
            dispose(); 
        }

    }//GEN-LAST:event_org3ActionPerformed

    private void org4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_org4ActionPerformed
        if (orgIds[3] > 0) { 
            new studOrgClicked(orgIds[3]).setVisible(true); 
            dispose(); 
        }

    }//GEN-LAST:event_org4ActionPerformed

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
            java.util.logging.Logger.getLogger(studOrg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studOrg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studOrg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studOrg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studOrg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutBtn;
    private javax.swing.JButton allBtn;
    private javax.swing.JButton bitBtn;
    private javax.swing.JButton bsedBtn;
    private javax.swing.JButton bshmBtn;
    private javax.swing.JButton bsitBtn;
    private javax.swing.JButton bstmBtn;
    private javax.swing.JLabel bsu;
    private javax.swing.JLabel dark;
    private javax.swing.JPanel darkPanel;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JButton exBtn;
    private javax.swing.JLabel explore;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel navbar;
    private javax.swing.JButton org1;
    private javax.swing.JButton org2;
    private javax.swing.JButton org3;
    private javax.swing.JButton org4;
    private javax.swing.JButton org5;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JTextField searchInput1;
    private javax.swing.JPanel settings;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel studOrg;
    private javax.swing.JButton studOrgBtn;
    // End of variables declaration//GEN-END:variables
}
