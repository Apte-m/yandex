package extention

import Specification
import org.junit.jupiter.api.extension.ExtendWith


@Target(AnnotationTarget.FUNCTION,AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(Specification::class)
annotation class BasePath(val path: String)

