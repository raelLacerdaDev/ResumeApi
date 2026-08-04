package org.example.resumeapi.controllers.handler


import jakarta.servlet.http.HttpServletRequest
import org.example.resumeapi.services.exceptions.DatabaseException
import org.example.resumeapi.services.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.URI
import java.time.Instant


@RestControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValid(e: MethodArgumentNotValidException, request: HttpServletRequest) : ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "One or more fields are invalid!"
        )
        problemDetail.instance = URI.create(request.requestURI)
        problemDetail.title = "Validation Error"
        problemDetail.setProperty("timestamp", Instant.now())

        val errors = e.bindingResult.fieldErrors.map { fieldError ->
            mapOf(
                "field" to fieldError.field,
                "message" to fieldError.defaultMessage
            )
        }
        problemDetail.setProperty("invalid_params", errors)

        return problemDetail
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFound(e: ResourceNotFoundException, request: HttpServletRequest) : ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource Not Found")
        problemDetail.instance = URI.create(request.requestURI)
        problemDetail.title = "Resource Not Found"
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }

    @ExceptionHandler(DatabaseException::class)
    fun database(e: DatabaseException, request: HttpServletRequest) : ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Data integrity violation")
        problemDetail.instance = URI.create(request.requestURI)
        problemDetail.title = "Data integrity violation"
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }

}