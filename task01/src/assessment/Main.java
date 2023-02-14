package assessment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Main{

    public static void main(String[] args) throws IOException {
        
        String hostname = args[0];
        InetAddress host = InetAddress.getByName(hostname);
        System.out.println(host.getHostAddress());
        int port = Integer.parseInt(args[1]);

        Socket socket = new Socket(host.getHostAddress(), port);

        
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        List<Float> numberList = new LinkedList<>();
        String dataFromServer = "";
        
        // read data from server, convert to float and add to numberList
        while((dataFromServer=dis.readUTF()) != null){
            Float number = Float.parseFloat(dataFromServer);
            numberList.add(number);
        }

        // perform operations for numbers in numberList

        Float mean = calculateMean(numberList);
        
        Float stdDev = calculateStdDev(numberList);

        // Write output to server
        String name = "Fiona Teo Yayuan";
        String email = "fiona.teoy@gmail.com";

        dos.writeUTF(name);
        dos.writeUTF(email);
        dos.writeFloat(mean);
        dos.writeFloat(stdDev);
        dos.flush();
        
        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();

        socket.close();

    }

    public static Float calculateMean(List<Float> numberList){
        Float sum = 0f;
        int countOfNumbers = numberList.size();
        for (Float f : numberList){
            sum += f;
        }

        Float mean = sum / countOfNumbers;
        return mean;
    }

    public static float calculateStdDev(List<Float> numberList){

        Float mean = calculateMean(numberList);
        int countOfNumbers = numberList.size();
        Float stdDev = 0f;
        for (Float f : numberList){
            stdDev += (float) (Math.pow(f-mean, 2));
        }

        return (float)Math.sqrt(stdDev/countOfNumbers);
    }
}