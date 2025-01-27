package infra.datasource

import java.io._
import java.net.URL
import scala.language.postfixOps

object ImageStorage extends ImageStorageTrait {
}

trait ImageStorageTrait {
  val tmpPath = "/tmp"

  /**
    * urlから画像をダウンロードしてfilePathの場所に保存する
    */
  def download(url: String, filePath: String) = {
    val stream = new URL(url).openStream
    val buf = Stream.continually(stream.read).takeWhile(-1 !=).map(_.byteValue).toArray
    val bw = new BufferedOutputStream(new FileOutputStream(filePath))
    bw.write(buf)
    bw.close
  }

  /**
    * filepathを渡すとそのバイナリを返す
    */
  def binary(path: String): Array[Byte] = {
    val fis = new FileInputStream(path)
    Stream.continually(fis.read).takeWhile(-1 !=).map(_.byteValue).toArray
  }

  /** ファイル名を渡すと一時的な保存場所のpathを返す */
  def getTmpPath(fileName: String): String = {
    return tmpPath + "/" + fileName
  }

}
