package org.academiadecodigo.hackathon.foxtrot.menu;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.util.HashMap;
import java.util.Map;

public class InnerMenus extends Game {

    private final LwjglApplicationConfiguration config;
    private Map<ScreenTypes, MenuScreen> screenMap;

    public InnerMenus(LwjglApplicationConfiguration config) {
        this.config = config;

    }

    @Override
    public void create() {

        screenMap = new HashMap<ScreenTypes, MenuScreen>();
        MenuScreen screen = new Menu(this);
        MenuScreen screen2 = new InstructionScreen(this);
        MenuScreen game = new org.academiadecodigo.hackathon.foxtrot.Game(this);


        screen.init();
        screen2.init();

        screenMap.put(ScreenTypes.MENU,screen);
        screenMap.put(ScreenTypes.INSTRUCTION,screen2);
        screenMap.put(ScreenTypes.GAME, game);
        super.setScreen(screen);
    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        for (MenuScreen menuScreen : screenMap.values()) {
            menuScreen.dispose();
        }
    }

    public void setScreen(ScreenTypes screen) {
        super.setScreen(screenMap.get(screen));
    }

}
