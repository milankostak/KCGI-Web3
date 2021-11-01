package edu.kcg.web3.lecture05.controller

//import org.springframework.boot.web.servlet.error.ErrorController
//import org.springframework.stereotype.Controller
//import org.springframework.ui.Model
//import org.springframework.ui.set
//import org.springframework.web.bind.annotation.GetMapping
//import javax.servlet.RequestDispatcher
//import javax.servlet.http.HttpServletRequest
//
//
//@Controller
//class CustomErrorController : ErrorController {
//
//    @GetMapping("/error")
//    fun handleError(model: Model, httpRequest: HttpServletRequest): String {
//        val statusCode = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) as Int
//        println(statusCode)
//        val errorMsg = when (statusCode) {
//            400 -> "HTTP Error Code: 400. Bad Request."
//            401 -> "HTTP Error Code: 401. Unauthorized."
//            403 -> "HTTP Error Code: 403. Forbidden."
//            404 -> "HTTP Error Code: 404. Resource not found."
//            405 -> "HTTP Error Code: 405. Method not allowed."
//            500 -> "HTTP Error Code: 500. Internal server error."
//            else -> "Unknown Error"
//        }
//        model["errorMessage"] = errorMsg
//        return "error"
//    }
//
//}