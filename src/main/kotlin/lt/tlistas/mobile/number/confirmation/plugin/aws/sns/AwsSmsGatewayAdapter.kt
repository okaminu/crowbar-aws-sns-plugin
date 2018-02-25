package lt.tlistas.mobile.number.confirmation.plugin.aws.sns

import com.amazonaws.services.sns.model.InternalErrorException
import com.amazonaws.services.sns.model.InvalidParameterException
import com.amazonaws.services.sns.model.PublishRequest
import lt.tlistas.mobile.number.confirmation.api.ConfirmationMessageGateway
import lt.tlistas.mobile.number.confirmation.api.exception.ConfirmationMessageGatewayException
import lt.tlistas.mobile.number.confirmation.api.exception.InvalidMobileNumberException

class AwsSmsGatewayAdapter(private val snsClientBuilder: SnsClientBuilder) : ConfirmationMessageGateway {

    override fun send(message: String, address: String) {
        try {
            snsClientBuilder.build().publish(PublishRequest()
                    .withMessage(message)
                    .withPhoneNumber(address))
        } catch (e: InternalErrorException) {
            throw ConfirmationMessageGatewayException("Api exception ${e.message}")
        } catch (e: InvalidParameterException) {
            throw InvalidMobileNumberException("Mobile number $address is invalid")
        }
    }
}