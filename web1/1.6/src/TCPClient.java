import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-06
 * Time: 11:38
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        //连接时CLOSED 状态，抽象的
        Socket socket = new Socket("182.254.132.183", 8888);
        //已经是ESTABLISHED 状态
        socket.getInputStream().read();
    }
}
