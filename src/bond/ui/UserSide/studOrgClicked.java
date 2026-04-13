/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bond.ui.UserSide;

/**
 *
 * @author denis
 */
public class studOrgClicked extends javax.swing.JFrame {

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

        darkPanel1.setBackground(new java.awt.Color(0, 0, 0, 150));
        darkPanel1.setVisible(false);
        settingsPanel1.setVisible(false);

    }

    /**
     * Creates new form studOrgClicked
     */
    private int orgId;
    private javax.swing.JLabel eventTitle1 = new javax.swing.JLabel("");
private javax.swing.JLabel eventTitle2 = new javax.swing.JLabel("");

    public studOrgClicked(int orgId) {
        this.setLocationRelativeTo(null);
        this.orgId = orgId;
        initComponents();

        
        javax.swing.SwingUtilities.invokeLater(() -> {
            studOrgScroll.getVerticalScrollBar().setValue(0);
        });

        setLocationRelativeTo(null);
        settingsPanel1.setVisible(false);

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

        loadOrgData();
        locationLbl1.setVisible(false);
        dateLbl1.setLocation(450, dateLbl1.getY());
    }

    public studOrgClicked() {
        this(0);
    }
    
    //method for filter 
    private java.util.List<javax.swing.JLabel> eventRowLabels = new java.util.ArrayList<>();

    private void loadFilteredEvents(String filter) {
        for (javax.swing.JLabel lbl : eventRowLabels) jPanel1.remove(lbl);
        eventRowLabels.clear();
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();

            java.util.List<String[]> rows = new java.util.ArrayList<>();

            // Events
            String evSql;
            if ("Completed".equals(filter)) {
                evSql = "SELECT title, event_date AS dt, status AS evt_status, 'Event' AS type FROM event WHERE org_id=? AND status IN ('Completed','Cancelled') ORDER BY event_date DESC";
            } else if ("Upcoming".equals(filter)) {
                evSql = "SELECT title, event_date AS dt, status AS evt_status, 'Event' AS type FROM event WHERE org_id=? AND status IN ('Upcoming','Ongoing') ORDER BY event_date ASC";
            } else {
                evSql = "SELECT title, event_date AS dt, status AS evt_status, 'Event' AS type FROM event WHERE org_id=? ORDER BY event_date DESC";
            }
            java.sql.PreparedStatement ps1 = conn.prepareStatement(evSql);
            ps1.setInt(1, orgId);
            java.sql.ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                rows.add(new String[]{rs1.getString("title"), rs1.getString("dt"), rs1.getString("evt_status"), rs1.getString("type")});
            }

            // Announcements only shown in "All" view
            if (!"Completed".equals(filter) && !"Upcoming".equals(filter)) {
                java.sql.PreparedStatement ps2 = conn.prepareStatement(
                    "SELECT title, DATE(created_at) AS dt, 'Posted' AS ann_status, 'Announcement' AS type FROM announcement WHERE org_id=? ORDER BY created_at DESC"
                );
                ps2.setInt(1, orgId);
                java.sql.ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    rows.add(new String[]{rs2.getString("title"), rs2.getString("dt"), rs2.getString("ann_status"), rs2.getString("type")});
                }
            }

            conn.close();

            int rowY = 1870;
            for (String[] r : rows) {
                String title = r[0]; String date = r[1]; String status = r[2]; String type = r[3];

                // TYPE pill — x=60, w=105
                java.awt.Color typeBg = "Event".equals(type) ? new java.awt.Color(28, 94, 56) : new java.awt.Color(60, 120, 180);
                javax.swing.JLabel lType = new javax.swing.JLabel(type, javax.swing.SwingConstants.CENTER);
                lType.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 10));
                lType.setForeground(java.awt.Color.WHITE);
                lType.setBackground(typeBg);
                lType.setOpaque(true);
                lType.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 6, 2, 6));
                jPanel1.add(lType, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, rowY + 2, 105, 18));
                jPanel1.setComponentZOrder(lType, 0); eventRowLabels.add(lType);

                // TITLE — x=175, w=270
                javax.swing.JLabel lTitle = new javax.swing.JLabel(title);
                lTitle.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lTitle.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, rowY, 270, 22));
                jPanel1.setComponentZOrder(lTitle, 0); eventRowLabels.add(lTitle);

                // DATE — x=455, w=145
                javax.swing.JLabel lDate = new javax.swing.JLabel(date != null ? date : "—");
                lDate.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lDate.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, rowY, 145, 22));
                jPanel1.setComponentZOrder(lDate, 0); eventRowLabels.add(lDate);

                // STATUS — x=615, w=140
                java.awt.Color sc = "Completed".equals(status) || "Cancelled".equals(status)
                    ? new java.awt.Color(180, 100, 0)
                    : "Posted".equals(status) ? new java.awt.Color(60, 120, 180)
                    : new java.awt.Color(28, 94, 56);
                javax.swing.JLabel lStatus = new javax.swing.JLabel(status != null ? status : "—");
                lStatus.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD, 12));
                lStatus.setForeground(sc);
                jPanel1.add(lStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, rowY, 140, 22));
                jPanel1.setComponentZOrder(lStatus, 0); eventRowLabels.add(lStatus);
                
                rowY += 30;
            }
        } catch (Exception ex) { 
            System.out.println("Filter events error: " + ex.getMessage()); 
            ex.printStackTrace();
        }
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private javax.swing.JComboBox<String> yearFilterCombo;
    private java.util.Map<String, Integer> yearLabelToId = new java.util.LinkedHashMap<>();

    private void initOfficerYearFilter() {
        if (yearFilterCombo != null) jPanel1.remove(yearFilterCombo);
        yearLabelToId.clear();
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT DISTINCT ay.academic_year_id, ay.year_label " +
                "FROM officer o JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                "WHERE o.org_id = ? ORDER BY ay.academic_year_id DESC"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();
            java.util.List<String> labels = new java.util.ArrayList<>();
            while (rs.next()) {
                String lbl = rs.getString("year_label");
                int ayId   = rs.getInt("academic_year_id");
                labels.add(lbl);
                yearLabelToId.put(lbl, ayId);
            }
            conn.close();
            if (labels.isEmpty()) return;
            yearFilterCombo = new javax.swing.JComboBox<>(labels.toArray(new String[0]));
            yearFilterCombo.setSelectedIndex(0);
            yearFilterCombo.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 12));
            yearFilterCombo.setBackground(java.awt.Color.WHITE);
            yearFilterCombo.setForeground(new java.awt.Color(40, 40, 40));
            // ← Adjust x/y so the combo sits top-right of your officer table heading
            jPanel1.add(yearFilterCombo,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1195, 160, 28));
            jPanel1.setComponentZOrder(yearFilterCombo, 0);
            yearFilterCombo.addActionListener(e -> {
                String sel = (String) yearFilterCombo.getSelectedItem();
                if (sel != null) loadOfficerHistoryByYear(yearLabelToId.get(sel));
            });
            loadOfficerHistoryByYear(yearLabelToId.get(labels.get(0)));
        } catch (Exception ex) {
            System.out.println("Officer year filter error: " + ex.getMessage());
            ex.printStackTrace();
        }
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private void loadOfficerHistoryByYear(int academicYearId) {
        for (javax.swing.JLabel lbl : officerRowLabels) jPanel1.remove(lbl);
        officerRowLabels.clear();
        if (academicYearId < 0) { jPanel1.revalidate(); jPanel1.repaint(); return; }
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT o.full_name, o.position, ay.year_label " +
                "FROM officer o LEFT JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                "WHERE o.org_id = ? AND o.academic_year_id = ? " +
                "AND o.position IN " +
                "('President','Vice President','Secretary','Assistant Secretary'," +
                "'Treasurer','Assistant Treasurer','Auditor','Business Manager','Org Representative') " +
                "ORDER BY o.officer_id ASC"
            );
            ps.setInt(1, orgId);
            ps.setInt(2, academicYearId);
            java.sql.ResultSet rs = ps.executeQuery();
            int rowY = 1260;
            while (rs.next()) {
                String pos  = rs.getString("position")  != null ? rs.getString("position")  : "";
                String name = rs.getString("full_name") != null ? rs.getString("full_name") : "";
                String term = rs.getString("year_label")!= null ? rs.getString("year_label"): "—";
                
                javax.swing.JLabel lPos = new javax.swing.JLabel(pos);
                lPos.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                
                lPos.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, rowY, 280, 22));
                jPanel1.setComponentZOrder(lPos, 0); officerRowLabels.add(lPos);
                
                javax.swing.JLabel lName = new javax.swing.JLabel(name);
                lName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lName.setForeground(new java.awt.Color(40, 40, 40));
                
                jPanel1.add(lName, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, rowY, 280, 22));
                jPanel1.setComponentZOrder(lName, 0); officerRowLabels.add(lName);
                
                javax.swing.JLabel lTerm = new javax.swing.JLabel(term);
                lTerm.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lTerm.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lTerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, rowY, 160, 22));
                jPanel1.setComponentZOrder(lTerm, 0); officerRowLabels.add(lTerm);
                rowY += 30;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Load officer (by year) error: " + ex.getMessage());
            ex.printStackTrace();
        }
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private java.util.List<javax.swing.JLabel> officerRowLabels = new java.util.ArrayList<>();
    
    private void loadOfficerHistory() {
        for (javax.swing.JLabel lbl : officerRowLabels) {
            jPanel1.remove(lbl);
        }
        officerRowLabels.clear();

        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT o.full_name, o.position, ay.year_label " +
                "FROM officer o LEFT JOIN academic_year ay ON ay.academic_year_id = o.academic_year_id " +
                "WHERE o.org_id = ? AND o.position IN " +
                "('President','Vice President','Secretary','Assistant Secretary'," +
                "'Treasurer','Assistant Treasurer','Auditor','Business Manager','Org Representative') " +
                "ORDER BY ay.academic_year_id DESC, o.officer_id ASC"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();

            int rowY = 1260; // below header row at 1220
            while (rs.next()) {
                String name  = rs.getString("full_name");
                String pos   = rs.getString("position");
                String term  = rs.getString("year_label") != null ? rs.getString("year_label") : "—";

                javax.swing.JLabel lPos = new javax.swing.JLabel(pos);
                lPos.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lPos.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, rowY, 280, 22));
                jPanel1.setComponentZOrder(lPos, 0);
                officerRowLabels.add(lPos);


                javax.swing.JLabel lName = new javax.swing.JLabel(name);
                lName.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lName.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lName, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, rowY, 260, 22));
                jPanel1.setComponentZOrder(lName, 0);
                officerRowLabels.add(lName);

                javax.swing.JLabel lTerm = new javax.swing.JLabel(term);
                lTerm.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lTerm.setForeground(new java.awt.Color(40, 40, 40));
                jPanel1.add(lTerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, rowY, 160, 22));
                jPanel1.setComponentZOrder(lTerm, 0);
                officerRowLabels.add(lTerm);

                rowY += 30;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Load officer history error: " + ex.getMessage());
            ex.printStackTrace();
        }

        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private void loadOrgData() {
        
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();

            // Load org name + description + mission + vision
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT o.org_name, o.description, o.mission, o.vision, o.classification, " +
                "(SELECT a.full_name FROM adviser a WHERE a.org_id = o.org_id " +
                " ORDER BY a.adviser_id DESC LIMIT 1) AS adviser_name " +
                "FROM organization o WHERE o.org_id = ?"
            );
            ps.setInt(1, orgId);
            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                missionArea.setText(rs.getString("mission") != null ? rs.getString("mission") : "");
                visionArea.setText(rs.getString("vision")   != null ? rs.getString("vision")   : "");

                String orgName   = rs.getString("org_name")      != null ? rs.getString("org_name")      : "";
                String cls       = rs.getString("classification") != null ? rs.getString("classification") : "";
                String desc      = rs.getString("description")   != null ? rs.getString("description")   : "";
                String adviser   = rs.getString("adviser_name")  != null ? rs.getString("adviser_name")  : "—";

                // Org name: 
                javax.swing.JLabel lblOrgName = new javax.swing.JLabel(orgName);
                lblOrgName.setFont(new java.awt.Font("Playfair Display", java.awt.Font.BOLD, 20));
                lblOrgName.setForeground(java.awt.Color.WHITE);
                jPanel1.add(lblOrgName, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 26, 820, 30));
                jPanel1.setComponentZOrder(lblOrgName, 0);

                // Classification: 
                javax.swing.JLabel lblCls = new javax.swing.JLabel(cls.toUpperCase());
                lblCls.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.BOLD | java.awt.Font.ITALIC, 11));
                lblCls.setForeground(new java.awt.Color(200, 230, 215));
                lblCls.setOpaque(false);
                lblCls.setBorder(null);
                jPanel1.add(lblCls, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 400, 18));
                jPanel1.setComponentZOrder(lblCls, 0);

                // Description/Objectives: 
                String htmlDesc = "<html><div style='width:760px; font-family:\"Plus Jakarta Sans\","
                    + "sans-serif; font-size:13pt; line-height:1.5; color:white;'>"
                    + desc.replace("\n", "<br>")
                    + "</div></html>";
                javax.swing.JLabel lblDesc = new javax.swing.JLabel(htmlDesc);
                lblDesc.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                lblDesc.setOpaque(false);
                jPanel1.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 820, 175));
                jPanel1.setComponentZOrder(lblDesc, 0);

                // Adviser:
                javax.swing.JLabel lblAdviser = new javax.swing.JLabel("Adviser:  " + adviser);
                lblAdviser.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lblAdviser.setForeground(new java.awt.Color(220, 240, 230));
                lblAdviser.setOpaque(false);
                jPanel1.add(lblAdviser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 288, 700, 22));
                jPanel1.setComponentZOrder(lblAdviser, 0);

                jPanel1.revalidate();
                jPanel1.repaint();
            }

            // Load events using computed status
            //already called in initcomponents

            conn.close();

        } catch (Exception ex) {
            System.out.println("Load org data error: " + ex.getMessage());
            ex.printStackTrace();
        }

        loadOfficerHistory();
        initOfficerYearFilter();
        loadFilteredEvents("All");
    }

    private void addRow(String text, int x, int y, int w) {
        javax.swing.JLabel lbl = new javax.swing.JLabel(text != null ? text : "—");
        lbl.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
        lbl.setForeground(new java.awt.Color(40, 40, 40));
        jPanel1.add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, w, 20));
        jPanel1.setComponentZOrder(lbl, 0);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        possitionLbl1 = new javax.swing.JLabel();
        nameLbl = new javax.swing.JLabel();
        possitionLbl2 = new javax.swing.JLabel();
        termLbl = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        eventsLbl1 = new javax.swing.JLabel();
        eventsLb2 = new javax.swing.JLabel();
        dateLbl = new javax.swing.JLabel();
        locationLbl = new javax.swing.JLabel();
        statusLbl = new javax.swing.JLabel();
        completedBtn = new javax.swing.JButton();
        settingsPanel = new javax.swing.JPanel();
        settings = new javax.swing.JPanel();
        settingsLabel = new javax.swing.JLabel();
        exBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        darkPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchInput1 = new javax.swing.JTextField();
        searchIcon = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JButton();
        aboutBtn = new javax.swing.JButton();
        studOrgBtn = new javax.swing.JButton();
        dashboardBtn = new javax.swing.JButton();
        navbar = new javax.swing.JLabel();
        studOrgScroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dark = new javax.swing.JLabel();
        bsu = new javax.swing.JLabel();
        missionLbl = new javax.swing.JLabel();
        allBtn = new javax.swing.JButton();
        missionArea = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        eventsLbl2 = new javax.swing.JLabel();
        eventsLb3 = new javax.swing.JLabel();
        dateLbl1 = new javax.swing.JLabel();
        locationLbl1 = new javax.swing.JLabel();
        statusLbl1 = new javax.swing.JLabel();
        possitionLbl3 = new javax.swing.JLabel();
        nameLbl1 = new javax.swing.JLabel();
        possitionLbl4 = new javax.swing.JLabel();
        termLbl1 = new javax.swing.JLabel();
        visionLbl = new javax.swing.JLabel();
        visionArea = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        data = new javax.swing.JLabel();
        data1 = new javax.swing.JLabel();
        completedBtn2 = new javax.swing.JButton();
        upcomingBtn = new javax.swing.JButton();
        settingsPanel1 = new javax.swing.JPanel();
        settings1 = new javax.swing.JPanel();
        settingsLabel1 = new javax.swing.JLabel();
        exBtn1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        darkPanel1 = new javax.swing.JPanel();

        possitionLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        possitionLbl1.setForeground(new java.awt.Color(28, 94, 56));
        possitionLbl1.setText("OFFICER POSITIONS");

        nameLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        nameLbl.setForeground(new java.awt.Color(28, 94, 56));
        nameLbl.setText("NAME");

        possitionLbl2.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        possitionLbl2.setForeground(new java.awt.Color(28, 94, 56));
        possitionLbl2.setText("POSITION");

        termLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        termLbl.setForeground(new java.awt.Color(28, 94, 56));
        termLbl.setText("TERM");

        jSeparator2.setForeground(new java.awt.Color(28, 94, 56));

        eventsLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        eventsLbl1.setForeground(new java.awt.Color(28, 94, 56));
        eventsLbl1.setText("EVENTS");

        eventsLb2.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        eventsLb2.setForeground(new java.awt.Color(28, 94, 56));
        eventsLb2.setText("EVENTS");

        dateLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        dateLbl.setForeground(new java.awt.Color(28, 94, 56));
        dateLbl.setText("DATE");

        locationLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        locationLbl.setForeground(new java.awt.Color(28, 94, 56));
        locationLbl.setText("LOCATION");

        statusLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        statusLbl.setForeground(new java.awt.Color(28, 94, 56));
        statusLbl.setText("STATUS");

        completedBtn.setBorder(null);
        completedBtn.setBorderPainted(false);
        completedBtn.setContentAreaFilled(false);
        completedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completedBtnActionPerformed(evt);
            }
        });

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
        settingsPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 210, 60));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/login.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/lohinSettingshover.png"))); // NOI18N
        settingsPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, -1));

        darkPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout darkPanelLayout = new javax.swing.GroupLayout(darkPanel);
        darkPanel.setLayout(darkPanelLayout);
        darkPanelLayout.setHorizontalGroup(
            darkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        darkPanelLayout.setVerticalGroup(
            darkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchInput1.setBackground(new java.awt.Color(0, 0, 0));
        searchInput1.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        searchInput1.setBorder(null);
        searchInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInput1ActionPerformed(evt);
            }
        });
        jPanel3.add(searchInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 290, 30));

        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/searchBar_1.png"))); // NOI18N
        jPanel3.add(searchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 25, -1, -1));

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
        jPanel3.add(settingsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, -1, 50));

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
        jPanel3.add(aboutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, 50));

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
        jPanel3.add(studOrgBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, 50));

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
        jPanel3.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 170, 30));

        navbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/navBar_1.png"))); // NOI18N
        jPanel3.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        studOrgScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        studOrgScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        studOrgScroll.setViewportView(jPanel1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 2300));
        jPanel1.setPreferredSize(new java.awt.Dimension(998, 2300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bar.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        dark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/dark_1.png"))); // NOI18N
        jPanel1.add(dark, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 1020, 550));

        bsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/bulsu.png"))); // NOI18N
        jPanel1.add(bsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1000, 590));

        missionLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        missionLbl.setForeground(new java.awt.Color(28, 94, 56));
        missionLbl.setText("MISSION");
        jPanel1.add(missionLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 610, -1, -1));

        allBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/allBtn.png"))); // NOI18N
        allBtn.setBorder(null);
        allBtn.setBorderPainted(false);
        allBtn.setContentAreaFilled(false);
        allBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/AllHoverSmall.png"))); // NOI18N
        allBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBtnActionPerformed(evt);
            }
        });
        jPanel1.add(allBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 1760, -1, -1));

        missionArea.setEditable(false);
        missionArea.setBackground(new java.awt.Color(255, 255, 255));
        missionArea.setColumns(20);
        missionArea.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 16)); // NOI18N
        missionArea.setForeground(new java.awt.Color(28, 94, 56));
        missionArea.setLineWrap(true);
        missionArea.setRows(5);
        missionArea.setText(" \nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        missionArea.setAutoscrolls(false);
        missionArea.setBorder(null);
        missionArea.setCaretColor(new java.awt.Color(255, 255, 255));
        missionArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        missionArea.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        missionArea.setOpaque(false);
        missionArea.setPreferredSize(new java.awt.Dimension(700, 200));
        jPanel1.add(missionArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 830, 190));

        jSeparator4.setForeground(new java.awt.Color(28, 94, 56));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1710, 1000, 20));

        eventsLbl2.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        eventsLbl2.setForeground(new java.awt.Color(28, 94, 56));
        eventsLbl2.setText("EVENTS");
        jPanel1.add(eventsLbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1760, -1, -1));

        eventsLb3.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        eventsLb3.setForeground(new java.awt.Color(28, 94, 56));
        eventsLb3.setText("EVENTS");
        jPanel1.add(eventsLb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 1830, -1, -1));

        dateLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        dateLbl1.setForeground(new java.awt.Color(28, 94, 56));
        dateLbl1.setText("DATE");
        jPanel1.add(dateLbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1830, -1, -1));

        locationLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        locationLbl1.setForeground(new java.awt.Color(28, 94, 56));
        locationLbl1.setText("LOCATION");
        jPanel1.add(locationLbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 1830, -1, -1));

        statusLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        statusLbl1.setForeground(new java.awt.Color(28, 94, 56));
        statusLbl1.setText("STATUS");
        jPanel1.add(statusLbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 1830, -1, -1));

        possitionLbl3.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        possitionLbl3.setForeground(new java.awt.Color(28, 94, 56));
        possitionLbl3.setText("OFFICER POSITIONS");
        jPanel1.add(possitionLbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1150, -1, -1));

        nameLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        nameLbl1.setForeground(new java.awt.Color(28, 94, 56));
        nameLbl1.setText("NAME");
        jPanel1.add(nameLbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1220, -1, -1));

        possitionLbl4.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        possitionLbl4.setForeground(new java.awt.Color(28, 94, 56));
        possitionLbl4.setText("POSITION");
        jPanel1.add(possitionLbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1220, -1, -1));

        termLbl1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        termLbl1.setForeground(new java.awt.Color(28, 94, 56));
        termLbl1.setText("TERM");
        jPanel1.add(termLbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 1220, -1, -1));

        visionLbl.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        visionLbl.setForeground(new java.awt.Color(28, 94, 56));
        visionLbl.setText("VISION");
        jPanel1.add(visionLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 840, -1, -1));

        visionArea.setEditable(false);
        visionArea.setBackground(new java.awt.Color(255, 255, 255));
        visionArea.setColumns(20);
        visionArea.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 16)); // NOI18N
        visionArea.setForeground(new java.awt.Color(28, 94, 56));
        visionArea.setLineWrap(true);
        visionArea.setRows(5);
        visionArea.setText(" \nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        visionArea.setAutoscrolls(false);
        visionArea.setBorder(null);
        visionArea.setCaretColor(new java.awt.Color(255, 255, 255));
        visionArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        visionArea.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        visionArea.setOpaque(false);
        visionArea.setPreferredSize(new java.awt.Dimension(700, 200));
        jPanel1.add(visionArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 860, 830, 190));

        jSeparator3.setForeground(new java.awt.Color(28, 94, 56));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1090, 1000, 20));

        data.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/data.png"))); // NOI18N
        jPanel1.add(data, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1810, -1, -1));

        data1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/data.png"))); // NOI18N
        jPanel1.add(data1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1200, -1, -1));

        completedBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/completedBtn.png"))); // NOI18N
        completedBtn2.setBorder(null);
        completedBtn2.setBorderPainted(false);
        completedBtn2.setContentAreaFilled(false);
        completedBtn2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/completedHover.png"))); // NOI18N
        completedBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completedBtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(completedBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 1760, -1, -1));

        upcomingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/upcomingBtn.png"))); // NOI18N
        upcomingBtn.setBorder(null);
        upcomingBtn.setBorderPainted(false);
        upcomingBtn.setContentAreaFilled(false);
        upcomingBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/upcomingHover.png"))); // NOI18N
        upcomingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upcomingBtnActionPerformed(evt);
            }
        });
        jPanel1.add(upcomingBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1760, -1, -1));

        studOrgScroll.setViewportView(jPanel1);

        jPanel3.add(studOrgScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 998, 600));

        settingsPanel1.setBackground(new java.awt.Color(255, 255, 255));
        settingsPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settings1.setBackground(new java.awt.Color(28, 94, 56));

        settingsLabel1.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 18)); // NOI18N
        settingsLabel1.setForeground(new java.awt.Color(255, 255, 255));
        settingsLabel1.setText("SETTINGS");

        exBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/exit_1.png"))); // NOI18N
        exBtn1.setBorder(null);
        exBtn1.setBorderPainted(false);
        exBtn1.setContentAreaFilled(false);
        exBtn1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/exitHover_1.png"))); // NOI18N
        exBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settings1Layout = new javax.swing.GroupLayout(settings1);
        settings1.setLayout(settings1Layout);
        settings1Layout.setHorizontalGroup(
            settings1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settings1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(settingsLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(exBtn1)
                .addGap(17, 17, 17))
        );
        settings1Layout.setVerticalGroup(
            settings1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settings1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(settings1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settings1Layout.createSequentialGroup()
                        .addComponent(exBtn1)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settings1Layout.createSequentialGroup()
                        .addComponent(settingsLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );

        settingsPanel1.add(settings1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 250, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/regOrg.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/regOrgHover.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        settingsPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 210, 60));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/login.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/bond/assets/UserImages/lohinSettingshover.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        settingsPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, -1));

        jPanel3.add(settingsPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 210, 600));

        darkPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout darkPanel1Layout = new javax.swing.GroupLayout(darkPanel1);
        darkPanel1.setLayout(darkPanel1Layout);
        darkPanel1Layout.setHorizontalGroup(
            darkPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        darkPanel1Layout.setVerticalGroup(
            darkPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jPanel3.add(darkPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 600));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInput1ActionPerformed
        // TODO add your handling code here:
        searchInput1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { doSearch(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { doSearch(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { doSearch(); }
            private void doSearch() {
                // TODO: filter displayed org list by searchInput1.getText()
                // For now at minimum: System.out.println("search: " + searchInput1.getText());
            }
        });
        
    }//GEN-LAST:event_searchInput1ActionPerformed

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        darkPanel1.setVisible(true);
        settingsPanel1.setVisible(true);
        settingsPanel1.setLocation(750, 0);
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void aboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutBtnActionPerformed
        // TODO add your handling code here:
        new about().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutBtnActionPerformed

    private void studOrgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studOrgBtnActionPerformed
        new studOrg().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_studOrgBtnActionPerformed

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        new dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void completedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completedBtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_completedBtnActionPerformed

    private void allBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnActionPerformed
        // TODO add your handling code here:
        loadFilteredEvents("All");
        
    }//GEN-LAST:event_allBtnActionPerformed

    private void completedBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completedBtn2ActionPerformed
        // TODO add your handling code here:
        loadFilteredEvents("Completed");
    }//GEN-LAST:event_completedBtn2ActionPerformed

    private void upcomingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upcomingBtnActionPerformed
        // TODO add your handling code here:
        loadFilteredEvents("Upcoming");
    }//GEN-LAST:event_upcomingBtnActionPerformed

    private void exBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exBtnActionPerformed

        darkPanel.setVisible(false);
        settingsPanel.setVisible(false);
    }//GEN-LAST:event_exBtnActionPerformed

    private void exBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exBtn1ActionPerformed

        darkPanel1.setVisible(false);
        settingsPanel1.setVisible(false);
    }//GEN-LAST:event_exBtn1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new registerAnOrg().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new loginChoices().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(studOrgClicked.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studOrgClicked.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studOrgClicked.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studOrgClicked.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studOrgClicked().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutBtn;
    private javax.swing.JButton allBtn;
    private javax.swing.JLabel bsu;
    private javax.swing.JButton completedBtn;
    private javax.swing.JButton completedBtn2;
    private javax.swing.JLabel dark;
    private javax.swing.JPanel darkPanel;
    private javax.swing.JPanel darkPanel1;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JLabel data;
    private javax.swing.JLabel data1;
    private javax.swing.JLabel dateLbl;
    private javax.swing.JLabel dateLbl1;
    private javax.swing.JLabel eventsLb2;
    private javax.swing.JLabel eventsLb3;
    private javax.swing.JLabel eventsLbl1;
    private javax.swing.JLabel eventsLbl2;
    private javax.swing.JButton exBtn;
    private javax.swing.JButton exBtn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel locationLbl;
    private javax.swing.JLabel locationLbl1;
    private javax.swing.JTextArea missionArea;
    private javax.swing.JLabel missionLbl;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JLabel nameLbl1;
    private javax.swing.JLabel navbar;
    private javax.swing.JLabel possitionLbl1;
    private javax.swing.JLabel possitionLbl2;
    private javax.swing.JLabel possitionLbl3;
    private javax.swing.JLabel possitionLbl4;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JTextField searchInput1;
    private javax.swing.JPanel settings;
    private javax.swing.JPanel settings1;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JLabel settingsLabel1;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel settingsPanel1;
    private javax.swing.JLabel statusLbl;
    private javax.swing.JLabel statusLbl1;
    private javax.swing.JButton studOrgBtn;
    private javax.swing.JScrollPane studOrgScroll;
    private javax.swing.JLabel termLbl;
    private javax.swing.JLabel termLbl1;
    private javax.swing.JButton upcomingBtn;
    private javax.swing.JTextArea visionArea;
    private javax.swing.JLabel visionLbl;
    // End of variables declaration//GEN-END:variables
}
