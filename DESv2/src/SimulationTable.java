import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SimulationTable {
    private SimulationData data;

    public SimulationTable(SimulationData data) {
        this.data = data;
    }

    public JScrollPane createTable() {
        String[] columnNames = {
                "Customer", "IAT", "Service Time", "Clock Time", "Start", "End", "In Queue", "In System", "Wait Time", "Time In System", "Idle Server"
        };

        Object[][] tableData = new Object[data.custNum.size()][columnNames.length];
        for (int i = 0; i < data.custNum.size(); i++) {
            tableData[i][0] = data.custNum.get(i);
            tableData[i][1] = data.Iat.get(i);
            tableData[i][2] = data.serviceTime.get(i);
            tableData[i][3] = data.clockTime.get(i);
            tableData[i][4] = data.serviceStart.get(i);
            tableData[i][5] = data.serviceEnd.get(i);
            tableData[i][6] = data.numInQueue.get(i);
            tableData[i][7] = data.numInSystem.get(i);
            tableData[i][8] = data.waitingTime.get(i);
            tableData[i][9] = data.timeInSystem.get(i);
            tableData[i][10] = data.serverIdleTime.get(i);
        }

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);

        // Apply the custom renderer to each column
        TableCellRenderer renderer = new AlternatingColumnColorRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
}
