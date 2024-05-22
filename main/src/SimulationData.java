import java.util.ArrayList;
import java.util.List;

public class SimulationData {
    public List<Integer> custNum;
    public List<Double> Iat;
    public List<Double> serviceTime;
    public List<Double> clockTime;
    public List<Double> serviceStart;
    public List<Double> serviceEnd;
    public List<Integer> numInSystem;
    public List<Integer> numInQueue;
    public List<Double> waitingTime;
    public List<Double> timeInSystem;
    public List<Double> serverIdleTime;

    public SimulationData() {
        initializeData();
    }

    private void initializeData() {
        custNum = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            custNum.add(i);
        }
        Iat = List.of(1.7, 1.4, 1.6, 0.9, 2.1, 2.3, 1.5, 2.5, 2.7, 2.2);
        serviceTime = List.of(1.6, 1.8, 1.4, 0.6, 0.9, 1.2, 0.6, 1.8, 0.4, 1.0);

        clockTime = new ArrayList<>();
        serviceStart = new ArrayList<>();
        serviceEnd = new ArrayList<>();
        numInSystem = new ArrayList<>();
        numInQueue = new ArrayList<>();
        waitingTime = new ArrayList<>();
        timeInSystem = new ArrayList<>();
        serverIdleTime = new ArrayList<>();
    }

    public void runSimulation() {
        double lastServiceEndTime = 0;

        for (int i = 0; i < custNum.size(); i++) {
            double arrivalTime = calculateArrivalTime(i);
            double startServiceTime = calculateServiceStartTime(i, arrivalTime, lastServiceEndTime);
            double endServiceTime = startServiceTime + serviceTime.get(i);

            double idleTime = calculateIdleTime(i, startServiceTime, lastServiceEndTime);
            double waitTime = startServiceTime - arrivalTime;
            double timeInSystemVal = waitTime + serviceTime.get(i);

            int inSystem = calculateNumInSystem(i, arrivalTime, endServiceTime);
            int inQueue = Math.max(0, inSystem - 1);

            // Update values
            lastServiceEndTime = endServiceTime;

            // Add results to lists
            clockTime.add(roundToOneDecimal(arrivalTime));
            serviceStart.add(roundToOneDecimal(startServiceTime));
            serviceEnd.add(roundToOneDecimal(endServiceTime));
            serverIdleTime.add(roundToOneDecimal(idleTime));
            waitingTime.add(roundToOneDecimal(waitTime));
            timeInSystem.add(roundToOneDecimal(timeInSystemVal));
            numInSystem.add(inSystem);
            numInQueue.add(inQueue);
        }
    }

    private double calculateArrivalTime(int index) {
        if (index == 0) {
            return Iat.get(index);
        }
        return Iat.get(index) + clockTime.get(index - 1);
    }

    private double calculateServiceStartTime(int index, double arrivalTime, double lastServiceEndTime) {
        if (index == 0) {
            return arrivalTime;
        }
        return Math.max(arrivalTime, lastServiceEndTime);
    }

    private double calculateIdleTime(int index, double startServiceTime, double lastServiceEndTime) {
        if (index == 0) {
            return 0;
        }
        return startServiceTime - lastServiceEndTime;
    }

    private int calculateNumInSystem(int currentIndex, double currentArrivalTime, double currentEndServiceTime) {
        int count = 0;
        for (int i = 0; i <= currentIndex; i++) {
            if (i < serviceEnd.size() && currentArrivalTime < serviceEnd.get(i)) {
                count++;
            }
        }
        return count;
    }

    private double roundToOneDecimal(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    public double calculateAverageWaitingTime() {
        return waitingTime.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calculateProbabilityOfWaiting() {
        long customersWhoWait = waitingTime.stream().filter(time -> time > 0).count();
        return (double) customersWhoWait / custNum.size();
    }

    public double calculateAverageTimeInSystem() {
        return timeInSystem.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calculateAverageServiceTime() {
        return serviceTime.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calculateServerUtilization() {
        double totalServiceTime = serviceTime.stream().mapToDouble(Double::doubleValue).sum();
        double totalTime = clockTime.get(clockTime.size() - 1) + serviceTime.get(serviceTime.size() - 1);
        return totalServiceTime / totalTime;
    }

    public double calculateAverageNumInSystem() {
        return numInSystem.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    public double calculateAverageNumInQueue() {
        return numInQueue.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    public double calculateProbabilityServerIdle() {
        long idleTimeCount = serverIdleTime.stream().filter(time -> time > 0).count();
        return (double) idleTimeCount / custNum.size();
    }

    public void displayStatistics() {
        System.out.println("Average Waiting Time: " + calculateAverageWaitingTime());
        System.out.println("Probability of Waiting: " + calculateProbabilityOfWaiting());
        System.out.println("Average Time in System: " + calculateAverageTimeInSystem());
        System.out.println("Average Service Time: " + calculateAverageServiceTime());
        System.out.println("Server Utilization: " + calculateServerUtilization());
        System.out.println("Average Number in System: " + calculateAverageNumInSystem());
        System.out.println("Average Number in Queue: " + calculateAverageNumInQueue());
        System.out.println("Probability that Server is Idle: " + calculateProbabilityServerIdle());
    }

    public static void main(String[] args) {
        SimulationData simulationData = new SimulationData();
        simulationData.runSimulation();
        simulationData.displayStatistics();
    }
}