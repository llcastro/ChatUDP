
package ChatUDP.cliente;

import ChatUDP.model.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luis
 */
public class TableModelCliente extends AbstractTableModel {
    
    private List<User> lista;
    String[] headerList = {"IP", "Porta", "Nome"};
    Class[] classes = {String.class, String.class, String.class};

    public TableModelCliente(List<User> lista) {
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
        User entity = null;
        entity = lista.get(rowIndex);
        return entity;
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
