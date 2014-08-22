package com.ludumdare.codebase.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ludumdare.codebase.Ludumdare30Main;

public class DesktopLauncher
{
    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.resizable = true;
        config.useGL30 = true;

        config.width = 800;
        config.height = 600;

        new LwjglApplication(new Ludumdare30Main(), config);
    }
}
