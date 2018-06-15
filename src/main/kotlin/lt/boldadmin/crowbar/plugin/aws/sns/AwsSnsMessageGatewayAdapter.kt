package lt.boldadmin.crowbar.plugin.aws.sns

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.PublishRequest
import lt.tlistas.crowbar.api.ConfirmationMessageGateway

class AwsSnsMessageGatewayAdapter(private val snsClient: AmazonSNS = AmazonSNSClientBuilder.defaultClient(),
                                  private val publishRequest: PublishRequest = PublishRequest()
) : ConfirmationMessageGateway {

    override fun send(message: String, address: String) {
        snsClient.publish(publishRequest
                .withMessage(message)
                .withPhoneNumber(address))
    }
}