package game;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class EdpGame {
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("No Sanctuary");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            
            LandingPage landingPage = new LandingPage(() -> {
                
                frame.getContentPane().removeAll();
                
                panel gamePanel = new panel(frame);
                frame.add(gamePanel);
                
                frame.pack();
                frame.revalidate();
                frame.repaint();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                gamePanel.showNarration = true;
                gamePanel.narrationAlpha = 0f;
                gamePanel.narrationComplete = true;
                
                gamePanel.startThread();
                gamePanel.requestFocusInWindow();
            });
            frame.add(landingPage);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        
    }
}