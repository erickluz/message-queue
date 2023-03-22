/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.erick.telemetria.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author erickluz
 */
public class dashboard extends javax.swing.JFrame {

    private boolean isChartsCreated = false;
    private static final Random random = new Random();
    JFreeChart speedChart;
    JFreeChart throttleChart;
    JFreeChart breakPressureChart;
    JFreeChart batteryVoltageChart;
    JFreeChart rotationChart;
    JFreeChart temperatureChart;
    DynamicTimeSeriesCollection speedDataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
    DynamicTimeSeriesCollection throttleDataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
    DynamicTimeSeriesCollection breakPressureDataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
    DynamicTimeSeriesCollection batteryVoltageDataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
    DynamicTimeSeriesCollection rotationDataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
    DynamicTimeSeriesCollection temperatureDataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
    
    /**
     * Creates new form dashboard
     */
    public dashboard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSpeed = new javax.swing.JPanel();
        pnlThrottle = new javax.swing.JPanel();
        pnlBreakPressure = new javax.swing.JPanel();
        pnlBatteryVoltage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlRotation = new javax.swing.JPanel();
        pnlTemperature = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnlSpeed.setBorder(javax.swing.BorderFactory.createTitledBorder("Speed"));

        javax.swing.GroupLayout pnlSpeedLayout = new javax.swing.GroupLayout(pnlSpeed);
        pnlSpeed.setLayout(pnlSpeedLayout);
        pnlSpeedLayout.setHorizontalGroup(
            pnlSpeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        pnlSpeedLayout.setVerticalGroup(
            pnlSpeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        pnlThrottle.setBorder(javax.swing.BorderFactory.createTitledBorder("Throttle"));

        javax.swing.GroupLayout pnlThrottleLayout = new javax.swing.GroupLayout(pnlThrottle);
        pnlThrottle.setLayout(pnlThrottleLayout);
        pnlThrottleLayout.setHorizontalGroup(
            pnlThrottleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlThrottleLayout.setVerticalGroup(
            pnlThrottleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlBreakPressure.setBorder(javax.swing.BorderFactory.createTitledBorder("Break Preassure"));

        javax.swing.GroupLayout pnlBreakPressureLayout = new javax.swing.GroupLayout(pnlBreakPressure);
        pnlBreakPressure.setLayout(pnlBreakPressureLayout);
        pnlBreakPressureLayout.setHorizontalGroup(
            pnlBreakPressureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBreakPressureLayout.setVerticalGroup(
            pnlBreakPressureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        pnlBatteryVoltage.setBorder(javax.swing.BorderFactory.createTitledBorder("Battery Voltage"));

        javax.swing.GroupLayout pnlBatteryVoltageLayout = new javax.swing.GroupLayout(pnlBatteryVoltage);
        pnlBatteryVoltage.setLayout(pnlBatteryVoltageLayout);
        pnlBatteryVoltageLayout.setHorizontalGroup(
            pnlBatteryVoltageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlBatteryVoltageLayout.setVerticalGroup(
            pnlBatteryVoltageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Fira Sans", 1, 18)); // NOI18N
        jLabel1.setText("Vehicle");

        jLabel2.setFont(new java.awt.Font("Fira Sans", 1, 18)); // NOI18N
        jLabel2.setText("Motor");

        pnlRotation.setBorder(javax.swing.BorderFactory.createTitledBorder("Rotation"));

        javax.swing.GroupLayout pnlRotationLayout = new javax.swing.GroupLayout(pnlRotation);
        pnlRotation.setLayout(pnlRotationLayout);
        pnlRotationLayout.setHorizontalGroup(
            pnlRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        pnlRotationLayout.setVerticalGroup(
            pnlRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        pnlTemperature.setBorder(javax.swing.BorderFactory.createTitledBorder("Temperature"));

        javax.swing.GroupLayout pnlTemperatureLayout = new javax.swing.GroupLayout(pnlTemperature);
        pnlTemperature.setLayout(pnlTemperatureLayout);
        pnlTemperatureLayout.setHorizontalGroup(
            pnlTemperatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlTemperatureLayout.setVerticalGroup(
            pnlTemperatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlRotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlBreakPressure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlBatteryVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlThrottle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThrottle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBreakPressure, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBatteryVoltage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTemperature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupCharts() {
        if (!isChartsCreated) {
            setChartSpeed();
            setChartBatteryVoltage();
            setChartBreakPressure();
            setChartRotation();
            setChartSpeed();
            setChartTemperature();
            setChartThrottle();
            setupConsumer(speedConsumerCallback(), "speed");
            setupConsumer(throttleConsumerCallback(), "throttle");
            setupConsumer(breakPressureConsumerCallback(), "breakpressure");
            setupConsumer(batteryVoltageConsumerCallback(), "batteryvoltage");
            setupConsumer(rotationConsumerCallback(), "rotation");
            setupConsumer(temperatureConsumerCallback(), "temperature");
            isChartsCreated = true;
        }
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        setupCharts();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    private void updateSpeedChart(float speed) {
        updateChart(speed, speedDataset);
    }

    private void updateThrottleChart(Float throttle) {
        updateChart(throttle, throttleDataset);
    }

    private void updateBreakPressureChart(Float breakPressure) {
        updateChart(breakPressure, breakPressureDataset);
    }

    private void updateBatteryVoltageChart(Float batteryVoltage) {
        updateChart(batteryVoltage, batteryVoltageDataset);
    }

    private void updateRotationChart(Float rotation) {
        updateChart(rotation, rotationDataset);
    }

    private void updateTemperatureChart(Float temperature) {
        updateChart(temperature, temperatureDataset);
    }

    private void updateChart(float data, DynamicTimeSeriesCollection dataset) {
        float[] newData = new float[1];
        newData[0] = data;
        dataset.advanceTime();
        dataset.appendData(newData);
    }

    private void setChartSpeed() {
        buildChart(speedChart, pnlSpeed, speedDataset, "Speed", "KM/h", 300);
    }

    private void setChartThrottle() {
        buildChart(throttleChart, pnlThrottle, throttleDataset, "Throttle", "%", 100);
    }

    private void setChartBreakPressure() {
        buildChart(breakPressureChart, pnlBreakPressure, breakPressureDataset, "Pressure", "PSI", 80);
    }

    private void setChartBatteryVoltage() {
        buildChart(batteryVoltageChart, pnlBatteryVoltage, batteryVoltageDataset, "Battery Voltage", "Volts", 24);
    }

    private void setChartRotation() {
        buildChart(rotationChart, pnlRotation, rotationDataset, "Rotation", "RPM", 9000);
    }

    private void setChartTemperature() {
        buildChart(temperatureChart, pnlTemperature, temperatureDataset, "Temperature", "ºC", 200);
    }

    private void buildChart(JFreeChart chart, javax.swing.JPanel panel, DynamicTimeSeriesCollection dataset, String title, String legend, int maxRange) {
//        dataset = new DynamicTimeSeriesCollection(1, (2 * 60), new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2011));
        dataset.addSeries(new float[]{0}, 0, "");
        chart = createChart(dataset, title, legend, maxRange);
        ChartPanel speedPanel = new ChartPanel(chart);
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(speedPanel);
        panel.validate();
    }

    private JFreeChart createChart(final XYDataset dataset, String title, String legend, int maxRange) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                title, "", legend, dataset, true, false, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(0, maxRange);
        return result;
    }

    private float randomValue() {
        return (float) (random.nextGaussian() * 100 / 3);
    }

    private float[] gaussianData() {
        float[] a = new float[2 * 60];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue();
        }
        return a;
    }

    private void setupConsumer(DeliverCallback deliverCallback, String queue) {
        try {
            Channel channel = getRabbitMQConnection().createChannel();
            channel.exchangeDeclare("telemetry", "topic");
            //String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queue, "telemetry", "");
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
            });
            Logger.getLogger(dashboard.class.getName()).log(Level.INFO, "Consumer configured on queue: " + queue);
        } catch (Exception ex) {
            Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Connection getRabbitMQConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("adm");
        factory.setPassword("adm");
        Connection connection = factory.newConnection();
        return connection;
    }

    private DeliverCallback speedConsumerCallback() {
        return (consumerTag, delivery) -> {
            this.updateSpeedChart(Float.valueOf(new String(delivery.getBody(), "UTF-8")));
        };
    }

    private DeliverCallback throttleConsumerCallback() {
        return (consumerTag, delivery) -> {
            this.updateThrottleChart(Float.valueOf(new String(delivery.getBody(), "UTF-8")));
        };
    }

    private DeliverCallback breakPressureConsumerCallback() {
        return (consumerTag, delivery) -> {
            this.updateBreakPressureChart(Float.valueOf(new String(delivery.getBody(), "UTF-8")));
        };
    }

    private DeliverCallback batteryVoltageConsumerCallback() {
        return (consumerTag, delivery) -> {
            this.updateBatteryVoltageChart(Float.valueOf(new String(delivery.getBody(), "UTF-8")));
        };
    }

    private DeliverCallback rotationConsumerCallback() {
        return (consumerTag, delivery) -> {
            this.updateRotationChart(Float.valueOf(new String(delivery.getBody(), "UTF-8")));
        };
    }

    private DeliverCallback temperatureConsumerCallback() {
        return (consumerTag, delivery) -> {
            this.updateTemperatureChart(Float.valueOf(new String(delivery.getBody(), "UTF-8")));
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlBatteryVoltage;
    private javax.swing.JPanel pnlBreakPressure;
    private javax.swing.JPanel pnlRotation;
    private javax.swing.JPanel pnlSpeed;
    private javax.swing.JPanel pnlTemperature;
    private javax.swing.JPanel pnlThrottle;
    // End of variables declaration//GEN-END:variables

}