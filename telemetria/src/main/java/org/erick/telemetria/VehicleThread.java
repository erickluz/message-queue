/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.erick.telemetria;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;

/**
 *
 * @author erickluz
 */
public class VehicleThread extends Thread {

    private volatile boolean execute = false;
    private JSlider sldBatteryVoltage;
    private JSlider sldBreakPreasure;
    private JSlider sldRotation;
    private JSlider sldSpeed;
    private JSlider sldTemperature;
    private JSlider sldThottle;
    private Channel channelMQ;
    private Connection connectionMQ;

    @Override
    public void run() {
        Logger.getLogger(VehicleThread.class.getName()).log(Level.INFO, "Thread Telemetry started");
        connectRabbitMQ();
        while (true) {
            if (this.execute) {
                try {
                    sendMQ(String.valueOf(this.sldSpeed.getValue()), "panel.speed");
                    sendMQ(String.valueOf(this.sldThottle.getValue()), "panel.throttle");
                    sendMQ(String.valueOf(this.sldBreakPreasure.getValue()), "panel.breakpressure");
                    sendMQ(String.valueOf(this.sldBatteryVoltage.getValue()), "panel.batteryvoltage");
                    sendMQ(String.valueOf(this.sldRotation.getValue()), "ecu.rotation");
                    sendMQ(String.valueOf(this.sldTemperature.getValue()), "ecu.temperature");
                    sleep(10L);
                } catch (Exception ex) {
                    Logger.getLogger(VehicleThread.class.getName()).log(Level.SEVERE, "Error", ex);
                }
            }
        }
    }

    private void sendMQ(String value, String topic) {
        try {
            this.channelMQ.basicPublish("telemetry", topic, null, value.getBytes());
        } catch (Exception e) {
            Logger.getLogger(VehicleThread.class.getName()).log(Level.SEVERE, "Erro to send message", e);
        }
    }
    
    private void connectRabbitMQ() {
        try {
            this.connectionMQ = getFactory().newConnection();
            this.channelMQ = connectionMQ.createChannel();
            this.channelMQ.exchangeDeclare("telemetry", "topic");
        } catch (Exception ex) {
            Logger.getLogger(VehicleThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ConnectionFactory getFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("adm");
        factory.setPassword("adm");
        return factory;
    }

    public void pauseTelemetry() {
        Logger.getLogger(VehicleThread.class.getName()).log(Level.INFO, "Telemetry paused");
        this.execute = false;
    }

    public void startTelemetry() {
        Logger.getLogger(VehicleThread.class.getName()).log(Level.INFO, "Telemetry started");
        this.execute = true;
    }

    public JSlider getSldRotation() {
        return this.sldRotation;
    }

    public void setSldRotation(JSlider sldRotation) {
        this.sldRotation = sldRotation;
    }

    public JSlider getSldBatteryVoltage() {
        return sldBatteryVoltage;
    }

    public void setSldBatteryVoltage(JSlider sldBatteryVoltage) {
        this.sldBatteryVoltage = sldBatteryVoltage;
    }

    public JSlider getSldBreakPreasure() {
        return sldBreakPreasure;
    }

    public void setSldBreakPreasure(JSlider sldBreakPreasure) {
        this.sldBreakPreasure = sldBreakPreasure;
    }

    public JSlider getSldSpeed() {
        return sldSpeed;
    }

    public void setSldSpeed(JSlider sldSpeed) {
        this.sldSpeed = sldSpeed;
    }

    public JSlider getSldTemperature() {
        return sldTemperature;
    }

    public void setSldTemperature(JSlider sldTemperature) {
        this.sldTemperature = sldTemperature;
    }

    public JSlider getSldThottle() {
        return sldThottle;
    }

    public void setSldThottle(JSlider sldThottle) {
        this.sldThottle = sldThottle;
    }


}
