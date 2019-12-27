import java.io.OutputStream

interface HttpBody {
  fun contentType(): String
  fun contentLength(): Int
  fun writeTo(output: OutputStream)
}

fun create(body: String, contentType: String): HttpBody {
  return create(body.toByteArray(Charsets.UTF_8), contentType)
}

fun create(body: ByteArray, contentType: String): HttpBody {
  return object : HttpBody {
    override fun contentType(): String = contentType

    override fun contentLength(): Int = body.size

    override fun writeTo(output: OutputStream) = output.write(body)
  }
}