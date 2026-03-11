/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import models.Riddle;

/**
 * Manages all three riddles.
 *
 * - Stores riddle data (question / answer / clue)
 * - Validates player answers
 * - Wrong answer  -> reduces current Day time (penalty)
 * - All 3 solved  -> sets portalSpawned = true  (Task 4 hook)
 */
public class RiddleManager {

    panel gp;

    public Riddle[] riddles       = new Riddle[3];
    public int      solvedCount   = 0;
    public boolean  portalSpawned = false;

    /** Ticks to strip from stateCounter on a wrong answer (300 = 5 s at 60 fps). */
    private static final int WRONG_PENALTY_TICKS = 300;

    // ---------------------------------------------------------------
    public RiddleManager(panel gp) {
        this.gp = gp;
        initRiddles();
    }

    // ---------------------------------------------------------------
    private void initRiddles() {

        riddles[0] = new Riddle(
            0,
            "I have cities, but no houses. Mountains, but no trees. " +
            "Water, but no fish. Roads, but no cars. What am I?",
            "map",
            "Riddle I — Cities without houses, mountains without trees..."
        );

        riddles[1] = new Riddle(
            1,
            "The more you take, the more you leave behind. What am I?",
            "footsteps",
            "Riddle II — The more you take, the more you leave behind..."
        );

        riddles[2] = new Riddle(
            2,
            "I speak without a mouth and hear without ears. " +
            "No body have I, yet I come alive with wind. What am I?",
            "echo",
            "Riddle III — No mouth, no ears, born from the wind..."
        );
    }

    // ---------------------------------------------------------------
    /**
     * Attempt to solve riddle[riddleIndex] with the given raw answer string.
     * @return true if correct (or already solved), false if wrong
     */
    public boolean tryAnswer(int riddleIndex, String rawAnswer) {
        if (riddleIndex < 0 || riddleIndex >= riddles.length) return false;

        Riddle r = riddles[riddleIndex];
        if (r.solved) return true;  // already done

        if (rawAnswer.toLowerCase().trim().equals(r.answer)) {
            r.solved = true;
            solvedCount++;

            appendClueToScroll(r);

            if (solvedCount >= 3) spawnPortal();
            return true;

        } else {
            applyWrongAnswerPenalty();
            return false;
        }
    }

    public Riddle getRiddle(int index) {
        if (index < 0 || index >= riddles.length) return null;
        return riddles[index];
    }

    public boolean isAllSolved() { return solvedCount >= 3; }

    // ---------------------------------------------------------------
    /** Shorten current Day by WRONG_PENALTY_TICKS. Safe if it's Night. */
    private void applyWrongAnswerPenalty() {
        if (gp.dC.currentState == dayCounter.dayNightState.Day) {
            gp.dC.stateCounter -= WRONG_PENALTY_TICKS;
            if (gp.dC.stateCounter < 0) gp.dC.stateCounter = 0;
        }
    }

    /** Append solved riddle's clue into the inventory scroll text. */
    private void appendClueToScroll(Riddle r) {
        String existing = gp.inventory.clueText == null ? "" : gp.inventory.clueText;
        if (!existing.contains(r.clue)) {
            gp.inventory.clueText = existing.isEmpty()
                ? r.clue
                : existing + "\n\n" + r.clue;
            gp.inventory.hasClue = true;
        }
    }

    
    private void spawnPortal() {
        portalSpawned = true;
        gp.objectM.revealPortal();
        System.out.println(" All riddles solved! Portal spawned.");
    }
}
