import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SimulationTable {
    private SimulationData data;

    public SimulationTable(SimulationData data) {
        this.data = data;
    }

    public JPanel createTables() {
        String[] columnNames = {
                "Customer", "IAT", "Service Time", "Clock Time", "Start", "End", "In Queue", "In System", "Wait Time", "Time In System", "Idle Server"
        };

        Object[][] simulationTableData = new Object[data.custNum.size()][columnNames.length];
        for (int i = 0; i < data.custNum.size(); i++) {
            simulationTableData[i][0] = data.custNum.get(i);
            simulationTableData[i][1] = data.Iat.get(i);
            simulationTableData[i][2] = data.serviceTime.get(i);
            simulationTableData[i][3] = data.clockTime.get(i);
            simulationTableData[i][4] = data.serviceStart.get(i);
            simulationTableData[i][5] = data.serviceEnd.get(i);
            simulationTableData[i][6] = data.numInQueue.get(i);
            simulationTableData[i][7] = data.numInSystem.get(i);
            simulationTableData[i][8] = data.waitingTime.get(i);
            simulationTableData[i][9] = data.timeInSystem.get(i);
            simulationTableData[i][10] = data.serverIdleTime.get(i);
        }

        DefaultTableModel simulationModel = new DefaultTableModel(simulationTableData, columnNames);
        JTable simulationTable = new JTable(simulationModel);
        TableCellRenderer renderer = new AlternatingColumnColorRenderer();
        for (int i = 0; i < simulationTable.getColumnCount(); i++) {
            simulationTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        JScrollPane simulationScrollPane = new JScrollPane(simulationTable);

        String[] statisticsColumnNames = {"Statistics", "Value"};
        Object[][] statisticsTableData = {
                {"Average Waiting Time", data.calculateAverageWaitingTime()},
                {"Probability of Waiting", data.calculateProbabilityOfWaiting()},
                {"Average Time in System", data.calculateAverageTimeInSystem()},
                {"Average Service Time", data.calculateAverageServiceTime()},
                {"Server Utilization", data.calculateServerUtilization()},
                {"Average Number in System", data.calculateAverageNumInSystem()},
                {"Average Number in Queue", data.calculateAverageNumInQueue()},
                {"Probability that Server is Idle", data.calculateProbabilityServerIdle()}
        };

        DefaultTableModel statisticsModel = new DefaultTableModel(statisticsTableData, statisticsColumnNames);
        JTable statisticsTable = new JTable(statisticsModel);

        JScrollPane statisticsScrollPane = new JScrollPane(statisticsTable);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(simulationScrollPane);
        panel.add(statisticsScrollPane);

        return panel;
    }

    public static void main(String[] args) {
        SimulationData simulationData = new SimulationData();
        simulationData.runSimulation();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulation Table");
            SimulationTable table = new SimulationTable(simulationData);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(table.createTables());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
