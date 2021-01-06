import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-06
 * Time: 11:36
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //CLOSED是抽象状态，实际上可能不存在
        ServerSocket serverSocket = new ServerSocket(8888);
        //serverSocket 对应的一个“链接”的状态是LISTED(listed)
        while (true) {
            Socket socket = serverSocket.accept();
            //socket 对应的链接是什么状态？
            //socket 对应的链接已经是ESTABLISHED(established) 状态
        }


    }
}
