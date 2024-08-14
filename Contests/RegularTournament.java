package Contests;

import Graphics.CompetitionPanel;

public class RegularTournament extends Tournament {
    private Scores scores;
    private boolean isRelay;
    private int maxRounds;

    public RegularTournament(CompetitionPanel competitionPanel, Scores scores, boolean isRelay, int maxRounds) {
        super(competitionPanel);
        this.scores = scores;
        this.isRelay = isRelay;
        this.maxRounds = maxRounds;
    }

    @Override
    public void startTournament() {
        // שימוש בקונסטרוקטור השני עם כל הפרמטרים
        TournamentThread tournamentThread = new TournamentThread(getCompetitionPanel(), scores, isRelay, maxRounds);
        tournamentThread.start();
    }
}