package chap9_14;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class JTableDefaultTableModelTest extends JFrame{

    private DefaultTableModel tableModel;  
    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    
    public JTableDefaultTableModelTest()
    {
        super();
        setTitle("Table");
        setBounds(100,100,500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"A","B"};  
        String [][]tableVales={{"A1","B1"},{"A2","B2"},{"A3","B3"},{"A4","B4"},{"A5","B5"}};
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);  
        getContentPane().add(scrollPane,BorderLayout.CENTER);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        table.addMouseListener(new MouseAdapter(){   
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow();
                Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                aTextField.setText(oa.toString());  
                bTextField.setText(ob.toString());
            }
        });
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
        getContentPane().add(panel,BorderLayout.SOUTH);
        panel.add(new JLabel("A: "));
        aTextField = new JTextField("A4",10);
        panel.add(aTextField);
        panel.add(new JLabel("B: "));
        bTextField = new JTextField("B4",10);
        panel.add(bTextField);
        final JButton addButton = new JButton("Add");  
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String []rowValues = {aTextField.getText(),bTextField.getText()};
                tableModel.addRow(rowValues);  
                int rowCount = table.getRowCount() +1;  
                aTextField.setText("A"+rowCount);
                bTextField.setText("B"+rowCount);
            }
        });
        panel.add(addButton);  
        
        final JButton updateButton = new JButton("Edit");  
        updateButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();
                if(selectedRow!= -1)   
                {
                    tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
                    tableModel.setValueAt(bTextField.getText(), selectedRow, 1);
                    //table.setValueAt(arg0, arg1, arg2)
                }
            }
        });
        panel.add(updateButton);
        
        final JButton delButton = new JButton("Delete");
        delButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();
                if(selectedRow!=-1)  
                {
                    tableModel.removeRow(selectedRow);  
                }
            }
        });
        panel.add(delButton);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JTableDefaultTableModelTest jTableDefaultTableModelTest = new JTableDefaultTableModelTest();
        jTableDefaultTableModelTest.setVisible(true);
    }

}