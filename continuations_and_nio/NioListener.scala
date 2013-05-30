
import scala.util.continuations._
import java.net.{InetAddress, InetSocketAddress}
import java.nio.channels.{SelectionKey, SocketChannel, ServerSocketChannel}

class NioListener(selector: NioSelector, hostAddr: InetAddress, port: Int) {

  val serverChannel = ServerSocketChannel.open()
  serverChannel.configureBlocking(false);
  val isa = new InetSocketAddress(hostAddr, port)
  serverChannel.socket.bind(isa)

  def start(continueListening: => Boolean): Unit = {
    reset {
      while (continueListening) {
        val socket = accept()
        NioConnection.newConnection(selector, socket)
      }
    }
  }

  private def accept(): SocketChannel @suspendable = {
    shift {
      k =>
        selector.register(serverChannel, SelectionKey.OP_ACCEPT, {
          val conn = serverChannel.accept()
          conn.configureBlocking(false)
          k(conn)
        })
    }
  }
}