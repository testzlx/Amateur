package service.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import service.demo.Hello;
import service.demo.HelloServiceImpl; 
 
public class HelloServiceServer { 
   /** 
    * 启动 Thrift 服务器
    * @param args 
    */ 
   public static void main(String[] args) { 
       try {


           // 设置服务端口为 7911
          // TServerSocket serverTransport = new TServerSocket(7911);
           // 设置协议工厂为 TBinaryProtocol.Factory
          // Factory proFactory = new TBinaryProtocol.Factory();
           // 关联处理器与 Hello 服务的实现
           TProcessor processor = new Hello.Processor(new HelloServiceImpl());
           TServerTransport serverTransport = new TServerSocket(7911);
           TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
           // Use this for a multithreaded server
           // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

           System.out.println("Starting the simple server...");
           server.serve();
       } catch (TTransportException e) {
           e.printStackTrace(); 
       } 
   } 
}

