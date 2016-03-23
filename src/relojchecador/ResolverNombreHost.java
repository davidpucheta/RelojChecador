/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 *
 * @author davidreyespucheta
 */
public class ResolverNombreHost {

    public static HashMap<String, String> getHostname() throws SocketException {
        String hostname = "Host Desconocido";
        String ipAddr = "Dirección IP Desconocida";
        String macAddr = "Dirección MAC Desconocida";
        HashMap<String, String> networkData = new HashMap<>();

        try {

            InetAddress addr;
            addr = InetAddress.getLocalHost();
            //Get Hostname (Computername)
            hostname = addr.getHostName().trim();
            //Get IP
            ipAddr = addr.getHostAddress().trim();

            //Get MAC
            NetworkInterface net = NetworkInterface.getByInetAddress(addr);
            byte[] mac = net.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            macAddr = sb.toString().trim();

        } catch (UnknownHostException ex) {
            System.out.println("Host Desconocido, err:" + ex.toString());
        }

        networkData.put("hostname", hostname);
        networkData.put("ip", ipAddr);
        networkData.put("mac", macAddr);
        //System.out.println("Hostname: " + hostname + "\nIP:  " + ipAddr + "\nMAC: " + macAddr);
        return networkData;
    }

}
