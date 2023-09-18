package net.averak.cap

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(nameGenerator = Application.FQCNBeanNameGenerator::class)
open class Application {

    class FQCNBeanNameGenerator : AnnotationBeanNameGenerator() {

        override fun buildDefaultBeanName(definition: BeanDefinition): String {
            return definition.beanClassName!!
        }

    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
