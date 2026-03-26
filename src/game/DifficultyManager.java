package game;

/**
 * DifficultyManager — central brain for all difficulty scaling.
 *
 * Two axes:
 *   Level (1-3): set once before the game starts (1 = hardest, 3 = baseline)
 *   Day   (1-3): read live from dayCounter each time a getter is called
 *
 * Usage:
 *   gp.diffM.getMonsterSpeed()   — current speed value for monsters
 *   gp.diffM.getMonsterCount()   — how many monsters spawn tonight
 *   gp.diffM.getApplesPerTree()  — apples spawned per apple tree
 *   gp.diffM.getWoodCount()      — total wood piles spawned on map
 *   gp.diffM.getDayDuration()    — daytime length in seconds (shorter on day 3)
 *   gp.diffM.getMonsterKnockDialogue() — fake-distress text per day
 *   gp.diffM.getNPCKnockDialogue()     — friendly survivor text per day
 */
public class DifficultyManager {

    panel gp;

    /**
     * Level set by player on landing page.
     * 3 = baseline, 2 = medium, 1 = hardest.
     * Defaults to 3 so the game is always playable without selection.
     */
    public int level = 3;

    public DifficultyManager(panel gp) {
        this.gp = gp;
    }

    // ---------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------
    private int getDay() {
        return gp.dC.dayCount;
    }

    // ---------------------------------------------------------------
    // Monster scaling
    // ---------------------------------------------------------------

    /**
     * Base monster speed 4, increased by day progress and level difficulty.
     * Day 1 / Level 3 → 4  (baseline)
     * Day 2           → +1
     * Day 3           → +2
     * Level 2         → additional +1
     * Level 1         → additional +2
     */
    public int getMonsterSpeed() {
        int speed = 4;
        int day = getDay();
        if (day == 2)      speed += 1;
        else if (day >= 3) speed += 2;
        if (level == 2)    speed += 1;
        else if (level == 1) speed += 2;
        return speed;
    }

    /**
     * How many monsters spawn at the start of each night.
     * Day 1 → 1
     * Day 2 → 2
     * Day 3 → 2 or 3 (random)
     */
    public int getMonsterCount() {
        int day = getDay();
        if (day == 1) return 1;
        if (day == 2) return 2;
        return (Math.random() < 0.5) ? 2 : 3;
    }

    // ---------------------------------------------------------------
    // Resource scaling (affected by level only, not day)
    // ---------------------------------------------------------------

    /**
     * Apples spawned per apple tree.
     * Level 3 → 2, Level 2 → 1, Level 1 → 1
     */
    public int getApplesPerTree() {
        return (level == 3) ? 2 : 1;
    }

    /**
     * Total number of wood piles placed on the map.
     * Level 3 → 15 (all), Level 2 → 10, Level 1 → 7
     */
    public int getWoodCount() {
        if (level == 3) return 15;
        if (level == 2) return 10;
        return 7;
    }

    // ---------------------------------------------------------------
    // Day duration
    // ---------------------------------------------------------------

    /**
     * Daytime length in ticks (0.016 per tick ≈ 60fps).
     * Day 3 is slightly shorter to add final-day pressure.
     */
    public float getDayDuration() {
        return (getDay() >= 3) ? 22f : 27f;
    }

    // ---------------------------------------------------------------
    // Knock dialogues — change per day
    // ---------------------------------------------------------------

    /**
     * What the monster says when pretending to be human.
     * Day 1: obviously fake / broken speech
     * Day 2: slightly more convincing
     * Day 3: most manipulative
     */
    public String getMonsterKnockDialogue() {
        switch (getDay()) {
            case 1:
                return "Hello?! Is anyone in there?! Pleasea needy help!\n" +
                       "Something is with here me!\n" +
                       "Please open just the door! I am person, I promeise!";
            case 2:
                return "Please! I'm hurt and I can't keep running!\n" +
                       "There's something in the trees and it's getting closer.\n" +
                       "Just let me in — I won't be any trouble, I swear.";
            default:
                return "I found the others. The ones who were here before you.\n" +
                       "They told me you'd be here. They said you'd help.\n" +
                       "Open the door. We need to leave together. Right now.";
        }
    }

    /**
     * What the friendly NPC says when knocking.
     * Day 1: simple and straightforward
     * Day 2: more detailed, slightly urgent
     * Day 3: knowledgeable, offers real value to make trust harder to deny
     */
    public String getNPCKnockDialogue() {
        switch (getDay()) {
            case 1:
                return "Hello? Is anyone there?\n" +
                       "I have supplies — food, wood. I just need shelter.\n" +
                       "I promise I am not one of them.";
            case 2:
                return "Hey! Over here — I saw your light from the road.\n" +
                       "I've been out here since yesterday. I have food.\n" +
                       "Please, just for the night.";
            default:
                return "I know what's out there. I've survived two nights already.\n" +
                       "I know how it thinks. Let me in and I can help you.\n" +
                       "We stand a better chance together.";
        }
    }
}
