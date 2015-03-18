package com.example.kamil.controller;

import android.os.AsyncTask;

import com.example.kamil.model.Game;
import com.example.kamil.model.Result;
import com.example.kamil.setgame.MainActivity;

/**
 * Created by kamil on 15.03.15.
 */
public class Controller {

    private static final int MIN_NUMBER_OF_SETS = 6;

    private static Controller controller = new Controller();

    private static MainActivity mainActivity;

    public static Controller getInstance(MainActivity mainActivity) {
        Controller.mainActivity = mainActivity;
        mainActivity.refreshCards(game);
        mainActivity.refreshLabels(game);
        return controller;
    }

    private static Game game;

    private Controller() {
        game = new Game();
    }

    private class LoadGame extends AsyncTask<Void, Void, Game> {

        @Override
        protected void onPreExecute() {
            mainActivity.showLoadingProgress();
        }

        @Override
        protected Game doInBackground(Void... params) {
            Game game = new Game();
            while (game.getLeft() < MIN_NUMBER_OF_SETS)
                game = new Game();
            return game;
        }

        @Override
        protected void onPostExecute(Game result) {
            game = result;
            mainActivity.refreshCards(game);
            mainActivity.refreshLabels(game);
            mainActivity.unselectAll();
            mainActivity.hideLoadingProgress();
        }

    }

    public void newGame() {

        LoadGame loader = new LoadGame();
        loader.execute();

    }

    public Game getGame() {
        return game;
    }

    public void check(int[] cardIndexes) {
        Result result = game.checkSelected(cardIndexes);
        switch (result) {
            case GOOD:
                mainActivity.toastGood();
                break;
            case SELECTED:
                mainActivity.toastAlreadySelected();
                break;
            case BAD:
                mainActivity.toastBad();
        }
        mainActivity.refreshLabels(game);
        mainActivity.unselectAll();
        if (game.getLeft() == 0) {
            mainActivity.disableButtons();
            mainActivity.winAlert();
        }
    }

}
