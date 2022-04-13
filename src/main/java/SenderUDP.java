import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;
class SenderUDP {
    public static void main(String[] args) throws Exception
    {

        DatagramSocket ds=new DatagramSocket();
        while(true)
        {
            String str="/packet_nb/";
            //InetAddress myIP=InetAddress.getByName("127.0.0.15");
            Random random =new Random();

            for(int i=0;i<100;i++) {

                InetAddress destinationIP = InetAddress.getByName(args[0]);
                String message=args[(random.nextInt(3))]+str+ i;
                System.out.println(message);
                DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), destinationIP, 2334);
                ds.send(dp);
            }
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("scrie ceva:");
            String name = reader.readLine();
            for(int i=0;i<3;i++){
                InetAddress destinationIP = InetAddress.getByName(args[0]);
                String message=args[i]+"/bye/"+ i;
                System.out.println(message);
                DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), destinationIP, 2334);
                ds.send(dp);
            }

                ds.close();
                break;


        }
    }
}
