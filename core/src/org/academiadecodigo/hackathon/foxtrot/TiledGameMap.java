package org.academiadecodigo.hackathon.foxtrot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private boolean firstMap = true;

    public TiledGameMap() {
        loadMap(1);

    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;


        if(getPlayer().getX() > 2600 && firstMap){

            getPlayer().setCanMove(false);
            tiledMap = null;
            System.out.println("hello world");
            loadMap(2);
            camera.position.x = 500;

            camera.update();
            getPlayer().setX(830);
            getPlayer().setY(352);
            firstMap = false;



            getPlayer().setCanMove(true);
        }


        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        if (camera.position.x < 2308) {
            camera.translate(2, 0);
            camera.update();
        }




        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.render(camera, batch);
        batch.end();
    }

    @Override
    public void update(float delta) {

        super.update(delta);
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {

        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);

        if (cell != null) {
            TiledMapTile tile = cell.getTile();


            if (tile != null) {

                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
        }

        return null;
    }

    public void loadMap(int map) {

        tiledMap = new TmxMapLoader().load("map"  + map + ".tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);


    }

    @Override
    public int getWidth() {


        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }
}
