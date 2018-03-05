package lt.tlistas.crowbar.plugin.aws.sns

import com.amazonaws.services.sns.model.InternalErrorException
import com.amazonaws.services.sns.model.InvalidParameterException
import com.amazonaws.services.sns.model.PublishRequest
import lt.tlistas.crowbar.api.ConfirmationMessageGateway
import lt.tlistas.crowbar.api.exception.ConfirmationMessageGatewayException
import lt.tlistas.crowbar.api.exception.InvalidAddressException

class AwsSmsGatewayAdapter(private val snsClientBuilder: SnsClientBuilder) : ConfirmationMessageGateway {

    override fun send(message: String, address: String) {
        try {
            snsClientBuilder.build().publish(PublishRequest()
                    .withMessage(message)
                    .withPhoneNumber(address))
        } catch (e: InternalErrorException) {
            throw ConfirmationMessageGatewayException("Api exception ${e.message}")
        } catch (e: InvalidParameterException) {
            throw InvalidAddressException("Mobile number $address is not valid")
        }
    }
}