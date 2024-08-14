package Contests;

import Graphics.AnimalThread;
import Graphics.CompetitionPanel;
import animals.Animal;

public class CourierTournament extends Tournament {
    public CourierTournament(CompetitionPanel competitionPanel) {
        super(competitionPanel);
    }


    public void setup(Animal[][] animals, double totalDistance) {
        Boolean startFlag = false;
        Scores scores = new Scores();

        for (Animal[] group : animals) {
            int n = group.length;
            Boolean[] flags = new Boolean[n];

            for (int i = 0; i < n; i++) {
                flags[i] = false;
            }

            for (int i = 0; i < n; i++) {
                Boolean finishFlag = (i == n - 1) ? false : flags[i + 1];
                AnimalThread animalThread = new AnimalThread(
                        group[i],
                        totalDistance / n,
                        (i == 0) ? startFlag : flags[i - 1],
                        finishFlag
                );
                new Thread(animalThread).start();
            }

            Referee referee = new Referee("Group" + group.hashCode(), scores, flags[n - 1]);
            new Thread(referee).start();
        }

        // העברת competitionPanel לקונסטרוקטור
        tournamentThread = new TournamentThread(getCompetitionPanel(), scores, startFlag, animals.length);
    }
}