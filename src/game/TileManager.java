/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author ADMIN
 */
public class TileManager {
    panel gp;
    Tiles[] tile;
    Tiles[] nightTile; // add lp para ma upload yung night tilr
    int mapTileNum[][];
    
    public TileManager(panel gp){
        this.gp = gp;
        tile = new Tiles[15];
        nightTile = new Tiles[15]; // added
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
        
    }
    
    public BufferedImage getRotatedImage(BufferedImage original, int degrees) {
        int w = original.getWidth();
        int h = original.getHeight();
        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.toRadians(degrees), w / 2.0, h / 2.0);
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();
        return rotated;
    }
    
    public void getTileImage(){
        try{
            BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/assets/no_sanctuary_map/MAP TILES.png"));
            tile[0] = new Tiles(sheet.getSubimage(732, 87, 64, 63), false); // solid grass
            tile[1] = new Tiles(sheet.getSubimage(735, 157, 58, 51), false); // grass w/ flowers
           /* tile[2] = new Tiles(sheet.getSubimage(619, 11, 59, 60), false); // straight road w/ grass - right side
            tile[3] = new Tiles(getRotatedImage(sheet.getSubimage(619, 11, 59, 60), 180), false); // straight road w/ grass - left side (naka-rotate lang gamit yung method sa taas)
            tile[4] = new Tiles(getRotatedImage(sheet.getSubimage(619, 11, 59, 60), 90), false); // straight road w/ grass - up side (naka-rotate lang gamit yung method sa taas)
            tile[5] = new Tiles(getRotatedImage(sheet.getSubimage(619, 11, 59, 60), 270), false); // straight road w/ grass - down side (naka-rotate lang gamit yung method sa taas)
            comment muna kasi parang mas okay na walang road hehe */

            
            BufferedImage nightSheet = ImageIO.read(getClass().getResourceAsStream("/assets/no_sanctuary_map/MAP_TILES_NIGHT.png"));
            nightTile[0] = new Tiles(nightSheet.getSubimage(732, 87, 64, 63), false); // solid grass - night
            nightTile[1] = new Tiles(nightSheet.getSubimage(735, 157, 58, 51), false); // grass w/ flowers - night
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/assets/no_sanctuary_map/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        
        boolean isNight = gp.dC.currentState == dayCounter.dayNightState.Night
                       || gp.dC.currentState == dayCounter.dayNightState.Sunset
                       || gp.dC.currentState == dayCounter.dayNightState.Sunrise;
        Tiles[] currentTile = isNight ? nightTile : tile;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            

            g2.drawImage(currentTile[tileNum].image, screenX, screenY, gp.tileSize + 4, gp.tileSize + 4 , null);

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    
}
