package lt.tlistas.test.unit

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.InternalErrorException
import com.amazonaws.services.sns.model.InvalidParameterException
import com.nhaarman.mockito_kotlin.*
import lt.tlistas.core.api.exception.GeocodeGatewayException
import lt.tlistas.core.api.exception.InvalidMobileNumberException
import lt.tlistas.core.api.exception.LocationNotFoundException
import lt.tlistas.core.api.exception.SmsGatewayException
import lt.tlistas.core.api.type.Location
import lt.tlistas.core.plugin.aws.sns.AwsSmsGatewayAdapter
import lt.tlistas.core.plugin.aws.sns.SnsClientBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class AwsSmsGatewayAdapterTest {

    @Mock
    private lateinit var snsClientBuilderMock: SnsClientBuilder

    private lateinit var awsSmsGatewayAdapter: AwsSmsGatewayAdapter

    @Rule
    @JvmField
    val expectedException = ExpectedException.none()!!

    @Before
    fun setUp() {
        awsSmsGatewayAdapter = AwsSmsGatewayAdapter(snsClientBuilderMock)
    }


    @Test
    fun `Send sms to number`() {
        val amazonSNS = mock<AmazonSNS>()
        doReturn(amazonSNS).`when`(snsClientBuilderMock).build()

        awsSmsGatewayAdapter.send("message", "number")

        verify(amazonSNS).publish(any())
    }

    @Test
    fun `Throws smsGatewayException on Api exception `() {
        expectedException.expect(SmsGatewayException::class.java)

        doAnswer {
            throw mock<InternalErrorException>()
        }.`when`(snsClientBuilderMock).build()

        awsSmsGatewayAdapter.send("message", "number")
    }

    @Test
    fun `Throws invalidNumberException `() {
        expectedException.expect(InvalidMobileNumberException::class.java)

        doAnswer {
            throw mock<InvalidParameterException>()
        }.`when`(snsClientBuilderMock).build()

        awsSmsGatewayAdapter.send("message", "number")
    }
}