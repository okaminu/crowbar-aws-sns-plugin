package lt.tlistas.core.plugin.aws.sns

import com.amazonaws.services.sns.model.InternalErrorException
import com.amazonaws.services.sns.model.InvalidParameterException
import com.amazonaws.services.sns.model.PublishRequest
import lt.tlistas.core.api.SmsGateway
import lt.tlistas.core.api.exception.InvalidMobileNumberException
import lt.tlistas.core.api.exception.SmsGatewayException

class AwsSmsGatewayAdapter(private val snsClientBuilder: SnsClientBuilder) : SmsGateway {

    override fun send(message: String, mobileNumber: String) {
        try {
                snsClientBuilder.build().publish(PublishRequest()
                    .withMessage(message)
                    .withPhoneNumber(mobileNumber))
        } catch (e: InternalErrorException) {
            throw SmsGatewayException("Api exception ${e.message}")
        } catch (e: InvalidParameterException) {
            throw InvalidMobileNumberException("Mobile number $mobileNumber is invalid")
        }
    }
}
