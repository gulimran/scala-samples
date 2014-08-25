package imran.scala.rest

import org.apache.http._
import org.apache.http.client.entity._
import org.apache.http.client.methods._
import org.apache.http.impl.client._
import org.apache.http.message._

object RestClient {

  def parseCommandlineArguments(args: Array[String]): Map[String, List[String]] = {

    def nameValuePair(paramName: String) = {
      def values(commaSeparatedValues: String) =
        commaSeparatedValues.split(",").toList

      val index = args.indexWhere(_ == paramName)
      (paramName, if (index == -1) Nil else values(args(index + 1)))
    }

    Map(nameValuePair("-d"), nameValuePair("-h"))
  }

  def splitByEqual(nameValue: String): Array[String] = nameValue.split("=")

  def headers(params: Map[String, List[String]]) =
    for (nameValue <- params("-h")) yield {
      def tokens = splitByEqual(nameValue)
      new BasicHeader(tokens(0), tokens(1))
  }

  def formEntity(params: Map[String, List[String]]) = {

    def toJavaList(scalaList: List[NameValuePair]) = {
      java.util.Arrays.asList(scalaList.toArray:_*)
    }

    def formParams =
      for (nameValue <- params("-d")) yield {
        def tokens = splitByEqual(nameValue)
        new BasicNameValuePair(tokens(0), tokens(1))
      }

    def formEntity = new UrlEncodedFormEntity(toJavaList(formParams), "UTF-8")
    formEntity
  }

  def handlePostRequest(url: String, params: Map[String, List[String]]) = {
    val httppost = new HttpPost(url)
    headers(params).foreach{ httppost.addHeader(_) }
    httppost.setEntity(formEntity(params))
    val responseBody = new DefaultHttpClient().execute(httppost, new BasicResponseHandler)
    println(responseBody)
  }

  def handleGetRequest(url: String, params: Map[String, List[String]]) = {
    val query = params("-d").mkString("&")
    val httpget = new HttpGet(url.concat("?").concat(query))
    headers(params).foreach{ httpget.addHeader(_) }
    val responseBody = new DefaultHttpClient().execute(httpget, new BasicResponseHandler)
    println(responseBody)
  }

  def handleDeleteRequest(url: String) = {
    val httpdelete = new HttpDelete(url)
    val response = new DefaultHttpClient().execute(httpdelete)
    println(response.getStatusLine)
  }

  def handleOptionsRequest(url: String, params: Map[String, List[String]]) = {
    val httpoptions = new HttpOptions(url)
    headers(params).foreach{ httpoptions.addHeader(_) }
    val response = new DefaultHttpClient().execute(httpoptions)
    println(httpoptions.getAllowedMethods(response))
  }

  def main(args: Array[String]) {
    require(args.size >= 2, "at minmum you should specify action(post, get, delete, options) and url")

    val command = args.head
    val url = args.last
    val params = parseCommandlineArguments(args)

    command match {
      case "post" => handlePostRequest(url, params)
      case "get" => handleGetRequest(url, params)
      case "delete" => handleDeleteRequest(url)
      case "options" => handleOptionsRequest(url, params)
    }
  }


}
