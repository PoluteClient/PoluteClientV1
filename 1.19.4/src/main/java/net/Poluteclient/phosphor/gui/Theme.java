package net.Poluteclient.phosphor.gui;

public interface Theme {
    default void preRender() {
        // do nothing
    }

    default void postRender() {
        // do nothing
    }
}
