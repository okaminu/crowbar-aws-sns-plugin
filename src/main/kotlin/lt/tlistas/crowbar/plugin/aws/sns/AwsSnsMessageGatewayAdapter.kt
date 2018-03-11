package lt.tlistas.crowbar.plugin.aws.sns

import com.amazonaws.services.sns.model.PublishRequest
import lt.tlistas.crowbar.api.ConfirmationMessageGateway

class AwsSnsMessageGatewayAdapter(private val snsClientBuilder: SnsClientBuilder) : ConfirmationMessageGateway {

    override fun send(message: String, address: String) {
        snsClientBuilder.build().publish(PublishRequest()
                .withMessage(message)
                .withPhoneNumber(address))
    }
}