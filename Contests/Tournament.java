package Contests;

import Graphics.CompetitionPanel;

public class Tournament {
    private CompetitionPanel competitionPanel;
    protected TournamentThread tournamentThread;

    public Tournament(CompetitionPanel competitionPanel) {
        this.competitionPanel = competitionPanel;
    }

    // מתודה להחזרת ה-CompetitionPanel
    public CompetitionPanel getCompetitionPanel() {
        return competitionPanel;
    }

    public void startTournament() {
        tournamentThread.start();
    }

    public void stopTournament() {
        tournamentThread.interrupt();
    }
}