package com.mygdx.game.states;

public class StatePattern {
    public static void main(String[] args)
    {
        GameStateManager stateContext = new GameStateManager();
        stateContext.alert();
        stateContext.alert();
        stateContext.set(new MenuState(stateContext));
        stateContext.alert();
        stateContext.alert();
        stateContext.alert();
    }
}
