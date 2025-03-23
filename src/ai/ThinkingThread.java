package ai;

import model.Champion;

public class ThinkingThread extends Thread {

    private Champion champion;
    private Mindset mindset;
    private int id;
    private boolean running;

    public ThinkingThread(Champion champion, int id) {
        this.champion = champion;
        this.mindset = this.champion.getMindset();
        this.id = id;
        this.running = true;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.mindset.launch(this.champion.getTrail().peek(), 0, -Mindset.INFINITE, Mindset.INFINITE);
            
            if(this.mindset.getVector() != null){
                this.champion.setVector(this.mindset.getVector());
            }
            
        }
    }

    public void stopIt(){
        System.out.println("THREAD :"+this.id+" stopped !");
        this.running=false;
    }
}
