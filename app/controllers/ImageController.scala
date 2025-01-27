package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api._
import play.api.mvc._
import org.joda.time.DateTime
import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData.FilePart
import play.api.libs.json._
import play.api.Play.current
import play.api.libs.concurrent.Akka
import akka.actor.Props
import domain.image.ImageRepository
import actors.ImageActor
import actors.ImageGenerateMessage

/** LGTMoonが持っているimagesの情報を返すcontroller */
class ImageController extends BaseControllerTrait {

  def recent = Action.async { request =>
    ImageRepository.imageIds(20).map {
      case None => InternalServerError(JsonBuilder.error("サーバーエラー"))
      case Some(imageIds) => Ok(JsonBuilder.imagesByIds(imageIds.map(_.toInt)))
    }
  }

  def random = Action.async { request =>
    ImageRepository.randomIds().map {
      case None => InternalServerError("データベース接続エラー")
      case Some(ids) => Ok(JsonBuilder.imagesByIds(ids))
    }
  }
}
