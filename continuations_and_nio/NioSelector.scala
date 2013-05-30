
import java.nio.channels.{SelectionKey, SelectableChannel}
import java.nio.channels.spi.SelectorProvider
import scala.collection.JavaConversions

/** Handle nio channel selection. */
class NioSelector {

  val selector = SelectorProvider.provider.openSelector()

  def register(channel: SelectableChannel, op: Int, body: => Unit) {
    val callback = {
      () => {
        body
      }
    }
    channel.register(selector, op, callback)
  }

  def selectOnce(timeout: Long) {
    selector.select(timeout)
    val jKeys: java.util.Set[SelectionKey] = selector.selectedKeys
    val keys = JavaConversions.asScalaSet(jKeys).toList
    selector.selectedKeys.clear()
    keys foreach {
      _.interestOps(0)
    }
    val callbacks = keys map {
      _.attachment.asInstanceOf[() => Unit]
    }
    executeCallbacks(callbacks) //Execute callbacks for all selected keys
  }

  def executeCallbacks(callbacks: List[() => Unit]) {
    callbacks foreach {
      _()
    }
  }

  def run() {
    selectLoop(true)
  }

  def selectLoop(continueProcessing: => Boolean) {
    while (continueProcessing) {
      selectOnce(0)
    }
  }
}