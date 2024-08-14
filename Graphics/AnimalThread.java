package Graphics;

import animals.Animal;

public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private Boolean startFlag;
    private Boolean finishFlag;

    public AnimalThread(Animal participant, double neededDistance, Boolean startFlag, Boolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        synchronized (startFlag) {
            while (!startFlag) {
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        while (!Thread.currentThread().isInterrupted() && participant.getDistance() < neededDistance) {
            synchronized (participant) {
                participant.updateDistance();
                if (participant.getDistance() >= neededDistance) {
                    synchronized (finishFlag) {
                        finishFlag = true;
                        finishFlag.notify();
                    }
                    break;
                }
            }
            try {
                Thread.sleep(100); // Configurable sleep time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}