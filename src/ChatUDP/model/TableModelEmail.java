
package ChatUDP.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luis
 */
public class TableModelEmail extends AbstractTableModel {
    
    private List<VOMail> lista;
    String[] headerList = {"Remetente", "Assunto"};
    Class[] classes = {String.class, String.class};

    public TableModelEmail(List<VOMail> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return headerList.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VOMail entity = null;
        entity = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entity.getRemetente();
            case 1:
                return entity.getAssunto();
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int arg0) {
        return classes[arg0];
    }

    @Override
    public String getColumnName(int col) {
        return headerList[col];
    }
}
