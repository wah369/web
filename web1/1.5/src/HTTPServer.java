import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-05
 * Time: 10:51
 */
public class HTTPServer {
    public static void main(String[] args) throws IOException {
        

        String body =   "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>陈沛鑫的网页</title>"
                + "<meta charset='utf-8'>"
                + "</head>"
                + "<body>"
                + "<h1>陈沛鑫</h1>"
                + "<p>比特老师</p>"
                + "</body>"
                + "</html>";
        String response = "HTTP/1.0 200 OK\r\n"
                + "Content-Type: text/html; charset=utf-8\r\n"
                + "Content-Length: 146\r\n"
                + "\r\n"
                + body;

        ServerSocket serverSocket = new ServerSocket(8888);
        // 可以不断的进行 请求-响应周期
        while (true) {
            // 开始一次通信 = 读取请求 + 发送响应
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // 读取请求
            Scanner scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                if (line.isEmpty()) {
                    // 读取到空行，代表请求结束
                    break;
                }
            }

            // 发送响应
            byte[] bytes = response.getBytes("UTF-8");
            os.write(bytes);
            os.flush();

            socket.close();
            // 结束一次通信
        }
    }
}

