package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int randomNumber;
    private int numberOfAttempts;

    private EditText guessInput;
    private TextView feedbackText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessInput = findViewById(R.id.guessInput);
        feedbackText = findViewById(R.id.feedbackText);
        submitButton = findViewById(R.id.submitButton);

        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        numberOfAttempts = 0;
        feedbackText.setText("Try to guess the number between " + lowerBound + " and " + upperBound);
    }

    public void onSubmitClick(View view) {
        String guessString = guessInput.getText().toString();

        if (!guessString.isEmpty()) {
            int playerGuess = Integer.parseInt(guessString);
            numberOfAttempts++;

            if (playerGuess < lowerBound || playerGuess > upperBound) {
                feedbackText.setText("Please enter a valid number between " + lowerBound + " and " + upperBound);
            } else if (playerGuess < randomNumber) {
                feedbackText.setText("Too low! Try again.");
            } else if (playerGuess > randomNumber) {
                feedbackText.setText("Too high! Try again.");
            } else {
                feedbackText.setText("Congratulations! You guessed the number in " + numberOfAttempts + " attempts.");
                submitButton.setEnabled(false);
            }
        }
    }

    public void onResetClick(View view) {
        initializeGame();
        guessInput.setText("");
        feedbackText.setText("");
        submitButton.setEnabled(true);
    }
}