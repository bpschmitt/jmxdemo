import java.util.Random;

public class SystemStatus implements SystemStatusMBean {
    private Integer numberOfSecondsRunning;
    private String programName;
    private Long numberOfUnixSecondsRunning;
    private Boolean switchStatus;
    private Thread backgroundThread;
    private Integer randomNumOne;
    private Integer randomNumTwo;

    public SystemStatus(String programName) {
        // First we initialize all the metrics
        this.backgroundThread = new Thread();
        this.programName = programName;
        this.numberOfSecondsRunning = 0;
        this.numberOfUnixSecondsRunning = System.currentTimeMillis() / 1000L;
        this.switchStatus = false;
        this.randomNumOne = 0;
        this.randomNumTwo = 0;

        // We will use a background thread to update the metrics
        this.backgroundThread = new Thread(() -> {
            try {
                while (true) {
                    // Every second we update the metrics
                    numberOfSecondsRunning += 1;
                    numberOfUnixSecondsRunning += 1;
                    switchStatus = !switchStatus;
                    randomNumOne = genNumber(100);
                    randomNumTwo = genNumber(1000);
                    Thread.sleep(1000L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.backgroundThread.setName("backgroundThread");
        this.backgroundThread.start();
    }

    private Integer genNumber(Integer range) {
        Random rand = new Random();
        return rand.nextInt(range);
    }


    // Through this getters, defined in the interface SystemStatusMBean,
    // all the metrics will be automatically retrieved

    @Override
    public Integer getNumberOfSecondsRunning() {
        return numberOfSecondsRunning;
    }

    @Override
    public String getProgramName() {
        return programName;
    }

    @Override
    public Long getNumberOfUnixSecondsRunning() {
        return numberOfUnixSecondsRunning;
    }

    @Override
    public Boolean getSwitchStatus() {
        return switchStatus;
    }

    @Override
    public Integer getRandomNumOne() {
        return randomNumOne;
    }

    @Override
    public Integer getRandomNumTwo() {
        return randomNumTwo;
    }
}
