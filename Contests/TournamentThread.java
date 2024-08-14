package Contests;

import Graphics.CompetitionPanel;

public class TournamentThread extends Thread {
    private CompetitionPanel competitionPanel;
    private Scores scores;
    private boolean isRelay;
    private int maxRounds;

    // קונסטרוקטור עבור CompetitionPanel בלבד
    public TournamentThread(CompetitionPanel competitionPanel) {
        this.competitionPanel = competitionPanel;
    }

    // קונסטרוקטור נוסף עבור CompetitionPanel ושאר הפרמטרים
    public TournamentThread(CompetitionPanel competitionPanel, Scores scores, boolean isRelay, int maxRounds) {
        this.competitionPanel = competitionPanel;
        this.scores = scores;
        this.isRelay = isRelay;
        this.maxRounds = maxRounds;
    }

    @Override
    public void run() {
        // לוגיקת התחרות עם תמיכה בפרמטרים החדשים
        while (!Thread.currentThread().isInterrupted()) {
            // לוגיקה שמבוססת על הפרמטרים
            try {
                Thread.sleep(100); // הפסקה קצרה כדי לעדכן את המידע
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}