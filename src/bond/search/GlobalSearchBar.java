/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 *
 * @author Erica
 */
public class GlobalSearchBar {
    
    private final JFrame owner;
    private final JTextField field;
    private final JWindow overlay;
    private final JPanel resultsPanel;
    private final java.util.function.Consumer<GlobalSearchRegistry.SearchResult> onNavigate;

    public GlobalSearchBar(JFrame owner,
            java.util.function.Consumer<GlobalSearchRegistry.SearchResult> onNavigate,
            Runnable onFocusGained,
            Runnable onFocusLost) {
        this.owner = owner;
        this.onNavigate = onNavigate;
        
        field = new JTextField();
        field.setBounds(245, 25, 375, 40);
        field.setOpaque(false);
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setForeground(new Color(180, 180, 180));
        field.setCaretColor(new Color(0, 0, 0, 0));
        field.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 13));
        field.setText("Search...");

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals("Search...")) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            field.setCaretColor(Color.WHITE); 
            field.getCaret().setVisible(true);
            if (onFocusGained != null) onFocusGained.run();
        }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isBlank()) {
                    field.setText("Search...");
                    field.setForeground(new Color(180, 180, 180));
                }
                field.setCaretColor(new Color(0, 0, 0, 0)); 
                field.getCaret().setVisible(false);         
                if (onFocusLost != null) onFocusLost.run();
                Timer t = new Timer(200, x -> hideOverlay());
                t.setRepeats(false);
                t.start();
            }
        });

        overlay = new JWindow(owner);
        overlay.setAlwaysOnTop(true);
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(Color.WHITE);
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(4, 0, 4, 0)));

        JScrollPane scroll = new JScrollPane(resultsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        overlay.add(scroll);

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    hideOverlay();
                    return;
                }
                String query = field.getText();
                if (query.isBlank() || query.equals("Search...")) {
                    hideOverlay();
                    return;
                }
                showResults(GlobalSearchRegistry.getInstance().search(query));
            }
        });
    }

    public void installInto(Container pane) {
        pane.add(field);
        pane.setComponentZOrder(field, 0);
        SwingUtilities.invokeLater(() -> pane.requestFocusInWindow());
    }

    public JTextField getField() { return field; }

    private void showResults(List<GlobalSearchRegistry.SearchResult> results) {
        resultsPanel.removeAll();

        if (results.isEmpty()) {
            JLabel none = new JLabel("  No results");
            none.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 12));
            none.setForeground(Color.GRAY);
            none.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
            resultsPanel.add(none);
        } else {
            GlobalSearchRegistry.SearchResult.Type lastType = null;
            for (GlobalSearchRegistry.SearchResult r : results) {
                if (r.type != lastType) {
                    resultsPanel.add(makeSectionHeader(r.type.name()));
                    lastType = r.type;
                }
                resultsPanel.add(makeResultRow(r));
            }
        }

        Point loc = field.getLocationOnScreen();
        overlay.setBounds(loc.x - 25, loc.y + field.getHeight() + 2,
                field.getWidth(), Math.min(results.size() * 46 + 32, 300));
        overlay.setVisible(true);
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private void hideOverlay() {
        overlay.setVisible(false);
    }

    private JPanel makeSectionHeader(String text) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(240, 240, 240));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        JLabel lbl = new JLabel("  " + text);
        lbl.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 10));
        lbl.setForeground(new Color(100, 100, 100));
        p.add(lbl, BorderLayout.CENTER);
        return p;
    }

    private JPanel makeResultRow(GlobalSearchRegistry.SearchResult r) {
        JPanel row = new JPanel(new BorderLayout(0, 2));
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(235, 235, 235)),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel title = new JLabel(r.title);
        title.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 13));
        title.setForeground(Color.BLACK);

        JLabel sub = new JLabel(r.subtitle);
        sub.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 11));
        sub.setForeground(Color.GRAY);

        row.add(title, BorderLayout.NORTH);
        row.add(sub, BorderLayout.SOUTH);

        row.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                row.setBackground(new Color(245, 247, 255));
                title.setBackground(new Color(245, 247, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                row.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                hideOverlay();
                field.setText("");
                field.setForeground(new Color(180, 180, 180));
                field.setCaretColor(new Color(0, 0, 0, 0));
                field.setText("Search...");
                onNavigate.accept(r);
            }
        });

        return row;
    }
}
