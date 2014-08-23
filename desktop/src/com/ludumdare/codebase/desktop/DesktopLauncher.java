package com.ludumdare.codebase.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ludumdare.codebase.Ludumdare30Main;

public class DesktopLauncher
{
    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Ludum Dare 30";
        config.width = 960; // 960
        config.height = 540; // 540
        config.resizable = true;
        config.samples = 4;

        new LwjglApplication(new Ludumdare30Main(), config);
    }
}
