import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import scala.concurrent.duration._
import scala.concurrent._
import infra.apiclient.GoogleCustomSearchClient

/** CustomSearchAdapterとGoogleCustomSearchClientの結合テスト */
class CustomSearchClientrTest extends Specification {
  "CustomSearchAdapter" should {
    "imagesで検索結果画像のURL一覧が取得できる" in new WithApplication {
      val future = GoogleCustomSearchClient.search("ご注文はうさぎですか？");
      val urlsOpt = Await.result(future, 10 seconds)
      urlsOpt must not be None
    }
  }
}
