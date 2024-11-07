package interfaz;

import Classes.Main;
import java.awt.Image;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        StarWarsLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        StarWarsWinsLabel = new javax.swing.JLabel();
        colaStarwarsUi1 = new interfaz.ColaUi();
        colaStarwarsUi2 = new interfaz.ColaUi();
        colaStarwarsUi3 = new interfaz.ColaUi();
        colaStarwarsUiRef = new interfaz.ColaUi();
        Panel = new javax.swing.JPanel();
        uiStarWarsId = new javax.swing.JLabel();
        uiStarTrekId = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        StarWarsPer = new javax.swing.JLabel();
        StarTrekPer = new javax.swing.JLabel();
        StarWarsWinLabel = new javax.swing.JLabel();
        StarTrekWinLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        calidadLamboUI = new javax.swing.JLabel();
        lamboHP = new javax.swing.JLabel();
        calidadBgUi = new javax.swing.JLabel();
        bugattiHP = new javax.swing.JLabel();
        stage = new javax.swing.JLabel();
        sliderPane = new javax.swing.JPanel();
        timeSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        winnersPane = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        winnersScrollPane = new javax.swing.JScrollPane();
        winnersLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        StarTrekLogo = new javax.swing.JLabel();
        StarTrekWinsLabel = new javax.swing.JLabel();
        colaStartrekUi1 = new interfaz.ColaUi();
        colaStartrekUi2 = new interfaz.ColaUi();
        colaStartrekUi3 = new interfaz.ColaUi();
        colaStartrekUiRef = new interfaz.ColaUi();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StarWarsLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/logoZelda.png"))); // NOI18N
        jPanel3.add(StarWarsLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 120));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 233, 25));
        jLabel1.setText("Juegos ganados");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        StarWarsWinsLabel.setBackground(new java.awt.Color(0, 0, 0));
        StarWarsWinsLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        StarWarsWinsLabel.setForeground(new java.awt.Color(255, 233, 25));
        StarWarsWinsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StarWarsWinsLabel.setText("0");
        jPanel3.add(StarWarsWinsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 70, 30));

        colaStarwarsUi1.setBackground(new java.awt.Color(204, 255, 255));
        colaStarwarsUi1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel3.add(colaStarwarsUi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        colaStarwarsUi2.setBackground(new java.awt.Color(204, 255, 255));
        colaStarwarsUi2.setForeground(new java.awt.Color(0, 204, 204));
        jPanel3.add(colaStarwarsUi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        colaStarwarsUi3.setBackground(new java.awt.Color(204, 255, 255));
        colaStarwarsUi3.setForeground(new java.awt.Color(0, 204, 204));
        jPanel3.add(colaStarwarsUi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        colaStarwarsUiRef.setBackground(new java.awt.Color(204, 255, 255));
        colaStarwarsUiRef.setForeground(new java.awt.Color(0, 204, 204));
        jPanel3.add(colaStarwarsUiRef, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 330, 600));

        Panel.setBackground(new java.awt.Color(153, 153, 153));
        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        uiStarWarsId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        uiStarWarsId.setForeground(new java.awt.Color(255, 255, 255));
        uiStarWarsId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel.add(uiStarWarsId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 230, 20));

        uiStarTrekId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        uiStarTrekId.setForeground(new java.awt.Color(255, 255, 255));
        uiStarTrekId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel.add(uiStarTrekId, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 230, 30));

        statusLabel.setBackground(new java.awt.Color(0, 0, 0));
        statusLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 490, 30));
        Panel.add(StarWarsPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, 220));
        Panel.add(StarTrekPer, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 230, 220));

        StarWarsWinLabel.setBackground(new java.awt.Color(255, 255, 0));
        StarWarsWinLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        StarWarsWinLabel.setForeground(new java.awt.Color(255, 51, 0));
        StarWarsWinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel.add(StarWarsWinLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 230, 20));

        StarTrekWinLabel.setBackground(new java.awt.Color(0, 204, 51));
        StarTrekWinLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        StarTrekWinLabel.setForeground(new java.awt.Color(255, 0, 0));
        StarTrekWinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel.add(StarTrekWinLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 230, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Calidad:");
        Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Calidad:");
        Panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HP:");
        Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 20, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("HP:");
        Panel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, -1, -1));

        calidadLamboUI.setForeground(new java.awt.Color(255, 255, 255));
        calidadLamboUI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Panel.add(calidadLamboUI, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 60, -1));

        lamboHP.setForeground(new java.awt.Color(255, 255, 255));
        lamboHP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Panel.add(lamboHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 60, -1));

        calidadBgUi.setForeground(new java.awt.Color(255, 255, 255));
        calidadBgUi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Panel.add(calidadBgUi, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 50, -1));

        bugattiHP.setForeground(new java.awt.Color(255, 255, 255));
        bugattiHP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Panel.add(bugattiHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 60, -1));

        stage.setBackground(new java.awt.Color(204, 204, 204));
        stage.setMaximumSize(new java.awt.Dimension(2489, 168));
        stage.setMinimumSize(new java.awt.Dimension(2489, 168));
        Panel.add(stage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 400));

        jPanel2.add(Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 500, 400));

        sliderPane.setBackground(new java.awt.Color(255, 153, 0));
        sliderPane.setForeground(new java.awt.Color(255, 255, 255));
        sliderPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timeSlider.setBackground(new java.awt.Color(255, 255, 255));
        timeSlider.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setMaximum(60);
        timeSlider.setMinimum(1);
        timeSlider.setMinorTickSpacing(5);
        timeSlider.setPaintLabels(true);
        timeSlider.setPaintTicks(true);
        timeSlider.setToolTipText("");
        timeSlider.setValue(10);
        timeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSliderStateChanged(evt);
            }
        });
        sliderPane.add(timeSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 260, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Seleccionar tiempo de simulacion");
        sliderPane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 30));

        jPanel2.add(sliderPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, 300, 90));

        winnersPane.setBackground(new java.awt.Color(153, 153, 153));
        winnersPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ganadores");
        winnersPane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        winnersScrollPane.setViewportView(winnersLabel);

        winnersPane.add(winnersScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 390, 50));

        jPanel2.add(winnersPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, 420, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 224, 64));
        jLabel2.setText("Juegos ganados");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel1.add(StarTrekLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 180, 120));

        StarTrekWinsLabel.setBackground(new java.awt.Color(51, 51, 51));
        StarTrekWinsLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        StarTrekWinsLabel.setForeground(new java.awt.Color(255, 224, 64));
        StarTrekWinsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StarTrekWinsLabel.setText("0");
        jPanel1.add(StarTrekWinsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 70, 30));

        colaStartrekUi1.setBackground(new java.awt.Color(204, 255, 255));
        colaStartrekUi1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.add(colaStartrekUi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        colaStartrekUi2.setBackground(new java.awt.Color(204, 255, 255));
        colaStartrekUi2.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.add(colaStartrekUi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        colaStartrekUi3.setBackground(new java.awt.Color(204, 255, 255));
        colaStartrekUi3.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.add(colaStartrekUi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        colaStartrekUiRef.setBackground(new java.awt.Color(204, 255, 255));
        colaStartrekUiRef.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.add(colaStartrekUiRef, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, 330, 600));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Naves.jpg"))); // NOI18N
        jPanel2.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSliderStateChanged
        // TODO add your handling code here:
        Main.ia.setSimulationTime(this.timeSlider.getValue());
    }//GEN-LAST:event_timeSliderStateChanged

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel StarTrekLogo;
    private javax.swing.JLabel StarTrekPer;
    private javax.swing.JLabel StarTrekWinLabel;
    private javax.swing.JLabel StarTrekWinsLabel;
    private javax.swing.JLabel StarWarsLogo;
    private javax.swing.JLabel StarWarsPer;
    private javax.swing.JLabel StarWarsWinLabel;
    private javax.swing.JLabel StarWarsWinsLabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel bugattiHP;
    private javax.swing.JLabel calidadBgUi;
    private javax.swing.JLabel calidadLamboUI;
    private interfaz.ColaUi colaStartrekUi1;
    private interfaz.ColaUi colaStartrekUi2;
    private interfaz.ColaUi colaStartrekUi3;
    private interfaz.ColaUi colaStartrekUiRef;
    private interfaz.ColaUi colaStarwarsUi1;
    private interfaz.ColaUi colaStarwarsUi2;
    private interfaz.ColaUi colaStarwarsUi3;
    private interfaz.ColaUi colaStarwarsUiRef;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lamboHP;
    private javax.swing.JPanel sliderPane;
    private javax.swing.JLabel stage;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JSlider timeSlider;
    private javax.swing.JLabel uiStarTrekId;
    private javax.swing.JLabel uiStarWarsId;
    private javax.swing.JLabel winnersLabel;
    private javax.swing.JPanel winnersPane;
    private javax.swing.JScrollPane winnersScrollPane;
    // End of variables declaration//GEN-END:variables

    private void init() {
        setTitulosColas();
        setImageLabel(StarWarsLogo, "src/imgs/StarWarsLogo.png");
        setImageLabel(StarTrekLogo, "src/imgs/StarTrekLogo.png");
        setImageLabel(stage, "src/imgs/stage.jpg");
    }

    public void setPersonajesImgsUi() {

        Random r = new Random();
        int numStarWars = r.nextInt(4) + 1;
        int numStarTrek = r.nextInt(4) + 1;

        String picStarWars = Integer.toString(numStarWars);
        String picStarTrek = Integer.toString(numStarTrek);

        setImageLabel(StarWarsPer, ("src/imgs/starwars" + picStarWars + ".png"));
        setImageLabel(StarTrekPer, ("src/imgs/startrek" + picStarTrek + ".png"));
    }

    private void setTitulosColas() {
        this.colaStarwarsUi1.setTitulo("Cola de prioridad 1");
        this.colaStarwarsUi2.setTitulo("Cola de prioridad 2");
        this.colaStarwarsUi3.setTitulo("Cola de prioridad 3");
        this.colaStarwarsUiRef.setTitulo("Cola de refuerzo");

        this.colaStartrekUi1.setTitulo("Cola de prioridad 1");
        this.colaStartrekUi2.setTitulo("Cola de prioridad 2");
        this.colaStartrekUi3.setTitulo("Cola de prioridad 3");
        this.colaStartrekUiRef.setTitulo("Cola de refuerzo");
    }

    private void setImageLabel(JLabel labelName, String root) {
        ImageIcon img = new ImageIcon(root);
        Icon icon = new ImageIcon(img.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }
    
    // set StarWars and StarTrek (images)

    public ColaUi getColaStarwarsUi1() {
        return colaStarwarsUi1;
    }

    public ColaUi getColaStarwarsUi2() {
        return colaStarwarsUi2;
    }

    public ColaUi getColaStarwarsUi3() {
        return colaStarwarsUi3;
    }

    public ColaUi getColaStarwarsUiRef() {
        return colaStarwarsUiRef;
    }

    public ColaUi getColaStartrekUi1() {
        return colaStartrekUi1;
    }

    public ColaUi getColaStartrekUi2() {
        return colaStartrekUi2;
    }

    public ColaUi getColaStartrekUi3() {
        return colaStartrekUi3;
    }

    public ColaUi getColaStartrekUiRef() {
        return colaStartrekUiRef;
    }

    public JLabel getWinnersLabel() {
        return this.winnersLabel;
    }

    public JLabel getStarWarsWinsLabel() {
        return StarWarsWinsLabel;
    }

    public JLabel getStarTrekWinsLabel() {
        return StarTrekWinsLabel;
    }

    public JLabel getUiStarWarsId() {
        return uiStarWarsId;
    }
    
    public JLabel getUiStarTrekId() {
        return uiStarTrekId;
    }

    public JLabel getStatusLabel() {
        return this.statusLabel;
    }

    public JLabel getStarWarsWinnerLabel() {
        return this.StarWarsWinLabel;
    }

    public JLabel getStarTrekWinnerLabel() {
        return this.StarTrekWinLabel;
    }
    
    
    public JLabel getCalidadStarWarsUi() {
        return this.calidadLamboUI;
    }

    public JLabel getCalidadStarTrekUi() {
        return this.calidadBgUi;
    }

    public JLabel getStarWarsHP() {
        return this.lamboHP;
    }

    public JLabel getStarTrekHP() {
        return this.bugattiHP;

    }
}
