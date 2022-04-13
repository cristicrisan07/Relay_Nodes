import java.io.FileWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class ReceiverUDP {



    public static void main(String[] args) throws Exception
    {
        int port=Integer.parseInt(args[2]);
        int destinationPort=Integer.parseInt(args[3]);
        CreateFile.main(new String[]{"ip_"+args[0]+".txt"});
        FileWriter fileWriter=new FileWriter("ip_"+args[0]+".txt");
        System.out.println(args[0]+" listens.");
        InetAddress myIpAddress=InetAddress.getByName(args[0]);
        DatagramSocket ds=new DatagramSocket(port,myIpAddress);
        DatagramSocket senderDS=new DatagramSocket();
        byte[] buff=new byte[1024];
        while(true){
            DatagramPacket dpreceive=new DatagramPacket(buff,buff.length);
            ds.receive(dpreceive);
            String str=new String(dpreceive.getData(),0,dpreceive.getLength());
            String[] splittedMessage=str.split("/");
            if(splittedMessage[1].equals("bye"))
            {
                System.out.println("Server Is Exiting .... BYE");
                fileWriter.write(args[0]+ " exits...BYE");
                fileWriter.close();
                break;
            }
            if(splittedMessage[0].equals(args[0])){

               System.out.println("Packet "+splittedMessage[2]+" received at: "+args[0]);
                fileWriter.write("Packet "+splittedMessage[2]+" received at: "+args[0]+"\n");
            }
            else{ if(!args[1].equals("")) {
                System.out.println("daaa:"+args[0]+"...." +str+ " and in message:"+ splittedMessage[0]+".");
                InetAddress destinationIP = InetAddress.getByName(args[1]);
                DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), destinationIP, destinationPort);
                senderDS.send(dp);
            }
            else {
                //System.out.println("There was an error. IP address not in network: "+args[1]);
                fileWriter.write("There was an error. IP address not in network: "+args[1]);
            }
            }

            buff=new byte[1024];
        }

    }
}