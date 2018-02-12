package lt.tlistas.loginn.backend.plugin.aws.sns

import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.PublishRequest



    fun main(args: Array<String>) {

      send("","")
    }

    fun send(message:String, mobileNumber: String) {
        val snsClient = AmazonSNSClientBuilder.standard().withRegion("eu-west-1").build()

       snsClient.publish(PublishRequest()
                .withMessage(message)
                .withPhoneNumber(mobileNumber))
    }

