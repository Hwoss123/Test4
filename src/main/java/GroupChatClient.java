import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;


public class GroupChatClient {
    //������ص�����
    private final String HOST = "127.0.0.1"; // ��������ip
    private final int PORT = 10086; //�������˿�
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    //������, ��ɳ�ʼ������
    public GroupChatClient() throws IOException {

        selector = Selector.open();
        //���ӷ�����
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        //���÷�����
        socketChannel.configureBlocking(false);
        //��channel ע�ᵽselector
        socketChannel.register(selector, SelectionKey.OP_READ);
        //�õ�username
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + " �Ѿ�׼�����");

    }

    //�������������Ϣ
    public void sendInfo(String info) {

        info = username + " ˵��" + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

//    ��ȡ�ӷ������˻ظ�����Ϣ
    public void readInfo() {
        try {
            int readChannels = selector.select();
            if(readChannels > 0) {//�п����õ�ͨ��
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    if(key.isReadable()) {
                        //�õ���ص�ͨ��
                        SocketChannel sc = (SocketChannel) key.channel();
                        //�õ�һ��Buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //��ȡ
                        sc.read(buffer);
                        //�Ѷ����Ļ�����������ת���ַ���
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                }
                iterator.remove(); //ɾ����ǰ��selectionKey, ��ֹ�ظ�����
            } else {
                System.out.println("û�п����õ�ͨ��...");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        //�������ǿͻ���
        GroupChatClient chatClient = new GroupChatClient();

        //����һ���߳�, ÿ��3�룬��ȡ�ӷ�������������
        new Thread(() -> {
            while (true) {
                chatClient.readInfo();
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //�������ݸ���������
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            chatClient.sendInfo(s);
        }
    }
}