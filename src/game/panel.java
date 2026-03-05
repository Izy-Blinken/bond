package game;

import java.awt.BasicStroke;
import models.Monster;
import models.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class panel extends JPanel implements Runnable {

    final int origtileSize = 16;
    public final int charHeight = origtileSize * 2;
    public final int charWidth = origtileSize;
    final int scale = 3;

    public final int tileSize = origtileSize * scale;
    public final int screenWidth = 960;
    public final int screenheight = 540;
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 45;
    private boolean monsterDialogueResponded = false;
    public boolean playerHealedThisNight = false;

    KeyHandler keyH = new KeyHandler();
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player  player = new Player(this, keyH);
    public ObjectManager objectM = new ObjectManager(this);
    public Monster monster = new Monster(this);
    public dayCounter dC = new dayCounter(this);
    public Inventory inventory = new Inventory(this);
    public boolean isGameOver = false;
    private boolean showMonsterDialogue = false;
    private String dialogueText = "";
    private Runnable onYesAction = null;
    private Runnable onNoAction = null;
    
    public InteractionChecker interactionChecker = new InteractionChecker(this);
    Thread GameThread;

    int fps = 60;

    public panel() {
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        handleDialogueClick(e.getX(), e.getY());
    }
        });
        
        this.setPreferredSize(new Dimension(screenWidth, screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void startThread() {
        
        GameThread = new Thread(this);
        GameThread.start();
    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (GameThread != null) {
            
            update();
            repaint();
            
            try {
                
                double remaining = nextDrawTime - System.nanoTime();
                remaining /= 1000000;
                if (remaining < 0) remaining = 0;
                Thread.sleep((long) remaining);
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException ex) { 
                ex.printStackTrace(); 
            }
        }
    }

    private boolean wasNight = false;

    public void update() {

    if (isGameOver) return;

    if (showMonsterDialogue) return;

    // Determine if it's night
    boolean isNight = (dC.currentState == dayCounter.dayNightState.Night
                    || dC.currentState == dayCounter.dayNightState.NightTitle
                    || dC.currentState == dayCounter.dayNightState.Sunset
                    || dC.currentState == dayCounter.dayNightState.Sunrise);

    // Spawn monster at the start of night
    if (isNight && !wasNight) {
        monster.spawnNearEdge();
    }

    // Update all entities
    player.update();
    monster.update();
    objectM.update();

    // Check interactions
    if (tileM.currentMap == 1) {
        interactionChecker.checkInteraction();
    } else {
        interactionChecker.checkInteriorInteraction();
    }

    // Monster dialogue
    if (monster.state == Monster.State.KNOCKING && !showMonsterDialogue && !monsterDialogueResponded && monster.actionTimer > 0) {
        showMonsterDialogue = true;
        dialogueText = "Don't choose 'yes', if you don't want me to come!";

        onYesAction = () -> {
            player.takeDamage(25);
            showMonsterDialogue = false;
            monsterDialogueResponded = true;
        };

        onNoAction = () -> {
            showMonsterDialogue = false;
            monsterDialogueResponded = true;
        };
    }

    if (!isNight || monster.state != Monster.State.KNOCKING) {
        monsterDialogueResponded = false;
    }

    // Update day/night counter
    dC.update();

    // Check for general game over conditions
    if (dC.dayCount > 5 || player.hp <= 0) {
        isGameOver = true;
    }

    // NIGHT-END: If night just ended and player didn’t heal
    if (wasNight && !isNight) {
        if (!playerHealedThisNight) {
            isGameOver = true;
            System.out.println("Game Over! You didn't heal during the night.");
        }
        playerHealedThisNight = false; // reset for next night
    }

    // Update wasNight at the very end
    wasNight = isNight;
}

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        objectM.draw(g2);
        if (tileM.currentMap == 1) { monster.draw(g2); }
        player.draw(g2);
        dC.draw(g2);
        dC.drawOverlay(g2);
        monster.drawAlert(g2);
        inventory.draw(g2);
        
        drawHUD(g2);
        drawFixedHPBar(g2);
        
        if (isGameOver) {
        g2.setFont(new Font("Arial", Font.BOLD, 60));
        g2.setColor(Color.RED);
        g2.drawString("GAME OVER", screenWidth / 2 - 180, screenheight / 2);
    }
        
        g2.dispose();
    }
    
    private void handleDialogueClick(int mx, int my) {

    if (!showMonsterDialogue) return;

    int w = 400;
    int h = 120;
    int x = screenWidth/2 - w/2;
    int y = screenheight/2 - h/2;

    // YES button area
    int yesX = x + 60;
    int yesY = y + 70;
    int yesW = 100;
    int yesH = 30;

    // NO button area
    int noX = x + 240;
    int noY = y + 70;
    int noW = 100;
    int noH = 30;

    // YES clicked
    if (mx >= yesX && mx <= yesX + yesW &&
        my >= yesY && my <= yesY + yesH) {

        if (onYesAction != null) {
            onYesAction.run();
        }
    }

    // NO clicked
    if (mx >= noX && mx <= noX + noW &&
        my >= noY && my <= noY + noH) {

        if (onNoAction != null) {
            onNoAction.run();
        }
    }
}
    
    private void drawPlayerHP(Graphics2D g2) {

    int barWidth = 200;
    int barHeight = 20;
    int x = 20;
    int y = 20;

    // Background (empty bar)
    g2.setColor(Color.DARK_GRAY);
    g2.fillRect(x, y, barWidth, barHeight);

    // Health ratio
    double hpPercent = (double) player.hp / player.maxHP;
    int currentWidth = (int)(barWidth * hpPercent);

    // Change color based on HP
    if (player.hp > 60) {
        g2.setColor(Color.GREEN);
    } else if (player.hp > 30) {
        g2.setColor(Color.ORANGE);
    } else {
        g2.setColor(Color.RED);
    }

    g2.fillRect(x, y, currentWidth, barHeight);

    // Border
    g2.setColor(Color.BLACK);
    g2.drawRect(x, y, barWidth, barHeight);

    // HP Text
    g2.setColor(Color.WHITE);
    g2.drawString(player.hp + " % ", x + 70, y + 15);
}
        
    private void drawFixedHPBar(Graphics2D g2) {

    int barWidth = 250;
    int barHeight = 25;

    int x = 650;
    int y = 20;

    // Background
    g2.setColor(Color.DARK_GRAY);
    g2.fillRoundRect(x, y, barWidth, barHeight, 15, 15);

    // HP percentage
    double hpPercent = (double) player.hp / player.maxHP;
    int currentWidth = (int)(barWidth * hpPercent);

    // Dynamic color
    if (player.hp > 60) {
        g2.setColor(new Color(40, 200, 40));
    } else if (player.hp > 30) {
        g2.setColor(new Color(255, 170, 0));
    } else {
        g2.setColor(new Color(200, 30, 30));
    }

    g2.fillRoundRect(x, y, currentWidth, barHeight, 15, 15);

    // Border
    g2.setColor(Color.BLACK);
    g2.drawRoundRect(x, y, barWidth, barHeight, 15, 15);

    // Text
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 16));
    String text = player.hp + " % ";

    int textWidth = g2.getFontMetrics().stringWidth(text);
    int textX = x + barWidth / 2 - textWidth / 2;
    int textY = y + 18;

    g2.drawString(text, textX, textY);
    }

    private void drawHUD(Graphics2D g2) {
        
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.setColor(Color.WHITE);

        if (tileM.currentMap == 1) {
            
            if (interactionChecker.showDoorPrompt) {
                
                models.ObjHouse house = (models.ObjHouse) objectM.ObjHouse[0];
                String prompt = house.isDoorOpen ? "E - Enter | F - Close Door" : "E - Open Door";
                g2.drawString(prompt, screenWidth / 2 - 120, screenheight - 60);
            }
            
            return;
        }

        // Exit door
        if (interactionChecker.showExitPrompt) {
            
            models.ObjHouse house = (models.ObjHouse) objectM.ObjHouse[0];
            String prompt = house.isDoorOpen ? "E - Exit | F - Close Door" : "F - Open Door";
            g2.drawString(prompt, screenWidth / 2 - 120, screenheight - 60);
        }

        // Window
        if (interactionChecker.showWindowPrompt) {
            
            g2.drawString("E - Open/Close Window", screenWidth / 2 - 120, screenheight - 60);
        }

        // Torch
        if (interactionChecker.showTorchPrompt) {
            
            models.ObjTorch torch = objectM.interior.torch;
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.setColor(torch.isLit ? new Color(255, 200, 50) : new Color(160, 160, 160));
            g2.drawString(torch.isLit ? "Torch burning — " + torch.getSecondsLeft() + "s left" : "Torch is OUT", screenWidth / 2 - 130, screenheight - 90);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString("E - Fuel Torch  (costs 3 wood)", screenWidth / 2 - 160, screenheight - 60);
        }

        if (interactionChecker.hasTorchFeedback()) {
            
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.setColor(new Color(255, 210, 60));
            g2.drawString(interactionChecker.torchFeedback, screenWidth / 2 - 160, screenheight - 120);
        }

        // Cabinet (wood)
        if (interactionChecker.showCabinetPrompt && !objectM.interior.cabinet.isOpen) {
            
            
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.setColor(Color.WHITE);
            g2.drawString("P - Open Cabinet", screenWidth / 2 - 130, screenheight - 60);
        }

        if (interactionChecker.hasCabinetFeedback()) {
            
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.setColor(new Color(200, 160, 80));
            g2.drawString(interactionChecker.cabinetFeedback, screenWidth / 2 - 150, screenheight - 120);
        }

        // Apple table
        if (interactionChecker.showAppleTablePrompt && !objectM.interior.appleTable.isOpen) {
            
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.setColor(Color.WHITE);
            g2.drawString("E - Open Table (Apple)", screenWidth / 2 - 120, screenheight - 60);
        }

        if (interactionChecker.hasAppleTableFeedback()) {
            
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.setColor(new Color(130, 210, 100));
            g2.drawString(interactionChecker.appleTableFeedback, screenWidth / 2 - 150, screenheight - 120);
        }

        // Extra darkness + warning when torch is out at night
        boolean isNight = dC.currentState == dayCounter.dayNightState.Night
                       || dC.currentState == dayCounter.dayNightState.Sunset
                       || dC.currentState == dayCounter.dayNightState.Sunrise;

        if (isNight && !objectM.interior.torch.isLit) {
            
            g2.setColor(new Color(0, 0, 8, 130));
            g2.fillRect(0, 0, screenWidth, screenheight);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.setColor(new Color(255, 60, 60, 210));
            g2.drawString("Torch is out!", screenWidth / 2 - 260, screenheight / 2);
        }
        
        if (showMonsterDialogue) {
            int w = 400;
            int h = 120;
            int x = screenWidth/2 - w/2;
            int y = screenheight/2 - h/2;
            
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRoundRect(x, y, w, h, 20, 20);
            
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, w, h, 20, 20);

            g2.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
            g2.setColor(Color.WHITE);
            int tx = x + 20;
            int ty = y + 40;
            g2.drawString(dialogueText, tx, ty);
            
            g2.setColor(new Color(50,150,50));
            g2.fillRoundRect(x + 60, y + 70, 100, 30, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawString("YES", x + 95, y + 90);
            
            g2.setColor(new Color(150,50,50));
            g2.fillRoundRect(x + 240, y + 70, 100, 30, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawString("NO", x + 285, y + 90);
        }
    }

    public void switchToInterior() {
        
        tileM.switchMap(2);
        player.worldX = 160;
        player.worldY = 144;
    }

    public void switchToExterior() {
        
        tileM.switchMap(1);
        player.worldX = 1455;
        player.worldY = 1056;
    }

    public boolean isPlayerSafe() {
        
        if (tileM.currentMap != 2) {
            return false;
        }
        
        models.ObjHouse house = (models.ObjHouse) objectM.ObjHouse[0];
        return !house.isDoorOpen && !house.isWindow1Open && !house.isWindow2Open && !house.isWindow3Open;
    }
}
