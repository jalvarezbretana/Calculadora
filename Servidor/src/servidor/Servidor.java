/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidor;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.text.DecimalFormat;

/**
 *
 * @author dam2a
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            System.out.println("Creando o socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);
            System.out.println("Aceptando conexiones");
            Socket newSocket = serverSocket.accept();
            System.out.println("Conexión recibida");

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());

            String mensaxe;
            while (!(mensaxe = dis.readUTF()).equals("exit")) {
                double num = dis.readDouble();

                System.out.println("\n2 Mensaxe recibido: " + mensaxe
                        + "\nNúmero recibido: " + num);
                double result = 0;
                String resposta = "";
                DecimalFormat df = new DecimalFormat("0.000");

                switch (mensaxe) {

                    case "1":
                        result = num / 7140;
                        resposta = "O resultado é que " + num + "m² son " + df.format(result) + " campos de fútbol.";
                        break;

                    case "2":
                        result = num * 12;
                        resposta = "Quedan " + df.format(result) + " meses para a súa xubilación.";
                        break;

                    case "3":
                        result = num / 48;
                        resposta = "O resultado é que " + num + " libros son " + df.format(result) + " en bibliografías de Reverte.";
                        break;

                    case "4":
                        result = num - 1.519;
                        if (result > 0) {
                            resposta = "Se pagas " + num + "€/l de gasolina estás pagando " + df.format(result) + "€/l de máis.";
                        } else {
                            resposta = "Ese precio non é real, o máis baixo é 1.519€/l.";
                        }
                        break;

                    default:
                        System.err.println("Erro de introducción");
                        break;

                }
                //enviar output de servidor a cliente
                System.out.println(resposta);
                dos.writeUTF(resposta);

            }

            System.out.println("Pechando o novo socket");
            newSocket.close();
            System.out.println("Pechando o socket servidor");
            serverSocket.close();
            System.out.println("Rematado");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
