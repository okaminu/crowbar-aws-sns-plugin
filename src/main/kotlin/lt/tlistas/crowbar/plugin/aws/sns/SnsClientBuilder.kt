package lt.tlistas.crowbar.plugin.aws.sns

import com.amazonaws.services.sns.AmazonSNSClientBuilder

class SnsClientBuilder {

    fun build() = AmazonSNSClientBuilder.standard().build()!!
}