package example.com;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Field to hold score
    int score;

    // Field to hold result text

    TextView rollResult;

    // RNG

    Random rand;

    // Fields to hold the dice value;

    int die1;
    int die2;
    int die3;

    // Field to hold score

    TextView scoreText;
    // ArrayList to hold dice values

    ArrayList<Integer> dice;

    // ArrayList to hold dice images

    ArrayList<ImageView> diceImageViews;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        // Sets score to 0
        score = 0;

        // Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to Diceout!", Toast.LENGTH_SHORT).show();

        // Initialize the Random number generator
        rand = new Random();

        // Link Instances to widgets in activity window
        rollResult = (TextView)findViewById(R.id.rollResult);

        // Initialize array list
        dice = new ArrayList<Integer>();
        diceImageViews = new ArrayList<ImageView>();

        // Initialize the score
        scoreText = (TextView)findViewById(R.id.scoreText);

        ImageView die1Image = (ImageView)findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView)findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView)findViewById(R.id.die3Image);

        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);


    }
    // Method that rolls the dice
    public void rollDice(View v) {
        score = 0;
        rollResult.setText("Clicked! :)");
        die1 = rand.nextInt(6) + 1; // From 0 - 5 then adding 1
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        dice.clear();

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        for(int i = 0; i < 3; i++) {
            String ImageName = "die_" + dice.get(i) +".png";

            try {
                InputStream stream = getAssets().open(ImageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(i).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String msg = "";
        if(die1 == die2 && die2 == die3) {
            // Triples
            int scoredelta = die1 * 100;
            msg = "You rolled a triple " +die1+ " worth " +scoredelta+ " points";
            score += scoredelta;
        } else if(die1 == die2 || die1 == die3 || die2 == die3) {
            // Doubles
            if(die1 == die2) {
                msg = "You rolled a double " + die1 + " worth 50 points";
                score += 50;
            } else if(die3 == die1){
                msg = "You rolled a double " + die1 + " worth 50 points";
                score += 50;
            } else {
                msg = "You rolled a double " + die3 + " worth 50 points";
                score += 50;
            }
        } else {
            msg = "You suck, you didn't score any points";
        }
        rollResult.setText(msg);
        scoreText.setText("Score: " +score);
//        Toast.makeText(getApplicationContext(),rngValue,Toast.LENGTH_SHORT).show();
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
}
