package lt.boldadmin.crowbar.plugin.aws.sns.test.unit

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import lt.boldadmin.crowbar.plugin.aws.sns.AwsSnsMessageGatewayAdapter
import org.junit.jupiter.api.Test

class AwsSnsMessageGatewayAdapterTest {

    @Test
    fun `Sends SMS to a mobile number`() {
        val message = "some message"
        val mobileNumber = "+37012345678"
        val amazonSnsSpy: AmazonSNS = mockk()
        val publishRequestSpy: PublishRequest = mockk()

        every { publishRequestSpy.withMessage(message) } returns publishRequestSpy
        every { publishRequestSpy.withPhoneNumber(mobileNumber) } returns publishRequestSpy
        every { amazonSnsSpy.publish(any()) } returns mockk()

        AwsSnsMessageGatewayAdapter(amazonSnsSpy, publishRequestSpy).send(message, mobileNumber)

        verify { amazonSnsSpy.publish(any()) }
        verify { publishRequestSpy.withPhoneNumber(mobileNumber) }
        verify { publishRequestSpy.withMessage(message) }
    }
}
