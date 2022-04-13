public class Main {
    public static void main(String[] args) throws Exception {
        String dest1ipAddress="127.0.0.1";
        String dest2ipAddress="127.0.0.2";
        String dest3ipAddress="127.0.0.3";
        String dest1port="2334";
        String dest2port="2335";
        String dest3port="2336";
        Thread t1 = new Thread( () -> {
            try {
                ReceiverUDP.main(new String[]{dest1ipAddress,dest2ipAddress,dest1port,dest2port});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "one");
        Thread t2 = new Thread(()->{
            try {
                ReceiverUDP.main(new String[]{dest2ipAddress,dest3ipAddress,dest2port,dest3port});
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"two");
        Thread t3 = new Thread(()->{
            try {
                ReceiverUDP.main(new String[]{dest3ipAddress,"",dest3port,dest3port});
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"three");

            t1.start();
            t2.start();
            t3.start();
        SenderUDP.main(new String[]{dest1ipAddress,dest2ipAddress,dest3ipAddress});




    }
}
