import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-05
 * Time: 10:27
 */
public class 一个简单的HTTP客户端 {
    public static void main(String[] args) throws IOException {
        // HTTP 请求 = 请求行 + 请求头们 + 可选的请求体
        // 请求行 = 方法 + URL + 版本
        // GET / HTTP/1.0\r\n
        // \r\n
        String request =  "GET / HTTP/1.0\r\n"
                + "\r\n";

        // 想办法通过 OS 提供的服务（经由传输层，将数据发送给 127.0.0.1 主机上的 8080 端口代表的进程）
        Socket socket = new Socket("182.254.132.183", 8080);
        InputStream is = socket.getInputStream();   // 获取对方发送过来的数据
        OutputStream os = socket.getOutputStream(); // 发送给对方的数据

        // 发送
        byte[] message = request.getBytes("utf-8");
        os.write(message);
        os.flush();

        // 接受
        Scanner scanner = new Scanner(is, "UTF-8");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }

        // 关闭
        socket.close();
    }
}
