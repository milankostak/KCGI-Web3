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
//        val status = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) as Int
//        println(status)
//        val errorMsg = when (status) {
//            400 -> "Http Error Code: 400. Bad Request"
//            401 -> "Http Error Code: 401. Unauthorized"
//            403 -> "Http Error Code: 403. Forbidden."
//            404 -> "Http Error Code: 404. Resource not found"
//            405 -> "Http Error Code: 405. Method not allowed"
//            500 -> "Http Error Code: 500. Internal Server Error"
//            else -> "Error"
//        }
//        model["errorMessage"] = errorMsg
//        return "error"
//    }
//
//}