/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ADMIN
 */
public class GameObject {
    public BufferedImage image;
    public BufferedImage nightImage;
    public int worldX, worldY;
    public boolean collision = false;
    public Rectangle solidArea;
    public boolean collected = false; // added: para matrack kung nakuha na

    protected void generateNightImage() {
        if (image == null) return;

        int w = image.getWidth();
        int h = image.getHeight();

        nightImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = nightImage.createGraphics();

        g2.drawImage(image, 0, 0, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.6f));
        g2.setColor(new Color(0, 0, 30));
        g2.fillRect(0, 0, w, h);

        g2.dispose();
    }
}
