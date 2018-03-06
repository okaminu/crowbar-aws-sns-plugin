package lt.tlistas.crowbar.test.unit

import com.amazonaws.services.sns.AmazonSNS
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import lt.tlistas.crowbar.plugin.aws.sns.AwsSmsGatewayAdapter
import lt.tlistas.crowbar.plugin.aws.sns.SnsClientBuilder
import org.junit.Test

class AwsSmsGatewayAdapterTest {

    @Test
    fun `Sends SMS to a mobile number`() {
        val snsClientBuilderMock = mock<SnsClientBuilder>()
        val amazonSnsMock = mock<AmazonSNS>()
        doReturn(amazonSnsMock).`when`(snsClientBuilderMock).build()

        AwsSmsGatewayAdapter(snsClientBuilderMock).send("someMessage", "+37012345678")

        verify(snsClientBuilderMock).build()
        verify(amazonSnsMock).publish(any())
    }
}