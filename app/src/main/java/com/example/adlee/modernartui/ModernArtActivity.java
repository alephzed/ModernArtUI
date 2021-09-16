package com.example.adlee.modernartui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ModernArtActivity extends Activity {
    final Context context = this;
    int alpha = 127;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);
        final TextView tvTh = (TextView) findViewById(R.id.topHorView);
        final TextView tvLv = (TextView) findViewById(R.id.leftVertView);
        tvLv.setBackgroundColor(getResources().getColor(R.color.reddish));
        tvTh.setBackgroundColor(getResources().getColor(R.color.purpleish));
        final TextView tvRv = (TextView) findViewById(R.id.rightVertView);
        tvRv.setBackgroundColor(getResources().getColor(R.color.yellowish));
        final TextView tvBh = (TextView) findViewById(R.id.bottomHorView);
        tvBh.setBackgroundColor(getResources().getColor(R.color.blueish));

        SeekBar s = (SeekBar) findViewById(R.id.colorChangeBar);
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float pct = (float) (progress / 100.0f);
                int currentColor = blendColors(getResources().getColor(R.color.greenish), getResources().getColor(R.color.blueish), pct);
                tvBh.setBackgroundColor(currentColor);
                currentColor = blendColors(getResources().getColor(R.color.pinkish), getResources().getColor(R.color.reddish), pct);
                tvLv.setBackgroundColor(currentColor);
                currentColor = blendColors(getResources().getColor(R.color.lightpurple), getResources().getColor(R.color.purpleish), pct);
                tvTh.setBackgroundColor(currentColor);
                currentColor = blendColors(getResources().getColor(R.color.brownish), getResources().getColor(R.color.yellowish), pct);
                tvRv.setBackgroundColor(currentColor);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showCustomDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Show desired Dialog
    void showCustomDialog() {

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.black);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(getResources().getText(R.string.learn_more));

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.moma.org";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button cancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        // if button is clicked, close the custom dialog
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        dialog.show();

    }

    /**
     * Method that does the magic of blending two colors from: https://developer.android.com/samples/SlidingTabsColors/src/com.example.android.common/view/SlidingTabStrip.html#l178
     * @param color1
     * @param color2
     * @param ratio
     * @return
     */
    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;
        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }

}
