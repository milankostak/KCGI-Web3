package edu.kcg.web3.lecture03b

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", value = ["/hello-servlet"])
class HelloServlet : HttpServlet() {

    private lateinit var message: String

    override fun init() {
        message = "Hello World!"
    }

    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val queryString = request.queryString
        val parameterNames = request.parameterNames

        response.writer.use { writer ->
            writer.println("<html>")
            writer.println("<head>")
            writer.println("<title>The best title</title>")
            writer.println("</head>")
            writer.println("<body>")
            writer.println("<p>$message</p>")
            writer.println("<p>some useful text</p>")
            writer.println("<p>${queryString.replace("&", "&amp;")}</p>")
            while (parameterNames.hasMoreElements()) {
                val parameter = parameterNames.nextElement().toString()
                val value = request.getParameter(parameter)
                writer.println("<p>Parameter: $parameter Value: $value</p>")
            }
            writer.println("</body>")
            writer.println("</html>")
        }
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }

    override fun destroy() {
    }
}