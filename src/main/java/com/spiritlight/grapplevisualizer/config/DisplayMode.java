package com.spiritlight.grapplevisualizer.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public enum DisplayMode {

    DISABLED(-255, -255),
    CENTER(0, 0, true),
    LEFT_CORNER(1, 1),
    ;
    private static final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    private final int x;
    private final int y;
    private final boolean center;

    DisplayMode(int x, int y) {
        this.x = x;
        this.y = y;
        this.center = false;
    }

    DisplayMode(int offsetX, int offsetY, boolean center) {
        this.x = offsetX;
        this.y = offsetY;
        this.center = center;
    }

    public int getX() {
        return this.center ? (sr.getScaledWidth() + x) / 2 : x;
    }

    public int getY() {
        return this.center ? (sr.getScaledHeight() + y) / 2 : y;
    }

    @Override
    public String toString() {
        switch(this) {
            case DISABLED:
                return "disabled";
            case CENTER:
                return "center";
            case LEFT_CORNER:
                return "left_corner";
        }
        return "";
    }

    public static DisplayMode fromString(String content) {
        for(DisplayMode mode : values()) {
            if(mode.toString().equalsIgnoreCase(content)) return mode;
        }
        return null;
    }
}
