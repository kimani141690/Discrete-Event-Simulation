
import javax.swing.*;
        import javax.swing.table.DefaultTableCellRenderer;
        import javax.swing.table.TableCellRenderer;
        import java.awt.*;

public class color extends DefaultTableCellRenderer {
    private static final Color HIGHLIGHT_COLOR = new Color(173, 216, 230); // Light blue color

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column % 2 == 0) {
            cellComponent.setBackground(HIGHLIGHT_COLOR);
        } else {
            cellComponent.setBackground(Color.WHITE);
        }

        return cellComponent;
    }
}
