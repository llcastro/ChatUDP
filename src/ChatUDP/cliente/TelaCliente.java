package ChatUDP.cliente;

import ChatUDP.model.PrepareMessages;
import ChatUDP.model.TableModelUsuarios;
import ChatUDP.model.User;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TelaCliente extends javax.swing.JFrame implements Runnable {

    private DatagramSocket dsocket;
    private int port;
    private String message;
    private InetAddress address;

    private ArrayList<User> usersConnected;
    private Thread thread;

    public TelaCliente() {
        initComponents();

        this.jButtonDesconectar.setEnabled(false);

        this.usersConnected = new ArrayList<>();
        jTableLogados.setModel(new TableModelUsuarios(this.usersConnected));

        this.initSocket();
    }

    private void initSocket() {
        try {
            this.dsocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WestPanel = new javax.swing.JPanel();
        jCheckBoxBroadcast = new javax.swing.JCheckBox();
        jScrollPaneTabelaConectados = new javax.swing.JScrollPane();
        jTableLogados = new javax.swing.JTable();
        NorthPanel = new javax.swing.JPanel();
        rigidBox4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jLabelIPServidor = new javax.swing.JLabel();
        rigidBox2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jFormattedTextFieldIPServidor = new javax.swing.JFormattedTextField();
        rigidBox1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jLabelPortaServidor = new javax.swing.JLabel();
        rigidBox6 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jFormattedTextFieldPortaServidor = new javax.swing.JFormattedTextField();
        rigidBox5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jButtonConectar = new javax.swing.JButton();
        rigidBox = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jButtonDesconectar = new javax.swing.JButton();
        CenterPanel = new javax.swing.JPanel();
        jLabelMensagem = new javax.swing.JLabel();
        jScrollPaneTextAreaMensagens = new javax.swing.JScrollPane();
        jTextAreaMensagens = new javax.swing.JTextArea();
        rigidBox3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jTextFieldMensagem = new javax.swing.JTextField();
        jButtonEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 10, 10));

        WestPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        WestPanel.setMaximumSize(new java.awt.Dimension(100, 300));
        WestPanel.setMinimumSize(new java.awt.Dimension(100, 49));
        WestPanel.setPreferredSize(new java.awt.Dimension(350, 430));
        WestPanel.setLayout(new javax.swing.BoxLayout(WestPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jCheckBoxBroadcast.setText("Broadcast");
        WestPanel.add(jCheckBoxBroadcast);

        jTableLogados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneTabelaConectados.setViewportView(jTableLogados);

        WestPanel.add(jScrollPaneTabelaConectados);

        getContentPane().add(WestPanel, java.awt.BorderLayout.WEST);

        NorthPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NorthPanel.setLayout(new javax.swing.BoxLayout(NorthPanel, javax.swing.BoxLayout.LINE_AXIS));
        NorthPanel.add(rigidBox4);

        jLabelIPServidor.setText("IP do Servidor: ");
        NorthPanel.add(jLabelIPServidor);
        NorthPanel.add(rigidBox2);
        jFormattedTextFieldIPServidor.setText("127.0.0.1");
        NorthPanel.add(jFormattedTextFieldIPServidor);
        NorthPanel.add(rigidBox1);

        jLabelPortaServidor.setText("Porta do Servidor");
        NorthPanel.add(jLabelPortaServidor);
        NorthPanel.add(rigidBox6);

        jFormattedTextFieldPortaServidor.setText("22000");
        NorthPanel.add(jFormattedTextFieldPortaServidor);
        NorthPanel.add(rigidBox5);

        jButtonConectar.setText("Conectar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });
        NorthPanel.add(jButtonConectar);
        NorthPanel.add(rigidBox);

        jButtonDesconectar.setText("Desconectar");
        jButtonDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesconectarActionPerformed(evt);
            }
        });
        NorthPanel.add(jButtonDesconectar);

        getContentPane().add(NorthPanel, java.awt.BorderLayout.NORTH);

        CenterPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CenterPanel.setLayout(new javax.swing.BoxLayout(CenterPanel, javax.swing.BoxLayout.Y_AXIS));

        jLabelMensagem.setText("Mensagens");
        CenterPanel.add(jLabelMensagem);

        jTextAreaMensagens.setEditable(false);
        jTextAreaMensagens.setColumns(20);
        jTextAreaMensagens.setRows(5);
        jTextAreaMensagens.setMargin(new java.awt.Insets(30, 30, 30, 30));
        jTextAreaMensagens.setMinimumSize(new java.awt.Dimension(150, 150));
        jScrollPaneTextAreaMensagens.setViewportView(jTextAreaMensagens);

        CenterPanel.add(jScrollPaneTextAreaMensagens);
        CenterPanel.add(rigidBox3);

        jTextFieldMensagem.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        CenterPanel.add(jTextFieldMensagem);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.setMaximumSize(new java.awt.Dimension(400, 25));
        jButtonEnviar.setMinimumSize(new java.awt.Dimension(75, 25));
        jButtonEnviar.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });
        CenterPanel.add(jButtonEnviar);

        getContentPane().add(CenterPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed
        String m;
        setIPPort();
        
        if (this.thread == null) {
            System.out.println("new thread");
            this.thread = new Thread(this);
            this.thread.start();
        }

        this.message = "1#jesus";
        System.out.println("send packet: " + this.message);
        sendMessage(this.message);

        this.jButtonConectar.setEnabled(false);
        this.jButtonDesconectar.setEnabled(true);
    }//GEN-LAST:event_jButtonConectarActionPerformed

    private void jButtonDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesconectarActionPerformed
        try {
            this.message = "5#";
            System.out.println("send packet: " + this.message);

            byte[] byteMessage = this.message.getBytes();
            DatagramPacket dpacket = new DatagramPacket(
                    byteMessage, byteMessage.length, this.address, this.port);
            dsocket.send(dpacket);
        } catch (IOException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDesconectarActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed

        String msg = this.jTextFieldMensagem.getText().toString();
        if (msg.isEmpty()) {
            System.out.println("empty message!");
            return;
        }

        if (jCheckBoxBroadcast.isSelected()) {
            // broadcast
            this.message = "3#999.999.999.999#99999#" + msg;
            sendMessage(this.message);
        } else {
            int row = jTableLogados.getSelectedRow();
            if (row < 0) {
                System.out.println("select a row!");
                return;
            }

            String ipUser = jTableLogados.getValueAt(row, 0).toString();
            int portUser = (int) jTableLogados.getValueAt(row, 1);

            this.message = "3#"
                    + ipUser + "#"
                    + portUser + "#"
                    + msg;

            sendMessage(this.message);
        }
        this.jTextAreaMensagens.setText(
                this.jTextAreaMensagens.getText() + "\nVocê: " + msg);
        this.jTextFieldMensagem.setText("");
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void disconnectChat() {
        this.jTextAreaMensagens.setText("desconectado!");

        this.jButtonConectar.setEnabled(true);
        this.jButtonDesconectar.setEnabled(false);

        this.usersConnected.clear();
        ((AbstractTableModel) jTableLogados.getModel()).fireTableDataChanged();
    }

    private void sendMessage(String protocol) {
        try {
            byte[] byteMessage = protocol.getBytes();
            DatagramPacket dpacket = new DatagramPacket(
                    byteMessage, byteMessage.length, this.address, this.port);
            dsocket.send(dpacket);
            System.out.println("sendMessage: " + protocol);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setIPPort() {
        try {
            if (jFormattedTextFieldPortaServidor.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite a porta do servidor");
                return;
            }
            if (jFormattedTextFieldIPServidor.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite o IP do servidor");
                return;
            }

            this.port = Integer.valueOf(
                    jFormattedTextFieldPortaServidor.getText().toString());

            this.address = InetAddress.getByName(
                    jFormattedTextFieldIPServidor.getText().toString());
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        // update table of users connected
        try {
            while (true) {
                byte[] users = new byte[1000];
                DatagramPacket dpacketReceived = new DatagramPacket(users, users.length);
                System.out.println("-----------------");
                dsocket.receive(dpacketReceived);

                byte[] usersReceived = dpacketReceived.getData();
                int packSize = dpacketReceived.getLength();
                String usersList = new String(usersReceived, 0, packSize).trim();
                System.out.println("received: " + usersList);

                int i = new PrepareMessages(this.usersConnected)
                        .splitConnectedUsers(usersList);
                
                switch (i) {
                    case 2: // receive users list
                        ((AbstractTableModel) jTableLogados.getModel()).fireTableDataChanged();
                        break;
                    case 5: // disconnect
                        this.message = usersList;
                        this.disconnectChat();
                        break;
                    case 4: // receive message
                        String s = new PrepareMessages().separateString(usersList, "#", 3);
                        String ip = new PrepareMessages().separateString(usersList, "#", 1);
                        this.jTextAreaMensagens.setText(
                                this.jTextAreaMensagens.getText() + "\n" + ip + ": " + s);
                        break;
                    default:
                        System.out.println("incorrect protocol: " + usersList);
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CenterPanel;
    private javax.swing.JPanel NorthPanel;
    private javax.swing.JPanel WestPanel;
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JButton jButtonDesconectar;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JCheckBox jCheckBoxBroadcast;
    private javax.swing.JFormattedTextField jFormattedTextFieldIPServidor;
    private javax.swing.JFormattedTextField jFormattedTextFieldPortaServidor;
    private javax.swing.JLabel jLabelIPServidor;
    private javax.swing.JLabel jLabelMensagem;
    private javax.swing.JLabel jLabelPortaServidor;
    private javax.swing.JScrollPane jScrollPaneTabelaConectados;
    private javax.swing.JScrollPane jScrollPaneTextAreaMensagens;
    private javax.swing.JTable jTableLogados;
    private javax.swing.JTextArea jTextAreaMensagens;
    private javax.swing.JTextField jTextFieldMensagem;
    private javax.swing.Box.Filler rigidBox;
    private javax.swing.Box.Filler rigidBox1;
    private javax.swing.Box.Filler rigidBox2;
    private javax.swing.Box.Filler rigidBox3;
    private javax.swing.Box.Filler rigidBox4;
    private javax.swing.Box.Filler rigidBox5;
    private javax.swing.Box.Filler rigidBox6;
    // End of variables declaration//GEN-END:variables
}
