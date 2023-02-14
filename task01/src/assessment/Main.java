package assessment;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Main{

    public static void main(String[] args) throws IOException {
        
        
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        // Socket socket = new Socket(host.getHostAddress(), port);
        Socket socket = new Socket(hostname, port);
        System.out.println("Client connected");

        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        List<Float> numberList = new LinkedList<>();

        // read data from server, convert to float and add to numberList
        String dataFromServer = ois.readUTF();
        System.out.println(dataFromServer);
        String[] numbers = dataFromServer.split(",");
        for(String s : numbers){
            numberList.add(Float.parseFloat(s));
        }
        
        // perform operations for numbers in numberList

        Float mean = calculateMean(numberList);
        
        Float stdDev = calculateStdDev(numberList);

        // Write output to server
        String name = "Fiona Teo Yayuan";
        String email = "fiona.teoy@gmail.com";

        oos.writeUTF(name);
        oos.writeUTF(email);
        oos.writeFloat(mean);
        oos.writeFloat(stdDev);
        oos.flush();
        
        oos.close();
        os.close();
        ois.close();
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
        for (Float num : numberList){
            stdDev += (float) (Math.pow(num-mean, 2));
        }

        return (float)Math.sqrt(stdDev/countOfNumbers);
    }
}