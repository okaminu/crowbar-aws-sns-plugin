package lt.tlistas.crowbar.test.unit

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import com.nhaarman.mockito_kotlin.*
import lt.tlistas.crowbar.plugin.aws.sns.AwsSnsMessageGatewayAdapter
import org.junit.Test

class AwsSnsMessageGatewayAdapterTest {

    @Test
    fun `Sends SMS to a mobile number`() {
        val message = "some message"
        val mobileNumber = "+37012345678"
        val amazonSnsMock = mock<AmazonSNS>()
        val publishRequestMock = mock<PublishRequest>()

        doReturn(publishRequestMock).`when`(publishRequestMock).withMessage(eq(message))
        doReturn(publishRequestMock).`when`(publishRequestMock).withPhoneNumber(eq(mobileNumber))

        AwsSnsMessageGatewayAdapter(amazonSnsMock, publishRequestMock).send(message, mobileNumber)

        verify(amazonSnsMock).publish(any())
        verify(publishRequestMock).withPhoneNumber(eq(mobileNumber))
        verify(publishRequestMock).withMessage(eq(message))
    }
}