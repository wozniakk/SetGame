package com.example.kamil.setgame;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.controller.Controller;
import com.example.kamil.model.Game;

public class MainActivity extends ActionBarActivity {

    private final int NUMBER_OF_BUTTONS = 12;
    private final String BUTTONS_STATE = "BUTTONS_STATE";
    private ImageButton[] imageButtons = new ImageButton[NUMBER_OF_BUTTONS];
    private TextView labelGood;
    private TextView labelLeft;
    private TextView labelBad;
    private Controller controller;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < NUMBER_OF_BUTTONS; ++i) {
            imageButtons[i] = (ImageButton) findViewById(getResources().getIdentifier("imageButton" + i, "id", getPackageName()));
            imageButtons[i].setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.isSelected())
                        v.setSelected(false);
                    else if (countSelected() < 3)
                        v.setSelected(true);
//
                    if (countSelected() == 3) {
                        controller.check(findSelected());
                    }
                }
            });
        }
        if (savedInstanceState != null) {
            boolean[] selectedButtons = savedInstanceState.getBooleanArray(BUTTONS_STATE);
            for (int i = 0; i < NUMBER_OF_BUTTONS; ++i)
                imageButtons[i].setSelected(selectedButtons[i]);
        }
        labelGood = (TextView) findViewById(R.id.labelGood);
        labelLeft = (TextView) findViewById(R.id.labelLeft);
        labelBad = (TextView) findViewById(R.id.labelBad);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.progress_title);
        progressDialog.setMessage(getResources().getString(R.string.progress_text));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        controller = Controller.getInstance(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        boolean[] selectedButtons = new boolean[NUMBER_OF_BUTTONS];
        for (int i = 0; i < NUMBER_OF_BUTTONS; ++i)
            selectedButtons[i] = imageButtons[i].isSelected();
        savedInstanceState.putBooleanArray(BUTTONS_STATE, selectedButtons);
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGame(MenuItem menuItem) {
        controller.newGame();
    }

    public void showRules(MenuItem menuItem) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_rules)));
        startActivity(intent);

    }

    public void aboutAlert(MenuItem menuItem) {

        new AlertDialog.Builder(this)
                .setTitle(R.string.about_alert_title)
                .setMessage(R.string.about_alert_text)
                .setPositiveButton(R.string.about_alert_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

    public void showLoadingProgress() {

        progressDialog.show();

    }

    public void hideLoadingProgress() {

        progressDialog.dismiss();

    }

    public void unselectAll() {
        for (ImageButton imageButton : imageButtons)
            imageButton.setSelected(false);
    }

    public void refreshCards(Game game) {

        for (int i = 0; i < NUMBER_OF_BUTTONS; ++i)
            imageButtons[i].setImageResource(getResources().getIdentifier(game.getCardSet().get(i).getResourceName(), "drawable", getPackageName()));

    }

    public void refreshLabels(Game game) {

        labelBad.setText(String.valueOf(game.getBad()));
        labelLeft.setText(String.valueOf(game.getLeft()));
        labelGood.setText(String.valueOf(game.getGood()));

    }

    private int countSelected() {
        int counter = 0;
        for (ImageButton imageButton : imageButtons)
            if (imageButton.isSelected())
                counter++;
        return counter;
    }

    private int[] findSelected() {

        int[] selectedIndexes = new int[3];
        int foundedCounter = 0;
        for (int i = 0; i < NUMBER_OF_BUTTONS; ++i)
            if (imageButtons[i].isSelected()) {
                selectedIndexes[foundedCounter] = i;
                foundedCounter++;
                if (foundedCounter == 3) break;
            }
        return selectedIndexes;

    }

    public void toastGood() {
        Toast toast = Toast.makeText(this, R.string.toast_good, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void toastBad() {
        Toast toast = Toast.makeText(this, R.string.toast_bad, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void toastAlreadySelected() {
        Toast toast = Toast.makeText(this, R.string.toast_already_selected, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void winAlert() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.win_title)
                .setMessage(R.string.win_text)
                .setPositiveButton(R.string.win_new_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.newGame();
                    }
                })
                .setNeutralButton(R.string.win_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

    public void enableButtons() {
        for (ImageButton imageButton : imageButtons)
            imageButton.setEnabled(true);
    }

    public void disableButtons() {
        for (ImageButton imageButton : imageButtons)
            imageButton.setEnabled(false);
    }

}
