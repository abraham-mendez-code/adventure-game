package main;

import entity.Entity;

public class CollisionManager {

    // class attributes
    GamePanel gp;

    // constructor
    public CollisionManager(GamePanel gp) {

        this.gp = gp;
    }

    // this method checks for Entity collision
    public void checkTile(Entity entity) {

        // Get the position for the entity collision rectangle
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // get the row and col position for the entity
        int entityLeftCol = entityLeftWorldX / gp.TILE_SIZE;
        int entityRightCol = entityRightWorldX / gp.TILE_SIZE;
        int entityTopRow = entityTopWorldY / gp.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / gp.TILE_SIZE;

        int tileNum1, tileNum2; // used to store tile information based on movement direction

        switch(entity.direction) {
            case "up":
                // check what tile the player is stepping into
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE; // find what tile the player is stepping into
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol] [entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol] [entityTopRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                // check what tile the player is stepping into
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE; // find what tile the player is stepping into
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol] [entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol] [entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                // check what tile the player is stepping into
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE; // find what tile the player is stepping into
                tileNum1 = gp.tileManager.mapTileNumber[entityLeftCol] [entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityLeftCol] [entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                // check what tile the player is stepping into
                entityRightCol = (entityRightWorldX + entity.speed) / gp.TILE_SIZE; // find what tile the player is stepping into
                tileNum1 = gp.tileManager.mapTileNumber[entityRightCol] [entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[entityRightCol] [entityBottomRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
