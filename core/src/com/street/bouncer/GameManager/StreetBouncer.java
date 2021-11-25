package com.street.bouncer.GameManager;

import com.badlogic.gdx.Game;
import com.street.bouncer.Screens.GameScreen;
import com.street.bouncer.Screens.MainMenuScreen;

public class StreetBouncer extends Game {

	public int bouncerHealth = 5;
	public final int bouncerMinHealth = 1;
	public int gamePoint = 0;

	@Override
	public void create() {
		setScreen(new MainMenuScreen(this));
	}
}
