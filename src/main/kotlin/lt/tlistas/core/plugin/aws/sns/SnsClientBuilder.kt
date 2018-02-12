package lt.tlistas.core.plugin.aws.sns

import com.amazonaws.services.sns.AmazonSNSClientBuilder

class SnsClientBuilder{

    fun build() = AmazonSNSClientBuilder.standard().withRegion(System.getenv("AWS_REGION")).build()!!
}