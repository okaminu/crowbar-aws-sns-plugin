package lt.tlistas.test.unit

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.InternalErrorException
import com.amazonaws.services.sns.model.InvalidParameterException
import com.nhaarman.mockito_kotlin.*
import lt.tlistas.mobile.number.confirmation.api.exception.ConfirmationMessageGatewayException
import lt.tlistas.mobile.number.confirmation.api.exception.InvalidAddressException
import lt.tlistas.mobile.number.confirmation.plugin.aws.sns.AwsSmsGatewayAdapter
import lt.tlistas.mobile.number.confirmation.plugin.aws.sns.SnsClientBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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
    fun `Sends SMS to a mobile number`() {
        val amazonSNS = mock<AmazonSNS>()
        doReturn(amazonSNS).`when`(snsClientBuilderMock).build()

        awsSmsGatewayAdapter.send(MESSAGE, MOBILE_NUMBER)

        verify(amazonSNS).publish(any())
    }

    @Test
    fun `Throws ConfirmationMessageGatewayException on Api exception `() {
        expectedException.expect(ConfirmationMessageGatewayException::class.java)
        doAnswer {
            throw mock<InternalErrorException>()
        }.`when`(snsClientBuilderMock).build()

        awsSmsGatewayAdapter.send(MESSAGE, MOBILE_NUMBER)
    }

    @Test
    fun `Throws InvalidAddressException`() {
        expectedException.expect(InvalidAddressException::class.java)
        doAnswer {
            throw mock<InvalidParameterException>()
        }.`when`(snsClientBuilderMock).build()

        awsSmsGatewayAdapter.send(MESSAGE, MOBILE_NUMBER)
    }

    companion object {
        private const val MESSAGE = "someMessage"
        private const val MOBILE_NUMBER = "+37012345678"
    }
}