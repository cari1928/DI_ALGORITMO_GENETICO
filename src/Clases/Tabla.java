package Clases;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Tabla extends javax.swing.JFrame {

    DefaultTableModel modelo;
    double[] data;
    String[][] info;
    List<Double[]> datos;

    public Tabla(String[] enca, List<Double[]> datos, String[] extras) {
        initComponents();
        this.datos = datos;

        info = listToArray();
        modelo = new DefaultTableModel(info, enca);
        jtbl_table.setModel(modelo);
        lblExtras.setText("Umbral: " + extras[0] + "; Alpha: " + extras[1]);

        btnEjecucion.setVisible(true);
        setTitle("Resultados");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private String[][] listToArray() {
        String[][] tmp = new String[datos.size()][datos.get(0).length];
        Double[] dato;
        for (int i = 0; i < datos.size(); i++) {
            dato = datos.get(i);

            for (int j = 0; j < dato.length; j++) {
                tmp[i][j] = String.valueOf(dato[j]);
            }
        }

        return tmp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_table = new javax.swing.JTable();
        btnEjecucion = new javax.swing.JButton();
        lblExtras = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtbl_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbl_table);

        btnEjecucion.setText("Ejecución");
        btnEjecucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecucionActionPerformed(evt);
            }
        });

        lblExtras.setText("lblExtras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblExtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEjecucion)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEjecucion)
                    .addComponent(lblExtras))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEjecucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecucionActionPerformed
        Ejecucion obje = new Ejecucion(datos);
    }//GEN-LAST:event_btnEjecucionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecucion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbl_table;
    private javax.swing.JLabel lblExtras;
    // End of variables declaration//GEN-END:variables
}
