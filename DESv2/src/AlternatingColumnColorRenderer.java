import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AlternatingColumnColorRenderer extends DefaultTableCellRenderer {
    private static final Color SIMULATION_HIGHLIGHT_COLOR = new Color(173, 216, 230); // Light blue color
    private static final Color STATISTICS_HIGHLIGHT_COLOR = new Color(240, 128, 128); // Light coral color

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.getName() != null && table.getName().equals("simulationTable")) {
            // Use the simulation highlight color
            if (column % 2 == 0) {
                cellComponent.setBackground(SIMULATION_HIGHLIGHT_COLOR);
            } else {
                cellComponent.setBackground(Color.WHITE);
            }
        } else {
            // Use the statistics highlight color
            if (column % 2 == 0) {
                cellComponent.setBackground(STATISTICS_HIGHLIGHT_COLOR);
            } else {
                cellComponent.setBackground(Color.WHITE);
            }
        }

        return cellComponent;
    }
}
